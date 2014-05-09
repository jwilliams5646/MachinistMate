package com.jwilliams.machinistmate.app.AppContent;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by john.williams on 5/5/2014.
 */
public class RobotoTextView extends TextView {
    public RobotoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            try {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
                setTypeface(tf);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
