package com.example.castroreyrobert.fragmentlistview;

import android.content.Intent;
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

    //Adding the fragment dynamically
    private void createAddFragment(){

        //Grabbing intent and fragment to launch from the MainActivityFragment
        Intent intent = getIntent();
        MainActivity.FRAGMENT_TO_LOAD ftl = (MainActivity.FRAGMENT_TO_LOAD) intent.getSerializableExtra
                (MainActivity.FRAGMENT_TO_LAUNCH);

        //For Fragment manager : DEFAULT
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (ftl){
            case EDIT:
                //Setting the name of the layout of the fragment
                NoteEditFragment noteEditFragment = new NoteEditFragment();
                fragmentTransaction.add(R.id.activity_note_detail_linearlayout, noteEditFragment,
                        "NOTE_EDIT_FRAGMENT");
                setTitle(R.string.toolbar_title_edit);

                break;
            case VIEW:
                //Setting the name of the layout of the fragment
                NoteViewFragment noteViewFragment = new NoteViewFragment();
                fragmentTransaction.add(R.id.activity_note_detail_linearlayout, noteViewFragment,
                        "NOTE_VIEW_FRAGMENT");

                //setting the toolbar title
                setTitle(R.string.toolbar_title_view);
                break;
        }
        fragmentTransaction.commit();


    }
}
