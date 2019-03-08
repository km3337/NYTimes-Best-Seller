package com.example.nytimes_best_seller.API.Model;

import com.google.gson.annotations.SerializedName;

public class IsbnsItem{

	@SerializedName("isbn13")
	private String isbn13;

	@SerializedName("isbn10")
	private String isbn10;

	public void setIsbn13(String isbn13){
		this.isbn13 = isbn13;
	}

	public String getIsbn13(){
		return isbn13;
	}

	public void setIsbn10(String isbn10){
		this.isbn10 = isbn10;
	}

	public String getIsbn10(){
		return isbn10;
	}

	@Override
 	public String toString(){
		return 
			"IsbnsItem{" + 
			"isbn13 = '" + isbn13 + '\'' + 
			",isbn10 = '" + isbn10 + '\'' + 
			"}";
		}
}