package com.example.castroreyrobert.fragmentlistview;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;



public class NoteEditFragment extends Fragment {

    private ImageButton imageIcon;
    private NoteModel.Category savedButtonCategory;
    private AlertDialog categoryAlertDialogObject;

    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        // Casting the Views from the fragment_note_edit
        EditText etTitle = (EditText) fragmentLayout.findViewById(R.id.etTitle);
        EditText etNote = (EditText) fragmentLayout.findViewById(R.id.etNote);
        imageIcon = (ImageButton)fragmentLayout.findViewById(R.id.imageButtonIcon);


        //Getting the intent from the launchNoteDetailActivity of the MainActivityFragment class
        Intent intent = getActivity().getIntent();

        //Setting the values from the views for this fragment
        etTitle.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE));
        etNote.setText(intent.getExtras().getString(MainActivity.NOTE_NOTE));

        //Setting the category from the NoteModel class
        NoteModel.Category noteCat = (NoteModel.Category)intent.getSerializableExtra(MainActivity.NOTE_CATEGORY);
        //Setting the image from fragment_note_view
        imageIcon.setImageResource(NoteModel.categoryToDrawable(noteCat));

        //Calling the createDialogBuilder method
        createDialogBuilder();

        //if the user clicks the ImageButton
        imageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryAlertDialogObject.show();
            }
        });
        return fragmentLayout;

    }

    //creating a dialog builder when the user clicks the imageButton
    private void createDialogBuilder(){
        final String [] categories = new String[]{"Personal", "Technical", "Quote", "Finance"};
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle("Choose a category");

        alertBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                //dismiss the alert dialog
                categoryAlertDialogObject.cancel();
                switch (item){
                    case 0:
                        savedButtonCategory = NoteModel.Category.PERSONAL;
                        imageIcon.setImageResource(R.drawable.notesfour);
                        break;
                    case 1:
                        savedButtonCategory = NoteModel.Category.TECHNICAL;
                        imageIcon.setImageResource(R.drawable.noteone);
                        break;
                    case 2:
                        savedButtonCategory = NoteModel.Category.QUOTE;
                        imageIcon.setImageResource(R.drawable.notesthree);
                        break;
                    case 3:
                        savedButtonCategory = NoteModel.Category.FINANCE;
                        imageIcon.setImageResource(R.drawable.notestwo);
                        break;
                }
            }
        });
        categoryAlertDialogObject = alertBuilder.create();

    }

}
