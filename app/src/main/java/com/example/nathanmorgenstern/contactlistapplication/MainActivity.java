package com.example.nathanmorgenstern.contactlistapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ContactListFragment.OnFragmentInteractionListener, ContactDetailsFragment.OnFragmentInteractionListener{

    private static final String MAIN_ACTIVITY = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFragmentInteraction(Uri uri){
        /*ContactListFragment f = (ContactListFragment) getFragmentManager().findFragmentById(R.id.contact_list_fragment);

        Intent i = new Intent(this, ContactListActivity.class);
        Log.v(MAIN_ACTIVITY, "New intent starting...");
        startActivity(i);*/
    }
}
