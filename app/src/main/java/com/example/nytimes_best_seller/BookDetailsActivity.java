package com.example.nytimes_best_seller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        TextView    rankTextView = findViewById(R.id.rank_number),
                    titleTextView = findViewById(R.id.title),
                    authorTextView = findViewById(R.id.author),
                    weeksTextView = findViewById(R.id.weeks_on_list),
                    descTextView = findViewById(R.id.description);

        int rank = getIntent().getIntExtra("rank", 0);
        rankTextView.setText(String.format("%d", rank));

        String title = getIntent().getStringExtra("title");
        titleTextView.setText(title);

        String author = getIntent().getStringExtra("author");
        authorTextView.setText(author);

        int weeks = getIntent().getIntExtra("weeksOnList", 0);
        weeksTextView.setText(weeks + (weeks == 1 ? " week on list" : " weeks on list"));

        String description = getIntent().getStringExtra("description");
        descTextView.setText(description);


    }

    public void openProductPage(View view) {
        String url = getIntent().getStringExtra("productURL");
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        Log.wtf("openProductPage", "Opening product page");
        startActivity(intent);
    }
}
