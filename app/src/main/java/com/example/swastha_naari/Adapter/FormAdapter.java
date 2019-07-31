package com.example.swastha_naari.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swastha_naari.Models.FormModel;
import com.example.swastha_naari.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import io.realm.OrderedRealmCollection;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {
    Context context;
    ArrayList<FormModel> arrayList;

    public FormAdapter(Context c, ArrayList<FormModel> data) {
        this.context = c;
        this.arrayList = data;
    }

    @NonNull
    @Override
    public FormAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.form_data_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FormAdapter.ViewHolder holder, final int position) {
//        holder.username.setText("Record: "  + arrayList.get(position).getName() );

        holder.mName.setText(arrayList.get(position).getName());
        holder.mAge.setText(arrayList.get(position).getAge());
        holder.mGuardianName.setText(arrayList.get(position).getGuardianName());
        holder.mBloodPressure.setText(arrayList.get(position).getBloodPressure());
        holder.mWeight.setText(arrayList.get(position).getWeight());
        holder.mHistory.setText(arrayList.get(position).getHistory());
        holder.mOtherMedicalReport.setText(arrayList.get(position).getMedicalReport());
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
