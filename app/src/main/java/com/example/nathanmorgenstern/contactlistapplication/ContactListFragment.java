package com.example.nathanmorgenstern.contactlistapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.fragment;
import static android.R.attr.orientation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String CONTACT_LIST = "CONTACT_LIST_FRAGMENT";
    private MySQLHelper sqlHelper;
    private CheckBox chBox;
    private TextView text;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ContactListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactListFragment newInstance(String param1, String param2) {
        ContactListFragment fragment = new ContactListFragment();
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
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        loadDataToListView();

        if (this.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            landscapeMode();
        }
        else
            portraitMode();

        //Add the deleteButton action listener
        Button deleteButton = (Button) getActivity().findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.onFragmentInteraction(null);
                Log.v(CONTACT_LIST, "Delete Button pressed");
                getCheckedBoxes();
                loadDataToListView();

                ContactDetailsFragment cdf = (ContactDetailsFragment)getFragmentManager().findFragmentById(R.id.fragment_container);
                if (getActivity().getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_LANDSCAPE && cdf != null)
                        cdf.loadDataToListView();
            }
        });

        ListView contactList = (ListView)getActivity().findViewById(R.id.contact_list_view);
        contactList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> list,
                                            View row,
                                            int index,
                                            long rowID) {
                        TextView name = (TextView) row.findViewById(R.id.list_row_text);

                        orientationHandler(name.getText().toString());
                    }
                });
    }

    public void portraitMode(){
        Button addButton = (Button) getActivity().findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.onFragmentInteraction(null);
                Log.v(CONTACT_LIST, "Add Button pressed");
                Intent i = new Intent(getActivity(), ContactDetailsActivity.class);
                Log.v(CONTACT_LIST, "New intent starting...");
                startActivity(i);
            }
        });
    }

    public void landscapeMode(){
        Button addButton = (Button) getActivity().findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.onFragmentInteraction(null);
                Log.v(CONTACT_LIST, "Add Button pressed");
                ContactDetailsFragment f = new ContactDetailsFragment();

                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, f);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                TextView rightSide = (TextView)getActivity().findViewById(R.id.right_side_toolbar);
                rightSide.setText("Contact Details");
            }
        });

        Log.v(CONTACT_LIST, "I'm in landscape mode");

    }
    //Reference: https://developer.android.com/training/basics/fragments/fragment-ui.html

    public void getCheckedBoxes(){
        View v;
        ListView contactList = (ListView) getActivity().findViewById(R.id.contact_list_view);
        Log.v(CONTACT_LIST, "Contact list.getCount(): " + contactList.getCount());
        for (int i = 0; i < contactList.getCount(); i++) {
            v = contactList.getAdapter().getView(i, null, null);
            //Getting the views by their index...
            chBox = v.findViewById(R.id.list_row_box);
            text  = v.findViewById(R.id.list_row_text);

            if (chBox.isChecked()) {
                Log.v(CONTACT_LIST, "Check box at index: " + i + " checked!");
                sqlHelper.deleteContact(text.getText().toString());
            }
            else
                Log.v(CONTACT_LIST, "Check box at index: " + i + " is not checked!");
        }

    }

    public void loadDataToListView(){
        ListView contactList = (ListView)getActivity().findViewById(R.id.contact_list_view);
        sqlHelper = new MySQLHelper(getContext());
        if(contactList != null) {
            ArrayList<String> itemList;
            itemList = sqlHelper.getAllContacts();
            Log.v(CONTACT_LIST, "itemList.size: " + itemList.size());

            CustomAdapter custom_adapter = new CustomAdapter(getContext(), R.layout.my_custom_view, itemList);
            contactList.setAdapter(custom_adapter);

            contactList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> list,
                                                View row,
                                                int index,
                                                long rowID) {
                            // code to run when user clicks that item
                            TextView name = (TextView) row.findViewById(R.id.list_row_text);

                            Log.v(CONTACT_LIST, "int index: " + index);
                            Log.v(CONTACT_LIST, "name: " + name.getText());
                            orientationHandler(name.getText().toString());
                        }
                    });
        }
    }

    public void orientationHandler(String name){

        if(this.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            Intent i = new Intent(getActivity(), ContactProfileActivity.class);
            i.putExtra("nameStr", name);
            Log.v(CONTACT_LIST, "New intent starting...");
            startActivity(i);
        }

        //Else,
        else if(this.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            ContactProfileFragment cpf = new ContactProfileFragment();

            FragmentManager fragmentManager = getActivity().getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, cpf);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


            ContactProfileFragment contactProfile = (ContactProfileFragment)getFragmentManager().findFragmentById(R.id.fragment_container);
            if(contactProfile != null)
                contactProfile.addContactDetails(name);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        loadDataToListView();
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
