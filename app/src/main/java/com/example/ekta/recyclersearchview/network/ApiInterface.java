package com.example.ekta.recyclersearchview.network;


import com.example.ekta.recyclersearchview.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users")
    Call<ArrayList<UserResponse>> getList();


}
