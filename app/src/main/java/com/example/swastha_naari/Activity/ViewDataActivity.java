package com.example.swastha_naari.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swastha_naari.Adapter.FormAdapter;
import com.example.swastha_naari.Models.FormModel;
import com.example.swastha_naari.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ViewDataActivity extends AppCompatActivity {

    Realm realm;
    ArrayList<FormModel> list = new ArrayList<>();
    RealmChangeListener realmChangeListener;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        RealmResults<FormModel> realmResults = realm.where(FormModel.class).findAllAsync();
        for (FormModel formModel:realmResults){
            list.add(formModel);
        }

        FormAdapter formAdapter = new FormAdapter(this, list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(formAdapter);

        refresh();
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
        Realm.deleteRealm(realm.getConfiguration());
    }
}
