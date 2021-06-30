package com.example.new_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class contactus extends AppCompatActivity
{
    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        btn=findViewById(R.id.imb);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(contactus.this, "PLEASE CHECK DETAIL ON PAGE 1", Toast.LENGTH_SHORT).show();
                geturl("https://iiita.ac.in/uploads/Fee%20Structure_Jul_2018_ExistingStudents271.pdf");
            }
        });
    }
    public void geturl(String s)
    {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}