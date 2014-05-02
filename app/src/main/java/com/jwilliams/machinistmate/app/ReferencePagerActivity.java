package com.jwilliams.machinistmate.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by John on 4/27/2014.
 */
public class ReferencePagerActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content,
                            new ReferencePagerFragment()).commit();
        }
    }
}
