package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class traceAbility extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_ability);
        ImageButton buttonback = (ImageButton)findViewById(R.id.btn_iback);
        buttonback.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(traceAbility.this, icebox.class);
                startActivity(intent1);
                traceAbility.this.finish();
            }
        });

        Button buttonin1 = (Button)findViewById(R.id.btn_iin1);
        buttonin1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(traceAbility.this, newfood1.class);
                startActivity(intent2);
                traceAbility.this.finish();
            }
        });
    }}

