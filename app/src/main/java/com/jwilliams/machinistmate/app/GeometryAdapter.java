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
        return(6);
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
                frag = (ObliqueTriangleFragment.newInstance(position));
                break;
            case 2:
                frag = (CircleFragment.newInstance(position));
                break;
            case 3:
                frag = (SquareFragment.newInstance(position));
                break;
            case 4:
                frag = (TrapezoidFragment.newInstance(position));
                break;
            case 5:
                frag = (ParallelogramFragment.newInstance(position));
                break;
            default:
                frag = (RightTriangleFragment.newInstance(position));
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
                title = (ObliqueTriangleFragment.getTitle(ctxt, position));
                break;
            case 2:
                title = (CircleFragment.getTitle(ctxt, position));
                break;
            case 3:
                title = (SquareFragment.getTitle(ctxt, position));
                break;
            case 4:
                title = (TrapezoidFragment.getTitle(ctxt, position));
                break;
            case 5:
                title = (ParallelogramFragment.getTitle(ctxt, position));
                break;
            default:
                title = (RightTriangleFragment.getTitle(ctxt, position));
                break;
        }
        return title;
    }

}
