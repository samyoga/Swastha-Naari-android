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

import java.util.UUID;

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

    @BindView(R.id.EditTextPregnancyAge)
    EditText firstPregnancyAge;

    @BindView(R.id.EditTextNumChild)
    EditText childNumber;

    @BindView(R.id.EditTextMenopause)
    EditText menopause;

    @BindView(R.id.EditTextBirthSpacing)
    EditText birthSpacing;

    @BindView(R.id.EditTextMensGap)
    EditText menstrualGap;

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
        String FirstPregnancyAge = firstPregnancyAge.getText().toString();
        String ChildNumber = childNumber.getText().toString();
        String Menopause = menopause.getText().toString();
        String BirthSpacing = birthSpacing.getText().toString();
        String MensGap = menstrualGap.getText().toString();
        String History = history.getText().toString();
        String MedicalReport = otherMedicalReport.getText().toString();

        realm.beginTransaction();
        FormModel formModel = realm.createObject(FormModel.class, UUID.randomUUID().toString());
        formModel.setName(Name);
        formModel.setAge(Age);
        formModel.setGuardianName(GuardianName);
        formModel.setBloodPressure(BloodPressure);
        formModel.setWeight(Weight);
        formModel.setFirstPregnancyAge(FirstPregnancyAge);
        formModel.setChildNumber(ChildNumber);
        formModel.setMenopauseAge(Menopause);
        formModel.setBirthSpacing(BirthSpacing);
        formModel.setMenstrualInterval(MensGap);
        formModel.setHistory(History);
        formModel.setMedicalReport(MedicalReport);
//        realm.deleteAll();
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
