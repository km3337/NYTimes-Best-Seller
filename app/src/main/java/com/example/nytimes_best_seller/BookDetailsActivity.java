package com.example.nytimes_best_seller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static android.support.v4.graphics.ColorUtils.setAlphaComponent;


public class BookDetailsActivity extends AppCompatActivity {
    JsonParser jsonparser = new JsonParser();
    TextView tv;
    String ab = "";
    JSONObject jobj = null;
    JSONArray jobjArr = null;
    String link = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(toTitleCase(getIntent().getStringExtra("title")));
        setContentView(R.layout.activity_book_details);

        final ImageButton buyButton = findViewById(R.id.buy_button);
        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openProductPage(v);
            }
        });

        //linking objects and values from the API calls to the layout views created
        // in the XML files so that they show on the screen
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
        //minor logic for number of weeks on the list
        if(weeks.equals("0")) weeks = "new this week";
        else if (weeks.equals("1")) weeks+= " week on list";
        else weeks += " weeks on list";
        weeksTextView.setText(weeks);

        String description = getIntent().getStringExtra("description");
        descTextView.setText(description);

        String delta = getRankDelta(rank);
        rankDeltaTextView.setText(delta);
        rankDeltaTextView.setTextColor(getColor(rank, getIntent().getIntExtra("weeksOnList", 0)));



        // inner class for Google Books API calls
        // creates the necessary json object from the isbn retrieved from the NYT API
        // and downloads the image for the book cover from Google Books

        class retrievedata extends AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... arg0) {
                // TODO Auto-generated method stub
                ab = makeServiceCall("https://www.googleapis.com/books/v1/volumes?q=isbn:" + getIntent().getStringExtra("isbn"));
                Log.wtf("json",ab);
                if (ab != null) {
                    try {
                        jobj = new JSONObject(ab);
                        jobjArr = jobj.getJSONArray("items");
                        jobj = jobjArr.getJSONObject(0).getJSONObject("volumeInfo");
                        jobj = jobj.getJSONObject("imageLinks");
                        link = jobj.getString("smallThumbnail");
                        Log.wtf("abc",link);
                        return link;
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                }
                return "No String ";
            }
            protected void onPostExecute(String ab){

                new DownloadImageTask((ImageView) findViewById(R.id.book_cover))
                        .execute(link);
            }

        }
        new retrievedata().execute();

    }

    //takes the url from api call, turns it into a string and is used to open amazon product page for each book
    public void openProductPage(View view) {
        String url = getIntent().getStringExtra("productURL");
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        Log.wtf("openProductPage", "Opening product page");
        startActivity(intent);
    }

    //logic for looking at the change in rank compared to previous week
    public String getRankDelta(int rank) {
        int D1 = getIntent().getIntExtra("ranklastweek", 0);
        int D2 = Math.abs((D1 - rank));
        String delta = Integer.toString(D2), dSymbol, output = "";
        if (getIntent().getIntExtra("weeksOnList", 0) > 1) {
            if (rank < D1) {
                dSymbol = getString(R.string.up);
            }
            else if (rank > D1) {
                dSymbol = getString(R.string.down);
            } else {
                dSymbol = getString(R.string.nochg);
                return dSymbol + " no change since last week";
            }
            output = dSymbol + " by " + delta + " since last week";
            return output;
        } else return getString(R.string.nochg) + " first week on list";
    }

    //logic for adding color to the text for the change in rank
    public int getColor(int rank, int weeksOnList){
        int color, alpha = 150;
        int rankLastWeek = getIntent().getIntExtra("ranklastweek", 0);
        if(weeksOnList > 1) {
            if (rankLastWeek < rank) {
                color = setAlphaComponent(Color.RED, alpha);
            }
            else if (rankLastWeek > rank) {
                color = setAlphaComponent(Color.GREEN, alpha);
            }
            else {
                color = setAlphaComponent(Color.BLUE, alpha);
            }
        }
        else {
            color = setAlphaComponent(Color.BLUE, alpha);
        }
        return color;
    }

    //changing book titles, which were originally ALL CAPS to Title Case for better readability
    public String toTitleCase(String s){
        StringBuilder word = new StringBuilder();
        for(String str : s.toLowerCase().split(" ")){
            word.append(str.substring(0,1).toUpperCase()).append(str.substring(1));
            word.append(" ");
        }
        return word.toString();
    }

    //makes api call for the book image to show on the details activity
    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e("bookimage", "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e("bookimage", "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("bookimage", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("bookimage", "Exception: " + e.getMessage());
        }
        return response;
    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    // downloading book image in the background to keep the application responsive and
    // efficiently communicate between main thread and background thread
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {

                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.wtf("Url", urldisplay.toString());
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
