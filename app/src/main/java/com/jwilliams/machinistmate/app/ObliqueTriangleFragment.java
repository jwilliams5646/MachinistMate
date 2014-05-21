package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

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
    private Spinner inputChoiceSpinner;
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
        answerChoiceSpinner = (Spinner)rootView.findViewById(R.id.oblique_answer_degrad_spinner);
        degRadSpinner1 = (Spinner)rootView.findViewById(R.id.oblique_deg_rad_choice_spinner1);
        degRadSpinner2 = (Spinner)rootView.findViewById(R.id.oblique_deg_rad_choice_spinner2);
        inputChoiceSpinner = (Spinner)rootView.findViewById(R.id.oblique_choice_spinner);
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
