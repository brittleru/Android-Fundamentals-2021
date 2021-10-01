package com.example.portfolio.activitiespassage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.portfolio.R;

public class PassageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage);

        Button button1 = (Button) findViewById(R.id.secret1);
        Button button2 = (Button) findViewById(R.id.secret2);
        Button button3 = (Button) findViewById(R.id.secret3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSecretActivity(v);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSecretActivity(v);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSecretActivity(v);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    protected void goSecretActivity(View v) {

        Intent intent;
        intent = new Intent(PassageActivity.this, SecretActivity.class);

        switch (v.getId()) {
            case R.id.secret1:
                intent.putExtra("Button", "1");
                startActivity(intent);
                break;

            case R.id.secret2:
                intent.putExtra("Button", "2");
                startActivity(intent);
                break;

            case R.id.secret3:
                intent.putExtra("Button", "3");
                startActivity(intent);
                break;

            default:
                break;
        }
    }

}


