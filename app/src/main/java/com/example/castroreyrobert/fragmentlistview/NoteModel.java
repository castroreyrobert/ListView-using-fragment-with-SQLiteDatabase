package com.example.castroreyrobert.fragmentlistview;



public class NoteModel {
    private String title, note;
    private long id, dateCreatedMilli;
    private Category category;

    public enum Category{PERSONAL, TECHNICAL, QUOTE, FINANCE}

    public NoteModel(String title, String note, Category category) {
        this.title = title;
        this.note = note;
        this.id = 0;
        this.dateCreatedMilli = 0;
        this.category = category;
    }

    public NoteModel(String title, String note, Long id, Long dateCreatedMilli, Category category) {
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

    public int getAssociatedDrawable(){return categoryToDrawable(category);}

    public static int categoryToDrawable(Category noteCategory){

        switch (noteCategory){
            case PERSONAL:
                return R.drawable.notesfour;
            case TECHNICAL:
                return R.drawable.noteone;
            case QUOTE:
                return R.drawable.notesthree;
            case FINANCE:
                return R.drawable.notestwo;
        }

        return R.drawable.notestwo;
    }
}
