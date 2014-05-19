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
 * Created by John on 5/12/2014.
 */
public class TrapezoidFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private Spinner trapChoice;
    private LinearLayout input1Layout;
    private LinearLayout input2Layout;
    private LinearLayout input3Layout;
    private LinearLayout input4Layout;
    private RobotoTextView input1View;
    private RobotoTextView input2View;
    private RobotoTextView input3View;
    private RobotoTextView input4View;
    private RobotoTextView answer;
    private EditText input1;
    private EditText input2;
    private EditText input3;
    private EditText input4;
    private Button calcButton;
    private boolean check;
    private int choice;


    public TrapezoidFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.trapezoid_detail, container, false);
        initializeLayout(rootView);
        setSpinnerAdapter();
        setSpinnerListener();
        setCalcListener();
        return rootView;
    }

    private void setCalcListener() {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(choice){
                    case 0:
                        calcArea();
                        break;
                    case 1:
                        calcBaseA();
                        break;
                    case 2:
                        calcBaseB();
                        break;
                    case 3:
                        calcSideC();
                        break;
                    case 4:
                        calcSideD();
                        break;
                    case 5:
                        calcHeight();
                        break;
                    case 6:
                        calcMedian();
                        break;
                    case 7:
                        calcPerimter();
                        break;
                    default:
                        calcArea();
                        break;
                }
            }
        });
    }

    private void calcPerimter() {
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double d = 0.0;

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

        try{
            c = Double.parseDouble(input3.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            d = Double.parseDouble(input4.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Double.toString(a+b+c+d));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcMedian() {
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
            answer.setText(Double.toString((a+b)/2));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcHeight() {
        double a = 0.0;
        double b = 0.0;
        double A = 0.0;

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

        try{
            A = Double.parseDouble(input3.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Double.toString(2*(A/(a+b))));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcSideD() {
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double p = 0.0;

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

        try{
            c = Double.parseDouble(input3.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            p = Double.parseDouble(input4.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Double.toString(p-a-b-c));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcSideC() {
        double a = 0.0;
        double b = 0.0;
        double d = 0.0;
        double p = 0.0;

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

        try{
            d = Double.parseDouble(input3.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        try{
            p = Double.parseDouble(input4.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Double.toString(p-a-b-d));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcBaseB() {
        double a = 0.0;
        double h = 0.0;
        double A = 0.0;

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

        try{
            A = Double.parseDouble(input3.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Double.toString(2*(A/h)-a));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcBaseA() {
        double b = 0.0;
        double h = 0.0;
        double A = 0.0;

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

        try{
            A = Double.parseDouble(input3.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Double.toString(2*(A/h)-b));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcArea() {
        double a = 0.0;
        double b = 0.0;
        double h = 0.0;

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

        try{
            h = Double.parseDouble(input3.getText().toString());
        }catch(NumberFormatException e){
            check = true;
        }

        if(!check){
            answer.setText(Double.toString(((a+b)/2)*h));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are missing or invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSpinnerListener() {
        AdapterView.OnItemSelectedListener trapSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choice = i;
                switch(i){
                    case 0:
                        clearLayouts();
                        setInitialLayout();
                        set3Layouts();
                        break;
                    case 1:
                        clearLayouts();
                        input1View.setText("Base (b)");
                        input2View.setText("Height (h)");
                        input3View.setText("Area");
                        set3Layouts();
                        break;
                    case 2:
                        clearLayouts();
                        input1View.setText("Base (a)");
                        input2View.setText("Height (h)");
                        input3View.setText("Area");
                        set3Layouts();
                        break;
                    case 3:
                        clearLayouts();
                        input1View.setText("Base (a)");
                        input2View.setText("Base (b)");
                        input3View.setText("Side (d)");
                        input4View.setText("Perimeter");
                        set4Layouts();
                        break;
                    case 4:
                        clearLayouts();
                        input1View.setText("Base (a)");
                        input2View.setText("Base (b)");
                        input3View.setText("Side (c)");
                        input4View.setText("Perimeter");
                        set4Layouts();
                        break;
                    case 5:
                        clearLayouts();
                        input1View.setText("Base (a)");
                        input2View.setText("Base (b)");
                        input3View.setText("Area");
                        set3Layouts();
                        break;
                    case 6:
                        clearLayouts();
                        input1View.setText("Base (a)");
                        input2View.setText("Base (b)");
                        input1Layout.setVisibility(View.VISIBLE);
                        input2Layout.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        clearLayouts();
                        input1View.setText("Base (a)");
                        input2View.setText("Base (b)");
                        input3View.setText("Side (c)");
                        input4View.setText("Side (d)");
                        set4Layouts();
                        break;
                    default:
                        clearLayouts();
                        setInitialLayout();
                        set3Layouts();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        trapChoice.setOnItemSelectedListener(trapSelectedListener);
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<CharSequence> trapAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.trap_calc_array, R.layout.spinner_background);
        trapAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        trapChoice.setAdapter(trapAdapter);
    }

    private void clearLayouts(){
        input1Layout.setVisibility(View.INVISIBLE);
        input2Layout.setVisibility(View.INVISIBLE);
        input3Layout.setVisibility(View.INVISIBLE);
        input4Layout.setVisibility(View.INVISIBLE);
    }

    private void set3Layouts(){
        input1Layout.setVisibility(View.VISIBLE);
        input2Layout.setVisibility(View.VISIBLE);
        input3Layout.setVisibility(View.VISIBLE);
        input4Layout.setVisibility(View.INVISIBLE);
    }

    private void set4Layouts(){
        input1Layout.setVisibility(View.VISIBLE);
        input2Layout.setVisibility(View.VISIBLE);
        input3Layout.setVisibility(View.VISIBLE);
        input4Layout.setVisibility(View.VISIBLE);
    }

    private void initializeLayout(View rootView) {
        trapChoice = (Spinner)rootView.findViewById(R.id.trap_spinner);
        input1Layout =(LinearLayout)rootView.findViewById(R.id.trap_input1_layout);
        input2Layout =(LinearLayout)rootView.findViewById(R.id.trap_input2_layout);
        input3Layout =(LinearLayout)rootView.findViewById(R.id.trap_input3_layout);
        input4Layout =(LinearLayout)rootView.findViewById(R.id.trap_input4_layout);
        input1View = (RobotoTextView)rootView.findViewById(R.id.trap_input1_view);
        input2View = (RobotoTextView)rootView.findViewById(R.id.trap_input2_view);
        input3View = (RobotoTextView)rootView.findViewById(R.id.trap_input3_view);
        input4View = (RobotoTextView)rootView.findViewById(R.id.trap_input4_view);
        answer = (RobotoTextView)rootView.findViewById(R.id.trap_answer);
        input1 = (EditText)rootView.findViewById(R.id.trap_input1);
        input2 = (EditText)rootView.findViewById(R.id.trap_input2);
        input3 = (EditText)rootView.findViewById(R.id.trap_input3);
        input4 = (EditText)rootView.findViewById(R.id.trap_input4);
        calcButton = (Button)rootView.findViewById(R.id.trap_calc_button);
        check = false;
        choice = 0;
        setInitialLayout();
    }

    private void setInitialLayout() {
        input1View.setText("Base (a)");
        input2View.setText("Base (b)");
        input3View.setText("Height (h)");
    }

    static TrapezoidFragment newInstance(int position) {
        TrapezoidFragment frag=new TrapezoidFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.trapezoid), position + 1));
    }
}
