package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SportDetail extends Activity {
    SQLiteDatabase db ;
    DBhelper dbhelper;
    int id, userid;
    EditText name, height, weight, BMI, sportname, sporttime, sprotcalories, date;
    String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_detail);

        dbhelper = new DBhelper(this);
        db = dbhelper.getWritableDatabase();

        name = (EditText) findViewById(R.id.et_name);
        height = (EditText) findViewById(R.id.et_height);
        weight = (EditText) findViewById(R.id.et_weight);
        BMI = (EditText) findViewById(R.id.et_BMI);
        sportname = (EditText) findViewById(R.id.et_sportname);
        sporttime = (EditText) findViewById(R.id.et_sporttime);
        sprotcalories = (EditText) findViewById(R.id.et_sprotcalories);
        date = (EditText) findViewById(R.id.et_date);

        Bundle bundle4 = this.getIntent().getExtras();

        name.setText(bundle4.getString("name"));
        height.setText(bundle4.getString("height"));
        weight.setText(bundle4.getString("weight"));
        BMI.setText(bundle4.getString("BMI"));
        sportname.setText(bundle4.getString("sportname"));
        sporttime.setText(bundle4.getString("sporttime"));
        sprotcalories.setText(bundle4.getString("calories"));
        date.setText(bundle4.getString("date"));
        id = bundle4.getInt("id");
        level = bundle4.getString("level");
        userid = bundle4.getInt("userid");

        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle3 = new Bundle();
                bundle3.putInt("userid", userid);
                bundle3.putString("name", name.getText().toString());
                bundle3.putString("height", height.getText().toString());
                bundle3.putString("weight", weight.getText().toString());
                bundle3.putString("BMI", BMI.getText().toString());
                bundle3.putString("level", level);
                Intent i = new Intent(SportDetail.this, SportRecord.class);
                i.putExtras(bundle3);
                startActivity(i);
                SportDetail.this.finish();
            }
        });

        ImageButton buttondelete = (ImageButton) findViewById(R.id.btn_delete);
        buttondelete.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert("警告", "是否確定刪除此筆資料？");
            }
        });
    }

    private void showAlert(String title,String context)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(context);
        alert.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                    }
                });
        alert.setPositiveButton("確定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        long long1 = db.delete("sport", " _id=" + id, null);
                        Bundle bundle3 = new Bundle();
                        bundle3.putInt("userid", userid);
                        bundle3.putString("name", name.getText().toString());
                        bundle3.putString("height", height.getText().toString());
                        bundle3.putString("weight", weight.getText().toString());
                        bundle3.putString("BMI", BMI.getText().toString());
                        bundle3.putString("level", level);
                        Intent i = new Intent(SportDetail.this, SportRecord.class);
                        i.putExtras(bundle3);
                        startActivity(i);
                        SportDetail.this.finish();
                    }
                });
        alert.show();
    }

}
