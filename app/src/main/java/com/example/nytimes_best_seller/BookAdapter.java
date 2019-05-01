package com.example.nytimes_best_seller;

import android.content.Context;
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
        int rank = bResults.getRank(), rankLastWeek = bResults.getRankLastWeek();

        TextView rankTextView = listItem.findViewById(R.id.list_rank_num);
        rankTextView.setText(Integer.toString(rank));

        TextView rankChangeTextView = listItem.findViewById(R.id.list_rank_change);
        rankChangeTextView.setText((rankLastWeek<rank ? "-" : rankLastWeek>rank ? "+" : "~"));

        TextView titleTextView = listItem.findViewById(R.id.list_book_title);
        titleTextView.setText(bResults.getBookDetails().get(0).getTitle());

        //TODO: find out how to get book image through
        ImageView bookCoverImageView = listItem.findViewById(R.id.list_book_image);

        return listItem;

    }


    public void updateBookList(final BooksResponse serverResponse, List<BookResults> newList){
        mBookResults.clear();
        mBookResults = serverResponse.getResults();
        mBookResults.addAll(newList);
        this.notifyDataSetChanged();
    }


}
