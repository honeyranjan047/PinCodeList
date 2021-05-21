package com.pizerolabs.pincodelist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    ArrayList<com.pizerolabs.pincodelist.CategoryModel> categoryModels;

    public CategoryAdapter(Context context, ArrayList<com.pizerolabs.pincodelist.CategoryModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, null);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        com.pizerolabs.pincodelist.CategoryModel categoryModel = categoryModels.get(position);
        holder.textView.setText(categoryModel.getCityZip());
        holder.textView2.setText(categoryModel.getCityName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode( categoryModel.getCityZip()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView textView, textView2;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textView2 = itemView.findViewById(R.id.zipCode);
            textView = itemView.findViewById(R.id.category);
        }
    }
}
