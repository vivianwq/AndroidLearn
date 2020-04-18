package com.wq.andoidlearning.materialdesign.bottomsheet;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class BottomSheetDialog4Activity extends AppCompatActivity {

    private DemoBottomSheetDialogFragment demoBottomSheetDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog4);
    }

    public void showDialog(View view) {
        demoBottomSheetDialogFragment = DemoBottomSheetDialogFragment.newInstance();
        demoBottomSheetDialogFragment.show(getSupportFragmentManager(), "demo");
    }

    public void hideDialog(View view) {
        if (demoBottomSheetDialogFragment != null) {
            demoBottomSheetDialogFragment.dismiss();
        }
    }
}
