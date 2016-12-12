package com.example.ekta.recyclersearchview;

import com.example.ekta.recyclersearchview.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ekta on 12/12/16.
 */
public interface OnListFetchListener {
    void onSuccess(Response<ArrayList<UserResponse>> response);

    void onFailure(Throwable t);
}
