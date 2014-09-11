package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jwilliams.machinistmate.app.Fragments.ObliqueTriangleFragment;
import com.jwilliams.machinistmate.app.Fragments.ParallelogramFragment;
import com.jwilliams.machinistmate.app.Fragments.RectangleFragment;
import com.jwilliams.machinistmate.app.Fragments.RightTriangleFragment;
import com.jwilliams.machinistmate.app.Fragments.SquareFragment;
import com.jwilliams.machinistmate.app.Fragments.TrapezoidFragment;

/**
 * Created by john.williams on 5/8/2014.
 */
public class GeometryAdapter extends FragmentStatePagerAdapter {
    //Context ctxt=null;

    public GeometryAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        //this.ctxt=ctxt;
    }

    @Override
    public int getCount() {
        return(7);
    }

    @Override
      public int getItemPosition(Object object){
        return GeometryAdapter.POSITION_NONE;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return (RightTriangleFragment.newInstance(position));
            case 1:
                return (ObliqueTriangleFragment.newInstance(position));
            case 2:
                return (CircleFragment.newInstance(position));
            case 3:
                return (SquareFragment.newInstance(position));
            case 4:
                return (RectangleFragment.newInstance(position));
            case 5:
                return (TrapezoidFragment.newInstance(position));
            case 6:
                return (ParallelogramFragment.newInstance(position));
            default:
                return (RightTriangleFragment.newInstance(position));
        }
    }

    @Override
    public String getPageTitle(int position) {

        switch (position)
        {
            case 0:
                return "Right Triangle";
            case 1:
                return "Oblique Triangle";
            case 2:
                return "Circle";
            case 3:
                return "Square";
            case 4:
                return "Rectangle";
            case 5:
                return "Trapezoid";
            case 6:
                return "Parallelogram";
            default:
                return "Right Triangle";
        }
    }

}
