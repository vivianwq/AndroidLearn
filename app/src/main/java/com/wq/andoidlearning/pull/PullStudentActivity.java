package com.wq.andoidlearning.pull;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PullStudentActivity extends AppCompatActivity {

    private TextView tvContent;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_student);
        tvContent = findViewById(R.id.tvContent);
        stringBuilder = new StringBuilder();

        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("student.xml");
            List<Student> students = null;

            students = ParseByPull.getStudents(inputStream);
            for (Student stu : students) {
                Log.e("StudentInfo", stu.toString());
                stringBuilder.append("studentInfo--" + stu.toString() + "\n");
            }
            tvContent.setText(stringBuilder.toString());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
