package com.wq.andoidlearning.component.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {

    public MyContentProvider() {
    }

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public static final String AUTHORITY = "com.wq.andoidlearning.component.contentprovider.MyContentProvider";

    public static final int BOOK_URI_CODE = 0;

    public static final int USER_URI_CODE = 1;

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, DbOpenHelper.BOOK_TABLE_NAME, BOOK_URI_CODE);
        uriMatcher.addURI(AUTHORITY, DbOpenHelper.USER_TABLE_NAME, USER_URI_CODE);
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
        }
        return tableName;
    }


    @Override
    public boolean onCreate() {
        context = getContext();
        initProviderData();
        return false;
    }

    //初始化原始数据
    public void initProviderData() {
        sqLiteDatabase = new DbOpenHelper(context).getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookName", "数据结构");
        sqLiteDatabase.insert(DbOpenHelper.BOOK_TABLE_NAME, null, contentValues);
        contentValues.put("bookName", "编译原理");
        sqLiteDatabase.insert(DbOpenHelper.BOOK_TABLE_NAME, null, contentValues);
        contentValues.put("bookName", "网络原理");
        sqLiteDatabase.insert(DbOpenHelper.BOOK_TABLE_NAME, null, contentValues);
        contentValues.clear();

        contentValues.put("userName", "叶");
        contentValues.put("sex", "女");
        sqLiteDatabase.insert(DbOpenHelper.USER_TABLE_NAME, null, contentValues);
        contentValues.put("userName", "叶叶");
        contentValues.put("sex", "男");
        sqLiteDatabase.insert(DbOpenHelper.USER_TABLE_NAME, null, contentValues);
        contentValues.put("userName", "叶应是叶");
        contentValues.put("sex", "男");
        sqLiteDatabase.insert(DbOpenHelper.USER_TABLE_NAME, null, contentValues);
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalStateException("Unsupported URI:" + uri);
        }
        return sqLiteDatabase.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalStateException("Unsupported URI:" + uri);
        }
        sqLiteDatabase.insert(tableName, null, values);
        context.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalStateException("Unsupported URI:" + uri);
        }
        int count = sqLiteDatabase.delete(tableName, selection, selectionArgs);
        if (count > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalStateException("Unsupported URI:" + uri);
        }
        int row = sqLiteDatabase.update(tableName, values, selection, selectionArgs);
        if (row > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}
