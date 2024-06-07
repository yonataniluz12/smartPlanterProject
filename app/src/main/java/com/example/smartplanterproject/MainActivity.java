package com.example.smartplanterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText  /**
     * The Email et.
     */
    emailET, /**
     * The Password et.
     */
    passwordET;
    /**
     * The Check data.
     */
    Boolean checkData = false;
    boolean isCreated = true;
    public NetworkStateReceiver networkStateReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        emailET = findViewById(R.id.editTextTextEmailAddress);
        passwordET = findViewById(R.id.editTextTextPassword);


        networkStateReceiver = new NetworkStateReceiver();
        IntentFilter connectFilter = new IntentFilter();
        connectFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReceiver,connectFilter);


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly. ;
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            startActivity(new Intent(MainActivity.this,PersonalInformation.class));
        }
    }
    public void onStop() {
        super.onStop();

    }
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }
    /**
     * Register.
     *
     * @param view the view
     */
    public void register(View view) {

        if (emailET != null && passwordET != null) {
        mAuth.createUserWithEmailAndPassword(emailET.getText().toString(),passwordET.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(MainActivity.this,PersonalInformation.class));
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"register failed ",Toast.LENGTH_SHORT).show();}
                    }
                });
        }
        else {
            Toast.makeText(MainActivity.this, "Email or password field is null", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Go to login.
     *
     * @param view the view
     */
    public void goToLogin(View view)
    {
        startActivity(new Intent(MainActivity.this,Login.class));
    }
}