package com.example.swastha_naari.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.swastha_naari.Adapter.FormAdapter;
import com.example.swastha_naari.Adapter.UsernameAdapter;
import com.example.swastha_naari.Models.FormModel;
import com.example.swastha_naari.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class DatabyUsernameActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    String username;
    ArrayList<FormModel> list = new ArrayList<>();

    RealmChangeListener realmChangeListener;

    Realm realm;

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
//            username = formModel.getName();
            list.add(formModel);
//            Log.d("username", username);
        }

        UsernameAdapter usernameAdapter = new UsernameAdapter(this, list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(usernameAdapter);

        refresh();
    }

    private void refresh(){
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                UsernameAdapter usernameAdapter = new UsernameAdapter(DatabyUsernameActivity.this, list);
                recyclerView.setAdapter(usernameAdapter);
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
