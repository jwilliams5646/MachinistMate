package com.jwilliams.machinistmate.app;

import android.database.Cursor;
import android.database.SQLException;
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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jwilliams.machinistmate.app.AppContent.DbHelper;
import com.jwilliams.machinistmate.app.AppContent.RobotoButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by john.williams on 6/6/2014.
 */
public class GMFragment extends Fragment {
    private LinearLayout codeHeader;
    private LinearLayout addressHeader;
    private RobotoButton gButton;
    private RobotoButton mButton;
    private RobotoButton addressButton;
    private GridView codeGrid;
    private GridView addressGrid;
    private TableLayout codeTable;
    private List<String> li;
    private List<String> ad;
    private ArrayAdapter<String> codeAdapter;
    private ArrayAdapter<String> addressAdapter;
    private int dbSwitch;
    private static final String TEST_DEVICE_ID = "03f3f1d189532cca";
    private AdView adView;
    private AdRequest adRequest;

    DbHelper myDbHelper;


    public GMFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.g_m_codes, container, false);
        dbSwitch = 0;
        setAd(rootView);
        setLayout(rootView);
        setCodeAdapter();
        setAddressAdapter();
        setButtonListeners();
        new setList().execute();

        return rootView;
    }

    private void setLayout(View rootView) {
        codeHeader = (LinearLayout)rootView.findViewById(R.id.gm_codes_header);
        addressHeader = (LinearLayout)rootView.findViewById(R.id.gm_address_header);
        gButton = (RobotoButton)rootView.findViewById(R.id.gm_g_button);
        mButton = (RobotoButton)rootView.findViewById(R.id.gm_m_button);
        addressButton = (RobotoButton)rootView.findViewById(R.id.gm_address_button);
        codeGrid = (GridView)rootView.findViewById(R.id.gm_code_grid);
        addressGrid = (GridView)rootView.findViewById(R.id.gm_address_grid);
        codeTable = (TableLayout)rootView.findViewById(R.id.gm_code_table);
    }

    private void setButtonListeners() {
        gButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeHeader.setVisibility(View.VISIBLE);
                addressHeader.setVisibility(View.INVISIBLE);
                codeGrid.setVisibility(View.VISIBLE);
                addressGrid.setVisibility(View.INVISIBLE);
                dbSwitch = 0;
                new setList().execute();
                myDbHelper.close();
            }
        });
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeHeader.setVisibility(View.VISIBLE);
                addressHeader.setVisibility(View.INVISIBLE);
                codeGrid.setVisibility(View.VISIBLE);
                addressGrid.setVisibility(View.INVISIBLE);
                dbSwitch = 1;
                new setList().execute();
            }
        });
        addressButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeHeader.setVisibility(View.INVISIBLE);
                addressHeader.setVisibility(View.VISIBLE);
                codeGrid.setVisibility(View.INVISIBLE);
                addressGrid.setVisibility(View.VISIBLE);
                dbSwitch = 2;
                new setList().execute();
            }
        });
    }

    private void setAd(View rootView){
        adView = (AdView)rootView.findViewById(R.id.drill_adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(TEST_DEVICE_ID)
                .build();
        adView.loadAd(adRequest);
    }



    private void setCodeAdapter(){
        //instantiates the array list used for the adapter

        li = new ArrayList<String>();
        //the adapter, sets the list to the layout
        codeAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.grid_item_layout, li);
    }
    private void setAddressAdapter(){
        //instantiates the array list used for the adapter

        ad = new ArrayList<String>();
        //the adapter, sets the list to the layout
        addressAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.grid_item_layout, ad);
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

    private class setList extends AsyncTask {
        Cursor c;
        String code;
        String desc;
        String m;
        String t;

        @Override
        protected void onPreExecute(){
            Log.d("pre-execute", "executing");
            if(codeAdapter!=null){
                codeAdapter.clear();
            }
            if(addressAdapter!=null){
                addressAdapter.clear();
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
                    Log.d("do-inbackground", "case 1");
                    c = myDbHelper.getGCodes();
                    //createListAdapter(c);
                    break;
                case 1:
                    c = myDbHelper.getMCodes();
                    createListAdapter(c);
                    break;
                case 2:
                    c = myDbHelper.getAddCodes();
                    createAddressListAdapter(c);
                    break;
                default:
                    c = myDbHelper.getGCodes();
                    createListAdapter(c);
                    break;
            }


            Log.d("DB Thread", "Ending work");
            return null;
        }
        private void createListAdapter(Cursor c) {
            while (c.moveToNext()) {
                TableRow tableRow = new TableRow(getActivity());

                //ArrayList<Object> row = data.get(position);

                TextView text1 = new TextView(getActivity());
                text1.setText(c.getString(c.getColumnIndex("code")));
                tableRow.addView(text1);

                TextView textOne = new TextView(getActivity());
                textOne.setText(c.getString(c.getColumnIndex("desc")));
                tableRow.addView(textOne);

                TextView textTwo = new TextView(getActivity());
                textTwo.setText(c.getString(c.getColumnIndex("mill")));
                tableRow.addView(textTwo);

                TextView text4 = new TextView(getActivity());
                text4.setText(c.getString(c.getColumnIndex("turn")));
                tableRow.addView(text4);

                codeTable.addView(tableRow);
            }

/*            Log.d("create-list adapter", "doing");
            while (c.moveToNext()) {
                code = c.getString(c.getColumnIndex("code"));
                desc = c.getString(c.getColumnIndex("desc"));
                m = c.getString(c.getColumnIndex("mill"));
                t = c.getString(c.getColumnIndex("turn"));
                if(code != null) {
                    li.add(code);
                }else{
                    li.add("");
                }
                if(desc != null) {
                    li.add(desc);
                }else{
                    li.add("");
                }
                if(m != null) {
                    li.add(m);
                }else{
                    li.add("");
                }
                if(t != null) {
                    li.add(t);
                }else{
                    li.add("");
                }
            }*/
        }

        private void createAddressListAdapter(Cursor c) {
            while(c.moveToNext()) {
                code = c.getString(c.getColumnIndex("code"));
                desc = c.getString(c.getColumnIndex("desc"));
                ad.add(code);
                ad.add(desc);
            }
        }

        @Override
        protected void onPostExecute(Object result){
            createListAdapter(c);
            Log.d("post-execute", "setting");
            switch(dbSwitch){
                case 0:
                    Log.d("post-execute", "codeAdapter");
                    codeGrid.setAdapter(codeAdapter);
                    break;
                case 1:
                    codeGrid.setAdapter(codeAdapter);
                    break;
                case 2:
                    addressGrid.setAdapter(addressAdapter);
                    break;
            }
            c = null;
            myDbHelper = null;
            code = null;
            desc = null;
            m = null;
            t = null;
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
