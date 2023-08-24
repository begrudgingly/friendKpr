package com.example.labapp;


import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class d2 extends AppCompatActivity {

    private ArrayList<FriendModel> friendModelArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d2);

        friendModelArrayList = new ArrayList<>();
        dbHandler = new DBHandler(d2.this);

        friendModelArrayList = dbHandler.readFriends();

        courseRVAdapter = new CourseRVAdapter(friendModelArrayList, d2.this);
        coursesRV = findViewById(R.id.idRVFriends);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(d2.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        coursesRV.setAdapter(courseRVAdapter);
    }
}