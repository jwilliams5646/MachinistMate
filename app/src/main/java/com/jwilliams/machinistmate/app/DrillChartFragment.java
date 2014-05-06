package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.jwilliams.machinistmate.app.AppContent.DrilldbHelper;
import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john.williams on 4/24/2014.
 */
public class DrillChartFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private Button allInfo;
    private Button wiregaugeButton;
    private Button letterButton;
    private Button fractionButton;
    private Button metricButton;
    private RobotoTextView typeHeader;
    private GridView referenceGridView;
    private DrilldbHelper myDbHelper;
    private List<String> li;
    private ArrayAdapter<String> adapter;
    private Typeface tf;

    public DrillChartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.drill_chart_layout, container, false);

        setLayout(rootView);
        setAdapter();
        setDatabase();

        setAllInfo();

        allInfo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAllInfo();
            }
        });

        wiregaugeButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWireGauge();
            }
        });

        letterButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLetter();
            }
        });

        fractionButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFraction();
            }
        });

        metricButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMetric();
            }
        });
        return rootView;
    }


    //view pager setup info
    static DrillChartFragment newInstance(int position) {
        DrillChartFragment frag=new DrillChartFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.drill_size_chart), position + 2));
    }//end view pager setup info


    public void setLayout(View rootView){
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        allInfo = (Button)rootView.findViewById(R.id.drill_all_button);
        wiregaugeButton = (Button)rootView.findViewById(R.id.drill_wiregauge_button);
        letterButton = (Button)rootView.findViewById(R.id.drill_letter_button);
        fractionButton = (Button)rootView.findViewById(R.id.drill_fraction_button);
        metricButton = (Button)rootView.findViewById(R.id.drill_metric_button);

        allInfo.setTypeface(tf);
        wiregaugeButton.setTypeface(tf);
        letterButton.setTypeface(tf);
        fractionButton.setTypeface(tf);
        metricButton.setTypeface(tf);

        typeHeader = (RobotoTextView)rootView.findViewById(R.id.type_header);

        referenceGridView = (GridView)rootView.findViewById(R.id.drill_chart_grid);
    }

    private void setAdapter(){
        //instantiates the array list used for the adapter
        li = new ArrayList<String>();
        //the adapter, sets the list to the layout
        adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.grid_item_layout, li);
    }

    private void setDatabase(){
        //instantiates the database and the
        myDbHelper = new DrilldbHelper(getActivity());
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
    }


    public void openDb(){
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
    }

    public void setAllInfo(){
        openDb();
        adapter.clear();
        typeHeader.setText("All");
        Cursor c = myDbHelper.byAll();
        while(c.moveToNext()) {
            String size = c.getString(c.getColumnIndex("size"));
            String standard = c.getString(c.getColumnIndex("standard"));
            String metric = c.getString(c.getColumnIndex("metric"));
            li.add(size);
            li.add(standard);
            li.add(metric);
        }
        referenceGridView.setAdapter(adapter);
        myDbHelper.close();
    }

    public void setWireGauge(){

        openDb();
        adapter.clear();
        typeHeader.setText("Wiregauge");
        Cursor c = myDbHelper.byWireGauge();
        while(c.moveToNext()) {
            String size = c.getString(c.getColumnIndex("size"));
            String standard = c.getString(c.getColumnIndex("standard"));
            String metric = c.getString(c.getColumnIndex("metric"));
            li.add(size);
            li.add(standard);
            li.add(metric);
        }
        referenceGridView.setAdapter(adapter);
        myDbHelper.close();
    }


    private void setLetter() {
        openDb();
        adapter.clear();
        typeHeader.setText("Letter");
        Cursor c = myDbHelper.byLetter();
        while(c.moveToNext()) {
            String size = c.getString(c.getColumnIndex("size"));
            String standard = c.getString(c.getColumnIndex("standard"));
            String metric = c.getString(c.getColumnIndex("metric"));
            li.add(size);
            li.add(standard);
            li.add(metric);
        }
        referenceGridView.setAdapter(adapter);
        myDbHelper.close();
    }

    public void setFraction(){
        openDb();
        adapter.clear();
        typeHeader.setText("Fraction");
        Cursor c = myDbHelper.byFraction();
        while(c.moveToNext()) {
            String size = c.getString(c.getColumnIndex("size"));
            String standard = c.getString(c.getColumnIndex("standard"));
            String metric = c.getString(c.getColumnIndex("metric"));
            li.add(size);
            li.add(standard);
            li.add(metric);
        }
        referenceGridView.setAdapter(adapter);
        myDbHelper.close();
    }

    public void setMetric(){
        openDb();
        adapter.clear();
        typeHeader.setText("Metric");
        Cursor c = myDbHelper.byMetric();
        while(c.moveToNext()) {
            String size = c.getString(c.getColumnIndex("size"));
            String standard = c.getString(c.getColumnIndex("standard"));
            String metric = c.getString(c.getColumnIndex("metric"));
            li.add(size);
            li.add(standard);
            li.add(metric);
        }
        referenceGridView.setAdapter(adapter);
        myDbHelper.close();
    }

}
