package com.jwilliams.machinistmate.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by John on 6/1/2014.
 */
public class ConversionPagerActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content,
                            new ConversionPagerFragment()).commit();
        }
    }
}
