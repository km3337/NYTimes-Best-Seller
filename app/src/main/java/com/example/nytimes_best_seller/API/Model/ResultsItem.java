package com.example.nytimes_best_seller.API.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("isbns")
	private List<IsbnsItem> isbns;

	@SerializedName("dagger")
	private int dagger;

	@SerializedName("asterisk")
	private int asterisk;

	@SerializedName("book_details")
	private List<BookDetailsItem> bookDetails;

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

	@SerializedName("reviews")
	private List<ReviewsItem> reviews;

	@SerializedName("rank")
	private int rank;

	@SerializedName("published_date")
	private String publishedDate;

	@SerializedName("rank_last_week")
	private int rankLastWeek;

	public void setIsbns(List<IsbnsItem> isbns){
		this.isbns = isbns;
	}

	public List<IsbnsItem> getIsbns(){
		return isbns;
	}

	public void setDagger(int dagger){
		this.dagger = dagger;
	}

	public int getDagger(){
		return dagger;
	}

	public void setAsterisk(int asterisk){
		this.asterisk = asterisk;
	}

	public int getAsterisk(){
		return asterisk;
	}

	public void setBookDetails(List<BookDetailsItem> bookDetails){
		this.bookDetails = bookDetails;
	}

	public List<BookDetailsItem> getBookDetails(){
		return bookDetails;
	}

	public void setListName(String listName){
		this.listName = listName;
	}

	public String getListName(){
		return listName;
	}

	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	public String getDisplayName(){
		return displayName;
	}

	public void setWeeksOnList(int weeksOnList){
		this.weeksOnList = weeksOnList;
	}

	public int getWeeksOnList(){
		return weeksOnList;
	}

	public void setBestsellersDate(String bestsellersDate){
		this.bestsellersDate = bestsellersDate;
	}

	public String getBestsellersDate(){
		return bestsellersDate;
	}

	public void setAmazonProductUrl(String amazonProductUrl){
		this.amazonProductUrl = amazonProductUrl;
	}

	public String getAmazonProductUrl(){
		return amazonProductUrl;
	}

	public void setReviews(List<ReviewsItem> reviews){
		this.reviews = reviews;
	}

	public List<ReviewsItem> getReviews(){
		return reviews;
	}

	public void setRank(int rank){
		this.rank = rank;
	}

	public int getRank(){
		return rank;
	}

	public void setPublishedDate(String publishedDate){
		this.publishedDate = publishedDate;
	}

	public String getPublishedDate(){
		return publishedDate;
	}

	public void setRankLastWeek(int rankLastWeek){
		this.rankLastWeek = rankLastWeek;
	}

	public int getRankLastWeek(){
		return rankLastWeek;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"isbns = '" + isbns + '\'' + 
			",dagger = '" + dagger + '\'' + 
			",asterisk = '" + asterisk + '\'' + 
			",book_details = '" + bookDetails + '\'' + 
			",list_name = '" + listName + '\'' + 
			",display_name = '" + displayName + '\'' + 
			",weeks_on_list = '" + weeksOnList + '\'' + 
			",bestsellers_date = '" + bestsellersDate + '\'' + 
			",amazon_product_url = '" + amazonProductUrl + '\'' + 
			",reviews = '" + reviews + '\'' + 
			",rank = '" + rank + '\'' + 
			",published_date = '" + publishedDate + '\'' + 
			",rank_last_week = '" + rankLastWeek + '\'' + 
			"}";
		}
}