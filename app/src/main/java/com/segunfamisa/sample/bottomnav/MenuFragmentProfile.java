package com.segunfamisa.sample.bottomnav;


import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.segunfamisa.sample.bottomnav.activities.LoginActivity;


/**
 * Fragment class for each nav menu item
 */
public class MenuFragmentProfile extends Fragment implements View.OnClickListener{


    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_COLOR = "arg_color";

    private String mText;
    private int mColor;

    private View mContent;
    private TextView mTextView;

    private AppCompatButton logoutButton;

    private FirebaseAuth auth;
    private PackageInstaller.Session session;

    private TextView textD;

    public static Fragment newInstance(String text, int color) {
        Fragment frag = new MenuFragmentProfile();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //firebase user&auth

        auth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        logoutButton = (AppCompatButton) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(this);

        textD = (TextView) view.findViewById(R.id.textLOL);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // retrieve text and color from bundle or savedInstanceState
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            mText = args.getString(ARG_TEXT);
            mColor = args.getInt(ARG_COLOR);
        } else {
            mText = savedInstanceState.getString(ARG_TEXT);
            mColor = savedInstanceState.getInt(ARG_COLOR);
        }

        // initialize views
        mContent = view.findViewById(R.id.fragment_content);
        mTextView = (TextView) view.findViewById(R.id.text);

        // set text and background color
        mTextView.setText(mText);
        mContent.setBackgroundColor(mColor);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_TEXT, mText);
        outState.putInt(ARG_COLOR, mColor);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.logoutButton:
                logoutApp();
                break;
        }
    }

    private void logoutApp() {

    textD.setText("Bye Bye Burcu");

     FirebaseAuth.getInstance().signOut();

        startActivity(new Intent(getActivity(), LoginActivity.class));


    }
}
