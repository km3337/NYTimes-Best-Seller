package com.example.nytimes_best_seller.Category_API.Service;

import com.example.nytimes_best_seller.Category_API.Model.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/* This interface is used when making the API call.
   I specify all the required information in here before
   making the call.
 */

public interface BookCategoriesAPI {

    @GET("lists/names.json?api-key=L9HsljIGNjG3w8GyW6DATDQklRr5TWXi")
    @Headers("Accept: application/json")
    Call<CategoryResponse> getCategoryInfo();

}
