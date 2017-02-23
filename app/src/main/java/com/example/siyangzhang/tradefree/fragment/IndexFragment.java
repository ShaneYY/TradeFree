package com.example.siyangzhang.tradefree.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.siyangzhang.tradefree.ACache;
import com.example.siyangzhang.tradefree.Activity.ItemShow;
import com.example.siyangzhang.tradefree.Activity.PublishItemActivity;
import com.example.siyangzhang.tradefree.Adapter.ItemListAdapter;
import com.example.siyangzhang.tradefree.Bean.Item;
import com.example.siyangzhang.tradefree.R;
import java.util.ArrayList;
import java.util.List;


public class IndexFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public static ACache mCache;
    private RecyclerView mItemViewList;
    private List<Item> mItemMessage;
    private ItemListAdapter mAdapter;
    private String title = "Default";
    public static final String TITLE = "title";
    public static final int PUBLISHITEM = 1;
    public static final String Item_Info = "Item_Info";

    private OnFragmentInteractionListener mListener;
    private FloatingActionButton floatingActionButton;

    public IndexFragment() {
        mItemMessage = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
        }

        mAdapter = new ItemListAdapter(mItemMessage, getActivity());
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        mItemViewList = (RecyclerView) view.findViewById(R.id.item_list);
        mItemViewList.setAdapter(mAdapter);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_btn);
        mAdapter.setOnItemClickListener(new ItemListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ItemShow.class);
                intent.putExtra(Item_Info, mItemMessage.get(position).getTitle() + mItemMessage.get(position).getDetail());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                mItemMessage.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });
        // publishBar= (FrameLayout) view.findViewById(R.id.publish_bar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mItemViewList.setLayoutManager(linearLayoutManager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PublishItemActivity.class);
                startActivityForResult(intent, PUBLISHITEM);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            switch (requestCode) {
                case PUBLISHITEM:
                    Item item = (Item) data.getSerializableExtra(Item_Info);

                    mItemMessage.add(item);
                    mCache=ACache.get(getContext());
                    mCache.put(item.getDetail(),item,2*ACache.TIME_DAY);

                    mAdapter.notifyDataSetChanged();
                    // Log.d("testPublish", partTimeJob.getTitle() + "-----" + partTimeJob.getContent());
                    break;
            }
        } else {
            return;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onIndexFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
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
        void onIndexFragmentInteraction(Uri uri);
    }
}
