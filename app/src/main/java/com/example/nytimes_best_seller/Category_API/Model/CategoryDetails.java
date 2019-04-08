package com.example.nytimes_best_seller.Category_API.Model;

public class CategoryDetails {
	private String newestPublishedDate;
	private String oldestPublishedDate;
	private String listName;
	private String listNameEncoded;
	private String displayName;
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
			"BookResults{" +
			"newest_published_date = '" + newestPublishedDate + '\'' + 
			",oldest_published_date = '" + oldestPublishedDate + '\'' + 
			",list_name = '" + listName + '\'' + 
			",list_name_encoded = '" + listNameEncoded + '\'' + 
			",display_name = '" + displayName + '\'' + 
			",updated = '" + updated + '\'' + 
			"}";
		}
}
