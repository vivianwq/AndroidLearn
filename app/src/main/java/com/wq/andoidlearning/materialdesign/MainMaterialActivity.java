package com.wq.andoidlearning.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.materialdesign.appbar.AppBarLayoutActivity;
import com.wq.andoidlearning.materialdesign.behavior.BehaviorMainActivity;
import com.wq.andoidlearning.materialdesign.bottom.BottomBarActivity;
import com.wq.andoidlearning.materialdesign.bottomsheet.BottomSheetMainActivity;
import com.wq.andoidlearning.materialdesign.floating.FABMainActivity;
import com.wq.andoidlearning.materialdesign.nested.NestedDemosActivity;
import com.wq.andoidlearning.materialdesign.nestedscrollview.NestedScrollViewActivity;
import com.wq.andoidlearning.materialdesign.snackbar.SnackBarActivity;
import com.wq.andoidlearning.materialdesign.tablayout.TabLayoutMainActivity;
import com.wq.andoidlearning.materialdesign.textinputlayout.TextInputLayoutActivity;
import com.wq.andoidlearning.materialdesign.titlebar.TitleBarActivity;

public class MainMaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_material);

        findViewById(R.id.btnAppBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        AppBarLayoutActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnAppBar2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        NestedScrollViewActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnTitleBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        TitleBarActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnNested).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        NestedDemosActivity.class);
                startActivity(intent);

            }
        });


        findViewById(R.id.btnFloatingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        FABMainActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnBehaviorMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        BehaviorMainActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnBottomSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        BottomSheetMainActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnBottomBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        BottomBarActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnTextInputLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        TextInputLayoutActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnTabLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        TabLayoutMainActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnTextInputLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        TextInputLayoutActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnSnackBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMaterialActivity.this,
                        SnackBarActivity.class);
                startActivity(intent);

            }
        });


    }
}
