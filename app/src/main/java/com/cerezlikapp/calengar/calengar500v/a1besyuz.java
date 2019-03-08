package com.cerezlikapp.calengar.calengar500v;




import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;


public class a1besyuz extends Fragment {

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private RecyclerView rv;
    private Toolbar toolbar3;
    private ArrayList<a1verinesne> kelimeArrayList;
    private a1kelimeadaptor adaptor;
    private a1veritabani vt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.a1besyuz, container, false);
        Context context=container.getContext();
        rv=rootview.findViewById(R.id.rv);
        toolbar3=rootview.findViewById(R.id.toolbar3);
        toolbar3.setTitle("2500 Kelime");
        ((AppCompatActivity)context).setSupportActionBar(toolbar3);

        vt=new a1veritabani(context);

        veritabanikopyala();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));

        kelimeArrayList=new a1KelimelerDao().tumkelimeler(vt);



        adaptor=new a1kelimeadaptor(context,kelimeArrayList);

        rv.setAdapter(adaptor);


        return rootview;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.besyuzarama, menu);
        MenuItem searchItem = menu.findItem(R.id.action_ara);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    aramaYap(newText);

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    aramaYap(query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ara:
                
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
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

    public void aramaYap(String aramaKelime) {
        kelimeArrayList=new a1KelimelerDao().kelimeara(vt,aramaKelime);

        adaptor=new a1kelimeadaptor(getContext(),kelimeArrayList);

        rv.setAdapter(adaptor);
    }




}


