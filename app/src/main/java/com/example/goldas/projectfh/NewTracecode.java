package com.example.goldas.projectfh;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.goldas.projectfh.DBconstant.BUYINGDATE;
import static com.example.goldas.projectfh.DBconstant.ITEM;
import static com.example.goldas.projectfh.DBconstant.KIND;
import static com.example.goldas.projectfh.DBconstant.LIMITDATE;
import static com.example.goldas.projectfh.DBconstant.QUANTITY;
import static com.example.goldas.projectfh.DBconstant.STORAGEPLACE;
import static com.example.goldas.projectfh.DBconstant.TABLE_NAME;
import static com.example.goldas.projectfh.DBconstant.UNIT;

public class NewTracecode extends Activity {
    private Spinner editkind;
    private Spinner edititem;
    private EditText quantity ;
    private TextView tv_buyday;
    private TextView tv_limitday;
    private Spinner editstorage;
    private Spinner unit;
    private Spinner unit2;
    private ImageButton btnitrue;
    private EditText et_kind;
    private EditText et_name;

    private int bYear, bMonth, bDay;
    private int lYear, lMonth, lDay;

    SQLiteDatabase dbrw;
    DBhelper dbhelper;

    InputStream is=null;
    String result=null;
    String line=null;
    String liquid;

    String[] roll_no,name;
    String strfood;
    String url, tracecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tracecode);
        Bundle bundle = this.getIntent().getExtras();
        strfood = bundle.getString("foodname");
        if(strfood.contains("玉女番茄")){
            strfood = "聖女蕃茄";
        }if(strfood.contains("番茄")){
            strfood = "蕃茄";
        }if(strfood.contains("茂谷柑")){
            strfood = "柑橘";
        }if(strfood.contains("橘子")){
            strfood = "桔子";
        }if(strfood.contains("青椒")){
            strfood = "甜椒";
        }if(strfood.contains("黃瓜")){
            strfood = "胡瓜";
        }if(strfood.contains("番石榴")){
            strfood = "芭樂";
        }if(strfood.contains("雪梨")){
            strfood = "水梨";
        }if(strfood.contains("A菜")){
            strfood = "萵苣";
        }if(strfood.contains("大豆")){
            strfood = "黃豆";
        }if(strfood.contains("青江白菜")){
            strfood = "青江菜";
        }if(strfood.contains("紅蘿蔔")){
            strfood = "胡蘿蔔";
        }if(strfood.contains("香芋")){
            strfood = "芋頭";
        }if(strfood.contains("地瓜") || strfood.contains("甘藷")){
            strfood = "甘薯";
        }if(strfood.contains("葉用甘藷") || strfood.contains("葉用甘薯") || strfood.contains("地瓜葉")){
            strfood = "甘薯葉";
        }
        url = bundle.getString("url");
        tracecode = bundle.getString("tracecode");

        findViews();
        setListeners();
        dbhelper = new DBhelper(this);
        dbrw = dbhelper.getWritableDatabase();
        new NetworkTask().execute();



        Button buyDate = (Button) findViewById(R.id.buydate);

        buyDate.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button limitDate = (Button) findViewById(R.id.limitdate);

        limitDate.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog2();
            }
        });

        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_iback);
        buttonback.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(NewTracecode.this, traceAbility.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                bundle.putString("tracecode", tracecode);
                intent1.putExtras(bundle);
                startActivity(intent1);
                NewTracecode.this.finish();
            }
        });

    }


    private void setListeners(){
        btnitrue.setOnClickListener(new itrue_ButtonClickListener());

    }
    class itrue_ButtonClickListener implements View.OnClickListener {
        public void onClick(View v) {
            Calendar c2 = Calendar.getInstance();
            int thisYear = c2.get(Calendar.YEAR);
            int thisMonth = c2.get(Calendar.MONTH);
            int thisDay = c2.get(Calendar.DAY_OF_MONTH);
            if (buyYear > thisYear) {
                showAlert("錯誤資訊", "購買日期輸入錯誤");
                buyYear = buyMonth = buyDay = 0;
                tv_buyday.setText("");
            } else if (buyYear == thisYear && buyMonth > thisMonth+1) {
                showAlert("錯誤資訊", "購買日期輸入錯誤");
                buyYear = buyMonth = buyDay = 0;
                tv_buyday.setText("");
            } else if (buyYear == thisYear && buyMonth == thisMonth+1 && buyDay > thisDay) {
                showAlert("錯誤資訊", "購買日期輸入錯誤");
                buyYear = buyMonth = buyDay = 0;
                tv_buyday.setText("");
            }
            else if(limitYear < buyYear){
                showAlert("錯誤資訊", "有效日期輸入錯誤");
                limitYear = limitMonth = limitDay = 0;
                tv_limitday.setText("");}
            else if(limitYear == buyYear && limitMonth < buyMonth) {
                showAlert("錯誤資訊", "有效日期輸入錯誤");
                limitYear = limitMonth = limitDay = 0;
                tv_limitday.setText("");}
            else if(limitYear == buyYear && limitMonth == buyMonth && limitDay < buyDay) {
                showAlert("錯誤資訊", "有效日期輸入錯誤");
                limitYear = limitMonth = limitDay = 0;
                tv_limitday.setText("");
            }
            else if ("".equals(quantity.getText().toString().trim())) {
                showAlert("錯誤資訊", "數量尚未輸入");
            } else {
                iceboxAlert("提醒視窗","是否確定新增此筆資料？");
            }
        }

    };

    private int buyYear, buyMonth, buyDay;
    public void showDatePickerDialog() {
        // 設定初始日期
        final Calendar c = Calendar.getInstance();
        bYear = c.get(Calendar.YEAR);
        bMonth = c.get(Calendar.MONTH);
        bDay = c.get(Calendar.DAY_OF_MONTH);

        // 跳出日期選擇器
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // 完成選擇，顯示日期
                        tv_buyday.setText(year + "/" + (monthOfYear + 1) + "/"
                                + dayOfMonth);
                        buyYear = year;
                        buyMonth = (monthOfYear + 1);
                        buyDay = dayOfMonth;
                    }
                }, bYear, bMonth, bDay);
        dpd.show();
    }

    private int limitYear, limitMonth, limitDay;
    public void showDatePickerDialog2() {
        // 設定初始日期
        final Calendar c2 = Calendar.getInstance();
        lYear = c2.get(Calendar.YEAR);
        lMonth = c2.get(Calendar.MONTH);
        lDay = c2.get(Calendar.DAY_OF_MONTH);

        // 跳出日期選擇器
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // 完成選擇，顯示日期
                        tv_limitday.setText(year + "/" + (monthOfYear + 1) + "/"
                                + dayOfMonth);
                        limitYear = year;
                        limitMonth = (monthOfYear + 1);
                        limitDay = dayOfMonth;
                    }
                }, lYear, lMonth, lDay);
        dpd.show();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        dbrw.close();

    }

    private void findViews(){
        editkind = (Spinner) findViewById(R.id.spinner);
        edititem = (Spinner) findViewById(R.id.spinner2);
        et_kind = (EditText) findViewById(R.id.et_kind);
        et_name = (EditText) findViewById(R.id.et_name);
        quantity = (EditText) findViewById(R.id.editText);
        tv_buyday = (TextView) findViewById(R.id.tv_buyday);
        tv_limitday = (TextView) findViewById(R.id.tv_limitday);
        editstorage = (Spinner) findViewById(R.id.sp_iplace);
        btnitrue = (ImageButton) findViewById(R.id.btn_itrue);
        unit = (Spinner) findViewById(R.id.sp_iunit);
        unit2 = (Spinner) findViewById(R.id.sp_iunit2);

    }

    private void spinner_fn() {
// TODO Auto-generated method stub

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinnerlayout, roll_no);
        dataAdapter1.setDropDownViewResource(R.layout.spinnerlayout);
        editkind.setAdapter(dataAdapter1);


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinnerlayout, name);
        dataAdapter2.setDropDownViewResource(R.layout.spinnerlayout);
        edititem.setAdapter(dataAdapter2);

        editkind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
// TODO Auto-generated method stub
                edititem.setSelection(position);
                et_kind.setText(editkind.getSelectedItem().toString());
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub
            }

        });



        edititem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
// TODO Auto-generated method stub

                for (int i = 0; i < edititem.getCount(); i++) {
                    String temp = name[i];
                    temp = temp.replace(" ", "");
                    if (strfood.contains(temp)) {
                        editkind.setSelection(i);
                        et_name.setText(edititem.getSelectedItem().toString());
                    }
                }

                liquid = edititem.getSelectedItem().toString();
                if (liquid.contains("脂鮮乳") || liquid.contains("養樂多") || liquid.contains("優酪乳") || (liquid.contains("牛油") || liquid.contains("豬油") || liquid.contains("雞油") || liquid.contains("花生油") || liquid.contains("葵花油") || liquid.contains("玉米油") || liquid.contains("沙拉油") || liquid.contains("橄欖油") || liquid.contains("辣椒油") || liquid.contains("椰子油") | liquid.contains("芝麻油"))) {
                    unit.setVisibility(View.GONE);
                    unit2.setVisibility(View.VISIBLE);
                } else {
                    unit2.setVisibility(View.GONE);
                    unit.setVisibility(View.VISIBLE);
                }
                if("".equals(et_name.getText().toString().trim())){
                    showAlert2("錯誤資訊", "找不到此筆資料");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub
            }
        });

    }

    class NetworkTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(NewTracecode.this);

        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected String doInBackground(String... params) {
            if(isOnline()) {

                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://163.13.201.82/test.php");
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
                    roll_no = new String[JA.length()];
                    name = new String[JA.length()];

                    for (int i = 0; i < JA.length(); i++) {
                        json = JA.getJSONObject(i);
                        roll_no[i] = json.getString("nu_kind");
                        name[i] = json.getString("nu_foodname");

                    }
//                Toast.makeText(getApplicationContext(), "sss", Toast.LENGTH_LONG).show();

                    for (int i = 0; i < roll_no.length; i++) {
                        List<String> list1 = new ArrayList<String>();
                        List<String> list2 = new ArrayList<String>();
                        list1.add(roll_no[i]);
                        list2.add(name[i]);
//                    Log.d("test", "roll_no = " + roll_no[i] + ", name = " + name[i]);
                    }


                } catch (Exception e) {

                    Log.e("Fail 3", e.toString());
//login.this.finish();

                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            spinner_fn();

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
    private void showAlert(String title,String context)
    {
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

    private void showAlert2(String title,String context)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(context);
        alert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                        NewTracecode.this.finish();
                    }
                });
        alert.show();
    }

    private void iceboxAlert(String title,String context) {
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
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        String strkind =  editkind.getSelectedItem().toString();
                        strkind = strkind.replace(" ","");
                        values.put(KIND, strkind);
                        String stritem = edititem.getSelectedItem().toString();
                        stritem = stritem.replace(" ","");
                        values.put(ITEM, stritem);
                        values.put(QUANTITY, quantity.getText().toString());
                        values.put(BUYINGDATE, tv_buyday.getText().toString());
                        values.put(LIMITDATE, tv_limitday.getText().toString());
                        values.put(STORAGEPLACE, editstorage.getSelectedItem().toString());
                        if(unit.getVisibility() == View.VISIBLE ) {
                            values.put(UNIT, unit.getSelectedItem().toString());
                        }else{
                            values.put(UNIT, unit2.getSelectedItem().toString());
                        }
                        db.insert(TABLE_NAME, null, values);
//
//                        切換頁面
//                        Toast.makeText(v.getContext(), "已新增輸入儲藏資料", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(NewTracecode.this, icebox.class);
                        startActivity(i);
                        finish();
                    }
                });
        alert.show();
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
