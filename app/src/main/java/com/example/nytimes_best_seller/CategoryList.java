package com.example.nytimes_best_seller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nytimes_best_seller.Category_API.Model.CategoryDetails;
import com.example.nytimes_best_seller.Category_API.Model.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {
    ListView listofCategories;
    ArrayList<String> categoryTitles;
    final ArrayAdapter<String> adapter;
    List<CategoryDetails> categoryResults;

    public CategoryList(final Context context, ListView listOfCategories){
        this.listofCategories = listOfCategories;
        categoryTitles = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(context, R.layout.text_item, categoryTitles);
        listOfCategories.setAdapter(adapter);
    }

    public void initializeBookList(final CategoryResponse serverResponse){
        categoryTitles.clear();
        int numResults = serverResponse.getNumResults ();
        categoryResults = serverResponse.getResults();
        for(int i = 0;i < numResults;i++ ){
            categoryTitles.add(serverResponse.getResults().get(i).getListName());
        }
        adapter.notifyDataSetChanged();
    }

    public void setItemListener(final Context context, final CategoryResponse serverResponse){
        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener (){
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                Intent intent = new Intent(context, BookListActivity.class);
                intent.putExtra("list_name", serverResponse.getResults().get(position).getListNameEncoded());
                context.startActivity(intent);
            }
        };
        listofCategories.setOnItemClickListener(messageClickedHandler);
    }
}