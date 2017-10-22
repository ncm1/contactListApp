package com.example.nathanmorgenstern.contactlistapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ContactProfileActivity extends AppCompatActivity implements ContactProfileFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);
    }

    public void onFragmentInteraction(Uri uri){

    }
}
