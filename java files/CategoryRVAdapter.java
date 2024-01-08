package com.example.purplecloud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private ArrayList<CategoryRV> categoryRVArrayList;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryRVAdapter(ArrayList<CategoryRV> categoryRVArrayList, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRVArrayList = categoryRVArrayList;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_categories, parent, false);
        return new CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, int position) {
        CategoryRV categoryRV = categoryRVArrayList.get(position);
        holder.categoryTV.setText(categoryRV.getCategory());
        Picasso.get().load(categoryRV.getCategoryImageURL()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              categoryClickInterface.onCategoryClick(position);
                categoryClickInterface.onCategoryClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryRVArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private ImageView categoryIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.idTVCategories);
            categoryIV = itemView.findViewById(R.id.idIVCategories);
        }
    }
}
