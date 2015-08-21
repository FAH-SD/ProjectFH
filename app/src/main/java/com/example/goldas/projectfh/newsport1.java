package com.example.goldas.projectfh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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


public class newsport1 extends Activity implements View.OnClickListener {
    private Spinner sports, sp_caloric, kind;
    int userid;
    InputStream is=null;
    String result=null;
    String line=null;


    String[] sport_name,caloric,sport_kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsport1);
        EditText name = (EditText)findViewById(R.id.et_sname);
        final EditText height = (EditText)findViewById(R.id.et_sheight);
        final EditText weight = (EditText)findViewById(R.id.et_sweight);
        final EditText BMI = (EditText)findViewById(R.id.et_sBMI);
        final EditText level = (EditText)findViewById(R.id.et_slevel);
        sports =(Spinner)findViewById(R.id.sp_name);
        kind =(Spinner)findViewById(R.id.sp_type);
        sp_caloric =(Spinner)findViewById(R.id.sp_caloric);
        final EditText et_calorie = (EditText)findViewById(R.id.et_calorie);
        final EditText et_time = (EditText)findViewById(R.id.et_time);

        Bundle bundle2 = this.getIntent().getExtras();
        userid = bundle2.getInt("userid");
        name.setText(bundle2.getString("name"));
        height.setText(bundle2.getString("height"));
        weight.setText(bundle2.getString("weight"));
        BMI.setText(bundle2.getString("BMI"));
        level.setText(bundle2.getString("level"));

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
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle3 = new Bundle();
                bundle3.putInt("userid", userid);
                bundle3.putString("height", height.getText().toString());
                bundle3.putString("weight", weight.getText().toString());
                bundle3.putString("BMI", BMI.getText().toString());
                bundle3.putString("level", level.getText().toString());
                Intent intent1 = new Intent();
                intent1.setClass(newsport1.this, sport.class);
                intent1.putExtras(bundle3);
                startActivity(intent1);
                newsport1.this.finish();
            }
        });

        Button btn_ok = (Button)findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if("".equals(et_time.getText().toString())) {
                    showAlert("錯誤資訊","尚未輸入時間");

                }else {
                    Float w = Float.valueOf(weight.getText().toString());
                    Float min = Float.valueOf(et_time.getText().toString());
                    Float hr = min / 60;
                    String temp = sp_caloric.getSelectedItem().toString();
                    temp = temp.replace(" ", "");
                    Float kcal = Float.valueOf(temp);
                    Float totalkcal = w * hr * kcal;
                    et_calorie.setText(String.format("%.2f", totalkcal));
                }
            }
        });

        
        new NetworkTask().execute();
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

    // 點擊空白區域 自動隱藏鍵盤
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){

            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }


    private void showAlert(String title,String context){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(context);
        alert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                    }
                });
        alert.show();
    }

    class NetworkTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(newsport1.this);
        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://163.13.201.82/tes2t.php");
                HttpResponse response = httpclient.execute(httppost);
                Log.e("Fail 1", "3");

                HttpEntity entity = response.getEntity();
                Log.e("Fail 1", "4");

                is = entity.getContent();
                Log.e("Pass 1", "connection success ");
            } catch (Exception e) {
                Log.e("Fail 1", e.toString());
//                Toast.makeText(getApplicationContext(), "Invalid IP Address", Toast.LENGTH_LONG).show();
                finish();
            }


            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();
            } catch (Exception e) {
                Log.e("Fail 2", e.toString());
            }


            try {
                JSONArray JA = new JSONArray(result);
                JSONObject json = null;
                caloric = new String[JA.length()];
                sport_name = new String[JA.length()];
                sport_kind = new String[JA.length()];
                for (int i = 0; i < JA.length(); i++) {
                    json = JA.getJSONObject(i);
                    sport_name[i] = json.getString("s_sport");
                    sport_kind[i] = json.getString("s_kind");
                    caloric[i] = json.getString("s_caloric");

                }
//                Toast.makeText(getApplicationContext(), "sss", Toast.LENGTH_LONG).show();

                for (int i = 0; i < sport_name.length; i++) {
                    List<String> list1 = new ArrayList<String>();
                    List<String> list2 = new ArrayList<String>();
                    List<String> list3 = new ArrayList<String>();
                    list1.add(sport_name[i]);
                    list2.add(caloric[i]);
                    list3.add(sport_kind[i]);
//                    Log.d("test", "roll_no = " + roll_no[i] + ", name = " + name[i]);
                }



            } catch (Exception e) {

                Log.e("Fail 3", e.toString());
//login.this.finish();

            }
            return null;
        }
        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            spinner_fn();


        }
    }
    private void spinner_fn() {
// TODO Auto-generated method stub

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinnerlayout, sport_name);
        dataAdapter1.setDropDownViewResource(R.layout.spinnerlayout);
        sports.setAdapter(dataAdapter1);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinnerlayout, caloric);
        dataAdapter2.setDropDownViewResource(R.layout.spinnerlayout);
        sp_caloric.setAdapter(dataAdapter2);

        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinnerlayout, sport_kind);
        dataAdapter3.setDropDownViewResource(R.layout.spinnerlayout);
        kind.setAdapter(dataAdapter3);

        sports.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
// TODO Auto-generated method stub
                kind.setSelection(position);
                sp_caloric.setSelection(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub
            }

        });

        kind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id)
            {
// TODO Auto-generated method stub
                sports.setSelection(position);
                sp_caloric.setSelection(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
// TODO Auto-generated method stub
            }

        });


    }
}

