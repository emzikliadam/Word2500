package com.cerezlikapp.calengar.calengar500v;



import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class a2kelimeekle extends Fragment{
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private static ArrayList<a2notlar> notlarArrayList;
    private static a2notlaradaptor adapter;
    private static a2veritabani vt;


    public static void refreshAdapter(){

        notlarArrayList.clear();
        notlarArrayList.addAll(new a2NotlarDao().tumnotlar(vt));
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview=inflater.inflate(R.layout.a2kelimeekle, container, false);
        final Context context=container.getContext();

        toolbar=rootview.findViewById(R.id.ekletoolbar);
        rv=rootview.findViewById(R.id.eklerv);
        fab=rootview.findViewById(R.id.eklefab);

        vt=new a2veritabani(context);

        toolbar.setTitle("Kelime Defteri");
        ((AppCompatActivity)context).setSupportActionBar(toolbar);


        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));

        notlarArrayList= new a2NotlarDao().tumnotlar(vt);






        //toplam kelime sayisi:
        toolbar.setSubtitle("Kelime Sayisi: "+notlarArrayList.size());

        adapter=new a2notlaradaptor(context,notlarArrayList);

        rv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=getActivity().getLayoutInflater();
                View view=inflater.inflate(R.layout.a2alertdialog,null,false);

                final EditText ingilizce=view.findViewById(R.id.edit_username);
                final EditText turkce=view.findViewById(R.id.edit_password);
                final TextView button=view.findViewById(R.id.buttonekle);
                final TextView cancel=view.findViewById(R.id.cikis);

                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setView(view);
                alert.setCancelable(false);
                final AlertDialog dialog=alert.create();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        String ingilizceal=ingilizce.getText().toString().trim();
                        String turkceal=turkce.getText().toString().trim();

                        if (TextUtils.isEmpty(ingilizceal)) {
                            Snackbar.make(v,"İngilizce Kelime Giriniz",Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(turkceal)) {
                            Snackbar.make(v,"Turkce Kelime Giriniz",Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        new a2NotlarDao().notEkle(vt,ingilizceal,turkceal);



                        Toast.makeText(context,"Kelime Başarıyla Eklendi.", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });




                dialog.show();



            }
        });


        return rootview;
    }


}
