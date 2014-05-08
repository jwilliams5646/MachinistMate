package com.jwilliams.machinistmate.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by john.williams on 5/8/2014.
 */
public class GeometryPagerActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content,
                            new GeometryPagerFragment()).commit();
        }
    }
}

