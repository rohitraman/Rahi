package com.example.roshan.rahiapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PinActivity extends AppCompatActivity {
    PinLockView lockView;
    PinLockListener listener;
    IndicatorDots dots;
    String encryptedValue;
    DatabaseReference reference;
    ValueEventListener listener1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        reference = FirebaseDatabase.getInstance().getReference();
        listener = new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                try {
                    encryptedValue = AESEncrypt.encrypt(pin);
                    listener1 = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                User user = snapshot.getValue(User.class);
                                if (user != null) {
                                    if (user.getEmail().equals(DBUser.user.getEmail())) {
                                        if (user.getPin().equals(encryptedValue)) {
                                            startActivity(new Intent(PinActivity.this, MainActivity.class));
                                            finish();
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };

                    reference.addValueEventListener(listener1);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onEmpty() {
                Log.i("empty", "Empty");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Log.i("Pin Change", intermediatePin + pinLength);
            }
        };

        dots = (IndicatorDots) findViewById(R.id.indicator);

        lockView = (PinLockView) findViewById(R.id.pin_lockview);
        lockView.attachIndicatorDots(dots);
        lockView.setPinLockListener(listener);

    }

}
