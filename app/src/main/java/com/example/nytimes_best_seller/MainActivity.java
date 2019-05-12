package com.example.nytimes_best_seller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nytimes_best_seller.Category_API.Model.CategoryResponse;
import com.example.nytimes_best_seller.Category_API.Service.BookCategoriesAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    CategoryList categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView bookListView = findViewById(R.id.list_view);
        categoryList = new CategoryList(this, bookListView);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/books/v3/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        BookCategoriesAPI client = retrofit.create(BookCategoriesAPI.class);   //using an Interface to make API call
        Call<CategoryResponse> categoryServerCall = client.getCategoryInfo();

        categoryServerCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Toast.makeText(MainActivity.this, "Categories Loaded", Toast.LENGTH_SHORT).show();
                categoryList.initializeBookList(response.body());
                categoryList.setItemListener(MainActivity.this, response.body());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Categories Failed to Load", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
