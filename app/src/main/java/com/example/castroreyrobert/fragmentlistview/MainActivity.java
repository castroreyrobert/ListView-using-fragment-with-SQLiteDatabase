package com.example.castroreyrobert.fragmentlistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //STRING KEYS FOR PASSING THE DATA OF THE NOTES
    public static final String NOTE_ID = "com.example.castroreyrobert.fragmentlistview.ID";
    public static final String NOTE_TITLE = "com.example.castroreyrobert.fragmentlistview.TITLE";
    public static final String NOTE_NOTE = "com.example.castroreyrobert.fragmentlistview.NOTE";
    public static final String NOTE_CATEGORY = "com.example.castroreyrobert.fragmentlistview.CATEGORY";

    //KEY FOR GETTING THE PASSING THE FRAGMENT PROPERLY
    public static final String FRAGMENT_TO_LAUNCH = "com.example.castroreyrobert.fragmentlistview.FRAGMENT_TO_LAUNCH";
    //KEYS FOR LOADING THE FRAGMENT PROPERLY
    public enum FRAGMENT_TO_LOAD {VIEW, EDIT, ADD}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //If the user clicks the menu
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_add){
            Intent intent = new Intent(this, NoteDetailActivity.class);
            intent.putExtra(FRAGMENT_TO_LAUNCH, FRAGMENT_TO_LOAD.ADD);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
