package com.softmeth.pizzeria;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChicagoToppingAdapter extends RecyclerView.Adapter<ChicagoToppingAdapter.ViewHolder> {

    public List<String> chicagoToppings;
    private LayoutInflater inflater;
    private Context context;
    private ClickListener onClickListener;

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    // data is passed into the constructor
    public ChicagoToppingAdapter(Context context, List<String> data) {
        if (data == null) {
            this.chicagoToppings = new ArrayList<>();
        } else
            this.chicagoToppings = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }


    /**
     * This method will inflate the row layout for the items in the RecyclerView
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.topping_row, parent, false);
        return new ViewHolder(view);
    }


    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     *
     * @param holder   the instance of ViewHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String top = chicagoToppings.get(position);
        holder.toppingTextView.setText(top);
        Context c = this.context;

//        holder.toppingTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//            }
//        });
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
            if (onClickListener != null) onClickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return chicagoToppings.get(id);
    }

    public int getSize() {
        return chicagoToppings.size();
    }

    public void addItem(String topping) {
        chicagoToppings.add(topping);
        this.notifyDataSetChanged();
        ;
    }

    public void removeItem(String topping) {
        chicagoToppings.remove(topping);
        this.notifyDataSetChanged();
        ;
    }

    public void setItems(List<String> toppings) {
        chicagoToppings = new ArrayList<>();
        chicagoToppings.addAll(toppings);
        this.notifyDataSetChanged();
    }

    public void setClickListener(ClickListener listener) {
        this.onClickListener = listener;
    }
}