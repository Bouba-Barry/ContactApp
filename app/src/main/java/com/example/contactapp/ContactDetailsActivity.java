package com.example.contactapp;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView emailText;
    private Button contactButton;
    private TextView typeCpt;


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        // récuperons les élements:
        nameText = findViewById(R.id.nameAccountTxt);
        emailText = findViewById(R.id.mailTxt);
        contactButton = findViewById(R.id.number);
        typeCpt = findViewById(R.id.AccountTypeTxt);


        long contactId = getIntent().getLongExtra("contactId", -1);
        if (contactId != -1) {
            Cursor cursor = getContentResolver().query(
                    ContactsContract.RawContacts.CONTENT_URI,
                    null,
                    ContactsContract.RawContacts.CONTACT_ID + " = ?",
                    new String[]{String.valueOf(contactId)},
                    null
            );
        }

    }
}
