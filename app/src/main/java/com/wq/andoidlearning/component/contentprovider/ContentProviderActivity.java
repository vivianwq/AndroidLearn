package com.wq.andoidlearning.component.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class ContentProviderActivity extends AppCompatActivity {
    private Button btnQueryDB;
    private TextView tvContent;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        btnQueryDB = findViewById(R.id.btnQueryDB);
        tvContent = findViewById(R.id.tvContent);
        stringBuilder = new StringBuilder();
        btnQueryDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri bookUri = Uri.parse("content://com.wq.andoidlearning.component.contentprovider.MyContentProvider/book");
                ContentValues contentValues = new ContentValues();
                contentValues.put("bookName", "插入新数据名字");
                getContentResolver().insert(bookUri, contentValues);
                Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "bookName"}, null, null, null);
                if (bookCursor != null) {
                    stringBuilder = new StringBuilder();
                    while (bookCursor.moveToNext()) {
                        stringBuilder.append("ID:" + bookCursor.getInt(bookCursor.getColumnIndex("_id"))
                                + "  BookName:" + bookCursor.getString(bookCursor.getColumnIndex("bookName"))
                                + "\n");

                    }
                    tvContent.setText(stringBuilder.toString());
                    bookCursor.close();
                }
            }
        });
        findViewById(R.id.btnQueryUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri userUri = Uri.parse("content://com.wq.andoidlearning.component.contentprovider.MyContentProvider/user");
                ContentValues contentValues = new ContentValues();
                contentValues.clear();
                contentValues.put("userName", "叶叶页");
                contentValues.put("sex", "男");
                getContentResolver().insert(userUri, contentValues);
                Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "userName", "sex"}, null, null, null);
                if (userCursor != null) {
                    stringBuilder = new StringBuilder();
                    while (userCursor.moveToNext()) {
                        stringBuilder.append("ID:" + userCursor.getInt(userCursor.getColumnIndex("_id"))
                                + "  UserName:" + userCursor.getString(userCursor.getColumnIndex("userName"))
                                + "  Sex:" + userCursor.getString(userCursor.getColumnIndex("sex")
                        ) + "\n");
                    }
                    tvContent.setText(stringBuilder.toString());
                    userCursor.close();
                }
            }
        });
        findViewById(R.id.btnQueryContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContacts();
            }
        });
    }

    public void showContacts() {
        stringBuilder = new StringBuilder();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor == null) {
            stringBuilder.append("显示联系人错误");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            stringBuilder.append("ID:");
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            stringBuilder.append(contactId);

            stringBuilder.append("\t\t");
            stringBuilder.append("名字");
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            stringBuilder.append(contactName);

            //根据联系人ID查询对应的电话号码
            Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null, null);
            //取得电话号码(可能会存在多个号码)
            if (phoneCursor != null) {
                stringBuilder.append("\t\t");
                stringBuilder.append("号码:");
                while (phoneCursor.moveToNext()) {
                    String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    stringBuilder.append(phoneNumber);
                    stringBuilder.append("\n");
                }
                phoneCursor.close();
            }
        }
        cursor.close();
        tvContent.setText(stringBuilder.toString());
    }
}
