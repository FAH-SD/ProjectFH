package com.tku.goldas.projectfh;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tku.goldas.projectfh.DDBconstant.DTABLE_NAME;
import static com.tku.goldas.projectfh.DDBconstant.D_DISHNAME;
import static com.tku.goldas.projectfh.DDBconstant.D_MQUANTITY;
import static com.tku.goldas.projectfh.DDBconstant.D_MEMBER;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD1;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD1;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD1;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD2;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD2;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD2;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD3;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD3;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD3;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD4;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD4;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD4;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD5;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD5;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD5;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD6;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD6;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD6;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD7;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD7;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD7;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD8;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD8;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD8;
import static com.tku.goldas.projectfh.DDBconstant.D_FOOD9;
import static com.tku.goldas.projectfh.DDBconstant.D_QFOOD9;
import static com.tku.goldas.projectfh.DDBconstant.D_UFOOD9;
import static com.tku.goldas.projectfh.DDBconstant.D_DATE;
import static com.tku.goldas.projectfh.DDBconstant.ID_UFOOD1;
import static com.tku.goldas.projectfh.DDBconstant.ID_UFOOD2;
import static com.tku.goldas.projectfh.DDBconstant.ID_UFOOD3;
import static com.tku.goldas.projectfh.DDBconstant.ID_UFOOD4;
import static com.tku.goldas.projectfh.DDBconstant.ID_UFOOD5;
import static com.tku.goldas.projectfh.DDBconstant.ID_UFOOD6;
import static com.tku.goldas.projectfh.DDBconstant.ID_UFOOD7;
import static com.tku.goldas.projectfh.DDBconstant.ID_UFOOD8;
import static com.tku.goldas.projectfh.DDBconstant.ID_UFOOD9;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class newdish extends Activity implements View.OnClickListener{
    TextView tv_member, tv_amount;
    EditText dishname;
    Button btn_food1, btn_food2, btn_food3, btn_food4, btn_food5, btn_food6, btn_food7, btn_food8, btn_food9;
    TextView tv_food1, tv_food2, tv_food3, tv_food4, tv_food5, tv_food6, tv_food7, tv_food8, tv_food9;
    TextView tv_qfood1, tv_qfood2, tv_qfood3, tv_qfood4, tv_qfood5, tv_qfood6, tv_qfood7, tv_qfood8, tv_qfood9;
    TextView tv_ufood1, tv_ufood2, tv_ufood3, tv_ufood4, tv_ufood5, tv_ufood6, tv_ufood7, tv_ufood8, tv_ufood9;
    String itv_ufood1,itv_ufood2,itv_ufood3,itv_ufood4,itv_ufood5,itv_ufood6,itv_ufood7,itv_ufood8,itv_ufood9;
    String strbtn = "1";

    SQLiteDatabase db;
    DBhelper dbhelper;

    //定義時間格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Date dt=new Date();
    final String dts=sdf.format(dt);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdish);
        ImageButton btnback = (ImageButton)findViewById(R.id.btn_back);
        tv_member = (TextView)findViewById(R.id.tv_member);
        tv_amount = (TextView)findViewById(R.id.tv_amount);
        dishname = (EditText)findViewById(R.id.dishname);
        dbhelper = new DBhelper(this);
        db = dbhelper.getWritableDatabase();

        btn_food1 = (Button)findViewById(R.id.btn_food1);
        btn_food2 = (Button)findViewById(R.id.btn_food2);
        btn_food3 = (Button)findViewById(R.id.btn_food3);
        btn_food4 = (Button)findViewById(R.id.btn_food4);
        btn_food5 = (Button)findViewById(R.id.btn_food5);
        btn_food6 = (Button)findViewById(R.id.btn_food6);
        btn_food7 = (Button)findViewById(R.id.btn_food7);
        btn_food8 = (Button)findViewById(R.id.btn_food8);
        btn_food9 = (Button)findViewById(R.id.btn_food9);

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

        Bundle bundle2 = this.getIntent().getExtras();
        tv_member.setText(bundle2.getString("member"));
        tv_amount.setText(bundle2.getString("amount"));
        dishname.setText(bundle2.getString("dishname"));

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
        itv_ufood1 = bundle2.getString("idf1");
        itv_ufood2 = bundle2.getString("idf2");
        itv_ufood3 = bundle2.getString("idf3");
        itv_ufood4 = bundle2.getString("idf4");
        itv_ufood5 = bundle2.getString("idf5");
        itv_ufood6 = bundle2.getString("idf6");
        itv_ufood7 = bundle2.getString("idf7");
        itv_ufood8 = bundle2.getString("idf8");
        itv_ufood9 = bundle2.getString("idf9");

        if("".equals(tv_food1.getText().toString().trim())){
            btn_food2.setVisibility(View.GONE);
        }else{
            btn_food2.setVisibility(View.VISIBLE);
            tv_food2.setVisibility(View.VISIBLE);
            tv_qfood2.setVisibility(View.VISIBLE);
            tv_ufood2.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food2.getText().toString().trim())){
            btn_food3.setVisibility(View.GONE);
        }else{
            btn_food3.setVisibility(View.VISIBLE);
            tv_food3.setVisibility(View.VISIBLE);
            tv_qfood3.setVisibility(View.VISIBLE);
            tv_ufood3.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food3.getText().toString().trim())){
            btn_food4.setVisibility(View.GONE);
        }else{
            btn_food4.setVisibility(View.VISIBLE);
            tv_food4.setVisibility(View.VISIBLE);
            tv_qfood4.setVisibility(View.VISIBLE);
            tv_ufood4.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food4.getText().toString().trim())){
            btn_food5.setVisibility(View.GONE);
        }else{
            btn_food5.setVisibility(View.VISIBLE);
            tv_food5.setVisibility(View.VISIBLE);
            tv_qfood5.setVisibility(View.VISIBLE);
            tv_ufood5.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food5.getText().toString().trim())){
            btn_food6.setVisibility(View.GONE);
        }else{
            btn_food6.setVisibility(View.VISIBLE);
            tv_food6.setVisibility(View.VISIBLE);
            tv_qfood6.setVisibility(View.VISIBLE);
            tv_ufood6.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food6.getText().toString().trim())){
            btn_food7.setVisibility(View.GONE);
        }else{
            btn_food7.setVisibility(View.VISIBLE);
            tv_food7.setVisibility(View.VISIBLE);
            tv_qfood7.setVisibility(View.VISIBLE);
            tv_ufood7.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food7.getText().toString().trim())){
            btn_food8.setVisibility(View.GONE);
        }else{
            btn_food8.setVisibility(View.VISIBLE);
            tv_food8.setVisibility(View.VISIBLE);
            tv_qfood8.setVisibility(View.VISIBLE);
            tv_ufood8.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food8.getText().toString().trim())){
            btn_food9.setVisibility(View.GONE);
        }else{
            btn_food9.setVisibility(View.VISIBLE);
            tv_food9.setVisibility(View.VISIBLE);
            tv_qfood9.setVisibility(View.VISIBLE);
            tv_ufood9.setVisibility(View.VISIBLE);
        }

        ImageButton btn_true = (ImageButton) findViewById(R.id.btn_true);
        btn_true.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(dishname.getText().toString().trim())){
                    showAlert("錯誤資訊", "菜名尚未輸入");
                }
                if("0".equals(tv_amount.getText().toString().trim())){
                    showAlert("錯誤資訊", "成員尚未輸入");
                }
                if("".equals(tv_food1.getText().toString().trim())){
                    showAlert("錯誤資訊", "食材尚未輸入");
                }
                if ("".equals(dishname.getText().toString().trim()) ||  "".equals(tv_food1.getText().toString().trim()) || "0".equals(tv_amount.getText().toString().trim())) {
                }else{
                    dishAlert("提醒視窗", "是否確定新增此筆資料？");
                }

            }
        });

        btnback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                backAlert("警告視窗","菜單尚未編輯完成，是否確定離開？");

            }
        });

        Button btn_member = (Button)findViewById(R.id.btn_member);
        btn_member.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b1 = new Bundle();
                b1.putString("member",tv_member.getText().toString());
                b1.putString("amount", tv_amount.getText().toString());
                b1.putString("dishname", dishname.getText().toString());
                b1.putString("f1", tv_food1.getText().toString());
                b1.putString("qf1",tv_qfood1.getText().toString());
                b1.putString("uf1",tv_ufood1.getText().toString());
                b1.putString("idf1",itv_ufood1);
                b1.putString("f2", tv_food2.getText().toString());
                b1.putString("qf2",tv_qfood2.getText().toString());
                b1.putString("uf2",tv_ufood2.getText().toString());
                b1.putString("idf2", itv_ufood2);
                b1.putString("f3", tv_food3.getText().toString());
                b1.putString("qf3",tv_qfood3.getText().toString());
                b1.putString("uf3",tv_ufood3.getText().toString());
                b1.putString("idf3", itv_ufood3);
                b1.putString("f4", tv_food4.getText().toString());
                b1.putString("qf4",tv_qfood4.getText().toString());
                b1.putString("uf4",tv_ufood4.getText().toString());
                b1.putString("idf4", itv_ufood4);
                b1.putString("f5", tv_food5.getText().toString());
                b1.putString("qf5",tv_qfood5.getText().toString());
                b1.putString("uf5",tv_ufood5.getText().toString());
                b1.putString("idf5", itv_ufood5);
                b1.putString("f6", tv_food6.getText().toString());
                b1.putString("qf6",tv_qfood6.getText().toString());
                b1.putString("uf6",tv_ufood6.getText().toString());
                b1.putString("idf6", itv_ufood6);
                b1.putString("f7", tv_food7.getText().toString());
                b1.putString("qf7",tv_qfood7.getText().toString());
                b1.putString("uf7",tv_ufood7.getText().toString());
                b1.putString("idf7", itv_ufood7);
                b1.putString("f8", tv_food8.getText().toString());
                b1.putString("qf8",tv_qfood8.getText().toString());
                b1.putString("uf8",tv_ufood8.getText().toString());
                b1.putString("idf8", itv_ufood8);
                b1.putString("f9", tv_food9.getText().toString());
                b1.putString("qf9",tv_qfood9.getText().toString());
                b1.putString("uf9",tv_ufood9.getText().toString());
                b1.putString("idf9", itv_ufood9);
                b1.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishMember.class);
                intent1.putExtras(b1);
                startActivity(intent1);
                newdish.this.finish();
            }
        });

        btn_food1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b1 = new Bundle();
                strbtn = "1";
                b1.putString("member",tv_member.getText().toString());
                b1.putString("amount", tv_amount.getText().toString());
                b1.putString("dishname", dishname.getText().toString());
                b1.putString("f1", tv_food1.getText().toString());
                b1.putString("qf1",tv_qfood1.getText().toString());
                b1.putString("uf1",tv_ufood1.getText().toString());
                b1.putString("idf1",itv_ufood1);
                b1.putString("f2", tv_food2.getText().toString());
                b1.putString("qf2",tv_qfood2.getText().toString());
                b1.putString("uf2",tv_ufood2.getText().toString());
                b1.putString("idf2", itv_ufood2);
                b1.putString("f3", tv_food3.getText().toString());
                b1.putString("qf3",tv_qfood3.getText().toString());
                b1.putString("uf3",tv_ufood3.getText().toString());
                b1.putString("idf3", itv_ufood3);
                b1.putString("f4", tv_food4.getText().toString());
                b1.putString("qf4",tv_qfood4.getText().toString());
                b1.putString("uf4",tv_ufood4.getText().toString());
                b1.putString("idf4", itv_ufood4);
                b1.putString("f5", tv_food5.getText().toString());
                b1.putString("qf5",tv_qfood5.getText().toString());
                b1.putString("uf5",tv_ufood5.getText().toString());
                b1.putString("idf5", itv_ufood5);
                b1.putString("f6", tv_food6.getText().toString());
                b1.putString("qf6",tv_qfood6.getText().toString());
                b1.putString("uf6",tv_ufood6.getText().toString());
                b1.putString("idf6", itv_ufood6);
                b1.putString("f7", tv_food7.getText().toString());
                b1.putString("qf7",tv_qfood7.getText().toString());
                b1.putString("uf7",tv_ufood7.getText().toString());
                b1.putString("idf7", itv_ufood7);
                b1.putString("f8", tv_food8.getText().toString());
                b1.putString("qf8",tv_qfood8.getText().toString());
                b1.putString("uf8",tv_ufood8.getText().toString());
                b1.putString("idf8", itv_ufood8);
                b1.putString("f9", tv_food9.getText().toString());
                b1.putString("qf9",tv_qfood9.getText().toString());
                b1.putString("uf9",tv_ufood9.getText().toString());
                b1.putString("idf9", itv_ufood9);
                b1.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishFood.class);
                intent1.putExtras(b1);
                startActivity(intent1);
                newdish.this.finish();
            }
        });

        btn_food2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b2 = new Bundle();
                strbtn = "2";
                b2.putString("member",tv_member.getText().toString());
                b2.putString("amount", tv_amount.getText().toString());
                b2.putString("dishname", dishname.getText().toString());
                b2.putString("f1", tv_food1.getText().toString());
                b2.putString("qf1",tv_qfood1.getText().toString());
                b2.putString("uf1",tv_ufood1.getText().toString());
                b2.putString("idf1",itv_ufood1);
                b2.putString("f2", tv_food2.getText().toString());
                b2.putString("qf2",tv_qfood2.getText().toString());
                b2.putString("uf2",tv_ufood2.getText().toString());
                b2.putString("idf2", itv_ufood2);
                b2.putString("f3", tv_food3.getText().toString());
                b2.putString("qf3",tv_qfood3.getText().toString());
                b2.putString("uf3",tv_ufood3.getText().toString());
                b2.putString("idf3", itv_ufood3);
                b2.putString("f4", tv_food4.getText().toString());
                b2.putString("qf4",tv_qfood4.getText().toString());
                b2.putString("uf4",tv_ufood4.getText().toString());
                b2.putString("idf4", itv_ufood4);
                b2.putString("f5", tv_food5.getText().toString());
                b2.putString("qf5",tv_qfood5.getText().toString());
                b2.putString("uf5",tv_ufood5.getText().toString());
                b2.putString("idf5", itv_ufood5);
                b2.putString("f6", tv_food6.getText().toString());
                b2.putString("qf6",tv_qfood6.getText().toString());
                b2.putString("uf6",tv_ufood6.getText().toString());
                b2.putString("idf6", itv_ufood6);
                b2.putString("f7", tv_food7.getText().toString());
                b2.putString("qf7",tv_qfood7.getText().toString());
                b2.putString("uf7",tv_ufood7.getText().toString());
                b2.putString("idf7", itv_ufood7);
                b2.putString("f8", tv_food8.getText().toString());
                b2.putString("qf8",tv_qfood8.getText().toString());
                b2.putString("uf8",tv_ufood8.getText().toString());
                b2.putString("idf8", itv_ufood8);
                b2.putString("f9", tv_food9.getText().toString());
                b2.putString("qf9",tv_qfood9.getText().toString());
                b2.putString("uf9",tv_ufood9.getText().toString());
                b2.putString("idf9", itv_ufood9);
                b2.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishFood.class);
                intent1.putExtras(b2);
                startActivity(intent1);
                newdish.this.finish();
            }
        });

        btn_food3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b2 = new Bundle();
                strbtn = "3";
                b2.putString("member",tv_member.getText().toString());
                b2.putString("amount", tv_amount.getText().toString());
                b2.putString("dishname", dishname.getText().toString());
                b2.putString("f1", tv_food1.getText().toString());
                b2.putString("qf1",tv_qfood1.getText().toString());
                b2.putString("uf1",tv_ufood1.getText().toString());
                b2.putString("idf1",itv_ufood1);
                b2.putString("f2", tv_food2.getText().toString());
                b2.putString("qf2",tv_qfood2.getText().toString());
                b2.putString("uf2",tv_ufood2.getText().toString());
                b2.putString("idf2", itv_ufood2);
                b2.putString("f3", tv_food3.getText().toString());
                b2.putString("qf3",tv_qfood3.getText().toString());
                b2.putString("uf3",tv_ufood3.getText().toString());
                b2.putString("idf3", itv_ufood3);
                b2.putString("f4", tv_food4.getText().toString());
                b2.putString("qf4",tv_qfood4.getText().toString());
                b2.putString("uf4",tv_ufood4.getText().toString());
                b2.putString("idf4", itv_ufood4);
                b2.putString("f5", tv_food5.getText().toString());
                b2.putString("qf5",tv_qfood5.getText().toString());
                b2.putString("uf5",tv_ufood5.getText().toString());
                b2.putString("idf5", itv_ufood5);
                b2.putString("f6", tv_food6.getText().toString());
                b2.putString("qf6",tv_qfood6.getText().toString());
                b2.putString("uf6",tv_ufood6.getText().toString());
                b2.putString("idf6", itv_ufood6);
                b2.putString("f7", tv_food7.getText().toString());
                b2.putString("qf7",tv_qfood7.getText().toString());
                b2.putString("uf7",tv_ufood7.getText().toString());
                b2.putString("idf7", itv_ufood7);
                b2.putString("f8", tv_food8.getText().toString());
                b2.putString("qf8",tv_qfood8.getText().toString());
                b2.putString("uf8",tv_ufood8.getText().toString());
                b2.putString("idf8", itv_ufood8);
                b2.putString("f9", tv_food9.getText().toString());
                b2.putString("qf9",tv_qfood9.getText().toString());
                b2.putString("uf9",tv_ufood9.getText().toString());
                b2.putString("idf9", itv_ufood9);
                b2.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishFood.class);
                intent1.putExtras(b2);
                startActivity(intent1);
                newdish.this.finish();
            }
        });

        btn_food4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b2 = new Bundle();
                strbtn = "4";
                b2.putString("member",tv_member.getText().toString());
                b2.putString("amount", tv_amount.getText().toString());
                b2.putString("dishname", dishname.getText().toString());
                b2.putString("f1", tv_food1.getText().toString());
                b2.putString("qf1",tv_qfood1.getText().toString());
                b2.putString("uf1",tv_ufood1.getText().toString());
                b2.putString("idf1",itv_ufood1);
                b2.putString("f2", tv_food2.getText().toString());
                b2.putString("qf2",tv_qfood2.getText().toString());
                b2.putString("uf2",tv_ufood2.getText().toString());
                b2.putString("idf2", itv_ufood2);
                b2.putString("f3", tv_food3.getText().toString());
                b2.putString("qf3",tv_qfood3.getText().toString());
                b2.putString("uf3",tv_ufood3.getText().toString());
                b2.putString("idf3", itv_ufood3);
                b2.putString("f4", tv_food4.getText().toString());
                b2.putString("qf4",tv_qfood4.getText().toString());
                b2.putString("uf4",tv_ufood4.getText().toString());
                b2.putString("idf4", itv_ufood4);
                b2.putString("f5", tv_food5.getText().toString());
                b2.putString("qf5",tv_qfood5.getText().toString());
                b2.putString("uf5",tv_ufood5.getText().toString());
                b2.putString("idf5", itv_ufood5);
                b2.putString("f6", tv_food6.getText().toString());
                b2.putString("qf6",tv_qfood6.getText().toString());
                b2.putString("uf6",tv_ufood6.getText().toString());
                b2.putString("idf6", itv_ufood6);
                b2.putString("f7", tv_food7.getText().toString());
                b2.putString("qf7",tv_qfood7.getText().toString());
                b2.putString("uf7",tv_ufood7.getText().toString());
                b2.putString("idf7", itv_ufood7);
                b2.putString("f8", tv_food8.getText().toString());
                b2.putString("qf8",tv_qfood8.getText().toString());
                b2.putString("uf8",tv_ufood8.getText().toString());
                b2.putString("idf8", itv_ufood8);
                b2.putString("f9", tv_food9.getText().toString());
                b2.putString("qf9",tv_qfood9.getText().toString());
                b2.putString("uf9",tv_ufood9.getText().toString());
                b2.putString("idf9", itv_ufood9);
                b2.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishFood.class);
                intent1.putExtras(b2);
                startActivity(intent1);
                newdish.this.finish();
            }
        });

        btn_food5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b2 = new Bundle();
                strbtn = "5";
                b2.putString("member",tv_member.getText().toString());
                b2.putString("amount", tv_amount.getText().toString());
                b2.putString("dishname", dishname.getText().toString());
                b2.putString("f1", tv_food1.getText().toString());
                b2.putString("qf1",tv_qfood1.getText().toString());
                b2.putString("uf1",tv_ufood1.getText().toString());
                b2.putString("idf1",itv_ufood1);
                b2.putString("f2", tv_food2.getText().toString());
                b2.putString("qf2",tv_qfood2.getText().toString());
                b2.putString("uf2",tv_ufood2.getText().toString());
                b2.putString("idf2", itv_ufood2);
                b2.putString("f3", tv_food3.getText().toString());
                b2.putString("qf3",tv_qfood3.getText().toString());
                b2.putString("uf3",tv_ufood3.getText().toString());
                b2.putString("idf3", itv_ufood3);
                b2.putString("f4", tv_food4.getText().toString());
                b2.putString("qf4",tv_qfood4.getText().toString());
                b2.putString("uf4",tv_ufood4.getText().toString());
                b2.putString("idf4", itv_ufood4);
                b2.putString("f5", tv_food5.getText().toString());
                b2.putString("qf5",tv_qfood5.getText().toString());
                b2.putString("uf5",tv_ufood5.getText().toString());
                b2.putString("idf5", itv_ufood5);
                b2.putString("f6", tv_food6.getText().toString());
                b2.putString("qf6",tv_qfood6.getText().toString());
                b2.putString("uf6",tv_ufood6.getText().toString());
                b2.putString("idf6", itv_ufood6);
                b2.putString("f7", tv_food7.getText().toString());
                b2.putString("qf7",tv_qfood7.getText().toString());
                b2.putString("uf7",tv_ufood7.getText().toString());
                b2.putString("idf7", itv_ufood7);
                b2.putString("f8", tv_food8.getText().toString());
                b2.putString("qf8",tv_qfood8.getText().toString());
                b2.putString("uf8",tv_ufood8.getText().toString());
                b2.putString("idf8", itv_ufood8);
                b2.putString("f9", tv_food9.getText().toString());
                b2.putString("qf9",tv_qfood9.getText().toString());
                b2.putString("uf9",tv_ufood9.getText().toString());
                b2.putString("idf9", itv_ufood9);
                b2.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishFood.class);
                intent1.putExtras(b2);
                startActivity(intent1);
                newdish.this.finish();
            }
        });

        btn_food6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b2 = new Bundle();
                strbtn = "6";
                b2.putString("member",tv_member.getText().toString());
                b2.putString("amount", tv_amount.getText().toString());
                b2.putString("dishname", dishname.getText().toString());
                b2.putString("f1", tv_food1.getText().toString());
                b2.putString("qf1",tv_qfood1.getText().toString());
                b2.putString("uf1",tv_ufood1.getText().toString());
                b2.putString("idf1",itv_ufood1);
                b2.putString("f2", tv_food2.getText().toString());
                b2.putString("qf2",tv_qfood2.getText().toString());
                b2.putString("uf2",tv_ufood2.getText().toString());
                b2.putString("idf2", itv_ufood2);
                b2.putString("f3", tv_food3.getText().toString());
                b2.putString("qf3",tv_qfood3.getText().toString());
                b2.putString("uf3",tv_ufood3.getText().toString());
                b2.putString("idf3", itv_ufood3);
                b2.putString("f4", tv_food4.getText().toString());
                b2.putString("qf4",tv_qfood4.getText().toString());
                b2.putString("uf4",tv_ufood4.getText().toString());
                b2.putString("idf4", itv_ufood4);
                b2.putString("f5", tv_food5.getText().toString());
                b2.putString("qf5",tv_qfood5.getText().toString());
                b2.putString("uf5",tv_ufood5.getText().toString());
                b2.putString("idf5", itv_ufood5);
                b2.putString("f6", tv_food6.getText().toString());
                b2.putString("qf6",tv_qfood6.getText().toString());
                b2.putString("uf6",tv_ufood6.getText().toString());
                b2.putString("idf6", itv_ufood6);
                b2.putString("f7", tv_food7.getText().toString());
                b2.putString("qf7",tv_qfood7.getText().toString());
                b2.putString("uf7",tv_ufood7.getText().toString());
                b2.putString("idf7", itv_ufood7);
                b2.putString("f8", tv_food8.getText().toString());
                b2.putString("qf8",tv_qfood8.getText().toString());
                b2.putString("uf8",tv_ufood8.getText().toString());
                b2.putString("idf8", itv_ufood8);
                b2.putString("f9", tv_food9.getText().toString());
                b2.putString("qf9",tv_qfood9.getText().toString());
                b2.putString("uf9",tv_ufood9.getText().toString());
                b2.putString("idf9", itv_ufood9);
                b2.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishFood.class);
                intent1.putExtras(b2);
                startActivity(intent1);
                newdish.this.finish();
            }
        });

        btn_food7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b2 = new Bundle();
                strbtn = "7";
                b2.putString("member",tv_member.getText().toString());
                b2.putString("amount", tv_amount.getText().toString());
                b2.putString("dishname", dishname.getText().toString());
                b2.putString("f1", tv_food1.getText().toString());
                b2.putString("qf1",tv_qfood1.getText().toString());
                b2.putString("uf1",tv_ufood1.getText().toString());
                b2.putString("idf1",itv_ufood1);
                b2.putString("f2", tv_food2.getText().toString());
                b2.putString("qf2",tv_qfood2.getText().toString());
                b2.putString("uf2",tv_ufood2.getText().toString());
                b2.putString("idf2", itv_ufood2);
                b2.putString("f3", tv_food3.getText().toString());
                b2.putString("qf3",tv_qfood3.getText().toString());
                b2.putString("uf3",tv_ufood3.getText().toString());
                b2.putString("idf3", itv_ufood3);
                b2.putString("f4", tv_food4.getText().toString());
                b2.putString("qf4",tv_qfood4.getText().toString());
                b2.putString("uf4",tv_ufood4.getText().toString());
                b2.putString("idf4", itv_ufood4);
                b2.putString("f5", tv_food5.getText().toString());
                b2.putString("qf5",tv_qfood5.getText().toString());
                b2.putString("uf5",tv_ufood5.getText().toString());
                b2.putString("idf5", itv_ufood5);
                b2.putString("f6", tv_food6.getText().toString());
                b2.putString("qf6",tv_qfood6.getText().toString());
                b2.putString("uf6",tv_ufood6.getText().toString());
                b2.putString("idf6", itv_ufood6);
                b2.putString("f7", tv_food7.getText().toString());
                b2.putString("qf7",tv_qfood7.getText().toString());
                b2.putString("uf7",tv_ufood7.getText().toString());
                b2.putString("idf7", itv_ufood7);
                b2.putString("f8", tv_food8.getText().toString());
                b2.putString("qf8",tv_qfood8.getText().toString());
                b2.putString("uf8",tv_ufood8.getText().toString());
                b2.putString("idf8", itv_ufood8);
                b2.putString("f9", tv_food9.getText().toString());
                b2.putString("qf9",tv_qfood9.getText().toString());
                b2.putString("uf9",tv_ufood9.getText().toString());
                b2.putString("idf9", itv_ufood9);
                b2.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishFood.class);
                intent1.putExtras(b2);
                startActivity(intent1);
                newdish.this.finish();
            }
        });

        btn_food8.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b2 = new Bundle();
                strbtn = "8";
                b2.putString("member",tv_member.getText().toString());
                b2.putString("amount", tv_amount.getText().toString());
                b2.putString("dishname", dishname.getText().toString());
                b2.putString("f1", tv_food1.getText().toString());
                b2.putString("qf1",tv_qfood1.getText().toString());
                b2.putString("uf1",tv_ufood1.getText().toString());
                b2.putString("idf1",itv_ufood1);
                b2.putString("f2", tv_food2.getText().toString());
                b2.putString("qf2",tv_qfood2.getText().toString());
                b2.putString("uf2",tv_ufood2.getText().toString());
                b2.putString("idf2", itv_ufood2);
                b2.putString("f3", tv_food3.getText().toString());
                b2.putString("qf3",tv_qfood3.getText().toString());
                b2.putString("uf3",tv_ufood3.getText().toString());
                b2.putString("idf3", itv_ufood3);
                b2.putString("f4", tv_food4.getText().toString());
                b2.putString("qf4",tv_qfood4.getText().toString());
                b2.putString("uf4",tv_ufood4.getText().toString());
                b2.putString("idf4", itv_ufood4);
                b2.putString("f5", tv_food5.getText().toString());
                b2.putString("qf5",tv_qfood5.getText().toString());
                b2.putString("uf5",tv_ufood5.getText().toString());
                b2.putString("idf5", itv_ufood5);
                b2.putString("f6", tv_food6.getText().toString());
                b2.putString("qf6",tv_qfood6.getText().toString());
                b2.putString("uf6",tv_ufood6.getText().toString());
                b2.putString("idf6", itv_ufood6);
                b2.putString("f7", tv_food7.getText().toString());
                b2.putString("qf7",tv_qfood7.getText().toString());
                b2.putString("uf7",tv_ufood7.getText().toString());
                b2.putString("idf7", itv_ufood7);
                b2.putString("f8", tv_food8.getText().toString());
                b2.putString("qf8",tv_qfood8.getText().toString());
                b2.putString("uf8",tv_ufood8.getText().toString());
                b2.putString("idf8", itv_ufood8);
                b2.putString("f9", tv_food9.getText().toString());
                b2.putString("qf9",tv_qfood9.getText().toString());
                b2.putString("uf9",tv_ufood9.getText().toString());
                b2.putString("idf9", itv_ufood9);
                b2.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishFood.class);
                intent1.putExtras(b2);
                startActivity(intent1);
                newdish.this.finish();
            }
        });

        btn_food9.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b2 = new Bundle();
                strbtn = "9";
                b2.putString("member",tv_member.getText().toString());
                b2.putString("amount", tv_amount.getText().toString());
                b2.putString("dishname", dishname.getText().toString());
                b2.putString("f1", tv_food1.getText().toString());
                b2.putString("qf1",tv_qfood1.getText().toString());
                b2.putString("uf1",tv_ufood1.getText().toString());
                b2.putString("idf1",itv_ufood1);
                b2.putString("f2", tv_food2.getText().toString());
                b2.putString("qf2",tv_qfood2.getText().toString());
                b2.putString("uf2",tv_ufood2.getText().toString());
                b2.putString("idf2", itv_ufood2);
                b2.putString("f3", tv_food3.getText().toString());
                b2.putString("qf3",tv_qfood3.getText().toString());
                b2.putString("uf3",tv_ufood3.getText().toString());
                b2.putString("idf3", itv_ufood3);
                b2.putString("f4", tv_food4.getText().toString());
                b2.putString("qf4",tv_qfood4.getText().toString());
                b2.putString("uf4",tv_ufood4.getText().toString());
                b2.putString("idf4", itv_ufood4);
                b2.putString("f5", tv_food5.getText().toString());
                b2.putString("qf5",tv_qfood5.getText().toString());
                b2.putString("uf5",tv_ufood5.getText().toString());
                b2.putString("idf5", itv_ufood5);
                b2.putString("f6", tv_food6.getText().toString());
                b2.putString("qf6",tv_qfood6.getText().toString());
                b2.putString("uf6",tv_ufood6.getText().toString());
                b2.putString("idf6", itv_ufood6);
                b2.putString("f7", tv_food7.getText().toString());
                b2.putString("qf7",tv_qfood7.getText().toString());
                b2.putString("uf7",tv_ufood7.getText().toString());
                b2.putString("idf7", itv_ufood7);
                b2.putString("f8", tv_food8.getText().toString());
                b2.putString("qf8",tv_qfood8.getText().toString());
                b2.putString("uf8",tv_ufood8.getText().toString());
                b2.putString("idf8", itv_ufood8);
                b2.putString("f9", tv_food9.getText().toString());
                b2.putString("qf9",tv_qfood9.getText().toString());
                b2.putString("uf9",tv_ufood9.getText().toString());
                b2.putString("idf9", itv_ufood9);
                b2.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(newdish.this, DishFood.class);
                intent1.putExtras(b2);
                startActivity(intent1);
                newdish.this.finish();
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

    private void backAlert(String title,String context)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(context);
        alert.setPositiveButton("確定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                        Intent intent1 = new Intent();
                        intent1.setClass(newdish.this, dish.class);
                        Bundle b = new Bundle();
                        b.putString("member", tv_amount.getText().toString());
                        b.putString("amount", tv_amount.getText().toString());
                        b.putString("dishname", dishname.getText().toString());
                        b.putString("f1", tv_food1.getText().toString());
                        b.putString("qf1", tv_qfood1.getText().toString());
                        b.putString("uf1", tv_ufood1.getText().toString());
                        b.putString("f2", tv_food2.getText().toString());
                        b.putString("qf2", tv_qfood2.getText().toString());
                        b.putString("uf2", tv_ufood2.getText().toString());
                        b.putString("f3", tv_food3.getText().toString());
                        b.putString("qf3", tv_qfood3.getText().toString());
                        b.putString("uf3", tv_ufood3.getText().toString());
                        b.putString("f4", tv_food4.getText().toString());
                        b.putString("qf4", tv_qfood4.getText().toString());
                        b.putString("uf4", tv_ufood4.getText().toString());
                        b.putString("f5", tv_food5.getText().toString());
                        b.putString("qf5", tv_qfood5.getText().toString());
                        b.putString("uf5", tv_ufood5.getText().toString());
                        b.putString("f6", tv_food6.getText().toString());
                        b.putString("qf6", tv_qfood6.getText().toString());
                        b.putString("uf6", tv_ufood6.getText().toString());
                        b.putString("f7", tv_food7.getText().toString());
                        b.putString("qf7", tv_qfood7.getText().toString());
                        b.putString("uf7", tv_ufood7.getText().toString());
                        b.putString("f8", tv_food8.getText().toString());
                        b.putString("qf8", tv_qfood8.getText().toString());
                        b.putString("uf8", tv_ufood8.getText().toString());
                        b.putString("f9", tv_food9.getText().toString());
                        b.putString("qf9", tv_qfood9.getText().toString());
                        b.putString("uf9", tv_ufood9.getText().toString());
                        b.putString("strbtn", strbtn);
                        intent1.putExtras(b);
                        startActivity(intent1);
                        newdish.this.finish();
                    }
                });

        alert.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                    }
                });
        alert.show();
    }
    private void dishAlert(String title,String context)
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
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(D_DISHNAME, dishname.getText().toString());
                        values.put(D_MEMBER, tv_member.toString());
                        values.put(D_MQUANTITY, tv_amount.getText().toString());
                        values.put(D_FOOD1, tv_food1.getText().toString());
                        values.put(D_QFOOD1, tv_qfood1.getText().toString());
                        values.put(D_UFOOD1, tv_ufood1.getText().toString());
                        values.put(ID_UFOOD1,itv_ufood1);
                        values.put(D_FOOD2, tv_food2.getText().toString());
                        values.put(D_QFOOD2, tv_qfood2.getText().toString());
                        values.put(D_UFOOD2, tv_ufood2.getText().toString());
                        values.put(ID_UFOOD2,itv_ufood2);
                        values.put(D_FOOD3, tv_food3.getText().toString());
                        values.put(D_QFOOD3, tv_qfood3.getText().toString());
                        values.put(D_UFOOD3, tv_ufood3.getText().toString());
                        values.put(ID_UFOOD3,itv_ufood3);
                        values.put(D_FOOD4, tv_food4.getText().toString());
                        values.put(D_QFOOD4, tv_qfood4.getText().toString());
                        values.put(D_UFOOD4, tv_ufood4.getText().toString());
                        values.put(ID_UFOOD4,itv_ufood4);
                        values.put(D_FOOD5, tv_food5.getText().toString());
                        values.put(D_QFOOD5, tv_qfood5.getText().toString());
                        values.put(D_UFOOD5, tv_ufood5.getText().toString());
                        values.put(ID_UFOOD5,itv_ufood5);
                        values.put(D_FOOD6, tv_food6.getText().toString());
                        values.put(D_QFOOD6, tv_qfood6.getText().toString());
                        values.put(D_UFOOD6, tv_ufood6.getText().toString());
                        values.put(ID_UFOOD6,itv_ufood6);
                        values.put(D_FOOD7, tv_food7.getText().toString());
                        values.put(D_QFOOD7, tv_qfood7.getText().toString());
                        values.put(D_UFOOD7, tv_ufood7.getText().toString());
                        values.put(ID_UFOOD7,itv_ufood7);
                        values.put(D_FOOD8, tv_food8.getText().toString());
                        values.put(D_QFOOD8, tv_qfood8.getText().toString());
                        values.put(D_UFOOD8, tv_ufood8.getText().toString());
                        values.put(ID_UFOOD8,itv_ufood8);
                        values.put(D_FOOD9, tv_food9.getText().toString());
                        values.put(D_QFOOD9, tv_qfood9.getText().toString());
                        values.put(D_UFOOD9, tv_ufood9.getText().toString());
                        values.put(ID_UFOOD9,itv_ufood9);
                        values.put(D_DATE, dts);
                        db.insert(DTABLE_NAME, null, values);

                        Intent i = new Intent(newdish.this, dish.class);
                        startActivity(i);
                        finish();
                    }
                });
        alert.show();
    }

}
