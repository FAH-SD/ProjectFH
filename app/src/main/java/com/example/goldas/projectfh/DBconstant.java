package com.example.goldas.projectfh;

/**
 * Created by Goldas on 2015/6/10.
 */
import android.provider.BaseColumns;

public interface DBconstant extends BaseColumns {
    public static final String TABLE_NAME = "icebox";

    public static final String KIND = "kind";
    public static final String ITEM = "item";
    public static final String QUANTITY = "quantity";
    public static final String LIMITDATE = "limitdate";
    public static final String BUYINGDATE = "buyingdate";
    public static final String STORAGEPLACE = "storage";
    public static final String UNIT = "unit";
}