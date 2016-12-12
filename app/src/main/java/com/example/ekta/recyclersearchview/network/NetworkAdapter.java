package com.example.ekta.recyclersearchview.network;

import android.util.Log;

import com.example.ekta.recyclersearchview.OnListFetchListener;
import com.example.ekta.recyclersearchview.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkAdapter {

    private static final String TAG = NetworkAdapter.class.getSimpleName();
    private static NetworkAdapter sInstance = null;
    private ApiInterface mApiService = ApiClient.getClient().create(ApiInterface.class);

    private NetworkAdapter() {
        // Exists only to defeat instantiation.
    }

    public static NetworkAdapter getInstance() {
        if (sInstance == null) {
            sInstance = new NetworkAdapter();
        }
        return sInstance;
    }

    public void getUserData(final OnListFetchListener listener) {
        Call<ArrayList<UserResponse>> call = mApiService.getList();
        call.enqueue(new Callback<ArrayList<UserResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<UserResponse>> call, Response<ArrayList<UserResponse>> response) {
                ArrayList<UserResponse> body = response.body();
                if (body != null) {

                    Log.d(TAG, body.toString());
                } else {
                    Log.d(TAG, response.toString());
                }
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ArrayList<UserResponse>> call, Throwable t) {
                Log.e(TAG, t.toString());
                listener.onFailure(t);
            }
        });
    }
}

