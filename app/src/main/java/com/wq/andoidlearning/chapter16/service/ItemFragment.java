package com.wq.andoidlearning.chapter16.service;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.chapter16.service.dummy.DummyContent;
import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private String type;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount, String type) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            type = getArguments().getString("type");
        }
        if (type.equals("1")) {
            EventBus.getDefault().post(new ServiceBean("FragmentPagerAdapter--onCreate"));
        } else {
            EventBus.getDefault().post(new ServiceBean("FragmentStatePagerAdapter--onCreate"));
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (type.equals("1")) {
            EventBus.getDefault().post(new ServiceBean("FragmentPagerAdapter--onResume"));
        } else {
            EventBus.getDefault().post(new ServiceBean("FragmentStatePagerAdapter--onResume"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        if (type.equals("1")) {
            EventBus.getDefault().post(new ServiceBean("FragmentPagerAdapter--onCreateView"));
        } else {
            EventBus.getDefault().post(new ServiceBean("FragmentStatePagerAdapter--onCreateView"));
        }
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (type.equals("1")) {
            EventBus.getDefault().post(new ServiceBean("FragmentPagerAdapter--onDestroyView"));
        } else {
            EventBus.getDefault().post(new ServiceBean("FragmentStatePagerAdapter--onDestroyView"));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (type.equals("1")) {
            EventBus.getDefault().post(new ServiceBean("FragmentPagerAdapter--onDestroy"));
        } else {
            EventBus.getDefault().post(new ServiceBean("FragmentStatePagerAdapter--onDestroy"));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        if (type.equals("1")) {
            EventBus.getDefault().post(new ServiceBean("FragmentPagerAdapter--onDetach"));
        } else {
            EventBus.getDefault().post(new ServiceBean("FragmentStatePagerAdapter--onDetach"));
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyContent.DummyItem item);
    }
}
