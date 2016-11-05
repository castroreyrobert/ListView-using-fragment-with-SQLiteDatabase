package com.example.castroreyrobert.fragmentlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;


public class DBHelper{

    private static final String DB_NAME = "notes.db";
    private static final int DB_VERSION = 1;

    public static final String TB_NAME = "notes_table";
    public static final String COL_ID = "ID";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_NOTE = "NOTE";
    public static final String COL_CATEGORY = "CATEGORY";
    public static final String COL_DATE = "DATE";

    private String[] allColumn = {COL_ID,COL_TITLE,COL_NOTE, COL_CATEGORY, COL_DATE};

    public static final String CREATE_QUERY = "CREATE TABLE " + TB_NAME + "( "
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT NOT NULL, "
            + COL_NOTE + " TEXT NOT NULL, "
            + COL_CATEGORY + " TEXT NOT NULL, "
            + COL_DATE + " );";

    private SQLiteDatabase db;
    private Context context;

    private DBHandler dbHandler;

    //Constructor for the class DBHelper
    public DBHelper(Context ctx) {
        context = ctx;
    }

    public DBHelper open() throws android.database.SQLException{
        dbHandler = new DBHandler(context);
        db = dbHandler.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHandler.close();
    }

    //This method gets called if you want to add a new note
    public NoteModel addNote(String title, String note, NoteModel.Category category){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TITLE, title);
        contentValues.put(COL_NOTE, note);
        contentValues.put(COL_CATEGORY, category.name() + "");
        contentValues.put(COL_DATE, Calendar.getInstance().getTimeInMillis() + "");

        long insertId = db.insert(TB_NAME, null, contentValues);

        Cursor res = db.query(TB_NAME, allColumn, COL_ID + " = " + insertId, null,null,null,null);

        res.moveToFirst();
        NoteModel noteModel = cursorToNote(res);
        res.close();
        return noteModel;
    }

    //This method gets called if you want to edit the existing note
    public long updateNote(long idToUpdate, String title, String note, NoteModel.Category category){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TITLE, title);
        contentValues.put(COL_NOTE, note);
        contentValues.put(COL_CATEGORY, category.name() + "");
        contentValues.put(COL_DATE, Calendar.getInstance().getTimeInMillis()+ "");

        return db.update(TB_NAME, contentValues, COL_ID + " = " + idToUpdate, null);

    }

    public long deleteNote(long idToDelete){
        return db.delete(TB_NAME, COL_ID + " = " + idToDelete, null);

    }


    //This method gets called in displaying the notes in the ListView
    public ArrayList<NoteModel> getAllNotes(){
        ArrayList<NoteModel> arrayNotes = new ArrayList<NoteModel>();

        //Grab all the information in our database for the notes in it
        Cursor res = db.query(TB_NAME, allColumn, null, null, null, null, null);

        //Loop through all of the rows (arraynotes) in our database and create new note objects from
        //those rows and add them to our array list
        for (res.moveToLast(); !res.isBeforeFirst(); res.moveToPrevious()){
            NoteModel noteModel = cursorToNote(res);
            arrayNotes.add(noteModel);
        }

        //Close our cursor (Required)
        res.close();
        return arrayNotes;
    }

    private NoteModel cursorToNote(Cursor res){
        NoteModel newNote = new NoteModel(res.getString(1), res.getString(2),
                NoteModel.Category.valueOf(res.getString(3)), res.getLong(0), res.getLong(4));
        return newNote;
    }


    private static class DBHandler extends SQLiteOpenHelper{

        public DBHandler(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXIST " + TB_NAME);
            onCreate(db);
        }


    }

}