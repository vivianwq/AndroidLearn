package com.wq.andoidlearning.materialdesign.bottomsheet;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.wq.andoidlearning.R;

public class BottomSheetDialog3Activity extends AppCompatActivity {

    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog3);
    }

    public void showDialog(View view) {
        dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.layout_bottom_sheet_dialog);
        dialog.show();
    }

    public void hideDialog(View view) {
        if (dialog != null && dialog.isShowing()) {
            dialog.hide();
        }
    }
}
