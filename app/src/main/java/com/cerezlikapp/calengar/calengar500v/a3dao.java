package com.cerezlikapp.calengar.calengar500v;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class a3dao {
    public ArrayList<a1verinesne> rastgele5(a1veritabani vt) {
        ArrayList<a1verinesne> soruarraylist = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM calengar500v ORDER BY RANDOM() LIMIT 100", null);


        while (c.moveToNext()) {
            a1verinesne k = new a1verinesne(c.getInt(c.getColumnIndex("id")),
                    c.getString((c.getColumnIndex("ingilizce"))),
                    c.getString((c.getColumnIndex("turkce"))));

            soruarraylist.add(k);
        }

        return soruarraylist;

    }

    public ArrayList<a1verinesne> rastgele3(a1veritabani vt, int id) {
        ArrayList<a1verinesne> soruarraylist = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();


        Cursor c = db.rawQuery("SELECT * FROM calengar500v WHERE id !="+id+" ORDER BY RANDOM() LIMIT 3", null);


        while (c.moveToNext()) {
            a1verinesne k = new a1verinesne(c.getInt(c.getColumnIndex("id"))
                    , c.getString(c.getColumnIndex("ingilizce"))
                    , c.getString(c.getColumnIndex("turkce")));

            soruarraylist.add(k);
        }

        return soruarraylist;


    }
}