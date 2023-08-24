package com.example.labapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class d5 extends AppCompatActivity {
    private ArrayList<FriendModel> friendModelArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d5);

        materialSearchBar = findViewById(R.id.searchBar);
        dbHandler = new DBHandler(d5.this);
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for(String search:suggestList)
                {
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }

                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    coursesRV.setAdapter(courseRVAdapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        friendModelArrayList = new ArrayList<>();
        dbHandler = new DBHandler(d5.this);

        friendModelArrayList = dbHandler.readFriends();

        courseRVAdapter = new CourseRVAdapter(friendModelArrayList, d5.this);
        coursesRV = findViewById(R.id.idFriendSearch);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(d5.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        coursesRV.setAdapter(courseRVAdapter);
    }

    private void startSearch(String text) {
        friendModelArrayList = new ArrayList<>();
        friendModelArrayList = dbHandler.getFriendByName(text);
        courseRVAdapter = new CourseRVAdapter(friendModelArrayList, d5.this);
        coursesRV.setAdapter(courseRVAdapter);
    }
}