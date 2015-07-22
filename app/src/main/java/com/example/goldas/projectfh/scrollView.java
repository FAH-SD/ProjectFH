package com.example.goldas.projectfh;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;


public class scrollView extends Activity {


//        private ScrollView container = null;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            // 隐藏标题栏
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            // 设置视图
//            setContentView(R.layout.activity_scroll_view);
//
//            container = (ScrollView) findViewById(R.id.scrollView);
//
//            // 模块1
//            Button tab1 = (Button) findViewById(R.id.tab1);
//            tab1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    container.removeAllViews();
//                    container.addView(getLocalActivityManager().startActivity(
//                            "Module1",
//                            new Intent(scrollView.this, homepage.class)
//                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                            .getDecorView());
//                }
//            });
//
//            // 模块2
//            Button tab2 = (Button) findViewById(R.id.tab2);
//            tab2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    container.removeAllViews();
//                    container.addView(getLocalActivityManager().startActivity(
//                            "Module2",
//                            new Intent(scrollView.this, family.class)
//                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                            .getDecorView());
//                }
//            });
//
//            // 模块3
//            Button tab3 = (Button) findViewById(R.id.tab3);
//            tab3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    container.removeAllViews();
//                    container.addView(getLocalActivityManager().startActivity(
//                            "Module3",
//                            new Intent(scrollView.this, icebox.class)
//                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                            .getDecorView());
//                }
//            });
//        }
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_scroll_view);


        Button family = (Button) findViewById(R.id.family);
        family.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent1 = new Intent();
                intent1.setClass(scrollView.this, family.class);
                startActivity(intent1);
            }
        });
        Button icebox = (Button) findViewById(R.id.iceboxx);
        icebox.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentfreeze = new Intent();
                intentfreeze.setClass(scrollView.this, icebox.class);
                startActivity(intentfreeze);
            }
        });
        Button dish = (Button) findViewById(R.id.dish);
        dish.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentnormal = new Intent();
                intentnormal.setClass(scrollView.this, dish.class);
                startActivity(intentnormal);
            }
        });
        Button set = (Button) findViewById(R.id.set);
        set.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentfridge = new Intent();
                intentfridge.setClass(scrollView.this, set.class);
                startActivity(intentfridge);
            }
        });
    }
}
