package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import static android.provider.BaseColumns._ID;
import static com.example.goldas.projectfh.DBconstant.ITEM;
import static com.example.goldas.projectfh.DBconstant.KIND;
import static com.example.goldas.projectfh.DBconstant.QUANTITY;
import static com.example.goldas.projectfh.DBconstant.TABLE_NAME;
import static com.example.goldas.projectfh.DBconstant.BUYINGDATE;
import static com.example.goldas.projectfh.DBconstant.LIMITDATE;
import static com.example.goldas.projectfh.DBconstant.STORAGEPLACE;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Method;


public class newfood1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfood1);
        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_iback);
buttonback.setOnClickListener(new ImageButton.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent1 = new Intent();
        intent1.setClass(newfood1.this, icebox.class);
        startActivity(intent1);
        newfood1.this.finish();
        }
        });
        Button buttonin2 = (Button)findViewById(R.id.btn_iin2);
        buttonin2.setOnClickListener(new Button.OnClickListener(){
@Override
public void onClick(View v) {
        Intent intent2 = new Intent();
        intent2.setClass(newfood1.this, newfood2.class);
        startActivity(intent2);
        newfood1.this.finish();
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
        }





