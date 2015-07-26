package com.example.goldas.projectfh;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by roy on 2015/7/26.
 */
public class leftMenu extends Activity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_fragment);

        Button lefthome = (Button) findViewById(R.id.lefthome);
        lefthome.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                rightFragment fragment = new rightFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.right_layout, fragment);
                transaction.commit();
            }
        });

        Button leftfamily = (Button) findViewById(R.id.leftfamily);
        leftfamily.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                familyFragment fragment1 = new familyFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.right_layout, fragment1);
                transaction1.commit();
                fragment1.getActivity();

            }
        });

        Button lefticebox = (Button) findViewById(R.id.lefticebox);
        lefticebox.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                iceboxFragment fragment5 = new iceboxFragment();
                FragmentManager fragmentManager5 = getFragmentManager();
                FragmentTransaction transaction5 = fragmentManager5.beginTransaction();
                transaction5.replace(R.id.right_layout, fragment5);
                transaction5.commit();
                fragment5.getActivity();
            }
        });

        Button leftdish = (Button) findViewById(R.id.leftdish);
        leftdish.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dishFragment fragment2 = new dishFragment();
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
                transaction2.replace(R.id.right_layout, fragment2);
                transaction2.commit();
            }
        });

        Button leftsport = (Button) findViewById(R.id.leftsport);
        leftsport.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sportFragment fragment3 = new sportFragment();
                FragmentManager fragmentManager3 = getFragmentManager();
                FragmentTransaction transaction3 = fragmentManager3.beginTransaction();
                transaction3.replace(R.id.right_layout, fragment3);
                transaction3.commit();
            }
        });

        Button leftset = (Button) findViewById(R.id.leftset);
        leftset.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                setFragment fragment4 = new setFragment();
                FragmentManager fragmentManager4 = getFragmentManager();
                FragmentTransaction transaction4 = fragmentManager4.beginTransaction();
                transaction4.replace(R.id.right_layout, fragment4);
                transaction4.commit();
            }
        });
    }
}
