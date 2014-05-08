package com.jwilliams.machinistmate.app;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



/**
 * Created by john.williams on 5/8/2014.
 */
public class RightTriangleFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    private static final String KEY_POSITION="position";

    private ImageView triangle;

    static RightTriangleFragment newInstance(int position) {
        RightTriangleFragment frag=new RightTriangleFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.right_triangle_calc), position + 1));
    }


    public RightTriangleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.right_triangle_detail, container, false);

        triangle = (ImageView)rootView.findViewById(R.id.right_triangle_imageView);

        return rootView;
    }
}
