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

/**
 * Created by John on 5/12/2014.
 */
public class SquareFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout squareInput2Layout;
    private EditText squareInput1;
    private EditText squareInput2;
    private Spinner squareRectChoice;
    private Spinner squareCalcChoice;
    private Button squareCalc;
    private int squareRect;
    private int calcChoice;
    AdapterView.OnItemSelectedListener squareChoiceSelectedListener;
    AdapterView.OnItemSelectedListener rectCalcChoiceSelectedListener;
    AdapterView.OnItemSelectedListener squareCalcChoiceSelectedListener;

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
        setSquareCalcChoiceListener();

        return rootView;

    }

    private void setSquareCalcChoiceListener() {

        squareCalcChoiceSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calcChoice = i;
                switch(i){
                    case 0:

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                calcChoice = 0;
            }
        };

    }

    private void setSquareRectChoiceListener() {


        squareChoiceSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                squareRect = i;
                setCalcChoicesSpinnerAdapter(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                squareRect = 0;
                setCalcChoicesSpinnerAdapter(0);
            }
        };

        squareRectChoice.setOnItemSelectedListener(squareChoiceSelectedListener);
    }

    private void setRectChoiceListener(){

        rectCalcChoiceSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calcChoice = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                calcChoice = 0;
            }
        };

    }

    private void setInitialLayout(View rootView) {
        squareInput2Layout = (LinearLayout)rootView.findViewById(R.id.square_input2_layout);
        squareInput1 = (EditText)rootView.findViewById(R.id.square_input1);
        squareInput2 = (EditText)rootView.findViewById(R.id.square_input2);
        squareRectChoice = (Spinner)rootView.findViewById(R.id.square_rect_spinner);
        squareCalcChoice = (Spinner)rootView.findViewById(R.id.square_choice);
        squareCalc = (Button)rootView.findViewById(R.id.square_calc);
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
            squareCalcChoice.setAdapter(sqCalcChoiceAdapter);
            squareInput2Layout.setVisibility(View.INVISIBLE);

        }else{
            ArrayAdapter<CharSequence> sqCalcChoiceAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.rect_calc_array, R.layout.spinner_background);
            sqCalcChoiceAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
            squareCalcChoice.setAdapter(sqCalcChoiceAdapter);
            squareInput2Layout.setVisibility(View.VISIBLE);

        }
    }

private void clearInput2Layout(){
    squareInput2Layout.setVisibility(View.INVISIBLE);
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
