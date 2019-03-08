package com.cerezlikapp.calengar.calengar500v;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class calengar500v extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0activity_calengar500v);

        getFragmentManager().beginTransaction().add(R.id.framelayout,new a3ongun()).commit();

        bottomNavigationView=findViewById(R.id.bottomnavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment=null;

                if(menuItem.getItemId()==R.id.ongun) {
                    fragment= new a3ongun();
                }
                if(menuItem.getItemId()==R.id.besyuz) {
                    fragment=new a1besyuz();
                }
                if(menuItem.getItemId()==R.id.ekle) {
                    fragment=new a2kelimeekle();
                }

                getFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();

                return true;
            }
        });
    }

}
