package com.example.smartplanterproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBref {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference refUser = FBDB.getReference("user");
    public static  DatabaseReference refFlowerpot = FBDB.getReference("flowerPot");

}
