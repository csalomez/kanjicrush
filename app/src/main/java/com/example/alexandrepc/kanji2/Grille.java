package com.example.alexandrepc.kanji2;

/**
 * Created by AlexandrePC on 28/01/2015.
 */

import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;



class Grille {

    private Kanji[][] matrice;
    private int hauteur; //ordonnées
    private int largeur; //abscisses

    public Grille(int hauteur,int largeur){
        int i ,j;
        for (i=0; i<hauteur; i++){
            for (j=0;j<largeur; j++){
                matrice[i][j] = new Kanji();
            }
        }
    }

    public Grille(LinkedList<Kanji> listeKanji, int hauteur,int largeur){
        int i ,j;

        this.hauteur=hauteur;
        this.largeur=largeur;

        ListIterator<Kanji> li = listeKanji.listIterator();
        matrice = new Kanji[hauteur][largeur];
        for (i=hauteur-1; i>=0; i--){

            for (j=largeur-1;j>=0; j--){
                if(li.hasNext()){
                    matrice[i][j] = new Kanji(li.next());

                }
                else{
                    matrice[i][j] = new Kanji();
                }
            }
        }
    }


    public boolean estVide(){
        int i ,j;
        for (i=0; i<hauteur; i++){
            for (j=0;j<largeur; j++){
                if(!matrice[i][j].estNull()){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean estRemplie(){
        int i,j;
        for (i=0; i<hauteur; i++){
            for (j=0;j<largeur; j++){
                if(matrice[i][j].estNull()){
                    return false;
                }
            }
        }
        return true;
    }

    public Kanji recupKanji(int i,int j){
        return matrice[i][j];
    }

    // public void supprimerKanji(int i,int j){
    // 	if(i<hauteur && j<largeur && !matrice[i][j].estNull()){
    // 	    matrice[i][j].metNull();
    // 	}
    // 	else {
    // 	    System.out.println("Erreur lors de la suppression du kanji");
    // 	}
    // }

    // public boolean correspondSens(int i,int j,String sens){
    // 	return matrice[i][j].correspondSens(sens);
    // }

    // public boolean correspondPhonetique(int i,int j,String phonetique){
    // 	return matrice[i][j].correspondPhonetique(phonetique);
    // }


    public void affiche(){
        int i,j;
        for (i=0; i<hauteur; i++){
            for (j=0;j<largeur; j++){
                matrice[i][j].affiche();
            }
            Log.d("","\n----------------------------------------");
        }
    }

}




