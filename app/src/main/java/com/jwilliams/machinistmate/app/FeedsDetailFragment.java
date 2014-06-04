package com.jwilliams.machinistmate.app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jwilliams.machinistmate.app.AppContent.Calculations;
import com.jwilliams.machinistmate.app.AppContent.RobotoButton;
import com.jwilliams.machinistmate.app.AppContent.RobotoRadioButton;
import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;

/**
 * Created by John on 4/4/2014.
 * <p/>
 * This class is the View-Controller for the Feeds calculation.
 */
public class FeedsDetailFragment extends Fragment {

    private RobotoTextView feedAnswer;
    private boolean feedType;
    private EditText feedSpeedInput;
    private EditText feedPerToothInput;
    private EditText numberTeethInput;
    private RobotoTextView feedAnswerType;
    private RobotoTextView precisionView;
    private RobotoButton feedCalc;
    private RobotoButton addButton;
    private RobotoButton minusButton;
    private RobotoRadioButton standardButton;
    private RobotoRadioButton metricButton;
    private LinearLayout feedAnswerLayout;
    private RadioGroup feedRadioGroup;
    private int precision;

    public FeedsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feeds_item_detail, container, false);

        setLayoutVariables(rootView);
        setTwoPane();
        setPrecisionListeners();
        setRadioButtonListeners();
        setCalcButtonListener();
        return rootView;
    }

    private void setCalcButtonListener() {
        feedCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                calcFeed();
            }
        });
    }

    private void setRadioButtonListeners() {
        feedRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.feeds_standard_radio:
                        feedAnswerType.setText(getText(R.string.ipm));
                        feedPerToothInput.setHint(R.string.in);
                        break;
                    case R.id.feeds_metric_radio:
                        feedAnswerType.setText(getText(R.string.mmpm));
                        feedPerToothInput.setHint(R.string.mm);
                        break;
                }
            }});
    }

    private void setPrecisionListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(precision < 6) {
                    precision++;
                    precisionView.setText(Integer.toString(precision));
                }else{
                    Toast.makeText(getActivity(), "Max precision reached.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(precision > 1) {
                    precision--;
                    precisionView.setText(Integer.toString(precision));
                }else{
                    Toast.makeText(getActivity(), "You can't go down any farther.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setLayoutVariables(View rootView){
        feedAnswer = (RobotoTextView) rootView.findViewById(R.id.feed_answer);
        feedSpeedInput = (EditText) rootView.findViewById(R.id.feed_speed_input);
        feedPerToothInput = (EditText) rootView.findViewById(R.id.feed_per_tooth_input);
        numberTeethInput = (EditText) rootView.findViewById(R.id.number_teeth_input);
        feedAnswerType = (RobotoTextView) rootView.findViewById(R.id.feed_answer_type);
        precisionView = (RobotoTextView) rootView.findViewById(R.id.feed_precision_view);
        feedAnswerLayout = (LinearLayout) rootView.findViewById(R.id.feed_answer_layout);
        feedRadioGroup = (RadioGroup) rootView.findViewById(R.id.feed_radio_group);
        standardButton = (RobotoRadioButton)rootView.findViewById(R.id.feeds_standard_radio);
        metricButton = (RobotoRadioButton)rootView.findViewById(R.id.feeds_metric_radio);
        feedCalc = (RobotoButton) rootView.findViewById(R.id.feed_calc);
        addButton = (RobotoButton) rootView.findViewById(R.id.feed_add_button);
        minusButton = (RobotoButton) rootView.findViewById(R.id.feed_minus_button);
        feedAnswerType.setText(getText(R.string.ipm));
        feedType = true;
        precision = 2;
        precisionView.setText(Integer.toString(precision));
    }

    private void setTwoPane(){
        if (ItemListActivity.mTwoPane) {
            final float SCALE = getActivity().getResources().getDisplayMetrics().density;
            int topMargin = (int) (SpeedsDetailFragment.TOP_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            int x = (int) (SpeedsDetailFragment.OTHER_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams) feedAnswerLayout.getLayoutParams();
            assert params != null;
            params.setMargins(x, topMargin, x, x);
            feedAnswerLayout.setLayoutParams(params);
        }
    }

    public void calcFeed() {
        int speed = 0;
        double fpt = 0.0;
        int numTeeth = 0;
        boolean notValid = false;

        try {
            speed = Integer.parseInt(feedSpeedInput.getText().toString());
        } catch (NumberFormatException e) {
            feedSpeedInput.setHint("Invalid");
            notValid = true;
        }
        try {
            fpt = Double.parseDouble(feedPerToothInput.getText().toString());
        } catch (NumberFormatException e) {
            feedPerToothInput.setHint("Invalid");
            notValid = true;
        }
        try {
            numTeeth = Integer.parseInt(numberTeethInput.getText().toString());
        } catch (NumberFormatException e) {
            numberTeethInput.setHint("Invalid");
            notValid = true;
        }
        if (notValid) {
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        feedAnswer.setText(Calculations.formatter(speed * fpt * numTeeth, precision));
    }
}

