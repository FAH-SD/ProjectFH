package com.tku.goldas.projectfh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.preference.DialogPreference;
import android.support.v4.media.MediaDescriptionCompatApi21;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.lang.reflect.Method;


public class set extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);


        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                set.this.finish();
            }
        });
        Button facebook = (Button)findViewById(R.id.btn_facebook);
        facebook.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url1 = "https://www.facebook.com/104SDA5";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                startActivity(i);
            }
        });

        Button aboutus = (Button)findViewById(R.id.btn_about);
        final AlertDialog alertDialog = AD("關於我們","2015 SD_大四專題製作\n" +
                "\n" +
                "食健：健康飲食管理\n" +
                "Healthy lifestyle\n" +
                "—Healthy Catering Management\n" +
                "\n" +
                "指導教授：游佳萍\n" +
                "團隊成員：許哲維、羅　集、蔡孟庭");
        aboutus.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
            alertDialog.show();

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
                Bundle bundle1 = new Bundle();
                bundle1.putInt("userid", 0);
                bundle1.putString("height", "");
                bundle1.putString("weight", "");
                bundle1.putString("BMI", "");
                intentsport.putExtras(bundle1);
                startActivity(intentsport);
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
    private AlertDialog AD(String Title, String Message) {
        AlertDialog.Builder buidler = new AlertDialog.Builder(set.this);
        buidler.setTitle("關於我們");
        buidler.setMessage("2015 SD_大四專題製作\n" +
                "\n" +
                "食健：健康飲食管理\n" +
                "Healthy lifestyle\n" +
                "—Healthy Catering Management\n" +
                "\n" +
                "指導教授：游佳萍\n" +
                "團隊成員：許哲維、羅　集、蔡孟庭");
        buidler.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return buidler.create();
    }



}
