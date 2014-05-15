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
 * Created by John on 5/12/2014.
 */
public class CircleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout circleInput2Layout;
    private LinearLayout circleOrLayout;
    private LinearLayout circleInput3Layout;
    private LinearLayout circleOr1Layout;
    private EditText circleInput1;
    private EditText circleInput2;
    private EditText circleInput3;
    private RobotoTextView circleView1;
    private RobotoTextView circleView2;
    private RobotoTextView circleView3;
    private RobotoTextView circleAnswer;
    private Spinner circleSpinner;
    private Button circleCalcButton;
    private  boolean check1;
    private  boolean check2;
    private  boolean check3;
    private int pos;


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
        setSpinnerAdapter();
        setSpinnerListener();
        setCalcListener();

        return rootView;
    }

    private void setCalcListener() {
        circleCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBools();
                switch (pos) {
                    case 0:
                        calcRadius();
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
                        calcRadius();
                        break;
                }
            }
        });
    }

    private void calcCircumference() {
        double r = 0.0;
        try {
            r = Double.parseDouble(circleInput1.getText().toString());
        } catch (NumberFormatException e) {
            check1 = true;
        }

        if(!check1){
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
            check1 = true;
        }

        if(!check1){
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
            check1 = true;
        }

        if(!check1){
            circleAnswer.setText(Double.toString(2*r));
        }else{
            Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcRadius() {
        double d = 0.0;
        double a = 0.0;
        double c = 0.0;
        try {
            d = Double.parseDouble(circleInput1.getText().toString());
        } catch (NumberFormatException e) {
            check1 = true;
        }
        try{
            a = Double.parseDouble(circleInput2.getText().toString());
        }catch (NumberFormatException e){
            check2=true;
        }
        try{
            c = Double.parseDouble(circleInput3.getText().toString());
        }catch (NumberFormatException e){
            check3=true;
        }
        if(!check1 && check2 && check3) {
            circleAnswer.setText(Double.toString(d/2));
        }
        if(check1 && !check2 && check3) {
            circleAnswer.setText(Double.toString(Math.sqrt(a/Math.PI)));
        }
        if(check1 && check2 && !check3) {
            circleAnswer.setText(Double.toString(c/(2*Math.PI)));
        }
        if(check1 && check2 && check3){
            Toast.makeText(getActivity(), "Please enter 1 valid input", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Please enter only 1 valid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void setBools(){
        check1=false;
        check2=false;
        check3=false;
    }

    private void initialLayout(){
        circleInput2Layout.setVisibility(View.VISIBLE);
        circleOrLayout.setVisibility(View.VISIBLE);
        circleInput3Layout.setVisibility(View.VISIBLE);
        circleOr1Layout.setVisibility(View.VISIBLE);
        circleView1.setText("Diameter");
        circleView2.setText("Area");
        circleView3.setText("Circumference");
    }

    private void setSpinnerListener() {
        AdapterView.OnItemSelectedListener circleSpinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                if(i==0) {
                    initialLayout();
                }else{
                    circleInput2Layout.setVisibility(View.INVISIBLE);
                    circleOrLayout.setVisibility(View.INVISIBLE);
                    circleInput3Layout.setVisibility(View.INVISIBLE);
                    circleOr1Layout.setVisibility(View.INVISIBLE);
                    circleView1.setText("Radius");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        circleSpinner.setOnItemSelectedListener(circleSpinnerListener);
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<CharSequence> circleAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.circle_calc_array, R.layout.spinner_background);
        circleAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        circleSpinner.setAdapter(circleAdapter);
    }

    private void initializeLayout(View rootView) {
        circleInput2Layout = (LinearLayout)rootView.findViewById(R.id.circle_input2_layout);
        circleOrLayout = (LinearLayout)rootView.findViewById(R.id.circle_or_layout);
        circleInput3Layout = (LinearLayout)rootView.findViewById(R.id.circle_input3_layout);
        circleOr1Layout = (LinearLayout)rootView.findViewById(R.id.circle_or1_layout);
        circleInput1 = (EditText)rootView.findViewById(R.id.circle_input1);
        circleInput2 = (EditText)rootView.findViewById(R.id.circle_input2);
        circleInput3 = (EditText)rootView.findViewById(R.id.circle_input3);
        circleView1 = (RobotoTextView)rootView.findViewById(R.id.circle_view1);
        circleView2 = (RobotoTextView)rootView.findViewById(R.id.circle_view2);
        circleView3 = (RobotoTextView)rootView.findViewById(R.id.circle_view3);
        circleAnswer = (RobotoTextView)rootView.findViewById(R.id.circle_answer);
        circleSpinner = (Spinner)rootView.findViewById(R.id.circle_choice);
        circleCalcButton = (Button)rootView.findViewById(R.id.circle_calc);
        setBools();
        pos = 0;
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
