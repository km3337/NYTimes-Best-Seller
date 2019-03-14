package com.example.nytimes_best_seller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nytimes_best_seller.API.Model.ServerResponse;

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

    public void refreshBookList(final ServerResponse serverResponse){
        bookTitles.clear();
        int numResults = serverResponse.getNumResults ();
        for(int i = 0;i < numResults;i++ ){
            bookTitles.add(serverResponse.getResults().get (i).getBookDetails ().get ( 0 ).getTitle ());
        }
        adapter.notifyDataSetChanged ();
    }


    public void setItemListener(final Context context, final ServerResponse serverResponse){
        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener (){
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                openProductPage ( context,serverResponse.getResults ().get ( position ).getAmazonProductUrl () );
                Log.wtf ( "Called","OpenProductPage was called" );
            }
        };
//
        bookListView.setOnItemClickListener(messageClickedHandler);
    }

    private void openProductPage(final Context context, String url){
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        Log.wtf ( "openProductPage", "Opening product page");
        if(intent.resolveActivity(context.getPackageManager()) != null){
            context.startActivity(intent);
        }
    }



}