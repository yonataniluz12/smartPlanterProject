package com.example.smartplanterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Personal information.
 */
public class PersonalInformation extends AppCompatActivity {
    private static final String TAG = "PersonalInformation";
    FirebaseUser fbUser;
    String Uid;
    EditText id, name;
    String check;
    ArrayList<Planter> planters;
    private FirebaseAuth mAuth;
    private NetworkStateReceiver networkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        id = findViewById(R.id.editTextTextEmailAddress3);
        name = findViewById(R.id.editTextText2);

        mAuth = FirebaseAuth.getInstance();
        fbUser = mAuth.getCurrentUser();
        networkStateReceiver = new NetworkStateReceiver();
        IntentFilter connectFilter = new IntentFilter();
        connectFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReceiver, connectFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }

    public void goToMyPlanter(View view) {
        if(id != null){checkEmailInFirebase(id.getText().toString());}
        else {Toast.makeText(PersonalInformation.this, "Please enter an email", Toast.LENGTH_SHORT).show();}

        //creating user out of not "sure" data
        Uid = fbUser.getUid();
        User user = new User(name.getText().toString(),Uid);
        planters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Planter p = new Planter();
            p.setUid(Uid);
            p.setSerNum(i);
            planters.add(p);
        }


        FBref.refFlowerpot.child(Uid).setValue(planters);
        FBref.refUser.child(Uid).setValue(user);

        startActivity(new Intent(PersonalInformation.this,MyPlanters.class));
    }

    private void checkEmailInFirebase(String email) {
        if(mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.isSuccessful()) {
                            SignInMethodQueryResult result = task.getResult();
                            List<String> signInMethods = result.getSignInMethods();
                            // Check if the email is registered
                            if (signInMethods != null && signInMethods.size() > 0) {
                                System.out.println("EXISTS");
                                // Email exists in Firebase Authentication
                                // Show toast message
                                Toast.makeText(PersonalInformation.this, "Email exists in Firebase Authentication", Toast.LENGTH_SHORT).show();
                            } else {
                                // Email does not exist in Firebase Authentication
                                // Show toast message
                                Toast.makeText(PersonalInformation.this, "Email does not exist in Firebase Authentication", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // An error occurred
                            // Show toast message with error
                            Toast.makeText(PersonalInformation.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
        });
    }
}