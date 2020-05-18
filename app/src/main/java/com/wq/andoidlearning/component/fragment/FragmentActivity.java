package com.wq.andoidlearning.component.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class FragmentActivity extends AppCompatActivity {
    private ContentFragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        contentFragment = ContentFragment.newInstance();

        getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.container, contentFragment)
                .commit();
    }
}
