package com.android.me.bandmasterdetail;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ItemDetailActivity extends AppCompatActivity {

    ItemDetailFragment fragmentItemDetail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        // Fetch the item to display from bundle
        Band item = (Band) getIntent().getSerializableExtra("item");
        if (savedInstanceState == null) {
            // Insert detail fragment based on the item passed
            fragmentItemDetail = ItemDetailFragment.newInstance(item); // <-------
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItemDetail);
            ft.commit();
        }
    }
}
