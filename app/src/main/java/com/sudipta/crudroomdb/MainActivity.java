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
    private Button insertBtn;
    private Button readBtn;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        className = findViewById(R.id.className);
        insertBtn = findViewById(R.id.insertBtn);
        readBtn = findViewById(R.id.readBtn);

        setUpDb();

        //Insert Data
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student(firstName.getText().toString(), lastName.getText().toString(), className.getText().toString());

                myDatabase.dao().studentInsertion(student);
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> studentList = myDatabase.dao().getStudent();
                for (int i = 0; i < studentList.size(); i++) {
                    String msg = "ID " + studentList.get(i).getStuId() + " , " +
                            "Name " + studentList.get(i).getStuFirstName() +" "+ studentList.get(i).getStuLastName() + " , " +
                            "Class " + studentList.get(i).getStuClass();
                    Log.d("STUDENT_DATA ", msg);
                }
            }
        });
    }

    private void setUpDb() {
        myDatabase = Room.databaseBuilder(MainActivity.this, MyDatabase.class, "studentDB")
                .allowMainThreadQueries().build();
    }
}