package com.example.swastha_naari.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swastha_naari.Activity.ViewDataActivity;
import com.example.swastha_naari.Activity.VisualizeDataActivity;
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

        holder.mName.setText("Name: " + arrayList.get(position).getName());
        holder.mAge.setText("Age: " + arrayList.get(position).getAge());
        holder.mGuardianName.setText("Guardian's name: " + arrayList.get(position).getGuardianName());
        holder.mBloodPressure.setText("Blood pressure: " + arrayList.get(position).getBloodPressure());
        holder.mWeight.setText("Weight: " + arrayList.get(position).getWeight());
        holder.mfirstPregnancyAge.setText("Age at first pregnancy: " + arrayList.get(position).getFirstPregnancyAge());
        holder.mchildNum.setText("Number of children: " + arrayList.get(position).getChildNumber());
        holder.mmenopauseAge.setText("Age at menopause: " + arrayList.get(position).getMenopauseAge());
        holder.mBirthSpacing.setText("Birth spacing: " + arrayList.get(position).getBirthSpacing());
        holder.mmensGap.setText("Menstrual interval: " + arrayList.get(position).getMenstrualInterval());
        holder.mHistory.setText("History: " + arrayList.get(position).getHistory());
        holder.mOtherMedicalReport.setText("Other medical report: " + arrayList.get(position).getMedicalReport());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), VisualizeDataActivity.class);
                intent.putExtra("name", arrayList.get(position).getName());
                intent.putExtra("id", arrayList.get(position).getId());
                intent.putExtra("weight", arrayList.get(position).getWeight());
                intent.putExtra("firstPregnancyAge", arrayList.get(position).getFirstPregnancyAge());
                intent.putExtra("childNumber", arrayList.get(position).getChildNumber());
                intent.putExtra("menopauseAge", arrayList.get(position).getMenopauseAge());
                intent.putExtra("birthSpacing", arrayList.get(position).getBirthSpacing());
                intent.putExtra("mensGap", arrayList.get(position).getMenstrualInterval());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName, mAge, mGuardianName, mBloodPressure, mWeight,mfirstPregnancyAge, mchildNum, mmenopauseAge, mBirthSpacing, mmensGap , mHistory, mOtherMedicalReport;
        TextView username;

        public ViewHolder(View view) {
            super(view);

            mName = view.findViewById(R.id.cvName);
            mAge = view.findViewById(R.id.cvAge);
            mGuardianName = view.findViewById(R.id.cvGuardianName);
            mBloodPressure = view.findViewById(R.id.cvBloodPressure);
            mWeight = view.findViewById(R.id.cvWeight);
            mfirstPregnancyAge = view.findViewById(R.id.cvFirstPregnancyAge);
            mchildNum = view.findViewById(R.id.cvChildNumber);
            mmenopauseAge = view.findViewById(R.id.cvMenopause);
            mBirthSpacing = view.findViewById(R.id.cvBirthSpacing);
            mmensGap = view.findViewById(R.id.cvMensGap);
            mHistory = view.findViewById(R.id.cvHistory);
            mOtherMedicalReport = view.findViewById(R.id.cvOtherMedicalReport);
//            username = view.findViewById(R.id.userName);
        }
    }
}
