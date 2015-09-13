package com.tku.goldas.projectfh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.tku.goldas.projectfh.BDBconstant.BTABLE_NAME;
import static com.tku.goldas.projectfh.BDBconstant.B_ITEM;
import static com.tku.goldas.projectfh.BDBconstant.B_QUANTITY;
import static com.tku.goldas.projectfh.BDBconstant.B_UNIT;
import static com.tku.goldas.projectfh.BDBconstant.B_DATE;
import static com.tku.goldas.projectfh.DBconstant.QUANTITY;

public class DishFood extends Activity {
    private SQLiteDatabase db = null;
    DBhelper dbhelper;
    Cursor cursor;
    String strtype = "全部";
    ListView foodlist;
    String item, amount, unit, maxamount;
    float a, ma;
    int foodid;
    EditText et_choose;
    String aid;
    String strbtn;
    String tv_member, tv_amount, dishname;
    String tv_food1, tv_food2, tv_food3, tv_food4, tv_food5, tv_food6, tv_food7, tv_food8, tv_food9;
    String tv_qfood1, tv_qfood2, tv_qfood3, tv_qfood4, tv_qfood5, tv_qfood6, tv_qfood7, tv_qfood8, tv_qfood9;
    String tv_ufood1, tv_ufood2, tv_ufood3, tv_ufood4, tv_ufood5, tv_ufood6, tv_ufood7, tv_ufood8, tv_ufood9;
    String itv_ufood1,itv_ufood2,itv_ufood3,itv_ufood4,itv_ufood5,itv_ufood6,itv_ufood7,itv_ufood8,itv_ufood9;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String ld = null;
    Date dt=new Date(); //今日時間
    String dts=sdf.format(dt);
    Long day2 = Long.valueOf(0);
    ArrayList results = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_food);

        final EditText et_item = (EditText) findViewById(R.id.et_item);
        foodlist = (ListView) findViewById(R.id.listView3);
        et_choose = (EditText) findViewById(R.id.et_choose);

        Bundle bundle2 = this.getIntent().getExtras();
        tv_member = bundle2.getString("member");
        tv_amount = bundle2.getString("amount");
        dishname = bundle2.getString("dishname");

        tv_food1 = bundle2.getString("f1");
        tv_qfood1 = bundle2.getString("qf1");
        tv_ufood1 = bundle2.getString("uf1");
        itv_ufood1 = bundle2.getString("idf1");
        tv_food2 = bundle2.getString("f2");
        tv_qfood2 = bundle2.getString("qf2");
        tv_ufood2 = bundle2.getString("uf2");
        itv_ufood2 = bundle2.getString("idf2");
        tv_food3 = bundle2.getString("f3");
        tv_qfood3 = bundle2.getString("qf3");
        tv_ufood3 = bundle2.getString("uf3");
        itv_ufood3 = bundle2.getString("idf3");
        tv_food4 = bundle2.getString("f4");
        tv_qfood4 = bundle2.getString("qf4");
        tv_ufood4 = bundle2.getString("uf4");
        itv_ufood4 = bundle2.getString("idf4");
        tv_food5 = bundle2.getString("f5");
        tv_qfood5 = bundle2.getString("qf5");
        tv_ufood5 = bundle2.getString("uf5");
        itv_ufood5 = bundle2.getString("idf5");
        tv_food6 = bundle2.getString("f6");
        tv_qfood6 = bundle2.getString("qf6");
        tv_ufood6 = bundle2.getString("uf6");
        itv_ufood6 = bundle2.getString("idf6");
        tv_food7 = bundle2.getString("f7");
        tv_qfood7 = bundle2.getString("qf7");
        tv_ufood7 = bundle2.getString("uf7");
        itv_ufood7 = bundle2.getString("idf7");
        tv_food8 = bundle2.getString("f8");
        tv_qfood8 = bundle2.getString("qf8");
        tv_ufood8 = bundle2.getString("uf8");
        itv_ufood8 = bundle2.getString("idf8");
        tv_food9 = bundle2.getString("f9");
        tv_qfood9 = bundle2.getString("qf9");
        tv_ufood9 = bundle2.getString("uf9");
        itv_ufood9 = bundle2.getString("idf9");
        strbtn = bundle2.getString("strbtn");

        dbhelper = new DBhelper(this);
        db = (new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor = getAll();
        UpdateAdapter(cursor);

        foodlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    cursor.moveToPosition(position);
                    Food clickedCategory = (Food) results.get(position);
                    foodid = clickedCategory.getId();
                    Cursor c2 = get(foodid);
                    maxamount = c2.getString(2);
                    unit = c2.getString(3);
                    aid = c2.getString(5);
                    item = c2.getString(1);
                    item = item.replace(" ", "");
                    showAlert();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });


        final Spinner sp_type = (Spinner) findViewById(R.id.type);
        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                results.clear();
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


        Button btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                results.clear();
                cursor = getItem(et_item.getText().toString());
                UpdateAdapter(cursor);
            }
        });

        Button btn_wtbuy = (Button) findViewById(R.id.btn_wtbuy);
        btn_wtbuy.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
                    Bundle b1 = new Bundle();
                    b1.putString("member", tv_member);
                    b1.putString("amount", tv_amount);
                    b1.putString("dishname", dishname);
                    b1.putString("f1", tv_food1);
                    b1.putString("qf1", tv_qfood1);
                    b1.putString("uf1", tv_ufood1);
                    b1.putString("idf1",itv_ufood1);
                    b1.putString("f2", tv_food2);
                    b1.putString("qf2", tv_qfood2);
                    b1.putString("uf2", tv_ufood2);
                    b1.putString("idf2", itv_ufood2);
                    b1.putString("f3", tv_food3);
                    b1.putString("qf3", tv_qfood3);
                    b1.putString("uf3", tv_ufood3);
                    b1.putString("idf3", itv_ufood3);
                    b1.putString("f4", tv_food4);
                    b1.putString("qf4", tv_qfood4);
                    b1.putString("uf4", tv_ufood4);
                    b1.putString("idf4", itv_ufood4);
                    b1.putString("f5", tv_food5);
                    b1.putString("qf5", tv_qfood5);
                    b1.putString("uf5", tv_ufood5);
                    b1.putString("idf5", itv_ufood5);
                    b1.putString("f6", tv_food6);
                    b1.putString("qf6", tv_qfood6);
                    b1.putString("uf6", tv_ufood6);
                    b1.putString("idf6", itv_ufood6);
                    b1.putString("f7", tv_food7);
                    b1.putString("qf7", tv_qfood7);
                    b1.putString("uf7", tv_ufood7);
                    b1.putString("idf7", itv_ufood7);
                    b1.putString("f8", tv_food8);
                    b1.putString("qf8", tv_qfood8);
                    b1.putString("uf8", tv_ufood8);
                    b1.putString("idf8", itv_ufood8);
                    b1.putString("f9", tv_food9);
                    b1.putString("qf9", tv_qfood9);
                    b1.putString("uf9", tv_ufood9);
                    b1.putString("idf9", itv_ufood9);
                    b1.putString("strbtn", strbtn);
                    Intent intent1 = new Intent();
                    intent1.setClass(DishFood.this, DishFood2.class);
                    intent1.putExtras(b1);
                    startActivity(intent1);
                    DishFood.this.finish();
                }else{
                    showAlert2("錯誤資訊", "尚未連線至網路");
                }
            }
        });



        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b1 = new Bundle();
                b1.putString("member", tv_member);
                b1.putString("amount", tv_amount);
                b1.putString("dishname", dishname);
                b1.putString("f1", tv_food1);
                b1.putString("qf1", tv_qfood1);
                b1.putString("uf1", tv_ufood1);
                b1.putString("idf1",itv_ufood1);
                b1.putString("f2", tv_food2);
                b1.putString("qf2", tv_qfood2);
                b1.putString("uf2", tv_ufood2);
                b1.putString("idf2", itv_ufood2);
                b1.putString("f3", tv_food3);
                b1.putString("qf3", tv_qfood3);
                b1.putString("uf3", tv_ufood3);
                b1.putString("idf3", itv_ufood3);
                b1.putString("f4", tv_food4);
                b1.putString("qf4", tv_qfood4);
                b1.putString("uf4", tv_ufood4);
                b1.putString("idf4", itv_ufood4);
                b1.putString("f5", tv_food5);
                b1.putString("qf5", tv_qfood5);
                b1.putString("uf5", tv_ufood5);
                b1.putString("idf5", itv_ufood5);
                b1.putString("f6", tv_food6);
                b1.putString("qf6", tv_qfood6);
                b1.putString("uf6", tv_ufood6);
                b1.putString("idf6", itv_ufood6);
                b1.putString("f7", tv_food7);
                b1.putString("qf7", tv_qfood7);
                b1.putString("uf7", tv_ufood7);
                b1.putString("idf7", itv_ufood7);
                b1.putString("f8", tv_food8);
                b1.putString("qf8", tv_qfood8);
                b1.putString("uf8", tv_ufood8);
                b1.putString("idf8", itv_ufood8);
                b1.putString("f9", tv_food9);
                b1.putString("qf9", tv_qfood9);
                b1.putString("uf9", tv_ufood9);
                b1.putString("idf9", itv_ufood9);
                b1.putString("strbtn", strbtn);
                Intent intent1 = new Intent();
                intent1.setClass(DishFood.this, newdish.class);
                intent1.putExtras(b1);
                startActivity(intent1);
                DishFood.this.finish();

            }
        });


        ImageButton btn_true = (ImageButton) findViewById(R.id.btn_true);
        btn_true.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.equals("")) {
                    showAlert2("錯誤資訊", "數量尚未輸入");
                }else {
                    a = Float.parseFloat(amount);
                    ma = Float.parseFloat(maxamount);
                }
                if(a>ma){
                    showAlert3("警告視窗", "輸入數量超過庫存，是否將"+item+(a-ma)+unit+"加入購買清單?");

                }

                if(a<=ma){
                    switch (strbtn) {
                        case "1":
                            tv_food1 = item;
                            tv_qfood1 = amount;
                            tv_ufood1 = unit;
                            itv_ufood1=aid;
                            break;
                        case "2":
                            tv_food2 = item;
                            tv_qfood2 = amount;
                            tv_ufood2 = unit;
                            itv_ufood2=aid;
                            break;
                        case "3":
                            tv_food3 = item;
                            tv_qfood3 = amount;
                            tv_ufood3 = unit;
                            itv_ufood3=aid;
                            break;
                        case "4":
                            tv_food4 = item;
                            tv_qfood4 = amount;
                            tv_ufood4 = unit;
                            itv_ufood4=aid;
                            break;
                        case "5":
                            tv_food5 = item;
                            tv_qfood5 = amount;
                            tv_ufood5 = unit;
                            itv_ufood5=aid;
                            break;
                        case "6":
                            tv_food6 = item;
                            tv_qfood6 = amount;
                            tv_ufood6 = unit;
                            itv_ufood6=aid;
                            break;
                        case "7":
                            tv_food7 = item;
                            tv_qfood7 = amount;
                            tv_ufood7 = unit;
                            itv_ufood7=aid;
                            break;
                        case "8":
                            tv_food8 = item;
                            tv_qfood8 = amount;
                            tv_ufood8 = unit;
                            itv_ufood8=aid;
                            break;
                        case "9":
                            tv_food9 = item;
                            tv_qfood9 = amount;
                            tv_ufood9 = unit;
                            itv_ufood9 = aid;
                            break;
                    }
                    if(amount.equals(maxamount)){
                        deleteAlert("警告視窗", item + amount + unit + "將會從儲藏室刪除");
                    }else {
                        reviseAlert("警告視窗", item + amount + unit + "將會從儲藏室刪除");
                    }
                }

            }
        });

    }

    public void UpdateAdapter(Cursor cursor) {
//當cursor有東西 並且數量 >=0
        if (cursor != null && cursor.getCount() >= 0){
            ListViewItem[] items = new ListViewItem[cursor.getCount()];
            int rows_num = cursor.getCount();
            if(rows_num != 0) {
                //將指標移至第一筆資料
                cursor.moveToFirst();

                for(int i=0; i<rows_num; i++) {
                    String ld = cursor.getString(4);
                    results.add(new Food(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));
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
                            items[i] = new ListViewItem(cursor.getString(2), cursor.getString(3),cursor.getString(7),cursor.getString(4), CustomAdapter.TYPE_DISHLIST2);
                        }else{
                            items[i] = new ListViewItem(cursor.getString(2), cursor.getString(3),cursor.getString(7),cursor.getString(4), CustomAdapter.TYPE_DISHLIST);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //將指標移至下一筆資料
                    cursor.moveToNext();
                }
            }
            CustomAdapter customAdapter = new CustomAdapter(this, R.id.txtitem, items);

            foodlist.setAdapter(customAdapter);


        }
    }

    public Cursor get(long rowId) throws SQLException {
        Cursor c = db.rawQuery("SELECT _id, item,quantity,unit,limitdate,aid from icebox WHERE _id=" + rowId + " ORDER BY item", null);
        if (c.getCount() > 0) {
            c.moveToFirst();
        }

        return c;

    }

    public Cursor getAll() { // 查詢所有資料

        Cursor c = db.rawQuery("SELECT _id, kind, item, quantity, limitdate, buyingdate, storage, unit,aid from icebox ORDER BY item", null);
        return c;
    }

    public Cursor getKind(String strkind) { // 查詢Kind
        Cursor c = db.rawQuery("SELECT _id, kind, item, quantity, limitdate, buyingdate, storage, unit, aid FROM icebox WHERE kind LIKE '%" + strkind + "%'  ORDER BY item", null);
        return c;
    }


    public Cursor getItem(String stritem) { // 查詢Item
        Cursor c = db.rawQuery("SELECT _id, kind, item, quantity, limitdate, buyingdate, storage, unit FROM icebox  WHERE item LIKE '%" + stritem + "%'  ORDER BY item", null);
        return c;
    }


    private void showAlert() {
        LayoutInflater inflater = LayoutInflater.from(DishFood.this);
        final View view1 = inflater.inflate(R.layout.amountview, null);

        final EditText editText = (EditText) view1.findViewById(R.id.edittext);
        final TextView tv_unit = (TextView) view1.findViewById(R.id.tv_unit);
        tv_unit.setText(unit);
//                final Spinner sp_unit2 = (Spinner) view1.findViewById(R.id.sp_unit2);
//                if (item.contains("脂鮮乳") || item.contains("養樂多") || item.contains("優酪乳") || (item.contains("牛油") || item.contains("豬油") || item.contains("雞油") || item.contains("花生油") || item.contains("葵花油") || item.contains("玉米油") || item.contains("沙拉油") || item.contains("橄欖油") || item.contains("辣椒油") || item.contains("椰子油") | item.contains("芝麻油"))) {
//                    sp_unit.setVisibility(View.GONE);
//                    sp_unit2.setVisibility(View.VISIBLE);
//                } else {
//                    sp_unit2.setVisibility(View.GONE);
//                    sp_unit.setVisibility(View.VISIBLE);
//                }
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
//                        if (sp_unit.getVisibility() == View.VISIBLE) {
//                            unit = sp_unit.getSelectedItem().toString();
//                        } else {
//                            unit = sp_unit2.getSelectedItem().toString();
//                        }
                if (editText.getText().toString() != ""
                        && editText.getText().toString() != null) {
                    amount = editText.getText().toString();
                } else {
                    amount = "";
                }

                String text = item + ", " + amount + unit;
                et_choose.setText(text);
            }
        });

//                sp_unit.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view,
//                                               int position, long id) {
//                        if (sp_unit.getVisibility() == View.VISIBLE) {
//                            unit = sp_unit.getSelectedItem().toString();
//                        } else {
//                            unit = sp_unit2.getSelectedItem().toString();
//                        }
//
//                        if (editText.getText().toString() != ""
//                                && editText.getText().toString() != null) {
//                            amount = editText.getText().toString();
//                        } else {
//                            amount = "";
//                        }
//
//                        String text = item + ", " + amount + unit;
//                        et_choose.setText(text);
//
//                    }
//
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                        // TODO Auto-generated method stub
//
//                    }
//                });
//
//                sp_unit2.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view,
//                                               int position, long id) {
//                        if (sp_unit.getVisibility() == View.VISIBLE) {
//                            unit = sp_unit.getSelectedItem().toString();
//                        } else {
//                            unit = sp_unit2.getSelectedItem().toString();
//                        }
//
//                        if (editText.getText().toString() != ""
//                                && editText.getText().toString() != null) {
//                            amount = editText.getText().toString();
//                        } else {
//                            amount = "";
//                        }
//
//                        String text = item + ", " + amount + unit;
//                        et_choose.setText(text);
//
//                    }
//
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                        // TODO Auto-generated method stub
//
//                    }
//                });

        new AlertDialog.Builder(DishFood.this).setTitle("選取" + item )
                .setMessage("請輸入數量  (目前庫存" + maxamount + unit +")").setView(view1)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    private void showAlert2(String title, String context) {
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

    private void showAlert3(String title,String context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(context);
        alert.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                    }
                });
        alert.setPositiveButton("確定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                        // TODO Auto-generated method stub
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        long long1 = db.delete("icebox", " _id=" + foodid, null);
                        values.put(B_ITEM, item);
                        values.put(B_QUANTITY, (a-ma));
                        values.put(B_UNIT, unit);
                        values.put(B_DATE, dts);
                        db.insert(BTABLE_NAME, null, values);

                        switch (strbtn) {
                            case "1":
                                tv_food1 = item;
                                tv_qfood1 = amount;
                                tv_ufood1 = unit;
                                itv_ufood1=aid;
                                break;
                            case "2":
                                tv_food2 = item;
                                tv_qfood2 = amount;
                                tv_ufood2 = unit;
                                itv_ufood2=aid;
                                break;
                            case "3":
                                tv_food3 = item;
                                tv_qfood3 = amount;
                                tv_ufood3 = unit;
                                itv_ufood3=aid;
                                break;
                            case "4":
                                tv_food4 = item;
                                tv_qfood4 = amount;
                                tv_ufood4 = unit;
                                itv_ufood4=aid;
                                break;
                            case "5":
                                tv_food5 = item;
                                tv_qfood5 = amount;
                                tv_ufood5 = unit;
                                itv_ufood5=aid;
                                break;
                            case "6":
                                tv_food6 = item;
                                tv_qfood6 = amount;
                                tv_ufood6 = unit;
                                itv_ufood6=aid;
                                break;
                            case "7":
                                tv_food7 = item;
                                tv_qfood7 = amount;
                                tv_ufood7 = unit;
                                itv_ufood7=aid;
                                break;
                            case "8":
                                tv_food8 = item;
                                tv_qfood8 = amount;
                                tv_ufood8 = unit;
                                itv_ufood8=aid;
                                break;
                            case "9":
                                tv_food9 = item;
                                tv_qfood9 = amount;
                                tv_ufood9 = unit;
                                itv_ufood9 = aid;
                                break;
                        }

                        Bundle b1 = new Bundle();
                        b1.putString("member", tv_member);
                        b1.putString("amount", tv_amount);
                        b1.putString("f1", tv_food1);
                        b1.putString("qf1", tv_qfood1);
                        b1.putString("uf1", tv_ufood1);
                        b1.putString("idf1", itv_ufood1);
                        b1.putString("f2", tv_food2);
                        b1.putString("qf2", tv_qfood2);
                        b1.putString("uf2", tv_ufood2);
                        b1.putString("idf2", itv_ufood2);
                        b1.putString("f3", tv_food3);
                        b1.putString("qf3", tv_qfood3);
                        b1.putString("uf3", tv_ufood3);
                        b1.putString("idf3", itv_ufood3);
                        b1.putString("f4", tv_food4);
                        b1.putString("qf4", tv_qfood4);
                        b1.putString("uf4", tv_ufood4);
                        b1.putString("idf4", itv_ufood4);
                        b1.putString("f5", tv_food5);
                        b1.putString("qf5", tv_qfood5);
                        b1.putString("uf5", tv_ufood5);
                        b1.putString("idf5", itv_ufood5);
                        b1.putString("f6", tv_food6);
                        b1.putString("qf6", tv_qfood6);
                        b1.putString("uf6", tv_ufood6);
                        b1.putString("idf6", itv_ufood6);
                        b1.putString("f7", tv_food7);
                        b1.putString("qf7", tv_qfood7);
                        b1.putString("uf7", tv_ufood7);
                        b1.putString("idf7", itv_ufood7);
                        b1.putString("f8", tv_food8);
                        b1.putString("qf8", tv_qfood8);
                        b1.putString("uf8", tv_ufood8);
                        b1.putString("idf8", itv_ufood8);
                        b1.putString("f9", tv_food9);
                        b1.putString("qf9", tv_qfood9);
                        b1.putString("uf9", tv_ufood9);
                        b1.putString("idf9", itv_ufood9);
                        b1.putString("strbtn", strbtn);

                        Intent intent1 = new Intent();
                        intent1.setClass(DishFood.this, newdish.class);
                        intent1.putExtras(b1);
                        startActivity(intent1);
                        DishFood.this.finish();

                    }
                });
        alert.show();
    }

    private void deleteAlert(String title,String context)
    {
        android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(context);
        alert.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                    }
                });
        alert.setPositiveButton("確定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        long long1 = db.delete("icebox", " _id=" + foodid, null);
                        Bundle b1 = new Bundle();
                        b1.putString("member", tv_member);
                        b1.putString("amount", tv_amount);
                        b1.putString("f1", tv_food1);
                        b1.putString("qf1", tv_qfood1);
                        b1.putString("uf1", tv_ufood1);
                        b1.putString("idf1", itv_ufood1);
                        b1.putString("f2", tv_food2);
                        b1.putString("qf2", tv_qfood2);
                        b1.putString("uf2", tv_ufood2);
                        b1.putString("idf2", itv_ufood2);
                        b1.putString("f3", tv_food3);
                        b1.putString("qf3", tv_qfood3);
                        b1.putString("uf3", tv_ufood3);
                        b1.putString("idf3", itv_ufood3);
                        b1.putString("f4", tv_food4);
                        b1.putString("qf4", tv_qfood4);
                        b1.putString("uf4", tv_ufood4);
                        b1.putString("idf4", itv_ufood4);
                        b1.putString("f5", tv_food5);
                        b1.putString("qf5", tv_qfood5);
                        b1.putString("uf5", tv_ufood5);
                        b1.putString("idf5", itv_ufood5);
                        b1.putString("f6", tv_food6);
                        b1.putString("qf6", tv_qfood6);
                        b1.putString("uf6", tv_ufood6);
                        b1.putString("idf6", itv_ufood6);
                        b1.putString("f7", tv_food7);
                        b1.putString("qf7", tv_qfood7);
                        b1.putString("uf7", tv_ufood7);
                        b1.putString("idf7", itv_ufood7);
                        b1.putString("f8", tv_food8);
                        b1.putString("qf8", tv_qfood8);
                        b1.putString("uf8", tv_ufood8);
                        b1.putString("idf8", itv_ufood8);
                        b1.putString("f9", tv_food9);
                        b1.putString("qf9", tv_qfood9);
                        b1.putString("uf9", tv_ufood9);
                        b1.putString("idf9", itv_ufood9);
                        b1.putString("strbtn", strbtn);
                        Intent intent1 = new Intent();
                        intent1.setClass(DishFood.this, newdish.class);
                        intent1.putExtras(b1);
                        startActivity(intent1);
                        DishFood.this.finish();
                    }
                });
        alert.show();
    }

    private void reviseAlert(String title,String context)
    {
        android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(context);
        alert.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                    }
                });
        alert.setPositiveButton("確定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        float a = Float.parseFloat(amount);
                        float ma = Float.parseFloat(maxamount);
                        values.put(QUANTITY, (ma-a));
                        long long1 = db.update("icebox", values, "_ID=" + foodid, null);
                        Bundle b1 = new Bundle();
                        b1.putString("member", tv_member);
                        b1.putString("amount", tv_amount);
                        b1.putString("f1", tv_food1);
                        b1.putString("qf1", tv_qfood1);
                        b1.putString("uf1", tv_ufood1);
                        b1.putString("idf1",itv_ufood1);
                        b1.putString("f2", tv_food2);
                        b1.putString("qf2", tv_qfood2);
                        b1.putString("uf2", tv_ufood2);
                        b1.putString("idf2",itv_ufood2);
                        b1.putString("f3", tv_food3);
                        b1.putString("qf3", tv_qfood3);
                        b1.putString("uf3", tv_ufood3);
                        b1.putString("idf3",itv_ufood3);
                        b1.putString("f4", tv_food4);
                        b1.putString("qf4", tv_qfood4);
                        b1.putString("uf4", tv_ufood4);
                        b1.putString("idf4",itv_ufood4);
                        b1.putString("f5", tv_food5);
                        b1.putString("qf5", tv_qfood5);
                        b1.putString("uf5", tv_ufood5);
                        b1.putString("idf5",itv_ufood5);
                        b1.putString("f6", tv_food6);
                        b1.putString("qf6", tv_qfood6);
                        b1.putString("uf6", tv_ufood6);
                        b1.putString("idf6",itv_ufood6);
                        b1.putString("f7", tv_food7);
                        b1.putString("qf7", tv_qfood7);
                        b1.putString("uf7", tv_ufood7);
                        b1.putString("idf7",itv_ufood7);
                        b1.putString("f8", tv_food8);
                        b1.putString("qf8", tv_qfood8);
                        b1.putString("uf8", tv_ufood8);
                        b1.putString("idf8",itv_ufood8);
                        b1.putString("f9", tv_food9);
                        b1.putString("qf9", tv_qfood9);
                        b1.putString("uf9", tv_ufood9);
                        b1.putString("idf9",itv_ufood9);
                        b1.putString("strbtn", strbtn);
                        Intent intent1 = new Intent();
                        intent1.setClass(DishFood.this, newdish.class);
                        intent1.putExtras(b1);
                        startActivity(intent1);
                        DishFood.this.finish();
                    }
                });
        alert.show();
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

    // 點擊空白區域 自動隱藏鍵盤
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {

            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

}