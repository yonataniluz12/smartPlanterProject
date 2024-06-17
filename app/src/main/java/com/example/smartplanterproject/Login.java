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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The type Login.
 */
public class Login extends AppCompatActivity {
    private NetworkStateReceiver networkStateReceiver;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


        networkStateReceiver = new NetworkStateReceiver();
        IntentFilter connectFilter = new IntentFilter();
        connectFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReceiver,connectFilter);

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(Login.this, MyPlanters.class));
            finish();}
    }

    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }

    /**
     * Log in.
     *
     * @param view the view
     */
    public void LogIn(View view) {
        EditText emailET = findViewById(R.id.editTextTextEmailAddress2);
        EditText passwordET = findViewById(R.id.editTextTextPassword2);
        mAuth.signInWithEmailAndPassword(emailET.getText().toString(),passwordET.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(Login.this,MyPlanters.class));
                            finish(); // Finish the current activity to prevent going back to it when pressing the back button
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this,"login failed Check the email or password ",Toast.LENGTH_SHORT).show();}
                    }
                });
    }
}