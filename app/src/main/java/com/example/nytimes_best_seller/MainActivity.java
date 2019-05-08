package com.example.nytimes_best_seller;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
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
    Button sort;
    BookList booklist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView bookListView = findViewById(R.id.list_view);
        booklist = new BookList ( this, bookListView );
        sort = findViewById ( R.id.sort );

        Toast.makeText(MainActivity.this,"Loading Books", Toast.LENGTH_LONG).show();


        Call<BooksResponse> serverCall = buildServerCall();

        //Actually making the call
        serverCall.enqueue(new Callback<BooksResponse>() {

            //getting a response
            @Override
            public void onResponse(Call<BooksResponse> call, final Response<BooksResponse> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                booklist.initializeBookList ( response.body () );
                booklist.setItemListener ( MainActivity.this, response.body () );
                sort.setOnClickListener ( new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        showPopup();
                    }
                });


            }

            //No response (invalid URL/call)
            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                if (t instanceof Exception) {
                    AlertDialog.Builder alert = new AlertDialog.Builder (MainActivity.this);
                            alert.setTitle("Unable to reach server");
                            alert.setMessage("Please check your internet connection");
                            alert.setPositiveButton("OK", null);
                            alert.show();

                }
                //Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();
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

    public void showPopup(){
        PopupMenu popupSortMenu = new PopupMenu(MainActivity.this, sort);
        popupSortMenu.getMenuInflater().inflate(R.menu.popup_sort_menu,popupSortMenu.getMenu());
        popupSortMenu.show();
        popupSortMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.wtf("tag", menuItem.toString());
                switch(menuItem.toString()){
                    case("By Week-Ascending") : booklist.sort("bwa");break;
                    case("By Week-Descending") : booklist.sort("bwd");break;
                }
                return true;
            }
        });
    }
}
