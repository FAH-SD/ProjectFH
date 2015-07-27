package com.example.goldas.projectfh;

/**
 * Created by Goldas on 2015/6/15.
 */
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class homepage extends Activity implements View.OnClickListener{


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
// 设置侧拉条目布局

//        Button lefthome = (Button)findViewById(R.id.lefthome);
//        lefthome.setOnClickListener(this);
//
//        Button leftfamily = (Button)findViewById(R.id.leftfamily);
//        leftfamily.setOnClickListener(this);
//
//        Button lefticebox = (Button)findViewById(R.id.lefticebox);
//        lefticebox.setOnClickListener(this);
//
//        Button leftdish = (Button)findViewById(R.id.leftdish);
//        leftdish.setOnClickListener(this);
//
//        Button leftsport = (Button)findViewById(R.id.leftsport);
//        leftsport.setOnClickListener(this);
//
//        Button leftset = (Button)findViewById(R.id.leftset);
//        leftset.setOnClickListener(this);

        Button family = (Button) findViewById(R.id.family);
        family.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent1 = new Intent();
                intent1.setClass(homepage.this, family.class);
                startActivity(intent1);
            }
        });
        Button icebox = (Button) findViewById(R.id.iceboxx);
        icebox.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent2 = new Intent();
                intent2.setClass(homepage.this, icebox.class);
                startActivity(intent2);
            }
        });

        Button dish = (Button) findViewById(R.id.dish);
        dish.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent3 = new Intent();
                intent3.setClass(homepage.this, dish.class);
                startActivity(intent3);
            }
        });

        Button sport = (Button) findViewById(R.id.sport);
        sport.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent5 = new Intent();
                intent5.setClass(homepage.this, sport.class);
                startActivity(intent5);
            }
        });
        Button set = (Button) findViewById(R.id.set);
        set.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent4 = new Intent();
                intent4.setClass(homepage.this, set.class);
                startActivity(intent4);
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
                break;
            case R.id.leftfamily:
                Intent intentfamily = new Intent();
                intentfamily.setClass(this, family.class);
                startActivity(intentfamily);
                break;
            case R.id.lefticebox:
                Intent intenticebox = new Intent();
                intenticebox.setClass(this, icebox.class);
                startActivity(intenticebox);
                break;
            case R.id.leftdish:
                Intent intentdish = new Intent();
                intentdish.setClass(this, dish.class);
                startActivity(intentdish);
                break;
            case R.id.leftsport:
                Intent intentsport = new Intent();
                intentsport.setClass(this, sport.class);
                break;
            case R.id.leftset:
                Intent intentset = new Intent();
                intentset.setClass(this, set.class);
                startActivity(intentset);
                break;
            default:
                break;

        }


    }

//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.lefthome:
//                rightFragment fragment = new rightFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.right_layout, fragment);
//                transaction.commit();
//                break;
//            case R.id.leftfamily:
//                familyFragment fragment1 = new familyFragment();
//                FragmentManager fragmentManager1 = getFragmentManager();
//                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
//                transaction1.replace(R.id.right_layout, fragment1);
//                transaction1.commit();
//                fragment1.getActivity();
//                break;
//            case R.id.lefticebox:
//                iceboxFragment fragment5 = new iceboxFragment();
//                FragmentManager fragmentManager5 = getFragmentManager();
//                FragmentTransaction transaction5 = fragmentManager5.beginTransaction();
//                transaction5.replace(R.id.right_layout, fragment5);
//                transaction5.commit();
//                fragment5.getActivity();
//                break;
//            case R.id.leftdish:
//                dishFragment fragment2 = new dishFragment();
//                FragmentManager fragmentManager2 = getFragmentManager();
//                FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
//                transaction2.replace(R.id.right_layout, fragment2);
//                transaction2.commit();
//                break;
//            case R.id.leftsport:
//                sportFragment fragment3 = new sportFragment();
//                FragmentManager fragmentManager3 = getFragmentManager();
//                FragmentTransaction transaction3 = fragmentManager3.beginTransaction();
//                transaction3.replace(R.id.right_layout, fragment3);
//                transaction3.commit();
//                break;
//            case R.id.leftset:
//                setFragment fragment4 = new setFragment();
//                FragmentManager fragmentManager4 = getFragmentManager();
//                FragmentTransaction transaction4 = fragmentManager4.beginTransaction();
//                transaction4.replace(R.id.right_layout, fragment4);
//                transaction4.commit();
//                break;
//            default:
//                break;
//
//        }
//
//    }


//    public void addNewTab(Context context, Class<?> cls, String tabName){
//        Resources res = getResources();
//        Intent intent = new Intent().setClass(this, cls);
//        TabHost.TabSpec spec = getTabHost().newTabSpec(tabName)
//                .setIndicator(tabName, res.getDrawable(R.drawable.home50))
//                .setContent(intent);
//        getTabHost().addTab(spec);
//    }
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    // TODO Auto-generated method stub

    if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
        new AlertDialog.Builder(homepage.this)
                .setTitle("確認視窗")
                .setMessage("確定要離開應用程式嗎？")
                .setIcon(R.drawable.icon_main_s)
                .setPositiveButton("確定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub

                            }
                        }).show();
    }
    return true;
}



}