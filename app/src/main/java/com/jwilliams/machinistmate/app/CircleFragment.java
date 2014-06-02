package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.graphics.Typeface;
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

import com.jwilliams.machinistmate.app.AppContent.Calculations;
import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;

/**
 * Created by John Williams on 5/20/2014.
 * Contents:
 * View-Controller for the circle calculations in the Geometry view-pager.
 */
public class CircleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout inputLayout;
    private LinearLayout radiusLayout;
    private EditText input1;
    private EditText radiusInput;
    private RobotoTextView view1;
    private RobotoTextView answer;
    private RobotoTextView precisionView;
    private Spinner answerSpinner;
    private Spinner radiusSpinner;
    private Button calcButton;
    private Button addButton;
    private Button minusButton;
    private  boolean check;
    private int pos;
    private int radiusChoice;
    private int precision;
    public static Typeface tf;


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
        setPrecisionListeners();

        return rootView;
    }

    private void setPrecisionListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(precision < 6) {
                    precision++;
                    precisionView.setText(Integer.toString(precision));
                }else{
                    Toast.makeText(getActivity(), "Max precision reached.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (precision > 1) {
                    precision--;
                    precisionView.setText(Integer.toString(precision));
                } else {
                    Toast.makeText(getActivity(), "You can't go down any farther.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setCalcListener() {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = false;
                switch (pos) {
                    case 0:
                        switch (radiusChoice) {
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
        double c = getRadiusInput();
        if(!check){
            answer.setText(Calculations.formatter(c / (2 * Math.PI), precision));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcRadArea() {
        double a = getRadiusInput();
        if(!check){
            answer.setText(Calculations.formatter(Math.sqrt(a / Math.PI), precision));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcRadDiam() {
        double d = getRadiusInput();
        if(!check){
            answer.setText(Calculations.formatter(d / 2, precision));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcCircumference() {
        double r = getInput1();
        if(!check){
            answer.setText(Calculations.formatter(2 * Math.PI * r, precision));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcArea() {
        double r = getInput1();
        if(!check){
            answer.setText(Calculations.formatter(Math.PI * Math.pow(r, 2), precision));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcDiameter() {
        double r = getInput1();
        if(!check){
            answer.setText(Calculations.formatter(2 * r, precision));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private double getRadiusInput(){
        double x = 0.0;
        try {
            x = Double.parseDouble(radiusInput.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        return x;
    }

    private double getInput1(){
        double x = 0.0;
        try {
            x = Double.parseDouble(input1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        return x;
    }

    private void initialLayout(){
        inputLayout.setVisibility(View.INVISIBLE);
        radiusLayout.setVisibility(View.VISIBLE);
    }

    private void setCircleSpinnerListener() {
        AdapterView.OnItemSelectedListener circleSpinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                if(i==0) {
                    radiusSpinner.setSelection(0);
                    initialLayout();
                }else{
                    view1.setText("Radius");
                    radiusLayout.setVisibility(View.INVISIBLE);
                    inputLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        answerSpinner.setOnItemSelectedListener(circleSpinnerListener);
    }

    private void setCircleSpinnerAdapter() {
        ArrayAdapter<CharSequence> circleAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.circle_calc_array, R.layout.spinner_background);
        circleAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        answerSpinner.setAdapter(circleAdapter);
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
        radiusSpinner.setOnItemSelectedListener(circleRadiusListener);
    }

    private void setCircleRadiusAdapter() {
        ArrayAdapter<CharSequence> circleRadiusAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.circle_radius_array, R.layout.spinner_background);
        circleRadiusAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        radiusSpinner.setAdapter(circleRadiusAdapter);
    }

    private void initializeLayout(View rootView) {
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        inputLayout = (LinearLayout)rootView.findViewById(R.id.c_layout1);
        radiusLayout = (LinearLayout)rootView.findViewById(R.id.circle_radius_layout);
        input1 = (EditText)rootView.findViewById(R.id.circle_input1);
        radiusInput = (EditText)rootView.findViewById(R.id.circle_radius_choice_input);
        view1 = (RobotoTextView)rootView.findViewById(R.id.circle_view1);
        precisionView = (RobotoTextView)rootView.findViewById(R.id.c_precision_view);
        answer = (RobotoTextView)rootView.findViewById(R.id.circle_answer);
        answerSpinner = (Spinner)rootView.findViewById(R.id.circle_choice);
        radiusSpinner = (Spinner)rootView.findViewById(R.id.circle_radius_choice);
        calcButton = (Button)rootView.findViewById(R.id.c_calc);
        addButton = (Button)rootView.findViewById(R.id.c_add_button);
        minusButton = (Button)rootView.findViewById(R.id.c_minus_button);
        check = false;
        pos = 0;
        radiusChoice = 0;
        precision = 2;
        precisionView.setText(Integer.toString(precision));
        addButton.setTypeface(tf);
        minusButton.setTypeface(tf);
        calcButton.setTypeface(tf);
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
