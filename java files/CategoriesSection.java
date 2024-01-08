package com.example.purplecloud;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class CategoriesSection extends Fragment implements CategoryRVAdapter.CategoryClickInterface{

    private RecyclerView category;
    private ArrayList<CategoryRV> categoryRVArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private TopHeadlines topHeadlinesCategory;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public CategoriesSection() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CategoriesSection.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CategoriesSection newInstance(String param1, String param2) {
//        CategoriesSection fragment = new CategoriesSection();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
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
        return inflater.inflate(R.layout.fragment_categories_section, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        category = getActivity().findViewById(R.id.idRVCategories);
        categoryRVArrayList = new ArrayList<>();
        categoryRVAdapter = new CategoryRVAdapter(categoryRVArrayList, getContext(), this::onCategoryClick );
        category.setAdapter(categoryRVAdapter);
        fetchCategories();
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryRVArrayList.get(position).getCategory();
        topHeadlinesCategory = new TopHeadlines();
        topHeadlinesCategory.setCategory(category);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, topHeadlinesCategory).commit();

    }


    //ImagesURLs and CategoryNames in an xml file and use arrayList
    private void fetchCategories() {
        categoryRVArrayList.add(new CategoryRV("All", "https://www.invaluable.com/blog/wp-content/uploads/sites/77/2018/03/invaluable-collecting-psychology-hero-v1.0-2x.jpg"));
        categoryRVArrayList.add(new CategoryRV("Business", "https://hbr.org/resources/images/article_assets/2022/08/Hero-Image.png"));
        categoryRVArrayList.add(new CategoryRV("Entertainment", "https://media.allure.com/photos/6127a91ac2052c31747415dd/16:9/w_2560%2Cc_limit/blackpink%2520lisa%2520gold%2520background.jpg"));
        categoryRVArrayList.add(new CategoryRV("General", "https://d1inegp6v2yuxm.cloudfront.net/royal-academy/image/upload/c_limit,cs_tinysrgb,dn_72,f_auto,fl_progressive.keep_iptc,w_1200/uerr4lgxnem9dhidjidd.jpg"));
        categoryRVArrayList.add(new CategoryRV("Health", "https://www.expatica.com/app/uploads/sites/10/2019/11/Health-Insurance-Quotes-1-1920x1080.jpg"));
        categoryRVArrayList.add(new CategoryRV("Science", "https://epi-rsc.rsc-cdn.org/globalassets/05-journals-books-databases/our-journals/00-journal-pages-heros/Chemical-Science-HERO.jpg?version=9e72b3c3"));
        categoryRVArrayList.add(new CategoryRV("Sports", "https://cdn.britannica.com/84/139484-050-D91679CC/Michael-Ballack-Germany-Italy-Cristian-Zaccardo-March-1-2006.jpg"));
        categoryRVArrayList.add(new CategoryRV("Technology", "https://greatpeopleinside.com/wp-content/uploads/2017/05/HR-GR8-technology.jpg"));
        categoryRVAdapter.notifyDataSetChanged();
    }
}