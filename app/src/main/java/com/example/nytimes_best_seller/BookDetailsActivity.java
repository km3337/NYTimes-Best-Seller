package com.example.nytimes_best_seller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        final Button buyButton = findViewById(R.id.buy_button);
        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openProductPage(v);
            }
        });

    }

    public void openProductPage(View view) {
        String url = getIntent().getStringExtra("productURL");
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        Log.wtf("openProductPage", "Opening product page");
        startActivity(intent);
    }
}
