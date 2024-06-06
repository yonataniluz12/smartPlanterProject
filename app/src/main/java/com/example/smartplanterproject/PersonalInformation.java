package com.example.smartplanterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The type Personal information.
 */
public class PersonalInformation extends AppCompatActivity {
    /**
     * The Id.
     */
    EditText id, /**
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
    private NetworkStateReceiver networkStateReceiver;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        id = findViewById(R.id.editTextTextEmailAddress3);
        name = findViewById(R.id.editTextText2);

    }
    public void onStop() {
        super.onStop();
        unregisterReceiver(networkStateReceiver);
    }
    @Override
    public void onStart() {
        super.onStart();
        IntentFilter connectFilter = new IntentFilter();
        connectFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReceiver,connectFilter);}
    /**
     * Go to my planter.
     *
     * @param view the view
     */
    public void goToMyPlanter(View view) {

            User user = new User(name.getText().toString(),id.getText().toString());
            planter.setKeyid(id.getText().toString());
            FBref.refFlowerpot.child(id.getText().toString()).setValue(planter.getKeyid());
            FBref.refUser.child(id.getText().toString()).setValue(user);
            startActivity(new Intent(PersonalInformation.this,MyPlanters.class));

    }
}