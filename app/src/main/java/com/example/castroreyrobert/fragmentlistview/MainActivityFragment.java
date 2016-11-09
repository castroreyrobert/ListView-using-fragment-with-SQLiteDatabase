package com.example.castroreyrobert.fragmentlistview;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivityFragment extends ListFragment {

    private ArrayList<NoteModel> noteModelArrayList;
    private NoteAdapter noteAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Adding the values of the notes from the DBHelper
        DBHelper dbHelper = new DBHelper(getActivity().getBaseContext());
        dbHelper.open();
        noteModelArrayList = dbHelper.getAllNotes();
        dbHelper.close();
        noteAdapter = new NoteAdapter(getActivity(),noteModelArrayList);
        setListAdapter(noteAdapter);

        //Displaying the menu if the user long click the item in the listview
        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        launchNoteDetailActivity(MainActivity.FRAGMENT_TO_LOAD.VIEW, position);
    }

    //Displaying the menu if the user long clicks the item in the listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //Setting the name of the menu
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu,menu);
    }


    //if the user clicks the menu in the menu item
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //Getting the position of whatever List item I long pressed on
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        //For deleting purposes
        NoteModel noteModel = (NoteModel) getListAdapter().getItem(rowPosition);

        //If the user clicks the specific menu item
        switch (item.getItemId()){

            case R.id.edit:
                launchNoteDetailActivity(MainActivity.FRAGMENT_TO_LOAD.EDIT, rowPosition);
                return true;

            case R.id.delete:
                DBHelper dbHelper = new DBHelper(getActivity().getBaseContext());
                dbHelper.open();
                dbHelper.deleteNote(noteModel.getId());

                //For refreshing the listview if we click the menu item delete
                noteModelArrayList.clear();
                noteModelArrayList.addAll(dbHelper.getAllNotes());
                noteAdapter.notifyDataSetChanged();

                dbHelper.close();
        }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(MainActivity.FRAGMENT_TO_LOAD ftl, int position){
        NoteModel noteModel = (NoteModel) getListAdapter().getItem(position);

        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(MainActivity.NOTE_TITLE, noteModel.getTitle());
        intent.putExtra(MainActivity.NOTE_NOTE, noteModel.getNote());
        intent.putExtra(MainActivity.NOTE_CATEGORY, noteModel.getCategory());
        intent.putExtra(MainActivity.NOTE_ID, noteModel.getId());

        //Setting the fragment of which to load
        switch (ftl){
            case VIEW:
                intent.putExtra(MainActivity.FRAGMENT_TO_LAUNCH, MainActivity.FRAGMENT_TO_LOAD.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.FRAGMENT_TO_LAUNCH, MainActivity.FRAGMENT_TO_LOAD.EDIT);
                break;
        }

        startActivity(intent);

    }
}
