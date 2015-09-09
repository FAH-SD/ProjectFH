package com.example.goldas.projectfh;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import static com.example.goldas.projectfh.DBconstant.BUYINGDATE;
import static com.example.goldas.projectfh.DBconstant.ITEM;
import static com.example.goldas.projectfh.DBconstant.KIND;
import static com.example.goldas.projectfh.DBconstant.LIMITDATE;
import static com.example.goldas.projectfh.DBconstant.QUANTITY;
import static com.example.goldas.projectfh.DBconstant.STORAGEPLACE;
import static com.example.goldas.projectfh.DBconstant.TABLE_NAME;
import static com.example.goldas.projectfh.DBconstant.UNIT;

public class DishFood2 extends Activity {

    Cursor cursor;
    String strtype = "全部";
    ListView foodlist;
    Button btn_ok;
    Button btn_search;
    EditText et_choose;
    Spinner sp_type;
    EditText et_item;
    String strbtn;
    String item, amount, unit;
    String tv_member, tv_amount, dishname;
    String tv_food1, tv_food2, tv_food3, tv_food4, tv_food5, tv_food6, tv_food7, tv_food8, tv_food9;
    String tv_qfood1, tv_qfood2, tv_qfood3, tv_qfood4, tv_qfood5, tv_qfood6, tv_qfood7, tv_qfood8, tv_qfood9;
    String tv_ufood1, tv_ufood2, tv_ufood3, tv_ufood4, tv_ufood5, tv_ufood6, tv_ufood7, tv_ufood8, tv_ufood9;

    InputStream is=null;
    String result=null;
    String line=null;
    String liquid;
    String[] food_info;

    String[] id,roll_no,name;
    String[] a1, a2, a3, a4, a5, a6;

    int b1 = 0,b2 = 0,b3 = 0,b4 = 0,b5 = 0,b6 = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_food2);
        et_choose = (EditText) findViewById(R.id.et_choose);
        Bundle bundle2 = this.getIntent().getExtras();
        tv_member = bundle2.getString("member");
        tv_amount = bundle2.getString("amount");
        dishname = bundle2.getString("dishname");

        tv_food1 = bundle2.getString("f1");
        tv_qfood1 = bundle2.getString("qf1");
        tv_ufood1 = bundle2.getString("uf1");
        tv_food2 = bundle2.getString("f2");
        tv_qfood2 = bundle2.getString("qf2");
        tv_ufood2 = bundle2.getString("uf2");
        tv_food3 = bundle2.getString("f3");
        tv_qfood3 = bundle2.getString("qf3");
        tv_ufood3 = bundle2.getString("uf3");
        tv_food4 = bundle2.getString("f4");
        tv_qfood4 = bundle2.getString("qf4");
        tv_ufood4 = bundle2.getString("uf4");
        tv_food5 = bundle2.getString("f5");
        tv_qfood5 = bundle2.getString("qf5");
        tv_ufood5 = bundle2.getString("uf5");
        tv_food6 = bundle2.getString("f6");
        tv_qfood6 = bundle2.getString("qf6");
        tv_ufood6 = bundle2.getString("uf6");
        tv_food7 = bundle2.getString("f7");
        tv_qfood7 = bundle2.getString("qf7");
        tv_ufood7 = bundle2.getString("uf7");
        tv_food8 = bundle2.getString("f8");
        tv_qfood8 = bundle2.getString("qf8");
        tv_ufood8 = bundle2.getString("uf8");
        tv_food9 = bundle2.getString("f9");
        tv_qfood9 = bundle2.getString("qf9");
        tv_ufood9 = bundle2.getString("uf9");
        strbtn = bundle2.getString("strbtn");

        sp_type = (Spinner) findViewById(R.id.type);
        foodlist = (ListView) findViewById(R.id.listView4);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_search = (Button) findViewById(R.id.btn_search);
        et_item = (EditText) findViewById(R.id.et_item);
        new NetworkTask().execute();


        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b1 = new Bundle();
                b1.putString("member", tv_member);
                b1.putString("amount", tv_amount);
                b1.putString("dishname", dishname);
                b1.putString("f1", tv_food1);
                b1.putString("qf1", tv_qfood1);
                b1.putString("uf1", tv_ufood1);
                b1.putString("f2", tv_food2);
                b1.putString("qf2", tv_qfood2);
                b1.putString("uf2", tv_ufood2);
                b1.putString("f3", tv_food3);
                b1.putString("qf3", tv_qfood3);
                b1.putString("uf3", tv_ufood3);
                b1.putString("f4", tv_food4);
                b1.putString("qf4", tv_qfood4);
                b1.putString("uf4", tv_ufood4);
                b1.putString("f5", tv_food5);
                b1.putString("qf5", tv_qfood5);
                b1.putString("uf5", tv_ufood5);
                b1.putString("f6", tv_food6);
                b1.putString("qf6", tv_qfood6);
                b1.putString("uf6", tv_ufood6);
                b1.putString("f7", tv_food7);
                b1.putString("qf7", tv_qfood7);
                b1.putString("uf7", tv_ufood7);
                b1.putString("f8", tv_food8);
                b1.putString("qf8", tv_qfood8);
                b1.putString("uf8", tv_ufood8);
                b1.putString("f9", tv_food9);
                b1.putString("qf9", tv_qfood9);
                b1.putString("uf9", tv_ufood9);
                b1.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(DishFood2.this, DishFood.class);
                intent1.putExtras(b1);
                startActivity(intent1);
                DishFood2.this.finish();

            }
        });
        ImageButton btn_true = (ImageButton) findViewById(R.id.btn_true);
        btn_true.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.equals("")) {
                    showAlert2("錯誤資訊", "數量尚未輸入");
                }else{
                    switch (strbtn) {
                        case "1":
                            tv_food1 = item;
                            tv_qfood1 = amount;
                            tv_ufood1 = unit;
                            break;
                        case "2":
                            tv_food2 = item;
                            tv_qfood2 = amount;
                            tv_ufood2 = unit;
                            break;
                        case "3":
                            tv_food3 = item;
                            tv_qfood3 = amount;
                            tv_ufood3 = unit;
                            break;
                        case "4":
                            tv_food4 = item;
                            tv_qfood4 = amount;
                            tv_ufood4 = unit;
                            break;
                        case "5":
                            tv_food5 = item;
                            tv_qfood5 = amount;
                            tv_ufood5 = unit;
                            break;
                        case "6":
                            tv_food6 = item;
                            tv_qfood6 = amount;
                            tv_ufood6 = unit;
                            break;
                        case "7":
                            tv_food7 = item;
                            tv_qfood7 = amount;
                            tv_ufood7 = unit;
                            break;
                        case "8":
                            tv_food8 = item;
                            tv_qfood8 = amount;
                            tv_ufood8 = unit;
                            break;
                        case "9":
                            tv_food9 = item;
                            tv_qfood9 = amount;
                            tv_ufood9 = unit;
                            break;
                    }
                    Bundle b1 = new Bundle();
                    b1.putString("member", tv_member);
                    b1.putString("amount", tv_amount);
                    b1.putString("f1", tv_food1);
                    b1.putString("qf1", tv_qfood1);
                    b1.putString("uf1", tv_ufood1);
                    b1.putString("f2", tv_food2);
                    b1.putString("qf2", tv_qfood2);
                    b1.putString("uf2", tv_ufood2);
                    b1.putString("f3", tv_food3);
                    b1.putString("qf3", tv_qfood3);
                    b1.putString("uf3", tv_ufood3);
                    b1.putString("f4", tv_food4);
                    b1.putString("qf4", tv_qfood4);
                    b1.putString("uf4", tv_ufood4);
                    b1.putString("f5", tv_food5);
                    b1.putString("qf5", tv_qfood5);
                    b1.putString("uf5", tv_ufood5);
                    b1.putString("f6", tv_food6);
                    b1.putString("qf6", tv_qfood6);
                    b1.putString("uf6", tv_ufood6);
                    b1.putString("f7", tv_food7);
                    b1.putString("qf7", tv_qfood7);
                    b1.putString("uf7", tv_ufood7);
                    b1.putString("f8", tv_food8);
                    b1.putString("qf8", tv_qfood8);
                    b1.putString("uf8", tv_ufood8);
                    b1.putString("f9", tv_food9);
                    b1.putString("qf9", tv_qfood9);
                    b1.putString("uf9", tv_ufood9);
                    b1.putString("strbtn", strbtn);
                    Intent intent1 = new Intent();
                    intent1.setClass(DishFood2.this, newdish.class);
                    intent1.putExtras(b1);
                    startActivity(intent1);
                    DishFood2.this.finish();
                }

            }
        });
    }

    private void showAlert() {
        LayoutInflater inflater = LayoutInflater.from(DishFood2.this);
        final View view1 = inflater.inflate(R.layout.amountview2, null);

        final EditText editText = (EditText) view1.findViewById(R.id.edittext);
        final Spinner sp_unit = (Spinner) view1.findViewById(R.id.sp_unit1);

                final Spinner sp_unit2 = (Spinner) view1.findViewById(R.id.sp_unit2);
                if (item.contains("脂鮮乳") || item.contains("養樂多") || item.contains("優酪乳") || (item.contains("牛油") || item.contains("豬油") || item.contains("雞油") || item.contains("花生油") || item.contains("葵花油") || item.contains("玉米油") || item.contains("沙拉油") || item.contains("橄欖油") || item.contains("辣椒油") || item.contains("椰子油") | item.contains("芝麻油"))) {
                    sp_unit.setVisibility(View.GONE);
                    sp_unit2.setVisibility(View.VISIBLE);
                } else {
                    sp_unit2.setVisibility(View.GONE);
                    sp_unit.setVisibility(View.VISIBLE);
                }
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sp_unit.getVisibility() == View.VISIBLE) {
                    unit = sp_unit.getSelectedItem().toString();
                } else {
                    unit = sp_unit2.getSelectedItem().toString();
                }
                if (editText.getText().toString() != ""
                        && editText.getText().toString() != null) {
                    amount = editText.getText().toString();
                } else {
                    amount = "";
                }
                String text = item + ", " + amount + unit;
                et_choose.setText(text);
            }
        });
        sp_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                unit = sp_unit.getSelectedItem().toString();

                if (editText.getText().toString() != ""
                        && editText.getText().toString() != null) {
                    amount = editText.getText().toString();
                } else {
                    amount = "";
                }

                String text = item + ", " + amount + unit;
                et_choose.setText(text);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        sp_unit2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                unit = sp_unit2.getSelectedItem().toString();

                if (editText.getText().toString() != ""
                        && editText.getText().toString() != null) {
                    amount = editText.getText().toString();
                } else {
                    amount = "";
                }

                String text = item + ", " + amount + unit;
                et_choose.setText(text);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        new AlertDialog.Builder(DishFood2.this).setTitle("選取" + item )
                .setMessage("請輸入數量").setView(view1)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    private void showAlert2(String title, String context) {
        android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
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
    // 點擊空白區域 自動隱藏鍵盤
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){

            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }

    private void spinner_fn() {
// TODO Auto-generated method stub

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.dishfoodlist3, name);
        dataAdapter1.setDropDownViewResource(R.layout.spinnerlayout);
        foodlist.setAdapter(dataAdapter1);

        foodlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = foodlist.getItemAtPosition(position).toString();
                showAlert();
            }
        });



        btn_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tchose = sp_type.getSelectedItemPosition();

                switch (tchose) {
                    case 0:
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.dishfoodlist3, name);
                        dataAdapter.setDropDownViewResource(R.layout.spinnerlayout);
                        foodlist.setAdapter(dataAdapter);
                        break;
                    case 1:
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("五穀")) {
                                b1++;
                            }
                        }
                        a1 = new String[b1];
                        b1 = 0;
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("五穀")) {
                                a1[b1] = name[i];
                                b1++;
                            }
                        }
                        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.dishfoodlist3, a1);
                        dataAdapter1.setDropDownViewResource(R.layout.spinnerlayout);
                        foodlist.setAdapter(dataAdapter1);
                        break;
                    case 2:
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("蛋豆")) {
                                b2++;
                            }
                        }
                        a2 = new String[b2];
                        b2 = 0;
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("蛋豆")) {
                                a2[b2] = name[i];
                                b2++;
                            }
                        }
                        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.dishfoodlist3, a2);
                        dataAdapter2.setDropDownViewResource(R.layout.spinnerlayout);
                        foodlist.setAdapter(dataAdapter2);
                        break;
                    case 3:
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("蔬菜")) {
                                b3++;
                            }
                        }
                        a3 = new String[b3];
                        b3 = 0;
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("蔬菜")) {
                                a3[b3] = name[i];
                                b3++;
                            }
                        }
                        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.dishfoodlist3, a3);
                        dataAdapter3.setDropDownViewResource(R.layout.spinnerlayout);
                        foodlist.setAdapter(dataAdapter3);
                        break;
                    case 4:
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("水果")) {
                                b4++;
                            }
                        }
                        a4 = new String[b4];
                        b4 = 0;
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("水果")) {
                                a4[b4] = name[i];
                                b4++;
                            }
                        }
                        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.dishfoodlist3, a4);
                        dataAdapter4.setDropDownViewResource(R.layout.spinnerlayout);
                        foodlist.setAdapter(dataAdapter4);
                        break;
                    case 5:
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("奶類")) {
                                b5++;
                            }
                        }
                        a5 = new String[b5];
                        b5 = 0;
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("奶類")) {
                                a5[b5] = name[i];
                                b5++;
                            }
                        }
                        ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.dishfoodlist3, a5);
                        dataAdapter5.setDropDownViewResource(R.layout.spinnerlayout);
                        foodlist.setAdapter(dataAdapter5);
                        break;
                    case 6:
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("油脂")) {
                                b6++;
                            }
                        }
                        a6 = new String[b6];
                        b6 = 0;
                        for (int i = 0; i < name.length; i++) {
                            if (roll_no[i].contains("油脂")) {
                                a6[b6] = name[i];
                                b6++;
                            }
                        }
                        ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.dishfoodlist3, a6);
                        dataAdapter6.setDropDownViewResource(R.layout.spinnerlayout);
                        foodlist.setAdapter(dataAdapter6);
                        break;
                }
            }
        });

        btn_search.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = 0;
                for (int i = 0; i < name.length; i++) {
                    if (name[i].contains(et_item.getText().toString())) {
                        c++;
                    }
                }
                if (c > 0) {
                    String d[] = new String[c];
                    c = 0;
                    for (int i = 0; i < name.length; i++) {
                        if (name[i].contains(et_item.getText().toString())) {
                            d[c] = name[i];
                            c++;
                        }
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.dishfoodlist3, d);
                    dataAdapter.setDropDownViewResource(R.layout.spinnerlayout);
                    foodlist.setAdapter(dataAdapter);
                } else {
                    showAlert2("錯誤資訊","找不到此項目");
                }

            }
        });

    }

    class NetworkTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(DishFood2.this);

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

}