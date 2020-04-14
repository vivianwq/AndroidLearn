package com.wq.andoidlearning.status;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class StatusBarActivity1 extends AppCompatActivity {

    private Button btnShowByWindow, btnHideByWindow;
    private Button btnShowByDecorView, btnHideByDecorView;
    private Button btnShowTransparent;
    private Button btnChangeColor;
    private Button btnShowByWindowChangeLayout, btnHideByWindowChangeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar1);
        btnShowByWindow = findViewById(R.id.btnShowByWindow);
        btnHideByWindow = findViewById(R.id.btnHideByWindow);
        btnShowByDecorView = findViewById(R.id.btnShowByDecorView);
        btnHideByDecorView = findViewById(R.id.btnHideByDecorView);
        btnShowTransparent = findViewById(R.id.btnShowTransparent);
        btnShowByWindowChangeLayout = findViewById(R.id.btnShowByWindowChangeLayout);
        btnHideByWindowChangeLayout = findViewById(R.id.btnHideByWindowChangeLayout);
        btnChangeColor = findViewById(R.id.btnChangeColor);
        btnHideByWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusBarVisibleByWindow(StatusBarActivity1.this, false);
            }
        });
        btnShowByWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusBarVisibleByWindow(StatusBarActivity1.this, true);
            }
        });
        btnHideByDecorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusBarVisibleByDecorView(StatusBarActivity1.this, false);
            }
        });
        btnShowByDecorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusBarVisibleByDecorView(StatusBarActivity1.this, true);
            }
        });

        btnShowTransparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusBarTransparent(StatusBarActivity1.this);
            }
        });

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusBarColor(StatusBarActivity1.this, R.color.colorAccent);
            }
        });

        btnShowByWindowChangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusBarOverlay(StatusBarActivity1.this,false);
            }
        });

        btnHideByWindowChangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏
                setStatusBarOverlay(StatusBarActivity1.this,true);
            }
        });
    }

    /**
     * 通过Activity所属的Window
     *
     * @param activity
     * @param visible
     */
    public static void setStatusBarVisibleByWindow(Activity activity, boolean visible) {
        Window window = activity.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
            if (visible) {
                attributes.flags &= ~bits;
            } else {
                attributes.flags |= bits;
            }
            window.setAttributes(attributes);
        }
    }

    /**
     * 通过Activity所属的DecorView
     *
     * @param activity
     * @param visible
     */
    public static void setStatusBarVisibleByDecorView(Activity activity, boolean visible) {
        Window window = activity.getWindow();
        if (window != null) {
            if (visible) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            }
        }
    }

    /**
     * 通过代码设置状态栏透明
     *
     * @param activity
     */
    public static void setStatusBarTransparent(Activity activity) {
        Window window = activity.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            attributes.flags |= bits;
            window.setAttributes(attributes);
        }
    }

    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if (window != null) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorId));
            }
        }
    }

    public static void setStatusBarOverlay(Activity activity, boolean overlay) {
        Window window = activity.getWindow();
        if (window != null) {
            if (overlay) {
                //重叠
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                //不重叠
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }
}
