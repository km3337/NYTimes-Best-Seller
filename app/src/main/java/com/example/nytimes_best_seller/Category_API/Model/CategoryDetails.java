package com.example.nytimes_best_seller.Category_API.Model;

import com.google.gson.annotations.SerializedName;

public class CategoryDetails{

	@SerializedName("newest_published_date")
	private String newestPublishedDate;

	@SerializedName("oldest_published_date")
	private String oldestPublishedDate;

	@SerializedName("list_name")
	private String listName;

	@SerializedName("list_name_encoded")
	private String listNameEncoded;

	@SerializedName("display_name")
	private String displayName;

	@SerializedName("updated")
	private String updated;

	public String getNewestPublishedDate(){
		return newestPublishedDate;
	}

	public String getOldestPublishedDate(){
		return oldestPublishedDate;
	}

	public String getListName(){
		return listName;
	}

	public String getListNameEncoded(){
		return listNameEncoded;
	}

	public String getDisplayName(){
		return displayName;
	}

	public String getUpdated(){
		return updated;
	}

	@Override
 	public String toString(){
		return 
			"CategoryDetails{" +
			"newest_published_date = '" + newestPublishedDate + '\'' + 
			",oldest_published_date = '" + oldestPublishedDate + '\'' + 
			",list_name = '" + listName + '\'' + 
			",list_name_encoded = '" + listNameEncoded + '\'' + 
			",display_name = '" + displayName + '\'' + 
			",updated = '" + updated + '\'' + 
			"}";
		}
}