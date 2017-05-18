package com.hurs.alejandrogs.hurs01z;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alejandrogs.hurs01z.R;

import java.util.List;

/**
 * Created by alejandrogs on 12/05/17.
 */

public class RecyclerAdapterCardView extends RecyclerView.Adapter<RecyclerAdapterCardView.ViewHolder> {

    List<photorv> lista;

    public RecyclerAdapterCardView(List<photorv> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(lista.get(position).title);
        holder.image.setImageResource(lista.get(position).imageId);
    }

    @Override
    public int getItemCount() {

        return lista.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.CardView);
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageView)itemView.findViewById(R.id.image);
        }

    }
}
