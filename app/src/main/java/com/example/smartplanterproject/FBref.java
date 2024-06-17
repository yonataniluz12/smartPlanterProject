package com.example.smartplanterproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * The type F bref.
 */
public class FBref {
    /**
     * The constant FBDB.
     */
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    /**
     * The constant refUser.
     */
    public static DatabaseReference refUser = FBDB.getReference("user");
    /**
     * The constant refFlowerpot.
     */
    public static  DatabaseReference refFlowerpot = FBDB.getReference("flowerPot");

}
