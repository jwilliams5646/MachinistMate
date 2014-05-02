package com.jwilliams.machinistmate.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by john.williams on 3/26/2014.
 * <p/>
 * This class is the View-Controller for the Speeds calculations.
 */
public class SpeedsDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    public static final float TOP_MARGIN_FROM_FLOAT = 25.0f;
    public static final float OTHER_MARGIN_FROM_FLOAT = 4.0f;
    //set private view variables
    private TextView speedAnswer;
    private TextView surfaceType;
    private TextView diameterType;
    private EditText surfaceInput;
    private EditText diameterInput;
    private boolean speedsType;

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

        assert rootView != null;
        RadioGroup speedRadioGroup = (RadioGroup) rootView.findViewById(R.id.speed_radio_group);
        surfaceType = (TextView) rootView.findViewById(R.id.surface_type_view);
        diameterType = (TextView) rootView.findViewById(R.id.diameter_type_view);
        surfaceInput = (EditText) rootView.findViewById(R.id.surfaceInput);
        diameterInput = (EditText) rootView.findViewById(R.id.diameterInput);
        Button speedsCalc = (Button) rootView.findViewById(R.id.speed_calc);
        speedAnswer = (TextView) rootView.findViewById(R.id.speed_answer);
        LinearLayout speedAnswerLayout = (LinearLayout) rootView.findViewById(R.id.speed_answer_layout);

        speedsType = true;

        if (ItemListActivity.mTwoPane) {
            final float SCALE = getActivity().getResources().getDisplayMetrics().density;
            int topMargin = (int) (TOP_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            int x = (int) (OTHER_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams) speedAnswerLayout.getLayoutParams();
            assert params != null;
            params.setMargins(x, topMargin, x, x);
            speedAnswerLayout.setLayoutParams(params);
        }

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

    public void calcSpeed(boolean i) {
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




