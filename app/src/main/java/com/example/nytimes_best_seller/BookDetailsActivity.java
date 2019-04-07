package com.example.nytimes_best_seller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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
                    descTextView = findViewById(R.id.description),
                    rankDeltaTextView = findViewById(R.id.rankDelta);

        int rank = getIntent().getIntExtra("rank", 0);
        rankTextView.setText(String.valueOf(rank));

        String title = toTitleCase(getIntent().getStringExtra("title"));
        titleTextView.setText(title);

        String author = getIntent().getStringExtra("author");
        authorTextView.setText(author);

        String weeks = String.valueOf(getIntent().getIntExtra("weeksOnList", 0));
        weeks += (weeks.equals("1") ? " week on list" : " weeks on list");
        weeksTextView.setText(weeks);

        String description = getIntent().getStringExtra("description");
        descTextView.setText(description);

        String delta;
        int D1 = getIntent().getIntExtra("ranklastweek", 0);
        int D2 = Math.abs((D1 - rank));
        String dSymbol;
        if (rank < D1)
            dSymbol = "-";
        if (rank > D1)
            dSymbol = "+";
        else
            dSymbol = "~";

        String getDelta= Integer.toString(D2);
        delta = getDelta + " change in rank since last week " + "(" + dSymbol + ")";
        rankDeltaTextView.setText(delta);

    }

    public void openProductPage(View view) {
        String url = getIntent().getStringExtra("productURL");
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        Log.wtf("openProductPage", "Opening product page");
        startActivity(intent);
    }

    public String toTitleCase(String s){
        StringBuilder word = new StringBuilder();
        for(String str : s.toLowerCase().split(" ")){
            word.append(str.substring(0,1).toUpperCase()).append(str.substring(1));
            word.append(" ");
        }
        return word.toString();
    }
}