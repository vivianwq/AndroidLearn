package com.wq.andoidlearning.component.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.fragment.androidx.AndroidXFragmentActivity;

public class ContentFragment extends Fragment {

    private View view;

    public ContentFragment() {
    }


    public static ContentFragment newInstance() {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content, container, false);

        view.findViewById(R.id.btnLazyLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),
                        LazyLoadActivity.class));

            }
        });
        view.findViewById(R.id.btnAndroidX).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),
                        AndroidXFragmentActivity.class));

            }
        });
        return view;
    }
}
