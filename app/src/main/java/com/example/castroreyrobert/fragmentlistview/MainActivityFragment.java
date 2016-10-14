package com.example.castroreyrobert.fragmentlistview;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends ListFragment {

    private ArrayList<NoteModel> noteModelArrayList;
    private NoteAdapter noteAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       /* //Items on the listview
        String [] android_versions = new String[]{"Nougat", "Lollipop", "Kitkat", "JellyBean"};


        //Setting the adapter to the listview
        ArrayAdapter<String>  adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,
                android_versions);

        setListAdapter(adapter);*/

        //Adding the values of the notes
        noteModelArrayList = new ArrayList<NoteModel>();
        noteModelArrayList.add(new NoteModel("This is the title", "This is the note message",
                NoteModel.Category.FINANCE));
        noteModelArrayList.add(new NoteModel("Oh yeah!", "Need to study about SQLite Database",
                NoteModel.Category.PERSONAL));
        noteModelArrayList.add(new NoteModel("Nice!", "Study dynamically adding the value to the listview",
                NoteModel.Category.QUOTE));
        noteModelArrayList.add(new NoteModel("TODO", "Music Player",
                NoteModel.Category.TECHNICAL));
        noteModelArrayList.add(new NoteModel("Good Job!", "Have some relaxation",
                NoteModel.Category.PERSONAL));

        noteAdapter = new NoteAdapter(getActivity(),noteModelArrayList);

        setListAdapter(noteAdapter);

       /* //setting the divider of the listview
        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.darker_gray));
        getListView().setDividerHeight(1);*/
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


        Toast.makeText(getActivity(), String.valueOf(l.getItemAtPosition(position)), Toast.LENGTH_SHORT).show();
        super.onListItemClick(l, v, position, id);
    }
}
