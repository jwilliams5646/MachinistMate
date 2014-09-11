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
import com.jwilliams.machinistmate.app.SpeedsandFeedsClasses.Speeds;


/**
 * Created by John Williams on 3/26/2014.
 * This class is the View-Controller for Speeds.
 */
public class SpeedsDetailFragment extends Fragment {

    private RobotoTextView speedAnswer;
    private RobotoTextView surfaceType;
    private EditText surfaceInput;
    private EditText diameterInput;
    private boolean speedsType;
    private RadioGroup speedRadioGroup;
    private RobotoButton speedsCalc;
    private RobotoRadioButton standardButton;
    private RobotoRadioButton metricButton;
    private static final String TEST_DEVICE_ID = "03f3f1d189532cca";
    private AdView adView;
    private AdRequest adRequest;
    Speeds speeds;

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
        setAd(rootView);
        setLayout(rootView);
        setRadioGroupListener();
        setCalcButtonListener();
        return rootView;
    }

    private void setCalcButtonListener() {
        speedsCalc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validInput()) {
                    speedAnswer.setText(Formatter.formatOutput(speeds.getSpeed(), 0));
                } else {
                    Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setRadioGroupListener() {
        speedRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.speeds_standard_radio:
                        surfaceType.setText(getText(R.string.surface_standard));
                        diameterInput.setHint(getText(R.string.in));
                        speedsType = true;
                        break;
                    case R.id.speeds_metric_radio:
                        surfaceType.setText(getText(R.string.surface_metric));
                        diameterInput.setHint(getText(R.string.mm));
                        speedsType = false;
                        break;
                }
            }
        });
    }

    private void setAd(View rootView) {
        adView = (AdView) rootView.findViewById(R.id.speeds_adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(TEST_DEVICE_ID)
                .build();
        adView.loadAd(adRequest);
    }

    private void setLayout(View rootView) {
        speedRadioGroup = (RadioGroup) rootView.findViewById(R.id.speed_radio_group);
        surfaceType = (RobotoTextView) rootView.findViewById(R.id.surface_view);
        surfaceInput = (EditText) rootView.findViewById(R.id.surfaceInput);
        diameterInput = (EditText) rootView.findViewById(R.id.diameterInput);
        speedsCalc = (RobotoButton) rootView.findViewById(R.id.speed_calc);
        speedAnswer = (RobotoTextView) rootView.findViewById(R.id.speed_answer);
        standardButton = (RobotoRadioButton) rootView.findViewById(R.id.speeds_standard_radio);
        metricButton = (RobotoRadioButton) rootView.findViewById(R.id.speeds_metric_radio);
        speedsType = true;
    }

    private boolean validInput() {
        speeds = new Speeds(speedsType);
        boolean valid = true;

        try {
            speeds.setSurface(Double.parseDouble(surfaceInput.getText().toString()));
        } catch (NumberFormatException e) {
            surfaceInput.setHint("Invalid");
            valid = false;
        }

        try {
            speeds.setDiameter(Double.parseDouble(diameterInput.getText().toString()));
        } catch (NumberFormatException e) {
            diameterInput.setHint("Invalid");
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




