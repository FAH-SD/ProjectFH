package com.example.goldas.projectfh;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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
import java.lang.reflect.Method;
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

public class iceboxdbcreate extends Activity {
    private int bYear, bMonth, bDay;
    private int lYear, lMonth, lDay;
    //private TextView result = null;
//    private ListView listData = null;
    private Spinner editkind;
    private Spinner edititem;
    private EditText quantity ;
    private TextView tv_buyday;
    private TextView tv_limitday;
    private Spinner editstorage;
    SQLiteDatabase dbrw;
    DBhelper dbhelper;
    private ImageButton btnitrue;

    private Spinner sp;//�Ĥ@�ӤU�Կ��
    private Spinner sp2;//�ĤG�ӤU�Կ��
    InputStream is=null;
    String result=null;
    String line=null;

    String[] roll_no,name;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfood1);
        dbhelper = new DBhelper(this);
        dbrw = dbhelper.getWritableDatabase();

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

        Button buttonin1 = (Button)findViewById(R.id.btn_iin2);
        buttonin1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(iceboxdbcreate.this, newfood2.class);
                startActivity(intent2);
                iceboxdbcreate.this.finish();
            }
        });
    new NetworkTask().execute();






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
        quantity = (EditText) findViewById(R.id.editText);
        tv_buyday = (TextView) findViewById(R.id.tv_buyday);
        tv_limitday = (TextView) findViewById(R.id.tv_limitday);
        editstorage = (Spinner) findViewById(R.id.sp_iplace);
        btnitrue = (ImageButton) findViewById(R.id.btn_itrue);

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
                iceboxAlert("警告","是否確定新增此筆資料？");
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
                        values.put(KIND, editkind.getSelectedItem().toString());
                        values.put(ITEM, edititem.getSelectedItem().toString());
                        values.put(QUANTITY, quantity.getText().toString());
                        values.put(BUYINGDATE, tv_buyday.getText().toString());
                        values.put(LIMITDATE, tv_limitday.getText().toString());
                        values.put(STORAGEPLACE, editstorage.getSelectedItem().toString());
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        setIconEnable(menu, true);

        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        // TODO Auto-generated method stub
        return super.onPrepareOptionsMenu(menu);
    }

    //enable为true时，菜单添加图标有效，enable为false时无效。4.0系统默认无效
    private void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.homepage:
                Intent intenthomepage = new Intent();
                intenthomepage.setClass(this, homepage.class);
                startActivity(intenthomepage);
                this.finish();
                break;
            case R.id.family:
                Intent intentfamily = new Intent();
                intentfamily.setClass(this, family.class);
                startActivity(intentfamily);
                this.finish();
                break;
            case R.id.icebox:
                Intent intenticebox = new Intent();
                intenticebox.setClass(this, icebox.class);
                startActivity(intenticebox);
                this.finish();
                break;

            default:
                return super.onOptionsItemSelected(item);

        }

        return  true;
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


        editkind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id)
            {
// TODO Auto-generated method stub

                edititem.setSelection(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
// TODO Auto-generated method stub
            }

        });


        edititem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
// TODO Auto-generated method stub

                editkind.setSelection(position);

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
return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            spinner_fn();

        }
    }



}

