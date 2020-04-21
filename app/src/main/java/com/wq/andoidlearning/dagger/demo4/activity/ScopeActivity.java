package com.wq.andoidlearning.dagger.demo4.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.dagger.demo4.app.ScopeApp;
import com.wq.andoidlearning.dagger.demo4.app.ScopeAppComponent;
import com.wq.andoidlearning.dagger.demo4.app.ScopeAppData;

import javax.inject.Inject;

public class ScopeActivity extends AppCompatActivity {

    public static final String TAG = ScopeActivity.class.getSimpleName();

    private ScopeActivityComponent scopeActivityComponent;

    @Inject
    ScopeAppData scopeAppData;

    @Inject
    ScopeActivitySharedData scopeActivitySharedData1;

    @Inject
    ScopeActivitySharedData scopeActivitySharedData2;

    @Inject
    ScopeActivityNormalData scopeActivityNormalData1;

    @Inject
    ScopeActivityNormalData scopeActivityNormalData2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope);
        getScopeActivityComponent().inject(this);
        TextView tvContent = findViewById(R.id.tvContent);
        String result = "[ScopeActivity Space] \n scopeAppData=" + scopeAppData
                + "\n\n" + "scopeActivitySharedData1=" + scopeActivitySharedData1
                + "\n\n" + "scopeActivitySharedData2=" + scopeActivitySharedData2
                + "\n\n" + "scopeActivityNormalData1=" + scopeActivityNormalData1
                + "\n\n" + "scopeActivityNormalData2=" + scopeActivityNormalData2;
        tvContent.setText(result);


    }

    public ScopeActivityComponent getScopeActivityComponent() {
        if (scopeActivityComponent == null) {
            ScopeAppComponent scopeAppComponent = ((ScopeApp) getApplication()).getAppComponent();
            scopeActivityComponent = DaggerScopeActivityComponent
                    .builder()
                    .scopeAppComponent(scopeAppComponent)
                    .build();
        }
        return scopeActivityComponent;
    }
}
