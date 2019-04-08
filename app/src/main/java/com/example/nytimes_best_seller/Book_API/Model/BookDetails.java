package com.example.nytimes_best_seller.Book_API.Model;

import com.google.gson.annotations.SerializedName;

public class BookDetails {

	@SerializedName("contributor_note")
	private String contributorNote;

	@SerializedName("contributor")
	private String contributor;

	@SerializedName("author")
	private String author;

	@SerializedName("price")
	private int price;

	@SerializedName("age_group")
	private String ageGroup;

	@SerializedName("description")
	private String description;

	@SerializedName("publisher")
	private String publisher;

	@SerializedName("primary_isbn10")
	private String primaryIsbn10;

	@SerializedName("title")
	private String title;

	@SerializedName("primary_isbn13")
	private String primaryIsbn13;

	public String getContributorNote(){ return contributorNote; }

	public String getContributor(){
		return contributor;
	}

	public String getAuthor(){
		return author;
	}

	public int getPrice(){
		return price;
	}

	public String getAgeGroup(){
		return ageGroup;
	}

	public String getDescription(){
		return description;
	}

	public String getPublisher(){
		return publisher;
	}

	public String getPrimaryIsbn10(){
		return primaryIsbn10;
	}

	public String getTitle(){ return title; }

	public String getPrimaryIsbn13(){
		return primaryIsbn13;
	}

	@Override
 	public String toString(){
		return 
			"BookDetails{" +
			"contributor_note = '" + contributorNote + '\'' + 
			",contributor = '" + contributor + '\'' + 
			",author = '" + author + '\'' + 
			",price = '" + price + '\'' + 
			",age_group = '" + ageGroup + '\'' + 
			",description = '" + description + '\'' + 
			",publisher = '" + publisher + '\'' + 
			",primary_isbn10 = '" + primaryIsbn10 + '\'' + 
			",title = '" + title + '\'' + 
			",primary_isbn13 = '" + primaryIsbn13 + '\'' + 
			"}";
		}
}