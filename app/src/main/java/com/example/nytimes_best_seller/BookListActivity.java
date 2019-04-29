package com.example.nytimes_best_seller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.nytimes_best_seller.Book_API.Model.BooksResponse;
import com.example.nytimes_best_seller.Book_API.Service.BooksAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_main);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/books/v3/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        BooksAPI client = retrofit.create(BooksAPI.class);   //using an Interface to make API call
        Call<BooksResponse> serverCall = client.getServerInfo(getIntent().getStringExtra("list_name"));

//        serverCall.enqueue(new Callback<BooksResponse>() {
//
//            //getting a response
//            @Override
//            public void onResponse(Call<BooksResponse> call, final Response<BooksResponse> response) {
//                // Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
//                // booklist.initializeBookList ( response.body () );
//                //  booklist.setItemListener ( MainActivity.this, response.body () );
//                Toast.makeText(BookListActivity.this,response.body().getResults().get(0).getListName() + " successfully loaded", Toast.LENGTH_LONG).show();
//            }
//
//            //No response (invalid URL/call)
//            @Override
//            public void onFailure(Call<BooksResponse> call, Throwable t) {
//                Toast.makeText(BookListActivity.this, "fail", Toast.LENGTH_LONG).show();
//            }
//        });

    }


}
