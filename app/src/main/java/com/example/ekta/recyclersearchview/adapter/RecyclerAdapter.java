package com.example.ekta.recyclersearchview.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ekta.recyclersearchview.R;
import com.example.ekta.recyclersearchview.viewholder.ImageViewHolder;

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
        Bitmap bitmap = mBitmaps.get(position);
        holder.mImage.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return mBitmaps.size();
    }
}
