package com.wq.andoidlearning.rxjava;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

public class RxJavaDemo7Activity extends AppCompatActivity {

    private EditText etName, etPassWord;
    private Button btnLogin;
    private PublishSubject<String> nameSubject;
    private PublishSubject<String> passwordSubject;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo7);
        etName = findViewById(R.id.etName);
        etPassWord = findViewById(R.id.etPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        nameSubject = PublishSubject.create();
        passwordSubject = PublishSubject.create();
        etName.addTextChangedListener(new EditTextMonitor(nameSubject));
        etPassWord.addTextChangedListener(new EditTextMonitor(passwordSubject));
        Observable<Boolean> booleanObservable = Observable.combineLatest(nameSubject, passwordSubject,
                new BiFunction<String, String, Boolean>() {
                    @Override
                    public Boolean apply(String s, String s2) throws Exception {
                        int nameLength = s.length();
                        int passwordLength = s2.length();
                        return nameLength >= 2
                                && nameLength <= 8
                                && passwordLength >= 4
                                && passwordLength <= 16;
                    }
                });

        DisposableObserver<Boolean> disposableObserver = new DisposableObserver<Boolean>() {

            @Override
            public void onNext(Boolean aBoolean) {

                btnLogin.setText(aBoolean ? "登录" : "用户名或密码无效");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        booleanObservable.subscribe(disposableObserver);
        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(disposableObserver);

    }

    private class EditTextMonitor implements TextWatcher {
        private PublishSubject<String> publishSubject;

        public EditTextMonitor(PublishSubject<String> publishSubject) {
            this.publishSubject = publishSubject;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            publishSubject.onNext(s.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
