package com.example.castroreyrobert.fragmentlistview;



public class NoteModel {
    private String title, note;
    private long id, dateCreatedMilli;
    private Category category;

    //Setting the title of Category
    public enum Category{PERSONAL, TECHNICAL, ANDROID, HOME}

    public NoteModel(String title, String note, Category category) {
        this.title = title;
        this.note = note;
        this.id = 0;
        this.dateCreatedMilli = 0;
        this.category = category;
    }

    public NoteModel(String title, String note, Category category, Long id, Long dateCreatedMilli) {
        this.title = title;
        this.note = note;
        this.id = id;
        this.dateCreatedMilli = dateCreatedMilli;
        this.category = category;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public String getNote() {
        return note;
    }


    public Category getCategory() {
        return category;
    }


    public String toString(){
        return "ID: " + id + " Title: " + title + " Note: " + note + " IconID: " + category.name()
                + " Date: ";
    }

    //Getting the category and return the specific image
    public int getAssociatedDrawable(){return categoryToDrawable(category);}

    //Setting the image into what specific category
    public static int categoryToDrawable(Category noteCategory){

        switch (noteCategory){
            case PERSONAL:
                return R.drawable.personal;
            case TECHNICAL:
                return R.drawable.technical;
            case ANDROID:
                return R.drawable.android;
            case HOME:
                return R.drawable.home;
        }

        return R.drawable.personal;
    }
}
