package com.example.castroreyrobert.fragmentlistview;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        createAddFragment();
    }

    private void createAddFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        NoteViewFragment noteViewFragment = new NoteViewFragment();
        fragmentTransaction.add(R.id.activity_note_detail_linearlayout, noteViewFragment, "NOTE_VIEW_FRAGMENT");

        //setting the toolbar title
        setTitle(R.string.toolbar_title);
        fragmentTransaction.commit();
    }
}
