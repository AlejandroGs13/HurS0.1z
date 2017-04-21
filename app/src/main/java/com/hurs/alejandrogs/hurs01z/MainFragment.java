package com.hurs.alejandrogs.hurs01z;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alejandrogs.hurs01z.NoteActivity;
import com.example.alejandrogs.hurs01z.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by alejandrogs on 11/04/17.
 */

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList IDs;
    BDHelper bd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        IDs = new ArrayList();
        final View view  =inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        //Implementacion de el recyclerview
        bd= new BDHelper(getActivity().getApplicationContext());
        /*
        for (String datos: Data){
            IDs.add(datos);
        }*/
        IDs.addAll(bd.getAllNotes());
        RecyclerAdapter adapter = new RecyclerAdapter(IDs);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("DemoRecView", "Pulsado el elemento " + recyclerView.getChildPosition(v));
                //Toast.makeText(v.getContext(),"Pulso"+recyclerView.getChildPosition(v),Toast.LENGTH_SHORT).show();
                //preba(v,String.valueOf(IDs.get(recyclerView.getChildPosition(v))));
                VerNota(v,ID(v,String.valueOf(IDs.get(recyclerView.getChildPosition(v)))),String.valueOf(IDs.get(recyclerView.getChildPosition(v))),"1");
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    public void preba(View view,String id){
        Toast.makeText(view.getContext(),bd.getNote(id),Toast.LENGTH_SHORT).show();
    }

    public String ID(View view,String id){
        return bd.getNote(id);
    }


    public void VerNota(View view,String nota,String id,String editable){
        Intent Nota =new Intent(view.getContext().getApplicationContext(),NoteActivity.class);
        Nota.putExtra("Nota",nota);
        Nota.putExtra("ID",id);
        Nota.putExtra("Edit",editable);
        startActivity(Nota);
        getActivity().finish();
    }

}
