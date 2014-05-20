package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * Created by john.williams on 5/19/2014.
 */
public class RightTriangleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout rtDegRadLayout;
    private LinearLayout rtDegRadSideLayout;
    private LinearLayout rtInput1Layout;
    private LinearLayout rtInput2Layout;
    private LinearLayout rtInput3Layout;
    private RobotoTextView rtAnswer;
    private RobotoTextView rtInput1view;
    private RobotoTextView rtInput2view;
    private RobotoTextView rtInput3view;
    private EditText rtDegRadValInput;
    private EditText rtDegRadInput;
    private EditText rtInput1;
    private EditText rtInput2;
    private EditText rtInput3;
    private Spinner rtCalcChoice;
    private Spinner rtDegRadValChoice;
    private Spinner rtDegRadChoice;
    private Button rtCalc;
    private boolean check;
    private int choice;
    private int choice2;
    private int choice3;



    public RightTriangleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.right_triangle, container, false);
        initializeLayout(rootView);
        setRtChoiceSpinnerAdapter();
        setDegRadValSpinnerAdapter();
        setDegRadSpinnerAdapter();
        setRtChoiceSpinnerListeners();
        setRtDegRadValSpinnerListeners();
        setRtDegRadListener();
        setCalcListener();
        return rootView;
    }

    private void setCalcListener() {
        rtCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                switch(choice){
                    case 0:
                        switch(choice2) {
                            case 0:
                                calcSinDegree();
                                Log.d("Sin Degree"," choice 0, choice2 0");
                                break;
                            case 1:
                                calcSinRadian();
                                Log.d("Sin Radian", " choice 0, choice2 1");
                                break;
                            case 2:
                                calcSinValue();
                                Log.d("Sin Value", " choice 0, choice2 2");
                                break;
                        }
                        break;
                    case 1:
                        if(choice3 ==0) {
                            calcInvSinDeg();
                            Log.d("InvSin Degree", " choice 1, choice3 0");
                        }
                        if(choice3 == 1) {
                            calcInvSinRad();
                            Log.d("InvSin Radian", " choice 1, choice3 1");
                        }
                        break;
                    case 2:
                        switch(choice2) {
                            case 0:
                                calcCosDegree();
                                Log.d("Cos Degree", " choice 2, choice2 0");
                                break;
                            case 1:
                                calcCosRadian();
                                Log.d("Cos Radian", " choice 2, choice2 1");
                                break;
                            case 2:
                                calcCosValue();
                                Log.d("Cos Value", " choice 2, choice2 2");
                                break;
                        }
                        break;
                    case 3:
                        if(choice3 ==0) {
                            calcInvCosDeg();
                            Log.d("InvCos Degree", " choice 3, choice3 0");
                        }
                        if(choice3 == 1) {
                            calcInvCosRad();
                            Log.d("InvCos Radian", " choice 0, choice3 1");
                        }
                        break;
                    case 4:
                        switch(choice2) {
                            case 0:
                                calcTanDegree();
                                Log.d("Tan Degree", " choice 4, choice2 0");
                                break;
                            case 1:
                                calcTanRadian();
                                Log.d("Tan Radian", " choice 4, choice2 1");
                                break;
                            case 2:
                                calcTanValue();
                                Log.d("Tan Value", " choice 4, choice2 2");
                                break;
                        }
                        break;
                    case 5:
                        if(choice3 ==0) {
                            calcInvTanDeg();
                            Log.d("InvTan Degree", " choice 5, choice3 0");
                        }
                        if(choice3 == 1) {
                            calcInvTanRad();
                            Log.d("InvTan Radian", " choice 5, choice3 1");
                        }
                        break;
                    case 6:
                        calcArea();
                        Log.d("Area", " choice 6");
                        break;
                    case 7:
                        calcSideH();
                        Log.d("SideH", " choice 7");
                        break;
                    case 8:
                        calcSideO();
                        Log.d("SideO", " choice 8");
                        break;
                    case 9:
                        calcSideA();
                        Log.d("SideA", " choice 9");
                        break;
                    case 10:
                        calcPerimeter();
                        Log.d("Perimeter", " choice 10");
                        break;
                }
            }
        });
    }

    private void calcSinDegree() {
        double d = 0.0;
        try{
            d = Double.parseDouble(rtDegRadValInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.sin(Math.toRadians(d))));
        }else{
            singleValToast();
        }
    }

    private void calcSinRadian() {
        double r = 0.0;
        try{
            r = Double.parseDouble(rtDegRadValInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.sin(r)));
        }else{
            singleValToast();
        }
    }

    private void calcSinValue() {
        double o = 0.0;
        double h = 0.0;

        try{
            o = Double.parseDouble(rtInput2.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        try{
            h = Double.parseDouble(rtInput3.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(o/h));
        }else{
            doubleValToast();
        }
    }

    private void calcInvSinDeg() {
        double d = 0.0;
        try{
            d = Double.parseDouble(rtDegRadInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.toDegrees(Math.asin(d))));
        }else{
            singleValToast();
        }
    }

    private void calcInvSinRad() {
        double r = 0.0;
        try{
            r = Double.parseDouble(rtDegRadInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.asin(r)));
        }else{
            singleValToast();
        }
    }

    private void calcCosDegree() {
        double d = 0.0;
        try{
            d = Double.parseDouble(rtDegRadValInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.cos(Math.toRadians(d))));
        }else{
            singleValToast();
        }
    }

    private void calcCosRadian() {
        double r = 0.0;
        try{
            r = Double.parseDouble(rtDegRadValInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.cos(r)));
        }else{
            singleValToast();
        }
    }

    private void calcCosValue() {
        double a = 0.0;
        double h = 0.0;
        try{
            a = Double.parseDouble(rtInput2.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        try{
            h = Double.parseDouble(rtInput3.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(a/h));
        }else{
            doubleValToast();
        }
    }

    private void calcInvCosDeg() {
        double d = 0.0;
        try{
            d = Double.parseDouble(rtDegRadInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.toDegrees(Math.acos(d))));
        }else{
            singleValToast();
        }
    }

    private void calcInvCosRad() {
        double r = 0.0;
        try{
            r = Double.parseDouble(rtDegRadInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.acos(r)));
        }else{
            singleValToast();
        }
    }

    private void calcTanDegree() {
        double d = 0.0;
        try{
            d = Double.parseDouble(rtDegRadValInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.tan(Math.toRadians(d))));
        }else{
            singleValToast();
        }
    }

    private void calcTanRadian() {
        double r = 0.0;
        try{
            r = Double.parseDouble(rtDegRadValInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.tan(r)));
        }else{
            singleValToast();
        }
    }

    private void calcTanValue() {
        double o = 0.0;
        double a = 0.0;

        try{
            o = Double.parseDouble(rtInput2.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        try{
            a = Double.parseDouble(rtInput3.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(o/a));
        }else{
            doubleValToast();
        }
    }

    private void calcInvTanDeg() {
        double d = 0.0;
        try{
            d = Double.parseDouble(rtDegRadInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.toDegrees(Math.atan(d))));
        }else{
            singleValToast();
        }
    }

    private void calcInvTanRad() {
        double r = 0.0;
        try{
            r = Double.parseDouble(rtDegRadInput.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.atan(r)));
        }else{
            singleValToast();
        }
    }

    private void calcArea() {
        double a = 0.0;
        double o = 0.0;
        try{
            a = Double.parseDouble(rtInput1.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        try{
            o = Double.parseDouble(rtInput2.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString((o*a)/2));
        }else{
            doubleValToast();
        }
    }

    private void calcSideH() {
        double o = 0.0;
        double a = 0.0;
        try{
            o = Double.parseDouble(rtInput1.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        try{
            a = Double.parseDouble(rtInput2.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.sqrt(Math.pow(o,2)+Math.pow(a,2))));
        }else{
            doubleValToast();
        }
    }

    private void calcSideO() {
        double h = 0.0;
        double a = 0.0;
        try{
            h = Double.parseDouble(rtInput1.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        try{
            a = Double.parseDouble(rtInput2.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.sqrt(Math.pow(h,2)-Math.pow(a,2))));
        }else{
            doubleValToast();
        }
    }

    private void calcSideA() {
        double h = 0.0;
        double o = 0.0;
        try{
            h = Double.parseDouble(rtInput1.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        try{
            o = Double.parseDouble(rtInput2.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(Math.sqrt(Math.pow(h,2)-Math.pow(o,2))));
        }else{
            doubleValToast();
        }
    }

    private void calcPerimeter() {
        double h = 0.0;
        double o = 0.0;
        double a = 0.0;
        try{
            h = Double.parseDouble(rtInput1.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        try{
            o = Double.parseDouble(rtInput2.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        try{
            a = Double.parseDouble(rtInput3.getText().toString());
        }catch (NumberFormatException e){
            check = true;
        }
        if(!check){
            rtAnswer.setText(Double.toString(h+o+a));
        }else{
            doubleValToast();
        }
    }

    private void singleValToast(){
        Toast.makeText(getActivity(), "Invalid input", Toast.LENGTH_SHORT).show();
    }

    private void doubleValToast(){
        Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
    }

    private void setRtDegRadListener() {
        AdapterView.OnItemSelectedListener rtDegRadListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                choice3 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        rtDegRadChoice.setOnItemSelectedListener(rtDegRadListener);
    }

    private void setRtDegRadValSpinnerListeners() {
        AdapterView.OnItemSelectedListener rtDegRadSideListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                choice2 = i;
                switch (i) {
                    case 0:
                        rtDegRadValInput.setVisibility(View.VISIBLE);
                        rtInput2Layout.setVisibility(View.INVISIBLE);
                        rtInput3Layout.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        rtDegRadValInput.setVisibility(View.VISIBLE);
                        rtInput2Layout.setVisibility(View.INVISIBLE);
                        rtInput3Layout.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        if (choice == 0) {
                            rtDegRadValInput.setVisibility(View.INVISIBLE);
                            rtInput2view.setText("(O)pposite");
                            rtInput3view.setText("(H)ypotenuse");
                            rtInput2Layout.setVisibility(View.VISIBLE);
                            rtInput3Layout.setVisibility(View.VISIBLE);
                        }
                        if (choice == 2) {
                            rtDegRadValInput.setVisibility(View.INVISIBLE);
                            rtInput2view.setText("(A)djacent");
                            rtInput3view.setText("(H)ypotenuse");
                            rtInput2Layout.setVisibility(View.VISIBLE);
                            rtInput3Layout.setVisibility(View.VISIBLE);
                        }
                        if (choice == 4) {
                            rtDegRadValInput.setVisibility(View.INVISIBLE);
                            rtInput2view.setText("(O)pposite");
                            rtInput3view.setText("(A)djacent");
                            rtInput2Layout.setVisibility(View.VISIBLE);
                            rtInput3Layout.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        rtDegRadValChoice.setOnItemSelectedListener(rtDegRadSideListener);
    }

    private void setRtChoiceSpinnerListeners() {
        AdapterView.OnItemSelectedListener rtChoiceListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                clearLayouts();
                choice = i;
                switch(i){
                    case 0:
                        setInitialLayout();
                        rtDegRadValChoice.setSelection(0);
                        break;
                    case 1:
                        rtDegRadChoice.setSelection(0);
                        rtDegRadSideLayout.setVisibility(View.INVISIBLE);
                        rtDegRadLayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setInitialLayout();
                        rtDegRadValChoice.setSelection(0);
                        break;
                    case 3:
                        rtDegRadChoice.setSelection(0);
                        rtDegRadSideLayout.setVisibility(View.INVISIBLE);
                        rtDegRadLayout.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        setInitialLayout();
                        rtDegRadValChoice.setSelection(0);
                        break;
                    case 5:
                        rtDegRadChoice.setSelection(0);
                        rtDegRadSideLayout.setVisibility(View.INVISIBLE);
                        rtDegRadLayout.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        rtInput1view.setText("Base (A)");
                        rtInput2view.setText("Height (O)");
                        rtInput1Layout.setVisibility(View.VISIBLE);
                        rtInput2Layout.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        rtInput1view.setText("Side (O)");
                        rtInput2view.setText("Side (A)");
                        rtInput1Layout.setVisibility(View.VISIBLE);
                        rtInput2Layout.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        rtInput1view.setText("Side (H)");
                        rtInput2view.setText("Side (A)");
                        rtInput1Layout.setVisibility(View.VISIBLE);
                        rtInput2Layout.setVisibility(View.VISIBLE);
                        break;
                    case 9:
                        rtInput1view.setText("Side (H)");
                        rtInput2view.setText("Side (O)");
                        rtInput1Layout.setVisibility(View.VISIBLE);
                        rtInput2Layout.setVisibility(View.VISIBLE);
                        break;
                    case 10:
                        rtInput1view.setText("Side (H)");
                        rtInput2view.setText("Side (O)");
                        rtInput3view.setText("Side (A)");
                        rtInput1Layout.setVisibility(View.VISIBLE);
                        rtInput2Layout.setVisibility(View.VISIBLE);
                        rtInput3Layout.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        rtCalcChoice.setOnItemSelectedListener(rtChoiceListener);
    }
    private void setDegRadSpinnerAdapter() {
        ArrayAdapter<CharSequence> rtDegRadAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.degree_rad_array, R.layout.spinner_background);
        rtDegRadAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        rtDegRadChoice.setAdapter(rtDegRadAdapter);
    }

    private void setDegRadValSpinnerAdapter() {
        ArrayAdapter<CharSequence> rtDegRadValAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.degree_rad_value_array, R.layout.spinner_background);
        rtDegRadValAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        rtDegRadValChoice.setAdapter(rtDegRadValAdapter);
    }

    private void setRtChoiceSpinnerAdapter() {
        ArrayAdapter<CharSequence> rtAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.rt_array, R.layout.spinner_background);
        rtAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        rtCalcChoice.setAdapter(rtAdapter);
    }

    private void initializeLayout(View rootView) {
        rtDegRadLayout = (LinearLayout)rootView.findViewById(R.id.rt_degree_radian_layout);
        rtDegRadSideLayout = (LinearLayout)rootView.findViewById(R.id.rt_choice2_layout);
        rtInput1Layout = (LinearLayout)rootView.findViewById(R.id.rt_input1_layout);
        rtInput2Layout = (LinearLayout)rootView.findViewById(R.id.rt_input2_layout);
        rtInput3Layout = (LinearLayout)rootView.findViewById(R.id.rt_input3_layout);
        rtAnswer = (RobotoTextView)rootView.findViewById(R.id.rt_answer);
        rtInput1view = (RobotoTextView)rootView.findViewById(R.id.rt_input1_view);
        rtInput2view = (RobotoTextView)rootView.findViewById(R.id.rt_input2_view);
        rtInput3view = (RobotoTextView)rootView.findViewById(R.id.rt_input3_view);
        rtDegRadValInput = (EditText)rootView.findViewById(R.id.rt_degree_radian_value_input);
        rtDegRadInput = (EditText)rootView.findViewById(R.id.rt_degree_radian_input);
        rtInput1 = (EditText)rootView.findViewById(R.id.rt_input1);
        rtInput2 = (EditText)rootView.findViewById(R.id.rt_input2);
        rtInput3 = (EditText)rootView.findViewById(R.id.rt_input3);
        rtCalcChoice = (Spinner)rootView.findViewById(R.id.rt_spinner);
        rtDegRadValChoice = (Spinner)rootView.findViewById(R.id.rt_degree_radian_value_spinner);
        rtDegRadChoice = (Spinner)rootView.findViewById(R.id.rt_degree_radian_spinner);
        rtCalc = (Button)rootView.findViewById(R.id.rt_calc_button);
        check = false;
        choice = 0;
        choice2 = 0;
        choice3 = 0;
        setInitialLayout();
    }

    private void setInitialLayout() {
        rtDegRadValInput.setVisibility(View.VISIBLE);
        rtDegRadSideLayout.setVisibility(View.VISIBLE);
        rtInput1Layout.setVisibility(View.INVISIBLE);
        rtInput2Layout.setVisibility(View.INVISIBLE);
        rtInput3Layout.setVisibility(View.INVISIBLE);
    }
    private void clearLayouts(){
        rtDegRadLayout.setVisibility(View.INVISIBLE);
        rtDegRadValInput.setVisibility(View.INVISIBLE);
        rtDegRadSideLayout.setVisibility(View.INVISIBLE);
        rtInput1Layout.setVisibility(View.INVISIBLE);
        rtInput2Layout.setVisibility(View.INVISIBLE);
        rtInput3Layout.setVisibility(View.INVISIBLE);
    }

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
}
