package com.recyclerush.group5.recyclerush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by emillundgren on 2018-04-12.
 */

public class SecondActivity extends Activity{
    TextView text1;
    TextView text2;
    TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        text1=(TextView) findViewById(R.id.textView);
        text2=(TextView) findViewById(R.id.textView2);
        text3=(TextView) findViewById(R.id.textView3);

        text1.setText(getIntent().getExtras().getString("name"));
        text2.setText(getIntent().getExtras().getString("materials"));
        Toast.makeText(getApplicationContext(), getIntent().getExtras().getString("recyc"), Toast.LENGTH_LONG).show();


        text3.setText("Material:");

    }
}