package com.wq.andoidlearning.pull;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class PullXmlActivity extends AppCompatActivity {
    private TextView tvContent;

    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_xml);
        stringBuilder = new StringBuilder();
        tvContent = findViewById(R.id.tvContent);
        parseLayout(R.layout.activity_pull_xml);
        findViewById(R.id.btnPull).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PullXmlActivity.this, PullStudentActivity.class));
            }
        });

    }

    private void parseLayout(int layout) {
        Resources resources = getResources();
        XmlPullParser parser = resources.getLayout(layout);
        try {
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_DOCUMENT:
                        stringBuilder.append("START_DOCUMENT - "
                                + parser.getName() + "\n");
                        Log.i("parseLayout", "START_DOCUMENT - "
                                + parser.getName() + "\n");
                        break;
                    case XmlPullParser.START_TAG:
                        stringBuilder.append("START_TAG - "
                                + parser.getName() + "\n");
                        Log.i("parseLayout", "START_TAG - "
                                + parser.getName());
                        int attrCount = parser.getAttributeCount();
                        String separator = " , ";
                        for (int i = 0; i < attrCount; i++) {
                            StringBuffer sb = new StringBuffer();
                            sb.append("name = ");
                            sb.append(parser.getAttributeName(i));
                            sb.append(separator);
                            sb.append("value = ");
                            sb.append(parser.getAttributeValue(i));
                            sb.append(separator);
                            sb.append("type = ");
                            sb.append(parser.getAttributeType(i));
                            Log.i("parseLayout", sb.toString());
                            stringBuilder.append(sb.toString() + "\n");
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        Log.i("parseLayout", "END_TAG - "
                                + parser.getName());
                        stringBuilder.append("END_TAG - "
                                + parser.getName() + "\n");

                }
                type = parser.next();
            }

            Log.i("parseLayout", "END_DOCUMENT - "
                    + parser.getName());
            stringBuilder.append("END_DOCUMENT - "
                    + parser.getName() + "\n");
            tvContent.setText(stringBuilder.toString());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
