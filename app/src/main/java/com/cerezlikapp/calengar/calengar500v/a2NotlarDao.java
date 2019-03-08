package com.cerezlikapp.calengar.calengar500v;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class a2NotlarDao {

    public ArrayList<a2notlar> tumnotlar(a2veritabani vt) {
            ArrayList<a2notlar>  notlarArrayList=new ArrayList<>();
        SQLiteDatabase db=vt.getWritableDatabase();

        Cursor d=db.rawQuery("SELECT * FROM calengarekle",null);


        while (d.moveToNext()) {
            a2notlar k=new a2notlar(d.getInt(d.getColumnIndex("id")),
                    d.getString((d.getColumnIndex("ingilizce"))),
                    d.getString((d.getColumnIndex("turkce"))));

            notlarArrayList.add(k);
        }
        db.close();

        return notlarArrayList;



    }

    public void notSil(a2veritabani vt,int id) {

        SQLiteDatabase db=vt.getWritableDatabase();
        db.delete("calengarekle","id=?",new String[]{String.valueOf(id)});
        db.close();

    }
    public void notEkle(a2veritabani vt,String ingilizce,String turkce) {
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("ingilizce",ingilizce);
        values.put("turkce",turkce);

        db.insertOrThrow("calengarekle",null,values);
        db.close();
    }
    public void notGuncelle(a2veritabani vt,int id,String ingilizce,String turkce) {
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("ingilizce",ingilizce);
        values.put("turkce",turkce);

        db.update("calengarekle",values,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }






    }

