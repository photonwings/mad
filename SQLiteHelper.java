package com.example.program5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import androidx.annotation.Nullable;
public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }public void insertData(String username,String password){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "insert into users values(?,?)";
        SQLiteStatement statement = database.compileStatement(query);
        statement.clearBindings();
        statement.bindString(1,username);
        statement.bindString(2,password);
        statement.executeInsert();
        statement.close();
        database.close();
    }
    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}