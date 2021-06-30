package com.example.new_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pg1 extends AppCompatActivity {
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg1);
        btn1=findViewById(R.id.b1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(pg1.this,login2.class);
                startActivity(intent);
            }
        });
        btn2=findViewById(R.id.b2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                gotourl("https://docs.google.com/forms/d/1T_OZq1Rxpuuvo-p7q2KpJJcsC7D-2lnD1xHqi-o5Dwk/edit");
            }
        });
    }
    private void gotourl(String s)
    {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}