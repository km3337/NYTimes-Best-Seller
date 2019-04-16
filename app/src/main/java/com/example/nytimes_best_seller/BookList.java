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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Foster Brown on 3/13/2019.
 */

public class BookList {
    ListView bookListView;
    ArrayList<String> bookTitles;
    final ArrayAdapter<String> adapter;
    List<BookResults> bookResults;
    final int NUMBOOKS = 15;


    public BookList(final Context context, ListView listOfBooks){
        this.bookListView = listOfBooks;
        bookTitles = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(context, R.layout.text_item, bookTitles);
        bookListView.setAdapter(adapter);
    }

    public void initializeBookList(final BooksResponse serverResponse){
        bookTitles.clear();
        int numResults = serverResponse.getNumResults ();
        bookResults = serverResponse.getResults();
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
            }
        };
        bookListView.setOnItemClickListener(messageClickedHandler);
    }

    //Sorts books
    public void sort(String flag){
        switch(flag){
            case("bwa") : Collections.sort ( bookResults, new SortByWeeksAscending ());break;
            case("bwd") : Collections.sort ( bookResults, new SortByWeeksDescending ());break;
        }
        refresh();


    }

    //Resets array to be updated
    private void refresh(){
        bookTitles.clear();
        for(int i = 0;i<NUMBOOKS;i++){
            Log.wtf ( "Book", bookResults.get(i).getBookDetails().get(0).getTitle() + " " + Integer.toString (  bookResults.get(i).getRank()));
            bookTitles.add(bookResults.get(i).getBookDetails().get(0).getTitle());
        }
        adapter.notifyDataSetChanged ();
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


class SortByWeeksAscending implements Comparator<BookResults> {
    public int compare(BookResults a,BookResults b){
        return a.getWeeksOnList() - b.getWeeksOnList();
    }
}
class SortByWeeksDescending implements Comparator<BookResults> {
    public int compare(BookResults a,BookResults b){
        return b.getWeeksOnList() - a.getWeeksOnList();
    }
}