package com.example.contactapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter {
    private final Context context;
    private List<Contact> contactList;
    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> contactList) {
        super(context, resource, contactList);
        this.context= context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_contact_item, parent, false);
        } else {
            convertView = (LinearLayout) convertView;
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        String photoUriString = contactList.get(position).getImage();

        if (photoUriString != null) {
            Uri photoUri = Uri.parse(photoUriString);
            imageView.setImageURI(photoUri);
        } else {
            imageView.setImageResource(R.drawable.img); // Remplacez "default_image" par l'ID de votre image par défaut
        }

        TextView viewTitle = convertView.findViewById(R.id.Contact_item);
        viewTitle.setText(contactList.get(position).getNom());
        viewTitle.setTag(contactList.get(position).getNom());

        return convertView;
    }


}
