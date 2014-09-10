package com.jwilliams.machinistmate.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jwilliams.machinistmate.app.AppContent.Calculations;
import com.jwilliams.machinistmate.app.AppContent.RobotoButton;
import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;
import com.jwilliams.machinistmate.app.GeometryClasses.Circle;
import com.jwilliams.machinistmate.app.GeometryClasses.ShowImage;
import com.squareup.picasso.Picasso;

/**
 * Created by John Williams on 5/20/2014.
 * Contents:
 * View-Controller for the circle calculations in the Geometry view-pager.
 */
public class CircleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private EditText input;
    private double inputValue;
    private RobotoTextView circleView;
    private RobotoTextView answer;
    private RobotoTextView precisionView;
    private Spinner answerSpinner;
    private Spinner radiusSpinner;
    private RobotoButton calcButton;
    private RobotoButton addButton;
    private RobotoButton minusButton;
    private int pos;
    private int radiusChoice;
    private int precision;
    private ArrayAdapter<CharSequence> circleAdapter;
    private static final String TEST_DEVICE_ID = "03f3f1d189532cca";
    private AdView adView;
    private AdRequest adRequest;


    public CircleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.circle_detail, container, false);
        setCircle(rootView);
        setAd(rootView);
        initializeLayout(rootView);
        initialLayout();
        setCircleSpinnerAdapter();
        setCircleRadiusAdapter();
        setCircleSpinnerListener();
        setCircleRadiusListener();
        setCalcListener();
        setPrecisionListeners();

        return rootView;
    }

    private void setCircle(View rootView) {
        ImageView image = (ImageView)rootView.findViewById(R.id.circle_image);
        Picasso.with(getActivity())
                .load(R.drawable.circle)
                .fit()
                .centerInside()
                .into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowImage enlarge = new ShowImage(getActivity(),R.drawable.circle);
                enlarge.setDialog();
            }
        });
    }

    private void setAd(View rootView){
        adView = (AdView)rootView.findViewById(R.id.c_adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(TEST_DEVICE_ID)
                .build();
        adView.loadAd(adRequest);
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
                if (precision > 1) {
                    precision--;
                    precisionView.setText(Integer.toString(precision));
                } else {
                    Toast.makeText(getActivity(), "You can't go down any farther.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setCalcListener() {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validInput()) {
                    Circle circle = new Circle(inputValue);
                    switch (pos) {
                        case 0:
                            switch (radiusChoice) {
                                case 0:
                                    answer.setText(Calculations.formatter(circle.calcRadDiam(), precision));
                                    break;
                                case 1:
                                    answer.setText(Calculations.formatter(circle.calcRadArea(), precision));
                                    break;
                                case 2:
                                    answer.setText(Calculations.formatter(circle.calcRadCirc(), precision));
                                    break;
                            }
                            break;
                        case 1:
                            answer.setText(Calculations.formatter(circle.calcDiameter(), precision));
                            break;
                        case 2:
                            answer.setText(Calculations.formatter(circle.calcArea(), precision));
                            break;
                        case 3:
                            answer.setText(Calculations.formatter(circle.calcCircumference(), precision));
                            break;
                        default:
                            break;
                    }
                }else{
                    Toast.makeText(getActivity(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validInput(){
        try{
            inputValue = Double.parseDouble(input.getText().toString());
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    private void initialLayout(){
        circleView.setVisibility(View.GONE);
        radiusSpinner.setVisibility(View.VISIBLE);
    }

    private void setCircleSpinnerListener() {
        AdapterView.OnItemSelectedListener circleSpinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                if(i==0) {
                    radiusSpinner.setSelection(0);
                    initialLayout();
                }else{
                    circleView.setText("Radius");
                    circleView.setVisibility(View.VISIBLE);
                    radiusSpinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        answerSpinner.setOnItemSelectedListener(circleSpinnerListener);
    }

    private void setCircleSpinnerAdapter() {
        circleAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.circle_calc_array, R.layout.spinner_background);
        circleAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        answerSpinner.setAdapter(circleAdapter);
    }

    private void setCircleRadiusListener(){
        AdapterView.OnItemSelectedListener circleRadiusListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                radiusChoice = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        radiusSpinner.setOnItemSelectedListener(circleRadiusListener);
    }

    private void setCircleRadiusAdapter() {
        ArrayAdapter<CharSequence> circleRadiusAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.circle_radius_array, R.layout.spinner_background);
        circleRadiusAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        radiusSpinner.setAdapter(circleRadiusAdapter);
    }

    private void initializeLayout(View rootView) {
        input = (EditText)rootView.findViewById(R.id.circle_input);
        circleView = (RobotoTextView)rootView.findViewById(R.id.circle_view);
        precisionView = (RobotoTextView)rootView.findViewById(R.id.c_precision_view);
        answer = (RobotoTextView)rootView.findViewById(R.id.circle_answer);
        answerSpinner = (Spinner)rootView.findViewById(R.id.circle_choice);
        radiusSpinner = (Spinner)rootView.findViewById(R.id.circle_radius_choice);
        calcButton = (RobotoButton)rootView.findViewById(R.id.c_calc);
        addButton = (RobotoButton)rootView.findViewById(R.id.c_add_button);
        minusButton = (RobotoButton)rootView.findViewById(R.id.c_minus_button);
        pos = 0;
        radiusChoice = 0;
        precision = 2;
        precisionView.setText(Integer.toString(precision));
    }

    static CircleFragment newInstance(int position) {
        CircleFragment frag=new CircleFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }
    @Override
    public void onPause(){
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
        circleView = null;
        precisionView = null;
        answer = null;
        answerSpinner = null;
        radiusSpinner = null;
        calcButton = null;
        addButton = null;
        minusButton = null;
        circleAdapter = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
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
