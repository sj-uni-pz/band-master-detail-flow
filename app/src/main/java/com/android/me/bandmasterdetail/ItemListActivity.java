package com.android.me.bandmasterdetail;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class ItemListActivity extends AppCompatActivity
        implements ItemsListFragment.OnListItemSelectedListener {

    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        // Call this to determine which layout we are in (tablet or phone)
        determinePaneLayout();
    }

    private void determinePaneLayout() {
        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
        // If there is a second pane for details
        if (fragmentItemDetail != null) {
            isTwoPane = true;
            ItemsListFragment fragmentItemsList =
                    (ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentItemsList);
            fragmentItemsList.setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(Band item) {
        if (isTwoPane) { // single activity with list and detail
            // Replace framelayout with new detail fragment
            ItemDetailFragment fragmentItem = ItemDetailFragment.newInstance(item);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItem);
            ft.commit();
        } else { // go to separate activity
            // launch detail activity using intent
            Intent i = new Intent(this, ItemDetailActivity.class);
            i.putExtra("item", item);
            startActivity(i);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // you have to populate the empty given menu
        // what resource into what object
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // returns whether we handled the item click or not
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bandListView:
                return true;
            case R.id.addBand:
                startActivity(new Intent(this,MainActivity.class));
                return true;
            default:
                return false;
        }

    }
}
