package com.example.goldas.projectfh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.goldas.projectfh.DBconstant.BUYINGDATE;
import static com.example.goldas.projectfh.DBconstant.ITEM;
import static com.example.goldas.projectfh.DBconstant.KIND;
import static com.example.goldas.projectfh.DBconstant.LIMITDATE;
import static com.example.goldas.projectfh.DBconstant.QUANTITY;
import static com.example.goldas.projectfh.DBconstant.STORAGEPLACE;
import static com.example.goldas.projectfh.DBconstant.TABLE_NAME;
import static com.example.goldas.projectfh.FDBconstant.BIRTHYEAR;
import static com.example.goldas.projectfh.FDBconstant.FTABLE_NAME;
import static com.example.goldas.projectfh.FDBconstant.HEIGHT;
import static com.example.goldas.projectfh.FDBconstant.NAME;
import static com.example.goldas.projectfh.FDBconstant.WEIGHT;
import static com.example.goldas.projectfh.FDBconstant.SEX;
public class DBhelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "icebox.db";
    private final static int DATABASE_VERSION = 1;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String INIT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KIND + " CHAR, " +
                ITEM + " CHAR, " +
                QUANTITY + " interger no null, " +
                BUYINGDATE + " DATE no null, " +
                LIMITDATE + " DATE no null, " +
                STORAGEPLACE + " CHAR );";
        db.execSQL(INIT_TABLE);

        final String INIT_TABLE1 = "CREATE TABLE " + FTABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " CHAR, " +
                SEX + " CHAR, " +
                WEIGHT + " interger no null, " +
                HEIGHT + " interger no null, " +
                BIRTHYEAR + " interger no null );";
        db.execSQL(INIT_TABLE1);}

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}