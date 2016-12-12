package com.example.ekta.recyclersearchview.adapter;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.ekta.recyclersearchview.R;
import com.example.ekta.recyclersearchview.model.UserResponse;
import com.example.ekta.recyclersearchview.viewholder.UserViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<UserViewHolder>  implements Filterable{

    private final SortedList<UserResponse> mSortedList = new SortedList<>(UserResponse.class, new SortedList.Callback<UserResponse>() {
        @Override
        public int compare(UserResponse a, UserResponse b) {
            return mComparator.compare(a, b);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(UserResponse oldItem, UserResponse newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(UserResponse item1, UserResponse item2) {
            return item1.getId() == item2.getId();
        }
    });

    private Comparator<UserResponse> mComparator;
    private ArrayList<UserResponse> mUserResponses = new ArrayList<>();

    public RecyclerAdapter(Comparator<UserResponse> comparator) {
        mComparator = comparator;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new UserViewHolder(inflater.inflate(R.layout.user_items, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserResponse userResponse = mSortedList.get(position);
        holder.mUserId.setText(userResponse.getId().toString());
        holder.mTitle.setText(userResponse.getName());
        holder.mBody.setText(userResponse.getEmail());
    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    public void add(UserResponse model) {
        mSortedList.add(model);
    }

    public void remove(UserResponse model) {
        mSortedList.remove(model);
    }

    public void add(List<UserResponse> models) {
        mUserResponses.addAll(models);
        mSortedList.addAll(models);
    }

    public void remove(List<UserResponse> models) {
        mSortedList.beginBatchedUpdates();
        for (UserResponse model : models) {
            mSortedList.remove(model);
        }
        mSortedList.endBatchedUpdates();
    }

    public void replaceAll(List<UserResponse> models) {
        mSortedList.beginBatchedUpdates();
        for (int i = mSortedList.size() - 1; i >= 0; i--) {
            final UserResponse model = mSortedList.get(i);
            if (!models.contains(model)) {
                mSortedList.remove(model);
            }
        }
        mSortedList.addAll(models);
        mSortedList.endBatchedUpdates();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults result = new FilterResults();
                if (charSequence.length() > 0) {
                    final ArrayList<UserResponse> filteredModelList = new ArrayList<>();
                    for (UserResponse model : mUserResponses) {
                        final String text = model.getName().toLowerCase();
                        if (text.contains(charSequence)) {
                            filteredModelList.add(model);
                        }
                    }
                    result.values = filteredModelList;
                    result.count = filteredModelList.size();
                } else {
                    result.values = mUserResponses;
                    result.count = mUserResponses.size();
                }

                return result;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Object values = filterResults.values;
                ArrayList<UserResponse> userResponses = (ArrayList<UserResponse>) values;
                replaceAll(userResponses);
            }
        };
    }
}
