package com.example.nytimes_best_seller;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nytimes_best_seller.API.Model.ServerResponse;
import com.example.nytimes_best_seller.API.Service.NewYorkTimesAPI;

import java.net.URI;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FragmentActivity {

    String amazonProductURL;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager =  findViewById(R.id.pager);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());

        viewPager.setAdapter(swipeAdapter);
        final TextView amazonUrl = findViewById(R.id.textView);
        final Button buyBook = findViewById(R.id.button_id);
        buyBook.setVisibility(View.INVISIBLE);

        //Setting up the API call using retrofit

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/books/v3/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        NewYorkTimesAPI client = retrofit.create(NewYorkTimesAPI.class);   //using an Interface to make API call
        Call<ServerResponse> serverCall = client.getServerInfo();

        //Actually making the call

        serverCall.enqueue(new Callback<ServerResponse>() {

            //getting a response
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                amazonProductURL = response.body().getResults().get(4).getAmazonProductUrl();

                amazonUrl.setText(amazonProductURL);
                buyBook.setVisibility(View.VISIBLE);

                buyBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openProductPage(amazonProductURL);
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

    public void openProductPage(String url){
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }





}
