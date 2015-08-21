package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import static com.example.goldas.projectfh.FDBconstant.ALLERGY1;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY10;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY2;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY3;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY4;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY5;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY6;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY7;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY8;
import static com.example.goldas.projectfh.FDBconstant.ALLERGY9;
import static com.example.goldas.projectfh.FDBconstant.BIRTHYEAR;
import static com.example.goldas.projectfh.FDBconstant.FTABLE_NAME;
import static com.example.goldas.projectfh.FDBconstant.HABIT;
import static com.example.goldas.projectfh.FDBconstant.HEIGHT;
import static com.example.goldas.projectfh.FDBconstant.NAME;
import static com.example.goldas.projectfh.FDBconstant.SEX;
import static com.example.goldas.projectfh.FDBconstant.WEIGHT;


/**
 * Created by roy on 2015/8/1.
 */
public class familydetail extends Activity implements View.OnClickListener {
    SQLiteDatabase dbrw;
    DBhelper dbhelper;
    int id;
    EditText name, height, weight, year, habit;
    CheckBox egg, fish, peanut, nut, shellfish, beans, milk, chicken, pork, beef;
    String a1,a2,a3,a4,a5,a6,a7,a8,a9,a10;
    Spinner sp_habit;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_detail);

        dbhelper = new DBhelper(this);
        dbrw = dbhelper.getWritableDatabase();

        name = (EditText) findViewById(R.id.et_fname);
        EditText sex = (EditText) findViewById(R.id.et_fsex);
        height = (EditText) findViewById(R.id.et_fheight);
        weight = (EditText) findViewById(R.id.et_fweight);
        year = (EditText) findViewById(R.id.et_fyear);
        habit = (EditText) findViewById(R.id.et_fhabit);
        egg = (CheckBox)findViewById(R.id.egg);
        fish = (CheckBox)findViewById(R.id.fish);
        peanut = (CheckBox)findViewById(R.id. peanut);
        nut = (CheckBox)findViewById(R.id.nut);
        shellfish = (CheckBox)findViewById(R.id.shellfish);
        beans = (CheckBox)findViewById(R.id.beans);
        milk = (CheckBox)findViewById(R.id.milk);
        chicken = (CheckBox)findViewById(R.id.chicken);
        pork = (CheckBox)findViewById(R.id.pork);
        beef = (CheckBox)findViewById(R.id.beef);
        sp_habit = (Spinner)findViewById(R.id.sp_fhabit);

        Bundle bundle4 = this.getIntent().getExtras();

        name.setText(bundle4.getString("membername"));
        sex.setText(bundle4.getString("membersex"));
        height.setText(bundle4.getString("memberheight"));
        weight.setText(bundle4.getString("memberweight"));
        year.setText(bundle4.getString("memberbirthyear"));
        habit.setText(bundle4.getString("memberhabit"));
        id = bundle4.getInt("memberid");

        a1 = bundle4.getString("allergyegg");
        a2 = bundle4.getString("allergyfish");
        a3 = bundle4.getString("allergypeanut");
        a4 = bundle4.getString("allergynut");
        a5 = bundle4.getString("allergyshellfish");
        a6 = bundle4.getString("allergybeans");
        a7 = bundle4.getString("allergymilk");
        a8 = bundle4.getString("allergychicken");
        a9 = bundle4.getString("allergypork");
        a10 = bundle4.getString("allergybeef");

        if("1".equals(a1)){
            egg.setChecked(true);
        }
        if("1".equals(a2)){
            fish.setChecked(true);
        }
        if("1".equals(a3)){
            peanut.setChecked(true);
        }
        if("1".equals(a4)){
            nut.setChecked(true);
        }
        if("1".equals(a5)){
            shellfish.setChecked(true);
        }
        if("1".equals(a6)){
            beans.setChecked(true);
        }
        if("1".equals(a7)){
           milk.setChecked(true);
        }
        if("1".equals(a8)){
            chicken.setChecked(true);
        }
        if("1".equals(a9)){
            pork.setChecked(true);
        }
        if("1".equals(a10)){
            beef.setChecked(true);
        }
        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_fback);
        buttonback.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(familydetail.this, family.class);
                startActivity(intent1);
                familydetail.this.finish();
            }
        });

        ImageButton buttondelete = (ImageButton) findViewById(R.id.btn_fdelete);
        buttondelete.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert("警告", "是否確定刪除此筆資料？");
            }
        });

        final ImageButton buttontrue = (ImageButton) findViewById(R.id.btn_ftrue);
        buttontrue.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(name.getText().toString().trim())) {
                    showAlert2("錯誤資訊", "姓名尚未輸入");
                }

                try {
                    int h = Integer.parseInt(height.getText().toString());
                    if (h > 200 || h < 50) {
                        showAlert2("錯誤資訊", "身高只能介於50~200公分之間");
                        height.setText("");
                    }
                } catch (NumberFormatException e1) {
                    showAlert2("錯誤資訊", "身高尚未輸入");
                }

                try {
                    int w = Integer.parseInt(weight.getText().toString());
                    if (w > 200 || w < 10) {
                        showAlert2("錯誤資訊", "體重只能介於10~200公斤之間");
                        weight.setText("");
                    }
                } catch (NumberFormatException e2) {
                    showAlert2("錯誤資訊", "體重尚未輸入");
                }
                if(egg.isChecked()){
                    a1 = "1";
                }else{
                    a1 = "0";
                }
                if(fish.isChecked()){
                    a2 = "1";
                }else{
                    a2 = "0";
                }
                if(peanut.isChecked()){
                    a3 = "1";
                }else{
                    a3 = "0";
                }
                if(nut.isChecked()){
                    a4 = "1";
                }else{
                    a4 = "0";
                }
                if(shellfish.isChecked()){
                    a5 = "1";
                }else{
                    a5 = "0";
                }
                if(beans.isChecked()){
                    a6 = "1";
                }else{
                    a6 = "0";
                }
                if(milk.isChecked()){
                    a7 = "1";
                }else{
                    a7 = "0";
                }
                if(chicken.isChecked()){
                    a8 = "1";
                }else{
                    a8 = "0";
                }
                if(pork.isChecked()){
                    a9 = "1";
                }else{
                    a9 = "0";
                }
                if(beef.isChecked()){
                    a10 = "1";
                }else{
                    a10 = "0";
                }
                if ("".equals(height.getText().toString().trim()) || "".equals(weight.getText().toString().trim()) || "".equals(year.getText().toString().trim()) || "".equals(name.getText().toString().trim())) {
                } else {
                    showAlert3("警告", "是否確定編輯此筆資料？");
                }
            }
        });

        ImageButton buttonrevise = (ImageButton)findViewById(R.id.btn_frevise);
        buttonrevise.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttontrue.setVisibility(View.VISIBLE);
                sp_habit.setVisibility(View.VISIBLE);
                habit.setVisibility(View.GONE);
                name.setEnabled(true);
                height.setEnabled(true);
                weight.setEnabled(true);
                egg.setEnabled(true);
                fish.setEnabled(true);
                peanut.setEnabled(true);
                nut.setEnabled(true);
                shellfish.setEnabled(true);
                beans.setEnabled(true);
                milk.setEnabled(true);
                chicken.setEnabled(true);
                pork.setEnabled(true);
                beef.setEnabled(true);

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
    menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
    menu.setMenu(R.layout.activity_left_fragment);

// 导航打开监听事件
    menu.setOnOpenListener(new SlidingMenu.OnOpenListener()

    {
        @Override
        public void onOpen () {
    }
    }

    );
    // 导航关闭监听事件
    menu.setOnClosedListener(new SlidingMenu.OnClosedListener()

    {

        @Override
        public void onClosed () {
    }
    }

    );


    ImageView lefthome = (ImageView) findViewById(R.id.lefthome);
    lefthome.setOnClickListener(this);

    ImageView leftfamily = (ImageView) findViewById(R.id.leftfamily);
    leftfamily.setOnClickListener(this);

    ImageView lefticebox = (ImageView) findViewById(R.id.lefticebox);
    lefticebox.setOnClickListener(this);

    ImageView leftdish = (ImageView) findViewById(R.id.leftdish);
    leftdish.setOnClickListener(this);

    ImageView leftsport = (ImageView) findViewById(R.id.leftsport);
    leftsport.setOnClickListener(this);

    ImageView leftset = (ImageView) findViewById(R.id.leftset);
    leftset.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        long long1 = db.delete("family", " _id=" + id, null);
                        Intent i = new Intent(familydetail.this, family.class);
                        startActivity(i);
                        finish();
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
                    }
                });
        alert.show();
    }
    private void showAlert3(String title,String context)
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
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(NAME, name.getText().toString());
                        values.put(WEIGHT, weight.getText().toString());
                        values.put(HEIGHT, height.getText().toString());
                        values.put(HABIT, sp_habit.getSelectedItem().toString());
                        values.put(ALLERGY1, a1);
                        values.put(ALLERGY2, a2);
                        values.put(ALLERGY3, a3);
                        values.put(ALLERGY4, a4);
                        values.put(ALLERGY5, a5);
                        values.put(ALLERGY6, a6);
                        values.put(ALLERGY7, a7);
                        values.put(ALLERGY8, a8);
                        values.put(ALLERGY9, a9);
                        values.put(ALLERGY10, a10);
                        long long1 = db.update("family", values, "_ID=" + id, null);
                        Intent i = new Intent(familydetail.this, family.class);
                        startActivity(i);
                        finish();
                    }
                });
        alert.show();
    }
}