package com.example.maryjoe.segapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminHome extends AppCompatActivity {

    public static DatabaseReference database;

    FileInputStream serviceAccount = new FileInputStream("app/segappauth-firebase-adminsdk-8uch5-b576facd05.json");

    FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://segappauth.firebaseio.com")
            .build();

    FirebaseApp.initializeApp(options);

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

    public void displayUsers(){

        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                System.out.println("User: " + user.getUid());
            }
            page = page.getNextPage();
        }

        page = FirebaseAuth.getInstance().listUsers(null);
        for (ExportedUserRecord user : page.iterateAll()) {
            System.out.println("User: " + user.getUid());
        }
    }

}
