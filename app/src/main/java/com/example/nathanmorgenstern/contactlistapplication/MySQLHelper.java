package com.example.nathanmorgenstern.contactlistapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nathanmorgenstern.contactlistapplication.Contact;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

public class MySQLHelper extends SQLiteOpenHelper {

    private static final String SQL_DEBUGGER = "SQL_DEBUG";
    //Contact table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contact Table Columns names
    private static final String KEY_ID   = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_RELATIONSHIPS = "relationships";


    private static final String[] COLUMNS = {KEY_NAME,KEY_NUMBER,KEY_RELATIONSHIPS};

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ContactsDB";

    public MySQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_CONTACT_LIST_TABLE = "CREATE TABLE " +  TABLE_CONTACTS +  "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_NUMBER + " TEXT)";

        // create contacts table
        db.execSQL(CREATE_CONTACT_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // create fresh table
        this.onCreate(db);
    }

    public void addContact(Contact contact){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put(KEY_ID,contact.get_id()); //get the contact id
        values.put(KEY_NAME, contact.getName()); // get name
        values.put(KEY_NUMBER, contact.getNumber()); // get phone number

        //for logging
        //Log.v(SQL_DEBUGGER, "Contact id: " + contact.get_id());
        Log.v(SQL_DEBUGGER, "Contact name: " + contact.getName());
        Log.v(SQL_DEBUGGER, "Contact phone: " + contact.getNumber());

        // 3. insert
        db.insert(TABLE_CONTACTS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cursor_count = cursor.getCount();
        cursor.close();
        db.close();
        // return count
        return cursor_count;
    }

    // Getting All Contacts
    public ArrayList<String> getAllContacts() {
        ArrayList<String> contactList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                contactList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

}


//References: https://www.codeproject.com/Articles/119293/Using-SQLite-Database-with-Android
//http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/