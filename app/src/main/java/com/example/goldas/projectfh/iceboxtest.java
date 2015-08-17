package com.example.goldas.projectfh;



import com.example.goldas.projectfh.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.view.View.OnClickListener;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class iceboxtest extends Activity implements View.OnClickListener {
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private SimpleCursorAdapter adapter = null;
    Spinner type;
    private ListView iceboxview;
    String choose ="";
    int a = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iceboxtest);
        iceboxview = (ListView) findViewById(R.id.foodlist);
        type = (Spinner)findViewById(R.id.type);
        db = (new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor = db.rawQuery("SELECT _id, kind, item, quantity, limitdate, buyingdate, storage, unit from icebox", null);
        final String[] Stringlist = new String[cursor.getCount()];
        String ld;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date dt=new Date();
        String dts=sdf.format(dt);
        TextView tv_expired = (TextView) findViewById(R.id.tv_expired);
        TextView textview = (TextView) findViewById(R.id.textview);

        final ListViewItem[] items = new ListViewItem[cursor.getCount()];



        int rows_num = cursor.getCount();
        if(rows_num != 0) {
            //將指標移至第一筆資料
            cursor.moveToFirst();

            for(int i=0; i<rows_num; i++) {
                ld = cursor.getString(4);
                try {
                    Date dt2 = sdf.parse(ld);
                    Long ut2=dt2.getTime();
                    Date dt3 = sdf.parse(dts);
                    Long ut3=dt3.getTime();
                    Long timeP2=ut2-ut3;//毫秒差
                    Long day2=timeP2/1000*60*60*24;//日差
                    if(day2<0){
                        items[i] = new ListViewItem(cursor.getString(2), cursor.getString(3),cursor.getString(7),cursor.getString(4), CustomAdapter.TYPE_EXPIRED);
                        a++;
                    }else{
                        items[i] = new ListViewItem(cursor.getString(2), cursor.getString(3),cursor.getString(7),cursor.getString(4), CustomAdapter.TYPE_FRESH);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //將指標移至下一筆資料
                cursor.moveToNext();
            }
        }
        //關閉Cursor
        cursor.close();

        if(a>0){
            tv_expired.setVisibility(View.VISIBLE);
            textview.setVisibility(View.VISIBLE);
            tv_expired.setText(String.valueOf(a));
        }

        CustomAdapter customAdapter = new CustomAdapter(this, R.id.txtitem, R.id.txtquan, R.id.txtunit, R.id.txtld, items);
        iceboxview.setAdapter(customAdapter);
        iceboxview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    Cursor c = get(id);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("foodid", c.getInt(0));
                    bundle1.putString("foodkind", c.getString(1));
                    bundle1.putString("foodname", c.getString(2));
                    bundle1.putString("foodamount", c.getString(3));
                    bundle1.putString("foodbuyday", c.getString(5));
                    bundle1.putString("foodlimitday", c.getString(4));
                    bundle1.putString("foodstorage", c.getString(6));
                    bundle1.putString("foodunit", c.getString(7));
                    Intent i = new Intent(iceboxtest.this, foodDetail.class);
                    i.putExtras(bundle1);
                    startActivity(i);
                    iceboxtest.this.finish();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                iceboxtest.this.finish();
            }
        });

        Button buttonadd = (Button) findViewById(R.id.add);
        buttonadd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent1 = new Intent();
                intent1.setClass(iceboxtest.this, iceboxdbcreate.class);
                startActivity(intent1);
                iceboxtest.this.finish();
            }
        });
        Button buttonfreeze = (Button) findViewById(R.id.freezer);
        buttonfreeze.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentfreeze = new Intent();
                intentfreeze.setClass(iceboxtest.this, icebox_freeze.class);
                startActivity(intentfreeze);
                iceboxtest.this.finish();
            }
        });
        Button buttonnormal = (Button) findViewById(R.id.normal);
        buttonnormal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentnormal = new Intent();
                intentnormal.setClass(iceboxtest.this, icebox_normal.class);
                startActivity(intentnormal);
                iceboxtest.this.finish();
            }
        });
        Button buttonfridge = (Button) findViewById(R.id.fridge);
        buttonfridge.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentfridge = new Intent();
                intentfridge.setClass(iceboxtest.this, icebox_fridge.class);
                startActivity(intentfridge);
                iceboxtest.this.finish();
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
        Cursor c = db.rawQuery("SELECT _id,kind,item,quantity,limitdate,buyingdate,storage,unit from icebox WHERE _id="+ rowId, null);
        if(c.getCount()>0){
            c.moveToFirst();
        }
        return c;
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