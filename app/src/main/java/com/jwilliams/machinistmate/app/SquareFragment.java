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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;

/**
 * Created by John on 5/12/2014.
 */
public class SquareFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout squareInput2Layout;
    private EditText squareInput1;
    private EditText squareInput2;
    private RobotoTextView squareView1;
    private RobotoTextView squareView2;
    private RobotoTextView squareAnswer;
    private Spinner squareRectChoice;
    private Spinner squareRectCalcChoice;
    private Button squareRectCalcButton;
    private int squareRect;
    private int calcChoice;
    private RelativeLayout.LayoutParams orParams;
    private RelativeLayout.LayoutParams input2Params;
    private LinearLayout orLayout;
    private LinearLayout input2Layout;
    private boolean check;
    private boolean check2;

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

        setInitialLayout(rootView);
        setSquareRectSpinnerAdapter();
        setCalcChoicesSpinnerAdapter(0);
        setSquareRectChoiceListener();
        setSquareRectCalcListener();
        setSquareCalcListener();

        return rootView;

    }

    private void setSquareCalcListener() {
        squareRectCalcButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBools();
                if (squareRect == 0) {
                    squareCalc();
                } else {
                    rectCalc();
                }
            }
        });
    }

    private void rectCalc() {
        switch(calcChoice){
            case 0:
                calcRectArea();
                break;
            case 1:
                calcRectDiagonal();
                break;
            case 2:
                calcRectWidth();
                break;
            case 3:
                calcRectLength();
                break;
            case 4:
                calcRectPerimeter();
                break;
            default:
                break;
        }
    }

    private void calcRectPerimeter() {
        double w = 0.0;
        double l = 0.0;
        try {
            w = Double.parseDouble(squareInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        try{
            l = Double.parseDouble(squareInput2.getText().toString());
        }catch (NumberFormatException e){
            check=true;
        }
        if(!check) {
            squareAnswer.setText(Double.toString((w*2)+(l*2)));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcRectLength() {
        double w = 0.0;
        double d = 0.0;
        try {
            w = Double.parseDouble(squareInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        try{
            d = Double.parseDouble(squareInput2.getText().toString());
        }catch (NumberFormatException e){
            check=true;
        }
        if(!check) {
            squareAnswer.setText(Double.toString(Math.sqrt((Math.pow(d,2)- Math.pow(w,2)))));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcRectWidth() {
        double l = 0.0;
        double d = 0.0;
        try {
            l = Double.parseDouble(squareInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        try{
            d = Double.parseDouble(squareInput2.getText().toString());
        }catch (NumberFormatException e){
            check=true;
        }
        if(!check) {
            squareAnswer.setText(Double.toString(Math.sqrt((Math.pow(d,2)- Math.pow(l,2)))));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcRectDiagonal() {
        double w = 0.0;
        double l = 0.0;
        try {
            w = Double.parseDouble(squareInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        try{
            l = Double.parseDouble(squareInput2.getText().toString());
        }catch (NumberFormatException e){
            check=true;
        }
        if(!check) {
            squareAnswer.setText(Double.toString(Math.sqrt((Math.pow(w,2)+ Math.pow(l,2)))));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcRectArea() {
        double w = 0.0;
        double l = 0.0;
        try {
            w = Double.parseDouble(squareInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        try{
            l = Double.parseDouble(squareInput2.getText().toString());
        }catch (NumberFormatException e){
            check=true;
        }
        if(!check) {
            squareAnswer.setText(Double.toString(w*l));
        }else{
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void squareCalc() {
        switch(calcChoice){
            case 0:
                calcSquareArea();
                break;
            case 1:
                calcSquareDiagonal();
                break;
            case 2:
                calcSquareSide();
                break;
            case 3:
                calSquarePerimeter();
                break;
            default:
                break;
        }

    }

    private void calSquarePerimeter() {
        double wl = 0.0;
        try {
            wl = Double.parseDouble(squareInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        if(!check){
            squareAnswer.setText(Double.toString(wl*4));
        }else{
            Toast.makeText(getActivity(), "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcSquareSide() {
        double wl = 0.0;
        double p = 0.0;
        try {
            wl = Double.parseDouble(squareInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        try{
            p = Double.parseDouble(squareInput2.getText().toString());
        }catch (NumberFormatException e){
            check2=true;
        }
        if(!check && check2) {
            squareAnswer.setText(Double.toString(wl * 4));
        }
        if(check && !check2) {
            squareAnswer.setText(Double.toString(p/4));
        }
        if(check && check2){
            Toast.makeText(getActivity(), "Please enter diagonal or perimeter", Toast.LENGTH_SHORT).show();
        }
        if(!check && !check2){
            Toast.makeText(getActivity(), "Please enter diagonal OR perimeter", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcSquareDiagonal() {
        double wl = 0.0;
        try {
            wl = Double.parseDouble(squareInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        if(!check){
            squareAnswer.setText(Double.toString(Math.sqrt(2)*wl));
        }else{
            Toast.makeText(getActivity(), "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcSquareArea() {
        double wl = 0.0;
        try {
            wl = Double.parseDouble(squareInput1.getText().toString());
        } catch (NumberFormatException e) {
            check = true;
        }
        if(!check){
            squareAnswer.setText(Double.toString(Math.pow(wl,2)));
        }else{
            Toast.makeText(getActivity(), "Invalid input", Toast.LENGTH_SHORT).show();
        }

    }

    private void setSquareRectCalcListener() {
        AdapterView.OnItemSelectedListener squareRectCalcChoiceSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calcChoice = i;
                clearInput2Layout();
                switch(squareRect){
                    case 0:
                        resetLayout();
                        setSquareText();
                        break;
                    case 1:
                        resetLayout();
                        setRectText();
                        break;
                    default:
                        resetLayout();
                        setSquareText();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                calcChoice = 0;
                showInput2Layout();
                setSquareText();
            }
        };
        squareRectCalcChoice.setOnItemSelectedListener(squareRectCalcChoiceSelectedListener);
    }

    private void setRectText() {

        switch(calcChoice) {
            case 0:
                setAreaText(squareRect);
                break;
            case 1:
                setDiagonalText(squareRect);
                break;
            case 2:
                setWidthText();
                break;
            case 3:
                setLengthText();
                break;
            case 4:
                setPerimeterText(squareRect);
                break;
            default:
                setAreaText(squareRect);
                break;
        }
    }

    private void setSquareText() {
        switch(calcChoice) {
            case 0:
                setAreaText(squareRect);
                break;
            case 1:
                setDiagonalText(squareRect);
                break;
            case 2:
                setSideText();
                break;
            case 3:
                setPerimeterText(squareRect);
                break;
            default:
                setAreaText(squareRect);
                break;
        }
    }

    private void setSquareRectChoiceListener() {
        AdapterView.OnItemSelectedListener squareChoiceSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                squareRect = i;
                setCalcChoicesSpinnerAdapter(i);
                setAreaText(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                squareRect = 0;
                setCalcChoicesSpinnerAdapter(0);
            }
        };

        squareRectChoice.setOnItemSelectedListener(squareChoiceSelectedListener);
    }

    private void setInitialLayout(View rootView) {
        squareInput2Layout = (LinearLayout)rootView.findViewById(R.id.square_input2_layout);
        squareInput1 = (EditText)rootView.findViewById(R.id.square_input1);
        squareInput2 = (EditText)rootView.findViewById(R.id.square_input2);
        squareRectChoice = (Spinner)rootView.findViewById(R.id.square_rect_spinner);
        squareRectCalcChoice = (Spinner)rootView.findViewById(R.id.square_choice);
        squareRectCalcButton = (Button)rootView.findViewById(R.id.square_calc);
        squareView1 = (RobotoTextView)rootView.findViewById(R.id.square_view1);
        squareView2 = (RobotoTextView)rootView.findViewById(R.id.square_view2);
        squareAnswer = (RobotoTextView)rootView.findViewById(R.id.square_answer);
        orLayout = (LinearLayout)rootView.findViewById(R.id.square_or_layout);
        input2Layout = (LinearLayout)rootView.findViewById(R.id.square_input2_layout);
        orParams = (RelativeLayout.LayoutParams)orLayout.getLayoutParams();
        input2Params = (RelativeLayout.LayoutParams)input2Layout.getLayoutParams();
        check = false;
    }

    private void setSquareRectSpinnerAdapter() {
        ArrayAdapter<CharSequence> sqRectChoiceAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.square_rect_choice_array, R.layout.spinner_background);
        sqRectChoiceAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        squareRectChoice.setAdapter(sqRectChoiceAdapter);
    }

    private void setCalcChoicesSpinnerAdapter(int i){
        if(i==0){
            ArrayAdapter<CharSequence> sqCalcChoiceAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.square_calc_array, R.layout.spinner_background);
            sqCalcChoiceAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
            squareRectCalcChoice.setAdapter(sqCalcChoiceAdapter);
            squareInput2Layout.setVisibility(View.INVISIBLE);

        }else{
            ArrayAdapter<CharSequence> sqCalcChoiceAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.rect_calc_array, R.layout.spinner_background);
            sqCalcChoiceAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
            squareRectCalcChoice.setAdapter(sqCalcChoiceAdapter);
            squareInput2Layout.setVisibility(View.VISIBLE);
        }
    }

    private void clearInput2Layout(){
    squareInput2Layout.setVisibility(View.INVISIBLE);
    }

    private void showInput2Layout(){
        squareInput2Layout.setVisibility(View.VISIBLE);
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

    private void setAreaText(int i){
        if(i==0){
            squareView1.setText("Side W/L");
        } else {
            squareView1.setText("Side W");
            squareView2.setText("Side L");
        }
    }

    private void setDiagonalText(int i){
        if(i==0){
            squareView1.setText("Side W/L");

        } else {
            squareView1.setText("Side W");
            squareView2.setText("Side L");
        }
    }

    private void setPerimeterText(int i){
        if(i==0){
            squareView1.setText("Side W/L");
        } else {
            squareView1.setText("Side W");
            squareView2.setText("Side L");
        }
    }

    private void setSideText(){
        orParams.addRule(RelativeLayout.BELOW, R.id.square_input1_layout);
        orLayout.setLayoutParams(orParams);
        orLayout.setVisibility(View.VISIBLE);
        input2Params.addRule(RelativeLayout.BELOW, R.id.square_or_layout);
        input2Layout.setLayoutParams(input2Params);
        input2Layout.setVisibility(View.VISIBLE);
        squareView1.setText("(d)iagonal");
        squareView2.setText("Perimeter");
    }

    private void setWidthText(){
        squareView1.setText("Side L");
        squareView2.setText("d)iagonal");
    }

    private void setLengthText(){
        squareView1.setText("Side W");
        squareView2.setText("(d)iagonal");
    }

    private void resetLayout(){
        orParams.addRule(RelativeLayout.BELOW, R.id.square_input1_layout);
        orLayout.setLayoutParams(orParams);
        orLayout.setVisibility(View.INVISIBLE);

        if(squareRect == 0) {
            input2Params.addRule(RelativeLayout.BELOW, R.id.square_input1_layout);
            input2Layout.setLayoutParams(input2Params);
            input2Layout.setVisibility(View.INVISIBLE);
        }else{
            input2Params.addRule(RelativeLayout.BELOW, R.id.square_input1_layout);
            input2Layout.setLayoutParams(input2Params);
            input2Layout.setVisibility(View.VISIBLE);
        }
    }

    private void resetBools(){
        check = false;
        check2 = false;
    }
}
