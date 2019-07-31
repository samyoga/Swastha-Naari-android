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
        holder.mName.setText(arrayList.get(position).getName());
        holder.mAge.setText(arrayList.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mAge;


        public ViewHolder(View view) {
            super(view);

            mName = view.findViewById(R.id.cvName);
            mAge = view.findViewById(R.id.cvAge);
        }
    }
}
