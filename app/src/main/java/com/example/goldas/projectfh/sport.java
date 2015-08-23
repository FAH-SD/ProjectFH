package com.example.goldas.projectfh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class sport extends Activity implements View.OnClickListener{
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private SimpleCursorAdapter adapter = null;
    private EditText height, weight, BMI, level;
    private Button btn_ok, button3, button4, btn_record;
    private Spinner name;
    String stringbmi, stringh, stringw;
    String stringname;
    float fbmi, fh, fw;
    int userid;

    String[] sport_name,caloric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        db=(new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor= db.rawQuery("SELECT _id,name from family",null);
        adapter = new SimpleCursorAdapter(this, R.layout.familylist, cursor, new String[]{"name"},new int[]{R.id.fl_name});
        name = (Spinner)findViewById(R.id.sp_sname);
        name.setAdapter(adapter);
        height = (EditText)findViewById(R.id.et_sheight);
        weight = (EditText)findViewById(R.id.et_sweight);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        btn_record = (Button)findViewById(R.id.btn_record);
        BMI = (EditText)findViewById(R.id.et_sBMI);
        level = (EditText)findViewById(R.id.et_slevel);


        Bundle bundle2=this.getIntent().getExtras();
        userid = bundle2.getInt("userid");
        name.setSelection(userid);
        height.setText(bundle2.getString("height"));
        weight.setText(bundle2.getString("weight"));
        BMI.setText(bundle2.getString("BMI"));
        long id = (name.getSelectedItemId());
        try {
            Cursor c = get(id);
            stringname = c.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(BMI.getText().toString().equals("")){
            button3.setVisibility(View.INVISIBLE);
            button4.setVisibility(View.INVISIBLE);
            btn_record.setVisibility(View.INVISIBLE);
        }else{
            stringh = height.getText().toString();
            stringw = weight.getText().toString();
            fh = Float.parseFloat(stringh);
            fh = (fh / 100);
            float fh2 = fh * fh;
            fw = Float.parseFloat(stringw);
            fbmi = (fw / fh2);
            BMI.setText(String.format("%.2f", fbmi));

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
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.VISIBLE);
            btn_record.setVisibility(View.VISIBLE);
        }


        // configure the SlidingMenu
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.activity_left_fragment);
        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sport.this.finish();
            }
        });

        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putInt("userid", userid);
                bundle.putString("name", stringname);
                bundle.putString("height", height.getText().toString());
                bundle.putString("weight", weight.getText().toString());
                bundle.putString("BMI", BMI.getText().toString());
                bundle.putString("level", level.getText().toString());
                Intent intent1 = new Intent();
                intent1.setClass(sport.this, newsport1.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
                sport.this.finish();
            }
        });

        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle2 = new Bundle();
                bundle2.putInt("userid", userid);
                bundle2.putString("name", stringname);
                bundle2.putString("height", height.getText().toString());
                bundle2.putString("weight", weight.getText().toString());
                bundle2.putString("BMI", BMI.getText().toString());
                bundle2.putString("level", level.getText().toString());
                Intent intent1 = new Intent();
                intent1.setClass(sport.this, newsport2.class);
                intent1.putExtras(bundle2);
                startActivity(intent1);
                sport.this.finish();
            }
        });

        btn_record.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle4 = new Bundle();
                bundle4.putInt("userid", userid);
                bundle4.putString("name", stringname);
                bundle4.putString("height", height.getText().toString());
                bundle4.putString("weight", weight.getText().toString());
                bundle4.putString("BMI", BMI.getText().toString());
                bundle4.putString("level", level.getText().toString());
                Intent intent1 = new Intent();
                intent1.setClass(sport.this, SportRecord.class);
                intent1.putExtras(bundle4);
                startActivity(intent1);
                sport.this.finish();
            }
        });

        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long id = (name.getSelectedItemId());
                try {
                    Cursor c = get(id);
                    stringname = c.getString(1);
                    stringh = c.getString(2);
                    height.setText(stringh);
                    stringw = c.getString(3);
                    weight.setText(stringw);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                userid = name.getSelectedItemPosition();
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.VISIBLE);
                btn_record.setVisibility(View.VISIBLE);
                fh = Float.parseFloat(stringh);
                fh = (fh / 100);
                float fh2 = fh * fh;
                fw = Float.parseFloat(stringw);
                fbmi = (fw / fh2);
                BMI.setText(String.format("%.2f", fbmi));

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


            }
        });



// 导航打开监听事件
        menu.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {
            }
        });
        // 导航关闭监听事件
        menu.setOnClosedListener(new SlidingMenu.OnClosedListener() {

            @Override
            public void onClosed() {
            }
        });


        ImageView lefthome = (ImageView)findViewById(R.id.lefthome);
        lefthome.setOnClickListener(this);

        ImageView leftfamily = (ImageView)findViewById(R.id.leftfamily);
        leftfamily.setOnClickListener(this);

        ImageView lefticebox = (ImageView)findViewById(R.id.lefticebox);
        lefticebox.setOnClickListener(this);

        ImageView leftdish = (ImageView)findViewById(R.id.leftdish);
        leftdish.setOnClickListener(this);

        ImageView leftsport = (ImageView)findViewById(R.id.leftsport);
        leftsport.setOnClickListener(this);

        ImageView leftset = (ImageView)findViewById(R.id.leftset);
        leftset.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.lefthome:
                this.finish();
                break;
            case R.id.leftfamily:
                Intent intentfamily = new Intent();
                intentfamily.setClass(this, family.class);
                startActivity(intentfamily);
                this.finish();
                break;
            case R.id.lefticebox:
                Intent intenticebox = new Intent();
                intenticebox.setClass(this, icebox.class);
                startActivity(intenticebox);
                this.finish();
                break;
            case R.id.leftdish:
                Intent intentdish = new Intent();
                intentdish.setClass(this, dish.class);
                startActivity(intentdish);
                this.finish();
                break;
            case R.id.leftsport:
                Intent intentsport = new Intent();
                intentsport.setClass(this, sport.class);
                startActivity(intentsport);
                this.finish();
                break;
            case R.id.leftset:
                Intent intentset = new Intent();
                intentset.setClass(this, set.class);
                startActivity(intentset);
                this.finish();
                break;
            default:
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Cursor get(long rowId) throws SQLException {
        Cursor c = db.rawQuery("SELECT _id,name,height,weight from family WHERE _id=" + rowId, null);
        if(c.getCount()>0){
            c.moveToFirst();
        }

        return c;

    }

}



