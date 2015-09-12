package com.tku.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class DishDetail extends Activity {
    TextView tv_food1, tv_food2, tv_food3, tv_food4, tv_food5, tv_food6, tv_food7, tv_food8, tv_food9;
    TextView tv_qfood1, tv_qfood2, tv_qfood3, tv_qfood4, tv_qfood5, tv_qfood6, tv_qfood7, tv_qfood8, tv_qfood9;
    TextView tv_ufood1, tv_ufood2, tv_ufood3, tv_ufood4, tv_ufood5, tv_ufood6, tv_ufood7, tv_ufood8, tv_ufood9;
    TextView food2,food3,food4,food5,food6,food7,food8,food9;
    EditText et_dishname, et_amount;
//    TextView et_member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        et_dishname = (EditText)findViewById(R.id.et_dishname);
//        et_member = (TextView)findViewById(R.id.et_member);
        et_amount = (EditText)findViewById(R.id.et_amount);

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

        Bundle bundle2 = this.getIntent().getExtras();
        et_dishname.setText(bundle2.getString("dishname"));
//        et_member.setText(bundle2.getString("member"));
        et_amount.setText(bundle2.getString("amount"));

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

        if("".equals(tv_food2.getText().toString().trim())){
        }else{
            food2.setVisibility(View.VISIBLE);
            tv_food2.setVisibility(View.VISIBLE);
            tv_qfood2.setVisibility(View.VISIBLE);
            tv_ufood2.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food3.getText().toString().trim())){
        }else{
            food3.setVisibility(View.VISIBLE);
            tv_food3.setVisibility(View.VISIBLE);
            tv_qfood3.setVisibility(View.VISIBLE);
            tv_ufood3.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food4.getText().toString().trim())){
        }else{
            food4.setVisibility(View.VISIBLE);
            tv_food4.setVisibility(View.VISIBLE);
            tv_qfood4.setVisibility(View.VISIBLE);
            tv_ufood4.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food5.getText().toString().trim())){
        }else{
            food5.setVisibility(View.VISIBLE);
            tv_food5.setVisibility(View.VISIBLE);
            tv_qfood5.setVisibility(View.VISIBLE);
            tv_ufood5.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food6.getText().toString().trim())){
        }else{
            food6.setVisibility(View.VISIBLE);
            tv_food6.setVisibility(View.VISIBLE);
            tv_qfood6.setVisibility(View.VISIBLE);
            tv_ufood6.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food7.getText().toString().trim())){
        }else{
            food7.setVisibility(View.VISIBLE);
            tv_food7.setVisibility(View.VISIBLE);
            tv_qfood7.setVisibility(View.VISIBLE);
            tv_ufood7.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food8.getText().toString().trim())){
        }else{
            food8.setVisibility(View.VISIBLE);
            tv_food8.setVisibility(View.VISIBLE);
            tv_qfood8.setVisibility(View.VISIBLE);
            tv_ufood8.setVisibility(View.VISIBLE);
        }
        if("".equals(tv_food9.getText().toString().trim())){
        }else{
            food9.setVisibility(View.VISIBLE);
            tv_food9.setVisibility(View.VISIBLE);
            tv_qfood9.setVisibility(View.VISIBLE);
            tv_ufood9.setVisibility(View.VISIBLE);
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
    }

}
