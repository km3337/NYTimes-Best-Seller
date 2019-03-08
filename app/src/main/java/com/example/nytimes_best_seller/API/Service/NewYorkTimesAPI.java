package com.example.nytimes_best_seller.API.Service;

import com.example.nytimes_best_seller.API.Model.ServerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NewYorkTimesAPI {

    @GET("lists.json?list=hardcover-fiction&api-key=L9HsljIGNjG3w8GyW6DATDQklRr5TWXi")
    @Headers("Accept: application/json")
    Call<ServerResponse> getServerInfo();

}
