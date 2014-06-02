package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.jwilliams.machinistmate.app.AppContent.DbHelper;
import com.jwilliams.machinistmate.app.AppContent.Utility;

import java.io.IOException;

/**
 * Created by John Williams
 * Manages the LengthFragment Lifecycle
 */
public class LengthFragment extends Fragment {
    //Conversion Variables

    private static final String KEY_POSITION="position";
    private TextView answer;
    private TextView answerType;
    private Spinner inputSpinner;
    private Spinner outputSpinner;
    private Spinner convPrecisionSpinner;
    private EditText input;
    private Button calcButton;
    private LinearLayout answerLayout;
    int inputPos;
    int outputPos;
    int precSpinner;
    public static Typeface tf;
    private String output;

    public LengthFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.conversion_layout, container, false);

        setLayoutVariables(rootView);
        setTwoPane();
        setSpinnerAdapter();
        setPrecisionAdapter();
        setInputListener();
        setOutputListener();
        setCalcListener();

        AdapterView.OnItemSelectedListener convPrecisionSelectedListener = new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> spinner, View container,
                                       int position, long id) {
                precSpinner = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                precSpinner = 1;
            }
        };

        convPrecisionSpinner.setOnItemSelectedListener(convPrecisionSelectedListener);

        return rootView;
    }

    private void setCalcListener() {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new getCalculation().execute();
            }
        });
    }

    private void setOutputListener() {
        outputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                outputPos = i;
                setConversionType(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setInputListener() {
        inputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inputPos = i;
                setInputHint(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setInputHint(int position) {
        switch (position) {
            case 0:
                input.setHint("in");
                break;
            case 1:
                input.setHint("ft");
                break;
            case 2:
                input.setHint("yd");
                break;
            case 3:
                input.setHint("mm");
                break;
            case 4:
                input.setHint("cm");
                break;
            case 5:
                input.setHint("m");
                break;
            default:
                input.setHint("in");
                break;
        }
    }

    private void setLayoutVariables(View rootView){
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        answer = (TextView) rootView.findViewById(R.id.conv_answer);
        answerType = (TextView) rootView.findViewById(R.id.conv_answer_type);
        inputSpinner = (Spinner) rootView.findViewById(R.id.conv_input_spinner);
        outputSpinner = (Spinner) rootView.findViewById(R.id.conv_output_spinner);
        convPrecisionSpinner = (Spinner)rootView.findViewById(R.id.conv_prec_spinner);
        input = (EditText) rootView.findViewById(R.id.conv_input);
        calcButton = (Button) rootView.findViewById(R.id.conv_calc_button);
        answerLayout = (LinearLayout) rootView.findViewById(R.id.conv_answer_layout);
        calcButton.setTypeface(tf);
        inputPos = 0;
        outputPos = 0;
    }

    private void setTwoPane(){
        if (ItemListActivity.mTwoPane) {
            final float SCALE = getActivity().getResources().getDisplayMetrics().density;
            int topMargin = (int) (SpeedsDetailFragment.TOP_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            int x = (int) (SpeedsDetailFragment.OTHER_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams) answerLayout.getLayoutParams();
            assert params != null;
            params.setMargins(x, topMargin, x, x);
            answerLayout.setLayoutParams(params);
        }
    }

    private void setSpinnerAdapter(){
        ArrayAdapter<CharSequence> convAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.conversions_array, R.layout.spinner_background);
        convAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        inputSpinner.setAdapter(convAdapter);
        outputSpinner.setAdapter(convAdapter);
    }

    private void setPrecisionAdapter(){
        ArrayAdapter<CharSequence> precAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.precision_array, R.layout.spinner_background);
        precAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        convPrecisionSpinner.setAdapter(precAdapter);
    }

    private void setConversionType(int position){
        switch (position) {
            case 0:
                answerType.setText("in");
                output = "inch";
                break;
            case 1:
                answerType.setText("ft");
                output = "feet";
                break;
            case 2:
                answerType.setText("yd");
                output = "yard";
                break;
            case 3:
                answerType.setText("mm");
                output = "millimeter";
                break;
            case 4:
                answerType.setText("cm");
                output = "centimeter";
                break;
            case 5:
                answerType.setText("m");
                output = "meter";
                break;
            default:
                answerType.setText("in");
                output = "inch";
                break;
        }
    }

    private void setDatabase(DbHelper myDbHelper){
        //instantiates the database and the
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
            Log.d("Open Database","SQL Exception");
            throw sqle;
        }
    }

    private class getCalculation extends AsyncTask{
        private double calcInput = 0.0;

        @Override
        protected void onPreExecute(){
            try {
                calcInput = Double.parseDouble(input.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
                cancel(true);
            }
        }

        @Override
        protected Double doInBackground(Object[] params) {
            Log.d("DB Thread", "Starting work");
            DbHelper myDbHelper = new DbHelper(getActivity());
            setDatabase(myDbHelper);
            openDb(myDbHelper);
            Cursor c = myDbHelper.getConversionFactor(inputPos, output);
            c.moveToFirst();
            Double result = Utility.formatter(calcInput *
                    Double.parseDouble(c.getString(c.getColumnIndex(output))), precSpinner);
            myDbHelper.close();
            Log.d("DB Thread", "Ending work");
            return result;
        }

        @Override
        protected void onPostExecute(Object result){
            answer.setText(result.toString());
        }
    }

    static LengthFragment newInstance(int position) {
        LengthFragment frag=new LengthFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.length), position + 1));
    }
}
