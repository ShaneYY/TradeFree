package com.example.siyangzhang.tradefree.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.siyangzhang.tradefree.Activity.ViewCategActivity;
import com.example.siyangzhang.tradefree.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this Fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategFragment#newInstance} factory method to
 * create an instance of this Fragment.
 */
public class CategFragment extends BaseFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the Fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ImageView btnPC;
    private ImageView btnTV;
    private ImageView btnCL;
    private ImageView btnPH;
    private ImageView btnCM;
    private ImageView btnFD;
    private ImageView btnTB;
    private ImageView btnBC;

    public CategFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this Fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of Fragment CategFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategFragment newInstance(String param1, String param2) {
        CategFragment fragment = new CategFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ImageView01:
                intent.setClass(getActivity(), ViewCategActivity.class);
                intent.putExtra("type", "PC");
                startActivity(intent);
                break;
            case R.id.ImageView02:
                intent.setClass(getActivity(), ViewCategActivity.class);
                intent.putExtra("type", "TV");
                startActivity(intent);
                break;
            case R.id.ImageView03:
                intent.setClass(getActivity(), ViewCategActivity.class);
                intent.putExtra("type", "CL");
                startActivity(intent);
                break;
            case R.id.ImageView04:
                intent.setClass(getActivity(), ViewCategActivity.class);
                intent.putExtra("type", "PH");
                startActivity(intent);
                break;
            case R.id.ImageView05:
                intent.setClass(getActivity(), ViewCategActivity.class);
                intent.putExtra("type", "CM");
                startActivity(intent);
                break;
            case R.id.ImageView06:
                intent.setClass(getActivity(), ViewCategActivity.class);
                intent.putExtra("type", "FD");
                startActivity(intent);
                break;
            case R.id.ImageView07:
                intent.setClass(getActivity(), ViewCategActivity.class);
                intent.putExtra("type", "TB");
                startActivity(intent);
                break;
            case R.id.ImageView08:
                intent.setClass(getActivity(), ViewCategActivity.class);
                intent.putExtra("type", "BC");
                startActivity(intent);
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this Fragment
        //btnTV = (ImageButton) findViewById();
        View result = inflater.inflate(R.layout.fragment_categ, container, false);
        btnPC = (ImageView) result.findViewById(R.id.ImageView01);
        btnPC.setOnClickListener(this);
        btnTV = (ImageView) result.findViewById(R.id.ImageView02);
        btnTV.setOnClickListener(this);
        btnCL = (ImageView) result.findViewById(R.id.ImageView03);
        btnCL.setOnClickListener(this);
        btnPH = (ImageView) result.findViewById(R.id.ImageView04);
        btnPH.setOnClickListener(this);
        btnCM = (ImageView) result.findViewById(R.id.ImageView05);
        btnCM.setOnClickListener(this);
        btnFD = (ImageView) result.findViewById(R.id.ImageView06);
        btnFD.setOnClickListener(this);
        btnTB = (ImageView) result.findViewById(R.id.ImageView07);
        btnTB.setOnClickListener(this);
        btnBC = (ImageView) result.findViewById(R.id.ImageView08);
        btnBC.setOnClickListener(this);
        return result;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCategFragmentInteraction(uri);
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
     * Fragment to allow an interaction in this Fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCategFragmentInteraction(Uri uri);
    }
}
