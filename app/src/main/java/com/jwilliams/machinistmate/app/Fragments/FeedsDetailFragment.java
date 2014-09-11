package com.jwilliams.machinistmate.app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jwilliams.machinistmate.app.AppContent.Formatter;
import com.jwilliams.machinistmate.app.AppContent.RobotoButton;
import com.jwilliams.machinistmate.app.AppContent.RobotoRadioButton;
import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;
import com.jwilliams.machinistmate.app.R;
import com.jwilliams.machinistmate.app.SpeedsandFeedsClasses.Feeds;

/**
 * Created by John on 4/4/2014.
 * <p/>
 * This class is the View-Controller for the Feeds calculation.
 */
public class FeedsDetailFragment extends Fragment {

    private RobotoTextView feedAnswer;
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
    private RadioGroup feedRadioGroup;
    private int precision;
    private static final String TEST_DEVICE_ID = "03f3f1d189532cca";
    private AdView adView;
    private AdRequest adRequest;
    private Feeds feeds;

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
        setAd(rootView);
        setLayoutVariables(rootView);
        setPrecisionListeners();
        setRadioButtonListeners();
        setCalcButtonListener();
        return rootView;
    }

    private void setAd(View rootView){
        adView = (AdView)rootView.findViewById(R.id.feeds_adView);
        adRequest = new AdRequest.Builder()
/*                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(TEST_DEVICE_ID)*/
                .build();
        adView.loadAd(adRequest);
    }

    private void setCalcButtonListener() {
        feedCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                if(validInput()){
                    feedAnswer.setText(Formatter.formatOutput(feeds.getFeedRate(), precision));
                }else{
                    Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
                }
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
        feedRadioGroup = (RadioGroup) rootView.findViewById(R.id.feed_radio_group);
        standardButton = (RobotoRadioButton)rootView.findViewById(R.id.feeds_standard_radio);
        metricButton = (RobotoRadioButton)rootView.findViewById(R.id.feeds_metric_radio);
        feedCalc = (RobotoButton) rootView.findViewById(R.id.feed_calc);
        addButton = (RobotoButton) rootView.findViewById(R.id.feed_add_button);
        minusButton = (RobotoButton) rootView.findViewById(R.id.feed_minus_button);
        feedAnswerType.setText(getText(R.string.ipm));
        precision = 2;
        precisionView.setText(Integer.toString(precision));
    }

    public boolean validInput(){
        feeds = new Feeds();
        boolean valid = true;
        try {
            feeds.setSpeed(Integer.parseInt(feedSpeedInput.getText().toString()));
        } catch (NumberFormatException e) {
            feedSpeedInput.setHint("Invalid");
            valid = false;
        }
        try {
            feeds.setFpt(Double.parseDouble(feedPerToothInput.getText().toString()));
        } catch (NumberFormatException e) {
            feedPerToothInput.setHint("Invalid");
            valid = false;
        }
        try {
            feeds.setNumTeeth(Integer.parseInt(numberTeethInput.getText().toString()));
        } catch (NumberFormatException e) {
            numberTeethInput.setHint("Invalid");
            valid = false;
        }
        return valid;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Destroy the AdView.
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}

