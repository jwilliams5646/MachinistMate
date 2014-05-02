package com.jwilliams.machinistmate.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by John on 4/27/2014.
 */
public class ReferencePagerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reference_pager, container, false);
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) rootView.findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(Color.parseColor("#0099CC"));

        pager.setAdapter(buildAdapter());

        return rootView;
    }
    private PagerAdapter buildAdapter() {
        return(new ReferenceAdapter(getActivity(), getChildFragmentManager()));
    }
}
