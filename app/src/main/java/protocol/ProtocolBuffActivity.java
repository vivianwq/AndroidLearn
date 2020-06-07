package protocol;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import tutorial.Student;

public class ProtocolBuffActivity extends AppCompatActivity {

    private TextView tvContent;
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_buff);
        tvContent = findViewById(R.id.tvContent);
        Student.Person.Builder builder = Student.Person.newBuilder();
        String email = builder.getEmail();
        builder.setName("vivian");
        builder.setEmail("522029704@qq.com");
        stringBuilder.append(email);
        stringBuilder.append(builder.getName());
        stringBuilder.append("test");
        tvContent.setText(stringBuilder);

        try {


            File file = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "proto_person");
            if(!file.exists()) {
                file.createNewFile();
            }
            //序列化
            FileOutputStream outputStream = new FileOutputStream(file);
            builder.build().writeTo(outputStream);

            //反序列化
            InputStream inputStream = new FileInputStream(file);
            Student.Person person1 = Student.Person.parseFrom(inputStream);
            tvContent.setText(person1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
