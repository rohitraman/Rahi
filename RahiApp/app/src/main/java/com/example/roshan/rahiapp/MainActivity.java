package com.example.roshan.rahiapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button LogoutBtn,GetAvailabilityButton,GetRouteButton,GetPNRButton,GetAllTrainsButton,GetTrainButton,GetFareButton,GetTrainsOnButton,GetStatusButton;

    private ProgressDialog mProgress;

    private FirebaseAuth mAuth;

    private Session session;

    private void logout()
    {
        mAuth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogoutBtn = (Button)findViewById(R.id.btn_logout);

        GetAvailabilityButton = (Button) findViewById(R.id.btn_availability);

        GetPNRButton = (Button) findViewById(R.id.btn_pnr);

        GetRouteButton = (Button) findViewById(R.id.btn_route);

        GetAllTrainsButton = (Button)findViewById(R.id.btn_gettrains);

        GetTrainButton = (Button) findViewById(R.id.btn_get_train_details);

        GetFareButton = (Button) findViewById(R.id.btn_fare_1);

        GetTrainsOnButton = (Button) findViewById(R.id.btn_get_trains_on);

        GetStatusButton = (Button) findViewById(R.id.btn_status);

        mAuth = FirebaseAuth.getInstance();

        session = new Session(this);

        mProgress = new ProgressDialog(MainActivity.this);

        if (!session.loggedIn())
        {
            logout();
        }

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mProgress.setTitle("Logging Out...");
                mProgress.setMessage("Please wait while we logout...");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();

                mAuth.signOut();

                mProgress.dismiss();

                session.setLoggedIn(false);
                logout();
            }
        });

        GetAvailabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GetAvailabilityActivity.class));
                finish();
            }
        });

        GetPNRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GetPNRActivity.class));
                finish();
            }
        });

        GetRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GetRouteActivity.class));
                finish();
            }
        });

        GetAllTrainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GetAllTrainsActivity.class));
                finish();
            }
        });

        GetTrainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GetTrainActivity.class));
                finish();
            }
        });

        GetFareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GetFareActivity.class));
                finish();
            }
        });

        GetTrainsOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GetTrainsOnActivity.class));
                finish();
            }
        });

        GetStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GetStatusActivity.class));
                finish();
            }
        });
    }
}