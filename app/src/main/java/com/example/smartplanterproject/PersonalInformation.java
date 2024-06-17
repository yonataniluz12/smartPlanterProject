package com.example.smartplanterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;

/**
 * The type Personal information.
 */
public class PersonalInformation extends AppCompatActivity {
    /**
     * The Id.
     */
    EditText id,
    /**
     * The Name.
     */
    name;
    /**
     * The Check.
     */
    String check;
    /**
     * The Planter.
     */
    Planter planter = new Planter();
    private FirebaseAuth mAuth;
    private NetworkStateReceiver networkStateReceiver;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        id = findViewById(R.id.editTextTextEmailAddress3);
        name = findViewById(R.id.editTextText2);


        networkStateReceiver = new NetworkStateReceiver();
        IntentFilter connectFilter = new IntentFilter();
        connectFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReceiver,connectFilter);
    }


    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }

    /**
     * Go to my planter.
     *
     * @param view the view
     */
    public void goToMyPlanter(View view) {
            if(id != null){checkEmailInFirebase(id.getText().toString());}
            else {Toast.makeText(PersonalInformation.this, "Please enter an email", Toast.LENGTH_SHORT).show();}
            User user = new User(name.getText().toString(),id.getText().toString());
            planter.setKeyid(id.getText().toString());
            FBref.refFlowerpot.child(id.getText().toString()).setValue(planter.getKeyid());
            FBref.refUser.child(id.getText().toString()).setValue(user);

            startActivity(new Intent(PersonalInformation.this,MyPlanters.class));

    }

    private void checkEmailInFirebase(String email) {
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.isSuccessful()) {
                            SignInMethodQueryResult result = task.getResult();
                            List<String> signInMethods = result.getSignInMethods();
                            // Check if the email is registered
                            if (signInMethods != null && signInMethods.size() > 0) {
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