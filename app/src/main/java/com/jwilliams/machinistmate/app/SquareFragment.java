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
public class SquareFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout inputLayout1;
    private LinearLayout sideInputLayout;
    private EditText input1;
    private EditText sideInput;
    private RobotoTextView view1;
    private RobotoTextView precisionView;
    private RobotoTextView answer;
    private Spinner answerChoice;
    private Spinner sideChoice;
    private Button addButton;
    private Button minusButton;
    private Button calcButton;
    private int precision;
    private int sidePos;
    private int answerPos;
    private boolean check;
    public static Typeface tf;

    public SquareFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.square_detail, container, false);

        initializeLayout(rootView);
        setAnswerChoiceAdapter();
        setSideChoiceAdapter();
        setAnswerChoiceListener();
        setSideChoiceListener();
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
                if(precision > 1) {
                    precision--;
                    precisionView.setText(Integer.toString(precision));
                }else{
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
                switch(answerPos){
                    case 0:
                        calcArea();
                        break;
                    case 1:
                        calcDiagonal();
                        break;
                    case 2:
                        switch(sidePos){
                            case 0:
                                calcSideByArea();
                                break;
                            case 1:
                                calcSideByDiagonal();
                                break;
                            case 2:
                                calcSideByPerimeter();
                                break;
                            }
                        break;
                    case 3:
                        calcPerimeter();
                        break;
                }
            }

            private void calcPerimeter() {
                double s = getInput();

                if(!check){
                    answer.setText(Calculations.formatter(s*4,precision));
                }else{
                    Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
            }

            private void calcSideByPerimeter() {
                double p = getSideInput();

                if(!check){
                    answer.setText(Calculations.formatter(p/4, precision));
                }else{
                    Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
            }

            private void calcSideByDiagonal() {
                double d = getSideInput();

                if(!check){
                    answer.setText(Calculations.formatter(Math.sqrt(2)*(d/2),precision));
                }else{
                    Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
            }

            private void calcSideByArea() {
                double a = getSideInput();

                if(!check){
                    answer.setText(Calculations.formatter(Math.sqrt(a), precision));
                }else{
                    Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
            }

            private void calcDiagonal() {
                double s = getInput();

                if(!check){
                    answer.setText(Calculations.formatter(Math.sqrt(2)*s,precision));
                }else{
                    Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
            }

            private void calcArea() {
                double s = getInput();

                if(!check){
                    answer.setText(Calculations.formatter(s*s, precision));
                }else{
                    Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private double getInput(){
        double x = 0.0;
        try{
            x = Double.parseDouble(input1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }
        return x;
    }

    private double getSideInput(){
        double x = 0.0;
        try{
            x = Double.parseDouble(sideInput.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }
        return x;

    }

    private void setSideChoiceListener() {
        sideChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sidePos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}});
    }

    private void setAnswerChoiceListener() {
        answerChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                answerPos = i;
                clearInput();
                switch(i){
                    case 0:
                        setBasicLayout();
                        break;
                    case 1:
                        setBasicLayout();
                        break;
                    case 2:
                        setSideLayout();
                        break;
                    case 3:
                        setBasicLayout();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}});
    }

    private void setSideChoiceAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.square_side_array, R.layout.spinner_background);
        adapter.setDropDownViewResource(R.layout.spinner_drop_down);
        sideChoice.setAdapter(adapter);
    }

    private void setAnswerChoiceAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.square_calc_array, R.layout.spinner_background);
        adapter.setDropDownViewResource(R.layout.spinner_drop_down);
        answerChoice.setAdapter(adapter);
    }

    private void initializeLayout(View rootView) {
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        inputLayout1 = (LinearLayout)rootView.findViewById(R.id.sq_layout1);
        sideInputLayout = (LinearLayout)rootView.findViewById(R.id.sq_side_layout);
        input1 = (EditText)rootView.findViewById(R.id.sq_input1);
        sideInput = (EditText)rootView.findViewById(R.id.sq_side_input);
        precisionView = (RobotoTextView)rootView.findViewById(R.id.sq_precision_view);
        view1 = (RobotoTextView)rootView.findViewById(R.id.sq_view1);
        answer = (RobotoTextView)rootView.findViewById(R.id.sq_answer);
        answerChoice = (Spinner)rootView.findViewById(R.id.sq_choice);
        sideChoice = (Spinner)rootView.findViewById(R.id.sq_side_choice);
        calcButton = (Button)rootView.findViewById(R.id.sq_calc);
        addButton = (Button)rootView.findViewById(R.id.sq_add_button);
        minusButton = (Button)rootView.findViewById(R.id.sq_minus_button);
        sidePos = 0;
        answerPos = 0;
        precision = 2;
        check = false;
        precisionView.setText(Integer.toString(precision));
        addButton.setTypeface(tf);
        minusButton.setTypeface(tf);
        calcButton.setTypeface(tf);
        setBasicLayout();
    }

    private void setBasicLayout() {
        view1.setText("Side (s)");
        sideInputLayout.setVisibility(View.INVISIBLE);
        inputLayout1.setVisibility(View.VISIBLE);
    }

    private void setSideLayout(){
        sideInputLayout.setVisibility(View.VISIBLE);
        inputLayout1.setVisibility(View.INVISIBLE);
    }

    private void clearInput(){
        input1.setText("");
        sideInput.setText("");
    }

    static SquareFragment newInstance(int position) {
        SquareFragment frag=new SquareFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.square), position + 1));
    }

}
