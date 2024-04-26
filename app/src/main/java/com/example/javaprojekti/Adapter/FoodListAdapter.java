package com.example.javaprojekti.Adapter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.javaprojekti.Activity.DetailActivity;
import com.example.javaprojekti.Activity.ListFoodActivity;
import com.example.javaprojekti.Domain.Foods;
import com.example.javaprojekti.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.viewholder> {
    ArrayList<Foods> items;
    Context context;

    public FoodListAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflates = LayoutInflater.from(context).inflate(R.layout.viewholder_list_food, parent, false);
        return new viewholder(inflates);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder,int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.timeTxt.setText("$" + items.get(position).getPrice());
        holder.rateTxt.setText("" + items.get(position).getStar());

        // Load image using Glide
        Glide.with(context)
                .load(items.get(position).getImagePath()) // Firebase Storage URL
                .placeholder(R.drawable.pizza) // Placeholder image while loading
                .centerCrop()
                .into(holder.img);

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context, DetailActivity.class);
            intent.putExtra("object",items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt, priceTxt, rateTxt, timeTxt;
        ImageView img;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            rateTxt = itemView.findViewById(R.id.rateTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            img = itemView.findViewById(R.id.img);

        }
    }
}

