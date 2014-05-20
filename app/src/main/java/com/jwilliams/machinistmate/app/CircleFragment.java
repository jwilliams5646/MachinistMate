package com.jwilliams.machinistmate.app;

import android.content.Context;
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
import android.widget.Toast;

import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;

/**
 * Created by john.williams on 5/20/2014.
 */
public class CircleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout circleInput1Layout;
    private LinearLayout circleRadiusLayout;
    private EditText circleInput1;
    private EditText circleRadiusInput;
    private RobotoTextView circleView1;
    private RobotoTextView circleAnswer;
    private Spinner circleSpinner;
    private Spinner circleRadiusSpinner;
    private Button circleCalcButton;
    private  boolean check;
    private int pos;
    private int radiusChoice;


    public CircleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.circle_detail, container, false);
        initializeLayout(rootView);
        initialLayout();
        setCircleSpinnerAdapter();
        setCircleRadiusAdapter();
        setCircleSpinnerListener();
        setCircleRadiusListener();
        setCalcListener();

        return rootView;
    }

    private void setCalcListener() {
        circleCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = false;
                switch (pos) {
                    case 0:
                        switch(radiusChoice){
                            case 0:
                                calcRadDiam();
                                break;
                            case 1:
                                calcRadArea();
                                break;
                            case 2:
                                calcRadCirc();
                                break;
                        }
                        break;
                    case 1:
                        calcDiameter();
                        break;
                    case 2:
                        calcArea();
                        break;
                    case 3:
                        calcCircumference();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void calcRadCirc() {
        double c = 0.0;
        try {
            c = Double.parseDouble(circleRadiusInput.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }

        if(!check){
            circleAnswer.setText(Double.toString(c/(2*Math.PI)));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcRadArea() {
        double a = 0.0;
        try {
            a = Double.parseDouble(circleRadiusInput.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }

        if(!check){
            circleAnswer.setText(Double.toString(Math.sqrt(a/Math.PI)));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcRadDiam() {
        double d = 0.0;
        try {
            d = Double.parseDouble(circleRadiusInput.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }

        if(!check){
            circleAnswer.setText(Double.toString(d/2));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcCircumference() {
        double r = 0.0;
        try {
            r = Double.parseDouble(circleInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }

        if(!check){
            circleAnswer.setText(Double.toString(2*Math.PI*r));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcArea() {
        double r = 0.0;
        try {
            r = Double.parseDouble(circleInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }

        if(!check){
            circleAnswer.setText(Double.toString(Math.PI*Math.pow(r,2)));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcDiameter() {
        double r = 0.0;
        try {
            r = Double.parseDouble(circleInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }

        if(!check){
            circleAnswer.setText(Double.toString(2*r));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialLayout(){
        circleInput1Layout.setVisibility(View.INVISIBLE);
        circleRadiusLayout.setVisibility(View.VISIBLE);
    }

    private void setCircleSpinnerListener() {
        AdapterView.OnItemSelectedListener circleSpinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                if(i==0) {
                    circleRadiusSpinner.setSelection(0);
                    initialLayout();
                }else{
                    circleView1.setText("Radius");
                    circleRadiusLayout.setVisibility(View.INVISIBLE);
                    circleInput1Layout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        circleSpinner.setOnItemSelectedListener(circleSpinnerListener);
    }

    private void setCircleSpinnerAdapter() {
        ArrayAdapter<CharSequence> circleAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.circle_calc_array, R.layout.spinner_background);
        circleAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        circleSpinner.setAdapter(circleAdapter);
    }

    private void setCircleRadiusListener(){
        AdapterView.OnItemSelectedListener circleRadiusListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                radiusChoice = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private void setCircleRadiusAdapter() {
        ArrayAdapter<CharSequence> circleRadiusAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.circle_radius_array, R.layout.spinner_background);
        circleRadiusAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        circleRadiusSpinner.setAdapter(circleRadiusAdapter);
    }

    private void initializeLayout(View rootView) {
        circleInput1Layout = (LinearLayout)rootView.findViewById(R.id.circle_input1_layout);
        circleRadiusLayout = (LinearLayout)rootView.findViewById(R.id.circle_radius_layout);
        circleInput1 = (EditText)rootView.findViewById(R.id.circle_input1);
        circleRadiusInput = (EditText)rootView.findViewById(R.id.circle_radius_choice_input);
        circleView1 = (RobotoTextView)rootView.findViewById(R.id.circle_view1);
        circleAnswer = (RobotoTextView)rootView.findViewById(R.id.circle_answer);
        circleSpinner = (Spinner)rootView.findViewById(R.id.circle_choice);
        circleRadiusSpinner = (Spinner)rootView.findViewById(R.id.circle_radius_choice);
        circleCalcButton = (Button)rootView.findViewById(R.id.circle_calc);
        check = false;
        pos = 0;
        radiusChoice = 0;
    }

    static CircleFragment newInstance(int position) {
        CircleFragment frag=new CircleFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.circle), position + 1));
    }
}
