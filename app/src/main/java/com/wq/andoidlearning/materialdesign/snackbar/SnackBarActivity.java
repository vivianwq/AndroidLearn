package com.wq.andoidlearning.materialdesign.snackbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.wq.andoidlearning.R;

public class SnackBarActivity extends AppCompatActivity {

    private CoordinatorLayout coordinator;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        coordinator = findViewById(R.id.coordinator);
    }

    public void showSnackBar(View view) {
        snackbar = Snackbar.make(coordinator, "我是提示信息",
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(getResources().getColor(android.R.color.holo_orange_dark));
        snackbar.setAction("点击", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("showSnackBar", "onClick");
            }
        });
        snackbar.show();
    }

    public void changeColor(View view) {
        View view1 = snackbar.getView();
        view1.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
    }

    public void changeTextColor(View view) {
        ViewGroup view1 = (ViewGroup) snackbar.getView();
        SnackbarContentLayout contentLayout = (SnackbarContentLayout) view1.getChildAt(0);
        TextView textView = (TextView) contentLayout.getChildAt(0);
        textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
    }

    public void showSnackBarText(View view) {
        Snackbar
                .make(coordinator, "提示信息", Snackbar.LENGTH_INDEFINITE)
                .show();
    }
}
