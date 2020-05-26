package com.wq.andoidlearning.window;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.wq.andoidlearning.R;
import com.wq.andoidlearning.window.dialogfragment.DialogFragmentActivity;
import com.wq.andoidlearning.window.recyclerview.RecyclerViewMainActivity;
import com.wq.andoidlearning.window.viewlife.ViewLifeCycleActivity;

public class WindowMainActivity extends AppCompatActivity {
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_main);
        findViewById(R.id.btnToastView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WindowMainActivity.this, ToastViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnDialogFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WindowMainActivity.this, DialogFragmentActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WindowMainActivity.this, ViewLifeCycleActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnRecyclerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WindowMainActivity.this, RecyclerViewMainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnAlertDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WindowMainActivity.this);
                builder.setIcon(R.mipmap.ic_app);
                builder.setMessage("潇湘剑雨");
                builder.setTitle("这个是标题");
                builder.setView(R.layout.activity_window_main);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
        findViewById(R.id.btnPopupWindow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建对象
                PopupWindow popupWindow = new PopupWindow(WindowMainActivity.this);
                View inflate = LayoutInflater.from(WindowMainActivity.this).inflate(R.layout.view_pop_custom, null);
                //设置view布局
                popupWindow.setContentView(inflate);
                popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                //设置动画的方法
                popupWindow.setAnimationStyle(R.style.BottomDialog);
                //设置PopUpWindow的焦点，设置为true之后，PopupWindow内容区域，才可以响应点击事件
                popupWindow.setTouchable(true);
                //设置背景透明
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                //点击空白处的时候让PopupWindow消失
                popupWindow.setOutsideTouchable(true);
                // true时，点击返回键先消失 PopupWindow
                // 但是设置为true时setOutsideTouchable，setTouchable方法就失效了（点击外部不消失，内容区域也不响应事件）
                // false时PopupWindow不处理返回键，默认是false
                popupWindow.setFocusable(false);
                //设置dismiss事件
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                });
                boolean showing = popupWindow.isShowing();
                if (!showing) {
                    //show，并且可以设置位置
                    popupWindow.showAsDropDown(v);
                }
            }
        });
        findViewById(R.id.btnNotPopupWindow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow(WindowMainActivity.this);
                View inflate = LayoutInflater.from(WindowMainActivity.this).inflate(R.layout.view_pop_custom, null);
                popupWindow.setContentView(inflate);
                popupWindow.setAnimationStyle(R.style.BottomDialog);
                popupWindow.showAsDropDown(v);
            }
        });

        findViewById(R.id.btnSnackBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(v);
            }
        });
    }

    public void showSnackBar(View view) {
        Snackbar sb = Snackbar.make(view, "潇湘剑雨", Snackbar.LENGTH_LONG)
                .setAction("删除吗？", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击了"是吗？"字符串操作
                        Toast.makeText(WindowMainActivity.this, "点击了是吗", Toast.LENGTH_LONG).show();
                    }
                })
                .setActionTextColor(Color.RED)
                .setText("SnackBar消息内容")
                .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        switch (event) {
                            case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                            case Snackbar.Callback.DISMISS_EVENT_MANUAL:
                            case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                            case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                                Toast.makeText(WindowMainActivity.this, "删除成功", Toast.LENGTH_LONG).show();
                                break;
                            case Snackbar.Callback.DISMISS_EVENT_ACTION:
                                Toast.makeText(WindowMainActivity.this, "撤销了删除操作", Toast.LENGTH_LONG).show();
                                break;
                        }
                        Log.d("MainActivity", "onDismissed");
                    }

                    @Override
                    public void onShown(Snackbar transientBottomBar) {
                        super.onShown(transientBottomBar);
                        Log.d("MainActivity", "onShown");
                    }
                });
        sb.show();
    }
}
