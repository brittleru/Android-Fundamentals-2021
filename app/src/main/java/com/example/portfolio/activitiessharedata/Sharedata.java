package com.example.portfolio.activitiessharedata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.portfolio.R;

public class Sharedata extends AppCompatActivity {

    private String webUser;
    private String locUser;
    private String shrUser;
    private String dilUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedata);

        // Text IDs
        EditText webText = (EditText) findViewById(R.id.webText);
        EditText locationText = (EditText) findViewById(R.id.locText);
        EditText shareText = (EditText) findViewById(R.id.shrText);
        EditText dialText = (EditText) findViewById(R.id.dilText);

        // Button IDs
        Button webButton = (Button) findViewById(R.id.webButton);
        Button locationButton = (Button) findViewById(R.id.locButton);
        Button shareButton = (Button) findViewById(R.id.shrButton);
        Button dialButton = (Button) findViewById(R.id.dilButton);


        // Set text event listeners
        webText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                webUser = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        locationText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                locUser = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        shareText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                shrUser = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dialText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dilUser = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Set button click listeners
        webButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(webUser));
                startActivity(intent);
            }
        });
        locationButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String sanitizeString = "google.navigation:q=";
                sanitizeString += locUser;

                intent.setData(Uri.parse(sanitizeString));
                startActivity(intent);
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, shrUser);
                intent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(intent, null);
                startActivity(shareIntent);

            }
        });
        dialButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phoneNumber = "tel:";
                phoneNumber += dilUser;
                intent.setData(Uri.parse(phoneNumber));

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }


}