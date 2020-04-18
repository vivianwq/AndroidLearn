package com.wq.andoidlearning.materialdesign.floating;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wq.andoidlearning.R;

public class FloatingButton3Activity extends AppCompatActivity {

    private FloatingActionButton fab;
    private CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_button3);

        fab = findViewById(R.id.fab);
        coordinator = findViewById(R.id.coordinator);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinator, "点击Fab", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
