package com.jwilliams.machinistmate.app.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jwilliams.machinistmate.app.ConversionAdapter;
import com.jwilliams.machinistmate.app.R;

/**
 * Created by John on 6/1/2014.
 */
public class ConversionPagerFragment extends Fragment {
    Typeface tf;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pager, container, false);
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);

        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");

        PagerTabStrip pagerTabStrip = (PagerTabStrip) rootView.findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(Color.parseColor("#0099CC"));

        for (int i = 0; i < pagerTabStrip.getChildCount(); ++i) {
            View nextChild = pagerTabStrip.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(tf);
            }
        }

        pager.setAdapter(buildAdapter());


        return rootView;
    }
    private PagerAdapter buildAdapter() {
        return(new ConversionAdapter(getActivity(), getChildFragmentManager()));
    }
}
