package com.example.castroreyrobert.fragmentlistview;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;



public class NoteEditFragment extends Fragment {

    private EditText etTitle, etNote;
    private ImageButton imageIcon;
    private NoteModel.Category savedButtonCategory;

    //Object for the alertDialog
    private AlertDialog categoryAlertDialogObject, confirmAlertDialogObject;

    private long noteID = 0;

    //Variable if the orientation change
    private static final String ORIENTATION_STATE = "Orientation Change";

    //variable for the Adding a new note
    private boolean newNote = false;

    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Getting the bundle that we passed from the NoteDetailActivity
        Bundle bundle = this.getArguments();
        if (bundle != null){
            newNote = bundle.getBoolean(NoteDetailActivity.ADD_NOTE);
        }

        if (savedInstanceState != null){
            savedButtonCategory = (NoteModel.Category) savedInstanceState.get(ORIENTATION_STATE);
        }


        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        // Casting the Views from the fragment_note_edit
        etTitle = (EditText) fragmentLayout.findViewById(R.id.etTitle);
        etNote = (EditText) fragmentLayout.findViewById(R.id.etNote);
        imageIcon = (ImageButton)fragmentLayout.findViewById(R.id.imageButtonIcon);
        Button btnSave = (Button)fragmentLayout.findViewById(R.id.buttonSaveNote);


        //Getting the intent from the launchNoteDetailActivity of the MainActivityFragment class
        Intent intent = getActivity().getIntent();

        //Setting the values from the views for this fragment
        //Getting the values from the intent that we passed from the MainActivityFragment
        etTitle.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE));
        etTitle.setHint("Title here......");
        etNote.setText(intent.getExtras().getString(MainActivity.NOTE_NOTE));
        etNote.setHint("Notes here.....");
        noteID = intent.getExtras().getLong(MainActivity.NOTE_ID,0);

        //If the user change the orientation, the imageButton should stay what it has
        if (savedButtonCategory != null){
            imageIcon.setImageResource(NoteModel.categoryToDrawable(savedButtonCategory));


        }else if (!newNote){
            //Setting the category from the NoteModel class
            NoteModel.Category noteCat = (NoteModel.Category)intent.getSerializableExtra(MainActivity.NOTE_CATEGORY);

            //Getting the default category if the user doesn't change the Category
            savedButtonCategory = noteCat;

            //Setting the image from fragment_note_view
            imageIcon.setImageResource(NoteModel.categoryToDrawable(noteCat));

        }

        //Calling the createCategoryDialogBuilder method
        createCategoryDialogBuilder();

        //Calling the createConfirmDialogBuilder
        createConfirmDialogBuilder();

        //If the user clicks the ImageButton
        imageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryAlertDialogObject.show();
            }
        });

        //If the user clicks the SaveButton
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmAlertDialogObject.show();
            }
        });

        return fragmentLayout;

    }
    //If the user change the orientation
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Saving the imageButton picture before changing the orientation
        outState.putSerializable(ORIENTATION_STATE, savedButtonCategory);
    }

    //creating a dialog builder when the user clicks the imageButton
    private void createCategoryDialogBuilder(){
        final String [] categories = new String[]{"Personal", "Technical", "Android", "Home"};
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle("Choose a category");

        //Displaying the array categories to the alertDialog
        alertBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                //dismisses the alert dialog if the user choose a category
                categoryAlertDialogObject.cancel();

                switch (item){
                    case 0:
                        savedButtonCategory = NoteModel.Category.PERSONAL;
                        imageIcon.setImageResource(R.drawable.personal);
                        break;
                    case 1:
                        savedButtonCategory = NoteModel.Category.TECHNICAL;
                        imageIcon.setImageResource(R.drawable.technical);
                        break;
                    case 2:
                        savedButtonCategory = NoteModel.Category.ANDROID;
                        imageIcon.setImageResource(R.drawable.android);
                        break;
                    case 3:
                        savedButtonCategory = NoteModel.Category.HOME;
                        imageIcon.setImageResource(R.drawable.home);
                        break;
                }
            }
        });
        categoryAlertDialogObject = alertBuilder.create();

    }

    //AlertDialog if the user clicks the SaveButton
    private void createConfirmDialogBuilder(){

        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle("Confirm");
        confirmBuilder.setMessage("Are you sure you want to save the changes?");
        confirmBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Note Title: " + etTitle.getText().toString() + "\nNote: "
                        + etNote.getText().toString()
                        + "\nCategory: " + savedButtonCategory, Toast.LENGTH_SHORT).show();

                //Adds a new note
                DBHelper dbHelper = new DBHelper(getActivity().getBaseContext());
                dbHelper.open();
                if (newNote){

                    //If it's a new note then create a database
                    dbHelper.addNote(etTitle.getText().toString(), etNote.getText().toString(),
                            (savedButtonCategory == null)? NoteModel.Category.PERSONAL : savedButtonCategory);
                }else {
                    //Otherwise update the note
                    dbHelper.updateNote(noteID, etTitle.getText().toString(), etNote.getText().toString(),
                            savedButtonCategory);
                }
                dbHelper.close();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        confirmBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        confirmAlertDialogObject = confirmBuilder.create();
    }

}
