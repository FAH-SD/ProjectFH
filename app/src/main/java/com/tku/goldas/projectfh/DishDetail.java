package com.tku.goldas.projectfh;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

public class DishDetail extends Activity {
    TextView tv_food1, tv_food2, tv_food3, tv_food4, tv_food5, tv_food6, tv_food7, tv_food8, tv_food9;
    TextView tv_qfood1, tv_qfood2, tv_qfood3, tv_qfood4, tv_qfood5, tv_qfood6, tv_qfood7, tv_qfood8, tv_qfood9;
    TextView tv_ufood1, tv_ufood2, tv_ufood3, tv_ufood4, tv_ufood5, tv_ufood6, tv_ufood7, tv_ufood8, tv_ufood9;
    TextView cal1,cal2,cal3,cal4,cal5,cal6,cal7,cal8,cal9;
    TextView n1,n2,n3,n4,n5,n6,n7,n8,n9;
    TextView food2,food3,food4,food5,food6,food7,food8,food9;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    TextView tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9;
    EditText et_dishname, et_amount, et_date;
    String[] food_info;
    float a1,a2,a3,a4,a5,a6,a7,a8,a9;
    float b1,b2,b3,b4,b5,b6,b7,b8,b9;
    float total1 = 0, total2 = 0, total3 = 0, total4 =0, total5 = 0, total6 =0, total7 =0,total8 = 0, total9= 0, total_all = 0;
    String idk1,idk2,idk3,idk4,idk5,idk6,idk7,idk8,idk9;
    float totalcal;

    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private SimpleCursorAdapter adapter = null;

//    TextView et_member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);


        et_dishname = (EditText)findViewById(R.id.et_dishname);
//        et_member = (TextView)findViewById(R.id.et_member);
        et_amount = (EditText)findViewById(R.id.et_amount);
        et_date = (EditText)findViewById(R.id.et_date);

        food2 = (TextView)findViewById(R.id.food2);
        food3 = (TextView)findViewById(R.id.food3);
        food4 = (TextView)findViewById(R.id.food4);
        food5 = (TextView)findViewById(R.id.food5);
        food6 = (TextView)findViewById(R.id.food6);
        food7 = (TextView)findViewById(R.id.food7);
        food8 = (TextView)findViewById(R.id.food8);
        food9 = (TextView)findViewById(R.id.food9);

        tv_food1 = (TextView)findViewById(R.id.tv_food1);
        tv_food2 = (TextView)findViewById(R.id.tv_food2);
        tv_food3 = (TextView)findViewById(R.id.tv_food3);
        tv_food4 = (TextView)findViewById(R.id.tv_food4);
        tv_food5 = (TextView)findViewById(R.id.tv_food5);
        tv_food6 = (TextView)findViewById(R.id.tv_food6);
        tv_food7 = (TextView)findViewById(R.id.tv_food7);
        tv_food8 = (TextView)findViewById(R.id.tv_food8);
        tv_food9 = (TextView)findViewById(R.id.tv_food9);

        tv_qfood1 = (TextView)findViewById(R.id.tv_qfood1);
        tv_qfood2 = (TextView)findViewById(R.id.tv_qfood2);
        tv_qfood3 = (TextView)findViewById(R.id.tv_qfood3);
        tv_qfood4 = (TextView)findViewById(R.id.tv_qfood4);
        tv_qfood5 = (TextView)findViewById(R.id.tv_qfood5);
        tv_qfood6 = (TextView)findViewById(R.id.tv_qfood6);
        tv_qfood7 = (TextView)findViewById(R.id.tv_qfood7);
        tv_qfood8 = (TextView)findViewById(R.id.tv_qfood8);
        tv_qfood9 = (TextView)findViewById(R.id.tv_qfood9);

        tv_ufood1 = (TextView)findViewById(R.id.tv_ufood1);
        tv_ufood2 = (TextView)findViewById(R.id.tv_ufood2);
        tv_ufood3 = (TextView)findViewById(R.id.tv_ufood3);
        tv_ufood4 = (TextView)findViewById(R.id.tv_ufood4);
        tv_ufood5 = (TextView)findViewById(R.id.tv_ufood5);
        tv_ufood6 = (TextView)findViewById(R.id.tv_ufood6);
        tv_ufood7 = (TextView)findViewById(R.id.tv_ufood7);
        tv_ufood8 = (TextView)findViewById(R.id.tv_ufood8);
        tv_ufood9 = (TextView)findViewById(R.id.tv_ufood9);
        cal1=(TextView)findViewById(R.id.tv_cal1);
        cal2=(TextView)findViewById(R.id.tv_cal2);
        cal3=(TextView)findViewById(R.id.tv_cal3);
        cal4=(TextView)findViewById(R.id.tv_cal4);
        cal5=(TextView)findViewById(R.id.tv_cal5);
        cal6=(TextView)findViewById(R.id.tv_cal6);
        cal7=(TextView)findViewById(R.id.tv_cal7);
        cal8=(TextView)findViewById(R.id.tv_cal8);
        cal9=(TextView)findViewById(R.id.tv_cal9);
        n1=(TextView)findViewById(R.id.n1);
        n2=(TextView)findViewById(R.id.n2);
        n3=(TextView)findViewById(R.id.n3);
        n4=(TextView)findViewById(R.id.n4);
        n5=(TextView)findViewById(R.id.n5);
        n6=(TextView)findViewById(R.id.n6);
        n7=(TextView)findViewById(R.id.n7);
        n8=(TextView)findViewById(R.id.n8);
        n9=(TextView)findViewById(R.id.n9);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);
        t6=(TextView)findViewById(R.id.t6);
        t7=(TextView)findViewById(R.id.t7);
        t8=(TextView)findViewById(R.id.t8);
        t9=(TextView)findViewById(R.id.t9);
        tt1=(TextView)findViewById(R.id.tt1);
        tt2=(TextView)findViewById(R.id.tt2);
        tt3=(TextView)findViewById(R.id.tt3);
        tt4=(TextView)findViewById(R.id.tt4);
        tt5=(TextView)findViewById(R.id.tt5);
        tt6=(TextView)findViewById(R.id.tt6);
        tt7=(TextView)findViewById(R.id.tt7);
        tt8=(TextView)findViewById(R.id.tt8);
        tt9=(TextView)findViewById(R.id.tt9);
        Bundle bundle2 = this.getIntent().getExtras();
        et_dishname.setText(bundle2.getString("dishname"));
//        et_member.setText(bundle2.getString("member"));
        et_amount.setText(bundle2.getString("amount"));
        et_date.setText(bundle2.getString("date"));

        tv_food1.setText(bundle2.getString("f1"));
        tv_qfood1.setText(bundle2.getString("qf1"));
        tv_ufood1.setText(bundle2.getString("uf1"));
        tv_food2.setText(bundle2.getString("f2"));
        tv_qfood2.setText(bundle2.getString("qf2"));
        tv_ufood2.setText(bundle2.getString("uf2"));
        tv_food3.setText(bundle2.getString("f3"));
        tv_qfood3.setText(bundle2.getString("qf3"));
        tv_ufood3.setText(bundle2.getString("uf3"));
        tv_food4.setText(bundle2.getString("f4"));
        tv_qfood4.setText(bundle2.getString("qf4"));
        tv_ufood4.setText(bundle2.getString("uf4"));
        tv_food5.setText(bundle2.getString("f5"));
        tv_qfood5.setText(bundle2.getString("qf5"));
        tv_ufood5.setText(bundle2.getString("uf5"));
        tv_food6.setText(bundle2.getString("f6"));
        tv_qfood6.setText(bundle2.getString("qf6"));
        tv_ufood6.setText(bundle2.getString("uf6"));
        tv_food7.setText(bundle2.getString("f7"));
        tv_qfood7.setText(bundle2.getString("qf7"));
        tv_ufood7.setText(bundle2.getString("uf7"));
        tv_food8.setText(bundle2.getString("f8"));
        tv_qfood8.setText(bundle2.getString("qf8"));
        tv_ufood8.setText(bundle2.getString("uf8"));
        tv_food9.setText(bundle2.getString("f9"));
        tv_qfood9.setText(bundle2.getString("qf9"));
        tv_ufood9.setText(bundle2.getString("uf9"));

        db=(new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor= db.rawQuery("SELECT _id,b_item,b_quantity,b_unit,b_date from Shoppinglist WHERE b_date  LIKE '%" + et_date.getText().toString() + "%'",null);
        adapter = new SimpleCursorAdapter(this, R.layout.shoppinglistitem, cursor, new String[]{"b_item", "b_quantity", "b_unit"},new int[]{R.id.txtitem, R.id.txtquan, R.id.txtunit});


        new NetworkTask().execute(bundle2.getString("idf1"));

        if(!("".equals(tv_food2.getText().toString().trim()))){
            food2.setVisibility(View.VISIBLE);
            tv_food2.setVisibility(View.VISIBLE);
            tv_qfood2.setVisibility(View.VISIBLE);
            tv_ufood2.setVisibility(View.VISIBLE);
//            t2.setVisibility(View.VISIBLE);
//            cal2.setVisibility(View.VISIBLE);
//            tt2.setVisibility(View.VISIBLE);
            new NetworkTask2().execute(bundle2.getString("idf2"));
        }
        if(!("".equals(tv_food3.getText().toString().trim()))){
            food3.setVisibility(View.VISIBLE);
            tv_food3.setVisibility(View.VISIBLE);
            tv_qfood3.setVisibility(View.VISIBLE);
            tv_ufood3.setVisibility(View.VISIBLE);
//            t3.setVisibility(View.VISIBLE);
//            cal3.setVisibility(View.VISIBLE);
//            tt3.setVisibility(View.VISIBLE);
            new NetworkTask3().execute(bundle2.getString("idf3"));
        }
        if(!("".equals(tv_food4.getText().toString().trim()))){

            food4.setVisibility(View.VISIBLE);
            tv_food4.setVisibility(View.VISIBLE);
            tv_qfood4.setVisibility(View.VISIBLE);
            tv_ufood4.setVisibility(View.VISIBLE);
//            t4.setVisibility(View.VISIBLE);
//            cal4.setVisibility(View.VISIBLE);
//            tt4.setVisibility(View.VISIBLE);
            new NetworkTask4().execute(bundle2.getString("idf4"));
        }
        if(!("".equals(tv_food5.getText().toString().trim()))){

            food5.setVisibility(View.VISIBLE);
            tv_food5.setVisibility(View.VISIBLE);
            tv_qfood5.setVisibility(View.VISIBLE);
            tv_ufood5.setVisibility(View.VISIBLE);
//            t5.setVisibility(View.VISIBLE);
//            cal5.setVisibility(View.VISIBLE);
//            tt5.setVisibility(View.VISIBLE);
            new NetworkTask5().execute(bundle2.getString("idf5"));
        }
        if(!("".equals(tv_food6.getText().toString().trim()))){

            food6.setVisibility(View.VISIBLE);
            tv_food6.setVisibility(View.VISIBLE);
            tv_qfood6.setVisibility(View.VISIBLE);
            tv_ufood6.setVisibility(View.VISIBLE);
//            t6.setVisibility(View.VISIBLE);
//            cal6.setVisibility(View.VISIBLE);
//            tt6.setVisibility(View.VISIBLE);
            new NetworkTask6().execute(bundle2.getString("idf6"));
        }
        if(!("".equals(tv_food7.getText().toString().trim()))){

            food7.setVisibility(View.VISIBLE);
            tv_food7.setVisibility(View.VISIBLE);
            tv_qfood7.setVisibility(View.VISIBLE);
            tv_ufood7.setVisibility(View.VISIBLE);
//            t7.setVisibility(View.VISIBLE);
//            cal7.setVisibility(View.VISIBLE);
//            tt7.setVisibility(View.VISIBLE);
            new NetworkTask7().execute(bundle2.getString("idf7"));
        }
        if(!("".equals(tv_food8.getText().toString().trim()))){

            food8.setVisibility(View.VISIBLE);
            tv_food8.setVisibility(View.VISIBLE);
            tv_qfood8.setVisibility(View.VISIBLE);
            tv_ufood8.setVisibility(View.VISIBLE);
//            t8.setVisibility(View.VISIBLE);
//            cal8.setVisibility(View.VISIBLE);
//            tt8.setVisibility(View.VISIBLE);
            new NetworkTask8().execute(bundle2.getString("idf8"));
        }
        if(!("".equals(tv_food9.getText().toString().trim()))){

            food9.setVisibility(View.VISIBLE);
            tv_food9.setVisibility(View.VISIBLE);
            tv_qfood9.setVisibility(View.VISIBLE);
            tv_ufood9.setVisibility(View.VISIBLE);
//            t9.setVisibility(View.VISIBLE);
//            cal9.setVisibility(View.VISIBLE);
//            tt9.setVisibility(View.VISIBLE);
            new NetworkTask9().execute(bundle2.getString("idf9"));
        }

        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent1 = new Intent();
                intent1.setClass(DishDetail.this, dish.class);
                startActivity(intent1);
                DishDetail.this.finish();
            }
        });


        Button btn_slist = (Button)findViewById(R.id.btn_slist);
        btn_slist.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showAlert();

            }
        });

    }


    private void showAlert() {
        LayoutInflater inflater = LayoutInflater.from(DishDetail.this);
        final View view1 = inflater.inflate(R.layout.shoppinglist, null);

        final ListView listview = (ListView) view1.findViewById(R.id.listView5);
        listview.setAdapter(adapter);
        new AlertDialog.Builder(DishDetail.this).setTitle(et_date.getText().toString()+"購買清單")
                .setView(view1)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    class NetworkTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


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

                    Log.d("iceboxdbcreate", column[0] + "=" + food_info[0]);
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
                String a = food_info[0];
                return a;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            super.onPostExecute(number);
            n1.setText(number);
            idk1=n1.getText().toString();
            a1=Float.parseFloat(idk1);
            String idkk1 = tv_qfood1.getText().toString();
            b1=Float.parseFloat(idkk1);
            total1=a1*b1/100;
            total_all += total1;
            cal1.setText(String.valueOf(total_all));

        }
    }
    class NetworkTask2 extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


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

                    Log.d("iceboxdbcreate", column[0] + "=" + food_info[0]);
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
                String a = food_info[0];
                return a;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            n2.setText(number);
            idk2=n2.getText().toString();
            a2=Float.parseFloat(idk2);
            String idkk2 = tv_qfood2.getText().toString();
            b2=Float.parseFloat(idkk2);
            total2=a2*b2/100;
            total_all += total2;
            cal2.setText(String.valueOf(total2));
            cal1.setText(String.valueOf(total_all));

        }
    }
    class NetworkTask3 extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


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

                    Log.d("iceboxdbcreate", column[0] + "=" + food_info[0]);
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
                String a = food_info[0];
                return a;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            n3.setText(number);
            idk3=n3.getText().toString();
            a3=Float.parseFloat(idk3);
            String idkk2 = tv_qfood3.getText().toString();
            b3=Float.parseFloat(idkk2);
            total3=a3*b3/100;
            total_all += total3;
            cal3.setText(String.valueOf(total3));
            cal1.setText(String.valueOf(total_all));
        }
    }
    class NetworkTask4 extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


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

                    Log.d("iceboxdbcreate", column[0] + "=" + food_info[0]);
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
                String a = food_info[0];
                return a;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            n4.setText(number);
            idk4=n4.getText().toString();
            a4=Float.parseFloat(idk4);
            String idkk4 = tv_qfood4.getText().toString();
            b4=Float.parseFloat(idkk4);
            total4=a4*b4/100;
            total_all += total4;
            cal4.setText(String.valueOf(total4));
            cal1.setText(String.valueOf(total_all));
        }
    }
    class NetworkTask5 extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


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

                    Log.d("iceboxdbcreate", column[0] + "=" + food_info[0]);
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
                String a = food_info[0];
                return a;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            n5.setText(number);
            idk5=n5.getText().toString();
            a5=Float.parseFloat(idk5);
            String idkk5 = tv_qfood5.getText().toString();
            b5=Float.parseFloat(idkk5);
            total5=a5*b5/100;
            total_all += total5;
            cal5.setText(String.valueOf(total5));
            cal1.setText(String.valueOf(total_all));
        }
    }
    class NetworkTask6 extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


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

                    Log.d("iceboxdbcreate", column[0] + "=" + food_info[0]);
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
                String a = food_info[0];
                return a;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            n6.setText(number);
            idk6=n6.getText().toString();
            a6=Float.parseFloat(idk6);
            String idkk6 = tv_qfood6.getText().toString();
            b6=Float.parseFloat(idkk6);
            total6=a6*b6/100;
            total_all += total6;
            cal6.setText(String.valueOf(total6));
            cal1.setText(String.valueOf(total_all));
        }
    }
    class NetworkTask7 extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


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

                    Log.d("iceboxdbcreate", column[0] + "=" + food_info[0]);
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
                String a = food_info[0];
                return a;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            n7.setText(number);
            idk7=n7.getText().toString();
            a7=Float.parseFloat(idk7);
            String idkk7 = tv_qfood7.getText().toString();
            b7=Float.parseFloat(idkk7);
            total7=a7*b7/100;
            total_all += total7;
            cal7.setText(String.valueOf(total7));
            cal1.setText(String.valueOf(total_all));
        }
    }
    class NetworkTask8 extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


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

                    Log.d("iceboxdbcreate", column[0] + "=" + food_info[0]);
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
                String a = food_info[0];
                return a;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            n8.setText(number);
            idk8=n8.getText().toString();
            a8=Float.parseFloat(idk8);
            String idkk8 = tv_qfood8.getText().toString();
            b8=Float.parseFloat(idkk8);
            total8=a8*b8/100;
            total_all += total8;
            cal8.setText(String.valueOf(total8));
            cal1.setText(String.valueOf(total_all));
        }
    }
    class NetworkTask9 extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


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

                    Log.d("iceboxdbcreate", column[0] + "=" + food_info[0]);
                }
                else{
                    Log.d("iceboxdbcreate", "value = null");
                }
                String a = food_info[0];
                return a;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String number) {
            dialog.cancel();
            n9.setText(number);
            idk9=n9.getText().toString();
            a9=Float.parseFloat(idk9);
            String idkk9 = tv_qfood9.getText().toString();
            b9=Float.parseFloat(idkk9);
            total9=a9*b9/100;
            total_all += total9;
            Log.d("DishDetail", "total_all = " + total_all);
            cal9.setText(String.valueOf(total9));
            cal1.setText(String.valueOf(total_all));
        }
    }

}
