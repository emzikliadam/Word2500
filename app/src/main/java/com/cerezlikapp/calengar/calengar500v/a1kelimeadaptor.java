package com.cerezlikapp.calengar.calengar500v;

import android.app.AlertDialog;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class a1kelimeadaptor extends RecyclerView.Adapter<a1kelimeadaptor.cardtutucu> implements TextToSpeech.OnInitListener{
    private Context mcontext;
    private List<a1verinesne> kelimeliste;
    private TextToSpeech tts; //yeni ekledim

    public a1kelimeadaptor(Context mcontext, List<a1verinesne> kelimeliste) {
        this.mcontext = mcontext;
        this.kelimeliste = kelimeliste;
    }

    public class cardtutucu extends RecyclerView.ViewHolder{
        private TextView numara, ingilizce, turkce;
        private CardView kelime_card;

        View itemView;
        public cardtutucu(View itemView) {
        super(itemView);
        kelime_card=itemView.findViewById(R.id.kelime_card);
        numara=itemView.findViewById(R.id.numara);
        ingilizce=itemView.findViewById(R.id.ingilizce);
        turkce=itemView.findViewById(R.id.turkce);


    }
    }



    @Override
    public cardtutucu onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.a1besyuzcard,viewGroup,false);
        return new cardtutucu(view);
    }

    @Override
    public void onBindViewHolder(cardtutucu holder, int i) {

    final a1verinesne kelime=kelimeliste.get(i);
    holder.numara.setText(kelime.getId()+"");
    holder.ingilizce.setText(kelime.getIngilizce());
    holder.turkce.setText(kelime.getTurkce());

    tts=new TextToSpeech(mcontext,null);


        //burayı yeni ekledim
        holder.numara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(kelime.getIngilizce(),tts.QUEUE_FLUSH,null); //2

            }
        });

    holder.kelime_card.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //AlertDiolog
            AlertDialog.Builder builder=new AlertDialog.Builder(mcontext);
            builder.setTitle("Kelime "+kelime.getId()).setMessage("İngilizce: "+kelime.getIngilizce()+"\n\n----------------------\n\n"+
            "Türkçe: "+kelime.getTurkce());



            builder.create().show();


        }
    });



    }

    @Override
    public int getItemCount() {
        return kelimeliste.size();
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

