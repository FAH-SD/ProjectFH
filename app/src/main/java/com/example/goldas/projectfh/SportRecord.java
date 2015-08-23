package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;

public class SportRecord extends Activity {
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private SimpleCursorAdapter adapter = null;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_record);
        final EditText name = (EditText)findViewById(R.id.et_sname);
        final EditText height = (EditText)findViewById(R.id.et_sheight);
        final EditText weight = (EditText)findViewById(R.id.et_sweight);
        final EditText BMI = (EditText)findViewById(R.id.et_sBMI);
        final EditText level = (EditText)findViewById(R.id.et_slevel);
        Bundle bundle2 = this.getIntent().getExtras();
        userid = bundle2.getInt("userid");
        name.setText(bundle2.getString("name"));
        height.setText(bundle2.getString("height"));
        weight.setText(bundle2.getString("weight"));
        BMI.setText(bundle2.getString("BMI"));
        level.setText(bundle2.getString("level"));

        db=(new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor= db.rawQuery("SELECT _id, s_sportname, s_date from sport WHERE s_username = '" + name.getText().toString()+ "' " , null);
        adapter = new SimpleCursorAdapter(this, R.layout.sport_record_list, cursor, new String[]{"s_sportname","s_date"}, new int[]{ R.id.txtsport, R.id.txtrdate});
        ListView sportview = (ListView)findViewById(R.id.sportRecordList);
        sportview.setAdapter(adapter);

        float fbmi = Float.parseFloat(BMI.getText().toString());
        if (fbmi < 18.5) {
            level.setText("體重過輕");
            level.setBackgroundColor(Color.rgb(210, 255, 185));
        } else if (18.5 <= fbmi && fbmi < 24) {
            level.setText("正常範圍");
            level.setBackgroundColor(Color.GREEN);
        } else if (24 <= fbmi && fbmi < 27) {
            level.setText("過重");
            level.setBackgroundColor(Color.YELLOW);
        } else if (24 <= fbmi && fbmi < 30) {
            level.setText("輕度肥胖");
            level.setBackgroundColor(Color.rgb(255, 175, 25));
        } else if (30 <= fbmi && fbmi < 35) {
            level.setText("中度肥胖");
            level.setBackgroundColor(Color.rgb(255, 155, 170));
        } else if (35 <= fbmi) {
            level.setText("重度肥胖");
            level.setBackgroundColor(Color.rgb(180, 0, 10));
        }
        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle3 = new Bundle();
                bundle3.putInt("userid", userid);
                bundle3.putString("height", height.getText().toString());
                bundle3.putString("weight", weight.getText().toString());
                bundle3.putString("BMI", BMI.getText().toString());
                bundle3.putString("level", level.getText().toString());
                Intent intent1 = new Intent();
                intent1.setClass(SportRecord.this, sport.class);
                intent1.putExtras(bundle3);
                startActivity(intent1);
                SportRecord.this.finish();
            }
        });


        sportview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                try {
                    Cursor c2 = get(id);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("memberid", c2.getInt(0));
                    bundle2.putString("name", c2.getString(1));
                    bundle2.putString("height", c2.getString(2));
                    bundle2.putString("weight", c2.getString(3));
                    bundle2.putString("BMI", c2.getString(4));
                    bundle2.putString("sportname", c2.getString(5));
                    bundle2.putString("sporttime", c2.getString(6));
                    bundle2.putString("calories", c2.getString(7));
                    bundle2.putString("date", c2.getString(8));
                    Intent i = new Intent(SportRecord.this, SportDetail.class);
                    i.putExtras(bundle2);
                    startActivity(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public Cursor get(long rowId) throws SQLException {
        Cursor c = db.rawQuery("SELECT _id,s_username,s_height,s_weight,s_BMI,s_sportname,s_sporttime,s_calories,s_date from sport WHERE _id="+ rowId, null);
        if(c.getCount()>0){
            c.moveToFirst();
        }

        return c;

    }

    protected void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
