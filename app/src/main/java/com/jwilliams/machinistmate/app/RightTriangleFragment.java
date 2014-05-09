package com.jwilliams.machinistmate.app;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.jwilliams.machinistmate.app.AppContent.RobotoTextView;


/**
 * Created by john.williams on 5/8/2014.
 */
public class RightTriangleFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    private static final String KEY_POSITION="position";

    private ImageView triangle;
    private RobotoTextView rtAnswer;
    private Spinner rtSpinner;
    private EditText rtDegreeRadianInput;
    private EditText rtOppositeInput;
    private EditText rtHypotenuseInput;
    private EditText rtAdjacentInput;
    private EditText rtBaseInput;
    private EditText rtHeightInput;
    private Spinner rtDegreeRadianSpinner;
    private LinearLayout rtDegreeRadianLayout;
    private LinearLayout rtOppositeLayout;
    private LinearLayout rtHypotenuseLayout;
    private LinearLayout rtAdjacentLayout;
    private LinearLayout rtBaseLayout;
    private LinearLayout rtHeightLayout;
    private LinearLayout rtOrLayout;
    private int rtSpinnerPosition;
    private int rtDegreeRadianPosition;

    static RightTriangleFragment newInstance(int position) {
        RightTriangleFragment frag=new RightTriangleFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.right_triangle_calc), position + 1));
    }


    public RightTriangleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.right_triangle_detail, container, false);

        setLayout(rootView);
        setSpinnerAdapters();

        AdapterView.OnItemSelectedListener rtItemSelectedListener = new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        rtSpinnerPosition = 0;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        rtOrLayout.setVisibility(View.VISIBLE);
                        rtOppositeLayout.setVisibility(View.VISIBLE);
                        rtHypotenuseLayout.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        rtSpinnerPosition = 1;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        rtSpinnerPosition = 2;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        rtOrLayout.setVisibility(View.VISIBLE);
                        rtOppositeLayout.setVisibility(View.VISIBLE);
                        rtHypotenuseLayout.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        rtSpinnerPosition = 3;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        rtSpinnerPosition = 4;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        rtOrLayout.setVisibility(View.VISIBLE);
                        rtOppositeLayout.setVisibility(View.VISIBLE);
                        rtAdjacentLayout.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        rtSpinnerPosition = 5;
                        clearLayouts();
                        rtDegreeRadianLayout.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        rtSpinnerPosition = 6;
                        clearLayouts();
                        rtBaseLayout.setVisibility(View.VISIBLE);
                        rtHeightLayout.setVisibility(View.VISIBLE);
                        break;
                    default:
                        rtSpinnerPosition = 0;
                        clearLayouts();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rtSpinnerPosition = 0;
                clearLayouts();
            }
        };
        rtSpinner.setOnItemSelectedListener(rtItemSelectedListener);

        return rootView;
    }

    private void setSpinnerAdapters() {
        ArrayAdapter<CharSequence> rtAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.rt_array, R.layout.spinner_background);
        rtAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        rtSpinner.setAdapter(rtAdapter);

        ArrayAdapter<CharSequence> rtDRAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.rt_degree_rad_array, R.layout.spinner_background);
        rtDRAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        rtDegreeRadianSpinner.setAdapter(rtDRAdapter);
    }

    private void setLayout(View rootView) {
        triangle = (ImageView)rootView.findViewById(R.id.right_triangle_imageView);
        rtAnswer = (RobotoTextView)rootView.findViewById(R.id.rt_answer);
        rtSpinner = (Spinner)rootView.findViewById(R.id.rt_spinner);
        rtDegreeRadianInput = (EditText)rootView.findViewById(R.id.rt_degree_radian_input);
        rtOppositeInput = (EditText)rootView.findViewById(R.id.rt_opposite_input);
        rtHypotenuseInput = (EditText)rootView.findViewById(R.id.rt_hypotenuse_input);
        rtAdjacentInput = (EditText)rootView.findViewById(R.id.rt_adjacent_input);
        rtBaseInput = (EditText)rootView.findViewById(R.id.rt_base_input);
        rtHeightInput = (EditText)rootView.findViewById(R.id.rt_height_input);
        rtDegreeRadianSpinner = (Spinner)rootView.findViewById(R.id.rt_degree_radian_spinner);
        rtDegreeRadianLayout = (LinearLayout)rootView.findViewById(R.id.rt_degree_radian_layout);
        rtOppositeLayout = (LinearLayout)rootView.findViewById(R.id.rt_opposite_layout);
        rtHypotenuseLayout = (LinearLayout)rootView.findViewById(R.id.rt_hypotenuse_layout);
        rtAdjacentLayout = (LinearLayout)rootView.findViewById(R.id.rt_adjacent_layout);
        rtBaseLayout = (LinearLayout)rootView.findViewById(R.id.rt_base_layout);
        rtHeightLayout = (LinearLayout)rootView.findViewById(R.id.rt_height_layout);
        rtOrLayout = (LinearLayout)rootView.findViewById(R.id.rt_or_layout);
    }
    private void clearLayouts(){
        rtDegreeRadianLayout.setVisibility(View.INVISIBLE);
        rtOppositeLayout.setVisibility(View.INVISIBLE);
        rtHypotenuseLayout.setVisibility(View.INVISIBLE);
        rtAdjacentLayout.setVisibility(View.INVISIBLE);
        rtBaseLayout.setVisibility(View.INVISIBLE);
        rtHeightLayout.setVisibility(View.INVISIBLE);
        rtOrLayout.setVisibility(View.INVISIBLE);
    }
}
