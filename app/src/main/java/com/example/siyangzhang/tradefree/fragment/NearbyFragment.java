package com.example.siyangzhang.tradefree.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.siyangzhang.tradefree.Bean.Db;
import com.example.siyangzhang.tradefree.R;
import com.example.siyangzhang.tradefree.Util.PictureUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MapView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NearbyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NearbyFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private GoogleMap mMap;
    MapView mMapView;
    private GoogleMap googleMap;
    String TAG="mapTest";

    public NearbyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NearbyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NearbyFragment newInstance(String param1, String param2) {
        NearbyFragment fragment = new NearbyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nearby, container, false);
        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if(mapFragment == null){
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        } else {
            mapFragment.getMapAsync(this);
        }
        return rootView;
    }

    private SupportMapFragment mapFragment;

    private double latitude;
    private double longitude;

    private Db db;
    private SQLiteDatabase dbRead, dbWrite;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

        LocationManager locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 35000, 10, locationListener);
        Location location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location != null){
            latitude = location.getLatitude(); //经度
            longitude = location.getLongitude(); //纬度
        }

        LatLng myLocation = new LatLng(latitude, longitude);
        LatLng dl = new LatLng(40.002133,-83.015911);
        LatLng cl = new LatLng(40.002439,-83.015003);

        db = new Db(getActivity());
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

//        Cursor c = dbRead.rawQuery("SELECT * FROM ITEM WHERE (Latitude-? <= ? AND Latitude-?>= ? AND Longitude-?<=? Longitude-?>=?",
//                new String[]{String.valueOf(latitude),"0.001",String.valueOf(latitude),"-0.001",String.valueOf(longitude),"0.001",String.valueOf(longitude),"-0.001"});

        double LongHigh=longitude+0.0001;
        double LongLow=longitude-0.0001;
        double LatiHigh=latitude+0.0001;
        double LatiLow=latitude-0.0001;

        Cursor c=dbRead.query("ITEM",
                null,
                "Longitude<? AND Longitude>? AND Latitude<? AND Latitude>?",
                new String[] {String.valueOf(LongHigh),String.valueOf(LongLow),String.valueOf(LatiHigh),String.valueOf(LatiLow)},
                null,null,null
        );
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {

                View v = getActivity().getLayoutInflater().inflate(R.layout.info_window_layout, null);

                LatLng latLng = arg0.getPosition();
                String itemT=arg0.getTitle();
                String Det=arg0.getSnippet();
                TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
                TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);

                ImageView im=(ImageView) v.findViewById(R.id.im);

                // Setting the latitude
                tvLat.setText("ITEM:" + itemT);
                //tvLng.setText("DETAIL:"+ Det);

                String path = getActivity().getFileStreamPath(Det).getAbsolutePath();
                BitmapDrawable b = PictureUtils.getScaledDrawable(getActivity(), path);
                im.setImageDrawable(b);
                im.setRotation(90);
                return v;
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                // call an activity(xml file)
            }

        });

        while (c.moveToNext()){
            int latiColIndex=c.getColumnIndex("Latitude");
            int LongColIndex=c.getColumnIndex("Longitude");
            double lati=c.getDouble(latiColIndex);
            double longi=c.getDouble(LongColIndex);
            Log.d(TAG, "onMapReady: "+lati);
            Log.d(TAG,"onMapReady: "+longi);

            int itemTitleIndex=c.getColumnIndex("ItemTitle");
            String itemTitle=c.getString(itemTitleIndex);
            int detialIndex=c.getColumnIndex("Detail");
            String Detail=c.getString(detialIndex);
            int slrIDIndex=c.getColumnIndex("SellerID");
            String slrID=c.getString(slrIDIndex);
            int photoIndex=c.getColumnIndex("Photo");
            String Photo=c.getString(photoIndex);

            LatLng cLocation=new LatLng(lati,longi);
            googleMap.addMarker(new MarkerOptions().position(cLocation).title(itemTitle).snippet(Photo));
        }



        //LatLng sydney = new LatLng(-34, 151);
        //googleMap.addMarker(new MarkerOptions().position(myLocation).title("Me"));
//        googleMap.addMarker(new MarkerOptions().position(dl).title("Marker in Dreese Lab"));
//        googleMap.addMarker(new MarkerOptions().position(cl).title("Marker in Caldwell"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(25));
    }
    LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Log.e("Map", "Location changed : Lat: "
                        + location.getLatitude() + " Lng: "
                        + location.getLongitude());
            }
        }
    };





    //NO NEED TO USE THE CODE BELOW


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onNearbyFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
              //      + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onNearbyFragmentInteraction(Uri uri);
    }



}
