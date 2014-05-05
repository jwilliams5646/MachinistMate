package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by John on 4/20/2014.
 */
public class FractionMetricFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    private static final String KEY_POSITION="position";

    static FractionMetricFragment newInstance(int position) {
        FractionMetricFragment frag=new FractionMetricFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.fraction_metric_chart), position + 1));
    }

    GridView referenceGridView;

    public FractionMetricFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fraction_metric_detail, container, false);

        referenceGridView = (GridView)rootView.findViewById(R.id.chart_reference_grid);

        ArrayAdapter<CharSequence> metricStandardAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.fraction_metric_list, R.layout.grid_item_layout);
        referenceGridView.setAdapter(metricStandardAdapter);

        return rootView;
    }


}
