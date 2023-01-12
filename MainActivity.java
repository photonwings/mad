package com.example.program5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private EditText e1,e2;
    private Button logButton,registerButton;
    private SQLiteHelper helper;
    private SQLiteDatabase database;
    private String username, password,tempPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextTextPassword);
        logButton = findViewById(R.id.button);
        registerButton = findViewById(R.id.button2);
        helper = new SQLiteHelper(getBaseContext(),"userdb",null,1);
        helper.queryData("create table if not exists users(username varchar,password varchar)");
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = e1.getText().toString();
                password = e2.getText().toString();
                try{
                    helper = new SQLiteHelper(getBaseContext(),"userdb",null,1);
                    final String path = getApplicationContext().getDatabasePath("userdb").getPath();
                    database = SQLiteDatabase.openOrCreateDatabase(path,null);
                    Cursor cursor = database.rawQuery("select password from users where username=?",new String[]
                            {username});
                    if(cursor.getCount() == 0){
                        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        while(cursor.moveToNext()){
                            tempPass = cursor.getString(0);
                        }
                        if(tempPass.equals(password)){
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Incorrect Credential", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this,Registration.class);
            startActivity(i);
        }
        });
    }
}