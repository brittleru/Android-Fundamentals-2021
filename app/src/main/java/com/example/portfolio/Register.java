package com.example.portfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class Register extends AppCompatActivity {

    private static final String emailRegex = "^(.+)@(.+)$";
    private static final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern EMAIL_ADDRESS = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
    private static final Pattern PASSWORD = Pattern.compile(passwordRegex);
    CircularProgressButton circularProgressButton;
    ImageView back;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        circularProgressButton = findViewById(R.id.circularRegisterBt);
        back = findViewById(R.id.arrow);

        firstName = findViewById(R.id.editTextFName);
        lastName = findViewById(R.id.editTextLName);
        email = findViewById(R.id.editTextE);
        password = findViewById(R.id.editTextPassword1);
        password2 = findViewById(R.id.editTextPassword2);

        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final UserEntity userEntity = new UserEntity();
                userEntity.setFirstName(firstName.getText().toString());
                userEntity.setLastName(lastName.getText().toString());
                userEntity.setEmail(email.getText().toString());
                userEntity.setPassword(password.getText().toString());

                String passwordText = password2.getText().toString();

                if (validateInput(userEntity, passwordText)) {
                    UserDatabase userDatabase = UserDatabase.getUserDataBase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Register.this, "User Registered!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                    Intent intent = new Intent(Register.this, Login.class);// New activity
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private Boolean validateInput(UserEntity userEntity, String pass2) {
        if (userEntity.getFirstName().isEmpty() || userEntity.getLastName().isEmpty() || userEntity.getEmail().isEmpty() || userEntity.getPassword().isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!userEntity.getPassword().equals(pass2)) {
            Toast.makeText(this, "Passwords doesn't match " + userEntity.getPassword(), Toast.LENGTH_SHORT).show();
            password.setError("Password doesn't match");
            return false;
        }
        else if (!validateEmailRegex(userEntity.getEmail())) {
            Toast.makeText(this, "Please enter an valid email!", Toast.LENGTH_SHORT).show();
            email.setError("Invalid Email!");
            return false;
        }
        else if (!validatePasswordRegex(userEntity.getPassword())) {
            Toast.makeText(this, "Password should have 1 digit, 1 lowercase, 1 uppercase, 1 special character, no whitespaces and at least 8 characters", Toast.LENGTH_LONG).show();
            password.setError("Password should have 1 digit, 1 lowercase, 1 uppercase, 1 special character, no whitespaces and at least 8 characters");
            return false;
        }
        else {
            Toast.makeText(this, "Register successful!", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private static boolean validateEmailRegex(String emailRegex) {
        Matcher matcher = EMAIL_ADDRESS.matcher(emailRegex);
        return matcher.matches();
    }
    private static boolean validatePasswordRegex(String passwordRegex) {
        Matcher matcher = PASSWORD.matcher(passwordRegex);
        return matcher.matches();
    }
}
