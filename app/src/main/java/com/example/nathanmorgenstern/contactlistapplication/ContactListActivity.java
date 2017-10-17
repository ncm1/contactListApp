package com.example.nathanmorgenstern.contactlistapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nathanmorgenstern on 10/14/17.
 */

public class ContactListActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_greeting2);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Intent i = getIntent();
        ContactListFragment f = (ContactListFragment) getFragmentManager().findFragmentById(R.id.contact_list_fragment);
    }

    public void onFragmentInteraction(Uri uri){

    }
}
