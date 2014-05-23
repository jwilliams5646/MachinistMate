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
import android.widget.Spinner;
import android.widget.Toast;

import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;

import java.text.DecimalFormat;

/**
 * Created by John on 5/12/2014.
 */
public class ObliqueTriangleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private EditText sideAInput;
    private EditText sideBInput;
    private EditText sideCInput;
    private EditText angleXInput;
    private EditText angleYInput;
    private EditText angleZInput;
    private RobotoTextView areaAnswer;
    private RobotoTextView heightAnswer;
    private RobotoTextView perimeterAnswer;
    private Spinner angleXSpinner;
    private Spinner angleYSpinner;
    private Spinner angleZSpinner;
    private Button calcButton;
    private Button clearButton;
    private int spinnerX;
    private int spinnerY;
    private int spinnerZ;

    public ObliqueTriangleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.oblique_triangle_detail, container, false);
        initializeLayout(rootView);
        return rootView;
    }

    private void initializeLayout(View rootView) {
        sideAInput = (EditText)rootView.findViewById(R.id.oblique_a_input);
        sideBInput = (EditText)rootView.findViewById(R.id.oblique_b_input);
        sideCInput = (EditText)rootView.findViewById(R.id.oblique_c_input);
        angleXInput = (EditText)rootView.findViewById(R.id.oblique_x_input);
        angleYInput = (EditText)rootView.findViewById(R.id.oblique_y_input);
        angleZInput = (EditText)rootView.findViewById(R.id.oblique_z_input);
        areaAnswer = (RobotoTextView)rootView.findViewById(R.id.oblique_area_answer);
        heightAnswer = (RobotoTextView)rootView.findViewById(R.id.oblique_height_answer);
        perimeterAnswer = (RobotoTextView)rootView.findViewById(R.id.oblique_perimeter_answer);
        angleXSpinner = (Spinner)rootView.findViewById(R.id.oblique_x_spinner);
        angleYSpinner = (Spinner)rootView.findViewById(R.id.oblique_y_spinner);
        angleZSpinner = (Spinner)rootView.findViewById(R.id.oblique_z_spinner);
        calcButton = (Button)rootView.findViewById(R.id.oblique_calc_button);
        clearButton = (Button)rootView.findViewById(R.id.oblique_clear_button);
        spinnerX = 0;
        spinnerY = 0;
        spinnerZ = 0;
        setAngleAdapters();
        setAngleXListener();
        setAngleYListener();
        setAngleZListener();
        setClearListener();
        setCalcButtonListener();
    }

    private void setCalcButtonListener() {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = 0.0;
                double b = 0.0;
                double c = 0.0;
                double x = 0.0;
                double y = 0.0;
                double z = 0.0;
                boolean ca = true;
                boolean cb = true;
                boolean cc = true;
                boolean cx = true;
                boolean cy = true;
                boolean cz = true;
                int count = 0;
                DecimalFormat df = new DecimalFormat("##.####");

                try {
                    a = Double.parseDouble(sideAInput.getText().toString());
                } catch (NumberFormatException e) {
                    ca=false;
                    count++;
                    Log.d("Side (a) has no value", Integer.toString(count));
                }

                try {
                    b = Double.parseDouble(sideBInput.getText().toString());
                } catch (NumberFormatException e) {
                    cb=false;
                    count++;
                    Log.d("Side (b) has no value", Integer.toString(count));
                }

                try {
                    c = Double.parseDouble(sideCInput.getText().toString());
                } catch (NumberFormatException e) {
                    cc=false;
                    count++;
                    Log.d("Side (c) has no value", Integer.toString(count));
                }

                try {
                    x = Double.parseDouble(angleXInput.getText().toString());
                } catch (NumberFormatException e) {
                    cx=false;
                    count++;
                    Log.d("Angle (x) has no value", Integer.toString(count));
                }

                try {
                    y = Double.parseDouble(angleYInput.getText().toString());
                } catch (NumberFormatException e) {
                    cy=false;
                    count++;
                    Log.d("Angle (y) has no value", Integer.toString(count));
                }

                try {
                    z = Double.parseDouble(angleZInput.getText().toString());
                } catch (NumberFormatException e) {
                    cz=false;
                    count++;
                    Log.d("Angle (z) has no value", Integer.toString(count));
                }

                if(count > 3){
                    Toast.makeText(getActivity(), "You need at least 3 values to proceed", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(count < 3){
                    Toast.makeText(getActivity(), "Input a maximum of 3 values", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(cx && cy && cz && !ca && !cb && !cc){
                    Toast.makeText(getActivity(), "Triangle can not be solved with only angles.  This is the triangle equivalent of dividing by zero.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(z < y || z < x){
                    Toast.makeText(getActivity(), "Angle (z) must the widest angle in your triangle.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ca && cb &&  cc && !cx && !cy && !cz){
                    sss_abc(a,b,c,df);
                }




            }

            private double arccosd(double x){
                if (Math.abs(x - 1) < 0.000000001) x = 1.0;
                return Math.acos(x)*(180/Math.PI);
            }


            private void sss_abc(double a, double b, double c, DecimalFormat df) {
                if(b < c || b < a){
                    Toast.makeText(getActivity(), "Side (b) is the base of the triangle and must be the longest side.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double x = Math.acos((c * c + b * b - a * a) / (2 * b * c));
                double y = Math.acos((a * a + b * b - c * c) / (2 * a * b));
                double z = 180 - Double.parseDouble(df.format(Math.toDegrees(x))) - Double.parseDouble(df.format(Math.toDegrees(y)));
                if(spinnerX == 0){
                    angleXInput.setText(df.format(Math.toDegrees(x)));
                }else{
                    angleXInput.setText(Double.toString(x));
                }
                if(spinnerY == 0){
                    angleYInput.setText(df.format(Math.toDegrees(y)));
                }else{
                    angleYInput.setText(Double.toString(y));
                }
                if(spinnerZ == 0){
                    angleZInput.setText(df.format(z));
                }else{
                    angleZInput.setText(Double.toString(Math.toRadians(z)));
                }
                areaAnswer.setText(Double.toString((a+b+c/2)));
                perimeterAnswer.setText(Double.toString(a+b+c));
                heightAnswer.setText(Double.toString(2/((a+b+c/2)/b)));
            }
        });

    }

    private void setClearListener(){
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sideAInput.setText("");
                sideBInput.setText("");
                sideCInput.setText("");
                angleXInput.setText("");
                angleYInput.setText("");
                angleZInput.setText("");
                areaAnswer.setText("");
                heightAnswer.setText("");
                perimeterAnswer.setText("");
            }
        });
    }

    private void setAngleXListener() {
        angleXSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerX = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setAngleYListener() {
        angleYSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerY = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setAngleZListener() {
        angleZSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerZ = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setAngleAdapters() {
        ArrayAdapter<CharSequence> angleAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.degree_rad_array, R.layout.spinner_background);
        angleAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        angleXSpinner.setAdapter(angleAdapter);
        angleYSpinner.setAdapter(angleAdapter);
        angleZSpinner.setAdapter(angleAdapter);
    }

    static ObliqueTriangleFragment newInstance(int position) {
        ObliqueTriangleFragment frag=new ObliqueTriangleFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.oblique), position + 1));
    }
}
