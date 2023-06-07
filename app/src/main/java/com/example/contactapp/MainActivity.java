package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.contactapp.Contact;
import com.example.contactapp.ContactAdapter;
import com.example.contactapp.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        List<Contact> contactList = new ArrayList<>();

        cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_URI},
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                @SuppressLint("Range") String photoUriString = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));

                contactList.add(new Contact(displayName, photoUriString));
            }

            cursor.close();
        }

        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.list_contact_item, contactList);
        listView.setAdapter(contactAdapter);

        // quand il click sur un contact, on va lui afficher ses détails
        listView.setOnItemClickListener((parent, view, position, id) -> {
            cursor.moveToPosition(position);
            @SuppressLint("Range") long contactId = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
            intent.putExtra("contactId", contactId);
            startActivity(intent);
        });
    }
}
