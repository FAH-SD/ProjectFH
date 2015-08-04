package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class foodDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        EditText kind = (EditText) findViewById(R.id.et_ikind);
        EditText name = (EditText) findViewById(R.id.et_iname);
        EditText amount = (EditText) findViewById(R.id.et_iamount);
        EditText unit = (EditText) findViewById(R.id.et_iunit);
        EditText buyday = (EditText) findViewById(R.id.et_ibuyday);
        EditText limitday = (EditText) findViewById(R.id.et_ilimitday);
        EditText place = (EditText) findViewById(R.id.et_iplace);
        EditText condition = (EditText) findViewById(R.id.et_icondition);

        Bundle bundle2=this.getIntent().getExtras();

        kind.setText(bundle2.getString("foodkind"));
        name.setText(bundle2.getString("foodname"));
        amount.setText(bundle2.getString("foodamount"));

        buyday.setText(bundle2.getString("foodbuyday"));
        limitday.setText(bundle2.getString("foodlimitday"));
        place.setText(bundle2.getString("foodstorage"));

        //定義時間格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if("".equals(buyday.getText().toString().trim())) {}
            else{
                try {
                    Date dt1 = sdf.parse(buyday.getText().toString());
                    Date dt2 =sdf.parse(limitday.getText().toString());
                    //取得兩個時間的Unix時間
                    Long ut1=dt1.getTime();
                    Long ut2=dt2.getTime();
                    //相減獲得兩個時間差距的毫秒
                    Long timeP=ut2-ut1;//毫秒差
                    long day=timeP/1000*60*60*24;//日差

                    Date dt=new Date();
                    String dts=sdf.format(dt);
                    Date dt3 = sdf.parse(dts);
                    Long ut3=dt3.getTime();
                    Long timeP2=ut2-ut3;//毫秒差
                    Long day2=timeP2/1000*60*60*24;//日差
                    if(day2<=1){
                        condition.setText("糟糕");
                    }if(day2<day*0.5){
                        condition.setText("普通");
                    }else{
                        condition.setText("良好");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }





        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_iback);
        buttonback.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(foodDetail.this, icebox.class);
                startActivity(intent1);
                foodDetail.this.finish();
            }
        });
    }

}
