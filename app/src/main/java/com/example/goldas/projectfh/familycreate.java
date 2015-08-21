package com.example.goldas.projectfh;

        import static android.provider.BaseColumns._ID;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY1;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY10;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY2;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY3;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY4;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY5;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY6;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY7;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY8;
        import static com.example.goldas.projectfh.FDBconstant.ALLERGY9;
        import static com.example.goldas.projectfh.FDBconstant.FTABLE_NAME;
        import static com.example.goldas.projectfh.FDBconstant.HABIT;
        import static com.example.goldas.projectfh.FDBconstant.NAME;
        import static com.example.goldas.projectfh.FDBconstant.WEIGHT;
        import static com.example.goldas.projectfh.FDBconstant.HEIGHT;
        import static com.example.goldas.projectfh.FDBconstant.BIRTHYEAR;
        import static com.example.goldas.projectfh.FDBconstant.SEX;
        import android.app.Activity;
        import android.content.ContentValues;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.view.View.OnClickListener;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.RadioButton;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

        import java.lang.reflect.Method;


public class familycreate extends Activity  implements View.OnClickListener {
    static String tablename = "family";
    private EditText name, height, weight, birthyear;
    private Spinner sex, habit;
    private ImageButton submit, reset, back;
    private CheckBox egg, fish, peanut, nut, shellfish, beans, milk, chicken, pork, beef;
    SQLiteDatabase dbrw;
    DBhelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familyadd);

        dbhelper = new DBhelper(this);
        dbrw = dbhelper.getWritableDatabase();

        goToinit();

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


        ImageView lefthome = (ImageView) findViewById(R.id.lefthome);
        lefthome.setOnClickListener(this);

        ImageView leftfamily = (ImageView) findViewById(R.id.leftfamily);
        leftfamily.setOnClickListener(this);

        ImageView lefticebox = (ImageView) findViewById(R.id.lefticebox);
        lefticebox.setOnClickListener(this);

        ImageView leftdish = (ImageView) findViewById(R.id.leftdish);
        leftdish.setOnClickListener(this);

        ImageView leftsport = (ImageView) findViewById(R.id.leftsport);
        leftsport.setOnClickListener(this);

        ImageView leftset = (ImageView) findViewById(R.id.leftset);
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



    public void goToinit(){


        findViews();
        setListeners();

    }

    //關閉資料庫
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        dbrw.close();

    }

    private void findViews(){
        name = (EditText)findViewById(R.id.et_fname);
        height = (EditText)findViewById(R.id.et_fheight);
        weight = (EditText)findViewById(R.id.et_fweight);
        sex =(Spinner)findViewById(R.id.sp_fgender);
        birthyear = (EditText)findViewById(R.id.et_fyear);
        submit = (ImageButton)findViewById(R.id.btn_itrue);
        reset = (ImageButton)findViewById(R.id.btn_idelete);
        back = (ImageButton)findViewById(R.id.btn_iback);
        habit =(Spinner)findViewById(R.id.sp_fhabit);

        egg = (CheckBox)findViewById(R.id.egg);
        fish = (CheckBox)findViewById(R.id.fish);
        peanut = (CheckBox)findViewById(R.id. peanut);
        nut = (CheckBox)findViewById(R.id.nut);
        shellfish = (CheckBox)findViewById(R.id.shellfish);
        beans = (CheckBox)findViewById(R.id.beans);
        milk = (CheckBox)findViewById(R.id.milk);
        chicken = (CheckBox)findViewById(R.id.chicken);
        pork = (CheckBox)findViewById(R.id.pork);
        beef = (CheckBox)findViewById(R.id.beef);
    }
    private void setListeners(){
        submit.setOnClickListener(new submit_ButtonClickListener());
        reset.setOnClickListener(new reset_ButtonClickListener());

        back.setOnClickListener(new back_ButtonClickListener());
    }


    String a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
    class submit_ButtonClickListener implements OnClickListener{
        public void onClick(View v) {

            if("".equals(name.getText().toString().trim())){
                showAlert("錯誤資訊", "姓名尚未輸入");
            }

            try {
                int h = Integer.parseInt(height.getText().toString());
                if (h > 200 || h < 50) {
                    showAlert("錯誤資訊", "身高只能介於50~200公分之間");
                    height.setText("");
                }
            } catch (NumberFormatException e1) {
                showAlert("錯誤資訊", "身高尚未輸入");
            }

            try {
                int w = Integer.parseInt(weight.getText().toString());
                if (w > 200 || w < 10) {
                    showAlert("錯誤資訊", "體重只能介於10~200公斤之間");
                    weight.setText("");
                }
            } catch (NumberFormatException e2) {
                showAlert("錯誤資訊", "體重尚未輸入");
            }

            try {
                int y = Integer.parseInt(birthyear.getText().toString());
                if (y < 1901 || y > 2015) {
                    showAlert("錯誤資訊", "出生年只能介於1901~2015年之間");
                    birthyear.setText("");
                }
            } catch (NumberFormatException e3) {
                showAlert("錯誤資訊", "出生年尚未輸入");
            }
            if(egg.isChecked()){
                a1 = "1";
            }else{
                a1 = "0";
            }
            if(fish.isChecked()){
                a2 = "1";
            }else{
                a2 = "0";
            }
            if(peanut.isChecked()){
                a3 = "1";
            }else{
                a3 = "0";
            }
            if(nut.isChecked()){
                a4 = "1";
            }else{
                a4 = "0";
            }
            if(shellfish.isChecked()){
                a5 = "1";
            }else{
                a5 = "0";
            }
            if(beans.isChecked()){
                a6 = "1";
            }else{
                a6 = "0";
            }
            if(milk.isChecked()){
                a7 = "1";
            }else{
                a7 = "0";
            }
            if(chicken.isChecked()){
                a8 = "1";
            }else{
                a8 = "0";
            }
            if(pork.isChecked()){
                a9 = "1";
            }else{
                a9 = "0";
            }
            if(beef.isChecked()){
                a10 = "1";
            }else{
                a10 = "0";
            }
            if ("".equals(height.getText().toString().trim()) ||  "".equals(weight.getText().toString().trim()) || "".equals(birthyear.getText().toString().trim()) || "".equals(name.getText().toString().trim())) {
            }else{
                familyAlert("提醒視窗","是否確定新增此筆資料？");
            }
        }


    };


    class back_ButtonClickListener implements OnClickListener{
        public void onClick(View v){
        Intent i = new Intent(familycreate.this,family.class);
        startActivity(i);
        finish();
    }
    };
    class reset_ButtonClickListener implements OnClickListener{
        public void onClick(View v){
            clearedit();
            Toast.makeText(v.getContext(), "已重置目前輸入資料", Toast.LENGTH_LONG).show();
        }
    }
    private void clearedit(){
        name.setText("");
        weight.setText("");
        height.setText("");
        birthyear.setText("");

    }


    // 點擊空白區域 自動隱藏鍵盤
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){

            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }


    private void showAlert(String title,String context)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
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
    private void familyAlert(String title,String context)
    {
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
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(NAME, name.getText().toString());
                        values.put(SEX, sex.getSelectedItem().toString());
                        values.put(WEIGHT, weight.getText().toString());
                        values.put(HEIGHT, height.getText().toString());
                        values.put(BIRTHYEAR, birthyear.getText().toString());
                        values.put(ALLERGY1, a1);
                        values.put(ALLERGY2, a2);
                        values.put(ALLERGY3, a3);
                        values.put(ALLERGY4, a4);
                        values.put(ALLERGY5, a5);
                        values.put(ALLERGY6, a6);
                        values.put(ALLERGY7, a7);
                        values.put(ALLERGY8, a8);
                        values.put(ALLERGY9, a9);
                        values.put(ALLERGY10, a10);
                        values.put(HABIT, habit.getSelectedItem().toString());
                        db.insert(FTABLE_NAME, null, values);
//                        切換頁面
//                        Toast.makeText(v.getContext(), "已新增輸入家庭資料", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(familycreate.this, family.class);
                        startActivity(i);
                        finish();
                    }
                });
        alert.show();
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




