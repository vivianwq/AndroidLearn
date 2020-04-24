package com.wq.andoidlearning.trace;

import android.content.Context;
import android.util.Log;

public class TraceViewOperation {

    public static void writeOnActivityOnCreate(Context context, int count) {
        writeSomething(context, count);
    }

    public static void writeOnActivityOnResume(final Context context, final int count) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                writeSomething(context, count);

            }
        }.start();
    }

    public static void writeSomething(Context context, int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += i;
            Log.i("writeSomething", "打印i" + i);
        }
    }
}
