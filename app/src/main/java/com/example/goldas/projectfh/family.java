package com.example.goldas.projectfh;



import com.example.goldas.projectfh.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.view.View.OnClickListener;
import android.view.ContextMenu.ContextMenuInfo;

import java.lang.reflect.Method;

/**
 * Created by Goldas on 2015/6/11.
 */
public class family extends ListActivity {
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
        setListAdapter(adapter);
//        registerForContextMenu(getListView());


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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        setIconEnable(menu, true);

        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        // TODO Auto-generated method stub
        return super.onPrepareOptionsMenu(menu);
    }

    //enable为true时，菜单添加图标有效，enable为false时无效。4.0系统默认无效
    private void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.homepage:
                Intent intenthomepage = new Intent();
                intenthomepage.setClass(this, homepage.class);
                startActivity(intenthomepage);
                this.finish();
                break;
            case R.id.family:
                Intent intentfamily = new Intent();
                intentfamily.setClass(this, family.class);
                startActivity(intentfamily);
                this.finish();
                break;
            case R.id.icebox:
                Intent intenticebox = new Intent();
                intenticebox.setClass(this, icebox.class);
                startActivity(intenticebox);
                this.finish();
                break;
            case R.id.dish:
                Intent intentdish = new Intent();
                intentdish.setClass(this, dish.class);
                startActivity(intentdish);
                this.finish();
                break;

            case R.id.set:
                Intent intentset = new Intent();
                intentset.setClass(this, set.class);
                startActivity(intentset);
                this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);

        }

        return  true;
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
//
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