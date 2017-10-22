package com.example.nathanmorgenstern.contactlistapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity implements ContactListFragment.OnFragmentInteractionListener, ContactDetailsFragment.OnFragmentInteractionListener, ContactProfileFragment.OnFragmentInteractionListener{

    private static final String MAIN_ACTIVITY = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFragmentInteraction(Uri uri){

    }

}
