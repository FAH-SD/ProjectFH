package com.example.goldas.projectfh;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.google.zxing.integration.android.*;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class newfood2 extends Activity implements View.OnClickListener{
    private Activity mainactivity;
    private static EditText scan_content;
    private Button scan_btn;
    private Button btn_gov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfood2);
        final Intent intent = new Intent(this, traceAbility.class);


        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_iback);
        buttonback.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                newfood2.this.finish();
            }
        });

        init_view();
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(mainactivity);
                scanIntegrator.initiateScan();
            }
        });


        btn_gov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strInput = scan_content.getText().toString();
//                if("".equals(strInput)) {
//                    showAlert("錯誤資訊","尚未進行掃描");}
//
//                else if(strInput.contains("http://tqr.tw/?q=")){
//                    new NetworkTask().execute();
//                }else{
//                    scan_content.setText("");
//                    showAlert("錯誤資訊","該條碼不是產銷履歷碼");
//                }

                intent.putExtra("abc", scan_content.getText().toString());
                startActivity(intent);
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

    // 點擊空白區域 自動隱藏鍵盤
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){

            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }


    //    public static void Parsing() throws Exception {
//
//            String strInput = scan_content.getText().toString();
//            URL url = new URL(strInput);
//            Document xmlDoc = Jsoup.parse(url, 3000); //使用Jsoup jar 去解析網頁
//            //(要解析的文件,timeout)
//            Elements span = xmlDoc.select("span"); //要解析的tag元素為span
//            String number = span.text();
//            trace_number.setText(number); //得到span tag的內容
//
//    }
private void showAlert(String title,String context){

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

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanningResult!=null){
            String scanContent=scanningResult.getContents();
                if("".equals(scanContent)) {
                    showAlert("錯誤資訊","尚未進行掃描");}

                else if(scanContent.contains("http://tqr.tw/?q=")){
                    scan_content.setText(scanContent);
                }else{
                    scan_content.setText("");
                    showAlert("錯誤資訊","該條碼不是產銷履歷碼");
                }

        }else{
            Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
        }
    }


    private void init_view(){
        this.scan_content=(EditText)findViewById(R.id.editText3);
        this.mainactivity=this;
        this.scan_btn = (Button)findViewById(R.id.scan_btn);
        this.btn_gov = (Button)findViewById(R.id.btn_gov);
    }

//    class NetworkTask extends AsyncTask<String, Void, String>{
//        Document xmlDoc;
//        ProgressDialog dialog = new ProgressDialog(newfood2.this);
//        @Override
//        protected void onPreExecute(){
//            dialog.setTitle("讀取中...");
//            dialog.setMessage("正在讀取中...");
//            dialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            Log.d("newfood2","Parsing");
//            String strInput = scan_content.getText().toString();
//            Log.d("newfood2","strInput = " + strInput);
//            URL url = null;
//
//            try{
//                url = new URL(strInput);
//                xmlDoc = Jsoup.parse(url, 3000);
//            }catch(IOException e){
//                e.printStackTrace();
//                return null;
//            }
//
//            Elements span = xmlDoc.select("span");
//            String number = span.get(0).text();
//
//            return number;
//        }
//
//        @Override
//        protected void onPostExecute(String number){
//            dialog.cancel();
//            Log.d("newfood2", "before number = " + number);
//            number = number.replace("-(批次)","");
//            Log.d("newfood2","after number = "+number);
//            trace_number.setText(number);
//        }
//    }

}



