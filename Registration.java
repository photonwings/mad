package com.example.program5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Registration extends AppCompatActivity {
    private EditText e1,e2;
    private Button registerButton;
    private String username,password;
    private static SQLiteHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);e1 = findViewById(R.id.editTextTextPersonName2);
        e2 = findViewById(R.id.editTextTextPassword2);
        registerButton = findViewById(R.id.button3);
        helper = new SQLiteHelper(getBaseContext(),"userdb",null,1);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = e1.getText().toString();
                password = e2.getText().toString();
                helper.insertData(username,password);
                Toast.makeText(Registration.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}