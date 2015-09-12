package com.tku.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class SportList extends Activity {
    TextView sport_c0, sport_c1, sport_c2, sport_c3,sport_c4, sport_c5,sport_c6,sport_c7, sport_c8, sport_c9, sport_c10,sport_c11, sport_c12,sport_c13,sport_c14, sport_c15, sport_c16, sport_c17,sport_c18, sport_c19,sport_c20;
    TextView sport_hr0,sport_hr1,sport_hr2,sport_hr3,sport_hr4,sport_hr5,sport_hr6,sport_hr7,sport_hr8,sport_hr9,sport_hr10,sport_hr11,sport_hr12,sport_hr13,sport_hr14,sport_hr15,sport_hr16,sport_hr17,sport_hr18,sport_hr19,sport_hr20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_list);

        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SportList.this.finish();
            }
        });
        sport_c0 = (TextView)findViewById(R.id.sport_c0);
        sport_c1 = (TextView)findViewById(R.id.sport_c1);
        sport_c2 = (TextView)findViewById(R.id.sport_c2);
        sport_c3 = (TextView)findViewById(R.id.sport_c3);
        sport_c4 = (TextView)findViewById(R.id.sport_c4);
        sport_c5 = (TextView)findViewById(R.id.sport_c5);
        sport_c6 = (TextView)findViewById(R.id.sport_c6);
        sport_c7 = (TextView)findViewById(R.id.sport_c7);
        sport_c8 = (TextView)findViewById(R.id.sport_c8);
        sport_c9 = (TextView)findViewById(R.id.sport_c9);
        sport_c10 = (TextView)findViewById(R.id.sport_c10);
        sport_c11 = (TextView)findViewById(R.id.sport_c11);
        sport_c12 = (TextView)findViewById(R.id.sport_c12);
        sport_c13 = (TextView)findViewById(R.id.sport_c13);
        sport_c14 = (TextView)findViewById(R.id.sport_c14);
        sport_c15 = (TextView)findViewById(R.id.sport_c15);
        sport_c16 = (TextView)findViewById(R.id.sport_c16);
        sport_c17 = (TextView)findViewById(R.id.sport_c17);
        sport_c18 = (TextView)findViewById(R.id.sport_c18);
        sport_c19 = (TextView)findViewById(R.id.sport_c19);
        sport_c20 = (TextView)findViewById(R.id.sport_c20);

        sport_hr0 = (TextView)findViewById(R.id.sport_hr0);
        sport_hr1 = (TextView)findViewById(R.id.sport_hr1);
        sport_hr2 = (TextView)findViewById(R.id.sport_hr2);
        sport_hr3 = (TextView)findViewById(R.id.sport_hr3);
        sport_hr4 = (TextView)findViewById(R.id.sport_hr4);
        sport_hr5 = (TextView)findViewById(R.id.sport_hr5);
        sport_hr6 = (TextView)findViewById(R.id.sport_hr6);
        sport_hr7 = (TextView)findViewById(R.id.sport_hr7);
        sport_hr8 = (TextView)findViewById(R.id.sport_hr8);
        sport_hr9 = (TextView)findViewById(R.id.sport_hr9);
        sport_hr10 = (TextView)findViewById(R.id.sport_hr10);
        sport_hr11 = (TextView)findViewById(R.id.sport_hr11);
        sport_hr12 = (TextView)findViewById(R.id.sport_hr12);
        sport_hr13 = (TextView)findViewById(R.id.sport_hr13);
        sport_hr14 = (TextView)findViewById(R.id.sport_hr14);
        sport_hr15 = (TextView)findViewById(R.id.sport_hr15);
        sport_hr16 = (TextView)findViewById(R.id.sport_hr16);
        sport_hr17 = (TextView)findViewById(R.id.sport_hr17);
        sport_hr18 = (TextView)findViewById(R.id.sport_hr18);
        sport_hr19 = (TextView)findViewById(R.id.sport_hr19);
        sport_hr20 = (TextView)findViewById(R.id.sport_hr20);

        Bundle bundle2 = this.getIntent().getExtras();

        String strkcal = bundle2.getString("kcal");
        Float kcal = Float.parseFloat(strkcal);
        String strw = bundle2.getString("weight");
        Float weight = Float.parseFloat(strw);

        float t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20;
        t0 = Float.parseFloat(sport_c0.getText().toString());
        t1 = Float.parseFloat(sport_c1.getText().toString());
        t2 = Float.parseFloat(sport_c2.getText().toString());
        t3 = Float.parseFloat(sport_c3.getText().toString());
        t4 = Float.parseFloat(sport_c4.getText().toString());
        t5 = Float.parseFloat(sport_c5.getText().toString());
        t6 = Float.parseFloat(sport_c6.getText().toString());
        t7 = Float.parseFloat(sport_c7.getText().toString());
        t8 = Float.parseFloat(sport_c8.getText().toString());
        t9 = Float.parseFloat(sport_c9.getText().toString());
        t10 = Float.parseFloat(sport_c10.getText().toString());
        t11 = Float.parseFloat(sport_c11.getText().toString());
        t12 = Float.parseFloat(sport_c12.getText().toString());
        t13 = Float.parseFloat(sport_c13.getText().toString());
        t14 = Float.parseFloat(sport_c14.getText().toString());
        t15 = Float.parseFloat(sport_c15.getText().toString());
        t16 = Float.parseFloat(sport_c16.getText().toString());
        t17 = Float.parseFloat(sport_c17.getText().toString());
        t18 = Float.parseFloat(sport_c18.getText().toString());
        t19 = Float.parseFloat(sport_c19.getText().toString());
        t20 = Float.parseFloat(sport_c20.getText().toString());

        float a0,a1, a2, a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20;
        a0 = kcal/weight/t0;
        a1 = kcal/weight/t1;
        a2 = kcal/weight/t2;
        a3 = kcal/weight/t3;
        a4 = kcal/weight/t4;
        a5 = kcal/weight/t5;
        a6 = kcal/weight/t6;
        a7 = kcal/weight/t7;
        a8 = kcal/weight/t8;
        a9 = kcal/weight/t9;
        a10 = kcal/weight/t10;
        a11 = kcal/weight/t11;
        a12 = kcal/weight/t12;
        a13 = kcal/weight/t13;;
        a14 = kcal/weight/t14;
        a15 = kcal/weight/t15;
        a16 = kcal/weight/t16;
        a17 = kcal/weight/t17;
        a18 = kcal/weight/t18;
        a19 = kcal/weight/t19;
        a20 = kcal/weight/t20;

        sport_hr0.setText(String.format("%.2f", a0));
        sport_hr1.setText(String.format("%.2f", a1));
        sport_hr2.setText(String.format("%.2f", a2));
        sport_hr3.setText(String.format("%.2f", a3));
        sport_hr4.setText(String.format("%.2f", a4));
        sport_hr5.setText(String.format("%.2f", a5));
        sport_hr6.setText(String.format("%.2f", a6));
        sport_hr7.setText(String.format("%.2f", a7));
        sport_hr8.setText(String.format("%.2f", a8));
        sport_hr9.setText(String.format("%.2f", a9));
        sport_hr10.setText(String.format("%.2f", a10));
        sport_hr11.setText(String.format("%.2f", a11));
        sport_hr12.setText(String.format("%.2f", a12));
        sport_hr13.setText(String.format("%.2f", a13));
        sport_hr14.setText(String.format("%.2f", a14));
        sport_hr15.setText(String.format("%.2f", a15));
        sport_hr16.setText(String.format("%.2f", a16));
        sport_hr17.setText(String.format("%.2f", a17));
        sport_hr18.setText(String.format("%.2f", a18));
        sport_hr19.setText(String.format("%.2f", a19));
        sport_hr20.setText(String.format("%.2f", a20));


    }

}
