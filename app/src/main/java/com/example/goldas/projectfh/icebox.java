package com.example.goldas.projectfh;



import com.example.goldas.projectfh.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.NetworkRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
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
import java.util.ArrayList;
import java.util.Date;


public class icebox extends Activity implements View.OnClickListener {
    private SQLiteDatabase db = null;
    Cursor cursor;
    SimpleCursorAdapter adapter = null;
    Spinner type;
    ListView iceboxview;
    String choose ="";
    int a = 0;
    ArrayList results = new ArrayList();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String ld = null;
    Date dt=new Date(); //今日時間
    String dts=sdf.format(dt);
    Long day2 = Long.valueOf(0);
    TextView tv_expired;
    TextView textview;
    String strtype = "全部";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icebox);
        iceboxview = (ListView) findViewById(R.id.foodlist);
        type = (Spinner)findViewById(R.id.type);
        db = (new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor=getAll();
        UpdateAdapter(cursor);

        final Spinner sp_type = (Spinner) findViewById(R.id.type);
        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                strtype = sp_type.getSelectedItem().toString();
                if (sp_type.getSelectedItemPosition() == 0) {
                    cursor = getAll();
                    UpdateAdapter(cursor);
                } else {
                    cursor = getKind(strtype);
                    UpdateAdapter(cursor);
                }
            }
        });


        tv_expired = (TextView) findViewById(R.id.tv_expired);
        textview = (TextView) findViewById(R.id.textview);



        if(a>0){
            tv_expired.setVisibility(View.VISIBLE);
            textview.setVisibility(View.VISIBLE);
            tv_expired.setText(String.valueOf(a));
            a = 0;
        }

        iceboxview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                try {
                    cursor.moveToPosition(position);
                    results.clear();
                    if (strtype.equals("全部")) {
                        cursor = getAll();
                        UpdateAdapter(cursor);
                    } else {
                        cursor = getKind(strtype);
                        UpdateAdapter(cursor);
                    }
                    Food clickedCategory = (Food) results.get(position);
                    int foodid = clickedCategory.getId();
                    Cursor c = get(foodid);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("foodid", c.getInt(0));
                    bundle1.putString("foodkind", c.getString(1));
                    bundle1.putString("foodname", c.getString(2));
                    bundle1.putString("foodamount", c.getString(3));
                    bundle1.putString("foodbuyday", c.getString(5));
                    bundle1.putString("foodlimitday", c.getString(4));
                    bundle1.putString("foodstorage", c.getString(6));
                    bundle1.putString("foodunit", c.getString(7));
                    Intent i = new Intent(icebox.this, foodDetail.class);
                    i.putExtras(bundle1);
                    startActivity(i);
                    icebox.this.finish();
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
                icebox.this.finish();
            }
        });

        Button buttonadd = (Button) findViewById(R.id.add);
        buttonadd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                    Intent intent1 = new Intent();
                    intent1.setClass(icebox.this, iceboxdbcreate.class);
                    startActivity(intent1);
                    icebox.this.finish();
            }
        });
        Button buttonfreeze = (Button) findViewById(R.id.freezer);
        buttonfreeze.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentfreeze = new Intent();
                intentfreeze.setClass(icebox.this, icebox_freeze.class);
                startActivity(intentfreeze);
                icebox.this.finish();
            }
        });
        Button buttonnormal = (Button) findViewById(R.id.normal);
        buttonnormal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentnormal = new Intent();
                intentnormal.setClass(icebox.this, icebox_normal.class);
                startActivity(intentnormal);
                icebox.this.finish();
            }
        });
        Button buttonfridge = (Button) findViewById(R.id.fridge);
        buttonfridge.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentfridge = new Intent();
                intentfridge.setClass(icebox.this, icebox_fridge.class);
                startActivity(intentfridge);
                icebox.this.finish();
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
        Cursor c = db.rawQuery("SELECT _id,kind,item,quantity,limitdate,buyingdate,storage,unit from icebox WHERE _id="+ rowId+" ORDER BY item", null);
        if(c.getCount()>0){
            c.moveToFirst();
        }

        return c;

    }

    public Cursor getAll(){ // 查詢所有資料

        Cursor c = db.rawQuery("SELECT _id, kind, item, quantity, limitdate, buyingdate, storage, unit from icebox ORDER BY item", null);
        return c;
    }

    public Cursor getKind(String strkind){ // 查詢Kind
        Cursor c = db.rawQuery("SELECT _id, kind, item, quantity, limitdate, buyingdate, storage, unit FROM icebox WHERE kind LIKE '%" + strkind + "%'  ORDER BY item", null);
        return c;
    }

    public void UpdateAdapter(Cursor cursor){
//當cursor有東西 並且數量 >=0
        if (cursor != null && cursor.getCount() >= 0){
            ListViewItem[] items = new ListViewItem[cursor.getCount()];
            int rows_num = cursor.getCount();
            if(rows_num != 0) {
                //將指標移至第一筆資料
                cursor.moveToFirst();

                for(int i=0; i<rows_num; i++) {
                    String ld = cursor.getString(4);
                    results.add(new Food(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7)));
                    try {
                        if("".equals(ld)) {
                            day2 = Long.valueOf(0);
                        }else{
                            Date dt2 = sdf.parse(ld);
                            Long ut2 = dt2.getTime();
                            Date dt3 = sdf.parse(dts);
                            Long ut3 = dt3.getTime();
                            Long timeP2 = ut2 - ut3;//毫秒差
                            day2 = timeP2 / 1000 * 60 * 60 * 24;//日差
                        }
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
            CustomAdapter customAdapter = new CustomAdapter(this, R.id.txtitem, items);
            iceboxview.setAdapter(customAdapter); // 將adapter增加到listview01中
            tv_expired = (TextView) findViewById(R.id.tv_expired);
            textview = (TextView) findViewById(R.id.textview);
            tv_expired.setText(String.valueOf(a));
            if(a>0){
                tv_expired.setVisibility(View.VISIBLE);
                textview.setVisibility(View.VISIBLE);
                tv_expired.setText(String.valueOf(a));
            }else{
                tv_expired.setVisibility(View.GONE);
                textview.setVisibility(View.GONE);
            } a = 0;
        }
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


    private void showAlert(String title,String context)
    {
        android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
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