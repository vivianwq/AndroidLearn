package com.wq.andoidlearning.materialdesign.textinputlayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.wq.andoidlearning.R;

public class TextInputLayoutActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

        textInputLayout = findViewById(R.id.textInputLayout);
        //检测长度应该低于6位数
        textInputLayout.getEditText().addTextChangedListener(
                new MinLengthTextWatcher("长度应低于6位数!", textInputLayout));

        //开启计数
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(10);//最大输入限制
    }

    class MinLengthTextWatcher implements TextWatcher {

        private String errorStr;
        private TextInputLayout textInputLayout;


        public MinLengthTextWatcher(String errorStr, TextInputLayout textInputLayout) {
            this.errorStr = errorStr;
            this.textInputLayout = textInputLayout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //文字改变后回调
            if (textInputLayout.getEditText().getText().toString().length() <= 6) {
                textInputLayout.setErrorEnabled(false);
            } else {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(errorStr);
            }
        }
    }
}
