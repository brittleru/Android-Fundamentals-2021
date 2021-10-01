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

public class MessageSending extends AppCompatActivity {

    private String userMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Bundle extras = getIntent().getExtras();

        TextView msgStatus = (TextView) findViewById(R.id.messageStatus);
        TextView userMsg = (TextView) findViewById(R.id.message);


        if (extras != null) {
            String theMsg = (String) extras.getString("reply");
            msgStatus.setText(R.string.rplRec);
            userMsg.setText(theMsg);
        }



        EditText message = (EditText) findViewById(R.id.editText);
        Button sendMessage = (Button) findViewById(R.id.button);


        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userMessage = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsgActivity2(v);
            }
        });


    }


    protected void sendMsgActivity2(View v) {
        Intent intent = new Intent(MessageSending.this, Received.class);
        intent.putExtra("msg", userMessage);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}