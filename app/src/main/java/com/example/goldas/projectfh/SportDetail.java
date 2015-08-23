package com.example.goldas.projectfh;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SportDetail extends Activity {
    EditText name, height, weight, BMI, sportname, sporttime, sprotcalories, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_detail);
        name = (EditText) findViewById(R.id.et_name);
        height = (EditText) findViewById(R.id.et_height);
        weight = (EditText) findViewById(R.id.et_weight);
        BMI = (EditText) findViewById(R.id.et_BMI);
        sportname = (EditText) findViewById(R.id.et_sportname);
        sporttime = (EditText) findViewById(R.id.et_sporttime);
        sprotcalories = (EditText) findViewById(R.id.et_sprotcalories);
        date = (EditText) findViewById(R.id.et_date);

        Bundle bundle4 = this.getIntent().getExtras();

        name.setText(bundle4.getString("name"));
        height.setText(bundle4.getString("height"));
        weight.setText(bundle4.getString("weight"));
        BMI.setText(bundle4.getString("BMI"));
        sportname.setText(bundle4.getString("sportname"));
        sporttime.setText(bundle4.getString("sporttime"));
        sprotcalories.setText(bundle4.getString("calories"));
        date.setText(bundle4.getString("date"));

        ImageButton buttonback = (ImageButton) findViewById(R.id.btn_back);
        buttonback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SportDetail.this.finish();
            }
        });
    }

}
