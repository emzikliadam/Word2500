package com.cerezlikapp.calengar.calengar500v;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;

public class a1veritabani extends SQLiteOpenHelper {
    Context context;
    public a1veritabani(Context context) {
        super(context,"calengar500v.sqlite", null, 1);
        this.context=context;

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS \"calengar500v\" (\n" +
                "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"ingilizce\"\tTEXT,\n" +
                "\t\"turkce\"\tTEXT\n" +
                ");");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS calengar500v");
        onCreate(db);
    }

    public void veritabanikopyala() {
        DatabaseCopyHelper databaseCopyHelper=new DatabaseCopyHelper(context);
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseCopyHelper.openDataBase();
    }

}
