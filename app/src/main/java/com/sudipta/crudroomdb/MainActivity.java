package com.sudipta.crudroomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sudipta.crudroomdb.Room.MyDatabase;
import com.sudipta.crudroomdb.Room.Student;

public class MainActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText className;
    private Button insertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        className = findViewById(R.id.className);
        insertBtn = findViewById(R.id.insertBtn);


        //Insert Data
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student =new Student(firstName.getText().toString(),lastName.getText().toString(),className.getText().toString());

                MyDatabase myDatabase = Room.databaseBuilder(MainActivity.this, MyDatabase.class,"studentDB")
                        .allowMainThreadQueries().build();
                myDatabase.dao().studentInsertion(student);
            }
        });
    }
}