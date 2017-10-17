package com.example.nathanmorgenstern.contactlistapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String CONTACT_DETAILS = "CONTACT_DETAILS";
    private MySQLHelper sqlHelper;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ContactDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactDetailsFragment newInstance(String param1, String param2) {
        ContactDetailsFragment fragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_details, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        sqlHelper = new MySQLHelper(getContext());
        loadDataToListView();

        Button addPersonButton = (Button)getActivity().findViewById(R.id.addPerson);
        addPersonButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                mListener.onFragmentInteraction(null);
                Log.v(CONTACT_DETAILS, "Add Button pressed");

                EditText name_input = (EditText)getActivity().findViewById(R.id.nameInput);
                EditText phone_input = (EditText)getActivity().findViewById(R.id.phoneNumberInput);
                //get the strings from the edit text field
                String nameStr = name_input.getText().toString();
                String phoneStr = phone_input.getText().toString();

                Log.v(CONTACT_DETAILS, "nameStr " + nameStr);
                Log.v(CONTACT_DETAILS, "phoneStr " + phoneStr);
                int id = sqlHelper.getContactsCount() + 1;

                //Create the new contact and add them to the database using sqlHelper class
                Contact newContact = new Contact(id,nameStr,phoneStr);
                sqlHelper.addContact(newContact);
                Log.v(CONTACT_DETAILS, "added contact with sqlHelper");
                getActivity().finish();
            }
        });
    }

    public void loadDataToListView(){
        ListView contactList = (ListView)getActivity().findViewById(R.id.relationshipListView);
        ArrayList<String> itemList;

        itemList = sqlHelper.getAllContacts();

        Log.v(CONTACT_DETAILS, "itemList.size: " + itemList.size());

        CustomAdapter custom_adapter = new CustomAdapter(getContext(), R.layout.my_custom_view, itemList);
        contactList.setAdapter(custom_adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
