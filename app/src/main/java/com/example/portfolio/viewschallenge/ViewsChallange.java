package com.example.portfolio.viewschallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portfolio.MainActivity;
import com.example.portfolio.R;

public class ViewsChallange extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_challange);
        Log.d(TAG, "This is a debug!!");

        // Declare IDs
//        ImageView imageView = (ImageView) findViewById(R.id.image);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.spinnerLoading);
        Button button = (Button) findViewById(R.id.buttonNormal);
        Button buttonGreen = (Button) findViewById(R.id.buttonGreen);
        EditText username = (EditText) findViewById(R.id.userName);
        EditText password = (EditText) findViewById(R.id.password);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        TextView volume = (TextView) findViewById(R.id.textSeekBar);


//        imageView.setImageResource(R.drawable.ic_launcher_foreground);
        int orangeColor = ContextCompat.getColor(this, R.color.orange);
        button.setBackgroundColor(orangeColor);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked Normal Button!");
                Toast.makeText(ViewsChallange.this, "Normal Button Clicked", Toast.LENGTH_SHORT).show();
                seekBar.setProgress(80);
                progressBar.setVisibility(View.GONE);

                button.setBackgroundColor(ContextCompat.getColor(ViewsChallange.this, R.color.teal_700));
            }
        });
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked Green Button!");
                Toast.makeText(ViewsChallange.this, "Green Button Clicked", Toast.LENGTH_SHORT).show();
                seekBar.setProgress(30);
                progressBar.setVisibility(View.VISIBLE);
                button.setBackgroundColor(orangeColor);
            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "Username beforeTextChanged(): " + s);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "Username onTextChanged(): " + s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "Username afterTextChanged(): " + s);

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "pass beforeTextChanged(): " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "pass onTextChanged(): " + s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "pass afterTextChanged(): " + s);

            }
        });

        ((CheckBox) findViewById(R.id.notifyCheckbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged(), isChecked = " + isChecked);

                if (isChecked) {
                    Toast.makeText(ViewsChallange.this, "We will send notifications", Toast.LENGTH_SHORT).show();
                    username.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(ViewsChallange.this, "We won't send notifications", Toast.LENGTH_SHORT).show();
                    username.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                }
            }
        });

        ((RadioGroup) findViewById(R.id.radioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                Log.d(TAG, "onCheckedChanged(): " + radioButton.getText().toString());
                Toast.makeText(ViewsChallange.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        ratingBar.setRating(1);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d(TAG, "onRatingChanged(), rating: " + rating);
                Toast.makeText(ViewsChallange.this, rating + " Stars", Toast.LENGTH_SHORT).show();
            }
        });

        ((Switch) findViewById(R.id.switchId)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged(), isChecked: " + isChecked);

                if (isChecked) {
                    Toast.makeText(ViewsChallange.this, "Backup Sync", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ViewsChallange.this, "No Backup", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged(), progress: " + progress);
                String progressText = (String) ("Volume: " + progress);
                volume.setText(progressText);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        searchView.setQuery("", false);

    }
}