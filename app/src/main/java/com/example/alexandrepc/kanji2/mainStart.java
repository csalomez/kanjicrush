package com.example.alexandrepc.kanji2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by AlexandrePC on 29/01/2015.
 */
public class mainStart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    Button buttonsuiv = (Button) findViewById(R.id.imageButton);
    buttonsuiv.setOnClickListener(new View.OnClickListener() {

        @Override
        //Intent permettent d’envoyer et recevoir des messages
        public void onClick(View v) {
            Intent intent = new Intent(mainStart.this, mainChoice.class);
            startActivity(intent);
        }
    });




}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



}

