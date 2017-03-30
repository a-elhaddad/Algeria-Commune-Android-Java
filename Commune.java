package wnplus.com.addell.wine.naklou.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import wnplus.com.addell.wine.naklou.App;
import wnplus.com.addell.wine.naklou.database.CommuneDatabase;


public class Commune {
    private String NomCommune;
    private String CodePostalCommune;
    private String CodeWilaya;
    private String IdCommune;
    private String lat;
    private String lon;
    private static ArrayList<Commune> ComArrayList;

    public Commune() {
    }

    public Commune(Context con) {

        CommuneCsvToDatabase(con);
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }


    public String getIdCommune() {
        return IdCommune;
    }

    public void setIdCommune(String idCommune) {
        IdCommune = idCommune;
    }

    public String getCodeWilaya() {
        return CodeWilaya;
    }

    public void setCodeWilaya(String codeWilaya) {
        CodeWilaya = codeWilaya;
    }

    public String getCodePostalCommune() {
        return CodePostalCommune;
    }

    public void setCodePostalCommune(String codePostalCommune) {
        CodePostalCommune = codePostalCommune;
    }

    public String getNomCommune() {
        return NomCommune;
    }

    public void setNomCommune(String nomCommune) {
        NomCommune = nomCommune;
    }

    public static void CommuneCsvToDatabase(Context context) {

        ComArrayList = new ArrayList<>();
        if (CommuneDatabase.getInstance(context).getAllCommune().size() == 0)
            try {

                InputStreamReader is = new InputStreamReader(context.getAssets().open("commune.csv"), "UTF-8");
                BufferedReader reader = new BufferedReader(is);
                reader.readLine();
                String line;
                String[] columns;


                while ((line = reader.readLine()) != null) {
                    columns = line.split(",");
                    Commune com = new Commune();
                    com.setIdCommune(columns[0].replaceAll("\"", ""));
                    com.setNomCommune(columns[2].replaceAll("\"", ""));
                    com.setCodePostalCommune(columns[1].replaceAll("\"", ""));
                    com.setCodeWilaya(columns[5].replaceAll("\"", ""));
                    com.setLat(columns[3].replaceAll("\"", ""));
                    com.setLon(columns[4].replaceAll("\"", ""));
                    Log.d("CommuneCsvToDatabase", "CommuneCsvToDatabase: " + columns[0] + " " + columns[1] + " " + columns[2] + " " + columns[3] + " " + columns[4] + " " + columns[5]);
                    ComArrayList.add(com);

                }
                CommuneDatabase.getInstance(context).addAllCommune(ComArrayList);
            } catch (Exception e) {
                Log.d("Commune", "CommuneCsvToDatabase: ");
            }




    }
    public static ArrayList<String> getCommunes() {

        ArrayList<Commune> Communes = CommuneDatabase.getInstance(App.getInstance()).getAllCommune();
        ArrayList<String> comList = new ArrayList<String>();

        for (int i = 0 ; i < Communes.size() ; i++) {

            comList.add(Communes.get(i).getNomCommune());
        }
        return comList;
    }

    public static ArrayList<String> getCommunes(int wilaya, Context con) {
        CommuneDatabase db = CommuneDatabase.getInstance(con);
        ArrayList<String> comList = db.getCommuneByWilaya(wilaya + "");
        return comList;
    }

}
