package com.example.nytimes_best_seller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nytimes_best_seller.Book_API.Model.BookResults;
import com.example.nytimes_best_seller.Book_API.Model.BooksResponse;

import java.util.ArrayList;

/**
 * Created by Foster Brown on 3/13/2019.
 */

public class BookList {
    ListView bookListView;
    ArrayList<String> bookTitles;
    final ArrayAdapter<String> adapter;

    public BookList(final Context context, ListView listOfBooks){
        this.bookListView = listOfBooks;
        bookTitles = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(context, R.layout.book_item, bookTitles);
        bookListView.setAdapter(adapter);
    }

    public void refreshBookList(final BooksResponse serverResponse){
        bookTitles.clear();
        int numResults = serverResponse.getNumResults ();
        for(int i = 0;i < numResults;i++ ){
            bookTitles.add(serverResponse.getResults().get (i).getBookDetails ().get ( 0 ).getTitle ());
        }
        adapter.notifyDataSetChanged ();
    }


    public void setItemListener(final Context context, final BooksResponse serverResponse){
        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener (){
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                BookResults resultsItem = serverResponse.getResults().get(position);
                startDetails(v, context, resultsItem);

                Log.wtf ( "Called","OpenProductPage was called" );
            }
        };

        bookListView.setOnItemClickListener(messageClickedHandler);
    }

    public void startDetails(View v, Context context, BookResults resultsItem) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.putExtra("productURL", resultsItem.getAmazonProductUrl());
        intent.putExtra("rank", resultsItem.getRank());
        intent.putExtra("ranklastweek", resultsItem.getRankLastWeek());
        intent.putExtra("title", resultsItem.getBookDetails().get(0).getTitle());
        intent.putExtra("weeksOnList", resultsItem.getWeeksOnList());
        intent.putExtra("author", resultsItem.getBookDetails().get(0).getAuthor());
        intent.putExtra("description", resultsItem.getBookDetails().get(0).getDescription());
        context.startActivity(intent);
    }

}
