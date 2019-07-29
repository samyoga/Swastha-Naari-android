package com.example.swastha_naari;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataCollectActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @BindView(R.id.submit)
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collect);
        ButterKnife.bind(this);

        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataCollectActivity.this, ViewDataActivity.class);
                startActivity(intent);
            }
        });

    }
}
