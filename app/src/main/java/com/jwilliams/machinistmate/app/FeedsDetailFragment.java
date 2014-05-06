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

import com.jwilliams.machinistmate.app.AppContent.Utility;
import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;

/**
 * Created by John on 4/4/2014.
 * <p/>
 * This class is the View-Controller for the Feeds calculation.
 */
public class FeedsDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    //public static final String ARG_ITEM_ID = "item_id";

    //variables for views from layout
    private RobotoTextView feedAnswer;
    private boolean feedType;
    private EditText feedSpeedInput;
    private EditText feedPerToothInput;
    private EditText numberTeethInput;
    private RobotoTextView feedAnswerType;
    private RobotoTextView feedPerToothType;
    private Button feedCalc;
    private RadioButton standardButton;
    private RadioButton metricButton;
    private LinearLayout feedAnswerLayout;
    private RadioGroup feedRadioGroup;
    private Spinner feedPrecisionSpinner;
    public static Typeface tf;
    int precSpinner;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
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
        setPrecisionAdapter();

        feedRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.feeds_standard_radio:
                        feedAnswerType.setText(getText(R.string.ipm));
                        feedPerToothType.setText(R.string.in);
                        break;
                    case R.id.feeds_metric_radio:
                        feedAnswerType.setText(getText(R.string.mmpm));
                        feedPerToothType.setText(R.string.mm);
                        break;
                }
            }
        });

        feedCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                calcFeed();
            }
        });

        AdapterView.OnItemSelectedListener convPrecisionSelectedListener = new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> spinner, View container,
                                       int position, long id) {
                precSpinner = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                precSpinner = 1;
            }
        };

        feedPrecisionSpinner.setOnItemSelectedListener(convPrecisionSelectedListener);

        return rootView;
    }

    private void setLayoutVariables(View rootView){
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        feedAnswer = (RobotoTextView) rootView.findViewById(R.id.feed_answer);
        feedSpeedInput = (EditText) rootView.findViewById(R.id.feed_speed_input);
        feedPerToothInput = (EditText) rootView.findViewById(R.id.feed_per_tooth_input);
        numberTeethInput = (EditText) rootView.findViewById(R.id.number_teeth_input);
        feedAnswerType = (RobotoTextView) rootView.findViewById(R.id.feed_answer_type);
        feedPerToothType = (RobotoTextView) rootView.findViewById(R.id.feed_per_tooth_type);
        feedAnswerLayout = (LinearLayout) rootView.findViewById(R.id.feed_answer_layout);
        feedRadioGroup = (RadioGroup) rootView.findViewById(R.id.feed_radio_group);
        standardButton = (RadioButton)rootView.findViewById(R.id.feeds_standard_radio);
        metricButton = (RadioButton)rootView.findViewById(R.id.feeds_metric_radio);
        feedCalc = (Button) rootView.findViewById(R.id.feed_calc);
        feedPrecisionSpinner = (Spinner)rootView.findViewById(R.id.feed_prec_spinner);
        standardButton.setTypeface(tf);
        metricButton.setTypeface(tf);
        feedCalc.setTypeface(tf);
        feedAnswerType.setText(getText(R.string.ipm));
        feedType = true;
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

    private void setPrecisionAdapter(){
        ArrayAdapter<CharSequence> precAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.precision_array, R.layout.spinner_background);
        precAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        feedPrecisionSpinner.setAdapter(precAdapter);
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

        double feed = (speed * fpt * numTeeth);

        feedAnswer.setText(Double.toString(Utility.formatter(feed, precSpinner)));
    }


}

