package io.vsia.bmicalculator.business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import io.vsia.bmicalculator.model.Bmi;

public class BMIDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "BMI";
    private static final int DB_VERSION = 1;

    public BMIDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE BMI_ENTRY ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "GEWICHT REAL, "
                + "GROESSE REAL, "
                + "BMI REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createNewEntry (Bmi person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NAME", person.getName());
        values.put("GEWICHT", person.getGewicht());
        values.put("GROESSE", person.getGroesse());
        values.put("BMI", person.getBmi());

        db.insert("BMI_ENTRY", null, values);
        db.close();
    }

    public void deleteAllEntries() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("BMI_ENTRY", null, null);
    }

    public List<Bmi> showAllEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Bmi> returnedVal = new ArrayList<>();

        String query = "SELECT * FROM BMI_ENTRY";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Bmi bmi = new Bmi();
                bmi.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                bmi.setGewicht(cursor.getDouble(cursor.getColumnIndex("GEWICHT")));
                bmi.setGroesse(cursor.getDouble(cursor.getColumnIndex("GROESSE")));
                bmi.setBmi(cursor.getDouble(cursor.getColumnIndex("BMI")));
                returnedVal.add(bmi);
            } while (cursor.moveToNext());
        }

        return returnedVal;
    }

    public List<Bmi> showFilteredEntries(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Bmi> returnedVal = new ArrayList<>();

        String query = "SELECT * FROM BMI_ENTRY WHERE NAME = ?";
        String[] args = new String[]{name};
        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
                Bmi bmi = new Bmi();
                bmi.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                bmi.setGewicht(cursor.getDouble(cursor.getColumnIndex("GEWICHT")));
                bmi.setGroesse(cursor.getDouble(cursor.getColumnIndex("GROESSE")));
                bmi.setBmi(cursor.getDouble(cursor.getColumnIndex("BMI")));
                returnedVal.add(bmi);
            } while (cursor.moveToNext());
        }
        return returnedVal;
    }
}
