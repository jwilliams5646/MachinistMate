package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by john.williams on 5/8/2014.
 */
public class GeometryAdapter extends FragmentPagerAdapter {
    Context ctxt=null;

    public GeometryAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        this.ctxt=ctxt;
    }

    @Override
    public int getCount() {
        return(2);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag;

        switch (position)
        {
            case 0:
                frag = (RightTriangleFragment.newInstance(position));
                break;
            case 1:
                frag = (DrillChartFragment.newInstance(position));
                break;
            default:
                frag = (FractionMetricFragment.newInstance(position));
                break;
        }
        return frag;
    }

    @Override
    public String getPageTitle(int position) {
        String title;

        switch (position)
        {
            case 0:
                title = (RightTriangleFragment.getTitle(ctxt, position));
                break;
            case 1:
                title = (DrillChartFragment.getTitle(ctxt, position));
                break;
            default:
                title = (FractionMetricFragment.getTitle(ctxt, position));
                break;
        }
        return title;
    }

}
