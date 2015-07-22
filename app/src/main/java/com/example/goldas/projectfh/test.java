package com.example.goldas.projectfh;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Goldas on 2015/6/15.
 */
public class test extends ListActivity {
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private SimpleCursorAdapter adapter = null;
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icebox);
        db=(new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor = db.rawQuery("SELECT _id,kind,item,quantity,limitdate,buyingdate,storage from icebox", null);
        adapter = new SimpleCursorAdapter(this, R.layout.teest, cursor, new String[]{"kind,item,quantity,limitdate,buyingdate,storage"}, new int[]{R.id.txtkind, R.id.txtitem, R.id.txtquan, R.id.txtbd, R.id.txtld, R.id.txts});
        setListAdapter(adapter);
    }
    protected void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
