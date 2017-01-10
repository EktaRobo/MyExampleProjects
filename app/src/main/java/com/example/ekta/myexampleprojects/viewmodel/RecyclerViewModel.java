package com.example.ekta.myexampleprojects.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.example.ekta.myexampleprojects.BR;
import com.example.ekta.myexampleprojects.utils.DeviceUtils;

/**
 * Created by ekta on 9/1/17.
 */

public class RecyclerViewModel extends BaseObservable {
    private Bitmap picture;
    private boolean isLoading;

    public RecyclerViewModel(Bitmap picture) {
        this.picture = picture;
    }

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter("setImageBitmap")
    public static void setImageBitmap(ImageView imageView, RecyclerViewModel recyclerViewModel) {
        Context context = imageView.getContext();
        imageView.getLayoutParams().height = DeviceUtils.getDeviceHeight(context) / 4;
        imageView.getLayoutParams().width = DeviceUtils.getDeviceWidth(context) / 2;

        int ic_menu_report_image = android.R.drawable.ic_menu_report_image;
        Bitmap bitmap = recyclerViewModel.getPicture();
        if (bitmap != null && !bitmap.sameAs(BitmapFactory.decodeResource(context.getResources(),
                ic_menu_report_image))) {
            if (bitmap.getHeight() > bitmap.getWidth()) {
                imageView.getLayoutParams().height = DeviceUtils.getDeviceHeight(context) / 2;
                imageView.getLayoutParams().width = DeviceUtils.getDeviceWidth(context) / 2;

            } else {
                imageView.getLayoutParams().height = DeviceUtils.getDeviceHeight(context) / 4;
                imageView.getLayoutParams().width = DeviceUtils.getDeviceWidth(context) / 2;
            }
            imageView.setImageBitmap(bitmap);
            recyclerViewModel.setLoading(false);
        } else {
            imageView.setImageResource(ic_menu_report_image);
            recyclerViewModel.setLoading(true);

        }

    }

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR._all);
    }

    @Bindable
    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
        notifyPropertyChanged(BR._all);
    }
}
