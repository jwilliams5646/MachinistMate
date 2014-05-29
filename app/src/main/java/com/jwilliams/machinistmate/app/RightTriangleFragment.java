package com.jwilliams.machinistmate.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;

/**
 * Created by john.williams on 5/19/2014.
 */
public class RightTriangleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private EditText sideH;
    private EditText sideO;
    private EditText sideA;
    private EditText angleX;
    private EditText angleY;
    private RobotoTextView areaAnswer;
    private RobotoTextView perimeterAnswer;
    private Spinner angleXSpinner;
    private Spinner angleYSpinner;
    private Button calcButton;
    private Button clearButton;
    private Button questionButton;
    private int xPos;
    private int yPos;



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
        setSpinnerAdapters();
        setSpinnerXListener();
        setSpinnerYListener();
        setClearButtonListener();
        setQuestionButtonListener();
        setCalcButtonListener();
        return rootView;
    }

    private void setCalcButtonListener() {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double h = 0.0;
                double o = 0.0;
                double a = 0.0;
                double x = 0.0;
                double y = 0.0;
                double area = 0.0;
                double p = 0.0;
                boolean ch = false;
                boolean co = false;
                boolean ca = false;
                boolean cx = false;
                boolean cy = false;
                int count = 0;

                try{
                    h = Double.parseDouble(sideH.getText().toString());
                }catch(NumberFormatException e){
                    ch = true;
                    count++;
                }
                try{
                    o = Double.parseDouble(sideO.getText().toString());
                }catch(NumberFormatException e){
                    co = true;
                    count++;
                }
                try{
                    a = Double.parseDouble(sideA.getText().toString());
                }catch(NumberFormatException e){
                    ca = true;
                    count++;
                }
                try{
                    x = Double.parseDouble(angleX.getText().toString());
                }catch(NumberFormatException e){
                    cx = true;
                    count++;
                }
                try{
                    y = Double.parseDouble(angleY.getText().toString());
                }catch(NumberFormatException e){
                    cy = true;
                    count++;
                }

                if(!ch && !co && ca && cx && cy){
                    ss_ho(h, o, a, x, y, p, area);
                }
                else if(!ch && co && !ca && cx && cy){
                    ss_ha(h, o, a, x, y, p, area);
                }
                else if(!ch && co && ca && !cx && cy){
                    sa_hx(h, o, a, x, y, p, area);
                }
                else if(!ch && co && ca && cx && !cy){
                    sa_hy(h, o, a, x, y, p, area);
                }
                else if(ch && !co && !ca && cx && cy){
                    ss_oa(h, o, a, x, y, p, area);
                }
                else if(ch && !co && ca && !cx && cy){
                    sa_ox(h, o, a, x, y, p, area);
                }
                else if(ch && !co && ca && cx && !cy){
                    sa_oy(h, o, a, x, y, p, area);
                }
                else if(ch && co && !ca && !cx && cy){
                    sa_ax(h, o, a, x, y, p, area);
                }
                else if(ch && co && !ca && cx && !cy){
                    sa_ay(h, o, a, x, y, p, area);
                }
            }

            private void sa_ay(double h, double o, double a, double x, double y, double p, double area) {
                if(y < 1 || y >= 90){
                    setYToast();
                    return;
                }
                if(yPos == 1){
                    y = Math.toDegrees(y);
                }
                x = 90 - y;
                h = a/Math.sin(Math.toRadians(y));
                o = h*Math.sin(Math.toRadians(x));
                sideH.setText(Double.toString(h));
                sideO.setText(Double.toString(o));
                angleY.setText(Double.toString(y));
                setAreaPeri(h, o, a);
            }

            private void sa_ax(double h, double o, double a, double x, double y, double p, double area) {
                if(x < 1 || x >= 90){
                    setXToast();
                    return;
                }
                if(xPos == 1){
                    x = Math.toDegrees(x);
                }
                y = 90 - x;
                h = a/Math.sin(Math.toRadians(y));
                o = h*Math.sin(Math.toRadians(x));
                sideH.setText(Double.toString(h));
                sideO.setText(Double.toString(o));
                angleY.setText(Double.toString(y));
                setAreaPeri(h, o, a);
            }

            private void sa_oy(double h, double o, double a, double x, double y, double p, double area) {
                if(y < 1 || y >= 90){
                    setYToast();
                    return;
                }
                if(yPos == 1){
                    y = Math.toDegrees(y);
                }
                x = 90 - y;
                h = a/Math.sin(Math.toRadians(y));
                a = h*Math.cos(Math.toRadians(x));
                sideH.setText(Double.toString(h));
                sideA.setText(Double.toString(a));
                angleX.setText(Double.toString(x));
                setAreaPeri(h, o, a);

            }

            private void sa_ox(double h, double o, double a, double x, double y, double p, double area) {
                if(x < 1 || x >= 90){
                    setXToast();
                    return;
                }
                if(xPos == 1){
                    x = Math.toDegrees(x);
                }
                y = 90 - x;
                h = a/Math.sin(Math.toRadians(y));
                a = h*Math.cos(Math.toRadians(x));
                sideH.setText(Double.toString(h));
                sideA.setText(Double.toString(a));
                angleY.setText(Double.toString(y));
                setAreaPeri(h, o, a);
            }

            private void ss_oa(double h, double o, double a, double x, double y, double p, double area) {
                h = Math.sqrt(o*o + a*a);
                x = Math.toDegrees(Math.asin(Math.sin(o/h)));
                y = 90 - x;
                sideH.setText(Double.toString(h));
                angleX.setText(Double.toString(x));
                angleY.setText(Double.toString(y));
                setAreaPeri(h, o, a);
            }

            private void sa_hy(double h, double o, double a, double x, double y, double p, double area) {
                if(yPos == 1){
                    y = Math.toDegrees(x);
                }
                x = 90 - y;
                o = h*Math.sin(Math.toRadians(x));
                a = h*Math.cos(Math.toRadians(x));
                sideO.setText(Double.toString(o));
                sideA.setText(Double.toString(a));
                angleX.setText(Double.toString(x));
                setAreaPeri(h, o, a);
            }

            private void sa_hx(double h, double o, double a, double x, double y, double p, double area) {
                if(x < 1 || x >= 90){
                    setXToast();
                    return;
                }
                if(xPos == 1){
                    x = Math.toDegrees(x);
                }
                y = 90 - x;
                o = h*Math.sin(Math.toRadians(x));
                a = h*Math.cos(Math.toRadians(x));
                sideO.setText(Double.toString(o));
                sideA.setText(Double.toString(a));
                angleY.setText(Double.toString(y));
                setAreaPeri(h,o,a);
            }

            private void ss_ha(double h, double o, double a, double x, double y, double p, double area) {
                if(h<a) {
                    Toast.makeText(getActivity(), "Side H must always be longer than side A.", Toast.LENGTH_SHORT).show();
                    return;
                }
                o = Math.sqrt(h*h - a*a);
                x = Math.toDegrees(Math.asin(Math.sin(o/h)));
                y = 90 - x;
                area = (o+a)/2;
                p = h+o+a;
                sideO.setText(Double.toString(o));
                angleX.setText(Double.toString(x));
                angleY.setText(Double.toString(y));
                setAreaPeri(h, o, a);
            }

            private void ss_ho(double h, double o, double a, double x, double y, double p, double area) {
                a = Math.sqrt(h*h - o*o);
                x = Math.toDegrees(Math.asin(Math.sin(o/h)));
                y = 90 - x;
                sideA.setText(Double.toString(a));
                angleX.setText(Double.toString(x));
                angleY.setText(Double.toString(y));
                setAreaPeri(h, o, a);
            }

            private void setAreaPeri(double h, double o, double a) {
                areaAnswer.setText(Double.toString(getArea(o,a)));
                perimeterAnswer.setText(Double.toString(getPeri(h,o,a)));
            }

            private double getArea(double o, double a){
                return (o+a)/2;
            }

            private double getPeri(double h, double o, double a){
                return h+o+a;
            }

            private void setYToast() {
                Toast.makeText(getActivity(), "Angle y can't be greater than 90 or less than 1", Toast.LENGTH_SHORT).show();
                return;
            }

            private void setXToast() {
                Toast.makeText(getActivity(), "Angle x can't be greater than 90 or less than 1", Toast.LENGTH_SHORT).show();
                return;
            }

        });
    }

    private void setQuestionButtonListener() {
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog d = new Dialog(getActivity());

                // Set GUI of login screen
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.info_dialog);
                //Set dialog text
                final RobotoTextView dialog = (RobotoTextView)d.findViewById(R.id.dialog);
                dialog.setText("Input at least 2 values (angles only will not work).\n" +
                        "Side H must always be longer than side A.\n" +
                        "These calculations use the law of sine, cosine and/or tangent.");
                // Make dialog box visible.
                d.show();
            }});
    }

    private void setClearButtonListener() {
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void setSpinnerYListener() {
        angleYSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int i, long id) {
                yPos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}});
    }

    private void setSpinnerXListener() {
        angleXSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int i, long id) {
                xPos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}});
    }

    private void initializeLayout(View rootView) {
        sideH = (EditText)rootView.findViewById(R.id.rt_h_input);
        sideO = (EditText)rootView.findViewById(R.id.rt_o_input);
        sideA = (EditText)rootView.findViewById(R.id.rt_a_input);
        angleX = (EditText)rootView.findViewById(R.id.rt_x_input);
        angleY = (EditText)rootView.findViewById(R.id.rt_y_input);
        areaAnswer = (RobotoTextView)rootView.findViewById(R.id.rt_area_answer);
        perimeterAnswer = (RobotoTextView)rootView.findViewById(R.id.rt_perimeter_answer);
        angleXSpinner = (Spinner)rootView.findViewById(R.id.rt_x_spinner);
        angleYSpinner = (Spinner)rootView.findViewById(R.id.rt_y_spinner);
        calcButton = (Button)rootView.findViewById(R.id.rt_calc_button);
        clearButton = (Button)rootView.findViewById(R.id.rt_clear_button);
        questionButton = (Button)rootView.findViewById(R.id.rt_question_button);
        xPos = 0;
        yPos = 0;
    }

    private void clear(){
        sideH.setText("");
        sideO.setText("");
        sideA.setText("");
        angleX.setText("");
        angleY.setText("");
        areaAnswer.setText("");
        perimeterAnswer.setText("");
    }

    private void setSpinnerAdapters() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.degree_rad_array, R.layout.spinner_background);
        adapter.setDropDownViewResource(R.layout.spinner_drop_down);
        angleXSpinner.setAdapter(adapter);
        angleYSpinner.setAdapter(adapter);
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
