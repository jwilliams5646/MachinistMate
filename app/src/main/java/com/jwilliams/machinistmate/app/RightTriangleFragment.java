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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.jwilliams.machinistmate.app.AppContent.Calculations;
import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;


/**
 * Created by john.williams on 5/8/2014.
 */
public class RightTriangleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private ImageView triangle;
    private RobotoTextView rtAnswer;
    private RobotoTextView rtSide1;
    private RobotoTextView rtSide2;
    private RobotoTextView rtSide3;
    private Spinner rtSpinner;
    private EditText rtDegreeRadianInput;
    private EditText rtOppositeInput;
    private EditText rtHypotenuseInput;
    private EditText rtAdjacentInput;
    private EditText rtSide1Input;
    private EditText rtSide2Input;
    private EditText rtSide3Input;
    private Spinner rtDegreeRadianSpinner;
    private LinearLayout rtDegreeRadianLayout;
    private LinearLayout rtOppositeLayout;
    private LinearLayout rtHypotenuseLayout;
    private LinearLayout rtAdjacentLayout;
    private LinearLayout rtOrLayout;
    private LinearLayout rtSide1Layout;
    private LinearLayout rtSide2Layout;
    private LinearLayout rtSide3Layout;
    private RelativeLayout rtCalcButtonLayout;
    private int rtSpinnerPosition;
    private boolean isDegree;
    private Button rtCalcButton;
    private boolean degree;
    private boolean entry;
    RelativeLayout.LayoutParams params;

    static RightTriangleFragment newInstance(int position) {
        RightTriangleFragment frag=new RightTriangleFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.right_triangle_calc), position + 1));
    }


    public RightTriangleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.right_triangle_detail, container, false);

        setLayout(rootView);
        setSpinnerAdapters();

        AdapterView.OnItemSelectedListener rtItemSelectedListener = new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        rtSpinnerPosition = 0;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        rtOrLayout.setVisibility(View.VISIBLE);
                        rtOppositeLayout.setVisibility(View.VISIBLE);
                        rtHypotenuseLayout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_hypotenuse_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 1:
                        rtSpinnerPosition = 1;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_degree_radian_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 2:
                        rtSpinnerPosition = 2;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        rtOrLayout.setVisibility(View.VISIBLE);
                        rtOppositeLayout.setVisibility(View.VISIBLE);
                        rtHypotenuseLayout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_hypotenuse_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 3:
                        rtSpinnerPosition = 3;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_degree_radian_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 4:
                        rtSpinnerPosition = 4;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        rtOrLayout.setVisibility(View.VISIBLE);
                        rtOppositeLayout.setVisibility(View.VISIBLE);
                        rtAdjacentLayout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_adjacent_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 5:
                        rtSpinnerPosition = 5;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_degree_radian_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 6:
                        rtSpinnerPosition = 6;
                        clearLayouts();
                        rtSide1.setText("Base (A)");
                        rtSide2.setText("Height (O)");
                        rtSide1Layout.setVisibility(View.VISIBLE);
                        rtSide2Layout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_side2_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 7:
                        rtSpinnerPosition = 7;
                        clearLayouts();
                        rtSide1.setText("Side O");
                        rtSide2.setText("Side A");
                        rtSide1Layout.setVisibility(View.VISIBLE);
                        rtSide2Layout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_side2_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 8:
                        rtSpinnerPosition = 8;
                        clearLayouts();
                        rtSide1.setText("Side H");
                        rtSide2.setText("Side A");
                        rtSide1Layout.setVisibility(View.VISIBLE);
                        rtSide2Layout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_side2_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 9:
                        rtSpinnerPosition = 9;
                        clearLayouts();
                        rtSide1.setText("Side H");
                        rtSide2.setText("Side O");
                        rtSide1Layout.setVisibility(View.VISIBLE);
                        rtSide2Layout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_side2_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    case 10:
                        rtSpinnerPosition = 10;
                        clearLayouts();
                        rtSide1.setText("Side H");
                        rtSide2.setText("Side O");
                        rtSide3.setText("Side A");
                        rtSide1Layout.setVisibility(View.VISIBLE);
                        rtSide2Layout.setVisibility(View.VISIBLE);
                        rtSide3Layout.setVisibility(View.VISIBLE);
                        params.addRule(RelativeLayout.BELOW, R.id.rt_side3_layout);
                        rtCalcButtonLayout.setLayoutParams(params);
                        break;
                    default:
                        rtSpinnerPosition = 0;
                        clearLayouts();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rtSpinnerPosition = 0;
                clearLayouts();
            }
        };
        rtSpinner.setOnItemSelectedListener(rtItemSelectedListener);

        AdapterView.OnItemSelectedListener rtIsDegreeListener = new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        isDegree=true;
                        break;
                    case 1:
                        isDegree=false;
                        break;
                    default:
                        isDegree=true;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                isDegree=true;
            }
        };
        rtDegreeRadianSpinner.setOnItemSelectedListener(rtIsDegreeListener);

        rtCalcButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rtSpinnerPosition){
                    case 0:
                        calcSine();
                        break;
                    case 1:
                        calcInverseSine();
                        break;
                    case 2:
                        calcCos();
                        break;
                    case 3:
                        calcInverseCos();
                        break;
                    case 4:
                        calcTan();
                        break;
                    case 5:
                        calcInverseTan();
                        break;
                    case 6:
                        calcArea();
                        break;
                    case 7:
                        calcSideH();
                        break;
                    case 8:
                        calcSideO();
                        break;
                    case 9:
                        calcSideA();
                        break;
                    case 10:
                        calcPerimeter();
                        break;
                    default:
                        break;
                }
            }
        });
        return rootView;
    }

    private void calcPerimeter() {
        double sideH = getSide1();
        double sideO = getSide2();
        double sideA = getSide3();

        if(!entry){
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }else{
            rtAnswer.setText(Double.toString(Calculations.calcPerimeter(sideH, sideO, sideA)));
        }
        clearBools();
    }

    private void calcSideA() {
        double sideH = getSide1();
        double sideO = getSide2();

        if(entry){
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }else{
            rtAnswer.setText(Double.toString(Calculations.calcSideA(sideH, sideO)));
        }
        clearBools();
    }

    private void calcSideO() {
        double sideH = getSide1();
        double sideA = getSide2();

        if(entry){
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }else{
            rtAnswer.setText(Double.toString(Calculations.calcSideO(sideH, sideA)));
        }
        clearBools();
    }

    private void calcSideH() {
        double sideO = getSide1();
        double sideA = getSide2();

        if(entry){
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }else{
            rtAnswer.setText(Double.toString(Calculations.calcSideH(sideO, sideA)));
        }
        clearBools();
    }

    private void calcArea() {
        double base = getSide1();
        double height = getSide2();

        if(entry){
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }else{
            rtAnswer.setText(Double.toString(Calculations.calcArea(base, height)));
        }
        clearBools();
    }

    private void calcInverseTan() {
        double degreeRad = getDegreeRad();
        if(degree){
            clearBools();
            return;
        }
        if(isDegree) {
            rtAnswer.setText(Double.toString(Calculations.calcInvTanByDegree(degreeRad)));
        }else{
            rtAnswer.setText(Double.toString(Calculations.calcInvTanByRadian(degreeRad)));
        }
        clearBools();
    }

    private void calcTan() {
        double degreeRad = getDegreeRad();
        double opposite = getOpposite();
        double adjacent = getAdjacent();

        if(!degree && entry){
            if(isDegree) {
                rtAnswer.setText(Double.toString(Calculations.calcTanByDegree(degreeRad)));
            }else{
                rtAnswer.setText(Double.toString(Calculations.calcTanByRadian(degreeRad)));
            }
        }

        if(degree && !entry){
            rtAnswer.setText(Double.toString(Calculations.calcTanByValues(opposite, adjacent)));
        }

        if(degree && entry){
            Toast.makeText(getActivity(), "Please insert a Degree/Radian or Opposite and Hypotenuse", Toast.LENGTH_SHORT).show();
        }
        clearBools();
    }

    private void calcInverseCos() {
        double degreeRad = getDegreeRad();
        if (degree){
            clearBools();
            return;
        }
        if(isDegree) {
            rtAnswer.setText(Double.toString(Calculations.calcInvCosByDegree(degreeRad)));
        }else{
            rtAnswer.setText(Double.toString(Calculations.calcInvCosByRadian(degreeRad)));
        }
        clearBools();
    }

    private void calcCos() {
        double degreeRad = getDegreeRad();
        double opposite = getOpposite();
        double hypotenuse = getHypotenuse();

        if(!degree && entry){
            if(isDegree) {
                rtAnswer.setText(Double.toString(Calculations.calcCosByDegree(degreeRad)));
            }else{
                rtAnswer.setText(Double.toString(Calculations.calcCosByRadian(degreeRad)));
            }
        }

        if(degree && !entry){
            rtAnswer.setText(Double.toString(Calculations.calcCosByValues(opposite, hypotenuse)));
        }

        if(degree && entry){
            Toast.makeText(getActivity(), "Please insert a Degree/Radian or Opposite and Hypotenuse", Toast.LENGTH_SHORT).show();
        }
        clearBools();
    }

    private void calcInverseSine() {
        double degreeRad = getDegreeRad();
        if(degree){
            clearBools();
            return;
        }
        if(isDegree) {
            rtAnswer.setText(Double.toString(Calculations.calcInvSinByDegree(degreeRad)));
        }else{
            rtAnswer.setText(Double.toString(Calculations.calcInvSinByRadian(degreeRad)));
        }
        clearBools();
    }

    private void calcSine() {
        double degreeRad = getDegreeRad();
        double opposite = getOpposite();
        double hypotenuse = getHypotenuse();

        if(!degree && entry){
            if(isDegree) {
                rtAnswer.setText(Double.toString(Calculations.calcSinByDegree(degreeRad)));
            }else{
                rtAnswer.setText(Double.toString(Calculations.calcSinByRadian(degreeRad)));
            }
        }

        if(degree && !entry){
            rtAnswer.setText(Double.toString(Calculations.calcSinByValues(opposite, hypotenuse)));
        }

        if(degree && entry){
            Toast.makeText(getActivity(), "Please insert a Degree/Radian or Opposite and Hypotenuse", Toast.LENGTH_SHORT).show();
        }
        clearBools();
    }

    private double getHypotenuse() {
        double hypotenuse = 0.0;
        try{
            hypotenuse = Double.parseDouble(rtHypotenuseInput.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(getActivity(), "hypotenuse invalid", Toast.LENGTH_SHORT).show();
            entry =true;
        }
        return hypotenuse;
    }
    private double getOpposite(){
        double opposite = 0.0;
        try{
            opposite = Double.parseDouble(rtOppositeInput.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(getActivity(), "opposite invalid", Toast.LENGTH_SHORT).show();
            entry =true;
        }
        return opposite;
    }

    private double getAdjacent() {
        double adjacent = 0.0;
        try{
            adjacent = Double.parseDouble(rtAdjacentInput.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(getActivity(), "adjacent invalid", Toast.LENGTH_SHORT).show();
            entry =true;
        }
        return adjacent;
    }

    private double getDegreeRad() {
        double degreeRad = 0.0;
        try{
            degreeRad = Double.parseDouble(rtDegreeRadianInput.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(getActivity(), "degree is bad", Toast.LENGTH_SHORT).show();
            degree=true;
        }
        return degreeRad;
    }

    private double getSide1() {
        double side1 = 0.0;
        try{
            side1 = Double.parseDouble(rtSide1Input.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(getActivity(), "side 1 invalid", Toast.LENGTH_SHORT).show();
            entry =true;
        }
        return side1;
    }

    private double getSide2() {
        double side2 = 0.0;
        try{
            side2 = Double.parseDouble(rtSide2Input.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(getActivity(), "side 2 invalid", Toast.LENGTH_SHORT).show();
            entry =true;
        }
        return side2;
    }

    private double getSide3() {
        double side3 = 0.0;
        try{
            side3 = Double.parseDouble(rtSide3Input.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(getActivity(), "side 3 invalid", Toast.LENGTH_SHORT).show();
            entry =true;
        }
        return side3;
    }

    private void setSpinnerAdapters() {
        ArrayAdapter<CharSequence> rtAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.rt_array, R.layout.spinner_background);
        rtAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        rtSpinner.setAdapter(rtAdapter);

        ArrayAdapter<CharSequence> rtDRAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.rt_degree_rad_array, R.layout.spinner_background);
        rtDRAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        rtDegreeRadianSpinner.setAdapter(rtDRAdapter);
    }

    private void setLayout(View rootView) {
        triangle = (ImageView)rootView.findViewById(R.id.right_triangle_imageView);
        rtAnswer = (RobotoTextView)rootView.findViewById(R.id.rt_answer);
        rtSide1 = (RobotoTextView)rootView.findViewById(R.id.rt_side1_view);
        rtSide2 = (RobotoTextView)rootView.findViewById(R.id.rt_side2_view);
        rtSide3 = (RobotoTextView)rootView.findViewById(R.id.rt_side3_view);
        rtSpinner = (Spinner)rootView.findViewById(R.id.rt_spinner);
        rtDegreeRadianInput = (EditText)rootView.findViewById(R.id.rt_degree_radian_input);
        rtOppositeInput = (EditText)rootView.findViewById(R.id.rt_opposite_input);
        rtHypotenuseInput = (EditText)rootView.findViewById(R.id.rt_hypotenuse_input);
        rtAdjacentInput = (EditText)rootView.findViewById(R.id.rt_adjacent_input);
        rtSide1Input = (EditText)rootView.findViewById(R.id.rt_side1_input);
        rtSide2Input = (EditText)rootView.findViewById(R.id.rt_side2_input);
        rtSide3Input = (EditText)rootView.findViewById(R.id.rt_side3_input);
        rtDegreeRadianSpinner = (Spinner)rootView.findViewById(R.id.rt_degree_radian_spinner);
        rtDegreeRadianLayout = (LinearLayout)rootView.findViewById(R.id.rt_degree_radian_layout);
        rtOppositeLayout = (LinearLayout)rootView.findViewById(R.id.rt_opposite_layout);
        rtHypotenuseLayout = (LinearLayout)rootView.findViewById(R.id.rt_hypotenuse_layout);
        rtAdjacentLayout = (LinearLayout)rootView.findViewById(R.id.rt_adjacent_layout);
        rtOrLayout = (LinearLayout)rootView.findViewById(R.id.rt_or_layout);
        rtSide1Layout = (LinearLayout)rootView.findViewById(R.id.rt_side1_layout);
        rtSide2Layout = (LinearLayout)rootView.findViewById(R.id.rt_side2_layout);
        rtSide3Layout = (LinearLayout)rootView.findViewById(R.id.rt_side3_layout);
        rtCalcButton = (Button)rootView.findViewById(R.id.rt_calc);
        rtCalcButtonLayout = (RelativeLayout)rootView.findViewById(R.id.rt_calc_button_layout);
        params = (RelativeLayout.LayoutParams)rtCalcButtonLayout.getLayoutParams();
        degree = false;
        entry = false;
    }
    private void clearLayouts(){
        rtDegreeRadianLayout.setVisibility(View.INVISIBLE);
        rtOppositeLayout.setVisibility(View.INVISIBLE);
        rtHypotenuseLayout.setVisibility(View.INVISIBLE);
        rtAdjacentLayout.setVisibility(View.INVISIBLE);
        rtOrLayout.setVisibility(View.INVISIBLE);
        rtSide1Layout.setVisibility(View.INVISIBLE);
        rtSide2Layout.setVisibility(View.INVISIBLE);
        rtSide3Layout.setVisibility(View.INVISIBLE);
    }
    private void clearBools(){
        degree = false;
        entry = false;
    }
}
