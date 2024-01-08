package com.example.purplecloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsArticleActivity extends AppCompatActivity {

    private String title, description, content, imageURL, pubDate, url, author;
    private ImageView newsImageIV;
    private TextView titleTV, descTV, contentTV, authorTV, pubDateTV;
    private Button readFullNewsBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article);
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        content = getIntent().getStringExtra("content");
        imageURL = getIntent().getStringExtra("imageURL");
        pubDate = getIntent().getStringExtra("pubDate");
        url = getIntent().getStringExtra("url");
        author = getIntent().getStringExtra("author");

        newsImageIV = findViewById(R.id.newsDetailIV);
        titleTV = findViewById(R.id.newsDetailHeadTV);
        descTV = findViewById(R.id.newsDetailDescTV);
        contentTV = findViewById(R.id.newsDetailContentTV);
        authorTV = findViewById(R.id.newsDetailAuthorTV);
        pubDateTV = findViewById(R.id.newsDetailPubDateTV);
        readFullNewsBut = findViewById(R.id.newsDetailFullNewsButton);

        Picasso.get().load(imageURL).into(newsImageIV);
        titleTV.setText(title);
        descTV.setText(description);
        contentTV.setText(content);
        authorTV.setText(author);
        pubDateTV.setText(pubDate);
        readFullNewsBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }
}