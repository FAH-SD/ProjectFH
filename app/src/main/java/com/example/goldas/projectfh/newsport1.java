package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class newsport1 extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsport1);
        EditText name = (EditText)findViewById(R.id.et_sname);
        EditText height = (EditText)findViewById(R.id.et_sheight);
        EditText weight = (EditText)findViewById(R.id.et_sweight);
        EditText BMI = (EditText)findViewById(R.id.et_sBMI);
        EditText level = (EditText)findViewById(R.id.et_slevel);

        Bundle bundle2 = this.getIntent().getExtras();
        name.setText(bundle2.getString("name"));
        height.setText(bundle2.getString("height"));
        weight.setText(bundle2.getString("weight"));
        BMI.setText(bundle2.getString("BMI"));
        level.setText(bundle2.getString("level"));

        float fbmi = Float.parseFloat(BMI.getText().toString());
        if (fbmi < 18.5) {
            level.setText("體重過輕");
            level.setBackgroundColor(Color.rgb(210, 255, 185));
        } else if (18.5 <= fbmi && fbmi < 24) {
            level.setText("正常範圍");
            level.setBackgroundColor(Color.GREEN);
        } else if (24 <= fbmi && fbmi < 27) {
            level.setText("過重");
            level.setBackgroundColor(Color.YELLOW);
        } else if (24 <= fbmi && fbmi < 30) {
            level.setText("輕度肥胖");
            level.setBackgroundColor(Color.rgb(255, 175, 25));
        } else if (30 <= fbmi && fbmi < 35) {
            level.setText("中度肥胖");
            level.setBackgroundColor(Color.rgb(255, 155, 170));
        } else if (35 <= fbmi) {
            level.setText("重度肥胖");
            level.setBackgroundColor(Color.rgb(180, 0, 10));
        }

        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent1 = new Intent();
                intent1.setClass(newsport1.this, sport.class);
                startActivity(intent1);
                newsport1.this.finish();
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

}
