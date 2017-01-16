package com.android.me.bandmasterdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    Spinner genreSpinner;
    EditText descEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        descEditText = (EditText)findViewById(R.id.descEditText);
        genreSpinner = (Spinner)findViewById(R.id.genreSpinner);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String desc = descEditText.getText().toString();
                String genre = genreSpinner.getSelectedItem().toString();
                save(name,genre,desc);
                startActivity(new Intent(MainActivity.this,ItemListActivity.class));
            }
        });
    }

    private void save(String name, String genre, String desc) {
        Band band = new Band(name,genre,desc);
        Items.getInstance(this).add(band);
    }

    // caled the first time when user clicks on menu
    @Override
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
                startActivity(new Intent(MainActivity.this,ItemListActivity.class));
                return true;
            case R.id.addBand:
                return true;
            default:
                return false;
        }

    }


}
