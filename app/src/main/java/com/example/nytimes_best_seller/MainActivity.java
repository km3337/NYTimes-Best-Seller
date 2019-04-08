package com.example.nytimes_best_seller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nytimes_best_seller.Book_API.Model.BooksResponse;
import com.example.nytimes_best_seller.Book_API.Service.BooksAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String amazonProductURL;
    ListView bookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView bookListView = findViewById(R.id.list_view);
        final BookList booklist = new BookList ( this, bookListView );

        Toast.makeText(MainActivity.this,"Loading Books", Toast.LENGTH_LONG).show();


        Call<BooksResponse> serverCall = buildServerCall();

        //Actually making the call
        serverCall.enqueue(new Callback<BooksResponse>() {

            //getting a response
            @Override
            public void onResponse(Call<BooksResponse> call, final Response<BooksResponse> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                booklist.refreshBookList ( response.body () );
                booklist.setItemListener ( MainActivity.this, response.body () );

            }

            //No response (invalid URL/call)
            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();
            }
        });


    }

    //Setting up the API call using retrofit
    public Call<BooksResponse> buildServerCall(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/books/v3/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        BooksAPI client = retrofit.create(BooksAPI.class);   //using an Interface to make API call
        Call<BooksResponse> serverCall = client.getServerInfo();
        return serverCall;

    }
}
