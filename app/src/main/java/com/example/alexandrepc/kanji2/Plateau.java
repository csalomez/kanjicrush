package com.example.alexandrepc.kanji2;

/**
 * Created by AlexandrePC on 28/01/2015.
 */

import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;



class Plateau {

    private int mode;

    private LinkedList<Kanji> listeKanjiConstante; //Pas modifiée après qu'elle soit remplie
    private LinkedList<Kanji> listeKanjiRestant; // Vidée au fur et à mesure qu'on utilise les kanji.

    private LinkedList<Kanji> listKanji; //Correspond à la grille (truc affiché).
    private LinkedList<String> listCarac; //Pour tout i, listCarac(i) = listKanji(i).getCaractere()
    private LinkedList<String> listSens; //Liste de sens plus certains faux (à finir : il manque  rajouter les faux + mélange)
    private LinkedList<String> listPhonetique; //Liste de phonetique plus certains faux (à finir : il manque  rajouter les faux + mélange)

    public Plateau(LinkedList<Kanji> listAllKanji, int nbElements, int mode) {

        this.listeKanjiConstante = new LinkedList<Kanji>(listAllKanji);
        this.listeKanjiRestant = new LinkedList<Kanji>(listAllKanji);


        reductionListe(nbElements);//Liste de kanjis aléatoire pour remplir à 1/3 plus les kanjis vides pour remplir


        this.mode = mode;

        remplieListCarac();
        remplieListeSens();
        remplieListePhonetique();


    }

    public static int minVal (int a, int b){
        if (a<b)
            return a;
        else
            return b;
    }



    /*METHODES DANS LE CONSTRUCTEUR                              */
    public void reductionListe(int nbElements) {
    //Prend la liste totale des kanji et en sort une liste de hauteur*largeur/3 selectionné au hasard plus des kanjis vide pour remplir la grille (taille nbElements)
        listKanji = new LinkedList<Kanji>();
        int n = nbElements / 2;
        double i;
        int m = listeKanjiConstante.size();

        int min = minVal(n,m);

        while (min > 0) {
            i = Math.random() * (double) listeKanjiRestant.size();
            listKanji.add(listeKanjiRestant.get((int) i));
            listeKanjiRestant.remove((int) i);
            min--;
        }

        int currentLength = listKanji.size();

        while (currentLength < nbElements ) {
            listKanji.addFirst(new Kanji());
            currentLength++;
        }
    }

    public void remplieListCarac(){
        listCarac = new  LinkedList<String>();

        for (int i = 0; i < listKanji.size(); i++) {
            listCarac.add(listKanji.get(i).getCaractere());
        }
    }

    public void remplieListeSens() {
        listSens = new LinkedList<String>();
        int i = 0;

        while(i < listKanji.size()){
            if (listKanji.get(i).estNull() == false) {
                listSens.add(listKanji.get(i).getSens());
            }
            i++;
        }
    }

    public void remplieListePhonetique() {
        listPhonetique = new LinkedList<String>();
        int i = 0;

        while(i < listKanji.size()){
            if (listKanji.get(i).estNull() == false) {
                listPhonetique.add(listKanji.get(i).getPhonetique());
            }
            i++;
        }

    }


    /* METHODES DE CLASSE*/

    public boolean grilleVide() {
        int i = 0;
        while(i<listKanji.size() && listKanji.get(i).estNull()){
            i++;
        }
        return (i== listKanji.size());
    }

    public boolean grillePleine() {
        //Renvoie vrai si tous les caractères de la grille ne sont pas vides
        int i = 0;
        while(i<listKanji.size() && listKanji.get(i).estNull()){
            i++;
        }
        return (i== listKanji.size());
    }


    /* GETTERS & SETTERS                                                                         */

    public LinkedList<Kanji> getListKanji(){
        return listKanji;
    }
    public LinkedList<String> getListCarac(){
        return listCarac;
    }

    public LinkedList<String> getListSens(){
        return listSens;
    }

    public LinkedList<String> getListPhonetique(){
        return listPhonetique;
    }

    /*
        public void supprimerKanji(int i, int j){
            g.supprimerKanji(i,j);
        }

        public boolean correspondSens(int i,int j,String sens){
            return g.correspondSens(i,j,sens);
        }

        public boolean correspondPhonetique(int i,int j,String phonetique){
            return g.correspondPhonetique(i,j,phonetique);
        }


   public void affiche() {
        this.g.affiche();

        switch (mode) {
            case 1:

                Log.d("", "-----------------------------");

                for (int i = 0; i < listeSens.size(); i++) {
                    Log.d("", "Élément à l'index " + i + " = " + listeSens.get(i));
              }
               break;

            case 2:

                Log.d("", "-----------------------------");
                for (int i = 0; i < listePhonetique.size(); i++) {
                   Log.d("", "Élément à l'index " + i + " = " + listePhonetique.get(i));
                }
                break;

            case 3:
               Log.d("", "-----------------------------");

                for (int i = 0; i < listeSens.size(); i++) {
                    Log.d("", "Élément à l'index " + i + " = " + listeSens.get(i));
                }

                Log.d("", "-----------------------------");
                for (int i = 0; i < listePhonetique.size(); i++) {
                   Log.d("", "Élément à l'index " + i + " = " + listePhonetique.get(i));
                }
                break;
        }
    }*/


}
