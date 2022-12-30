package com.example.program6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {
    EditText messageEditText;
    Button sendBtn, contactBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtn = findViewById(R.id.button);
        contactBtn = findViewById(R.id.contact);
        messageEditText = findViewById(R.id.message1);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(i, 1);
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SmsManager smg = SmsManager.getDefault();
                    smg.sendTextMessage(contactBtn.getText().toString(), null,
                            messageEditText.getText().toString(), null, null);
                    Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.e("Message", e.toString());
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    Log.d("contact", e.toString());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    Uri contactData = data.getData();
                    Cursor cursor = managedQuery(contactData, null, null, null, null);
                    cursor.moveToFirst();
                    String number = "Contact Numbere";
                    int xz = cursor.getColumnIndex("data1");
                    number = cursor.getString(xz);
                    contactBtn.setText(number);
                } catch (Exception e) {
                    contactBtn.setText(e.toString());
                    Log.d("contact", e.toString());
                }
            }
        }
    }
}