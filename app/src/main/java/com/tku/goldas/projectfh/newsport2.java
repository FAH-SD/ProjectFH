package com.tku.goldas.projectfh;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class newsport2 extends Activity implements View.OnClickListener{
    int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsport2);

        EditText name = (EditText)findViewById(R.id.et_sname);
        final EditText height = (EditText)findViewById(R.id.et_sheight);
        final EditText weight = (EditText)findViewById(R.id.et_sweight);
        final EditText BMI = (EditText)findViewById(R.id.et_sBMI);
        final EditText level = (EditText)findViewById(R.id.et_slevel);
        final EditText calorie = (EditText)findViewById(R.id.et_calorie);

        final Bundle bundle3 = this.getIntent().getExtras();
        userid = bundle3.getInt("userid");
        name.setText(bundle3.getString("name"));
        height.setText(bundle3.getString("height"));
        weight.setText(bundle3.getString("weight"));
        BMI.setText(bundle3.getString("BMI"));
        level.setText(bundle3.getString("level"));

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
                Bundle bundle4 = new Bundle();
                bundle4.putInt("userid", userid);
                bundle4.putString("height", height.getText().toString());
                bundle4.putString("weight", weight.getText().toString());
                bundle4.putString("BMI", BMI.getText().toString());
                bundle4.putString("level", level.getText().toString());
                Intent intent1 = new Intent();
                intent1.setClass(newsport2.this, sport.class);
                intent1.putExtras(bundle4);
                startActivity(intent1);
                newsport2.this.finish();
            }
        });


        Button btn_ok = (Button)findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if("".equals(calorie.getText().toString())) {
                    showAlert("錯誤資訊","尚未輸入卡路里");

                }else{
                    int c = Integer.parseInt(calorie.getText().toString());
                    if(c>1000 || c<0) {
                        calorie.setText("");
                        showAlert("錯誤資訊","輸入值0~1000大卡為限");
                    }else {
                        Intent intent2 = new Intent();
                        intent2.setClass(newsport2.this, SportList.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("kcal", calorie.getText().toString());
                        bundle.putString("weight", weight.getText().toString());
                        intent2.putExtras(bundle);
                        startActivity(intent2);
                    }
                    }
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

    private void showAlert(String title,String context){

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

}

