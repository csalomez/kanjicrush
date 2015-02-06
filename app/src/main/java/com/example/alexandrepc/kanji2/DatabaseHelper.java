package com.example.alexandrepc.kanji2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "KanjiDB3";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*création de la table*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_KANJI_TABLE = "CREATE TABLE kanjis3 ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "caractere TEXT," +
                "sens TEXT, "+
                "phonetique TEXT )";
        db.execSQL(CREATE_KANJI_TABLE);
    }

    public void onDelete(){
        SQLiteDatabase db = this.getWritableDatabase();
        String DELETE_KANJI_TABLE = "DELETE from kanjis3";
           db.execSQL(DELETE_KANJI_TABLE);
    }

    /*supprimer_mettre à jour*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS kanjis3");
        this.onCreate(db);
    }

    private static final String TABLE_KANJIS = "kanjis3";
    private static final String KEY_ID = "id";
    private static final String KEY_CARACTERE = "caractere";
    private static final String KEY_SENS = "sens";
    private static final String KEY_PHONETIQUE = "phonetique";

    private static final String[] COLUMNS = {KEY_ID,KEY_CARACTERE,KEY_SENS,KEY_PHONETIQUE};

    public void addKanji(Kanji k){
        //Log.d("addKanji", k.toString());
        /*on ouvre la base et on insert les éléments*/
        /*on ecrit dans la table*/
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CARACTERE, k.getCaractere());
        values.put(KEY_SENS, k.getSens());
        values.put(KEY_PHONETIQUE, k.getPhonetique());
        db.insert(TABLE_KANJIS,
                null,
                values);
        db.close();
    }

    /*Récuperer un élément de la table à partir de l'id*/
    public Kanji getKanji(int id){
        /*on ouvre la base, et on lit chacune des lignes de la table*/
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_KANJIS,
                        COLUMNS,
                        " id = ?",
                        new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);
        /*parcourt des lignes grace au curseur*/
        if (cursor != null)
            cursor.moveToFirst();
        /*instancie un kanji*/
        Kanji k = new Kanji();
        /*valeur*/
        k.setId(Integer.parseInt(cursor.getString(0)));
        k.setCaractere(cursor.getString(1));
        k.setSens(cursor.getString(2));
        k.setPhonetique(cursor.getString(3));

        //Log.d("getKanji("+id+ ")", k.toString());


        return k;
    }

    /*recuperation des element de la table et on les fou dans la liste*/
    public LinkedList<Kanji> getAllKanjis() {
        LinkedList<Kanji> ks = new LinkedList<Kanji>();

        String query = "SELECT  * FROM " + TABLE_KANJIS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Kanji k = null;
        if (cursor.moveToFirst()) {
            do {
                k = new Kanji();
                k.setId(Integer.parseInt(cursor.getString(0)));
                k.setCaractere(cursor.getString(1));
                k.setSens(cursor.getString(2));
                k.setPhonetique(cursor.getString(3));

                // Add book to books
                ks.add(k);
            } while (cursor.moveToNext());
        }

        //Log.d("getAllKanjis()", ks.toString());
        return ks;
    }

    public void ParseFile(String fileName){


        String csvFile = "Hiragana1.csv";
        BufferedReader br = null;
        String line = "";
        String cvsLinesSplitedBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                Log.d("kaka", "shit");
                // use ; as separator
                String[] Kanji = line.split(cvsLinesSplitedBy);
                Log.d("aaa", "Kanji [kanji= " + Kanji[0]
                        + " , name=" + Kanji[1] + "]");


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }







}


/**
 * Created by Alexandre PATRY  on 23/01/2015.
 */
/*
 *onCreate fill a database after create it
 */
/*public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kanki2.db";
    static final String CARACTERE ="caractere";
    static final String SENS="sens";
    static final String PHONETIQUE="phonetique";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        db.execSQL("CREATE TABLE kanji2 (id integer <FL> primary key autoincrement, caractere c, sens s, phonetique p);");

*/
        /*insert data in our database to manage initialisation operation*/
  /*  ContentValues add = new ContentValues();

    add.put(CARACTERE, "ぱ");
    add.put(SENS, "pa");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "ぴ");
    add.put(SENS, "pi");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "ぷ");
    add.put(SENS, "pu");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "ぺ");
    add.put(SENS, "pe");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "ぽ");
    add.put(SENS, "po");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "ま");
    add.put(SENS, "ma");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "み");
    add.put(SENS, "mi");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "む");
    add.put(SENS, "mu");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "め");
    add.put(SENS, "me");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "も");
    add.put(SENS, "mo");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "や");
    add.put(SENS, "ya");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "ゆ");
    add.put(SENS, "yu");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "よ");
    add.put(SENS, "yo");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "ら");
    add.put(SENS, "ra");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "り");
    add.put(SENS, "ri");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "る");
    add.put(SENS, "ru");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "れ");
    add.put(SENS, "re");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);

    add.put(CARACTERE, "ろ");
    add.put(SENS, "ro");
    add.put(PHONETIQUE, "");
    db.insert("kanji2", CARACTERE, add);
*/
    /*I am a bit lazy to go on ... */
/*
    }*/

    /*onUpgrade converts a database with new version number */

/*
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        android.util.Log.w("kanji2", "delete old data");
        db.execSQL("drop table if exists kanji2");
        onCreate(db);
    }

}*/
