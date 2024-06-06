package com.example.smartplanterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
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
    String [] flowerBoxes = {"planter1","planter2","planter3"};
    /**
     * The Lv.
     */
    ListView lv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_planters);
        lv = findViewById(R.id.listView);
        lv.setOnItemClickListener(this);
        ArrayAdapter<String> adp = new ArrayAdapter<>(this,R.layout.activity_view_planter,flowerBoxes);
        lv.setAdapter(adp);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        startActivity(new Intent(MyPlanters.this,ViewPlanter.class));
    }

    /**
     * Add planter.
     *
     * @param view the view
     */
    public void addPlanter(View view) {

    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.add("credits");

    }
    public boolean onContextItemSelected(MenuItem item) {
        String oper=item.getTitle().toString();
        if(oper.equals("credits")){startActivity(new Intent(MyPlanters.this,Credits.class));}
        return true;
    }
}