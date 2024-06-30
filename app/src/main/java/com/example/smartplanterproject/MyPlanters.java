package com.example.smartplanterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * The type My planters.
 */
public class MyPlanters extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnCreateContextMenuListener{
    /**
     * The Flower boxes.
     */
    private static final String TAG = "MyPlanters";
    String[] flowerBoxes = {"planter1", "planter2", "planter3"};
    ListView lv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_planters);
        lv = findViewById(R.id.listView);
        lv.setOnItemClickListener(this);
        ArrayAdapter<String> adp = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, flowerBoxes);
        lv.setAdapter(adp);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(MyPlanters.this, ViewPlanter.class);
        intent.putExtra("planterNum",i);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Credits) {
            startActivity(new Intent(MyPlanters.this, Credits.class));
        }
        return true;
    }
}