package com.example.maryjoe.segapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);
    }

    public void editButton(View v) {
        Intent intent = new Intent(AdminHome.this, EditUser.class);
        startActivity(intent);
    }

    public void deleteButton(View v) {

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
