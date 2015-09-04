package com.example.goldas.projectfh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class traceAbility extends Activity {
    private TextView T1;
    private TextView T2;
    private TextView T3;
    private TextView T4;
    private TextView T5;
    private TextView T6;
    private TextView T7;
    private TextView T8;
    private TextView T9;
    private TextView T10;
    String url = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_ability);
        init_view();
        Bundle bundle = this.getIntent().getExtras();
        url = bundle.getString("abc");
        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_iback);
        buttonback.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(traceAbility.this, newfood2.class);
                startActivity(intent1);
                traceAbility.this.finish();
            }
        });

        Button btn_foodadd = (Button)findViewById(R.id.btn_foodadd);
        btn_foodadd.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                showAlert("確認視窗","是否確定新增此筆資料？");
            }
        });

        new NetworkTask().execute();
    }
    private void init_view(){
        this.T1=(TextView)findViewById(R.id.textView2);
        this.T2=(TextView)findViewById(R.id.textView3);
        this.T3=(TextView)findViewById(R.id.textView5);
        this.T4=(TextView)findViewById(R.id.textView7);
        this.T5=(TextView)findViewById(R.id.textView8);
        this.T6=(TextView)findViewById(R.id.textView9);
        this.T7=(TextView)findViewById(R.id.textView10);
        this.T8=(TextView)findViewById(R.id.textView11);
        this.T9=(TextView)findViewById(R.id.textView12);
        this.T10=(TextView)findViewById(R.id.textView13);
    }
    class NetworkTask extends AsyncTask<Void, Void, Void> {
        String TT1;
        String TT2;
        String TT3;
        String TT4;
        String TT5;
        String TT6;
        String TT7;
        String TT8;
        String TT10;
        String TT9;
        ProgressDialog dialog = new ProgressDialog(traceAbility.this);

        @Override
        protected void onPreExecute() {
            dialog.setTitle("讀取中...");
            dialog.setMessage("正在讀取中...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
//            Log.d("newfood2", "Parsing");
//            String strInput = scan_content.getText().toString();
//            Log.d("newfood2","strInput = " + strInput);


            try {

                Document doc = Jsoup.connect(url).get();
                Elements span = doc.select("span");
                Elements td = doc.select("td");
                TT1 = span.get(1).text();
                TT2 = td.get(2).text();
                TT3 = td.get(3).text();
                TT4 = td.get(4).text();
                TT5 = td.get(5).text();
                TT6 = td.get(6).text();
                TT7 = td.get(7).text();
                TT8 = td.get(8).text();
                TT9 = td.get(9).text();
                TT10 = td.get(10).text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void whatever) {
            dialog.cancel();
            TT1 = TT1.replace("-(批次)","");
            T1.setText(TT1);
            T2.setText(TT2);
            T3.setText(TT3);
            T4.setText(TT4);
            T5.setText(TT5);
            T6.setText(TT6);
            T7.setText(TT7);
            T8.setText(TT8);
            T9.setText(TT9);
            T10.setText(TT10);
        }
    }

    private void showAlert(String title,String context)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(context);
        alert.setPositiveButton("確定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                        Intent intent2 = new Intent();
                        intent2.setClass(traceAbility.this, NewTracecode.class);
                        intent2.putExtra("foodname", T5.getText().toString());
                        startActivity(intent2);
                        traceAbility.this.finish();
                    }
                });
        alert.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //按下按鈕後執行的動作，沒寫則退出Dialog
                    }
                });
        alert.show();
    }
}

