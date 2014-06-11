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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jwilliams.machinistmate.app.AppContent.RobotoButton;
import com.jwilliams.machinistmate.app.AppContent.RobotoRadioButton;
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
    private EditText surfaceInput;
    private EditText diameterInput;
    private boolean speedsType;
    private RadioGroup speedRadioGroup;
    private RobotoButton speedsCalc;
    private LinearLayout speedAnswerLayout;
    private RobotoRadioButton standardButton;
    private RobotoRadioButton metricButton;
    private static final String TEST_DEVICE_ID = "03f3f1d189532cca";
    private AdView adView;
    private AdRequest adRequest;

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
        setAd(rootView);
        setLayout(rootView);
        setTwoPane();

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

        speedsCalc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcSpeed(speedsType);
            }
        });

        return rootView;
    }

    private void setAd(View rootView){
        adView = (AdView)rootView.findViewById(R.id.speeds_adView);
        adRequest = new AdRequest.Builder()
/*                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(TEST_DEVICE_ID)*/
                .build();
        adView.loadAd(adRequest);
    }

    private void setLayout(View rootView){
        speedRadioGroup = (RadioGroup) rootView.findViewById(R.id.speed_radio_group);
        surfaceType = (RobotoTextView) rootView.findViewById(R.id.surface_view);
        surfaceInput = (EditText) rootView.findViewById(R.id.surfaceInput);
        diameterInput = (EditText) rootView.findViewById(R.id.diameterInput);
        speedsCalc = (RobotoButton) rootView.findViewById(R.id.speed_calc);
        speedAnswer = (RobotoTextView) rootView.findViewById(R.id.speed_answer);
        speedAnswerLayout = (LinearLayout) rootView.findViewById(R.id.speed_answer_layout);
        standardButton = (RobotoRadioButton)rootView.findViewById(R.id.speeds_standard_radio);
        metricButton = (RobotoRadioButton)rootView.findViewById(R.id.speeds_metric_radio);

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
        double surface = 0.0;
        double diameter = 0.0;
        int speed = 0;
        boolean notValid = false;

        try {
            surface = Double.parseDouble(surfaceInput.getText().toString());
        } catch (NumberFormatException e) {
            surfaceInput.setHint("Invalid");
            notValid = true;
        }

        try {
            diameter = Double.parseDouble(diameterInput.getText().toString());
        } catch (NumberFormatException e) {
            diameterInput.setHint("Invalid");
            notValid = true;
        }

        if (notValid) {
            Toast.makeText(getActivity(), "One or more inputs are invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (i) {
            speed = (int) ((surface * 3.82) / diameter);
            speedAnswer.setText(Integer.toString(speed));
        } else {
            speed = (int) ((surface * 320) / diameter);
            speedAnswer.setText(Integer.toString(speed));
        }
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

    /** Called before the activity is destroyed. */
    @Override
    public void onDestroy() {
        // Destroy the AdView.
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}




