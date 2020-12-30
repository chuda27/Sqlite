package com.choirulhuda.databasesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit, btnShowAll;
    private TextView tvStudentName;
    private EditText edtStudentName;

    private DatabaseHelper dbHelper;
    private ArrayList<String> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        btnSubmit = findViewById(R.id.btn_submit);
        btnShowAll = findViewById(R.id.btn_show_all_student);
        edtStudentName = findViewById(R.id.edt_student_name);
        tvStudentName = findViewById(R.id.tv_student_name);
    }

    public void actionSubmit(View view) {
        if (!edtStudentName.getText().toString().isEmpty()) {
            dbHelper.addStudentDetail(edtStudentName.getText().toString());
            edtStudentName.setText("");
            Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }

    }

    public void actionShowAll(View view) {
        studentList = dbHelper.getAllStudents();
        tvStudentName.setText("");
        StringBuilder allName = new StringBuilder();
        for (String studentName : studentList) {
            allName.append(studentName+", ");
        }
        tvStudentName.setText(allName);
    }
}