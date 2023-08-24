package com.example.labapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "FriendDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "friendTable";

    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String DOB_COL = "dob";
    private static final String PHONE_COL = "phone";
    private static final String EMAIL_COL = "email";
    private static final String ADDRESS_COL = "address";
    private static final String GENDER_COL = "gender";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DOB_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + ADDRESS_COL + " TEXT,"
                + GENDER_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewFriend(String friendName, String friendDob, String friendPhone, String friendEmail, String friendAddress, String friendGender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, friendName);
        values.put(DOB_COL, friendDob);
        values.put(PHONE_COL, friendPhone);
        values.put(EMAIL_COL, friendEmail);
        values.put(ADDRESS_COL, friendAddress);
        values.put(GENDER_COL, friendGender);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<FriendModel> readFriends() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorFriends = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<FriendModel> friendModelArrayList = new ArrayList<>();

        if(cursorFriends.moveToFirst()) {
            do {
                friendModelArrayList.add(new FriendModel(
                        cursorFriends.getString(1),
                        cursorFriends.getString(2),
                        cursorFriends.getString(3),
                        cursorFriends.getString(4),
                        cursorFriends.getString(5),
                        cursorFriends.getString(6)
                        ));
            } while(cursorFriends.moveToNext());
        }

        cursorFriends.close();
        return friendModelArrayList;
    }

    public void updateFriend(String OriginalFriendName, String friendName, String friendDob, String friendPhone, String friendEmail) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, friendName);
        values.put(DOB_COL, friendDob);
        values.put(PHONE_COL, friendPhone);
        values.put(EMAIL_COL, friendEmail);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{OriginalFriendName});
        db.close();
    }

    public long getFriendCount(String friendGen) {
        SQLiteDatabase db = this.getReadableDatabase();

        long numRows = DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE gender='" + friendGen + "'", null);

        return numRows;
    }

    public List<String> getNames() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorFriends = db.rawQuery("SELECT " + NAME_COL
                + " FROM " + TABLE_NAME, null);
        List<String> result = new ArrayList<>();

        if(cursorFriends.moveToFirst()) {
            do {
                result.add(cursorFriends.getString(1));
            } while(cursorFriends.moveToNext());
        }
        cursorFriends.close();
        return result;
    }

    public ArrayList<FriendModel> getFriendByName(String keyword) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorFriends = db.rawQuery("SELECT * FROM " + TABLE_NAME
                + " WHERE " + NAME_COL + " LIKE ?", new String[] {"%" + keyword + "%" });
        ArrayList<FriendModel> friendModelArrayList = new ArrayList<>();

        if(cursorFriends.moveToFirst()) {
            do {
                friendModelArrayList.add(new FriendModel(
                        cursorFriends.getString(1),
                        cursorFriends.getString(2),
                        cursorFriends.getString(3),
                        cursorFriends.getString(4),
                        cursorFriends.getString(5),
                        cursorFriends.getString(6)
                ));
            } while(cursorFriends.moveToNext());
        }
        cursorFriends.close();
        return friendModelArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
