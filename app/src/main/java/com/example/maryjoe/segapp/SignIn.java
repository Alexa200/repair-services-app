package com.example.maryjoe.segapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignIn extends AppCompatActivity implements View.OnClickListener {


    public static final String EXTRA_MESSAGE = "com.example.HomeRepairService.MESSAGE";

    private FirebaseAuth mAuth2;
    EditText editTextemail, editTextpassword;
    ProgressBar progBar;

    // used to determine if user is admin
    public static DatabaseReference mDatabase;
    public static FirebaseDatabase mFirebaseDatabase;
    public static FirebaseAuth mAuth;
    EditText usernameTextEdit;
    String userLoggingIn;

    Boolean adminFlag;

    public static UserInformation uInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        editTextemail = (EditText) findViewById(R.id.signInUsername);
        editTextpassword = (EditText) findViewById(R.id.signInPassword);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth2 = FirebaseAuth.getInstance();

        // initilizing what will be used to determine if user is admin
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();

        usernameTextEdit = (EditText) findViewById(R.id.signInUsername);
        userLoggingIn = usernameTextEdit.getText().toString();

        findViewById(R.id.btnSignIn).setOnClickListener(this);

        adminFlag = false;

        // this gets a snapshot of database at this time
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
            uInfo.setAccountType(ds.child(userLoggingIn).getValue(UserInformation.class).getAccountType());
        }
    }

    private void userLogin() {

        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextemail.setError("Email is needed");
            editTextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Enter correct email");
            editTextemail.requestFocus();
            return;

        }

        if(password.isEmpty()){
            editTextpassword.setError("Password is needed");
            editTextpassword.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextpassword.setError("Password should be more than six characters");
            editTextpassword.requestFocus();
            return;
        }

        progBar.setVisibility(View.VISIBLE);
        mAuth2.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Intent intent = new Intent(SignIn.this, WelcomePage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                userLogin();
                break;
        }
    }

    public void goToSignUp(View view){
        // opens a new activity when the sign up button is pushed
        Intent intent = new Intent(this, SignUp.class);
        EditText editText = (EditText) findViewById(R.id.signInUsername);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToWelcome(View view) {
        if (uInfo.getAccountType().equals("Admin")) {
            // opens a new activity when you sign up
            Intent intent = new Intent(this, AdminHome.class);
            startActivity(intent);
        } else {
            // opens a new activity when the sign in button is pushed
            Intent intent = new Intent(this, WelcomePage.class);
            EditText editText = (EditText) findViewById(R.id.signInUsername);
            String message = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }



}

