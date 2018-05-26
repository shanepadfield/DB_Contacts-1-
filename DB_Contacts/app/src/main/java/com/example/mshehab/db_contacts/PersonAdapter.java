package com.example.mshehab.db_contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mshehab on 11/20/17.
 */

public class PersonAdapter extends ArrayAdapter<Person> {
    public PersonAdapter(Context context, int resource, List<Person> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Person person = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);
        TextView textViewDept = convertView.findViewById(R.id.textViewDept);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        //set the data from the person object
        textViewName.setText(person.getName());
        textViewEmail.setText(person.getEmail());
        textViewPhone.setText(person.getPhone());
        textViewDept.setText(person.getDept());
        //imageView.setImageResource(person.picID);

        return convertView;
    }
}
