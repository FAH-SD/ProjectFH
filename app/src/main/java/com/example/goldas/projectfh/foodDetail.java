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

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class foodDetail extends Activity implements View.OnClickListener {
    SQLiteDatabase db ;
    DBhelper dbhelper;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        dbhelper = new DBhelper(this);
        db = dbhelper.getWritableDatabase();

        EditText kind = (EditText) findViewById(R.id.et_ikind);
        EditText name = (EditText) findViewById(R.id.et_iname);
        EditText amount = (EditText) findViewById(R.id.et_iamount);
        EditText unit = (EditText) findViewById(R.id.et_iunit);
        EditText buyday = (EditText) findViewById(R.id.et_ibuyday);
        EditText limitday = (EditText) findViewById(R.id.et_ilimitday);
        EditText place = (EditText) findViewById(R.id.et_iplace);
        EditText condition = (EditText) findViewById(R.id.et_icondition);

        Bundle bundle2=this.getIntent().getExtras();

        kind.setText(bundle2.getString("foodkind"));
        name.setText(bundle2.getString("foodname"));
        amount.setText(bundle2.getString("foodamount"));

        buyday.setText(bundle2.getString("foodbuyday"));
        limitday.setText(bundle2.getString("foodlimitday"));
        place.setText(bundle2.getString("foodstorage"));
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
                showAlert("警告", "是否刪除此筆資料？");
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
                Intent intenthomepage = new Intent();
                intenthomepage.setClass(this, homepage.class);
                startActivity(intenthomepage);
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
}