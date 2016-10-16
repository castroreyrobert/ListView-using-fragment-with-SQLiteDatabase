package com.example.castroreyrobert.fragmentlistview;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

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
        noteModelArrayList.add(new NoteModel("Title is the most important part of the note",
                "Body describes all you write in the note and the best part of the note. It also considers the body of the note",
                NoteModel.Category.PERSONAL));

        noteAdapter = new NoteAdapter(getActivity(),noteModelArrayList);

        setListAdapter(noteAdapter);

       /* //setting the divider of the listview
        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.darker_gray));
        getListView().setDividerHeight(1);*/

        //Displaying the menu if the user long click the item in the listview
        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        launchNoteDetailActivity(position);
    }

    //Setting the long_press_menu when the user long click the listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //Setting the name of the menu
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu,menu);
    }


    //If the user clicks the menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.edit:
                Toast.makeText(getActivity(), "We pressed edit", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(int position){
        NoteModel noteModel = (NoteModel) getListAdapter().getItem(position);

        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(MainActivity.NOTE_TITLE, noteModel.getTitle());
        intent.putExtra(MainActivity.NOTE_NOTE, noteModel.getNote());
        intent.putExtra(MainActivity.NOTE_CATEGORY, noteModel.getCategory());
        intent.putExtra(MainActivity.NOTE_ID, noteModel.getId());

        startActivity(intent);

    }
}
