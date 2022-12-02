package com.softmeth.pizzeria;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChicagoToppingAdapter extends RecyclerView.Adapter<ChicagoToppingAdapter.ViewHolder> {

    private List<String> chicagoToppings;
    private LayoutInflater inflater;
    private Context context;

    // data is passed into the constructor
    public ChicagoToppingAdapter(Context context, List<String> data) {
        this.inflater = LayoutInflater.from(context);
        this.chicagoToppings = data;
        this.context = context;
    }

    public void onItemClick(View view, int position){

    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.topping_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String top = chicagoToppings.get(position);
        holder.toppingTextView.setText(top);
        Context c= this.context;

        holder.toppingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c,top + " has been successfully added as a topping",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return chicagoToppings.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView toppingTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            toppingTextView = itemView.findViewById(R.id.toppingID);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return chicagoToppings.get(id);
    }

}