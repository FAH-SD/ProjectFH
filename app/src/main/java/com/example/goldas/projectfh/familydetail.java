package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import static com.example.goldas.projectfh.FDBconstant.BIRTHYEAR;
import static com.example.goldas.projectfh.FDBconstant.FTABLE_NAME;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_detail);

        dbhelper = new DBhelper(this);
        dbrw = dbhelper.getWritableDatabase();

        EditText name = (EditText) findViewById(R.id.et_fname);
        EditText sex = (EditText) findViewById(R.id.et_fsex);
        EditText height = (EditText) findViewById(R.id.et_fheight);
        EditText weight = (EditText) findViewById(R.id.et_fweight);
        EditText year = (EditText) findViewById(R.id.et_fyear);

        Bundle bundle4 = this.getIntent().getExtras();

        name.setText(bundle4.getString("membername"));
        sex.setText(bundle4.getString("membersex"));
        height.setText(bundle4.getString("memberheight"));
        weight.setText(bundle4.getString("memberweight"));
        year.setText(bundle4.getString("memberbirthyear"));
        id = bundle4.getInt("memberid");
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
                        long long1 = db.delete("family", " _id=" + id, null);
                        Intent i = new Intent(familydetail.this, family.class);
                        startActivity(i);
                        finish();
                    }
                });
        alert.show();
    }
}