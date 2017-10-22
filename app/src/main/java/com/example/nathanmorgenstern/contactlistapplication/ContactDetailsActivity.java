package com.example.nathanmorgenstern.contactlistapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ContactDetailsActivity extends AppCompatActivity implements ContactDetailsFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
    }

    public void onFragmentInteraction(Uri uri){
        /*
        //ContactDetailsFragment cdf = (ContactDetailsFragment)getFragmentManager().findFragmentById(R.id.contact_details_fragment);
        ContactListFragment clf = (ContactListFragment)getFragmentManager().findFragmentById(R.id.contact_list_fragment);
        if(clf != null)
            clf.loadDataToListView();
            */
    }

}
