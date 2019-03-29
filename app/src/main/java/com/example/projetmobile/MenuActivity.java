package com.example.projetmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class MenuActivity extends AppCompatActivity {
    ViewFlipper v_flipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void pokemon_list_activity(View view){
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, MainActivity.class);

        // Start the new activity.
        startActivity(randomIntent);
    }
}


