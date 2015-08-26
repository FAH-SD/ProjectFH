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
import static com.example.goldas.projectfh.DBconstant.UNIT;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY1;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY2;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY3;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY4;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY5;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY6;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY7;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY8;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY9;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY10;
import static com.example.goldas.projectfh.FDBconstant.BIRTHYEAR;
import static com.example.goldas.projectfh.FDBconstant.FTABLE_NAME;
import static com.example.goldas.projectfh.FDBconstant.HABIT;
import static com.example.goldas.projectfh.FDBconstant.HEIGHT;
import static com.example.goldas.projectfh.FDBconstant.NAME;
import static com.example.goldas.projectfh.FDBconstant.SEX;
import static com.example.goldas.projectfh.FDBconstant.WEIGHT;
import static com.example.goldas.projectfh.SDBconstant.S_USERNAME;
import static com.example.goldas.projectfh.SDBconstant.S_HEIGHT;
import static com.example.goldas.projectfh.SDBconstant.S_WEIGHT;
import static com.example.goldas.projectfh.SDBconstant.S_BMI;
import static com.example.goldas.projectfh.SDBconstant.S_SPORTNAME;
import static com.example.goldas.projectfh.SDBconstant.S_SPORTTIME;
import static com.example.goldas.projectfh.SDBconstant.S_CALORIES;
import static com.example.goldas.projectfh.SDBconstant.S_DATE;
import static com.example.goldas.projectfh.SDBconstant.STABLE_NAME;
import static com.example.goldas.projectfh.DDBconstant.DTABLE_NAME;
import static com.example.goldas.projectfh.DDBconstant.D_DISHNAME;
import static com.example.goldas.projectfh.DDBconstant.D_MQUANTITY;
import static com.example.goldas.projectfh.DDBconstant.D_MEMBER;
import static com.example.goldas.projectfh.DDBconstant.D_FOOD1;
import static com.example.goldas.projectfh.DDBconstant.D_QFOOD1;
import static com.example.goldas.projectfh.DDBconstant.D_UFOOD1;
import static com.example.goldas.projectfh.DDBconstant.D_FOOD2;
import static com.example.goldas.projectfh.DDBconstant.D_QFOOD2;
import static com.example.goldas.projectfh.DDBconstant.D_UFOOD2;
import static com.example.goldas.projectfh.DDBconstant.D_FOOD3;
import static com.example.goldas.projectfh.DDBconstant.D_QFOOD3;
import static com.example.goldas.projectfh.DDBconstant.D_UFOOD3;
import static com.example.goldas.projectfh.DDBconstant.D_FOOD4;
import static com.example.goldas.projectfh.DDBconstant.D_QFOOD4;
import static com.example.goldas.projectfh.DDBconstant.D_UFOOD4;
import static com.example.goldas.projectfh.DDBconstant.D_FOOD5;
import static com.example.goldas.projectfh.DDBconstant.D_QFOOD5;
import static com.example.goldas.projectfh.DDBconstant.D_UFOOD5;
import static com.example.goldas.projectfh.DDBconstant.D_FOOD6;
import static com.example.goldas.projectfh.DDBconstant.D_QFOOD6;
import static com.example.goldas.projectfh.DDBconstant.D_UFOOD6;
import static com.example.goldas.projectfh.DDBconstant.D_FOOD7;
import static com.example.goldas.projectfh.DDBconstant.D_QFOOD7;
import static com.example.goldas.projectfh.DDBconstant.D_UFOOD7;
import static com.example.goldas.projectfh.DDBconstant.D_FOOD8;
import static com.example.goldas.projectfh.DDBconstant.D_QFOOD8;
import static com.example.goldas.projectfh.DDBconstant.D_UFOOD8;
import static com.example.goldas.projectfh.DDBconstant.D_FOOD9;
import static com.example.goldas.projectfh.DDBconstant.D_QFOOD9;
import static com.example.goldas.projectfh.DDBconstant.D_UFOOD9;


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
                STORAGEPLACE + " CHAR, " +
                UNIT + " CHAR );";
        db.execSQL(INIT_TABLE);

        final String INIT_TABLE1 = "CREATE TABLE " + FTABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " CHAR, " +
                SEX + " CHAR, " +
                WEIGHT + " interger no null, " +
                HEIGHT + " interger no null, " +
                BIRTHYEAR + " interger no null, "+
                ALLERGY1 + " CHAR, "+
                ALLERGY2 + " CHAR, "+
                ALLERGY3 + " CHAR, "+
                ALLERGY4 + " CHAR, "+
                ALLERGY5 + " CHAR, "+
                ALLERGY6 + " CHAR, "+
                ALLERGY7 + " CHAR, "+
                ALLERGY8 + " CHAR, "+
                ALLERGY9 + " CHAR, "+
                ALLERGY10 + " CHAR,"+
                HABIT + " CHAR); ";
        db.execSQL(INIT_TABLE1);

        final String INIT_TABLE2 = "CREATE TABLE " + STABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                S_USERNAME + " CHAR, " +
                S_HEIGHT + " interger no null, " +
                S_WEIGHT + " interger no null, " +
                S_BMI + " CHAR, "+
                S_SPORTNAME + " CHAR, "+
                S_SPORTTIME + " CHAR, "+
                S_CALORIES + " CHAR, "+
                S_DATE + " CHAR); ";
        db.execSQL(INIT_TABLE2);

        final String INIT_TABLE3 = "CREATE TABLE " + DTABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                D_DISHNAME + " CHAR, " +
                D_MEMBER + " CHAR, " +
                D_MQUANTITY + " CHAR, " +
                D_FOOD1 + " CHAR, " +
                D_QFOOD1 + " CHAR, "+
                D_UFOOD1 + " CHAR, "+
                D_FOOD2 + " CHAR, " +
                D_QFOOD2 + " CHAR, "+
                D_UFOOD2 + " CHAR, "+
                D_FOOD3 + " CHAR, " +
                D_QFOOD3 + " CHAR, "+
                D_UFOOD3 + " CHAR, "+
                D_FOOD4 + " CHAR, " +
                D_QFOOD4 + " CHAR, "+
                D_UFOOD4 + " CHAR, "+
                D_FOOD5 + " CHAR, " +
                D_QFOOD5 + " CHAR, "+
                D_UFOOD5 + " CHAR, "+
                D_FOOD6 + " CHAR, " +
                D_QFOOD6 + " CHAR, "+
                D_UFOOD6 + " CHAR, "+
                D_FOOD7 + " CHAR, " +
                D_QFOOD7 + " CHAR, "+
                D_UFOOD7 + " CHAR, "+
                D_FOOD8 + " CHAR, " +
                D_QFOOD8 + " CHAR, "+
                D_UFOOD8 + " CHAR, "+
                D_FOOD9 + " CHAR, " +
                D_QFOOD9 + " CHAR, "+
                D_UFOOD9 + " CHAR); ";
        db.execSQL(INIT_TABLE3);}

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