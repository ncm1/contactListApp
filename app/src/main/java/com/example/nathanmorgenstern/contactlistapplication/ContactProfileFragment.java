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
    private String mParam1;
    private String mParam2;
    private String nameStr;

    private OnFragmentInteractionListener mListener;

    public ContactProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactProfileFragment newInstance(String param1, String param2) {
        ContactProfileFragment fragment = new ContactProfileFragment();
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

        Intent i = getActivity().getIntent();
        nameStr = i.getStringExtra("nameStr");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_profile, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        TextView rightSide = (TextView)getActivity().findViewById(R.id.right_side_toolbar);

        if(rightSide != null)
            rightSide.setText("Contact Profile");
        if(nameStr != null)
            portraitMode();
    }

    public void portraitMode(){
        TextView displayName = (TextView)getActivity().findViewById(R.id.contact_profile_name);

        Log.v(CONTACT_PROFILE, "On Create Display name: " + nameStr);
        displayName.setText(nameStr);
        MySQLHelper sqlHelper = new MySQLHelper(getContext());

        String phoneNmbr;
        phoneNmbr = sqlHelper.getContactPhone(nameStr);
        Log.v(CONTACT_PROFILE, "Display phone: " + phoneNmbr);
        TextView displayPhone = (TextView)getActivity().findViewById(R.id.contact_profile_phone_number);
        displayPhone.setText(phoneNmbr);
    }

    public void addContactDetails(String name){
        nameStr = name;
        TextView displayName = (TextView)getActivity().findViewById(R.id.contact_profile_name);
        Log.v(CONTACT_PROFILE, "Add Contact Details Display name: " + nameStr);
        displayName.setText(nameStr);

        MySQLHelper sqlHelper = new MySQLHelper(getContext());

        String phoneNmbr;
        phoneNmbr = sqlHelper.getContactPhone(nameStr);
        TextView displayPhone = (TextView)getActivity().findViewById(R.id.contact_profile_phone_number);
        displayPhone.setText(phoneNmbr);
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
