package com.example.castroreyrobert.fragmentlistview;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteViewFragment extends Fragment {


    public NoteViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_note_view, container, false);

        //Casting the views from the fragment_note_view layout
        ImageView imageIcon2 = (ImageView) fragmentLayout.findViewById(R.id.imageIcon2);
        TextView tvTitle2 = (TextView) fragmentLayout.findViewById(R.id.tvTitle2);
        TextView tvNote2 = (TextView) fragmentLayout.findViewById(R.id.tNote2);


        //Getting the data from the intent that we pass from the MainActivity fragment
        Intent intent = getActivity().getIntent();

        //Setting the text/image from the textviews of the fragment layout
        tvTitle2.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE));
        tvNote2.setText(intent.getExtras().getString(MainActivity.NOTE_NOTE));

        //Setting the category from the NoteModel class
        NoteModel.Category noteCat = (NoteModel.Category)intent.getSerializableExtra(MainActivity.NOTE_CATEGORY);
        //Setting the image from fragment_note_view
        imageIcon2.setImageResource(NoteModel.categoryToDrawable(noteCat));

        return fragmentLayout;
    }
}