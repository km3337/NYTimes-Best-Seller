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

import com.example.nytimes_best_seller.API.Model.ResultsItem;
import com.example.nytimes_best_seller.API.Model.ServerResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Foster Brown on 3/13/2019.
 */

public class BookList {
    ListView bookListView;
    ArrayList<String> bookTitles;
    final ArrayAdapter<String> adapter;
    ArrayList<Book> books;
    final int NUMBOOKS = 15;

    public BookList(final Context context, ListView listOfBooks){
        this.bookListView = listOfBooks;
        bookTitles = new ArrayList<String>();
        books = new ArrayList<Book>();
        adapter = new ArrayAdapter<String>(context, R.layout.book_item, bookTitles);
        bookListView.setAdapter(adapter);
    }

    // Refreshes BookList with new books from API
    public void initializeBookList(final ServerResponse serverResponse){
        bookTitles.clear();
        int numResults = serverResponse.getNumResults ();
        for(int i = 0;i < numResults;i++ ){
            Log.wtf("taf", Integer.toString(numResults));
            //bookTitles.add(serverResponse.getResults().get (i).getBookDetails ().get ( 0 ).getTitle ());
            books.add ( new Book ( serverResponse.getResults().get (i).getBookDetails ().get ( 0 ).getTitle (),
                    serverResponse.getResults ().get ( i ).getWeeksOnList ()) );
            bookTitles.add(books.get ( i ).name);
        }
        adapter.notifyDataSetChanged ();
    }

    // Directs each list item to it's book details page
    public void setItemListener(final Context context, final ServerResponse serverResponse){
        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener (){
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                ResultsItem resultsItem = serverResponse.getResults().get(position);
                startDetails(v, context, resultsItem);

                Log.wtf ( "Called","OpenProductPage was called" );
            }
        };
        bookListView.setOnItemClickListener(messageClickedHandler);
    }

    //Sorts books
    public void sort(String flag){
        switch(flag){
            case("bwa") : Collections.sort ( books, new SortByWeeksAscending ());break;
            case("bwd") : Collections.sort ( books, new SortByWeeksDescending ());break;
        }
        refresh();


    }

    public void startDetails(View v, Context context, ResultsItem resultsItem) {/*, BookDetailsItem bookDetailsItem){*/
        Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.putExtra("productURL", resultsItem.getAmazonProductUrl());

        context.startActivity(intent);
    }


    //Resets array to be updated
    private void refresh(){
        bookTitles.clear();
        for(int i = 0;i<NUMBOOKS;i++){
            Log.wtf ( "Book", books.get ( i ).name + " " + Integer.toString (  books.get ( i ).weeksOnList));
            bookTitles.add(books.get ( i ).name);
        }
        adapter.notifyDataSetChanged ();
    }


}


//Book class that links attributes we want to sort by
class Book{
    String name;
    int weeksOnList;
    public Book(String name,int weeksOnList){
        this.name = name;
        this.weeksOnList = weeksOnList;
    }
}

class SortByWeeksAscending implements Comparator<Book> {
    public int compare(Book a,Book b){
        return a.weeksOnList - b.weeksOnList;
    }
}
class SortByWeeksDescending implements Comparator<Book> {
    public int compare(Book a,Book b){
        return b.weeksOnList - a.weeksOnList;
    }
}