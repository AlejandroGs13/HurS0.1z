package com.hurs.alejandrogs.hurs01z;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alejandrogs.hurs01z.R;

/**
 * Created by alejandrogs on 11/04/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] Data;

    public RecyclerAdapter(String[] Data){
        this.Data = Data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(Data[position]);

    }

    @Override
    public int getItemCount() {
        return Data.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.item);
        }
    }
}

