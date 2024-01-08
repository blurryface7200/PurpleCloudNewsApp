package com.example.purplecloud;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class  MainActivity extends AppCompatActivity {

    //e5b4f2d86fef41209d3b6737b2820d6a

    BottomNavigationView bottomNavigationView;

    SearchSection searchSection = new SearchSection();
    CuratedNews curatedNews = new CuratedNews();
    TopHeadlines topHeadlines = new TopHeadlines();
    CategoriesSection categoriesSection = new CategoriesSection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Top Toolbar
//        Toolbar mToolbar = findViewById(R.id.toolbar_main);
//        setSupportActionBar(mToolbar);
        //You will need menu inflater function to inflate the icons
        //if u use the above approach

        //Bottom Toolbar
        bottomNavigationView  = findViewById(R.id.bottomBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchSection).commit();

//        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.notification);
//        badgeDrawable.setVisible(true);
//        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search_section_icon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchSection).commit();
                        return true;
                    case R.id.curated_news_icon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,curatedNews).commit();
                        return true;
                    case R.id.top_headlines_icon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,topHeadlines).commit();
                        return true;
                    case R.id.categories_section_icon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,categoriesSection).commit();
                        return true;
                }
                return false;
            }
        });



    }




}
