package com.example.portfolio.fragmentsslides;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.portfolio.MainActivity;
import com.example.portfolio.R;

public class FragSlides extends AppCompatActivity implements SetTextListener {

    private static final String TAG = FragSlides.class.getSimpleName();
    static final String KEY = "Key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_slides);

        Log.d(TAG, "onCreate()");
        Bundle bundle = new Bundle();
        bundle.putString(KEY, "Hello Fragment");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HelloFragment fragment = new HelloFragment();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragmentContainter, fragment, HelloFragment.class.getSimpleName());
        fragmentTransaction.commit();

        // Search for a fragment by ID or TAG
        HelloFragment staticFragment = (HelloFragment) fragmentManager.findFragmentById(R.id.fragmentHello);
        HelloFragment dynamicFragment = (HelloFragment) fragmentManager.findFragmentByTag(HelloFragment.class.getSimpleName());

        findViewById(R.id.hideFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Is fragment visible: " + staticFragment.isHidden());
                Toast.makeText(FragSlides.this, "Is fragment visible: " + staticFragment.isHidden(), Toast.LENGTH_SHORT).show();
                fragmentTransaction.hide(staticFragment);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
        HelloFragment fragment = (HelloFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentHello);
        assert fragment != null;
        fragment.setText("Hello user");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void setText(String text) {
        Log.d(TAG, "setText( " + text + " )");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed()");
    }
}