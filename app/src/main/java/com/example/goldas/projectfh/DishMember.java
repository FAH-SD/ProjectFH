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
import android.widget.TextView;
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
    String tv_food1, tv_food2, tv_food3, tv_food4, tv_food5, tv_food6, tv_food7, tv_food8, tv_food9;
    String tv_qfood1, tv_qfood2, tv_qfood3, tv_qfood4, tv_qfood5, tv_qfood6, tv_qfood7, tv_qfood8, tv_qfood9;
    String tv_ufood1, tv_ufood2, tv_ufood3, tv_ufood4, tv_ufood5, tv_ufood6, tv_ufood7, tv_ufood8, tv_ufood9;
    String strbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_member);
        db = (new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor = db.rawQuery("SELECT name from family", null);
        final String[] Stringlist = new String[cursor.getCount()];
        strmember = new String[cursor.getCount()];

        et_amount = (EditText)findViewById(R.id.editText);
        et_member = (EditText)findViewById(R.id.editText3);

        Bundle bundle2 = this.getIntent().getExtras();

        et_member.setText(bundle2.getString("member"));
        et_amount.setText(bundle2.getString("amount"));
        tv_food1 = bundle2.getString("f1");
        tv_qfood1 = bundle2.getString("qf1");
        tv_ufood1 = bundle2.getString("uf1");
        tv_food2 = bundle2.getString("f2");
        tv_qfood2 = bundle2.getString("qf2");
        tv_ufood2 = bundle2.getString("uf2");
        tv_food3 = bundle2.getString("f3");
        tv_qfood3 = bundle2.getString("qf3");
        tv_ufood3 = bundle2.getString("uf3");
        tv_food4 = bundle2.getString("f4");
        tv_qfood4 = bundle2.getString("qf4");
        tv_ufood4 = bundle2.getString("uf4");
        tv_food5 = bundle2.getString("f5");
        tv_qfood5 = bundle2.getString("qf5");
        tv_ufood5 = bundle2.getString("uf5");
        tv_food6 = bundle2.getString("f6");
        tv_qfood6 = bundle2.getString("qf6");
        tv_ufood6 = bundle2.getString("uf6");
        tv_food7 = bundle2.getString("f7");
        tv_qfood7 = bundle2.getString("qf7");
        tv_ufood7 = bundle2.getString("uf7");
        tv_food8 = bundle2.getString("f8");
        tv_qfood8 = bundle2.getString("qf8");
        tv_ufood8 = bundle2.getString("uf8");
        tv_food9 = bundle2.getString("f9");
        tv_qfood9 = bundle2.getString("qf9");
        tv_ufood9 = bundle2.getString("uf9");
        strbtn = bundle2.getString("strbtn");

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


        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putString("member","尚未選取成員");
                bundle.putString("amount", "0");
                bundle.putString("f1", tv_food1);
                bundle.putString("qf1",tv_qfood1);
                bundle.putString("uf1",tv_ufood1);
                bundle.putString("f2", tv_food2);
                bundle.putString("qf2",tv_qfood2);
                bundle.putString("uf2",tv_ufood2);
                bundle.putString("f3", tv_food3);
                bundle.putString("qf3",tv_qfood3);
                bundle.putString("uf3",tv_ufood3);
                bundle.putString("f4", tv_food4);
                bundle.putString("qf4",tv_qfood4);
                bundle.putString("uf4",tv_ufood4);
                bundle.putString("f5", tv_food5);
                bundle.putString("qf5",tv_qfood5);
                bundle.putString("uf5",tv_ufood5);
                bundle.putString("f6", tv_food6);
                bundle.putString("qf6",tv_qfood6);
                bundle.putString("uf6",tv_ufood6);
                bundle.putString("f7", tv_food7);
                bundle.putString("qf7",tv_qfood7);
                bundle.putString("uf7",tv_ufood7);
                bundle.putString("f8", tv_food8);
                bundle.putString("qf8",tv_qfood8);
                bundle.putString("uf8",tv_ufood8);
                bundle.putString("f9", tv_food9);
                bundle.putString("qf9",tv_qfood9);
                bundle.putString("uf9",tv_ufood9);
                bundle.putString("strbtn", strbtn);
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
                bundle.putString("f1", tv_food1);
                bundle.putString("qf1",tv_qfood1);
                bundle.putString("uf1",tv_ufood1);
                bundle.putString("f2", tv_food2);
                bundle.putString("qf2",tv_qfood2);
                bundle.putString("uf2",tv_ufood2);
                bundle.putString("f3", tv_food3);
                bundle.putString("qf3",tv_qfood3);
                bundle.putString("uf3",tv_ufood3);
                bundle.putString("f4", tv_food4);
                bundle.putString("qf4",tv_qfood4);
                bundle.putString("uf4",tv_ufood4);
                bundle.putString("f5", tv_food5);
                bundle.putString("qf5",tv_qfood5);
                bundle.putString("uf5",tv_ufood5);
                bundle.putString("f6", tv_food6);
                bundle.putString("qf6",tv_qfood6);
                bundle.putString("uf6",tv_ufood6);
                bundle.putString("f7", tv_food7);
                bundle.putString("qf7",tv_qfood7);
                bundle.putString("uf7",tv_ufood7);
                bundle.putString("f8", tv_food8);
                bundle.putString("qf8",tv_qfood8);
                bundle.putString("uf8",tv_ufood8);
                bundle.putString("f9", tv_food9);
                bundle.putString("qf9",tv_qfood9);
                bundle.putString("uf9",tv_ufood9);
                bundle.putString("strbtn", strbtn);
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
