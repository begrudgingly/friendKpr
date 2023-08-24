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

public class d1 extends AppCompatActivity {

    EditText editName, editPhone, editEmail, editAddress, editTextDate;
	DatePickerDialog.OnDateSetListener onDateSetListener;
    RadioGroup radioGroup;
    RadioButton male, female;
    Button buttonAdd;
    DBHandler dbHandler;

    String genSet = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d1);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        editAddress = findViewById(R.id.editAddress);
        editTextDate = findViewById(R.id.edit_date_view);

        radioGroup = findViewById(R.id.gender);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        buttonAdd = findViewById(R.id.buttonAdd);

        dbHandler = new DBHandler(d1.this);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(d1.this, onDateSetListener,year,month,day);

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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.male: genSet = "male";break;
                    case R.id.female: genSet = "female";break;
                }
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friendName = editName.getText().toString();
                String friendDob = editTextDate.getText().toString();
                String friendPhone = editPhone.getText().toString();
                String friendEmail = editEmail.getText().toString();
                String friendAddress = editAddress.getText().toString();
                String friendGender = genSet;

                if(friendName.isEmpty() && friendDob.isEmpty() && friendPhone.isEmpty() && friendEmail.isEmpty() && friendAddress.isEmpty() && friendGender.isEmpty()) {
                    Toast.makeText(d1.this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewFriend(friendName, friendDob, friendPhone, friendEmail, friendAddress, friendGender);

                Toast.makeText(d1.this, "You have added a new friend to the list.", Toast.LENGTH_SHORT).show();
                editName.setText("");
                editTextDate.setText("");
                editPhone.setText("");
                editEmail.setText("");
                editAddress.setText("");
            }
        });
    }
}