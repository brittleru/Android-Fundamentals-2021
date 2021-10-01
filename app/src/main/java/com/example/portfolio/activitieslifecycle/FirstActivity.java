package com.example.portfolio.activitieslifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.portfolio.MainActivity;
import com.example.portfolio.R;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = FirstActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Log.d(TAG, "onCreate(), savedInstanceState= " + savedInstanceState);
        TextView createView = (TextView) findViewById(R.id.createId);
        String textSet = "onCreate(): called";
        createView.setText(textSet);
        createView.setTextColor(this.getResources().getColor(R.color.green));

        Button button = (Button) findViewById(R.id.buttonAct);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart()");
        TextView startView = (TextView) findViewById(R.id.startId);
        String textSet = "onStart(): called";
        startView.setText(textSet);
        startView.setTextColor(this.getResources().getColor(R.color.green));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume()");
        TextView resumeView = (TextView) findViewById(R.id.resumeId);
        String textSet = "onResume(): called";
        resumeView.setText(textSet);
        resumeView.setTextColor(this.getResources().getColor(R.color.green));
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause()");
        TextView pauseView = (TextView) findViewById(R.id.pauseId);
        String textSet = "onPause(): called";
        pauseView.setText(textSet);
        pauseView.setTextColor(this.getResources().getColor(R.color.green));
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop()");
        TextView stopView = (TextView) findViewById(R.id.stopId);
        String textSet = "onStop(): called";
        stopView.setText(textSet);
        stopView.setTextColor(this.getResources().getColor(R.color.green));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy()");
        TextView destroyView = (TextView) findViewById(R.id.destroyId);
        String textSet = "onDestroy(): called";
        destroyView.setText(textSet);
        destroyView.setTextColor(this.getResources().getColor(R.color.green));
    }


    public void openActivity2() {
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        startActivity(intent);
    }



}