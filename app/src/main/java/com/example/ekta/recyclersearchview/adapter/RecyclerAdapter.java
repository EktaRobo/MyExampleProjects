package com.example.ekta.recyclersearchview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ekta.recyclersearchview.Constants;
import com.example.ekta.recyclersearchview.R;
import com.example.ekta.recyclersearchview.utils.DeviceUtils;
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
        Bitmap bitmap = null;
        if (mBitmaps.size() > position) {
            bitmap = mBitmaps.get(position);
        }
        ImageView image = holder.mImage;
        ProgressBar progressBar = holder.mProgressBar;
        Context context = image.getContext();
        image.getLayoutParams().height = DeviceUtils.getDeviceHeight(context) / 3;
        image.getLayoutParams().width = DeviceUtils.getDeviceWidth(context) / 3;
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
            progressBar.setVisibility(View.GONE);
        } else {
            image.setImageResource(android.R.drawable.ic_menu_report_image);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return Constants.END_POINTS.size();
    }
}
