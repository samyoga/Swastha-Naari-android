package com.example.swastha_naari.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swastha_naari.Models.FormModel;
import com.example.swastha_naari.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class DataCollectActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @BindView(R.id.EditTextName)
    EditText name;

    @BindView(R.id.EditTextAge)
    EditText age;

    @BindView(R.id.EditTextGuardianName)
    EditText guardianName;

    @BindView(R.id.EditTextBloodPressure)
    EditText bloodPressure;

    @BindView(R.id.EditTextWeight)
    EditText weight;

    @BindView(R.id.EditTextHistory)
    EditText history;

    @BindView(R.id.ETOtherMedicalReport)
    EditText otherMedicalReport;

    @BindView(R.id.submit)
    Button submitBtn;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collect);
        ButterKnife.bind(this);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

    }

    public void submit(){
        String Name = name.getText().toString();
        String Age = age.getText().toString();
        String GuardianName = guardianName.getText().toString();
        String BloodPressure = bloodPressure.getText().toString();
        String Weight = weight.getText().toString();
        String History = history.getText().toString();
        String MedicalReport = otherMedicalReport.getText().toString();

        realm.beginTransaction();
        FormModel formModel = realm.createObject(FormModel.class);
        formModel.setName(Name);
        formModel.setAge(Age);
        formModel.setGuardianName(GuardianName);
        formModel.setBloodPressure(BloodPressure);
        formModel.setWeight(Weight);
        formModel.setHistory(History);
        formModel.setMedicalReport(MedicalReport);
        realm.deleteAll();
        realm.commitTransaction();

        Intent intent = new Intent(DataCollectActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}
