package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;

public class DishMember extends Activity {
    private SQLiteDatabase db = null;
    private Cursor cursor = null;

    EditText et_member,et_amount;
    private int index = 0;
    int index2 = 0;
    String strindex;
    String[] strmember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_member);
        db = (new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor = db.rawQuery("SELECT name from family", null);
        final String[] Stringlist = new String[cursor.getCount()];
        strmember = new String[cursor.getCount()];

//取得資料表筆數
        int rows_num = cursor.getCount();
        if(rows_num != 0) {

            //將指標移至第一筆資料
            cursor.moveToFirst();

            for(int i=0; i<rows_num; i++) {
                Stringlist[i] = cursor.getString(0);

                //將指標移至下一筆資料
                cursor.moveToNext();
            }
        }
        //關閉Cursor
        cursor.close();

        ListAdapter adapter;
        adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_multiple_choice ,Stringlist);
        //使用ListAdapter來顯示你輸入的文字
        final ListView familylist = (ListView) findViewById(R.id.listView2);
        familylist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        familylist.setAdapter(adapter);
        //將ListAdapter設定至ListView裡面

        et_amount = (EditText)findViewById(R.id.editText);
        et_member = (EditText)findViewById(R.id.editText3);
        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putString("member","尚未選取成員");
                bundle.putString("amount","0");
                Intent intent2 = new Intent();
                intent2.setClass(DishMember.this, newdish.class);
                intent2.putExtras(bundle);
                startActivity(intent2);
                DishMember.this.finish();
            }
        });

        ImageButton buttontrue = (ImageButton) findViewById(R.id.btn_true);
        buttontrue.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putString("member", et_member.getText().toString());
                bundle.putString("amount", et_amount.getText().toString());
                Intent intent1 = new Intent();
                intent1.setClass(DishMember.this, newdish.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
                DishMember.this.finish();
            }
        });
        Button btn_choose = (Button) findViewById(R.id.btn_choose);
        btn_choose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String temp2 = "";
                for (int i = 0; i < familylist.getCount(); i++) {
                    if (familylist.isItemChecked(i)) {
                        index++;
                        String temp = Stringlist[i];
                        strmember[index2] = temp;
                        temp2 = temp2 + strmember[i] + " ";
                    }
                    index2++;
                }
                String stramount = String.valueOf(index);
                et_amount.setText(stramount);

                et_member.setText(temp2);
                index = 0;
                index2 = 0;

            }
        });

        Button btn_clear = (Button) findViewById(R.id.btn_clearall);
        btn_clear.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                for (int i = 0; i < familylist.getCount(); i++) {
                    familylist.setItemChecked(i,false);
                }
                index = 0;
                et_amount.setText("");
                et_member.setText("尚未選取成員");
            }
        });

    }

}
