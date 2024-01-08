package com.example.purplecloud;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsSearchResults extends Fragment {

    private RecyclerView searchResultsRV;
    private ArrayList<NewsArticle> newsArticleArrayList;
    private NewsArticleRVAdapter newsArticleRVAdapter;
    private String queryString;
    private String sortBy;

    public RecyclerView getSearchResultsRV() {
        return searchResultsRV;
    }

    public void setSearchResultsRV(RecyclerView searchResultsRV) {
        this.searchResultsRV = searchResultsRV;
    }

    public ArrayList<NewsArticle> getNewsArticleArrayList() {
        return newsArticleArrayList;
    }

    public void setNewsArticleArrayList(ArrayList<NewsArticle> newsArticleArrayList) {
        this.newsArticleArrayList = newsArticleArrayList;
    }

    public NewsArticleRVAdapter getNewsArticleRVAdapter() {
        return newsArticleRVAdapter;
    }

    public void setNewsArticleRVAdapter(NewsArticleRVAdapter newsArticleRVAdapter) {
        this.newsArticleRVAdapter = newsArticleRVAdapter;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public SearchResults() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment SearchResults.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static SearchResults newInstance(String param1, String param2) {
//        SearchResults fragment = new SearchResults();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_search_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchResultsRV = getActivity().findViewById(R.id.idRVSearchResults);
        newsArticleArrayList = new ArrayList<>();
        newsArticleRVAdapter = new NewsArticleRVAdapter(newsArticleArrayList, getContext());
        searchResultsRV.setAdapter(newsArticleRVAdapter);
        searchNewsArticles();
        newsArticleRVAdapter.notifyDataSetChanged();

    }


    public void searchNewsArticles(){
        //loadingPB visibility here
        newsArticleArrayList.clear();
        //sortBy later on

        String url = "https://newsapi.org/v2/everything?q=" + queryString + "&language=en&apiKey=e5b4f2d86fef41209d3b6737b2820d6a";

        String sortByUrl = ""; //later on

        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsResults> call;

        if (this.sortBy == null){
            call = retrofitAPI.getAllNews(url);
        } else {
            call = retrofitAPI.getAllNews(sortByUrl);
        }


        call.enqueue(new Callback<NewsResults>() {
            @Override
            public void onResponse(Call<NewsResults> call, Response<NewsResults> response) {
                NewsResults newsResults = response.body();
                //loadingPB visibility gone


                //First Image not appearing debug here!!
                ArrayList<NewsArticle> temp = newsResults.getArticles();
                for(int i=0; i<temp.size(); i++){
                    newsArticleArrayList.add(new NewsArticle(temp.get(i).getAuthor(), temp.get(i).getTitle(),
                            temp.get(i).getDescription(), temp.get(i).getUrl(), temp.get(i).getUrlToImage(),
                            temp.get(i).getPublishedAt(), temp.get(i).getContent()
                    ));
                }

                newsArticleRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsResults> call, Throwable t) {
                Toast.makeText(getActivity(),"Failed to fetch news!", Toast.LENGTH_SHORT).show();

            }
        });



    }




}