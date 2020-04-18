package com.wq.andoidlearning.materialdesign.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.wq.andoidlearning.R;

public class DemoBottomSheetDialogFragment extends BottomSheetDialogFragment {

    public static DemoBottomSheetDialogFragment newInstance() {
        return new DemoBottomSheetDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_bottom_sheet_dialog, container, false);
    }
}
