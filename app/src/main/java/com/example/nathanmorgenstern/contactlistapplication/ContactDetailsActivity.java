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
        //updateRelationshipView();
    }

    public void onFragmentInteraction(Uri uri){
    }

    /*public void updateRelationshipView(){
        ArrayList<String> itemList = new ArrayList<>();

        itemList.add("Hello");
        itemList.add("Hello");
        itemList.add("Hello");
        itemList.add("Hello");
        itemList.add("Hello");
        itemList.add("Hello");
        itemList.add("Hello");
        itemList.add("Hello");
        itemList.add("Hello");
        itemList.add("Hello");
        itemList.add("Hello");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,

                R.layout.my_custom_view, itemList);

        CustomAdapter custom_adapter = new CustomAdapter(this, R.layout.my_custom_view, itemList);
        ListView contactList = (ListView)findViewById(R.id.relationshipListView);
        contactList.setAdapter(custom_adapter);
    }*/
}
