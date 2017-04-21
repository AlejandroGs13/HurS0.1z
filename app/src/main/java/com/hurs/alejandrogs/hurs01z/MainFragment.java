package com.hurs.alejandrogs.hurs01z;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alejandrogs.hurs01z.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by alejandrogs on 11/04/17.
 */

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    BDHelper bd;
     String[] Data = new String[]{"Elemento 1","Elemento 2","Elemento 3","Elemento 4","Elemento 5","Elemento 6"
            ,"Elemento 7","Elemento 8","Elemento 9","Elemento 10","Elemento 11","Elemento 12","Elemento 13","Elemento 14"
            ,"Elemento 15"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ArrayList IDs = new ArrayList();
        View view  =inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        //Implementacion de el recyclerview
        bd= new BDHelper(getActivity().getApplicationContext());
        /*
        for (String datos: Data){
            IDs.add(datos);
        }*/
        IDs.addAll(bd.getAllCountries());
        RecyclerAdapter adapter = new RecyclerAdapter(IDs);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + recyclerView.getChildPosition(v));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }


}
