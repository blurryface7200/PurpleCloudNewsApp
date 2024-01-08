package com.example.purplecloud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsArticleRVAdapter extends RecyclerView.Adapter<NewsArticleRVAdapter.ViewHolder> {


    private ArrayList<NewsArticle> newsArticlesArrayList;
    private Context context;

    public NewsArticleRVAdapter(ArrayList<NewsArticle> newsArticlesArrayList, Context context) {
        this.newsArticlesArrayList = newsArticlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsArticleRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_top_headlines,parent,false);
        return new NewsArticleRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticleRVAdapter.ViewHolder holder, int position) {
        NewsArticle newsArticle = newsArticlesArrayList.get(position);
        holder.newsHeadingTV.setText(newsArticle.getTitle());
        holder.newsTextTV.setText(newsArticle.getDescription());
        Picasso.get().load(newsArticle.getUrlToImage()).into(holder.newsImageIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsArticleActivity.class);
                intent.putExtra("title", newsArticle.getTitle());
                intent.putExtra("content", newsArticle.getContent());
                intent.putExtra("description", newsArticle.getDescription());
                intent.putExtra("imageURL", newsArticle.getUrlToImage());
                intent.putExtra("url", newsArticle.getUrl());
                intent.putExtra("author", newsArticle.getAuthor());
                intent.putExtra("pubDate", newsArticle.getPublishedAt());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsArticlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView newsHeadingTV;
        private TextView newsTextTV;
        private ImageView newsImageIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //top headlines xml connection
            newsHeadingTV = itemView.findViewById(R.id.idTVTopHeadlines);
            newsTextTV = itemView.findViewById(R.id.idNewsText);
            newsImageIV = itemView.findViewById(R.id.idIVTopHeadlines);
        }
    }
}
