package com.example.ekta.myexampleprojects.viewholder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ekta.myexampleprojects.databinding.ImageBinding;
import com.example.ekta.myexampleprojects.viewmodel.RecyclerViewModel;

/**
 * Created by ekta on 12/12/16.
 */

public class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageBinding mBinding;

    public ImageViewHolder(View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    public void bind(RecyclerViewModel recyclerViewModel) {
        mBinding.setRecyclerViewModel(recyclerViewModel);
    }

    public ImageBinding getBinding() {
        return mBinding;
    }
}
