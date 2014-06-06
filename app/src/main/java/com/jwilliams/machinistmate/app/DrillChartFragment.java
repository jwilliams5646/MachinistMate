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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jwilliams.machinistmate.app.AppContent.DbHelper;
import com.jwilliams.machinistmate.app.AppContent.RobotoButton;
import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john.williams on 4/24/2014.
 */
public class DrillChartFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private RobotoButton allInfo;
    private RobotoButton wiregaugeButton;
    private RobotoButton letterButton;
    private RobotoButton fractionButton;
    private RobotoButton metricButton;
    private RobotoTextView typeHeader;
    private GridView referenceGridView;
    private List<String> li;
    private ArrayAdapter<String> adapter;
    private int dbSwitch;
    private static final String TEST_DEVICE_ID = "03f3f1d189532cca";
    private AdView adView;
    private AdRequest adRequest;

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
        setAd(rootView);
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

    private void setAd(View rootView){
        adView = (AdView)rootView.findViewById(R.id.drill_adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(TEST_DEVICE_ID)
                .build();
        adView.loadAd(adRequest);
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
        allInfo = (RobotoButton)rootView.findViewById(R.id.drill_all_button);
        wiregaugeButton = (RobotoButton)rootView.findViewById(R.id.drill_wiregauge_button);
        letterButton = (RobotoButton)rootView.findViewById(R.id.drill_letter_button);
        fractionButton = (RobotoButton)rootView.findViewById(R.id.drill_fraction_button);
        metricButton = (RobotoButton)rootView.findViewById(R.id.drill_metric_button);

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

    @Override
    public void onPause(){
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed. */
    @Override
    public void onDestroy() {
        // Destroy the AdView.
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
