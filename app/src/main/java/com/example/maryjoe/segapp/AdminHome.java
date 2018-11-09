package com.example.maryjoe.segapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHome extends AppCompatActivity {

    public static DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);
        database = FirebaseDatabase.getInstance().getReference();
    }

    public void addUserButton(View v){
        Intent intent = new Intent(AdminHome.this, AdminAddUser.class);
        startActivity(intent);
    }

    public void signOutButton(View v) {
        Intent intent = new Intent(AdminHome.this, SignIn.class);
        startActivity(intent);
    }

}
