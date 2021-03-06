package com.example.maryjoe.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.maryjoe.segapp.SignIn.EXTRA_MESSAGE;

public class AdminAddUser extends AppCompatActivity {

    public static String serviceType, priceOfService;

    EditText nameOfService, priceofService;

    public static DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_user);

        database = FirebaseDatabase.getInstance().getReference();
    }

    public void addService(){
        String service = nameOfService.getText().toString().trim();
        String price = priceofService.getText().toString().trim();
        Float p = Float.parseFloat(price);

        if(service.isEmpty()){
            nameOfService.setError("Service name is needed");
            nameOfService.requestFocus();
            return;
        }

        if(price.isEmpty()){
            priceofService.setError("Price is needed");
            priceofService.requestFocus();
            return;
        }

        if(!(p instanceof Float) ){
            priceofService.setError("Incorrect input. Has to be a decimal number");
            priceofService.requestFocus();
            return;
        }


    }

    public void signOutButton(View v){
        Intent intent = new Intent(AdminAddUser.this, SignIn.class);
        startActivity(intent);
    }

    public void goBackHome(View v) {

        EditText editTextService = (EditText) findViewById(R.id.serviceName);
        serviceType = editTextService.getText().toString();

        EditText editTextPrice = (EditText) findViewById(R.id.price);
        priceOfService = editTextPrice.getText().toString();


        database.child("Services").child(serviceType);
        database.child("Services").child(serviceType).child("Price").setValue(priceOfService);

        Intent intent = new Intent(this, AdminHome.class);
        EditText editText = (EditText) findViewById(R.id.serviceName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.addService:
                addService();
                break;
        }
    }
}