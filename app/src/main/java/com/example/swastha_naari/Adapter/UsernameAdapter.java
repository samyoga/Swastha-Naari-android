package com.example.swastha_naari.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swastha_naari.Activity.ViewDataActivity;
import com.example.swastha_naari.Models.FormModel;
import com.example.swastha_naari.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import io.realm.OrderedRealmCollection;

public class UsernameAdapter extends RecyclerView.Adapter<UsernameAdapter.ViewHolder> {
    Context context;
    ArrayList<FormModel> arrayList;

    public UsernameAdapter(Context c,  ArrayList<FormModel> data) {
        this.context = c;
        this.arrayList = data;
    }



    @NonNull
    @Override
    public UsernameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.form_data_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsernameAdapter.ViewHolder holder, final int position) {
        holder.mName.setText("Record of" + " "+ arrayList.get(position).getName());
//        holder.mAge.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewDataActivity.class);
                intent.putExtra("name", arrayList.get(position).getName());
                intent.putExtra("age", arrayList.get(position).getAge());
                intent.putExtra("age", arrayList.get(position).getGuardianName());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName, mAge, mGuardianName, mBloodPressure, mWeight, mHistory, mOtherMedicalReport;
        TextView username;

        public ViewHolder(View view) {
            super(view);

            mName = view.findViewById(R.id.cvName);
            mAge = view.findViewById(R.id.cvAge);
            mGuardianName = view.findViewById(R.id.cvGuardianName);
            mBloodPressure = view.findViewById(R.id.cvBloodPressure);
            mWeight = view.findViewById(R.id.cvWeight);
            mHistory = view.findViewById(R.id.cvHistory);
            mOtherMedicalReport = view.findViewById(R.id.cvOtherMedicalReport);
//            username = view.findViewById(R.id.userName);
        }
    }
}
