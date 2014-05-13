package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by John on 5/12/2014.
 */
public class TrapezoidFragment extends Fragment {

    private static final String KEY_POSITION="position";

    static TrapezoidFragment newInstance(int position) {
        TrapezoidFragment frag=new TrapezoidFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.trapezoid), position + 1));
    }


    public TrapezoidFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.trapezoid_detail, container, false);
        {

            return rootView;
        }
    }

}
