package com.example.nytimes_best_seller.Category_API.Model;

import java.util.List;

public class CategoryResponse{
	private String copyright;
	private List<CategoryDetails> results;
	private int numResults;
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