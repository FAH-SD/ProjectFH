package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.widget.Spinner;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Attributes;

import static com.example.goldas.projectfh.DBconstant.QUANTITY;
import static com.example.goldas.projectfh.DBconstant.STORAGEPLACE;
import static com.example.goldas.projectfh.DBconstant.UNIT;


public class foodDetail extends Activity implements View.OnClickListener {
    SQLiteDatabase db ;
    DBhelper dbhelper;
    int id;
    EditText amount, name;
    Spinner sp_unit,sp_unit2, sp_place;
    String liquid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        dbhelper = new DBhelper(this);
        db = dbhelper.getWritableDatabase();

        final EditText kind = (EditText) findViewById(R.id.et_ikind);
        name = (EditText) findViewById(R.id.et_iname);
        amount = (EditText) findViewById(R.id.et_iamount);
        final EditText unit = (EditText) findViewById(R.id.et_iunit);
        EditText buyday = (EditText) findViewById(R.id.et_ibuyday);
        EditText limitday = (EditText) findViewById(R.id.et_ilimitday);
        final EditText place = (EditText) findViewById(R.id.et_iplace);
        EditText condition = (EditText) findViewById(R.id.et_icondition);
        sp_unit = (Spinner)findViewById(R.id.sp_iunit);
        sp_unit2 = (Spinner)findViewById(R.id.sp_iunit2);
        sp_place = (Spinner)findViewById(R.id.sp_iplace);
        Bundle bundle2=this.getIntent().getExtras();

        kind.setText(bundle2.getString("foodkind"));
        name.setText(bundle2.getString("foodname"));
        amount.setText(bundle2.getString("foodamount"));

        buyday.setText(bundle2.getString("foodbuyday"));
        limitday.setText(bundle2.getString("foodlimitday"));
        place.setText(bundle2.getString("foodstorage"));
        unit.setText(bundle2.getString("foodunit"));
        id = bundle2.getInt("foodid");
        //定義時間格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if("".equals(buyday.getText().toString().trim())) {}
            else{
                try {
                    Date dt1 = sdf.parse(buyday.getText().toString());
                    Date dt2 =sdf.parse(limitday.getText().toString());
                    //取得兩個時間的Unix時間
                    Long ut1=dt1.getTime();
                    Long ut2=dt2.getTime();
                    //相減獲得兩個時間差距的毫秒
                    Long timeP=ut2-ut1;//毫秒差
                    long day=timeP/1000*60*60*24;//日差

                    Date dt=new Date();
                    String dts=sdf.format(dt);
                    Date dt3 = sdf.parse(dts);
                    Long ut3=dt3.getTime();
                    Long timeP2=ut2-ut3;//毫秒差
                    Long day2=timeP2/1000*60*60*24;//日差
                    if(day2>day*0.5){
                        condition.setText("良好");
                        condition.setBackgroundColor(Color.GREEN);
                    }if(day2<=day*0.5){
                        condition.setText("普通");
                        condition.setBackgroundColor(Color.YELLOW);
                    }if(day2<0){
                        condition.setText("糟糕");
                        condition.setBackgroundColor(Color.RED);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_iback);
        buttonback.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(foodDetail.this, icebox.class);
                startActivity(intent1);
                foodDetail.this.finish();
            }
        });

        ImageButton buttondelete = (ImageButton) findViewById(R.id.btn_idelete);
        buttondelete.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert("警告", "是否確定刪除此筆資料？");
            }
        });

        final ImageButton buttontrue = (ImageButton)findViewById(R.id.btn_itrue);
        buttontrue.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(amount.getText().toString().trim())) {
                    showAlert2("錯誤資訊", "數量尚未輸入");
                }else{
                    showAlert3("警告", "是否確定編輯此筆資料？");
                }
            }
        });


        ImageButton buttonrevise = (ImageButton)findViewById(R.id.btn_irevise);
        buttonrevise.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttontrue.setVisibility(View.VISIBLE);
                liquid = name.getText().toString();
                if(liquid.contains("脂鮮乳")||liquid.contains("養樂多")||liquid.contains("優酪乳")||(liquid.contains("牛油")||liquid.contains("豬油")||liquid.contains("雞油")||liquid.contains("花生油")||liquid.contains("葵花油")||liquid.contains("玉米油")||liquid.contains("沙拉油")||liquid.contains("橄欖油")||liquid.contains("辣椒油")||liquid.contains("椰子油")|liquid.contains("芝麻油"))){
                    sp_unit.setVisibility(View.GONE);
                    sp_unit2.setVisibility(View.VISIBLE);
                }else{
                    sp_unit2.setVisibility(View.GONE);
                    sp_unit.setVisibility(View.VISIBLE);
                }

                sp_place.setVisibility(View.VISIBLE);
                unit.setVisibility(View.GONE);
                place.setVisibility(View.GONE);
                amount.setEnabled(true);

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
                        long long1 = db.delete("icebox", " _id=" + id, null);
                        Intent i = new Intent(foodDetail.this, icebox.class);
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
                        values.put(QUANTITY, amount.getText().toString());
                        if(sp_unit.getVisibility() == View.VISIBLE ) {
                            values.put(UNIT, sp_unit.getSelectedItem().toString());
                        }else{
                            values.put(UNIT, sp_unit2.getSelectedItem().toString());
                        }
                        values.put(STORAGEPLACE, sp_place.getSelectedItem().toString());
                        long long1 = db.update("icebox", values, "_ID=" + id, null);
                        Intent i = new Intent(foodDetail.this, icebox.class);
                        startActivity(i);
                        finish();
                    }
                });
        alert.show();
    }
}