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

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView createView = (TextView) findViewById(R.id.createId1);
        String textSet = "onCreate(): called";
        createView.setText(textSet);
        createView.setTextColor(this.getResources().getColor(R.color.green));

        Button button = (Button) findViewById(R.id.buttonAct1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackMainActivity();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart()");
        TextView startView = (TextView) findViewById(R.id.startId1);
        String textSet = "onStart(): called";
        startView.setText(textSet);
        startView.setTextColor(this.getResources().getColor(R.color.green));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume()");
        TextView resumeView = (TextView) findViewById(R.id.resumeId1);
        String textSet = "onResume(): called";
        resumeView.setText(textSet);
        resumeView.setTextColor(this.getResources().getColor(R.color.green));
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause()");
        TextView pauseView = (TextView) findViewById(R.id.pauseId1);
        String textSet = "onPause(): called";
        pauseView.setText(textSet);
        pauseView.setTextColor(this.getResources().getColor(R.color.green));
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop()");
        TextView stopView = (TextView) findViewById(R.id.stopId1);
        String textSet = "onStop(): called";
        stopView.setText(textSet);
        stopView.setTextColor(this.getResources().getColor(R.color.green));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy()");
        TextView destroyView = (TextView) findViewById(R.id.destroyId1);
        String textSet = "onDestroy(): called";
        destroyView.setText(textSet);
        destroyView.setTextColor(this.getResources().getColor(R.color.green));
    }


    public void goBackMainActivity() {
        Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
        startActivity(intent);
    }
}