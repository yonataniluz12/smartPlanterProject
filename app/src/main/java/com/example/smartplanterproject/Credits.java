package com.example.smartplanterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;

/**
 * The type Credits.
 */
public class Credits extends AppCompatActivity implements View.OnCreateContextMenuListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.add("MyPlanters");

    }
    public boolean onContextItemSelected(MenuItem item) {
        String oper =item.getTitle().toString();
        if(oper.equals("MyPlanters")){finish();}
        return true;
    }
}