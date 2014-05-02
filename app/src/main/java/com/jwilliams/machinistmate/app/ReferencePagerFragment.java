package com.jwilliams.machinistmate.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
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
        View result = inflater.inflate(R.layout.reference_pager, container, false);
        ViewPager pager = (ViewPager) result.findViewById(R.id.pager);

        pager.setAdapter(buildAdapter());

        return (result);
    }
    private PagerAdapter buildAdapter() {
        return(new ReferenceAdapter(getActivity(), getChildFragmentManager()));
    }
}
