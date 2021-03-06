package com.tku.goldas.projectfh;



import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;

/**
 * Created by Goldas on 2015/6/11.
 */
public class family extends Activity implements View.OnClickListener{
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private SimpleCursorAdapter adapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forthescience);
        db=(new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor= db.rawQuery("SELECT _id,name from family",null);
        adapter = new SimpleCursorAdapter(this, R.layout.familylist, cursor, new String[]{"name"},new int[]{R.id.fl_name});
        ListView familyview = (ListView)findViewById(R.id.list);
        familyview.setAdapter(adapter);
//        registerForContextMenu(getListView());

        familyview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                try {
                    Cursor c2 = get(id);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("memberid", c2.getInt(0));
                    bundle3.putString("membername", c2.getString(1));
                    bundle3.putString("membersex", c2.getString(2));
                    bundle3.putString("memberheight", c2.getString(3));
                    bundle3.putString("memberweight", c2.getString(4));
                    bundle3.putString("memberbirthyear", c2.getString(5));
                    bundle3.putString("allergyegg", c2.getString(6));
                    bundle3.putString("allergyfish", c2.getString(7));
                    bundle3.putString("allergypeanut", c2.getString(8));
                    bundle3.putString("allergynut", c2.getString(9));
                    bundle3.putString("allergyshellfish", c2.getString(10));
                    bundle3.putString("allergybeans", c2.getString(11));
                    bundle3.putString("allergymilk", c2.getString(12));
                    bundle3.putString("allergychicken", c2.getString(13));
                    bundle3.putString("allergypork", c2.getString(14));
                    bundle3.putString("allergybeef", c2.getString(15));
                    bundle3.putString("memberhabit", c2.getString(16));
                    Intent i = new Intent(family.this, familydetail.class);
                    i.putExtras(bundle3);
                    startActivity(i);
                    family.this.finish();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });


        Button buttonadd = (Button) findViewById(R.id.add);
        buttonadd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent1 = new Intent();
                intent1.setClass(family.this, familycreate.class);
                startActivity(intent1);
                family.this.finish();
            }
        });


        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                family.this.finish();
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
        Cursor c = db.rawQuery("SELECT _id,name,sex,height,weight,birthyear,allergy1,allergy2,allergy3,allergy4,allergy5,allergy6,allergy7,allergy8,allergy9,allergy10,habit from family WHERE _id="+ rowId, null);
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





    protected void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }


//    public void onCreateContextMenu(ContextMenu menu, View v,  ContextMenuInfo menuInfo) {
//        menu.add(Menu.NONE,,Menu.NONE,"Delete");
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }
//.ㄐ
//    //步骤 3: ContextMenu的触发操作，例子将触发delete()
//    public boolean onContextItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case DELETE_ID:
//            /* 在此处，我们关键引入 AdapterView.AdapterContextMenuInfo来获取单元的信息。在有三个重要的信息。 1、id：The row id of the item for which the context menu is being displayed ，在cursorAdaptor中，实际就是表格的_id序号； 2、position 是list的元素的顺序；3、view就可以获得list中点击元素的View，通过view可以获取里面的显示的信息   */
//                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//                delete(info.id);
//                return true;
//            default:
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }
//
//    //步骤4: 对触发弹框，和Add的相似，确定后，更新数据库和更新ListView的显示，上次学习已有相类的例子，不再重复。其中getNameById是通过id查名字的方法。值得注意的是，为了内部类中使用，delete的参数采用来final的形式。
//    private void delete(final long  rowId){
//        if(rowId>0){
//            new AlertDialog.Builder(this)
//                    .setTitle("删除" + getNameById(rowId))
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            deleteData(rowId);
//                        }
//                    })
//                    .setNegativeButton("取消", null)
//                    .show();
//        }
//    }
//
//
//    private void deleteData(long rowId){
//        String[] str={String.valueOf(rowId)};
//        db.delete("family","_id=?",str);
//        cursor.requery();
//    }
}