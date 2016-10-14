package com.example.castroreyrobert.fragmentlistview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends ListFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String [] android_versions = new String[]{"Nougat", "Lollipop", "Kitkat", "JellyBean"};
        ArrayAdapter<String>  adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,
                android_versions);

        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Toast.makeText(getActivity(), String.valueOf(l.getItemAtPosition(position)), Toast.LENGTH_SHORT).show();
        super.onListItemClick(l, v, position, id);
    }
}
