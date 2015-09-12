package com.tku.goldas.projectfh;

/**
 * Created by Goldas on 2015/6/10.
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.tku.goldas.projectfh.DBconstant.BUYINGDATE;
import static com.tku.goldas.projectfh.DBconstant.ITEM;
import static com.tku.goldas.projectfh.DBconstant.KIND;
import static com.tku.goldas.projectfh.DBconstant.LIMITDATE;
import static com.tku.goldas.projectfh.DBconstant.QUANTITY;
import static com.tku.goldas.projectfh.DBconstant.STORAGEPLACE;
import static com.tku.goldas.projectfh.DBconstant.TABLE_NAME;
import static com.tku.goldas.projectfh.DBconstant.UNIT;

public class iceboxdbcreate extends Activity implements View.OnClickListener{
    private int bYear, bMonth, bDay;
    private int lYear, lMonth, lDay;
    //private TextView result = null;
//    private ListView listData = null;
    private Spinner editkind;
    private Spinner edititem;
    private Spinner type;
    private EditText quantity ;
    private TextView tv_buyday;
    private TextView tv_limitday;
    private Spinner editstorage;
    private Spinner unit;
    private Spinner unit2;
    SQLiteDatabase dbrw;
    DBhelper dbhelper;
    private ImageButton btnitrue;

    private Spinner sp;//�Ĥ@�ӤU�Կ��
    private Spinner sp2;//�ĤG�ӤU�Կ��
    InputStream is=null;
    String result=null;
    String line=null;
    String liquid;
    String[] food_info;

    String[] id,roll_no,name;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfood1);
        dbhelper = new DBhelper(this);
        dbrw = dbhelper.getWritableDatabase();
        new NetworkTask().execute();


        goToinit();


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
        buttonback.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(iceboxdbcreate.this, icebox.class);
                startActivity(intent1);
                iceboxdbcreate.this.finish();
            }
        });


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
    public void goToinit(){


        findViews();
        setListeners();

    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        dbrw.close();

    }



    private void findViews(){
        //result = (TextView) findViewById(R.id.txtResult);
//        listData = (ListView) findViewById(R.id.listView);
        editkind = (Spinner) findViewById(R.id.spinner);
        edititem = (Spinner) findViewById(R.id.spinner2);
        type = (Spinner) findViewById(R.id.type);
        quantity = (EditText) findViewById(R.id.editText);
        tv_buyday = (TextView) findViewById(R.id.tv_buyday);
        tv_limitday = (TextView) findViewById(R.id.tv_limitday);
        editstorage = (Spinner) findViewById(R.id.sp_iplace);
        btnitrue = (ImageButton) findViewById(R.id.btn_itrue);
        unit = (Spinner) findViewById(R.id.sp_iunit);
        unit2 = (Spinner) findViewById(R.id.sp_iunit2);
        //btnUpdate = (Button) findViewById(R.id.btnUpdate);
//        btnitrue.setOnClickListener(this);
        //btnDel.setOnClickListener(this);
        //btnUpdate.setOnClickListener(this);
    }

    private void setListeners(){
        btnitrue.setOnClickListener(new itrue_ButtonClickListener());

    }
    class itrue_ButtonClickListener implements OnClickListener{
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

//    private Cursor getCursor(){
//        SQLiteDatabase db = dbhelper.getReadableDatabase();
//        String[] columns = {_ID,KIND, ITEM, QUANTITY, BUYINGDATE, LIMITDATE, STORAGEPLACE};
//
//        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
//        startManagingCursor(cursor);
//
//        return cursor;
//    }

//    private void show(){
//
//        Cursor cursor = getCursor();
//
//        StringBuilder resultData = new StringBuilder("RESULT: \n");
//
//        while(cursor.moveToNext()){
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String tel = cursor.getString(2);
//            String email = cursor.getString(3);
//
//            resultData.append(id).append(": ");
//            resultData.append(name).append(": ");
//            resultData.append(tel).append(": ");
//            resultData.append(email).append(": ");
//            resultData.append("\n");
//        }
//
//        result.setText(resultData);
//    }
//
//    private void showInList(){
//
//        Cursor cursor = getCursor();
//
//        String[] from = {KIND, ITEM, QUANTITY, BUYINGDATE, LIMITDATE, STORAGEPLACE };
//        int[] to = {R.id.txtkind, R.id.txtitem, R.id.txtquan, R.id.txtbd, R.id.txtld, R.id.txts};
//
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.teest, cursor, from, to);
//        listData.setAdapter(adapter);
//    }
//
//    private void del(){
//        String id = editId.getText().toString();
//
//        SQLiteDatabase db = dbhelper.getWritableDatabase();
//        db.delete(TABLE_NAME, _ID + "=" + id, null);
//
//        cleanEditText();
//    }
//
//    private void update(){
//        String id = editId.getText().toString();
//
//        ContentValues values = new ContentValues();
//        values.put(NAME, editName.getText().toString());
//        values.put(TEL, editTel.getText().toString());
//        values.put(EMAIL, editEmail.getText().toString());
//
//        SQLiteDatabase db = dbhelper.getWritableDatabase();
//        db.update(TABLE_NAME, values, _ID + "=" + id, null);
//
//        cleanEditText();
//    }

//    private void cleanEditText(){
//        editName.setText("");
//        editTel.setText("");
//        editEmail.setText("");
//        editId.setText("");
//    }

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
                        String strkind = editkind.getSelectedItem().toString();
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
                        Intent i = new Intent(iceboxdbcreate.this, icebox.class);
                        startActivity(i);
                        finish();
                    }
                });
        alert.show();
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


        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
// TODO Auto-generated method stub
                int k1 = 0,k2 = 0,k3 = 0,k4 = 0,k5 = 0,k6 = 0;
                int t1=-1,t2=-1,t3=-1,t4=-1,t5=-1,t6 = -1;
                for(int i=0;i<editkind.getCount();i++){
                    String temp = roll_no[i];
                    if(temp.contains("五穀")){
                        k1 = i;
                        t1++;
                    }
                    if(temp.contains("奶類")){
                        k2 = i;
                        t2++;
                    }
                    if(temp.contains("水果")){
                        k3 = i;
                        t3++;
                    }
                    if(temp.contains("油脂")){
                        k4 = i;
                        t4++;
                    }
                    if(temp.contains("蔬菜")){
                        k5 = i;
                        t5++;
                    }
                    if(temp.contains("蛋豆")){
                        k6 = i;
                        t6++;
                    }
                }



                if (type.getSelectedItemPosition() == 0) {
                    editkind.setSelection(k1-t1);
                }
                if (type.getSelectedItemPosition() == 1) {
                    editkind.setSelection(k2-t2);
                }
                if (type.getSelectedItemPosition() == 2) {
                    editkind.setSelection(k3-t3);
                }
                if (type.getSelectedItemPosition() == 3) {
                    editkind.setSelection(k4-t4);
                }
                if (type.getSelectedItemPosition() == 4) {
                    editkind.setSelection(k5-t5);
                }
                if (type.getSelectedItemPosition() == 5) {
                    editkind.setSelection(k6-t6);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub
            }
        });

        editkind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
// TODO Auto-generated method stub

                String text = editkind.getSelectedItem().toString();
                text = text.replace(" ","");
                if (text.equals("五穀根莖類")) {
                    type.setSelection(0);

                }
                if (text.equals("奶類")) {
                    type.setSelection(1);
                }
                if (text.equals("水果類")) {
                    type.setSelection(2);
                }
                if (text.equals("油脂類")) {
                    type.setSelection(3);
                }
                if (text.equals("蔬菜類")) {
                    type.setSelection(4);
                }
                if (text.equals("蛋豆魚肉類")) {
                    type.setSelection(5);
                }
                edititem.setSelection(position);
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

                editkind.setSelection(position);

                liquid = edititem.getSelectedItem().toString();
                if (liquid.contains("脂鮮乳") || liquid.contains("養樂多") || liquid.contains("優酪乳") || (liquid.contains("牛油") || liquid.contains("豬油") || liquid.contains("雞油") || liquid.contains("花生油") || liquid.contains("葵花油") || liquid.contains("玉米油") || liquid.contains("沙拉油") || liquid.contains("橄欖油") || liquid.contains("辣椒油") || liquid.contains("椰子油") | liquid.contains("芝麻油"))) {
                    unit.setVisibility(View.GONE);
                    unit2.setVisibility(View.VISIBLE);
                } else {
                    unit2.setVisibility(View.GONE);
                    unit.setVisibility(View.VISIBLE);
                }
                Log.d("iceboxdbcreate", "position = " + id[position]);
                new NetworkTask2().execute(id[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub
            }
        });

    }

    class NetworkTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(iceboxdbcreate.this);

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
                id = new String[JA.length()];

                for (int i = 0; i < JA.length(); i++) {
                    json = JA.getJSONObject(i);
                    roll_no[i] = json.getString("nu_kind");
                    name[i] = json.getString("nu_foodname");
                    id[i] = json.getString("id");
                }
//                Toast.makeText(getApplicationContext(), "sss", Toast.LENGTH_LONG).show();

                for (int i = 0; i < roll_no.length; i++) {
                    List<String> list1 = new ArrayList<String>();
                    List<String> list2 = new ArrayList<String>();
                    List<String> list3 = new ArrayList<String>();

                    list1.add(roll_no[i]);
                    list2.add(name[i]);
                    list3.add(id[i]);
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



    class NetworkTask2 extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(iceboxdbcreate.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<NameValuePair> vars=new ArrayList< NameValuePair>();
                vars.add(new BasicNameValuePair("id",params[0]));
                method.setEntity(new UrlEncodedFormEntity(vars, HTTP.UTF_8));

                //接收PHP回傳的資料
                HttpResponse response = httpclient.execute(method);
                HttpEntity entity = response.getEntity();
                String value = EntityUtils.toString(entity, "utf-8");

                food_info = new String [12];
                String []column = {"nu_caloric", "nu_water", "nu_prote", "nu_fat", "nu_carbon", "nu_na", "nu_k", "nu_ca", "nu_mg", "nu_p", "nu_fe", "nu_zn"};

//                JSONObject jsonObject = new JSONObject(value);

//                String nu_caloric = jsonObject.getString("nu_caloric");
                if(entity != null){
                    for(int i=0; i<column.length; i++) {
                        food_info[i] = new JSONArray(value).getJSONObject(0).getString(column[i]);
                        Log.d("iceboxdbcreate", column[i] + "=" + food_info[i]);
                    }
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
        }
    }

}

