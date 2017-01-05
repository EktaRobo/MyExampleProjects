package com.example.ekta.myexampleprojects.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ekta.myexampleprojects.R;
import com.example.ekta.myexampleprojects.constant.Constants;
import com.example.ekta.myexampleprojects.utils.DeviceUtils;
import com.example.ekta.myexampleprojects.viewholder.ImageViewHolder;

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
        ImageView imageView = holder.mImage;
        ProgressBar progressBar = holder.mProgressBar;
        Context context = imageView.getContext();
        imageView.getLayoutParams().height = DeviceUtils.getDeviceHeight(context) / 4;
        imageView.getLayoutParams().width = DeviceUtils.getDeviceWidth(context) / 2;

        if (bitmap != null && !bitmap.sameAs(BitmapFactory.decodeResource(context.getResources(),
                android.R.drawable.ic_menu_report_image))) {
            if (bitmap.getHeight() > bitmap.getWidth()) {
                imageView.getLayoutParams().height = DeviceUtils.getDeviceHeight(context) / 2;
                imageView.getLayoutParams().width = DeviceUtils.getDeviceWidth(context) / 2;

            } else {
                imageView.getLayoutParams().height = DeviceUtils.getDeviceHeight(context) / 4;
                imageView.getLayoutParams().width = DeviceUtils.getDeviceWidth(context) / 2;

            }
            imageView.setImageBitmap(bitmap);
            progressBar.setVisibility(View.GONE);
        } else {
            imageView.setImageResource(android.R.drawable.ic_menu_report_image);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return Constants.END_POINTS.size();
    }
}
