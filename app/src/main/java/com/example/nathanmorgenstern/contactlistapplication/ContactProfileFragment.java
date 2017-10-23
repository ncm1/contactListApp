package com.example.nathanmorgenstern.contactlistapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import static android.R.attr.name;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String CONTACT_PROFILE = "CONTACT_PROFILE";

    // TODO: Rename and change types of parameters
    private String nameStr;
    private String instanceStr;

    private OnFragmentInteractionListener mListener;

    public ContactProfileFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ContactProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactProfileFragment newInstance(String param1, String param2) {
        ContactProfileFragment fragment = new ContactProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Log.v(CONTACT_PROFILE, "mParam1: " + param1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nameStr = getArguments().getString(ARG_PARAM1);
            instanceStr = getArguments().getString(ARG_PARAM2);
            Log.v(CONTACT_PROFILE, "onCreate nameStr: " + nameStr);
        }
        else {
            nameStr = "";
            instanceStr = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if(!instanceStr.equals("fromInstance")) {
            Intent i = getActivity().getIntent();
            nameStr = i.getStringExtra("nameStr");
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_profile, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        ContactListFragment clf = (ContactListFragment)getFragmentManager().findFragmentById(R.id.contact_list_fragment);
        Button addButtonContactList = (Button)getActivity().findViewById(R.id.add_button);
        if (this.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE && clf == null && addButtonContactList == null) {
            Intent i = new Intent(getActivity(), MainActivity.class);
            i.putExtra("activityCalled",CONTACT_PROFILE);
            i.putExtra("nameStr", nameStr);
            startActivity(i);
        }

        TextView rightSide = (TextView)getActivity().findViewById(R.id.right_side_toolbar);

        if(rightSide != null)
            rightSide.setText("Contact Profile");
        if(nameStr != null)
            setDisplay();
        else if(rightSide != null){
            try {
                nameStr = getArguments().getString(ARG_PARAM1);
                setDisplay();
            }catch (Exception e){};
        }

    }

    public void setDisplay(){
        TextView displayName = (TextView)getActivity().findViewById(R.id.contact_profile_name);

        Log.v(CONTACT_PROFILE, "On Create Display name: " + nameStr);
        if(!nameStr.equals("") && nameStr != null && displayName != null) {
            displayName.setText(nameStr);
            MySQLHelper sqlHelper = new MySQLHelper(getContext());

            String phoneNmbr = "";
            try {
                phoneNmbr = sqlHelper.getContactPhone(nameStr);
            }catch(Exception e){blankify();};

            Log.v(CONTACT_PROFILE, "Display phone: " + phoneNmbr);
            TextView displayPhone = (TextView) getActivity().findViewById(R.id.contact_profile_phone_number);
            displayPhone.setText(phoneNmbr);
        }
    }

    public void blankify(){
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(getActivity().getFragmentManager().findFragmentById(R.id.fragment_container));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        TextView rightSide = (TextView)getActivity().findViewById(R.id.right_side_toolbar);
        if(rightSide != null)
            rightSide.setText("");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
