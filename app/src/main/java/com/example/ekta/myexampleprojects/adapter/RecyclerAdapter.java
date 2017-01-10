package com.example.ekta.myexampleprojects.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ekta.myexampleprojects.R;
import com.example.ekta.myexampleprojects.constant.Constants;
import com.example.ekta.myexampleprojects.viewholder.ImageViewHolder;
import com.example.ekta.myexampleprojects.viewmodel.RecyclerViewModel;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private ArrayList<Bitmap> mBitmaps;

    public RecyclerAdapter(ArrayList<Bitmap> bitmaps) {
        mBitmaps = bitmaps;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ImageViewHolder(inflater.inflate(R.layout.image, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Bitmap picture = null;
        if (mBitmaps.size() > position) {
            picture = mBitmaps.get(position);
        }

        holder.bind(new RecyclerViewModel(picture));
    }

    @Override
    public int getItemCount() {
        return Constants.END_POINTS.size();
    }
}
