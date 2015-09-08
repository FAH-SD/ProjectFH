package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.lang.reflect.Method;
import java.sql.SQLException;


public class dish extends Activity implements View.OnClickListener{
    private SQLiteDatabase db = null;
    Cursor cursor;
    SimpleCursorAdapter adapter = null;
    ListView dishview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        dishview = (ListView) findViewById(R.id.listView4);
        db = (new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor= db.rawQuery("SELECT _id,d_dishname from dish", null);
        adapter = new SimpleCursorAdapter(this, R.layout.dishlist, cursor, new String[]{"d_dishname"},new int[]{R.id.di_name});
        dishview.setAdapter(adapter);

        dishview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                try {
                    Cursor c2 = get(id);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("dishid", c2.getInt(0));
                    bundle3.putString("dishname",c2.getString(1));
                    bundle3.putString("member",c2.getString(2));
                    bundle3.putString("amount", c2.getString(3));
                    bundle3.putString("f1", c2.getString(4));
                    bundle3.putString("qf1",c2.getString(5));
                    bundle3.putString("uf1",c2.getString(6));
                    bundle3.putString("f2", c2.getString(7));
                    bundle3.putString("qf2",c2.getString(8));
                    bundle3.putString("uf2",c2.getString(9));
                    bundle3.putString("f3", c2.getString(10));
                    bundle3.putString("qf3",c2.getString(11));
                    bundle3.putString("uf3",c2.getString(12));
                    bundle3.putString("f4", c2.getString(13));
                    bundle3.putString("qf4",c2.getString(14));
                    bundle3.putString("uf4",c2.getString(15));
                    bundle3.putString("f5", c2.getString(16));
                    bundle3.putString("qf5",c2.getString(17));
                    bundle3.putString("uf5", c2.getString(18));
                    bundle3.putString("f6", c2.getString(19));
                    bundle3.putString("qf6",c2.getString(20));
                    bundle3.putString("uf6",c2.getString(21));
                    bundle3.putString("f7", c2.getString(22));
                    bundle3.putString("qf7",c2.getString(23));
                    bundle3.putString("uf7",c2.getString(24));
                    bundle3.putString("f8", c2.getString(25));
                    bundle3.putString("qf8",c2.getString(26));
                    bundle3.putString("uf8",c2.getString(27));
                    bundle3.putString("f9", c2.getString(28));
                    bundle3.putString("qf9",c2.getString(29));
                    bundle3.putString("uf9",c2.getString(30));
                    Intent i = new Intent(dish.this, DishDetail.class);
                    i.putExtras(bundle3);
                    startActivity(i);
                    dish.this.finish();
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
                dish.this.finish();
            }
        });

        Button buttonadd = (Button)findViewById(R.id.add);
        buttonadd.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(dish.this, newdish.class);
                Bundle bundle = new Bundle();
                bundle.putString("member","尚未選取成員");
                bundle.putString("amount", "0");
                bundle.putString("dishname", "");
                bundle.putString("f1", "");
                bundle.putString("qf1","");
                bundle.putString("uf1","");
                bundle.putString("f2", "");
                bundle.putString("qf2","");
                bundle.putString("uf2","");
                bundle.putString("f3", "");
                bundle.putString("qf3","");
                bundle.putString("uf3","");
                bundle.putString("f4", "");
                bundle.putString("qf4","");
                bundle.putString("uf4","");
                bundle.putString("f5", "");
                bundle.putString("qf5","");
                bundle.putString("uf5","");
                bundle.putString("f6", "");
                bundle.putString("qf6","");
                bundle.putString("uf6","");
                bundle.putString("f7", "");
                bundle.putString("qf7","");
                bundle.putString("uf7","");
                bundle.putString("f8", "");
                bundle.putString("qf8","");
                bundle.putString("uf8","");
                bundle.putString("f9", "");
                bundle.putString("qf9", "");
                bundle.putString("uf9", "");
                bundle.putString("strbtn","1");
                intent1.putExtras(bundle);
                startActivity(intent1);
                dish.this.finish();
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

    public Cursor get(long rowId) throws SQLException {
        Cursor c = db.rawQuery("SELECT* from dish WHERE _id="+ rowId, null);
        if(c.getCount()>0){
            c.moveToFirst();
        }

        return c;

    }
}
