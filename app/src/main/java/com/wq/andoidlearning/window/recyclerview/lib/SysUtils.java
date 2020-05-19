package com.wq.andoidlearning.window.recyclerview.lib;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.wq.andoidlearning.MyApp;

public class SysUtils {

	public static int Dp2Px(Context context, float dp) {
		if (context==null){
			//避免空指针异常
			context = MyApp.myApp;
		}
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	public static int getScreenWidth(Activity activity){
		int width = 0;
		WindowManager windowManager = activity.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		width=display.getWidth();
		return width;
	}




}
