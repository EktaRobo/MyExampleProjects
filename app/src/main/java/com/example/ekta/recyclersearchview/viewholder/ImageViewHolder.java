package com.example.ekta.recyclersearchview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ekta.recyclersearchview.R;

/**
 * Created by ekta on 12/12/16.
 */

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView mImage;
    public ProgressBar mProgressBar;

    public ImageViewHolder(View itemView) {
        super(itemView);
        mImage = (ImageView) itemView.findViewById(R.id.image_view);
        mProgressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
    }
}
