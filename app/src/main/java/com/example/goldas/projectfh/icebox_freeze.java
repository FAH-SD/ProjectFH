package com.example.goldas.projectfh;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.lang.reflect.Method;
import java.sql.SQLException;


public class icebox_freeze extends Activity implements View.OnClickListener{
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private SimpleCursorAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icebox_freeze);
        Button buttonadd = (Button) findViewById(R.id.add);
        ListView iceboxview = (ListView)findViewById(R.id.foodlist);
        iceboxview.setAdapter(adapter);

        iceboxview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                try {
                    Cursor c = get(id);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("foodkind", c.getString(1));
                    bundle1.putString("foodname", c.getString(2));
                    bundle1.putString("foodamount", c.getString(3));
                    bundle1.putString("foodbuyday", c.getString(5));
                    bundle1.putString("foodlimitday", c.getString(4));
                    bundle1.putString("foodstroage", c.getString(6));
                    Intent i = new Intent(icebox_freeze.this, foodDetail.class);
                    i.putExtras(bundle1);
                    startActivity(i);
                    icebox_freeze.this.finish();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
        db = (new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor = db.rawQuery("SELECT*FROM icebox WHERE storage =? ", new String[]{"冷凍"});
        adapter = new SimpleCursorAdapter(this, R.layout.teest, cursor, new String[]{"kind","item","quantity","buyingdate","limitdate","storage"}, new int[]{R.id.txtkind, R.id.txtitem, R.id.txtquan, R.id.txtbd, R.id.txtld, R.id.txts});

        buttonadd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent1 = new Intent();
                intent1.setClass(icebox_freeze.this, iceboxdbcreate.class);
                startActivity(intent1);
                icebox_freeze.this.finish();
            }});
        Button buttonall = (Button) findViewById(R.id.all);
        buttonall.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentall = new Intent();
                intentall.setClass(icebox_freeze.this, icebox.class);
                startActivity(intentall);
                icebox_freeze.this.finish();
            }});
        Button buttonnormal = (Button) findViewById(R.id.normal);
        buttonnormal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentnormal = new Intent();
                intentnormal.setClass(icebox_freeze.this, icebox_normal.class);
                startActivity(intentnormal);
                icebox_freeze.this.finish();
            }
        });
        Button buttonfridge = (Button) findViewById(R.id.fridge);
        buttonfridge.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentfridge = new Intent();
                intentfridge.setClass(icebox_freeze.this, icebox_fridge.class);
                startActivity(intentfridge);
                icebox_freeze.this.finish();
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

    public Cursor get(long rowId) throws SQLException{
        Cursor c = db.rawQuery("SELECT _id,kind,item,quantity,limitdate,buyingdate,storage from icebox WHERE _id="+ rowId, null);
        if(c.getCount()>0){
            c.moveToFirst();
        }

        return c;

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
