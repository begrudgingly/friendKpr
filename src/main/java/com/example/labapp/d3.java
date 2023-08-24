package com.example.labapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class d3 extends AppCompatActivity {

    EditText editName, editPhone, editEmail, editTextDate;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    Button buttonUpdate;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d3);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        editTextDate = findViewById(R.id.edit_date_view);

        buttonUpdate = findViewById(R.id.buttonUpdate);
        dbHandler = new DBHandler(d3.this);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(d3.this, onDateSetListener,year,month,day);

                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                editTextDate.setText(date);
            }
        };

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friendName = editName.getText().toString();
                String friendDob = editTextDate.getText().toString();
                String friendPhone = editPhone.getText().toString();
                String friendEmail = editEmail.getText().toString();

                if(friendName.isEmpty() && friendDob.isEmpty() && friendPhone.isEmpty() && friendEmail.isEmpty()) {
                    Toast.makeText(d3.this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.updateFriend(friendName, friendName, friendDob, friendPhone, friendEmail);

                Toast.makeText(d3.this, "Friend's info has been updated.", Toast.LENGTH_SHORT).show();
                editName.setText("");
                editTextDate.setText("");
                editPhone.setText("");
                editEmail.setText("");
            }
        });
    }

}