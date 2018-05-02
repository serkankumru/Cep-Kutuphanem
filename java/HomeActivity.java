package com.example.taner.cep_kutuphanem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button mButton1;
    Button mButton2;
    Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mButton1=(Button)findViewById(R.id.btn1);
        mButton2=(Button)findViewById(R.id.btn2);
        mButton3=(Button)findViewById(R.id.btn3);



        mButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gecisYap = new Intent(HomeActivity.this, List1Activity.class);
                startActivity(gecisYap);
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gecisYap = new Intent(HomeActivity.this, List2Activity.class);
                startActivity(gecisYap);
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gecisYap = new Intent(HomeActivity.this, QRActivity.class);
                startActivity(gecisYap);
            }
        });

    }
}
