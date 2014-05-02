package com.jwilliams.machinistmate.app;

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

import com.jwilliams.machinistmate.app.AppContent.Calculations;

/**
 * Created by John on 4/6/2014.
 */
public class ConversionDetailFragment extends Fragment {

    //public static final String ARG_ITEM_ID = "item_id";
    public double calcInput = 0.0;
    TextView convAnswer;
    TextView convAnswerType;
    Spinner convInputSpinner;
    Spinner convOutputSpinner;
    EditText convInput;
    Button convCalcButton;
    LinearLayout convAnswerLayout;
    int inputSpinner;
    int outputSpinner;

    public ConversionDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.conversion_item_detail, container, false);
        assert rootView != null;

        setLayoutVariables(rootView);
        setTwoPane();
        setSpinnerAdapter();

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

        View.OnClickListener convCalcButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convCalculation();
            }
        };

        convCalcButton.setOnClickListener(convCalcButtonListener);

        return rootView;
    }

    private void setLayoutVariables(View rootView){
        convAnswer = (TextView) rootView.findViewById(R.id.conv_answer);
        convAnswerType = (TextView) rootView.findViewById(R.id.conv_answer_type);
        convInputSpinner = (Spinner) rootView.findViewById(R.id.conv_input_spinner);
        convOutputSpinner = (Spinner) rootView.findViewById(R.id.conv_output_spinner);
        convInput = (EditText) rootView.findViewById(R.id.conv_input);
        convCalcButton = (Button) rootView.findViewById(R.id.conv_calc_button);
        convAnswerLayout = (LinearLayout) rootView.findViewById(R.id.conv_answer_layout);
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

    private void setConversionType(int position){

        switch (position) {
            case 0:
                convAnswerType.setText("in");
                break;
            case 1:
                convAnswerType.setText("ft");
                break;
            case 2:
                convAnswerType.setText("yd");
                break;
            case 3:
                convAnswerType.setText("mm");
                break;
            case 4:
                convAnswerType.setText("cm");
                break;
            case 5:
                convAnswerType.setText("m");
                break;
            default:
                convAnswerType.setText("in");
                break;
        }
    }

    private void convCalculation() {
        try {
            calcInput = Double.parseDouble(convInput.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
            return;
        }
        convAnswer.setText(Double.toString(
                Calculations.conversionCalc(inputSpinner, outputSpinner, calcInput)));
    }
}
