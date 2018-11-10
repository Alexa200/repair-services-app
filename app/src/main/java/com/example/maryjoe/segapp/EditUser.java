package com.example.maryjoe.segapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;
import java.util.ArrayList;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class EditUser extends AppCompatActivity {

    public static DatabaseReference mDatabase;
    public static FirebaseAuth.AuthStateListener mAuthListener;
    public static FirebaseDatabase mFirebaseDatabase;
    public static FirebaseAuth mAuth;

    public EditText userToEdit;
    public String username;

    public ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();

        mListView = (ListView) findViewById(R.id.listview);

        userToEdit = (EditText) findViewById(R.id.enterUsername);
        username =  userToEdit.getText().toString();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds: dataSnapshot.getChildren()) {
            UserInformation uInfo = new UserInformation();
            uInfo.setName(ds.child(username).getValue(UserInformation.class).getName());
            uInfo.setName(ds.child(username).getValue(UserInformation.class).getAccountType());
            uInfo.setName(ds.child(username).getValue(UserInformation.class).getEmail());
            uInfo.setName(ds.child(username).getValue(UserInformation.class).getService());
            uInfo.setName(ds.child(username).getValue(UserInformation.class).getPrice());

            ArrayList<String> usersValues = new ArrayList<>();
            usersValues.add(uInfo.getName());
            usersValues.add(uInfo.getAccountType());
            usersValues.add(uInfo.getEmail());
            usersValues.add(uInfo.getService());
            usersValues.add(uInfo.getPrice());

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, usersValues);
            mListView.setAdapter(adapter);
        }
    }


}
