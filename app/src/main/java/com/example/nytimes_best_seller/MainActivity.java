package com.example.nytimes_best_seller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nytimes_best_seller.API.Model.ServerResponse;
import com.example.nytimes_best_seller.API.Service.NewYorkTimesAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/books/v3/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        NewYorkTimesAPI client = retrofit.create(NewYorkTimesAPI.class);
        Call<ServerResponse> serverCall = client.getServerInfo();
        serverCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                textView.setText(response.body().getResults().get(4).getAmazonProductUrl());
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();
            }
        });
    }

}
