package com.wq.andoidlearning.rxjava.demo10;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wq.andoidlearning.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;

public class RxJavaDemo10Activity extends AppCompatActivity implements IHolder {

    public static final String TAG = RxJavaDemo10Activity.class.getSimpleName();

    private Button btnWorker;
    private TextView tvContent;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo10);
        Log.d(TAG, "onCreate");
        btnWorker = findViewById(R.id.btnWorker);
        tvContent = findViewById(R.id.tvContent);
        compositeDisposable = new CompositeDisposable();
        btnWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWorker();
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    private void startWorker() {
        WorkerFragment workerFragment = getWorkerFragment();
        if (workerFragment == null) {
            addWorkerFragment();
        } else {
            Log.d(TAG, "WorkerFragment has attach");
        }
    }

    private void onWorkerFinished() {
        Log.d(TAG, "onWorkerFinished");
        removeWorkerFragment();
    }

    private void addWorkerFragment() {
        WorkerFragment workerFragment = new WorkerFragment();
        Bundle args = new Bundle();
        args.putString("task_name", "学习RxJava2");
        workerFragment.setArguments(args);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(workerFragment, WorkerFragment.TAG);
        transaction.commit();
    }

    private void removeWorkerFragment() {
        WorkerFragment workerFragment = getWorkerFragment();
        if (workerFragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(workerFragment);
            transaction.commit();
        }
    }

    private WorkerFragment getWorkerFragment() {
        FragmentManager manager = getSupportFragmentManager();
        return (WorkerFragment) manager.findFragmentByTag(WorkerFragment.TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        compositeDisposable.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onWorkerPrepared(ConnectableObservable<String> workerFlow) {

        DisposableObserver<String> disposableObserver = new DisposableObserver<String>() {

            @Override
            public void onNext(String s) {
                tvContent.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                onWorkerFinished();
                tvContent.setText("任务错误");

            }

            @Override
            public void onComplete() {
                onWorkerFinished();
                tvContent.setText("任务完成");
            }
        };
        workerFlow
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }
}
