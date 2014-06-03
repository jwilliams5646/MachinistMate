package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.jwilliams.machinistmate.app.AppContent.DbHelper;
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
    private List<String> li;
    private ArrayAdapter<String> adapter;
    private Typeface tf;
    private int dbSwitch;

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
        dbSwitch = 0;
        setLayout(rootView);
        setAdapter();
        new setGrid().execute();

        allInfo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeHeader.setText("All");
                dbSwitch = 0;
                new setGrid().execute();
            }
        });

        wiregaugeButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeHeader.setText("Wiregauge");
                dbSwitch = 1;
                new setGrid().execute();
            }
        });

        letterButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeHeader.setText("Letter");
                dbSwitch = 2;
                new setGrid().execute();
            }
        });

        fractionButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeHeader.setText("Fraction");
                dbSwitch = 3;
                new setGrid().execute();
            }
        });

        metricButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeHeader.setText("Metric");
                dbSwitch = 4;
                new setGrid().execute();
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

        typeHeader.setText("All");
    }

    private void setAdapter(){
        //instantiates the array list used for the adapter
        li = new ArrayList<String>();
        //the adapter, sets the list to the layout
        adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.grid_item_layout, li);
    }

    private void setDatabase(DbHelper myDbHelper){
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
    }


    public void openDb(DbHelper myDbHelper){
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
    }

    private class setGrid extends AsyncTask {
        Cursor c;
        DbHelper myDbHelper;
        String size;
        String standard;
        String metric;

        @Override
        protected void onPreExecute(){
            if(adapter!=null){
                adapter.clear();
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Log.d("DB Thread", "Starting work");
            myDbHelper = new DbHelper(getActivity());
            setDatabase(myDbHelper);
            openDb(myDbHelper);
            //Cursor c;

            switch (dbSwitch){
                case 0:
                    c = myDbHelper.byAll();
                    createListAdapter(c);
                    break;
                case 1:
                    c = myDbHelper.byWireGauge();
                    createListAdapter(c);
                    break;
                case 2:
                    c = myDbHelper.byLetter();
                    createListAdapter(c);
                    break;
                case 3:
                    c = myDbHelper.byFraction();
                    createListAdapter(c);
                    break;
                case 4:
                    c = myDbHelper.byMetric();
                    createListAdapter(c);
                    break;
                default:
                    c = myDbHelper.byAll();
                    createListAdapter(c);
                    break;
            }

            myDbHelper.close();
            Log.d("DB Thread", "Ending work");
            return null;
        }

        private void createListAdapter(Cursor c) {
            while(c.moveToNext()) {
                size = c.getString(c.getColumnIndex("size"));
                standard = c.getString(c.getColumnIndex("standard"));
                metric = c.getString(c.getColumnIndex("metric"));
                li.add(size);
                li.add(standard);
                li.add(metric);
            }
        }

        @Override
        protected void onPostExecute(Object result){
            referenceGridView.setAdapter(adapter);
            c = null;
            myDbHelper = null;
            size = null;
            standard = null;
            metric = null;
            this.cancel(true);
        }
    }
}
