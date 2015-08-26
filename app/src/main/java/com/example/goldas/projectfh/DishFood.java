package com.example.goldas.projectfh;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;

public class DishFood extends Activity {
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private SimpleCursorAdapter adapter = null;
    String strtype = "全部";
    ListView foodlist;
    String item, amount, unit;
    EditText et_choose;
    String strbtn;
    String tv_member, tv_amount;
    String tv_food1, tv_food2, tv_food3, tv_food4, tv_food5, tv_food6, tv_food7, tv_food8, tv_food9;
    String tv_qfood1, tv_qfood2, tv_qfood3, tv_qfood4, tv_qfood5, tv_qfood6, tv_qfood7, tv_qfood8, tv_qfood9;
    String tv_ufood1, tv_ufood2, tv_ufood3, tv_ufood4, tv_ufood5, tv_ufood6, tv_ufood7, tv_ufood8, tv_ufood9;
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

        db = (new DBhelper(getApplicationContext()).getWritableDatabase());
        cursor = getAll();
        UpdateAdapter(cursor);

        foodlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                try {
                    Cursor c2 = get(id);
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
            cursor = getItem(et_item.getText().toString());
            UpdateAdapter(cursor);
        }
    });



                ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
                btn_back.setOnClickListener(new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b1 = new Bundle();
                        b1.putString("member", tv_member);
                        b1.putString("amount", tv_amount);
                        b1.putString("f1", tv_food1);
                        b1.putString("qf1", tv_qfood1);
                        b1.putString("uf1", tv_ufood1);
                        b1.putString("f2", tv_food2);
                        b1.putString("qf2", tv_qfood2);
                        b1.putString("uf2", tv_ufood2);
                        b1.putString("f3", tv_food3);
                        b1.putString("qf3", tv_qfood3);
                        b1.putString("uf3", tv_ufood3);
                        b1.putString("f4", tv_food4);
                        b1.putString("qf4", tv_qfood4);
                        b1.putString("uf4", tv_ufood4);
                        b1.putString("f5", tv_food5);
                        b1.putString("qf5", tv_qfood5);
                        b1.putString("uf5", tv_ufood5);
                        b1.putString("f6", tv_food6);
                        b1.putString("qf6", tv_qfood6);
                        b1.putString("uf6", tv_ufood6);
                        b1.putString("f7", tv_food7);
                        b1.putString("qf7", tv_qfood7);
                        b1.putString("uf7", tv_ufood7);
                        b1.putString("f8", tv_food8);
                        b1.putString("qf8", tv_qfood8);
                        b1.putString("uf8", tv_ufood8);
                        b1.putString("f9", tv_food9);
                        b1.putString("qf9", tv_qfood9);
                        b1.putString("uf9", tv_ufood9);
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
                            showAlert2("錯誤資訊", "尚未輸入數量");
                        } else {
                            switch (strbtn) {
                                case "1":
                                    tv_food1 = item;
                                    tv_qfood1 = amount;
                                    tv_ufood1 = unit;
                                    break;
                                case "2":
                                    tv_food2 = item;
                                    tv_qfood2 = amount;
                                    tv_ufood2 = unit;
                                    break;
                                case "3":
                                    tv_food3 = item;
                                    tv_qfood3 = amount;
                                    tv_ufood3 = unit;
                                    break;
                                case "4":
                                    tv_food4 = item;
                                    tv_qfood4 = amount;
                                    tv_ufood4 = unit;
                                    break;
                                case "5":
                                    tv_food5 = item;
                                    tv_qfood5 = amount;
                                    tv_ufood5 = unit;
                                    break;
                                case "6":
                                    tv_food6 = item;
                                    tv_qfood6 = amount;
                                    tv_ufood6 = unit;
                                    break;
                                case "7":
                                    tv_food7 = item;
                                    tv_qfood7 = amount;
                                    tv_ufood7 = unit;
                                    break;
                                case "8":
                                    tv_food8 = item;
                                    tv_qfood8 = amount;
                                    tv_ufood8 = unit;
                                    break;
                                case "9":
                                    tv_food9 = item;
                                    tv_qfood9 = amount;
                                    tv_ufood9 = unit;
                                    break;
                            }
                            Bundle b1 = new Bundle();
                            b1.putString("member", tv_member);
                            b1.putString("amount", tv_amount);
                            b1.putString("f1", tv_food1);
                            b1.putString("qf1", tv_qfood1);
                            b1.putString("uf1", tv_ufood1);
                            b1.putString("f2", tv_food2);
                            b1.putString("qf2", tv_qfood2);
                            b1.putString("uf2", tv_ufood2);
                            b1.putString("f3", tv_food3);
                            b1.putString("qf3", tv_qfood3);
                            b1.putString("uf3", tv_ufood3);
                            b1.putString("f4", tv_food4);
                            b1.putString("qf4", tv_qfood4);
                            b1.putString("uf4", tv_ufood4);
                            b1.putString("f5", tv_food5);
                            b1.putString("qf5", tv_qfood5);
                            b1.putString("uf5", tv_ufood5);
                            b1.putString("f6", tv_food6);
                            b1.putString("qf6", tv_qfood6);
                            b1.putString("uf6", tv_ufood6);
                            b1.putString("f7", tv_food7);
                            b1.putString("qf7", tv_qfood7);
                            b1.putString("uf7", tv_ufood7);
                            b1.putString("f8", tv_food8);
                            b1.putString("qf8", tv_qfood8);
                            b1.putString("uf8", tv_ufood8);
                            b1.putString("f9", tv_food9);
                            b1.putString("qf9", tv_qfood9);
                            b1.putString("uf9", tv_ufood9);
                            b1.putString("strbtn", strbtn);
                            Intent intent1 = new Intent();
                            intent1.setClass(DishFood.this, newdish.class);
                            intent1.putExtras(b1);
                            startActivity(intent1);
                            DishFood.this.finish();
                        }

                    }
                });

            }

            public void UpdateAdapter(Cursor cursor) {
//當cursor有東西 並且數量 >=0
                if (cursor != null && cursor.getCount() >= 0) {
                    adapter = new SimpleCursorAdapter(this, R.layout.dishfoodlist, cursor, new String[]{"item"}, new int[]{R.id.txtitem});
                    foodlist = (ListView) findViewById(R.id.listView3);
                    foodlist.setAdapter(adapter);
                }
            }

            public Cursor get(long rowId) throws SQLException {
                Cursor c = db.rawQuery("SELECT _id, item from icebox WHERE _id=" + rowId + " ORDER BY item", null);
                if (c.getCount() > 0) {
                    c.moveToFirst();
                }

                return c;

            }

            public Cursor getAll() { // 查詢所有資料

                Cursor c = db.rawQuery("SELECT _id, item from icebox ORDER BY item", null);
                return c;
            }

            public Cursor getKind(String strkind) { // 查詢Kind
                Cursor c = db.rawQuery("SELECT _id, item, kind FROM icebox WHERE kind LIKE '%" + strkind + "%'  ORDER BY item", null);
                return c;
            }


            public Cursor getItem(String stritem) { // 查詢Item
                Cursor c = db.rawQuery("SELECT _id, item FROM icebox WHERE item LIKE '%" + stritem + "%'  ORDER BY item", null);
                return c;
            }


            private void showAlert() {
                LayoutInflater inflater = LayoutInflater.from(DishFood.this);
                final View view1 = inflater.inflate(R.layout.amountview, null);

                final EditText editText = (EditText) view1.findViewById(R.id.edittext);
                final Spinner sp_unit = (Spinner) view1.findViewById(R.id.sp_unit);
                final Spinner sp_unit2 = (Spinner) view1.findViewById(R.id.sp_unit2);
                if (item.contains("脂鮮乳") || item.contains("養樂多") || item.contains("優酪乳") || (item.contains("牛油") || item.contains("豬油") || item.contains("雞油") || item.contains("花生油") || item.contains("葵花油") || item.contains("玉米油") || item.contains("沙拉油") || item.contains("橄欖油") || item.contains("辣椒油") || item.contains("椰子油") | item.contains("芝麻油"))) {
                    sp_unit.setVisibility(View.GONE);
                    sp_unit2.setVisibility(View.VISIBLE);
                } else {
                    sp_unit2.setVisibility(View.GONE);
                    sp_unit.setVisibility(View.VISIBLE);
                }
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
                        if (sp_unit.getVisibility() == View.VISIBLE) {
                            unit = sp_unit.getSelectedItem().toString();
                        } else {
                            unit = sp_unit2.getSelectedItem().toString();
                        }
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

                sp_unit.setOnItemSelectedListener(new OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        if (sp_unit.getVisibility() == View.VISIBLE) {
                            unit = sp_unit.getSelectedItem().toString();
                        } else {
                            unit = sp_unit2.getSelectedItem().toString();
                        }

                        if (editText.getText().toString() != ""
                                && editText.getText().toString() != null) {
                            amount = editText.getText().toString();
                        } else {
                            amount = "";
                        }

                        String text = item + ", " + amount + unit;
                        et_choose.setText(text);

                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub

                    }
                });

                sp_unit2.setOnItemSelectedListener(new OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        if (sp_unit.getVisibility() == View.VISIBLE) {
                            unit = sp_unit.getSelectedItem().toString();
                        } else {
                            unit = sp_unit2.getSelectedItem().toString();
                        }

                        if (editText.getText().toString() != ""
                                && editText.getText().toString() != null) {
                            amount = editText.getText().toString();
                        } else {
                            amount = "";
                        }

                        String text = item + ", " + amount + unit;
                        et_choose.setText(text);

                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub

                    }
                });

                new AlertDialog.Builder(DishFood.this).setTitle("選取" + item)
                        .setMessage("請輸入數量").setView(view1)
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

            // 點擊空白區域 自動隱藏鍵盤
            public boolean onTouchEvent(MotionEvent event) {
                if (null != this.getCurrentFocus()) {

                    InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                }
                return super.onTouchEvent(event);
            }

        }