package com.example.nytimes_best_seller.Book_API.Model;

import com.google.gson.annotations.SerializedName;

public class BookDetailsItem{

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

	public void setContributorNote(String contributorNote){
		this.contributorNote = contributorNote;
	}

	public String getContributorNote(){
		return contributorNote;
	}

	public void setContributor(String contributor){
		this.contributor = contributor;
	}

	public String getContributor(){
		return contributor;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setAgeGroup(String ageGroup){
		this.ageGroup = ageGroup;
	}

	public String getAgeGroup(){
		return ageGroup;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setPublisher(String publisher){
		this.publisher = publisher;
	}

	public String getPublisher(){
		return publisher;
	}

	public void setPrimaryIsbn10(String primaryIsbn10){
		this.primaryIsbn10 = primaryIsbn10;
	}

	public String getPrimaryIsbn10(){
		return primaryIsbn10;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setPrimaryIsbn13(String primaryIsbn13){
		this.primaryIsbn13 = primaryIsbn13;
	}

	public String getPrimaryIsbn13(){
		return primaryIsbn13;
	}

	@Override
 	public String toString(){
		return 
			"BookDetailsItem{" + 
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