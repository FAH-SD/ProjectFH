package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import static android.provider.BaseColumns._ID;
import static com.example.goldas.projectfh.DBconstant.ITEM;
import static com.example.goldas.projectfh.DBconstant.KIND;
import static com.example.goldas.projectfh.DBconstant.QUANTITY;
import static com.example.goldas.projectfh.DBconstant.TABLE_NAME;
import static com.example.goldas.projectfh.DBconstant.BUYINGDATE;
import static com.example.goldas.projectfh.DBconstant.LIMITDATE;
import static com.example.goldas.projectfh.DBconstant.STORAGEPLACE;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.lang.reflect.Method;


public class newfood1 extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfood1);
        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_iback);
buttonback.setOnClickListener(new ImageButton.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent1 = new Intent();
        intent1.setClass(newfood1.this, icebox.class);
        startActivity(intent1);
        newfood1.this.finish();
        }
        });
        Button buttonin2 = (Button)findViewById(R.id.btn_iin2);
        buttonin2.setOnClickListener(new Button.OnClickListener(){
@Override
public void onClick(View v) {
        Intent intent2 = new Intent();
        intent2.setClass(newfood1.this, newfood2.class);
        startActivity(intent2);
        newfood1.this.finish();
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

    }







