package com.example.portfolio.activitiespassage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portfolio.R;

public class SecretActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

        Bundle extras = getIntent().getExtras();
        TextView title = (TextView) findViewById(R.id.titleSecret);
        TextView secretText = (TextView) findViewById(R.id.secretText);
        TextView secretAuthor = (TextView) findViewById(R.id.secretAuthor);
        ImageView secretImg = (ImageView) findViewById(R.id.secretImage);

        if (extras != null) {
            String button = extras.getString("Button");

            switch (button) {
                case "1":
                    secretText.setVisibility(View.VISIBLE);
                    title.setText(R.string.favTitle);
                    secretText.setText(R.string.favoriteQuote);
                    secretAuthor.setText(R.string.favoriteQuoteAuthor);

                    Toast.makeText(this, "Wow you selected my fav quote", Toast.LENGTH_SHORT).show();
                    break;

                case "2":
                    secretText.setVisibility(View.VISIBLE);
                    title.setText(R.string.factTitle);
                    secretText.setText(R.string.factText);
                    secretAuthor.setText(R.string.anotherFact);
                    Toast.makeText(this, "Booooooooring", Toast.LENGTH_SHORT).show();
                    break;

                case "3":
                    title.setText(R.string.favoriteImgTitle);
                    secretImg.setVisibility(View.VISIBLE);
                    secretImg.setImageResource(R.mipmap.cat_pc);
                    secretAuthor.setText(R.string.credit);
                    Toast.makeText(this, "Yay a cat picture", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }

        }

    }
}