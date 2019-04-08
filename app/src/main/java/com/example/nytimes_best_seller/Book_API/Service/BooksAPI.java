package com.example.nytimes_best_seller.Book_API.Service;

import com.example.nytimes_best_seller.Book_API.Model.BooksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/* This interface is used when making the API call.
   I specify all the required information in here before
   making the call.
 */

public interface BooksAPI {

    @GET("lists.json?list=hardcover-fiction&api-key=L9HsljIGNjG3w8GyW6DATDQklRr5TWXi")
    @Headers("Accept: application/json")
    Call<BooksResponse> getServerInfo();

}
