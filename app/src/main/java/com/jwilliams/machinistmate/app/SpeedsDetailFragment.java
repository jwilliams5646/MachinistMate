package com.jwilliams.machinistmate.app;

import android.graphics.Typeface;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;


/**
 * Created by john.williams on 3/26/2014.
 * <p/>
 * This class is the View-Controller for the Speeds calculations.
 */
public class SpeedsDetailFragment extends Fragment {

    public static final float TOP_MARGIN_FROM_FLOAT = 25.0f;
    public static final float OTHER_MARGIN_FROM_FLOAT = 4.0f;
    //set private view variables
    private RobotoTextView speedAnswer;
    private RobotoTextView surfaceType;
    private RobotoTextView diameterType;
    private EditText surfaceInput;
    private EditText diameterInput;
    private boolean speedsType;
    private Typeface tf;
    private RadioGroup speedRadioGroup;
    private Button speedsCalc;
    private LinearLayout speedAnswerLayout;
    private RadioButton standardButton;
    private RadioButton metricButton;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpeedsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.speeds_item_detail, container, false);

        setLayout(rootView);
        setTwoPane();

        speedRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.speeds_standard_radio:
                        surfaceType.setText(getText(R.string.surfaceFeet));
                        diameterType.setText(getText(R.string.in));
                        speedsType = true;
                        break;
                    case R.id.speeds_metric_radio:
                        surfaceType.setText(getText(R.string.surfaceMeters));
                        diameterType.setText(getText(R.string.mm));
                        speedsType = false;
                        break;
                }
            }
        });

        speedsCalc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcSpeed(speedsType);
            }
        });

        return rootView;
    }

    private void setLayout(View rootView){
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        speedRadioGroup = (RadioGroup) rootView.findViewById(R.id.speed_radio_group);
        surfaceType = (RobotoTextView) rootView.findViewById(R.id.surface_type_view);
        diameterType = (RobotoTextView) rootView.findViewById(R.id.diameter_type_view);
        surfaceInput = (EditText) rootView.findViewById(R.id.surfaceInput);
        diameterInput = (EditText) rootView.findViewById(R.id.diameterInput);
        speedsCalc = (Button) rootView.findViewById(R.id.speed_calc);
        speedAnswer = (RobotoTextView) rootView.findViewById(R.id.speed_answer);
        speedAnswerLayout = (LinearLayout) rootView.findViewById(R.id.speed_answer_layout);
        standardButton = (RadioButton)rootView.findViewById(R.id.speeds_standard_radio);
        metricButton = (RadioButton)rootView.findViewById(R.id.speeds_metric_radio);

        speedsCalc.setTypeface(tf);
        standardButton.setTypeface(tf);
        metricButton.setTypeface(tf);

        speedsType = true;
    }

    private void setTwoPane(){
        if (ItemListActivity.mTwoPane) {
            final float SCALE = getActivity().getResources().getDisplayMetrics().density;
            int topMargin = (int) (TOP_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            int x = (int) (OTHER_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams) speedAnswerLayout.getLayoutParams();
            assert params != null;
            params.setMargins(x, topMargin, x, x);
            speedAnswerLayout.setLayoutParams(params);
        }
    }

    private void calcSpeed(boolean i) {
        speedAnswer.setText("");
        double surfaceIn = 0.0;
        double diameter1 = 0.0;
        int speed = 0;
        boolean notValid = false;

        try {
            surfaceIn = Double.parseDouble(surfaceInput.getText().toString());
        } catch (NumberFormatException e) {
            surfaceInput.setHint("Invalid");
            notValid = true;
        }

        try {
            diameter1 = Double.parseDouble(diameterInput.getText().toString());
        } catch (NumberFormatException e) {
            diameterInput.setHint("Invalid");
            notValid = true;
        }

        if (notValid) {
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (i) {
            speed = (int) ((surfaceIn * 3.82) / diameter1);
            speedAnswer.setText(Integer.toString(speed));
        } else {
            speed = (int) ((surfaceIn * 320) / diameter1);
            speedAnswer.setText(Integer.toString(speed));
        }
    }
}




