package com.example.castroreyrobert.fragmentlistview;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class NoteAdapter extends ArrayAdapter<NoteModel> {
    public NoteAdapter(Context context, ArrayList<NoteModel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        //Get the date item for this position
        NoteModel noteModel = getItem(position);

        //Check if the existing view is being reused, otherwise inflate a new view from custom_row layout
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_row, parent, false);
            viewHolder = new ViewHolder();
            //Casting of views from the custom_row layout
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.tvNote = (TextView)convertView.findViewById(R.id.tvNote);
            viewHolder.imageIcon = (ImageView)convertView.findViewById(R.id.imageIcon);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Filling each views values
        viewHolder.tvTitle.setText(noteModel.getTitle());
        viewHolder.tvNote.setText(noteModel.getNote());
        viewHolder.imageIcon.setImageResource(noteModel.getAssociatedDrawable());

        return convertView;
    }

    static class ViewHolder{
        TextView tvTitle, tvNote;
        ImageView imageIcon;
    }
}
