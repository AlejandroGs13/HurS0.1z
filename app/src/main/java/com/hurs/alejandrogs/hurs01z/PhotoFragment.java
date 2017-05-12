package com.hurs.alejandrogs.hurs01z;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alejandrogs.hurs01z.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandrogs on 11/04/17.
 */

public class PhotoFragment extends Fragment{
    RecyclerView recyclerView;
    RecyclerAdapterCardView adapter;
    List <photorv> Lista;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view  =inflater.inflate(R.layout.fragment_photos,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.rvc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        initializeData();
        adapter = new RecyclerAdapterCardView(Lista);
        recyclerView.setAdapter(adapter);
        return view;
    }



    public  void initializeData(){
        Lista = new ArrayList<>();
        Lista.add(new photorv("Wachiturro",R.drawable.hola));
        Lista.add(new photorv("Wachiturro",R.drawable.hola));
        Lista.add(new photorv("Wachiturro",R.drawable.hola));
        Lista.add(new photorv("Wachiturro",R.drawable.hola));
    }
}
