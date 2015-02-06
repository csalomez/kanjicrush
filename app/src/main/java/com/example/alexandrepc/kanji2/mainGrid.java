package com.example.alexandrepc.kanji2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class mainGrid extends Activity {

    private LinkedList<Kanji> kanjiList;
    private LinkedList<String> listCarac;
    private LinkedList<String> listPhon;
    private LinkedList<String> listSens;

    private Kanji currentKanji;
    private int positionListPhonetique;
    private int positionListSens;

    private int score = 0;
    private TextView textView;

    private boolean kanjiIsSelected = false;

    //Affichage de la liste des caracteres
    private GridView mListCaractere = null;


    private HashMap<String, String> createKanji(String key, String name) {
        HashMap<String, String> kanji = new HashMap<String, String>();
        kanji.put(key, name);
        return kanji;
    }

    private void parseKanji(ArrayList al,DatabaseHelper db){
        String kanji = new String();
        String phonetique = new String();
        String sens = new String();
        int j = 0;

        for (int i=0; i<al.size();i++) {
            String[] string = (String[]) al.get(i); // Affiche la ligne i du fichier "Hiragana1.csv"
            String line = string[0];
            j = 0;
             while (j<line.length() && line.charAt(j) != ';'){
                kanji += line.charAt(j);
                j++;
             }
             j++;
            while (j<line.length() && line.charAt(j) != ';'){
                phonetique += line.charAt(j);
                j++;
            }
            j++;
            while (j<line.length()){
                sens += line.charAt(j);
                j++;
                Log.d("ERROR", sens);
            }

            Log.d("BLABLABLABLABLA","Kanji " + kanji + " phonetique " + phonetique + " sens " + sens);
            db.addKanji(new Kanji(kanji,phonetique,sens));
            kanji="";
            phonetique="";
            sens="";
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grid);

            currentKanji = new Kanji();
            positionListPhonetique = -1;
            positionListSens = -1;

            textView = (TextView) findViewById(R.id.textViewScore);


            textView.setText(Integer.toString(score));


            String csvFile = mainChoice.getLevel();
            ArrayList al = new ArrayList();


            try {

            CSVFile cs = new CSVFile(getAssets().open(csvFile));
            al = cs.read();
            Log.d("Chemin","yoloswag tamaman");
            String[] string = (String[]) al.get(0); // Affiche la ligne 2 du fichier "Hiragana1.csv"
            Log.d("COUILLE", string[0].substring(1));

              } catch (FileNotFoundException e) {
            e.printStackTrace();
              } catch (IOException e) {
            e.printStackTrace();
             }



            //Ajout d'élement dans la table*/
            DatabaseHelper db = new DatabaseHelper(this);

            db.onDelete();

            parseKanji(al,db);


            db.close();

            kanjiList = db.getAllKanjis();


            Plateau pl = new Plateau(kanjiList, 25, 1);

            listCarac = pl.getListCarac();
            listPhon = pl.getListPhonetique();
            listSens = pl.getListSens();
            kanjiList = pl.getListKanji();


            List<Map<String, String>> mapListPhon = new ArrayList<Map<String,String>>();

            for (int i =0; i< listPhon.size(); i++){
                if (!listPhon.get(i).equals("")) {
                    mapListPhon.add(createKanji("Kanji", listPhon.get(i)));
                }
            }

            ListView lv = (ListView) findViewById(R.id.list);
            SimpleAdapter simpleAdpt = new SimpleAdapter(this, mapListPhon, android.R.layout.simple_list_item_1, new String[] {"Kanji"}, new int[] {android.R.id.text1});
            lv.setAdapter(simpleAdpt);


             lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent,View v, int position, long id) {
                positionListPhonetique = position;
                Log.d("tttttt", Integer.toString(positionListPhonetique));
                Log.d("tttttt", currentKanji.getCaractere());
                if (!currentKanji.estNull()) {
                    Log.d("tttttt", listPhon.get(positionListPhonetique));

                    String phonOfGrid = currentKanji.getPhonetique();
                    String phonOfList = listPhon.get(positionListPhonetique);

                    if (phonOfGrid.compareTo(phonOfList) == 0) {
                        Toast.makeText(getBaseContext(), "Bonne association : " + currentKanji.getCaractere() + " se prononce " + phonOfList, Toast.LENGTH_SHORT).show();
                        score += 50;
                        textView.setText(Integer.toString(score));
                        positionListPhonetique = -1;
                        currentKanji.metNull();
                    } else {
                        Toast.makeText(getBaseContext(), "T'es nul !", Toast.LENGTH_SHORT).show();
                        positionListPhonetique = -1;
                        currentKanji.metNull();
                    }
                }
              }
            });

        Log.d("11","111");
            List<Map<String, String>> mapListSens = new ArrayList<Map<String,String>>();

             for (int z =0; z< listSens.size(); z++){
                 if (!listSens.get(z).equals("")) {
                    mapListSens.add(createKanji("Kanji", listSens.get(z)));
                }
            }

             ListView lv2 = (ListView) findViewById(R.id.listView2);
             SimpleAdapter simpleAdpt2 = new SimpleAdapter(this, mapListSens, android.R.layout.simple_list_item_1, new String[] {"Kanji"}, new int[] {android.R.id.text1});
             lv2.setAdapter(simpleAdpt2);

            lv2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent,View v, int position, long id) {
                positionListSens = position;

                if (!currentKanji.estNull()) {
                    String sensOfGrid = currentKanji.getSens();
                    String sensOfList = listSens.get(positionListSens);

                    Log.d("grilll", sensOfGrid);
                    Log.d("lissss", sensOfList);

                    if (sensOfGrid.compareTo(sensOfList) == 0) {
                        Toast.makeText(getBaseContext(), "Bonne association : " + currentKanji.getCaractere() + " signifie " + sensOfList, Toast.LENGTH_SHORT).show();
                        score += 50;
                        textView.setText(Integer.toString(score));
                        positionListSens = -1;
                        currentKanji.metNull();
                    } else {
                        Toast.makeText(getBaseContext(), "T'es nul !", Toast.LENGTH_SHORT).show();
                        positionListSens = -1;
                        currentKanji.metNull();
                    }
                }
            }
          });



        Log.d("11","111");

            mListCaractere = (GridView) findViewById(R.id.Grid);

            mListCaractere.setAdapter(new ButtonAdapter(this));
            //Que faire quand on clique sur un élément de la liste ? Gestion du clic
            mListCaractere.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                //modifier la fonctionnement de cette fonction l'avenir
                public void onItemClick(AdapterView<?> parent,
                                        View v, int position, long id){
                    Toast.makeText(getBaseContext(),
                            "Kanji" + (position + 1) + " Séléctionné !!!!",
                            Toast.LENGTH_SHORT).show();
                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Un adaptateur est le conteneur des informations d'une liste, au contraire de l'AdapterView,
    // qui affiche les informations et régit ses interactions avec l'utilisateur.
    // C'est donc dans l'adaptateur que se trouve la structure de données qui détermine comment
    // sont rangées les données. Ainsi, dans notre adaptateur se trouvera une liste de contacts
    // sous forme de ArrayList.

    //Dès qu'une classe hérite de BaseAdapter, il faut implémenter obligatoirement trois méthodes
    //ci-dessous obligatoirement

    public class ButtonAdapter extends BaseAdapter {
        //position est la position de l'item dans dans l'adaptateur

        private Context context;
        public ButtonAdapter(Context c){

            context = c;
        }
        public int getCount() {

            return listCarac.size();
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }

        //View getView(int position, View convertView, ViewGroup parent) est la plus délicate à utiliser.
        //En fait, cette méthode est appelée à chaque fois qu'un item est affiché à l'écran
        public View getView(int position, View convertView, ViewGroup parent) {
            final Button btn;
            if (convertView == null) {
                //btn = (Button) findViewById(R.id.GridButton);
                btn = new Button(context);
                btn.setLayoutParams(new GridView.LayoutParams(180, 180));
                btn.setPadding(0, 0, 0, 0);
                btn.setWidth(100);
                btn.setHeight(100);
                btn.setFocusable(false);
                btn.setClickable(false);


            } else {
                btn = (Button) convertView;
            }
            btn.setText(listCarac.get(position));
            btn.setBackgroundColor(Color.rgb(0, 0, 0));
            btn.setAlpha((float) 0.7);
            btn.setTextColor(Color.rgb(255, 226, 182));
            btn.setTextSize((float)26);
            //btn.setTextColor(Color.BLACK);
            btn.setId(position);


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn.setBackgroundColor(Color.rgb(0, 14, 255));

                    Log.d("wtf", btn.getText().toString());

                    currentKanji = findKanji(kanjiList, btn.getText().toString());

                    Log.d("r", Integer.toString(positionListPhonetique));
                    Log.d("r", currentKanji.getCaractere());

                    if (positionListPhonetique != -1) {
                        String phonOfGrid = currentKanji.getPhonetique();
                        String phonOfList = listPhon.get(positionListPhonetique);

                        if (phonOfGrid.compareTo(phonOfList) == 0) {
                            Toast.makeText(getBaseContext(), "Bonne association : " + currentKanji.getCaractere() + " se prononce " + phonOfList, Toast.LENGTH_SHORT).show();
                            score += 50;
                            textView.setText(Integer.toString(score));
                            positionListPhonetique = -1;
                            currentKanji.metNull();
                            btn.setBackgroundColor(Color.rgb(255, 255, 255));
                        } else {
                            Toast.makeText(getBaseContext(), "T'es nul !", Toast.LENGTH_SHORT).show();
                            positionListPhonetique = -1;
                            currentKanji.metNull();
                            btn.setBackgroundColor(Color.rgb(0, 0, 0));
                        }
                     }
                }
            });

            return btn;


        }


    }


    public Kanji findKanji (LinkedList<Kanji> kanjiList, String caractere) {
        int i = 0;
        int j = 0;
        int n = kanjiList.size();
        Log.d("nnnnnnnnnnn", Integer.toString(n));
        while (i < n) {
            Log.d("iii", "i : "+i+"---- "+ kanjiList.get(i).getCaractere());
            if (caractere.compareTo(kanjiList.get(i).getCaractere()) == 0) {
                Log.d("COMPARETO dans le while", caractere + " ----- " + kanjiList.get(i).getCaractere());
                j = i;
            }
            i++;
        }
        Log.d("jjjjjjjjjjjjjjjj", Integer.toString(j)+"°°°°°°°°°°°° "+Integer.toString(i));

        Log.d("COMPARETO", caractere + " ----- " + kanjiList.get(j).getCaractere());
        if (caractere.compareTo(kanjiList.get(j).getCaractere()) == 0)
            return new Kanji(kanjiList.get(j));
        else {
            Log.d("Erreur", caractere + " non trouvé");
            return new Kanji();
        }


    }







}