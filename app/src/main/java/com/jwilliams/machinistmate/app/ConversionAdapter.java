package com.jwilliams.machinistmate.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jwilliams.machinistmate.app.Fragments.LengthFragment;
import com.jwilliams.machinistmate.app.Fragments.VolumeFragment;

/**
 * Created by John on 6/1/2014.
 */
public class ConversionAdapter extends FragmentPagerAdapter {
    Context ctxt=null;

    public ConversionAdapter(Context ctxt, FragmentManager mgr) {
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
                frag = (LengthFragment.newInstance(position));
                break;
            case 1:
                frag = (VolumeFragment.newInstance(position));
                break;
            default:
                frag = (LengthFragment.newInstance(position));
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
                title = (LengthFragment.getTitle(ctxt, position));
                break;
            case 1:
                title = (VolumeFragment.getTitle(ctxt, position));
                break;
            default:
                title = (LengthFragment.getTitle(ctxt, position));
                break;
        }
        return title;
    }

}
