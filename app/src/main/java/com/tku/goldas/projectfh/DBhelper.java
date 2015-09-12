package com.tku.goldas.projectfh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.tku.goldas.projectfh.DBconstant.BUYINGDATE;
import static com.tku.goldas.projectfh.DBconstant.ITEM;
import static com.tku.goldas.projectfh.DBconstant.KIND;
import static com.tku.goldas.projectfh.DBconstant.LIMITDATE;
import static com.tku.goldas.projectfh.DBconstant.QUANTITY;
import static com.tku.goldas.projectfh.DBconstant.STORAGEPLACE;
import static com.tku.goldas.projectfh.DBconstant.TABLE_NAME;
import static com.tku.goldas.projectfh.DBconstant.UNIT;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY1;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY2;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY3;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY4;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY5;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY6;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY7;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY8;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY9;
import static com.tku.goldas.projectfh.FDBconstant.ALLERGY10;
import static com.tku.goldas.projectfh.FDBconstant.BIRTHYEAR;
import static com.tku.goldas.projectfh.FDBconstant.FTABLE_NAME;
import static com.tku.goldas.projectfh.FDBconstant.HABIT;
import static com.tku.goldas.projectfh.FDBconstant.HEIGHT;
import static com.tku.goldas.projectfh.FDBconstant.NAME;
import static com.tku.goldas.projectfh.FDBconstant.SEX;
import static com.tku.goldas.projectfh.FDBconstant.WEIGHT;
import static com.tku.goldas.projectfh.SDBconstant.S_USERNAME;
import static com.tku.goldas.projectfh.SDBconstant.S_HEIGHT;
import static com.tku.goldas.projectfh.SDBconstant.S_WEIGHT;
import static com.tku.goldas.projectfh.SDBconstant.S_BMI;
import static com.tku.goldas.projectfh.SDBconstant.S_SPORTNAME;
import static com.tku.goldas.projectfh.SDBconstant.S_SPORTTIME;
import static com.tku.goldas.projectfh.SDBconstant.S_CALORIES;
import static com.tku.goldas.projectfh.SDBconstant.S_DATE;
import static com.tku.goldas.projectfh.SDBconstant.STABLE_NAME;
import static com.tku.goldas.projectfh.DDBconstant.DTABLE_NAME;
import static com.tku.goldas.projectfh.DDBconstant.D_DISHNAME;
import static com.tku.goldas.projectfh.DDBconstant.D_MQUANTITY;
import static com.tku.goldas.projectfh.DDBconstant.D_MEMBER;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD1;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD1;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD1;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD2;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD2;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD2;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD3;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD3;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD3;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD4;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD4;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD4;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD5;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD5;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD5;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD6;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD6;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD6;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD7;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD7;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD7;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD8;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD8;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD8;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD9;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD9;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD9;


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
                KIND + " CHAR(15), " +
                ITEM + " CHAR(15), " +
                QUANTITY + " float no null, " +
                BUYINGDATE + " DATE no null, " +
                LIMITDATE + " DATE no null, " +
                STORAGEPLACE + " CHAR(5), " +
                UNIT + " CHAR(5) );";
        db.execSQL(INIT_TABLE);

        final String INIT_TABLE1 = "CREATE TABLE " + FTABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " CHAR(30), " +
                SEX + " CHAR(5), " +
                WEIGHT + " interger no null, " +
                HEIGHT + " interger no null, " +
                BIRTHYEAR + " interger no null, "+
                ALLERGY1 + " CHAR(2), "+
                ALLERGY2 + " CHAR(2), "+
                ALLERGY3 + " CHAR(2), "+
                ALLERGY4 + " CHAR(2), "+
                ALLERGY5 + " CHAR(2), "+
                ALLERGY6 + " CHAR(2), "+
                ALLERGY7 + " CHAR(2), "+
                ALLERGY8 + " CHAR(2), "+
                ALLERGY9 + " CHAR(2), "+
                ALLERGY10 + " CHAR(2),"+
                HABIT + " CHAR(10)); ";
        db.execSQL(INIT_TABLE1);

        final String INIT_TABLE2 = "CREATE TABLE " + STABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                S_USERNAME + " CHAR(30), " +
                S_HEIGHT + " interger no null, " +
                S_WEIGHT + " interger no null, " +
                S_BMI + " float no null, "+
                S_SPORTNAME + " CHAR(15), "+
                S_SPORTTIME + " float no null, "+
                S_CALORIES + " float no null, "+
                S_DATE + " DATE no null); ";
        db.execSQL(INIT_TABLE2);

        final String INIT_TABLE3 = "CREATE TABLE " + DTABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                D_DISHNAME + " CHAR(30), " +
                D_MEMBER + " CHAR(50), " +
                D_MQUANTITY + " interger no null, " +
                D_FOOD1 + " CHAR(15), " +
                D_QFOOD1 + " float no null, "+
                D_UFOOD1 + " CHAR(5), "+
                D_FOOD2 + " CHAR(15), " +
                D_QFOOD2 + " float no null, "+
                D_UFOOD2 + " CHAR(5), "+
                D_FOOD3 + " CHAR(15), " +
                D_QFOOD3 + " float no null, "+
                D_UFOOD3 + " CHAR(5), "+
                D_FOOD4 + " CHAR(15), " +
                D_QFOOD4 + " float no null, "+
                D_UFOOD4 + " CHAR(5), "+
                D_FOOD5 + " CHAR(15), " +
                D_QFOOD5 + " float no null, "+
                D_UFOOD5 + " CHAR(5), "+
                D_FOOD6 + " CHAR(15), " +
                D_QFOOD6 + " float no null, "+
                D_UFOOD6 + " CHAR(5), "+
                D_FOOD7 + " CHAR(15), " +
                D_QFOOD7 + " float no null, "+
                D_UFOOD7 + " CHAR(5), "+
                D_FOOD8 + " CHAR(15), " +
                D_QFOOD8 + " float no null, "+
                D_UFOOD8 + " CHAR(5), "+
                D_FOOD9 + " CHAR(15), " +
                D_QFOOD9 + " float no null, "+
                D_UFOOD9 + " CHAR(5)); ";
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