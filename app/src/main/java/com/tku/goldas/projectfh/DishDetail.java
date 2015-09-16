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
    TextView cal9,tw,tp,tf,tc;
    TextView n1,n2,n3,n4,n5,n6,n7,n8,n9;
    TextView food2,food3,food4,food5,food6,food7,food8,food9;
    EditText et_dishname, et_amount, et_date;
    String[] food_info;
    float a1,a2,a3,a4,a5,a6,a7,a8,a9;
    float b1,b2,b3,b4,b5,b6,b7,b8,b9;
    float c1,c2,c3,c4,c5,c6,c7,c8,c9;
    float d1,d2,d3,d4,d5,d6,d7,d8,d9;
    float e1,e2,e3,e4,e5,e6,e7,e8,e9;
    float m1,m2,m3,m4,m5,m6,m7,m8,m9;
    float totalcal1 = 0, totalcal2 = 0, totalcal3 = 0, totalcal4 =0, totalcal5 = 0, totalcal6 =0, totalcal7 =0,totalcal8 = 0, totalcal9= 0, total_all = 0;
    float totalwater1 = 0, totalwater_all = 0,totalwater2=0,totalwater3=0,totalwater4=0,totalwater5=0,totalwater6=0,totalwater7=0,totalwater8=0,totalwater9=0;
    float totalfat1 = 0,totalfat_all=0,totalfat2 = 0,totalfat3= 0,totalfat4 = 0,totalfat5 = 0,totalfat6 = 0,totalfat7 = 0,totalfat8 = 0,totalfat9 = 0;
    float totalprot1 = 0,totalprot_all=0,totalprot2 = 0,totalprot3 = 0,totalprot4 = 0,totalprot5 = 0,totalprot6 = 0,totalprot7 = 0,totalprot8 = 0,totalprot9 = 0;
    float totalcarbon1 = 0,totalcarbon_all=0,totalcarbon2 = 0,totalcarbon3 = 0,totalcarbon4 = 0,totalcarbon5 = 0,totalcarbon6 = 0,totalcarbon7 = 0,totalcarbon8 = 0,totalcarbon9 = 0;
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
        cal9=(TextView)findViewById(R.id.tv_cal9);
        tw=(TextView)findViewById(R.id.tv_water);
        tf=(TextView)findViewById(R.id.tv_fat);
        tp=(TextView)findViewById(R.id.tv_prot);
        tc=(TextView)findViewById(R.id.tv_carbon);
        n1=(TextView)findViewById(R.id.n1);
        n2=(TextView)findViewById(R.id.n2);
        n3=(TextView)findViewById(R.id.n3);
        n4=(TextView)findViewById(R.id.n4);
        n5=(TextView)findViewById(R.id.n5);
        n6=(TextView)findViewById(R.id.n6);
        n7=(TextView)findViewById(R.id.n7);
        n8=(TextView)findViewById(R.id.n8);
        n9=(TextView)findViewById(R.id.n9);

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

            new NetworkTask5().execute(bundle2.getString("idf5"));
        }
        if(!("".equals(tv_food6.getText().toString().trim()))){

            food6.setVisibility(View.VISIBLE);
            tv_food6.setVisibility(View.VISIBLE);
            tv_qfood6.setVisibility(View.VISIBLE);
            tv_ufood6.setVisibility(View.VISIBLE);

            new NetworkTask6().execute(bundle2.getString("idf6"));
        }
        if(!("".equals(tv_food7.getText().toString().trim()))){

            food7.setVisibility(View.VISIBLE);
            tv_food7.setVisibility(View.VISIBLE);
            tv_qfood7.setVisibility(View.VISIBLE);
            tv_ufood7.setVisibility(View.VISIBLE);

            new NetworkTask7().execute(bundle2.getString("idf7"));
        }
        if(!("".equals(tv_food8.getText().toString().trim()))){

            food8.setVisibility(View.VISIBLE);
            tv_food8.setVisibility(View.VISIBLE);
            tv_qfood8.setVisibility(View.VISIBLE);
            tv_ufood8.setVisibility(View.VISIBLE);

            new NetworkTask8().execute(bundle2.getString("idf8"));
        }
        if(!("".equals(tv_food9.getText().toString().trim()))){

            food9.setVisibility(View.VISIBLE);
            tv_food9.setVisibility(View.VISIBLE);
            tv_qfood9.setVisibility(View.VISIBLE);
            tv_ufood9.setVisibility(View.VISIBLE);

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

    public class nutrii{
        public String calo;
        public String prot;
        public String carbon;
        public String water;
        public String fat;

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

    class NetworkTask extends AsyncTask<String, Void, nutrii> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected nutrii doInBackground(String... params) {

            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<BasicNameValuePair> vars=new ArrayList<>();
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



            }
            catch(Exception e){
                e.printStackTrace();
            }
            String calo = food_info[0];
            String water = food_info[1];
            String prot = food_info[2];
            String fat = food_info[3];
            String carbon = food_info[4];

            nutrii n = new nutrii();
            n.calo=calo;
            n.water=water;
            n.prot=prot;
            n.fat=fat;
            n.carbon=carbon;

            return n;
        }


        @Override
        protected void onPostExecute(nutrii n) {
            dialog.cancel();
            a1=Float.parseFloat(n.calo);
            b1=Float.parseFloat(n.water);
            c1=Float.parseFloat(n.prot);
            d1=Float.parseFloat(n.fat);
            e1=Float.parseFloat(n.carbon);

            String idkk1 = tv_qfood1.getText().toString();
            m1=Float.parseFloat(idkk1);
            totalcal1=a1*m1/100;
            totalwater1=b1*m1/100;
            totalprot1=c1*m1/100;
            totalfat1=d1*m1/100;
            totalcarbon1=e1*m1/100;
            total_all += totalcal1;
            totalwater_all += totalwater1;
            totalprot_all += totalprot1;
            totalfat_all+=totalfat1;
            totalcarbon_all+=totalcarbon1;

            cal9.setText(String.valueOf(total_all));
            tw.setText(String.valueOf(totalwater_all));
            tp.setText(String.valueOf(totalprot_all));
            tc.setText(String.valueOf(totalcarbon_all));
            tf.setText(String.valueOf(totalfat_all));

            //            n1.setText(number);
//            idk1=n1.getText().toString();
//            a1=Float.parseFloat(idk1);

        }
    }
    class NetworkTask2 extends AsyncTask<String, Void, nutrii> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected nutrii doInBackground(String... params) {

            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<BasicNameValuePair> vars=new ArrayList<>();
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
            }
            catch(Exception e){
                e.printStackTrace();
            }
            String calo = food_info[0];
            String water = food_info[1];
            String prot = food_info[2];
            String fat = food_info[3];
            String carbon = food_info[4];

            nutrii n = new nutrii();
            n.calo=calo;
            n.water=water;
            n.prot=prot;
            n.fat=fat;
            n.carbon=carbon;

            return n;
        }


        @Override
        protected void onPostExecute(nutrii n) {
            dialog.cancel();
            a2=Float.parseFloat(n.calo);
            b2=Float.parseFloat(n.water);
            c2=Float.parseFloat(n.prot);
            d2=Float.parseFloat(n.fat);
            e2=Float.parseFloat(n.carbon);

            String idkk1 = tv_qfood2.getText().toString();
            m2=Float.parseFloat(idkk1);
            totalcal2=a2*m2/100;
            totalwater2=b2*m2/100;
            totalprot2=c2*m2/100;
            totalfat2=d2*m2/100;
            totalcarbon2=e2*m2/100;
            total_all += totalcal2;
            totalwater_all += totalwater2;
            totalprot_all += totalprot2;
            totalfat_all+=totalfat2;
            totalcarbon_all+=totalcarbon2;

            cal9.setText(String.valueOf(total_all));
            tw.setText(String.valueOf(totalwater_all));
            tp.setText(String.valueOf(totalprot_all));
            tc.setText(String.valueOf(totalcarbon_all));
            tf.setText(String.valueOf(totalfat_all));

        }
    }

    class NetworkTask3 extends AsyncTask<String, Void, nutrii> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected nutrii doInBackground(String... params) {

            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<BasicNameValuePair> vars=new ArrayList<>();
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
            }
            catch(Exception e){
                e.printStackTrace();
            }
            String calo = food_info[0];
            String water = food_info[1];
            String prot = food_info[2];
            String fat = food_info[3];
            String carbon = food_info[4];

            nutrii n = new nutrii();
            n.calo=calo;
            n.water=water;
            n.prot=prot;
            n.fat=fat;
            n.carbon=carbon;

            return n;
        }


        @Override
        protected void onPostExecute(nutrii n) {
            dialog.cancel();
            a3=Float.parseFloat(n.calo);
            b3=Float.parseFloat(n.water);
            c3=Float.parseFloat(n.prot);
            d3=Float.parseFloat(n.fat);
            e3=Float.parseFloat(n.carbon);

            String idkk1 = tv_qfood3.getText().toString();
            m3=Float.parseFloat(idkk1);
            totalcal3=a3*m3/100;
            totalwater3=b3*m3/100;
            totalprot3=c3*m3/100;
            totalfat3=d3*m3/100;
            totalcarbon3=e3*m3/100;
            total_all += totalcal3;
            totalwater_all += totalwater3;
            totalprot_all += totalprot3;
            totalfat_all+=totalfat3;
            totalcarbon_all+=totalcarbon3;

            cal9.setText(String.valueOf(total_all));
            tw.setText(String.valueOf(totalwater_all));
            tp.setText(String.valueOf(totalprot_all));
            tc.setText(String.valueOf(totalcarbon_all));
            tf.setText(String.valueOf(totalfat_all));

        }
    }

    class NetworkTask4 extends AsyncTask<String, Void, nutrii> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected nutrii doInBackground(String... params) {

            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<BasicNameValuePair> vars=new ArrayList<>();
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
            }
            catch(Exception e){
                e.printStackTrace();
            }
            String calo = food_info[0];
            String water = food_info[1];
            String prot = food_info[2];
            String fat = food_info[3];
            String carbon = food_info[4];

            nutrii n = new nutrii();
            n.calo=calo;
            n.water=water;
            n.prot=prot;
            n.fat=fat;
            n.carbon=carbon;

            return n;
        }


        @Override
        protected void onPostExecute(nutrii n) {
            dialog.cancel();
            a4=Float.parseFloat(n.calo);
            b4=Float.parseFloat(n.water);
            c4=Float.parseFloat(n.prot);
            d4=Float.parseFloat(n.fat);
            e4=Float.parseFloat(n.carbon);

            String idkk1 = tv_qfood4.getText().toString();
            m4=Float.parseFloat(idkk1);
            totalcal4=a4*m4/100;
            totalwater4=b4*m4/100;
            totalprot4=c4*m4/100;
            totalfat4=d4*m4/100;
            totalcarbon4=e4*m4/100;
            total_all += totalcal4;
            totalwater_all += totalwater4;
            totalprot_all += totalprot4;
            totalfat_all+=totalfat4;
            totalcarbon_all+=totalcarbon4;

            cal9.setText(String.valueOf(total_all));
            tw.setText(String.valueOf(totalwater_all));
            tp.setText(String.valueOf(totalprot_all));
            tc.setText(String.valueOf(totalcarbon_all));
            tf.setText(String.valueOf(totalfat_all));

        }
    }
    class NetworkTask5 extends AsyncTask<String, Void, nutrii> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected nutrii doInBackground(String... params) {

            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<BasicNameValuePair> vars=new ArrayList<>();
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
            }
            catch(Exception e){
                e.printStackTrace();
            }
            String calo = food_info[0];
            String water = food_info[1];
            String prot = food_info[2];
            String fat = food_info[3];
            String carbon = food_info[4];

            nutrii n = new nutrii();
            n.calo=calo;
            n.water=water;
            n.prot=prot;
            n.fat=fat;
            n.carbon=carbon;

            return n;
        }


        @Override
        protected void onPostExecute(nutrii n) {
            dialog.cancel();
            a5=Float.parseFloat(n.calo);
            b5=Float.parseFloat(n.water);
            c5=Float.parseFloat(n.prot);
            d5=Float.parseFloat(n.fat);
            e5=Float.parseFloat(n.carbon);

            String idkk1 = tv_qfood5.getText().toString();
            m5=Float.parseFloat(idkk1);
            totalcal5=a5*m5/100;
            totalwater5=b5*m5/100;
            totalprot5=c5*m5/100;
            totalfat5=d5*m5/100;
            totalcarbon5=e5*m5/100;
            total_all += totalcal5;
            totalwater_all += totalwater5;
            totalprot_all += totalprot5;
            totalfat_all+=totalfat5;
            totalcarbon_all+=totalcarbon5;

            cal9.setText(String.valueOf(total_all));
            tw.setText(String.valueOf(totalwater_all));
            tp.setText(String.valueOf(totalprot_all));
            tc.setText(String.valueOf(totalcarbon_all));
            tf.setText(String.valueOf(totalfat_all));

        }
    }
    class NetworkTask6 extends AsyncTask<String, Void, nutrii> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected nutrii doInBackground(String... params) {

            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<BasicNameValuePair> vars=new ArrayList<>();
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
            }
            catch(Exception e){
                e.printStackTrace();
            }
            String calo = food_info[0];
            String water = food_info[1];
            String prot = food_info[2];
            String fat = food_info[3];
            String carbon = food_info[4];

            nutrii n = new nutrii();
            n.calo=calo;
            n.water=water;
            n.prot=prot;
            n.fat=fat;
            n.carbon=carbon;

            return n;
        }


        @Override
        protected void onPostExecute(nutrii n) {
            dialog.cancel();
            a6=Float.parseFloat(n.calo);
            b6=Float.parseFloat(n.water);
            c6=Float.parseFloat(n.prot);
            d6=Float.parseFloat(n.fat);
            e6=Float.parseFloat(n.carbon);

            String idkk1 = tv_qfood6.getText().toString();
            m6=Float.parseFloat(idkk1);
            totalcal6=a6*m6/100;
            totalwater6=b6*m6/100;
            totalprot6=c6*m6/100;
            totalfat6=d6*m6/100;
            totalcarbon6=e6*m6/100;
            total_all += totalcal6;
            totalwater_all += totalwater6;
            totalprot_all += totalprot6;
            totalfat_all+=totalfat6;
            totalcarbon_all+=totalcarbon6;

            cal9.setText(String.valueOf(total_all));
            tw.setText(String.valueOf(totalwater_all));
            tp.setText(String.valueOf(totalprot_all));
            tc.setText(String.valueOf(totalcarbon_all));
            tf.setText(String.valueOf(totalfat_all));

        }
    }
    class NetworkTask7 extends AsyncTask<String, Void, nutrii> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected nutrii doInBackground(String... params) {

            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<BasicNameValuePair> vars=new ArrayList<>();
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
            }
            catch(Exception e){
                e.printStackTrace();
            }
            String calo = food_info[0];
            String water = food_info[1];
            String prot = food_info[2];
            String fat = food_info[3];
            String carbon = food_info[4];

            nutrii n = new nutrii();
            n.calo=calo;
            n.water=water;
            n.prot=prot;
            n.fat=fat;
            n.carbon=carbon;

            return n;
        }


        @Override
        protected void onPostExecute(nutrii n) {
            dialog.cancel();
            a7=Float.parseFloat(n.calo);
            b7=Float.parseFloat(n.water);
            c7=Float.parseFloat(n.prot);
            d7=Float.parseFloat(n.fat);
            e7=Float.parseFloat(n.carbon);

            String idkk1 = tv_qfood7.getText().toString();
            m7=Float.parseFloat(idkk1);
            totalcal7=a7*m7/100;
            totalwater7=b7*m7/100;
            totalprot7=c7*m7/100;
            totalfat7=d7*m7/100;
            totalcarbon7=e7*m7/100;
            total_all += totalcal7;
            totalwater_all += totalwater7;
            totalprot_all += totalprot7;
            totalfat_all+=totalfat7;
            totalcarbon_all+=totalcarbon7;

            cal9.setText(String.valueOf(total_all));
            tw.setText(String.valueOf(totalwater_all));
            tp.setText(String.valueOf(totalprot_all));
            tc.setText(String.valueOf(totalcarbon_all));
            tf.setText(String.valueOf(totalfat_all));

        }
    }
    class NetworkTask8 extends AsyncTask<String, Void, nutrii> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected nutrii doInBackground(String... params) {

            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<BasicNameValuePair> vars=new ArrayList<>();
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
            }
            catch(Exception e){
                e.printStackTrace();
            }
            String calo = food_info[0];
            String water = food_info[1];
            String prot = food_info[2];
            String fat = food_info[3];
            String carbon = food_info[4];

            nutrii n = new nutrii();
            n.calo=calo;
            n.water=water;
            n.prot=prot;
            n.fat=fat;
            n.carbon=carbon;

            return n;
        }


        @Override
        protected void onPostExecute(nutrii n) {
            dialog.cancel();
            a8=Float.parseFloat(n.calo);
            b8=Float.parseFloat(n.water);
            c8=Float.parseFloat(n.prot);
            d8=Float.parseFloat(n.fat);
            e8=Float.parseFloat(n.carbon);

            String idkk1 = tv_qfood8.getText().toString();
            m8=Float.parseFloat(idkk1);
            totalcal8=a8*m8/100;
            totalwater8=b8*m8/100;
            totalprot8=c8*m8/100;
            totalfat8=d8*m8/100;
            totalcarbon8=e8*m8/100;
            total_all += totalcal8;
            totalwater_all += totalwater8;
            totalprot_all += totalprot8;
            totalfat_all+=totalfat8;
            totalcarbon_all+=totalcarbon8;

            cal9.setText(String.valueOf(total_all));
            tw.setText(String.valueOf(totalwater_all));
            tp.setText(String.valueOf(totalprot_all));
            tc.setText(String.valueOf(totalcarbon_all));
            tf.setText(String.valueOf(totalfat_all));

        }
    }
    class NetworkTask9 extends AsyncTask<String, Void, nutrii> {
        ProgressDialog dialog = new ProgressDialog(DishDetail.this);


        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中..");
            dialog.setMessage("正在讀取中..");
            dialog.show();
//            dialog.show(newfood2.this,"讀取中..","正在讀取中..");
        }

        @Override
        protected nutrii doInBackground(String... params) {

            try
            {
                //連線到 url網址
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost("http://163.13.201.82/tes3t.php");

                //傳值給PHP
                List<BasicNameValuePair> vars=new ArrayList<>();
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
            }
            catch(Exception e){
                e.printStackTrace();
            }
            String calo = food_info[0];
            String water = food_info[1];
            String prot = food_info[2];
            String fat = food_info[3];
            String carbon = food_info[4];

            nutrii n = new nutrii();
            n.calo=calo;
            n.water=water;
            n.prot=prot;
            n.fat=fat;
            n.carbon=carbon;

            return n;
        }


        @Override
        protected void onPostExecute(nutrii n) {
            dialog.cancel();
            a9=Float.parseFloat(n.calo);
            b9=Float.parseFloat(n.water);
            c9=Float.parseFloat(n.prot);
            d9=Float.parseFloat(n.fat);
            e9=Float.parseFloat(n.carbon);

            String idkk1 = tv_qfood9.getText().toString();
            m9=Float.parseFloat(idkk1);
            totalcal9=a9*m9/100;
            totalwater9=b9*m9/100;
            totalprot9=c9*m9/100;
            totalfat9=d9*m9/100;
            totalcarbon9=e9*m9/100;
            total_all += totalcal9;
            totalwater_all += totalwater9;
            totalprot_all += totalprot9;
            totalfat_all+=totalfat9;
            totalcarbon_all+=totalcarbon9;

            cal9.setText(String.valueOf(total_all));
            tw.setText(String.valueOf(totalwater_all));
            tp.setText(String.valueOf(totalprot_all));
            tc.setText(String.valueOf(totalcarbon_all));
            tf.setText(String.valueOf(totalfat_all));

        }
    }
}
