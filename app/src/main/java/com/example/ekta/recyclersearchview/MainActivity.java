package com.example.ekta.recyclersearchview;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.ProgressBar;

import com.example.ekta.recyclersearchview.adapter.RecyclerAdapter;
import com.example.ekta.recyclersearchview.model.UserResponse;
import com.example.ekta.recyclersearchview.network.NetworkAdapter;

import java.util.ArrayList;
import java.util.Comparator;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, OnListFetchListener{
    private ProgressBar mProgressBar;
    private ArrayList<UserResponse> mUserResponses;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;


    private static final Comparator<UserResponse> ALPHABETICAL_COMPARATOR = new Comparator<UserResponse>() {
        @Override
        public int compare(UserResponse a, UserResponse b) {
            return a.getId().compareTo(b.getId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerAdapter(ALPHABETICAL_COMPARATOR);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        NetworkAdapter.getInstance().getUserData(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mAdapter.getFilter().filter(query.toLowerCase(), new Filter.FilterListener() {
            @Override
            public void onFilterComplete(int i) {
                mRecyclerView.scrollToPosition(0);

            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void onSuccess(Response<ArrayList<UserResponse>> response) {
        mProgressBar.setVisibility(View.GONE);
        mUserResponses = response.body();
        mAdapter.add(mUserResponses);
    }

    @Override
    public void onFailure(Throwable t) {
        mProgressBar.setVisibility(View.GONE);

    }
}
