package com.example.swastha_naari.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.util.Log;

import com.example.swastha_naari.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisualizeDataActivity extends AppCompatActivity{

    String name, id, weight, firstPregnancyAge, childNum, menopause, birthSpacing, menstrualInterval ;
    ArrayList<Entry> data = new ArrayList<>();
//    List<DropBoxManager.Entry> entries = new ArrayList<DropBoxManager.Entry>();
    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize_data);
//        ButterKnife.bind(this);

        chart = (LineChart) findViewById(R.id.chart);
//        chart.setOnChartGestureListener(VisualizeDataActivity.this);
//        chart.setOnChartValueSelectedListener(VisualizeDataActivity.this);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);

        Intent intent = getIntent();
        if (null != intent) {
            name = getIntent().getStringExtra("name");
            id = getIntent().getStringExtra("id");
            weight = getIntent().getStringExtra("weight");
            firstPregnancyAge = getIntent().getStringExtra("firstPregnancyAge");
            childNum = getIntent().getStringExtra("childNumber");
            menopause = getIntent().getStringExtra("menopauseAge");
            birthSpacing = getIntent().getStringExtra("birthSpacing");
            menstrualInterval = getIntent().getStringExtra("mensGap");
            Log.d("nancy", "onCreate: "+id);
        }

        data.add(new Entry(0, Integer.parseInt(weight)));
        data.add(new Entry(1, Integer.parseInt(weight)));
        data.add(new Entry(2, Integer.parseInt(weight)));
        data.add(new Entry(3, Integer.parseInt(weight)));
        data.add(new Entry(4, Integer.parseInt(weight)));
        data.add(new Entry(5, Integer.parseInt(weight)));
        data.add(new Entry(6, Integer.parseInt(weight)));

        LineDataSet set1 = new LineDataSet(data, "Weight");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData lineData = new LineData(dataSets);

        chart.setData(lineData);

//        data.add(weight);
//        data.add(firstPregnancyAge);
//        data.add(childNum);
//        data.add(menopause);
//        data.add(birthSpacing);
//        data.add(menstrualInterval);

//        for (data: dataObjects){
//            entries.add(new DropBoxManager.Entry(data.get))
//        }

    }
}
