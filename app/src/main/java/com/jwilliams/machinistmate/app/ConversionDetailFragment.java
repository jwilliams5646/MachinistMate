package com.jwilliams.machinistmate.app;

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

import com.jwilliams.machinistmate.app.AppContent.LengthdbHelper;
import com.jwilliams.machinistmate.app.AppContent.Utility;

import java.io.IOException;

/**
 * Created by John Williams
 * Manages the ConversionDetailFragment Lifecycle
 */
public class ConversionDetailFragment extends Fragment {
    //Conversion Variables
    public double calcInput = 0.0;
    private TextView convAnswer;
    private TextView convAnswerType;
    private Spinner convInputSpinner;
    private Spinner convOutputSpinner;
    private Spinner convPrecisionSpinner;
    private EditText convInput;
    private Button convCalcButton;
    private LinearLayout convAnswerLayout;
    int inputSpinner;
    int outputSpinner;
    int precSpinner;
    public static Typeface tf;
    private String output;

    public ConversionDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.conversion_item_detail, container, false);

        setLayoutVariables(rootView);
        setTwoPane();
        setSpinnerAdapter();
        setPrecisionAdapter();

        AdapterView.OnItemSelectedListener convInputSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> spinner, View container,
                                       int position, long id) {
                inputSpinner = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                inputSpinner = 0;
            }
        };

        AdapterView.OnItemSelectedListener convOutputSelectedListener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> spinner, View container,
                                       int position, long id) {
                outputSpinner = position;
                setConversionType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                outputSpinner = 0;
                convAnswerType.setText("in");
            }
        };

        convInputSpinner.setOnItemSelectedListener(convInputSelectedListener);
        convOutputSpinner.setOnItemSelectedListener(convOutputSelectedListener);

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

        View.OnClickListener convCalcButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new getCalculation().execute();
            }
        };

        convCalcButton.setOnClickListener(convCalcButtonListener);

        return rootView;
    }

    private void setLayoutVariables(View rootView){
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        convAnswer = (TextView) rootView.findViewById(R.id.conv_answer);
        convAnswerType = (TextView) rootView.findViewById(R.id.conv_answer_type);
        convInputSpinner = (Spinner) rootView.findViewById(R.id.conv_input_spinner);
        convOutputSpinner = (Spinner) rootView.findViewById(R.id.conv_output_spinner);
        convPrecisionSpinner = (Spinner)rootView.findViewById(R.id.conv_prec_spinner);
        convInput = (EditText) rootView.findViewById(R.id.conv_input);
        convCalcButton = (Button) rootView.findViewById(R.id.conv_calc_button);
        convAnswerLayout = (LinearLayout) rootView.findViewById(R.id.conv_answer_layout);
        convCalcButton.setTypeface(tf);
    }

    private void setTwoPane(){
        if (ItemListActivity.mTwoPane) {
            final float SCALE = getActivity().getResources().getDisplayMetrics().density;
            int topMargin = (int) (SpeedsDetailFragment.TOP_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            int x = (int) (SpeedsDetailFragment.OTHER_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams) convAnswerLayout.getLayoutParams();
            assert params != null;
            params.setMargins(x, topMargin, x, x);
            convAnswerLayout.setLayoutParams(params);
        }
    }

    private void setSpinnerAdapter(){
        ArrayAdapter<CharSequence> convAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.conversions_array, R.layout.spinner_background);
        convAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        convInputSpinner.setAdapter(convAdapter);
        convOutputSpinner.setAdapter(convAdapter);
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
                convAnswerType.setText("in");
                output = "inch";
                break;
            case 1:
                convAnswerType.setText("ft");
                output = "feet";
                break;
            case 2:
                convAnswerType.setText("yd");
                output = "yard";
                break;
            case 3:
                convAnswerType.setText("mm");
                output = "millimeter";
                break;
            case 4:
                convAnswerType.setText("cm");
                output = "centimeter";
                break;
            case 5:
                convAnswerType.setText("m");
                output = "meter";
                break;
            default:
                convAnswerType.setText("in");
                output = "inch";
                break;
        }
    }

    private void setDatabase(LengthdbHelper myDbHelper){
        //instantiates the database and the
        myDbHelper = new LengthdbHelper(getActivity());
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
    }

    public void openDb(LengthdbHelper myDbHelper){
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            Log.d("Open Database","SQL Exception");
            throw sqle;
        }
    }

    private class getCalculation extends AsyncTask{
        @Override
        protected void onPreExecute(){
            try {
                calcInput = Double.parseDouble(convInput.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
                cancel(true);
            }
        }

        @Override
        protected Double doInBackground(Object[] params) {
            Log.d("DB Thread", "Starting work");
            LengthdbHelper myDbHelper = new LengthdbHelper(getActivity());
            setDatabase(myDbHelper);
            openDb(myDbHelper);
            Cursor c = myDbHelper.getConversionFactor(inputSpinner, output);
            c.moveToFirst();
            Double result = Utility.formatter(calcInput *
                    Double.parseDouble(c.getString(c.getColumnIndex(output))), precSpinner);
            myDbHelper.close();
            Log.d("DB Thread", "Ending work");
            return result;
        }

        @Override
        protected void onPostExecute(Object result){
            convAnswer.setText(result.toString());
        }
    }
}
