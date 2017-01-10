package com.example.ekta.myexampleprojects.gallery;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ekta.myexampleprojects.R;
import com.example.ekta.myexampleprojects.adapter.RecyclerAdapter;
import com.example.ekta.myexampleprojects.constant.Constants;
import com.example.ekta.myexampleprojects.manager.NotificationListenerManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements NotificationListenerManager
        .Observer {
    private View mRootView;
    private RecyclerAdapter mAdapter;
    private ArrayList<Bitmap> mBitmaps = new ArrayList<>();


    public GalleryFragment() {
        // Required empty public constructor
    }

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.gallery_fragment, container, false);
        NotificationListenerManager.getInstance().addObserver(Constants.NotificationEnum
                .NOTIFY_BITMAP_DOWNLOAD, this);
        init();
        return mRootView;
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        /*for (int index = 0; index < Constants.END_POINTS.size(); index++) {
            mBitmaps.add(BitmapFactory.decodeResource(getResources(), android.R.drawable
                    .ic_menu_report_image));
        }*/
        mAdapter = new RecyclerAdapter(mBitmaps);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        // Create an `ItemTouchHelper` and attach it to the `RecyclerView`

        // Extend the Callback class
        ItemTouchHelper.Callback _ithCallback = getCallback();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(_ithCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
//        mPresenter.start(mContext);

    }

    @NonNull
    private ItemTouchHelper.Callback getCallback() {
        return new ItemTouchHelper.Callback() {
            //and in your imlpementaion of
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                // get the viewHolder's and target's positions in your adapter data, swap them
                Collections.swap(/*RecyclerView.Adapter's data collection*/ mBitmaps, viewHolder
                        .getAdapterPosition(), target.getAdapterPosition());
                // and notify the adapter that its dataset has changed
                mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target
                        .getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //TODO : Learn what it's used for
            }

            //defines the enabled move directions in each state (idle, swiping, dragging).
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder
                    viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START |
                                ItemTouchHelper.END);
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NotificationListenerManager.getInstance().removeObserver(Constants.NotificationEnum
                .NOTIFY_BITMAP_DOWNLOAD, this);
    }

    @Override
    public void update(Constants.NotificationEnum notificationName, Bundle data) {
        if (notificationName == Constants.NotificationEnum.NOTIFY_BITMAP_DOWNLOAD) {
            if (data != null) {
                Bitmap bitmap = data.getParcelable(Constants.BITMAP);
                int index = data.getInt(Constants.INDEX);
                mBitmaps.add(index, bitmap);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
