package wnplus.com.addell.wine.naklou.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import wnplus.com.addell.wine.naklou.model.Commune;


public class CommuneDatabase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "wizy.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_COM = "Dz_Com";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COMMUNE = "Commune";
    private static final String COLUMN_LAT = "Lat";
    private static final String COLUMN_LON = "Lon";
    private static final String COLUMN_NUM_WILAYA = "NumWilaya";
    private static final String COLUMN_CODE_POSTAL = "CodePostale";

    private static final String CREATE_COM = "CREATE TABLE " + TABLE_COM + " ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY , "
            + COLUMN_COMMUNE + " TEXT DEFAULT '' , "
            + COLUMN_CODE_POSTAL + " TEXT DEFAULT '' , "
            + COLUMN_NUM_WILAYA + " TEXT DEFAULT '' , "
            + COLUMN_LAT + " TEXT DEFAULT '' , "
            + COLUMN_LON + " TEXT DEFAULT '' " + " )";

    private static String TAG = "DATA";
    private static CommuneDatabase mInstance = null;

    public CommuneDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized CommuneDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new CommuneDatabase(context.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_COM);

        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addAllCommune(ArrayList<Commune> Coms) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(TABLE_COM, null, null);
        db.beginTransaction();
        try {
            for (Commune Com : Coms) {

                values.put(COLUMN_COMMUNE, Com.getNomCommune());
                values.put(COLUMN_CODE_POSTAL, Com.getCodePostalCommune());
                values.put(COLUMN_NUM_WILAYA, Com.getCodeWilaya());
                values.put(COLUMN_LAT, Com.getLat());
                values.put(COLUMN_LON, Com.getLon());

                db.insert(TABLE_COM, null, values);
                Log.d(TAG, "addAllCommune: " + values.toString());

            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            //Error in between database transaction
        } finally {
            db.endTransaction();
        }

        System.gc();
    }


    public ArrayList<Commune> getAllCommune() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;

        c = db.rawQuery("select * from " + TABLE_COM, null);

        ArrayList<Commune> ComArrayList = new ArrayList<Commune>();

        if (c.moveToFirst()) {
            do {
                Commune Com = new Commune();
                Com.setIdCommune(c.getString(0));
                Com.setNomCommune(c.getString(1));
                Com.setCodePostalCommune(c.getString(2));
                Com.setCodeWilaya(c.getString(3));
                Com.setLat(c.getString(4));
                Com.setLon(c.getString(5));
                ComArrayList.add(Com);
            } while (c.moveToNext());
        }

        c.close();
        System.gc();
        return ComArrayList;


    }

    public ArrayList<String> getCommuneByWilaya(String numWilaya) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;

        c = db.query(TABLE_COM, new String[]{COLUMN_COMMUNE},
                COLUMN_NUM_WILAYA + " = " + numWilaya , null, null, null, null);

        ArrayList<String> comArrayList = new ArrayList<String>();

        if (c.moveToFirst()) {
            do {
                Log.d(TAG, "getCommuneByWilaya: " + c.getString(0));
                comArrayList.add(c.getString(0));

            } while (c.moveToNext());
        }


        c.close();
        System.gc();
        return comArrayList;

    }


    public void deleteAllCommune() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COM, null, null);
        Log.d(TAG, "deleteAllCom: ");
        db.close();

    }


}

