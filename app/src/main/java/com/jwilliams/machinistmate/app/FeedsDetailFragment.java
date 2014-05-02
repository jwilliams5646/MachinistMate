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

import java.text.DecimalFormat;
import java.text.NumberFormat;

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
    public static final String ARG_ITEM_ID = "item_id";

    //variables for views from layout
    private TextView feedAnswer;
    private boolean feedType;
    private EditText feedSpeedInput;
    private EditText feedPerToothInput;
    private EditText numberTeethInput;
    private TextView feedAnswerType;
    private TextView feedPerToothType;

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

        assert rootView != null;
        feedAnswer = (TextView) rootView.findViewById(R.id.feed_answer);
        feedSpeedInput = (EditText) rootView.findViewById(R.id.feed_speed_input);
        feedPerToothInput = (EditText) rootView.findViewById(R.id.feed_per_tooth_input);
        numberTeethInput = (EditText) rootView.findViewById(R.id.number_teeth_input);
        feedAnswerType = (TextView) rootView.findViewById(R.id.feed_answer_type);
        feedPerToothType = (TextView) rootView.findViewById(R.id.feed_per_tooth_type);
        LinearLayout feedAnswerLayout = (LinearLayout) rootView.findViewById(R.id.feed_answer_layout);

        RadioGroup feedRadioGroup = (RadioGroup) rootView.findViewById(R.id.feed_radio_group);
        Button feedCalc = (Button) rootView.findViewById(R.id.feed_calc);

        if (ItemListActivity.mTwoPane) {
            final float SCALE = getActivity().getResources().getDisplayMetrics().density;
            int topMargin = (int) (SpeedsDetailFragment.TOP_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            int x = (int) (SpeedsDetailFragment.OTHER_MARGIN_FROM_FLOAT * SCALE + 0.5f);
            LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams) feedAnswerLayout.getLayoutParams();
            assert params != null;
            params.setMargins(x, topMargin, x, x);
            feedAnswerLayout.setLayoutParams(params);
        }

        feedAnswerType.setText(getText(R.string.feedType1));
        feedType = true;

        feedRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.feeds_standard_radio:
                        feedAnswerType.setText(getText(R.string.feedType1));
                        feedPerToothType.setText(R.string.in);
                        feedType = true;
                        break;
                    case R.id.feeds_metric_radio:
                        feedAnswerType.setText(getText(R.string.feedType2));
                        feedPerToothType.setText(R.string.mm);
                        feedType = false;
                        break;
                }
            }
        });

        feedCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                calcFeed();
            }
        });

        return rootView;
    }

    public void calcFeed() {
        int speed = 0;
        double fpt = 0.0;
        int numTeeth = 0;
        //double feed = 0.0;
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

        DecimalFormat df = new DecimalFormat("##.###");

        if (feedType) {
            feedAnswer.setText(df.format(feed));
        } else {
            feedAnswer.setText(df.format(feed));
        }
    }
}

