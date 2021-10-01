package com.example.portfolio.activitieschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.portfolio.R;

public class Received extends AppCompatActivity {

    private String replayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received);
        Bundle extras = getIntent().getExtras();

        TextView msgStatus = (TextView) findViewById(R.id.messageStatus1);
        TextView userMsg = (TextView) findViewById(R.id.message1);


        if (extras != null) {
            String theMsg = (String) extras.getString("msg");
            msgStatus.setText(R.string.msgRec);
            userMsg.setText(theMsg);
        }


        EditText message = (EditText) findViewById(R.id.editText1);
        Button sendMessage = (Button) findViewById(R.id.buttonRec);

        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                replayText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replayActivitiMain(v);
            }
        });
    }

    protected void replayActivitiMain(View v) {
        Intent intent = new Intent(Received.this, MessageSending.class);
        intent.putExtra("reply", replayText);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}