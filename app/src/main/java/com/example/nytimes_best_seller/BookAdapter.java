package com.example.nytimes_best_seller;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nytimes_best_seller.Book_API.Model.BookResults;
import com.example.nytimes_best_seller.Book_API.Model.BooksResponse;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.graphics.ColorUtils.setAlphaComponent;


public class BookAdapter extends ArrayAdapter {

    private Context mContext;
    private List<BookResults> mBookResults;

    public BookAdapter(@NonNull Context context, int resource, @NonNull List<BookResults> bookResults) {
        super(context, resource, bookResults);
        mContext = context;
        mBookResults = bookResults;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.book_item, parent, false);
        }
        BookResults bResults = mBookResults.get(position);
        int     rank = bResults.getRank(),
                rankLastWeek = bResults.getRankLastWeek(),
                weeksOnList = bResults.getWeeksOnList();

        String  noChange = mContext.getResources().getString(R.string.nochg),
                up = mContext.getResources().getString(R.string.up),
                down = mContext.getResources().getString(R.string.down);

        TextView rankTextView = listItem.findViewById(R.id.list_rank_num);
        rankTextView.setText(Integer.toString(rank));

        TextView rankChangeTextView = listItem.findViewById(R.id.list_rank_change);
        String rankSymbol;
        int color, alpha = 150;
        if(weeksOnList > 1) {
            if (rankLastWeek < rank) {
                rankSymbol = down;
                color = setAlphaComponent(Color.RED, alpha);
            }
            else if (rankLastWeek > rank) {
                rankSymbol = up;
                color = setAlphaComponent(Color.GREEN, alpha);
            }
            else {
                rankSymbol = noChange;
                color = setAlphaComponent(Color.BLUE, alpha);
            }
        }
        else {
            rankSymbol = noChange;
            color = setAlphaComponent(Color.BLUE, alpha);
        }
        rankChangeTextView.setText(rankSymbol);
        rankChangeTextView.setTextColor(color);

        TextView titleTextView = listItem.findViewById(R.id.list_book_title);

        String title = bResults.getBookDetails().get(0).getTitle();
        StringBuilder word = new StringBuilder();
        for(String str : title.toLowerCase().split(" ")){
            word.append(str.substring(0,1).toUpperCase()).append(str.substring(1));
            word.append(" ");
        }
        titleTextView.setText(word.toString());

        TextView authorTextView = listItem.findViewById(R.id.list_author);
        authorTextView.setText(bResults.getBookDetails().get(0).getAuthor());

        //TODO: find out how to get book image through
        ImageView bookCoverImageView = listItem.findViewById(R.id.list_book_image);

        return listItem;

    }



}
