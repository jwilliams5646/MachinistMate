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
public class ParallelogramFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout paraInput1Layout;
    private LinearLayout paraInput2Layout;
    private RobotoTextView paraInputView1;
    private RobotoTextView paraInputView2;
    private RobotoTextView paraAnswer;
    private EditText paraInput1;
    private EditText paraInput2;
    private Spinner paraSpinner;
    private Button paraCalc;
    private int setCalc;
    private boolean check;

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

        return rootView;
    }

    private void setCalcListener() {
        paraCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = false;
                switch(setCalc){
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
            x = Double.parseDouble(paraInput1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            paraAnswer.setText(Double.toString(180-x));
        }else{
            Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcX() {
        double y = 0.0;
        try{
            y = Double.parseDouble(paraInput1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            paraAnswer.setText(Double.toString(180-y));
        }else{
            Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcPerimeter() {
        double a = 0.0;
        double b = 0.0;
        try{
            a = Double.parseDouble(paraInput1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            b = Double.parseDouble(paraInput2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            paraAnswer.setText(Double.toString(2*(a+b)));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }

    }

    private void calcSide() {
        double p = 0.0;
        double b = 0.0;
        try{
            p = Double.parseDouble(paraInput1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            b = Double.parseDouble(paraInput2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            paraAnswer.setText(Double.toString((p/2)-b));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcHeight() {
        double a = 0.0;
        double b = 0.0;
        try{
            a = Double.parseDouble(paraInput1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            b = Double.parseDouble(paraInput2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            paraAnswer.setText(Double.toString(a/b));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcBase() {
        double a = 0.0;
        double h = 0.0;
        try{
            a = Double.parseDouble(paraInput1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            h = Double.parseDouble(paraInput2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            paraAnswer.setText(Double.toString(a/h));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcArea() {
        double b = 0.0;
        double h = 0.0;
        try{
            b = Double.parseDouble(paraInput1.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            h = Double.parseDouble(paraInput2.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            paraAnswer.setText(Double.toString(b*h));
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
                        paraInputView1.setText("Base (b)");
                        paraInputView2.setText("Height (h)");
                        break;
                    case 1:
                        paraInputView1.setText("Area");
                        paraInputView2.setText("Height (h)");
                        break;
                    case 2:
                        paraInputView1.setText("Area");
                        paraInputView2.setText("Base (b)");
                        break;
                    case 3:
                        paraInputView1.setText("Perimeter");
                        paraInputView2.setText("Base (b)");
                        break;
                    case 4:
                        paraInputView1.setText("Side (a)");
                        paraInputView2.setText("Base (b)");
                        break;
                    case 5:
                        paraInputView1.setText("Angle y");
                        paraInput2Layout.setVisibility(View.INVISIBLE);
                        break;
                    case 6:
                        paraInputView1.setText("Angle x");
                        paraInput2Layout.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        paraInputView1.setText("Base");
                        paraInputView2.setText("Height");
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
        paraInput1Layout.setVisibility(View.VISIBLE);
        paraInput2Layout.setVisibility(View.VISIBLE);
        paraInputView1.setText("Base");
        paraInputView2.setText("Height");
    }

    private void initializeLayout(View rootView) {
        paraInput1Layout = (LinearLayout)rootView.findViewById(R.id.para_input1_layout);
        paraInput2Layout = (LinearLayout)rootView.findViewById(R.id.para_input2_layout);
        paraInputView1 = (RobotoTextView)rootView.findViewById(R.id.para_view1);
        paraInputView2 = (RobotoTextView)rootView.findViewById(R.id.para_view2);
        paraAnswer = (RobotoTextView)rootView.findViewById(R.id.para_answer);
        paraInput1 = (EditText)rootView.findViewById(R.id.para_input1);
        paraInput2 = (EditText)rootView.findViewById(R.id.para_input2);
        paraSpinner = (Spinner)rootView.findViewById(R.id.para_spinner);
        paraCalc = (Button)rootView.findViewById(R.id.para_calc_button);
        setCalc = 0;
        check = false;
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
