package com.example.alexandrepc.kanji2;

import android.util.Log;

import java.util.LinkedList;

/**
 * Created by AlexandrePC on 24/01/2015.
 * Classe Kanji
 */
public class Kanji {
        private int id;
        private String caractere;
        private String sens;
        private String phonetique;

       public Kanji(){ //Correspond à un kanji vide, pour représenter une case vide
            this.caractere = "";
            this.sens = "";
            this.phonetique = "";
        }

        public Kanji (String caractere, String phonetique, String sens){
            this.caractere = caractere;
            this.sens = sens;
            this.phonetique = phonetique;
        }

        public Kanji(Kanji kanji){
            this.caractere = kanji.caractere;
            this.sens = kanji.sens;
            this.phonetique = kanji.phonetique;
        }

        public void metNull(){
            this.caractere = "";
            this.sens = "";
            this.phonetique = "";
        }

        public void metValeur(Kanji kanji){
            this.caractere = kanji.caractere;
            this.sens = kanji.sens;
            this.phonetique = kanji.phonetique;
        }

        public boolean estNull(){
            return caractere.equals("");
        }


        public boolean correspondSens(String sens){
            return (this.sens.equals(sens));
        }

        public boolean correspondPhonetique(String phonetique){
            return (this.phonetique.equals(phonetique));
        }

        public void affiche(){
            if (!this.estNull()){
                Log.d("| " + this.caractere + " |", "");
        }
        else {
            Log.d("","|    |");
            }
        }

        public String recupSens(){
            return this.sens;
        }

        public String recupPhonetique(){
            return this.phonetique;
            }

        //getters & setters
        public void setId (int id){
            this.id = id;
        }

        public void setCaractere (String caractere){
            this.caractere = caractere;
        }

        public void setSens (String sens){
            this.sens = sens;
        }

        public void setPhonetique (String phonetique){
            this.phonetique = phonetique;
        }

        public int getId() {
            return id;
        }

        public String getCaractere() {
            return caractere;
        }

        public String getSens() {
            return sens;
        }

        public String getPhonetique() {
               return phonetique;
        }



        @Override
        public String toString() {
            return "Kanji [id=" + id + "caractere=" + caractere + ", sens=" + sens + ", phonetique=" + phonetique
                    + "]";
        }
}
