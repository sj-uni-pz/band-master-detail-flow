package com.android.me.bandmasterdetail;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

/**
 * Created by ME on 1/5/2017.
 */

public class ItemsListFragment extends Fragment {
    private static final String TAG = "ItemsListFragment";
    static final String[] FROM = {Items.C_NAME};
    static final int[] TO = {android.R.id.text1};
    private SimpleCursorAdapter adapterItems;
    private ListView lvItems;
    Cursor items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create arraylist from item fixtures
         items = Items.getInstance(getActivity()).getBands();
        print(items);
        // Create adapter based on items
        adapterItems = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_activated_1,items,
                FROM,TO,SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    private void print(Cursor cursor){
        Log.d(TAG,"Cursor items: "+cursor.getCount());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items_list, container,
                false);
        // Bind adapter to ListView
        lvItems = (ListView) view.findViewById(R.id.lvItems);
        lvItems.setAdapter(adapterItems);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long rowId) {
                // Retrieve item based on position
                Band band = Items.getInstance(getActivity()).getBand(rowId);
                 Log.d(TAG,"-------------- "+ rowId);
                // Fire selected listener event with item
                listener.onItemSelected(band); // <--------------
            }
        });
        return view;
    }

    private OnListItemSelectedListener listener;

    public interface OnListItemSelectedListener {
        public void onItemSelected(Band item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListItemSelectedListener) {
            listener = (OnListItemSelectedListener) context;
        } else {
            throw new ClassCastException(
                    context.toString()
                            + " must implement ItemsListFragment.OnListItemSelectedListener");
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        lvItems.setChoiceMode(
                activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
                        : ListView.CHOICE_MODE_NONE);
    }

}
