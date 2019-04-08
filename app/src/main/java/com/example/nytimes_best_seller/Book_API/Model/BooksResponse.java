package com.example.nytimes_best_seller.Book_API.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BooksResponse {

	@SerializedName("copyright")
	private String copyright;

	@SerializedName("last_modified")
	private String lastModified;

	@SerializedName("results")
	private List<BookResults> results;

	@SerializedName("num_results")
	private int numResults;

	@SerializedName("status")
	private String status;

	public String getCopyright(){
		return copyright;
	}

	public String getLastModified(){
		return lastModified;
	}

	public List<BookResults> getResults(){
		return results;
	}

	public int getNumResults(){
		return numResults;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"BooksResponse{" +
			"copyright = '" + copyright + '\'' + 
			",last_modified = '" + lastModified + '\'' + 
			",results = '" + results + '\'' + 
			",num_results = '" + numResults + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}