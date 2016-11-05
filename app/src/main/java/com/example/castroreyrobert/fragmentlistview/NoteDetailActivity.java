package com.example.castroreyrobert.fragmentlistview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class NoteDetailActivity extends AppCompatActivity {

    //Key for the Bundle inorder to know if where adding a new note
    public static final String ADD_NOTE = "Add Note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        createAddFragment();
        loadPreferences();
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
                fragmentTransaction.add(R.id.noteDetailLayout, noteEditFragment,
                        "NOTE_EDIT_FRAGMENT");
                setTitle(R.string.toolbar_title_edit);

                break;
            case VIEW:
                //Setting the name of the layout of the fragment
                NoteViewFragment noteViewFragment = new NoteViewFragment();
                fragmentTransaction.add(R.id.noteDetailLayout, noteViewFragment,
                        "NOTE_VIEW_FRAGMENT");

                //setting the toolbar title
                setTitle(R.string.toolbar_title_view);
                break;

            case ADD:
                NoteEditFragment noteAddEditFragment = new NoteEditFragment();
                fragmentTransaction.add(R.id.noteDetailLayout, noteAddEditFragment,
                        "NOTE_ADD_FRAGMENT");

                //Passing the value to the NoteEdit Fragment inorder to know if we need to add a new note
                Bundle bundle = new Bundle();
                bundle.putBoolean(ADD_NOTE, true);
                noteAddEditFragment.setArguments(bundle);

                //setting the toolbar title
                setTitle(R.string.toolbar_title_add);
                break;
        }
        fragmentTransaction.commit();


    }

    //Loading the preference from the app_preferences.xml
    private void loadPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isBackgroundDark = sharedPreferences.getBoolean("background_color", false);

        //For the background color settings
        if (isBackgroundDark){
            LinearLayout noteDetailLayout = (LinearLayout) findViewById(R.id.noteDetailLayout);
            noteDetailLayout.setBackgroundColor(Color.parseColor("#F0F4C3"));
            noteDetailLayout.refreshDrawableState();
        }

        //For the title setting
        String title = sharedPreferences.getString("title", "Notes");
        setTitle(title);

    }

}
