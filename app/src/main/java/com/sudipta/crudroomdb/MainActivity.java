package com.sudipta.crudroomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sudipta.crudroomdb.Room.MyDatabase;
import com.sudipta.crudroomdb.Room.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText className;
    private EditText updateName;
    private EditText updateId;
    private EditText deleteId;
    private Button insertBtn;
    private Button readBtn;
    private Button updateBtn;
    private Button deleteBtn;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        className = findViewById(R.id.className);
        updateId = findViewById(R.id.updateid);
        updateName = findViewById(R.id.updatename);
        deleteId = findViewById(R.id.deleteId);
        insertBtn = findViewById(R.id.insertBtn);
        readBtn = findViewById(R.id.readBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        setUpDb();

        //Insert Data
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student(firstName.getText().toString(), lastName.getText().toString(), className.getText().toString());

                myDatabase.dao().studentInsertion(student);
            }
        });

        //read data
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> studentList = myDatabase.dao().getStudent();
                for (int i = 0; i < studentList.size(); i++) {
                    String msg = "ID " + studentList.get(i).getStuId() + " , " +
                            "Name " + studentList.get(i).getStuFirstName() + " " + studentList.get(i).getStuLastName() + " , " +
                            "Class " + studentList.get(i).getStuClass();
                    Log.d("STUDENT_DATA ", msg);
                }
            }
        });
        //update data
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.dao().updatestu(updateName.getText().toString(), Integer.parseInt(updateId.getText().toString()));
            }
        });

        //delete
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.dao().deletestu(Integer.parseInt(deleteId.getText().toString()));
            }
        });
    }


    // create a detabase object methad
    private void setUpDb() {
        myDatabase = Room.databaseBuilder(MainActivity.this, MyDatabase.class, "studentDB")
                .allowMainThreadQueries().build();
    }
}