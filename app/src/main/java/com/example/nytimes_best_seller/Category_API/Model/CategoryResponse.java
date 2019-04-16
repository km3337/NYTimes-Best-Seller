package com.example.nytimes_best_seller.Category_API.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse{

	@SerializedName("copyright")
	private String copyright;

	@SerializedName("results")
	private List<CategoryDetails> results;

	@SerializedName("num_results")
	private int numResults;

	@SerializedName("status")
	private String status;

	public String getCopyright(){
		return copyright;
	}

	public List<CategoryDetails> getResults(){
		return results;
	}

	public int getNumResults(){
		return numResults;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"CategoryResponse{" + 
			"copyright = '" + copyright + '\'' + 
			",results = '" + results + '\'' + 
			",num_results = '" + numResults + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}