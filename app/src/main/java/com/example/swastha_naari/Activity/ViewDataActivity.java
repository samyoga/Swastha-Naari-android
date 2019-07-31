package com.example.swastha_naari.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swastha_naari.Adapter.FormAdapter;
import com.example.swastha_naari.Models.FormModel;
import com.example.swastha_naari.R;

import java.text.Normalizer;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ViewDataActivity extends AppCompatActivity {

    Realm realm;
    ArrayList<FormModel> list = new ArrayList<>();
    RealmChangeListener realmChangeListener;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

//    @BindView(R.id.cvName)
//    TextView Name;
//
//    @BindView(R.id.cvAge)
//    TextView age;
//
//    @BindView(R.id.cvGuardianName)
//    TextView guardianName;
//
//    @BindView(R.id.cvBloodPressure)
//    TextView bloodPressure;
//
//    @BindView(R.id.cvWeight)
//    TextView weight;
//
//    @BindView(R.id.cvFirstPregnancyAge)
//    TextView firstPregnancyAge;
//
//    @BindView(R.id.cvChildNumber)
//    TextView childNumber;
//
//    @BindView(R.id.cvMenopause)
//    TextView menopause;
//
//    @BindView(R.id.cvBirthSpacing)
//    TextView birthSpacing;
//
//    @BindView(R.id.cvMensGap)
//    TextView mensGap;
//
//    @BindView(R.id.cvHistory)
//    TextView history;
//
//    @BindView(R.id.cvOtherMedicalReport)
//    TextView otherMedicalReport;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        ButterKnife.bind(this);

//        Name.setVisibility(View.VISIBLE);
//        age.setVisibility(View.VISIBLE);
//        guardianName.setVisibility(View.VISIBLE);
//        bloodPressure.setVisibility(View.VISIBLE);
//        weight.setVisibility(View.VISIBLE);
//        firstPregnancyAge.setVisibility(View.VISIBLE);
//        childNumber.setVisibility(View.VISIBLE);
//        menopause.setVisibility(View.VISIBLE);
//        birthSpacing.setVisibility(View.VISIBLE);
//        mensGap.setVisibility(View.VISIBLE);
//        history.setVisibility(View.VISIBLE);
//        otherMedicalReport.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

//        RealmQuery<FormModel> realmQuery = realm.where(FormModel.class);
//
//        for (String id: ids){
//            realmQuery = realmQuery.or().equalTo("id", id);
//        }

        RealmResults<FormModel> realmResults = realm.where(FormModel.class).findAllAsync();
        for (FormModel formModel: realmResults){
//
           list.add(getFormModel(formModel.getId()));
           Log.d("id", formModel.getId());
////            list.add(formModel);
        }

        Intent intent = getIntent();
        if (null != intent) {
            name = getIntent().getStringExtra("name");
        }


        FormAdapter formAdapter = new FormAdapter(this, list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(formAdapter);

        refresh();
    }

    public FormModel getFormModel(String id){
        return realm.where(FormModel.class).equalTo("id", id).findFirst();
    }

    private void refresh(){
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                FormAdapter formAdapter = new FormAdapter(ViewDataActivity.this, list);
                recyclerView.setAdapter(formAdapter);
            }
        };
        realm.addChangeListener(realmChangeListener);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}
