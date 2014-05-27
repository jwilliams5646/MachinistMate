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
        Log.d("acos(1.2628) =",Double.toString(Math.acos(1.2628)));
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
                if(x >= 90 || y >= 90){
                    Toast.makeText(getActivity(), "Only angle (z) can be 90 degrees or greater", Toast.LENGTH_SHORT).show();
                    return;
                }
/*
                if(z < y || z < x){
                    Toast.makeText(getActivity(), "Angle (z) must the widest angle in your triangle.", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                //a-b-c -> x-y-z
                if (cc && ca && cb && !cy && !cx && !cz){
                    sss_abc(a, b, c, df);
                }
                //a-c-z -> x-y-b
                else if (cc && ca && !cb && !cy && !cx && cz){
                    sas_xyb(a, c, z, df);       // side-angle-side
                }
                //c-b-x -> a-y-z
                else if (cc && !ca && cb && !cy && cx && !cz){
                    sas_ayz(c, b, x, df);       // side-angle-side
                }
                //a-b-y -> c-x-z
                else if (!cc && ca && cb && cy && !cx && !cz){
                    sas_cxz(a, b, y, df);      // side-angle-side
                }
                //c-a-y -> b-x-z
                else if (cc && ca && !cb && cy && !cx && !cz){
                    ssa_bxz(c, a, y, df);       // side-side-angle
                }
                //c-a-x -> b-y-z
                else if (cc && ca && !cb && !cy && cx && !cz){
                    ssa_byz(c,a,x,df);       // side-side-angle
                }

                else if (cc && !ca && cb && cy && !cx && !cz){
                    ssa_axz(c,b,y,df);       // side-side-angle
                }

                else if (cc && !ca && cb && !cy && !cx && cz){
                    ssa_ayx(c,b,z,df);       // side-side-angle
                }

                else if (!cc && ca && cb && !cy && cx && !cz){
                    ssa_cyz(a,b,x,df);      // side-side-angle
                }

                else if (!cc && ca && cb && !cy && !cx && cz){
                    ssa_cyx(a,b,z,df);       // side-side-angle
                }

                else if (cc && !ca && !cb && cy && cx && !cz){
                    asa_abz(c,y,x,df);       // angle-side-angle
                }

                else if (cc && !ca && !cb && cy && !cx && cz){
                    asa_abx(c,y,z,df);       // angle-side-angle
                }

                else if (cc && !ca && !cb && !cy && cx && cz){
                    asa_aby(c,x,z,df);      // angle-side-angle
                }

                else if (!cc && ca && !cb && cy && cx && !cz){
                    asa_cbz(a,y,x,df);       // angle-side-angle
                }

                else if (!cc && ca && !cb && cy && !cx && cz){
                    asa_cbx(a,y,z,df);       // angle-side-angle
                }

                else if (!cc && ca && !cb && !cy && cx && cz){
                    asa_cby(a,x,z,df);       // angle-side-angle
                }

                else if (!cc && !ca && cb && cy && cx && !cz){
                    asa_caz(b,y,x,df);      // angle-side-angle
                }

                else if (!cc && !ca && cb && cy && !cx && cz){
                    asa_cax(b,y,z,df);       // angle-side-angle
                }

                else if (!cc && !ca && cb && !cy && cx && cz){
                    asa_cay(b,x,z,df);       // angle-side-angle
                }




            }

            private void asa_cay(double b, double x, double z, DecimalFormat df) {
                if (spinnerX == 0) {
                    x = Math.toRadians(x);
                }
                if (spinnerZ == 0) {
                    z = Math.toRadians(z);
                }
                double y = 180 - (Math.toDegrees(x) + Math.toDegrees(z));
                if (x<=0){
                    Toast.makeText(getActivity(), "This is not a triangle", Toast.LENGTH_SHORT).show();
                    return;
                }
                double c = b*Math.sin(y)/Math.sin(z);
                double a = b*Math.sin(x)/Math.sin(z);
                setAreaPeriHeight(a,b,c,df);
                sideCInput.setText(df.format(c));
                sideAInput.setText(df.format(a));
                setAngleY(y, df);
            }

            private void asa_cax(double b, double y, double z, DecimalFormat df) {
                if (spinnerY == 0) {
                    y = Math.toRadians(y);
                }
                if (spinnerZ == 0) {
                    z = Math.toRadians(z);
                }
                double x = 180 - (Math.toDegrees(y) + Math.toDegrees(z));
                if (x<=0){
                    Toast.makeText(getActivity(), "This is not a triangle", Toast.LENGTH_SHORT).show();
                    return;
                }
                double c = b*Math.sin(y)/Math.sin(z);
                double a = b*Math.sin(x)/Math.sin(z);
                setAreaPeriHeight(a,b,c,df);
                sideCInput.setText(df.format(c));
                sideAInput.setText(df.format(a));
                setAngleX(x, df);
            }

            private void asa_caz(double b, double y, double x, DecimalFormat df) {
                if (spinnerY == 0) {
                    y = Math.toRadians(y);
                }
                if (spinnerX == 0) {
                    x = Math.toRadians(x);
                }
                double z = 180 - (Math.toDegrees(y) + Math.toDegrees(x));
                if (z<=0){
                    Toast.makeText(getActivity(), "This is not a triangle", Toast.LENGTH_SHORT).show();
                    return;
                }
                double c = b*Math.sin(y)/Math.sin(z);
                double a = b*Math.sin(x)/Math.sin(z);
                setAreaPeriHeight(a,b,c,df);
                sideCInput.setText(df.format(c));
                sideAInput.setText(df.format(a));
                setAngleZ(z, df);
            }

            private void asa_cby(double a, double x, double z, DecimalFormat df) {
                if (spinnerZ == 0) {
                    z = Math.toRadians(z);
                }
                if (spinnerX == 0) {
                    x = Math.toRadians(x);
                }
                double y = 180 - (Math.toDegrees(z) + Math.toDegrees(x));
                if (y<=0){
                    Toast.makeText(getActivity(), "This is not a triangle", Toast.LENGTH_SHORT).show();
                    return;
                }
                double c = a*Math.sin(y)/Math.sin(x);
                double b = a*Math.sin(z)/Math.sin(x);
                setAreaPeriHeight(a,b,c,df);
                sideCInput.setText(df.format(c));
                sideBInput.setText(df.format(b));
                setAngleY(y, df);
            }

            private void asa_cbx(double a, double y, double z, DecimalFormat df) {
                if (spinnerZ == 0) {
                    z = Math.toRadians(z);
                }
                if (spinnerY == 0) {
                    y = Math.toRadians(y);
                }
                double x = 180 - (Math.toDegrees(z) + Math.toDegrees(y));
                if (x<=0){
                    Toast.makeText(getActivity(), "This is not a triangle", Toast.LENGTH_SHORT).show();
                    return;
                }
                double c = a*Math.sin(y)/Math.sin(x);
                double b = a*Math.sin(z)/Math.sin(x);
                setAreaPeriHeight(a,b,c,df);
                sideCInput.setText(df.format(c));
                sideBInput.setText(df.format(b));
                setAngleX(x, df);
            }

            private void asa_cbz(double a, double y, double x, DecimalFormat df) {
                if (spinnerX == 0) {
                    x = Math.toRadians(x);
                }
                if (spinnerY == 0) {
                    y = Math.toRadians(y);
                }
                double z = 180 - (Math.toDegrees(x) + Math.toDegrees(y));
                if (z<=0){
                    Toast.makeText(getActivity(), "This is not a triangle", Toast.LENGTH_SHORT).show();
                    return;
                }
                double c = a*Math.sin(y)/Math.sin(x);
                double b = a*Math.sin(z)/Math.sin(x);
                setAreaPeriHeight(a,b,c,df);
                sideCInput.setText(df.format(c));
                sideBInput.setText(df.format(b));
                setAngleZ(z, df);
            }

            private void asa_aby(double c, double x, double z, DecimalFormat df) {
                if (spinnerX == 0) {
                    x = Math.toRadians(x);
                }
                if (spinnerZ == 0) {
                    z = Math.toRadians(z);
                }
                double y = 180 - (Math.toDegrees(x) + Math.toDegrees(z));
                if (y<=0){
                    Toast.makeText(getActivity(), "This is not a triangle", Toast.LENGTH_SHORT).show();
                    return;
                }
                double a = c*Math.sin(x)/Math.sin(y);
                double b = c*Math.sin(z)/Math.sin(y);
                setAreaPeriHeight(a,b,c,df);
                sideAInput.setText(df.format(a));
                sideBInput.setText(df.format(b));
                setAngleY(y, df);
            }

            private void asa_abx(double c, double y, double z, DecimalFormat df) {
                if (spinnerY == 0) {
                    y = Math.toRadians(y);
                }
                if (spinnerZ == 0) {
                    z = Math.toRadians(z);
                }
                double x = 180 - (Math.toDegrees(y) + Math.toDegrees(z));
                if (x <= 0){
                    Toast.makeText(getActivity(), "This is not a triangle", Toast.LENGTH_SHORT).show();
                    return;
                }
                double a = c*Math.sin(x)/Math.sin(y);
                double b = c*Math.sin(z)/Math.sin(y);
                setAreaPeriHeight(a,b,c,df);
                sideAInput.setText(df.format(a));
                sideBInput.setText(df.format(b));
                setAngleX(x, df);
            }

            private void asa_abz(double c, double y, double x, DecimalFormat df) {
                if(spinnerY == 1){
                    y = Math.toDegrees(y);
                }
                if(spinnerX == 1){
                    x = Math.toDegrees(x);
                }
                double z = 180 - (y + x);
                if (z <= 0){
                    Toast.makeText(getActivity(), "This is not a triangle", Toast.LENGTH_SHORT).show();
                    return;
                }
                double a = c*Math.sin(Math.toRadians(x) / Math.sin(Math.toRadians(y)));
                double b = c*Math.sin(Math.toRadians(z) / Math.sin(Math.toRadians(y)));
                setAreaPeriHeight(a,b,c,df);
                sideAInput.setText(df.format(a));
                sideBInput.setText(df.format(b));
                setAngleZ(z, df);
            }

            private void ssa_cyx(double a, double b, double z, DecimalFormat df) {
                if(spinnerZ == 0){
                    z = Math.toRadians(z);
                }
                double x = Math.toDegrees(Math.asin(a * Math.sin(z) / b));
                double y = 180.0 - (Math.toDegrees(z) + x);
                double c = Math.sqrt(a*a + b*b - 2*a*b*Math.cos(y));
                setAreaPeriHeight(a,b,c,df);
                sideCInput.setText(df.format(c));
                setAngleY(y, df);
                setAngleX(x, df);
            }

            private void ssa_cyz(double a, double b, double x, DecimalFormat df) {
                if(spinnerX == 0){
                    x = Math.toRadians(x);
                }
                double z = Math.toDegrees(Math.asin(b * Math.sin(x) / a));
                double y = 180.0 - (Math.toDegrees(x) + z);
                double c = Math.sqrt(a*a + b*b - 2*a*b*Math.cos(Math.toRadians(y)));
                setAreaPeriHeight(a,b,c,df);
                sideCInput.setText(df.format(c));
                setAngleY(y, df);
                setAngleZ(z, df);
            }

            private void ssa_ayx(double c, double b, double z, DecimalFormat df) {
                if(c > b && z >90){
                    Toast.makeText(getActivity(), "Side b cannot be shorter than side c if angle (z) is greater than 90 degrees", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinnerZ == 0){
                    z = Math.toRadians(z);
                }
                double y = Math.toDegrees(Math.asin(c*Math.sin(z)/b));
                double x = 180.0 - (y + Math.toDegrees(z));
                double a = Math.sqrt(c*c + b*b - 2*c*b*Math.cos(Math.toRadians(x)));
                setAreaPeriHeight(a,b,c,df);
                sideAInput.setText(df.format(a));
                setAngleY(y, df);
                setAngleX(x, df);
            }

            private void ssa_axz(double c, double b, double y, DecimalFormat df) {
                if(spinnerY == 0){
                    y = Math.toRadians(y);
                }
                double z = Math.toDegrees(Math.asin(b*Math.sin(y)/c));
                double x = 180.0 - (Math.toDegrees(y) + z);
                double a = Math.sqrt(c*c + b*b - 2*c*b*Math.cos(Math.toRadians(x)));
                setAreaPeriHeight(a,b,c,df);
                sideAInput.setText(df.format(a));
                setAngleX(x, df);
                setAngleZ(z, df);
            }

            private void ssa_byz(double c, double a, double x, DecimalFormat df) {
                if(spinnerX == 0){
                    x = Math.toRadians(x);
                }
                double y = Math.toDegrees(Math.asin(c * Math.sin(x) / a));
                double z = 180.0 - (y + Math.toDegrees(x));
                double b = Math.sqrt(c*c + a*a - 2*c*a*Math.cos(Math.toRadians(z)));
                setAreaPeriHeight(a,b,c,df);
                sideBInput.setText(df.format(b));
                setAngleY(y, df);
                setAngleZ(z, df);
            }

            private void ssa_bxz(double c, double a, double y, DecimalFormat df) {
                if(spinnerY == 0){
                    y = Math.toRadians(y);
                }
                double x = Math.toDegrees(Math.asin(a*Math.sin(y)/c));
                double z = 180.0 - (Math.toDegrees(y) + x);
                double b = Math.sqrt(c*c + a*a - 2*c*a*Math.cos(Math.toRadians(z)));
                setAreaPeriHeight(a,b,c,df);
                sideBInput.setText(df.format(b));
                setAngleX(x, df);
                setAngleZ(z, df);
            }

            private void sas_cxz(double a, double b, double y, DecimalFormat df) {
                if(spinnerY == 0){
                    y = Math.toRadians(y);
                }
                double c = Math.sqrt(a*a + b*b - 2*a*b*Math.cos(y));
                double x = 0.0;
                double z = 0.0;
                // only one angle can be > 90 deg
                if (a < b){
                    x = Math.toDegrees(Math.asin(a*Math.sin(y)/c));
                    z = 180 - (x + Math.toDegrees(y));
                }else{
                    z = Math.toDegrees(Math.asin(b * Math.sin(y) / c));
                    x = 180 - (z + Math.toDegrees(y));
                }
                setAreaPeriHeight(a,b,c,df);
                sideCInput.setText(df.format(c));
                setAngleX(x, df);
                setAngleZ(z, df);
            }

            private void sas_ayz(double c, double b, double x, DecimalFormat df) {
                if(spinnerX == 0){
                    x = Math.toRadians(x);
                }
                double a = Math.sqrt(c*c + b*b - 2*c*b*Math.cos(x));
                double y = 0.0;
                double z = 0.0;
                // only one angle can be > 90 deg
                if (c < b){
                    y = Math.toDegrees(Math.asin(c*Math.sin(x)/a));
                    Log.d(" x is ",Double.toString(Math.toDegrees(x)));
                    Log.d(" y is ",Double.toString(y));
                    z = 180 - (Math.toDegrees(x) + y);
                }
                else{
                    z = Math.toDegrees(Math.asin(b*Math.sin(x)/a));
                    Log.d(" x is ",Double.toString(Math.toDegrees(x)));
                    Log.d(" z is ",Double.toString(z));
                    y = 180 - (Math.toDegrees(x) + z);
                }
                setAreaPeriHeight(a,b,c,df);
                sideAInput.setText(df.format(a));
                setAngleY(y, df);
                setAngleZ(z, df);
            }

            private void sas_xyb(double a, double c, double z, DecimalFormat df) {
                if(spinnerZ == 0){
                    z = Math.toRadians(z);
                }
                double x = 0.0;
                double y = 0.0;
                double b = Math.sqrt(c*c + a*a - 2*c*a*(Math.cos(z)));

                if (c < a){  // only one angle can be > 90 deg
                    y = Math.toDegrees(Math.asin(c*Math.sin(z)/b));
                     Log.d(" y is ",Double.toString(y));
                    x = 180 - (y + Math.toDegrees(z));
                }
                else{
                    x = Math.toDegrees(Math.asin(a*Math.sin(z)/b));
                    Log.d(" x is ",Double.toString(x));
                    y = 180 - (x + Math.toDegrees(z));
                }
                setAreaPeriHeight(a,b,c,df);
                sideBInput.setText(df.format(b));
                setAngleX(x, df);
                setAngleY(y, df);
            }

            private void sss_abc(double a, double b, double c, DecimalFormat df) {
                if(b < c || b < a){
                    Toast.makeText(getActivity(), "Side (b) is the base of the triangle and must be the longest side.", Toast.LENGTH_SHORT).show();
                    return;
                }
                double x = Math.toDegrees(Math.acos((c * c + b * b - a * a) / (2 * b * c)));
                double y = Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2 * a * b)));
                double z = 180 - (x + y);
                if(isNaN(x) || isNaN(y) || isNaN(z)){
                    Toast.makeText(getActivity(), "This is not a triangle.", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    setAngleX(x, df);
                    setAngleY(y, df);
                    setAngleZ(z, df);
                    setAreaPeriHeight(a, b, c, df);
                }
            }

            private boolean isNaN(double x) {
                String y = Double.toString(x);
                if(y =="NaN")
                return true;
                else{
                    return false;
                }
            }

            private void setAngleX(double x, DecimalFormat df){
                if(spinnerX == 1){
                    angleXInput.setText(df.format(Math.toRadians(x)));
                }else {
                    angleXInput.setText(df.format(x));
                }
            }

            private void setAngleY(double y, DecimalFormat df){
                if(spinnerY == 1){
                    angleYInput.setText(df.format(Math.toRadians(y)));
                }else {
                    angleYInput.setText(df.format(y));
                }
            }

            private void setAngleZ(double z, DecimalFormat df){
                if(spinnerZ == 1){
                    angleZInput.setText(df.format(Math.toRadians(z)));
                }else {
                    angleZInput.setText(df.format(z));
                }
            }

            private double getArea(double a, double b, double c){
                double x = (a + b + c)/2;
                return Math.sqrt(x*(x - a)*(x - b)*(x - c));
            }

            private void setAreaPeriHeight(double a, double b, double c, DecimalFormat df) {
                double area = getArea(a,b,c);
                areaAnswer.setText(df.format(area));
                perimeterAnswer.setText(df.format(a + b + c));
                heightAnswer.setText(df.format(2 / (area / b)));
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
