package com.example.nytimes_best_seller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        Toast.makeText(this, getIntent().getStringExtra("list_name"), Toast.LENGTH_SHORT).show();
    }
}
