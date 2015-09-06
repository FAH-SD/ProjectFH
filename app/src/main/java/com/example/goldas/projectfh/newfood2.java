package com.example.goldas.projectfh;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.google.zxing.integration.android.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class newfood2 extends Activity {
    private Activity mainactivity;
    private static EditText scan_content;
    private static EditText trace_number;
    private Button scan_btn;
    private Button btn_gov;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfood2);
        final Intent i = new Intent(this, traceAbility.class);

        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_iback);
        buttonback.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
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
                if("".equals(strInput)) {
                    showAlert("錯誤資訊","尚未進行掃描");}
//                }else{
//                    Intent intentS = new Intent(Intent.ACTION_VIEW);
//                    intentS.setData(Uri.parse(strInput));
//                    startActivity(intentS);
//                }
                else if(strInput.contains("http://tqr.tw/?q=")){
                    new NetworkTask().execute();
                }else{
                    scan_content.setText("");
                    showAlert("錯誤資訊","該條碼不是產銷履歷碼");
                }
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("newfood2", "ok click");
                if(isOnline()) {
                    Log.d("newfood2", "have network");
                    String strInput = scan_content.getText().toString();
                    if ("".equals(trace_number.getText().toString().trim())) {
                        showAlert("錯誤資訊", "產銷履歷碼尚未輸入");
                    } else {
                        String strtn = trace_number.getText().toString();
//                    if (strtn.length() != 11 || strtn.length() != 16){
//                        showAlert("錯誤資訊", "產銷履歷碼輸入錯誤");
//                    }else {
                        String url = "http://tqr.tw/cn/cp.aspx?t=" + trace_number.getText().toString();
                        Bundle bundle = new Bundle();
                        bundle.putString("url", url);
                        bundle.putString("tracecode", trace_number.getText().toString());
                        i.putExtras(bundle);
                        startActivity(i);
                        newfood2.this.finish();
//                    }
                    }
                }
                else{
                    showAlert("錯誤資訊","尚未連線至網路");
                }
            }
        });
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
            scan_content.setText(scanContent);
        }else{
            Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
        }
    }


    private void init_view(){
        this.scan_content=(EditText)findViewById(R.id.editText3);
        this.trace_number=(EditText)findViewById(R.id.editText2);
        this.mainactivity=this;
        this.scan_btn = (Button)findViewById(R.id.scan_btn);
        this.btn_gov = (Button)findViewById(R.id.btn_gov);
        this.btn_ok = (Button)findViewById(R.id.btn_ok);
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

    class NetworkTask extends AsyncTask<String, Void, String>{
        Document xmlDoc;
        ProgressDialog dialog = new ProgressDialog(newfood2.this);
        @Override
        protected void onPreExecute(){
            dialog.setTitle("讀取中...");
            dialog.setMessage("正在讀取中...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("newfood2","Parsing");
            String strInput = scan_content.getText().toString();
            Log.d("newfood2","strInput = " + strInput);
            URL url = null;

            try{
                url = new URL(strInput);
                xmlDoc = Jsoup.parse(url, 3000);
            }catch(IOException e){
                e.printStackTrace();
                return null;
            }

            Elements span = xmlDoc.select("span");
            String number = span.get(0).text();

            return number;
        }

        @Override
        protected void onPostExecute(String number){
            dialog.cancel();
            Log.d("newfood2", "before number = " + number);
            number = number.replace("-(批次)","");
            Log.d("newfood2","after number = "+number);
            trace_number.setText(number);
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

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}




