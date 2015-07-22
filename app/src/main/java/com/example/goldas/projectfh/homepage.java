package com.example.goldas.projectfh;

/**
 * Created by Goldas on 2015/6/15.
 */
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;


public class homepage extends ActivityGroup{

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_page);
//        Button family = (Button) findViewById(R.id.family);
//        family.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent1 = new Intent();
//                intent1.setClass(homepage.this, family.class);
//                startActivity(intent1);
//            }
//        });
//        Button icebox = (Button) findViewById(R.id.iceboxx);
//        icebox.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent2 = new Intent();
//                intent2.setClass(homepage.this, icebox.class);
//                startActivity(intent2);
//            }
//        });
//
//        Button dish = (Button) findViewById(R.id.dish);
//        dish.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent3 = new Intent();
//                intent3.setClass(homepage.this, dish.class);
//                startActivity(intent3);
//            }
//        });
//
//        Button set = (Button) findViewById(R.id.set);
//        set.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent4 = new Intent();
//                intent4.setClass(homepage.this, set.class);
//                startActivity(intent4);
//            }
//        });
//    }
public ScrollView container = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隱藏標題欄
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 設置視圖
        setContentView(R.layout.activity_home_page);

        container = (ScrollView) findViewById(R.id.containerBody);
        container.addView(getLocalActivityManager().startActivity("homepage",
                new Intent(homepage.this, scrollView.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                .getDecorView());

        // 模块1
        ImageView btnModule1 = (ImageView) findViewById(R.id.btnModule1);
        btnModule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                container.addView(getLocalActivityManager().startActivity(
                        "homepage",
                        new Intent(homepage.this, scrollView.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView());
            }
        });

        // 模块2
        ImageView btnModule2 = (ImageView) findViewById(R.id.btnModule2);
        btnModule2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                container.addView(getLocalActivityManager().startActivity(
                        "family",
                        new Intent(homepage.this, family.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView());
            }
        });

        // 模块3
        ImageView btnModule3 = (ImageView) findViewById(R.id.btnModule3);
        btnModule3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                container.addView(getLocalActivityManager().startActivity(
                        "icebox",
                        new Intent(homepage.this, icebox.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView());
            }
        });

        // 模块4
        ImageView btnModule4 = (ImageView) findViewById(R.id.btnModule4);
        btnModule4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                container.addView(getLocalActivityManager().startActivity(
                        "dish",
                        new Intent(homepage.this, dish.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView());
            }
        });

        // 模块6
        ImageView btnModule5 = (ImageView) findViewById(R.id.btnModule6);
        btnModule5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                container.addView(getLocalActivityManager().startActivity(
                        "set",
                        new Intent(homepage.this, set.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView());
            }
        });

    }

}
