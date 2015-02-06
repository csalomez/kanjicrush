package com.example.alexandrepc.kanji2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by AlexandrePC on 30/01/2015.
 */
public class mainChoice extends Activity {

    private String[] commandArray = new String[]{"Mode 1"/*, "Mode 2", "Mode 3"*/};
    private static String level = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choice);
        Button buttonLv1 = (Button) findViewById(R.id.imageButton1);
        buttonLv1.setOnClickListener(new View.OnClickListener() {

            @Override
            //Intent permettent d’envoyer et recevoir des messages
            /*public void onClick(View v) {
                Intent intent = new Intent(mainChoice.this, mainGrid.class);
                startActivity(intent);
            }
        });*/


            public void onClick(View arg0) {
                showDialog();
                level = "Hiragana1.csv";
            }


        });

        Button buttonLv2 = (Button) findViewById(R.id.imageButton2);
        buttonLv2.setOnClickListener(new View.OnClickListener() {

            @Override
            //Intent permettent d’envoyer et recevoir des messages
            /*public void onClick(View v) {
                Intent intent = new Intent(mainChoice.this, mainGrid.class);
                startActivity(intent);
            }
        });*/


            public void onClick(View arg0) {
                showDialog();
                level = "Hiragana2.csv";
            }


        });

        Button buttonLv3 = (Button) findViewById(R.id.imageButton3);
        buttonLv3.setOnClickListener(new View.OnClickListener() {

            @Override
            //Intent permettent d’envoyer et recevoir des messages
            /*public void onClick(View v) {
                Intent intent = new Intent(mainChoice.this, mainGrid.class);
                startActivity(intent);
            }
        });*/


            public void onClick(View arg0) {
                showDialog();
                level = "Hiragana3.csv";
            }


        });

        Button buttonLv4 = (Button) findViewById(R.id.imageButton5);
        buttonLv4.setOnClickListener(new View.OnClickListener() {

            @Override
            //Intent permettent d’envoyer et recevoir des messages
            /*public void onClick(View v) {
                Intent intent = new Intent(mainChoice.this, mainGrid.class);
                startActivity(intent);
            }
        });*/


            public void onClick(View arg0) {
                showDialog();
                level = "Kanji1.csv";
            }


        });


        Button back = (Button) findViewById(R.id.imageButton4);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            //Intent permettent d’envoyer et recevoir des messages
            /*public void onClick(View v) {
                Intent intent = new Intent(mainChoice.this, mainGrid.class);
                startActivity(intent);
            }
        });*/


            public void onClick(View arg0) {
                Intent intent = new Intent(mainChoice.this, mainStart.class);
                startActivity(intent);
            }


        });


    }

    public static String getLevel(){
        return level;
    }


    private void showDialog() {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a mode");
        builder.setItems(commandArray, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                /*changer ça pour rediriger vers le bon chemin*/
                /*Toast.makeText(mainChoice.this,
                        commandArray[which] + " Selected", Toast.LENGTH_LONG)
                        .show();
                dialog.dismiss();*/
                Intent intent = new Intent(mainChoice.this, mainGrid.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Back",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
