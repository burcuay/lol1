package com.segunfamisa.sample.bottomnav.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.segunfamisa.sample.bottomnav.Home;
import com.segunfamisa.sample.bottomnav.R;

public class MainActivity extends AppCompatActivity  {
    private final AppCompatActivity activity = MainActivity.this;

    private  FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_menu);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authStateListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (user == null){

                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();

                }
                else{

                    startActivity(new Intent(MainActivity.this, Home.class));
                    finish();
                }
            }
        };


//        initViews();
//        initListeners();
//        initObjects();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }


}
