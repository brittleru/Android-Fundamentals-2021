package com.example.portfolio.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.portfolio.MainActivity;
import com.example.portfolio.R;

public class SharedPreferences extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String APP_KEY = "android_course_key";
    private static final String USER_LOCAL_STORE = "user_local_store";

    // Global Keys
    private static final String USER_FIRST_NAME = "user_first_name";
    private static final String USER_LAST_NAME = "user_last_name";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_TERMS = "user_terms";

    // Global IDs
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private TextView result;
    private Button save;
    private Button clear;
    private Button show;
    private CheckBox terms;

    // Global strings
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassWord;
    private String userTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        result = (TextView) findViewById(R.id.result);
        save = (Button) findViewById(R.id.saveButton);
        clear = (Button) findViewById(R.id.clearButton);
        show = (Button) findViewById(R.id.showButton);
        terms = (CheckBox) findViewById(R.id.terms);

//        Log.d(TAG, getDataSharedPref(this, ));
//        result.setText(getDataFromSharedPref());

        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userFirstName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userLastName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                userEmail = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userPassWord = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    userTerms = "Yes";
                }
                else {
                    //
                    userTerms = "No";
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "First name: " + getDataSharedPref(SharedPreferences.this, USER_FIRST_NAME) +
                        "\nLast name: " + getDataSharedPref(SharedPreferences.this, USER_LAST_NAME) +
                        "\nEmail: " + getDataSharedPref(SharedPreferences.this, USER_EMAIL) +
                        "\nPassword: " + getDataSharedPref(SharedPreferences.this, USER_PASSWORD) +
                        "\nAgreed: " + getDataSharedPref(SharedPreferences.this, USER_TERMS);

                result.setText(data);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteALLSharedPref(SharedPreferences.this);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataSharedPref(SharedPreferences.this, USER_FIRST_NAME, userFirstName);
                saveDataSharedPref(SharedPreferences.this, USER_LAST_NAME, userLastName);
                saveDataSharedPref(SharedPreferences.this, USER_EMAIL, userEmail);
                saveDataSharedPref(SharedPreferences.this, USER_PASSWORD, userPassWord);
                saveDataSharedPref(SharedPreferences.this, USER_TERMS, userTerms);
            }
        });
    }


    // CRUD operations
    // Create
    private static void saveDataSharedPref(Context context, String key, String value) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferences.USER_LOCAL_STORE, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    // Read
    private String getDataSharedPref(Context context, String key) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferences.USER_LOCAL_STORE, Context.MODE_PRIVATE);

        return sharedPreferences.getString(key, "Empty");
    }

    // Delete
    private static void deteleDataSharedPref(Context context, String key) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferences.USER_LOCAL_STORE, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    // Delete All
    private static void deleteALLSharedPref(Context context) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferences.USER_LOCAL_STORE, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}