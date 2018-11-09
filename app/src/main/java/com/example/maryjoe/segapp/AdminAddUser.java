package com.example.maryjoe.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.maryjoe.segapp.SignIn.EXTRA_MESSAGE;

public class AdminAddUser extends AppCompatActivity {

    public static String accountType, nameOfUser, emailOfUser, usernameOfUser, passwordOfUser, serviceType, priceOfService;

    public static DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_user);

        database = FirebaseDatabase.getInstance().getReference();
    }

    public void signOutButton(View v){
        Intent intent = new Intent(AdminAddUser.this, SignIn.class);
        startActivity(intent);
    }

    public void homeOwnerClick(View view) {
        accountType = "Homeowner";

        EditText editTextName = (EditText) findViewById(R.id.nameTextField);
        nameOfUser = editTextName.getText().toString();

        database.child("Homeowner").child(nameOfUser);

        EditText editTextEmail = (EditText) findViewById(R.id.emailTextField);
        emailOfUser = editTextEmail.getText().toString();

        database.child("Homeowner").child(nameOfUser).child("Email").setValue(emailOfUser);

        EditText editTextUserName = (EditText) findViewById(R.id.usernameTextField);
        usernameOfUser = editTextUserName.getText().toString();

        database.child("Homeowner").child(nameOfUser).child("Username").setValue(usernameOfUser);

        EditText editTextPassword = (EditText) findViewById(R.id.passwordTextField);
        passwordOfUser = editTextPassword.getText().toString();

        database.child("Homeowner").child(nameOfUser).child("Password").setValue(passwordOfUser);
    }

    public void serviceProviderClick(View view) {
        accountType = "Service Provider";

        LinearLayout serviceProLay = (LinearLayout) findViewById(R.id.serviceProviderLayout);
        serviceProLay.setVisibility(View.VISIBLE);

        EditText editTextName = (EditText) findViewById(R.id.nameTextField);
        nameOfUser = editTextName.getText().toString();

        database.child("Service Provider").child(nameOfUser);

        EditText editTextEmail = (EditText) findViewById(R.id.emailTextField);
        emailOfUser = editTextEmail.getText().toString();

        database.child("Homeowner").child(nameOfUser).child("Email").setValue(emailOfUser);

        EditText editTextUserName = (EditText) findViewById(R.id.usernameTextField);
        usernameOfUser = editTextUserName.getText().toString();

        database.child("Homeowner").child(nameOfUser).child("Username").setValue(usernameOfUser);

        EditText editTextPassword = (EditText) findViewById(R.id.passwordTextField);
        passwordOfUser = editTextPassword.getText().toString();

        database.child("Homeowner").child(nameOfUser).child("Password").setValue(passwordOfUser);

        EditText editTextService = (EditText) findViewById(R.id.serviceTypeField);
        serviceType = editTextPassword.getText().toString();

        database.child("Homeowner").child(nameOfUser).child("Service Type").setValue(serviceType);

        EditText editTextPrice = (EditText) findViewById(R.id.priceField);
        priceOfService = editTextPassword.getText().toString();

        database.child("Homeowner").child(nameOfUser).child("Price of Service").setValue(priceOfService);
    }

    public void goBackHome(View v) {
        switch(v.getId()) {
            case R.id.addUserButton:
                // calling authentication from original Sign Up
                SignUp validation = new SignUp();
                validation.registerUser();
                break;
        }
        Intent intent = new Intent(this, AdminHome.class);
        EditText editText = (EditText) findViewById(R.id.nameEditText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
