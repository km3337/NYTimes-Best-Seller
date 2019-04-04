package com.example.nytimes_best_seller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nytimes_best_seller.API.Model.ServerResponse;
import com.example.nytimes_best_seller.API.Service.NewYorkTimesAPI;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
        final Button sort = findViewById ( R.id.sort );

        Toast.makeText(MainActivity.this,"Loading Books", Toast.LENGTH_LONG).show();


        Call<ServerResponse> serverCall = buildServerCall();


        //Actually making the call
        serverCall.enqueue(new Callback<ServerResponse>() {

            //getting a response
            @Override
            public void onResponse(Call<ServerResponse> call, final Response<ServerResponse> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                booklist.refreshBookList ( response.body () );
                booklist.setItemListener ( MainActivity.this, response.body () );

                sort.setOnClickListener ( new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        booklist.sort ();
                        PopupMenu popupSortMenu = new PopupMenu(MainActivity.this, sort);
                        popupSortMenu.getMenuInflater().inflate(R.menu.popup_sort_menu,popupSortMenu.getMenu());
                        popupSortMenu.show();
                    }
                });

            }

            //No response (invalid URL/call)
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();
            }
        });


    }

    //Setting up the API call using retrofit
    public Call<ServerResponse> buildServerCall(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/books/v3/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        NewYorkTimesAPI client = retrofit.create(NewYorkTimesAPI.class);   //using an Interface to make API call
        Call<ServerResponse> serverCall = client.getServerInfo();
        return serverCall;

    }






}
