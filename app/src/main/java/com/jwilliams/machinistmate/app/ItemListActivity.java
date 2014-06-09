package com.jwilliams.machinistmate.app;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details
 * (if present) is a {@link ItemDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ItemListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity
        implements ItemListFragment.Callbacks {

    private Intent intent;
    private Fragment frag;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    public static boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);


        getActionBar().setDisplayShowTitleEnabled(false);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;


            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }

        if(mTwoPane){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link ItemListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        int input = Integer.parseInt(id);
        //check for two panes, sign of a large screen or tablet
        if (mTwoPane) {
            // Replaces fragment in the detail container
            //instantiate fragments by id

            switch (input) {
                case 1:
                    frag = new SpeedsDetailFragment();
                    break;

                case 2:
                    frag = new FeedsDetailFragment();
                    break;

                case 3:
                    frag = new GMFragment();
                    break;

                case 4:
                    frag = new ConversionPagerFragment();
                    break;

                case 5:
                    frag = new GeometryPagerFragment();
                    break;

                case 6:
                    frag = new ReferencePagerFragment();
                    break;

                default:
                    frag = new ItemDetailFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, frag)
                    .commit();


        } else {
            //if not two pane then set intent to change activities between menu items

            //instantiate activities by id
            switch (input) {
                case 1:
                    intent = new Intent(this, SpeedsDetailActivity.class);
                    break;

                case 2:
                    intent = new Intent(this, FeedsDetailActivity.class);
                    break;


                case 3:
                    intent = new Intent(this, GMActivity.class);
                    break;

                case 4:
                    intent = new Intent(this, ConversionPagerActivity.class);
                    break;

                case 5:
                    intent = new Intent(this, GeometryPagerActivity.class);
                    break;

                case 6:
                    intent = new Intent(this, ReferencePagerActivity.class);
                    break;

                default:
                    intent = new Intent(this, ItemDetailActivity.class);
                    break;
            }

            startActivity(intent);
        }
    }
}
