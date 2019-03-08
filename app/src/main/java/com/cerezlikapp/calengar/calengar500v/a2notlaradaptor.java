package com.cerezlikapp.calengar.calengar500v;


import android.app.AlertDialog;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class a2notlaradaptor extends RecyclerView.Adapter<a2notlaradaptor.cardtutucu> implements TextToSpeech.OnInitListener{
    private Context mcontext;
    private List<a2notlar> notlarliste;
    private a2veritabani vt;
    private TextToSpeech tts; //yeni ekledim


    public a2notlaradaptor(Context mcontext, List<a2notlar> notlarliste) {
        this.mcontext = mcontext;
        this.notlarliste = notlarliste;
    }






    public class cardtutucu extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView id,ingilizce,turkce;


        public cardtutucu(View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.id);
            ingilizce=itemView.findViewById(R.id.ingilizce);
            turkce=itemView.findViewById(R.id.turkce);
            cardView=itemView.findViewById(R.id.kelimeeklecard);
            vt=new a2veritabani(mcontext);



        }



    }

    @NonNull
    @Override
    public cardtutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.a2kelimeeklecard,viewGroup,false);
        return new cardtutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardtutucu holder, final int i) {
        final a2notlar not=notlarliste.get(i);

        holder.id.setText("");
        holder.ingilizce.setText(not.getIngilizce());
        holder.turkce.setText(not.getTurkce());

        tts=new TextToSpeech(mcontext,null);

        //burayı yeni ekledim
        holder.id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(not.getIngilizce(),tts.QUEUE_FLUSH,null); //2

            }
        });









        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view=LayoutInflater.from(mcontext).inflate(R.layout.detayalert,null,false);




                final EditText ingilizce=view.findViewById(R.id.edit_username);
                final EditText turkce=view.findViewById(R.id.edit_password);
                final TextView buttonkaydet=view.findViewById(R.id.buttonkaydet);
                final TextView buttonsil=view.findViewById(R.id.buttonsil);
                final TextView buttonguncelle=view.findViewById(R.id.buttonguncelle);



                AlertDialog.Builder alert=new AlertDialog.Builder(mcontext);

                alert.setView(view);
                alert.setCancelable(false);
                final AlertDialog dialog=alert.create();
                ingilizce.setText(not.getIngilizce());
                turkce.setText(not.getTurkce());

                buttonkaydet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(mcontext,"Kelime Başarıyla Kaydedildi.", Toast.LENGTH_SHORT).show();
                        dialog.cancel();


                    }
                });
                buttonguncelle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ingilizceal=ingilizce.getText().toString().trim();
                        String turkceal=turkce.getText().toString().trim();

                        if (TextUtils.isEmpty(ingilizceal)) {
                            Snackbar.make(v,"İngilizce Kelime Boş Bırakılamaz.",Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(turkceal)) {
                            Snackbar.make(v,"Turkce Kelime Boş Bırakılamaz.",Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        new a2NotlarDao().notGuncelle(vt,not.getId(),ingilizceal,turkceal);
                        a2kelimeekle.refreshAdapter();
                        Toast.makeText(mcontext,"Kelime Başarıyla Güncellendi.", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                buttonsil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new a2NotlarDao().notSil(vt,not.getId());
                        notlarliste.remove(not);
                        notifyDataSetChanged();
                        Toast.makeText(mcontext,"Kelime Başarıyla Silindi.", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });



                dialog.show();


            }
        });








    }

    @Override
    public int getItemCount() {
        return notlarliste.size();
    }


    @Override   //yeni
    public void onInit(int status) {
        if(status==tts.SUCCESS) {
            int sonuc=tts.setLanguage(Locale.US);
            if(sonuc==tts.LANG_MISSING_DATA||sonuc==tts.LANG_NOT_SUPPORTED) {
                Toast.makeText(mcontext,"Bu Dil Desteklenmiyor.",Toast.LENGTH_LONG).show();
            }
            else {


            }
        }
        else{
            Toast.makeText(mcontext,"Basarısız",Toast.LENGTH_LONG).show();
        }

    }


}
