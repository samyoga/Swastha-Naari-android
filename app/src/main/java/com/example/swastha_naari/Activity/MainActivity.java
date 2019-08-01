package com.example.swastha_naari.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swastha_naari.R;

public class MainActivity extends AppCompatActivity {

    private Button formButton;
    private Button report;
    TextView logout;

    SharedPreferences loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formButton = (Button) findViewById(R.id.form);
        report = (Button) findViewById(R.id.report);
        logout = (TextView) findViewById(R.id.logout);

//        loggedIn = getSharedPreferences("logged_in", MODE_PRIVATE);
//        if (loggedIn.getBoolean("loggedIn", true)){
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//        }


//        SharedPreferences loggedIn = getSharedPreferences("logged_in", MODE_PRIVATE);
////        String loginValue = loggedIn.getString("username", "");
//        Boolean loginValue = loggedIn.getBoolean("loggedIn", true);
//        Log.d("usernameLogIn", String.valueOf(loginValue));
//        if (loginValue == false) {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//        }

            formButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, DataCollectActivity.class);
                    startActivity(intent);
                }
            });

            report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DatabyUsernameActivity.class);
                    startActivity(intent);
                }
            });

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loggedIn = getSharedPreferences("logged_in", MODE_PRIVATE);
                    SharedPreferences.Editor editor = loggedIn.edit();
                    editor.clear();
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

    }
}
