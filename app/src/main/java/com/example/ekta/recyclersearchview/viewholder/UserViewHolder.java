package com.example.ekta.recyclersearchview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ekta.recyclersearchview.R;

/**
 * Created by ekta on 12/12/16.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView mUserId, mTitle, mBody;

    public UserViewHolder(View itemView) {
        super(itemView);
        mUserId = (TextView) itemView.findViewById(R.id.user_id);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mBody = (TextView) itemView.findViewById(R.id.body);
    }
}
