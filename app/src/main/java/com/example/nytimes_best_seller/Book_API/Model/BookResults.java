package com.example.nytimes_best_seller.Book_API.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BookResults {

	@SerializedName("dagger")
	private int dagger;

	@SerializedName("asterisk")
	private int asterisk;

	@SerializedName("book_details")
	private List<BookDetails> bookDetails;

	@SerializedName("list_name")
	private String listName;

	@SerializedName("display_name")
	private String displayName;

	@SerializedName("weeks_on_list")
	private int weeksOnList;

	@SerializedName("bestsellers_date")
	private String bestsellersDate;

	@SerializedName("amazon_product_url")
	private String amazonProductUrl;

	@SerializedName("rank")
	private int rank;

	@SerializedName("published_date")
	private String publishedDate;

	@SerializedName("rank_last_week")
	private int rankLastWeek;

	public int getDagger(){
		return dagger;
	}

	public int getAsterisk(){
		return asterisk;
	}

	public List<BookDetails> getBookDetails(){
		return bookDetails;
	}

	public String getListName(){
		return listName;
	}

	public String getDisplayName(){
		return displayName;
	}

	public int getWeeksOnList(){
		return weeksOnList;
	}

	public String getBestsellersDate(){
		return bestsellersDate;
	}

	public String getAmazonProductUrl(){
		return amazonProductUrl;
	}

	public int getRank(){
		return rank;
	}

	public String getPublishedDate(){
		return publishedDate;
	}

	public int getRankLastWeek(){
		return rankLastWeek;
	}

	@Override
 	public String toString(){
		return 
			"BookResults{" +
			",dagger = '" + dagger + '\'' + 
			",asterisk = '" + asterisk + '\'' + 
			",book_details = '" + bookDetails + '\'' + 
			",list_name = '" + listName + '\'' + 
			",display_name = '" + displayName + '\'' + 
			",weeks_on_list = '" + weeksOnList + '\'' + 
			",bestsellers_date = '" + bestsellersDate + '\'' + 
			",amazon_product_url = '" + amazonProductUrl + '\'' +
			",rank = '" + rank + '\'' + 
			",published_date = '" + publishedDate + '\'' + 
			",rank_last_week = '" + rankLastWeek + '\'' + 
			"}";
		}
}