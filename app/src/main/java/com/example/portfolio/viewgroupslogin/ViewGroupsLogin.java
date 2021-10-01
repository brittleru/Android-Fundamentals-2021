package com.example.portfolio.viewgroupslogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portfolio.MainActivity;
import com.example.portfolio.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewGroupsLogin extends AppCompatActivity {

    private static final String emailRegex = "^(.+)@(.+)$";
    private static final Pattern EMAIL_ADDRESS = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText email;
    private EditText password;
    private Button submit;
    private CheckBox acceptTerms;
    private TextView formEmail;
    private TextView formAccept;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_groups_login2);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submitButton);
        acceptTerms = findViewById(R.id.acceptTerms);
        formEmail = findViewById(R.id.displayEmail);
        formAccept = findViewById(R.id.displayAccept);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 5) {
                    email.setError("Email should have more than 8 characters");
                }
                else {
                    email.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Email = " + email.getText() + "\nPass = " + password.getText());

                if (email.getText().toString().isEmpty()) {
                    email.setError("The email should not be empty!");
                }
                else {
                    email.setError(null);
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("The password should not be empty!");
                }
                else {
                    password.setError(null);

//                Matcher matcher = EMAIL_ADDRESS.matcher(email.getText().toString());
                    boolean emailValid = validate(email.getText().toString());
                    if (emailValid) {
                        email.setError(null);
                        String formEmailText = "Email: " + email.getText().toString();
                        String formAcceptText = "";
                        if (acceptTerms.isChecked()) {
                            formAcceptText += "Accepted terms: Yes";
                        }
                        else {
                            formAcceptText += "Accepted terms: No";
                        }
                        formEmail.setText(formEmailText);
                        formAccept.setText(formAcceptText);
                        Toast.makeText(ViewGroupsLogin.this, "You are logged!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        email.setError("Fill the input with a valid email address");
                    }
                }

            }
        });
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = EMAIL_ADDRESS.matcher(emailStr);
        return matcher.matches();
    }
}