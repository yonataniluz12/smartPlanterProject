package com.example.smartplanterproject;

import static android.content.ContentValues.TAG;
import static com.example.smartplanterproject.FBref.refFlowerpot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

/**
 * The type View planter.
 */
public class ViewPlanter extends AppCompatActivity {
    /**
     * The Temp.
     */
    TextView temp,
    /**
     * The Humidity.
     */
    humidity,
    /**
     * The Sun light.
     */
    sunLight;
    /**
     * The Fan.
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch fan;
    /**
     * The Water.
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch water;
    /**
     * The Str.
     */
    String str;

    /**
     * The Temp chek.
     *
     * @author yonatan iluz@
     */
    int tempChek = -1;
    /**
     * The V.
     */
    ImageView iV;
    private int serNum;
    private NetworkStateReceiver networkStateReceiver;
    /**
     * The Images ref.
     */
    public StorageReference imagesRef;
    FirebaseUser fbUser;
    String Uid;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_planter);

        temp = findViewById(R.id.textView4);
        humidity = findViewById(R.id.textView5);
        sunLight = findViewById(R.id.textView6);
        fan = findViewById(R.id.switch1);
        water = findViewById(R.id.switch2);
        iV = findViewById(R.id.imageView);
        Intent gi = getIntent();
        serNum = gi.getIntExtra("planterNum",-1);
        mAuth = FirebaseAuth.getInstance();
        fbUser = mAuth.getCurrentUser();
        Uid = fbUser.getUid();
        networkStateReceiver = new NetworkStateReceiver();
        IntentFilter connectFilter = new IntentFilter();
        connectFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReceiver,connectFilter);


        ValueEventListener stuListener = new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("LISTENER");

                Log.d(TAG, "onDataChange triggered");

                for (DataSnapshot data : snapshot.getChildren()) {
                    str = (String) data.getKey();
                    Planter stuTmp = data.getValue(Planter.class);
                    if(stuTmp != null){
                        if(stuTmp.getTemp() != null) {
                            temp.setText("temperature: " + stuTmp.getTemp());
                            tempChek = Integer.parseInt(stuTmp.getTemp());
                        }
                        sunLight.setText("Sunlight: " + stuTmp.getIsSunLightSensor());
                        humidity.setText("humidity: " + stuTmp.getAirSensor());
                    }

                }
                if (tempChek > 25) { //its not checking the value of the temperature.
                    NotificationHelper.showNotification(ViewPlanter.this, "It is very hot, the fuel is ventilated for the seedling");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("CANCELED 2");
            }
        };

        System.out.println("REF"+ refFlowerpot);
        refFlowerpot.child(Uid).child(""+serNum).addValueEventListener(stuListener);
}


    public void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);

    }

    /**
     * Watering.
     *
     * @param view the view
     */
    public void watering(View view) {
                if (water.isChecked()) {
                    refFlowerpot.child("bereathWater").setValue(true);
                    Toast.makeText(ViewPlanter.this,"Watering",Toast.LENGTH_LONG).show();
                } else {
                    refFlowerpot.child("bereathWater").setValue(false);
                    Toast.makeText(ViewPlanter.this,"Watering stop",Toast.LENGTH_LONG).show();
                }
            }

    /**
     * Colding.
     *
     * @param view the view
     */
    public void Colding(View view) {
                if (fan.isChecked()) {
                    refFlowerpot.child("fan").setValue(true);
                    Toast.makeText(ViewPlanter.this,"Fan on",Toast.LENGTH_LONG).show();
                } else {
                    refFlowerpot.child("fan").setValue(false);
                    Toast.makeText(ViewPlanter.this,"Fan off",Toast.LENGTH_LONG).show();
                }
            }

    /**
     * See plant.
     *
     * @param view the view
     */
    public void seePlant(View view)
    {
        if(imagesRef != null){
            imagesRef.getBytes(1920*1080).addOnSuccessListener(bytes -> {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                iV.setImageBitmap(bitmap);
            }).addOnFailureListener(e -> {
                Toast.makeText(ViewPlanter.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
            })
            ;
        }
        else {Toast.makeText(ViewPlanter.this,"the image not upload",Toast.LENGTH_LONG).show();}

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Credits){startActivity(new Intent(ViewPlanter.this,Credits.class));}
        return true;
    }
}
