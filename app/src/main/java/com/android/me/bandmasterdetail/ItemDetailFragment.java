package com.android.me.bandmasterdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ME on 1/5/2017.
 */

public class ItemDetailFragment extends Fragment  {

    static final String TAG = "ItemDetailFragment";
    private Band item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = (Band) getArguments().getSerializable("item");
    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
            // Inflate view
            View view = inflater.inflate(R.layout.fragment_item_detail,
                container, false);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            TextView tvTitle2 = (TextView) view.findViewById(R.id.tvTitle2);
            TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
            if (item != null) {
                tvTitle.setText(item.getName());
                tvTitle2.setText(item.getGenre());
                tvBody.setText(item.getDescription());
                Log.d(TAG, "onCreateView Band id =" + item.getId());
            }
            // Return view
            return view;
        }

        // ItemDetailFragment.newInstance(item)
        public static ItemDetailFragment newInstance(Band item) {
            ItemDetailFragment fragmentDemo = new ItemDetailFragment();
            Bundle args = new Bundle();
            args.putSerializable("item", item);
            fragmentDemo.setArguments(args);
            return fragmentDemo;
        }
}
