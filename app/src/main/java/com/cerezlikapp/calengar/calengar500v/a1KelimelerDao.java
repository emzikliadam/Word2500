package com.cerezlikapp.calengar.calengar500v;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class a1KelimelerDao {

    public ArrayList<a1verinesne> tumkelimeler(a1veritabani vt) {
        ArrayList<a1verinesne> kelimearraylist=new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();

        Cursor c=db.rawQuery("SELECT * FROM calengar500v",null);


        while (c.moveToNext()) {
            a1verinesne k=new a1verinesne(c.getInt(c.getColumnIndex("id")),
            c.getString((c.getColumnIndex("turkce"))),
                    c.getString((c.getColumnIndex("ingilizce"))));

            kelimearraylist.add(k);
        }

        return kelimearraylist;



    }

    public ArrayList<a1verinesne> kelimeara(a1veritabani vt, String aramaKelime) {
        ArrayList<a1verinesne> kelimearraylist=new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();


        Cursor c=db.rawQuery("SELECT * FROM calengar500v WHERE ingilizce like'%"+aramaKelime+"%'",null);


        while (c.moveToNext()) {
            a1verinesne k=new a1verinesne(c.getInt(c.getColumnIndex("id"))
                   ,c.getString(c.getColumnIndex("turkce"))
                    ,c.getString(c.getColumnIndex("ingilizce")));

            kelimearraylist.add(k);
        }

        return kelimearraylist;



    }
}
