package com.cerezlikapp.calengar.calengar500v;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class a3ongun extends Fragment {
    private HashSet<a1verinesne> secenekkaristir=new HashSet<>();
    private ArrayList<a1verinesne> seceneklerliste=new ArrayList<>();


    private a1veritabani vt;
    private a1verinesne dogrusoru;
    private ArrayList<a1verinesne> soruliste;
    private ArrayList<a1verinesne> yanlıssecenek;


    private AlertDialog dialog;







    private int sorusayac=0;
    private int yanlıssayac=0;
    private int dogrusayac=0;



    TextView soru;
    TextView dogru;
    TextView yanlis;
    TextView kelime;
    TextView close;
    TextView scor;

    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;


    Button btn;
    TextView txt;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public static final String SHARED_SCORE_KEY="dogruSayac";
    public static final String SHARED_NAME="scorSayac";
    private int scorSayac;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootview=inflater.inflate(R.layout.a3ongun, container, false);
        final Context context=container.getContext();

        btn=rootview.findViewById(R.id.button);
        txt=rootview.findViewById(R.id.txt);
        scor=rootview.findViewById(R.id.scor);

        veritabanikopyala();

        preferences = context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        editor=preferences.edit();

        scorSayac=preferences.getInt(SHARED_SCORE_KEY,0);
        scor.setText("SCOR:2500/"+scorSayac);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=getActivity().getLayoutInflater();
                View view=inflater.inflate(R.layout.a3alertdialog,null,false);

                soru=view.findViewById(R.id.soru);
                dogru=view.findViewById(R.id.dogru);
                yanlis=view.findViewById(R.id.yanlis);
                kelime=view.findViewById(R.id.kelime);
                buttonA=view.findViewById(R.id.buttonA);
                buttonB=view.findViewById(R.id.buttonB);
                buttonC=view.findViewById(R.id.buttonC);
                buttonD=view.findViewById(R.id.buttonD);
                close=view.findViewById(R.id.close);

                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setView(view);
                alert.setCancelable(false);
                dialog=alert.create();

                vt=new a1veritabani(context);

                soruliste=new a3dao().rastgele5(vt);


                soruyukle();

                buttonA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dogruKontrol(buttonA);
                        sayacKontrol();
                    }
                });
                buttonB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dogruKontrol(buttonB);
                        sayacKontrol();

                    }
                });
                buttonC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dogruKontrol(buttonC);
                        sayacKontrol();

                    }
                });
                buttonD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dogruKontrol(buttonD);
                        sayacKontrol();

                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (dogrusayac>scorSayac){

                            editor.putInt(SHARED_SCORE_KEY,dogrusayac).commit();

                            scor.setText("SCOR:2500/"+dogrusayac);
                        }else{
                            scor.setText("SCOR:2500/"+scorSayac);
                        }
                        dialog.cancel();

                    }
                });

                dialog.show();
            }
        });





        return rootview;
    }

    public void soruyukle() {
        soru.setText((sorusayac+1)+". SORU");
        dogru.setText("Dogru:"+dogrusayac);
        yanlis.setText("Yanlıs:"+yanlıssayac);

        dogrusoru=soruliste.get(sorusayac);


        yanlıssecenek=new a3dao().rastgele3(vt,dogrusoru.getId());

        kelime.setText(dogrusoru.getTurkce());


        secenekkaristir.clear();

        secenekkaristir.add(dogrusoru);
        secenekkaristir.add(yanlıssecenek.get(0));
        secenekkaristir.add(yanlıssecenek.get(1));
        secenekkaristir.add(yanlıssecenek.get(2));

        seceneklerliste.clear();

        for(a1verinesne b:secenekkaristir) {
            seceneklerliste.add(b);
        }

        buttonA.setText(seceneklerliste.get(0).getIngilizce());
        buttonB.setText(seceneklerliste.get(1).getIngilizce());
        buttonC.setText(seceneklerliste.get(2).getIngilizce());
        buttonD.setText(seceneklerliste.get(3).getIngilizce());

    }

    public void dogruKontrol(Button buttonf) {

        String buttonyazi=buttonf.getText().toString();
        String dogrucevap=dogrusoru.getIngilizce();

        if (buttonyazi.equals(dogrucevap)) {
            dogrusayac++;
        }else {
            yanlıssayac++;
        }





    }
    public void sayacKontrol() {
        sorusayac++;

        if(sorusayac<=2500) {
            soruyukle();
        }else{
            dialog.cancel();
        }

    }

    public void veritabanikopyala() {
        DatabaseCopyHelper databaseCopyHelper=new DatabaseCopyHelper(getContext());
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseCopyHelper.openDataBase();
    }


}
