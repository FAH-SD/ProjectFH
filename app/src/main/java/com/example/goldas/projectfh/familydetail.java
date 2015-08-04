package com.example.goldas.projectfh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by roy on 2015/8/1.
 */
public class familydetail extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_detail);

        EditText name = (EditText) findViewById(R.id.et_fname);
        EditText sex = (EditText) findViewById(R.id.et_fsex);
        EditText height = (EditText) findViewById(R.id.et_fheight);
        EditText weight = (EditText) findViewById(R.id.et_fweight);
        EditText year = (EditText) findViewById(R.id.et_fyear);

        Bundle bundle2=this.getIntent().getExtras();

        name.setText(bundle2.getString("membername"));
    }
}
