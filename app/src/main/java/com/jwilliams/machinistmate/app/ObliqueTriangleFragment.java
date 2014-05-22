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
public class ObliqueTriangleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private LinearLayout input1Layout;
    private LinearLayout input2Layout;
    private LinearLayout input3Layout;
    private LinearLayout degRad1Layout;
    private LinearLayout degRad2Layout;
    private Spinner answerDegRadSpinner1;
    private Spinner degRadSpinner1;
    private Spinner degRadSpinner2;
    private Spinner answerChoiceSpinner;
    private Spinner inputStyleSpinner;
    private EditText input1;
    private EditText input2;
    private EditText input3;
    private EditText degRadInput1;
    private EditText degRadInput2;
    private RobotoTextView answer;
    private RobotoTextView degRadView1;
    private RobotoTextView degRadView2;
    private RobotoTextView input1view;
    private RobotoTextView input2view;
    private RobotoTextView input3view;
    private Button what;
    private Button calcAnswer;
    private boolean check;
    private int inputChoice;
    private int answerChoice;
    private int degRad1Choice;
    private int degRad2Choice;
    private int degRadAnswerChoice;




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
        input1Layout = (LinearLayout)rootView.findViewById(R.id.oblique_input1_layout);
        input2Layout = (LinearLayout)rootView.findViewById(R.id.oblique_input2_layout);
        input3Layout = (LinearLayout)rootView.findViewById(R.id.oblique_input3_layout);
        degRad1Layout = (LinearLayout)rootView.findViewById(R.id.oblique_deg_rad_layout1);
        degRad2Layout = (LinearLayout)rootView.findViewById(R.id.oblique_deg_rad_layout2);
        answerChoiceSpinner = (Spinner)rootView.findViewById(R.id.oblique_choice_spinner);
        answerDegRadSpinner1 = (Spinner)rootView.findViewById(R.id.oblique_answer_degrad_spinner);
        degRadSpinner1 = (Spinner)rootView.findViewById(R.id.oblique_deg_rad_choice_spinner1);
        degRadSpinner2 = (Spinner)rootView.findViewById(R.id.oblique_deg_rad_choice_spinner2);
        inputStyleSpinner = (Spinner)rootView.findViewById(R.id.oblique_input_style_spinner);
        input1 = (EditText)rootView.findViewById(R.id.oblique_input1);
        input2 = (EditText)rootView.findViewById(R.id.oblique_input2);
        input3 = (EditText)rootView.findViewById(R.id.oblique_input3);
        degRadInput1 = (EditText)rootView.findViewById(R.id.oblique_deg_rad_input1);
        degRadInput2 = (EditText)rootView.findViewById(R.id.oblique_deg_rad_input2);
        answer = (RobotoTextView)rootView.findViewById(R.id.oblique_answer);
        degRadView1 = (RobotoTextView)rootView.findViewById(R.id.oblique_degree_rad_view1);
        degRadView2 = (RobotoTextView)rootView.findViewById(R.id.oblique_degree_rad_view2);
        input1view = (RobotoTextView)rootView.findViewById(R.id.oblique_view1);
        input2view = (RobotoTextView)rootView.findViewById(R.id.oblique_view2);
        input3view = (RobotoTextView)rootView.findViewById(R.id.oblique_view3);
        what = (Button)rootView.findViewById(R.id.oblique_question_button);
        calcAnswer = (Button)rootView.findViewById(R.id.oblique_calc_button);
        check = false;
        inputChoice = 0;
        answerChoice = 0;
        degRad1Choice = 0;
        degRad2Choice = 0;
        degRadAnswerChoice = 0;
        setInitialLayout();
        setDegRadAdapters();
        setMainAdapters();
        setDegRadListeners();
        setLayoutChangeListener();
        setQuestionListener();
        setCalcAnswerListener();
    }

    private void setCalcAnswerListener() {
        calcAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double area = 0.0;
                double a = 0.0;
                double b = 0.0;
                double c = 0.0;
                double height = 0.0;
                double x = 0.0;
                double y = 0.0;
                double z = 0.0;
                double perimeter = 0.0;
                switch(inputChoice){
                    case 0:
                }
            }
        });

    }

    private void setQuestionListener() {
        what.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(inputChoice){
                    case 0:
                        Toast.makeText(getActivity(), "Two angles and a side not between", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "Two angles and a side between", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "Two sides with an angle in between", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(), "Two sides and an angle not between", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getActivity(), "All 3 sides", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void setLayoutChangeListener(){
        AdapterView.OnItemSelectedListener changeLayoutListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                inputChoice = pos;
                clearLayouts();
                switch(pos){
                    case 0:
                        setInitialLayout();
                        ArrayAdapter<CharSequence> answerChoiceAdapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.oblique_initial_calc_array, R.layout.spinner_background);
                        answerChoiceAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
                        answerChoiceSpinner.setAdapter(answerChoiceAdapter);
                        break;
                    case 1:
                        ArrayAdapter<CharSequence> answerChoice1Adapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.oblique_asa_array, R.layout.spinner_background);
                        answerChoice1Adapter.setDropDownViewResource(R.layout.spinner_drop_down);
                        answerChoiceSpinner.setAdapter(answerChoice1Adapter);
                        degRadView1.setText("Angle y");
                        degRadView2.setText("Angle x");
                        input3view.setText("Side b");
                        degRad1Layout.setVisibility(View.VISIBLE);
                        degRad2Layout.setVisibility(View.VISIBLE);
                        input3Layout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        ArrayAdapter<CharSequence> answerChoice2Adapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.oblique_sas_array, R.layout.spinner_background);
                        answerChoice2Adapter.setDropDownViewResource(R.layout.spinner_drop_down);
                        answerChoiceSpinner.setAdapter(answerChoice2Adapter);
                        degRadView1.setText("Angle y");
                        input2view.setText("Side a");
                        input3view.setText("Side b");
                        degRad1Layout.setVisibility(View.VISIBLE);
                        input2Layout.setVisibility(View.VISIBLE);
                        input3Layout.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        ArrayAdapter<CharSequence> answerChoice3Adapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.oblique_ass_array, R.layout.spinner_background);
                        answerChoice3Adapter.setDropDownViewResource(R.layout.spinner_drop_down);
                        answerChoiceSpinner.setAdapter(answerChoice3Adapter);
                        degRadView1.setText("Angle x");
                        input2view.setText("Side a");
                        input3view.setText("Side b");
                        degRad1Layout.setVisibility(View.VISIBLE);
                        input2Layout.setVisibility(View.VISIBLE);
                        input3Layout.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        ArrayAdapter<CharSequence> answerChoice4Adapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.oblique_sss_array, R.layout.spinner_background);
                        answerChoice4Adapter.setDropDownViewResource(R.layout.spinner_drop_down);
                        answerChoiceSpinner.setAdapter(answerChoice4Adapter);
                        input1view.setText("Side a");
                        input2view.setText("Side b");
                        input3view.setText("Side c");
                        input1Layout.setVisibility(View.VISIBLE);
                        input2Layout.setVisibility(View.VISIBLE);
                        input3Layout.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        inputStyleSpinner.setOnItemSelectedListener(changeLayoutListener);
    }

    private void setDegRadListeners() {
        AdapterView.OnItemSelectedListener degRadListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                degRadAnswerChoice = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        answerDegRadSpinner1.setOnItemSelectedListener(degRadListener);

        AdapterView.OnItemSelectedListener degRad1Listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                degRad1Choice = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        degRadSpinner1.setOnItemSelectedListener(degRad1Listener);

        AdapterView.OnItemSelectedListener degRad2Listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                degRad2Choice = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        degRadSpinner2.setOnItemSelectedListener(degRad2Listener);
    }

    private void setMainAdapters() {
        ArrayAdapter<CharSequence> inputStyleAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.oblique_area_array, R.layout.spinner_background);
        inputStyleAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        inputStyleSpinner.setAdapter(inputStyleAdapter);

        ArrayAdapter<CharSequence> answerChoiceAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.oblique_initial_calc_array, R.layout.spinner_background);
        answerChoiceAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        answerChoiceSpinner.setAdapter(answerChoiceAdapter);
    }

    private void setDegRadAdapters(){
        ArrayAdapter<CharSequence> degRadAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.degree_rad_array, R.layout.spinner_background);
        degRadAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        degRadSpinner1.setAdapter(degRadAdapter);
        degRadSpinner2.setAdapter(degRadAdapter);
        answerDegRadSpinner1.setAdapter(degRadAdapter);
    }

    private void setInitialLayout() {
        degRad1Layout.setVisibility(View.VISIBLE);
        degRad2Layout.setVisibility(View.VISIBLE);
        input3Layout.setVisibility(View.VISIBLE);
        degRadView1.setText("Angle y");
        degRadView2.setText("Angle z");
        input3view.setText("Side b");
    }

    private void clearLayouts(){
        input1Layout.setVisibility(View.INVISIBLE);
        input2Layout.setVisibility(View.INVISIBLE);
        input3Layout.setVisibility(View.INVISIBLE);
        degRad1Layout.setVisibility(View.INVISIBLE);
        degRad2Layout.setVisibility(View.INVISIBLE);
        input1.setText("");
        input2.setText("");
        input3.setText("");
        degRadInput1.setText("");
        degRadInput2.setText("");
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
