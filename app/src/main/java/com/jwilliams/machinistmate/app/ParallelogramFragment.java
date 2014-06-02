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
 * Created by John on 5/12/2014.
 */
public class ParallelogramFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout inputLayout1;
    private LinearLayout inputLayout2;
    private RobotoTextView inputView1;
    private RobotoTextView inputView2;
    private RobotoTextView answer;
    private RobotoTextView precisionView;
    private EditText input1;
    private EditText input2;
    private Spinner paraSpinner;
    private Button calcButton;
    private Button addButton;
    private Button minusButton;
    private int setCalc;
    private int precision;
    private boolean check;
    public static Typeface tf;

    public ParallelogramFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.parallelogram_detail, container, false);

        initializeLayout(rootView);
        initialLayout();
        setSpinnerAdapter();
        setSpinnerListener();
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
                switch (setCalc) {
                    case 0:
                        calcArea();
                        break;
                    case 1:
                        calcBase();
                        break;
                    case 2:
                        calcHeight();
                        break;
                    case 3:
                        calcSide();
                        break;
                    case 4:
                        calcPerimeter();
                        break;
                    case 5:
                        calcX();
                        break;
                    case 6:
                        calcY();
                        break;
                }
            }
        });
    }

    private void calcY() {
        double x = 0.0;
        try{
            x = Double.parseDouble(input1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Calculations.formatter(180 - x, precision));
        }else{
            Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcX() {
        double y = 0.0;
        try{
            y = Double.parseDouble(input1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Calculations.formatter(180 - y, precision));
        }else{
            Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcPerimeter() {
        double a = 0.0;
        double b = 0.0;
        try{
            a = Double.parseDouble(input1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            b = Double.parseDouble(input2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Calculations.formatter(2 * (a + b), precision));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }

    }

    private void calcSide() {
        double p = 0.0;
        double b = 0.0;
        try{
            p = Double.parseDouble(input1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            b = Double.parseDouble(input2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Calculations.formatter((p / 2) - b, precision));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcHeight() {
        double a = 0.0;
        double b = 0.0;
        try{
            a = Double.parseDouble(input1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            b = Double.parseDouble(input2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Calculations.formatter(a / b, precision));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcBase() {
        double a = 0.0;
        double h = 0.0;
        try{
            a = Double.parseDouble(input1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            h = Double.parseDouble(input2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Calculations.formatter(a / h, precision));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcArea() {
        double b = 0.0;
        double h = 0.0;
        try{
            b = Double.parseDouble(input1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            h = Double.parseDouble(input2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Calculations.formatter(b * h, precision));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSpinnerListener() {
        AdapterView.OnItemSelectedListener paraSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initialLayout();
                setCalc = i;
                switch(i){
                    case 0:
                        inputView1.setText("Base (b)");
                        inputView2.setText("Height (h)");
                        break;
                    case 1:
                        inputView1.setText("Area");
                        inputView2.setText("Height (h)");
                        break;
                    case 2:
                        inputView1.setText("Area");
                        inputView2.setText("Base (b)");
                        break;
                    case 3:
                        inputView1.setText("Perimeter");
                        inputView2.setText("Base (b)");
                        break;
                    case 4:
                        inputView1.setText("Side (a)");
                        inputView2.setText("Base (b)");
                        break;
                    case 5:
                        inputView1.setText("Angle y");
                        inputLayout2.setVisibility(View.INVISIBLE);
                        break;
                    case 6:
                        inputView1.setText("Angle x");
                        inputLayout2.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        inputView1.setText("Base");
                        inputView2.setText("Height");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        paraSpinner.setOnItemSelectedListener(paraSelectedListener);
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<CharSequence> paraAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.para_calc_array, R.layout.spinner_background);
                paraAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        paraSpinner.setAdapter(paraAdapter);
    }

    private void initialLayout() {
        inputLayout1.setVisibility(View.VISIBLE);
        inputLayout2.setVisibility(View.VISIBLE);
        inputView1.setText("Base");
        inputView2.setText("Height");
    }

    private void initializeLayout(View rootView) {
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        inputLayout1 = (LinearLayout)rootView.findViewById(R.id.para_input1_layout);
        inputLayout2 = (LinearLayout)rootView.findViewById(R.id.para_layout2);
        inputView1 = (RobotoTextView)rootView.findViewById(R.id.para_view1);
        inputView2 = (RobotoTextView)rootView.findViewById(R.id.para_view2);
        precisionView = (RobotoTextView)rootView.findViewById(R.id.para_precision_view);
        answer = (RobotoTextView)rootView.findViewById(R.id.para_answer);
        input1 = (EditText)rootView.findViewById(R.id.para_input1);
        input2 = (EditText)rootView.findViewById(R.id.para_input2);
        paraSpinner = (Spinner)rootView.findViewById(R.id.para_spinner);
        calcButton = (Button)rootView.findViewById(R.id.para_calc_button);
        addButton = (Button)rootView.findViewById(R.id.para_add_button);
        minusButton = (Button)rootView.findViewById(R.id.para_minus_button);
        setCalc = 0;
        precision = 2;
        check = false;
        precisionView.setText(Integer.toString(precision));
        addButton.setTypeface(tf);
        minusButton.setTypeface(tf);
        calcButton.setTypeface(tf);
    }

    static ParallelogramFragment newInstance(int position) {
        ParallelogramFragment frag=new ParallelogramFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.parallelogram), position + 1));
    }
}
