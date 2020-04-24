package com.wq.andoidlearning.trace;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class TraceViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_view);
        TraceViewOperation.writeOnActivityOnCreate(this,1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TraceViewOperation.writeOnActivityOnResume(this,1000);
    }
}
