package com.jwilliams.machinistmate.app;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);

            Bundle speedArguments = new Bundle();
            speedArguments.putString(SpeedsDetailFragment.ARG_ITEM_ID, id);
            SpeedsDetailFragment speedFragment = new SpeedsDetailFragment();
            speedFragment.setArguments(speedArguments);

            Bundle feedArguments = new Bundle();
            feedArguments.putString(FeedsDetailFragment.ARG_ITEM_ID, id);
            FeedsDetailFragment feedFragment = new FeedsDetailFragment();
            feedFragment.setArguments(feedArguments);

            ConversionDetailFragment convFragment = new ConversionDetailFragment();

            Bundle geometryArguments = new Bundle();
            geometryArguments.putString(GeometryDetailFragment.ARG_ITEM_ID, id);
            GeometryDetailFragment geometryFragment = new GeometryDetailFragment();
            geometryFragment.setArguments(geometryArguments);

            ReferencePagerFragment referenceFragment = new ReferencePagerFragment();

            //instantiate fragments by id
            switch (input) {
                case 1:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, speedFragment)
                            .commit();
                    break;

                case 2:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, feedFragment)
                            .commit();
                    break;

                case 3:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, convFragment)
                            .commit();
                    break;

                case 4:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, geometryFragment)
                            .commit();
                    break;

                case 5:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, referenceFragment)
                            .commit();
                    break;

                default:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                    break;
            }


        } else {
            //if not two pane then set intent to change activities between menu items
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);

            Intent speedIntent = new Intent(this, SpeedsDetailActivity.class);
            speedIntent.putExtra(SpeedsDetailFragment.ARG_ITEM_ID, id);

            Intent feedIntent = new Intent(this, FeedsDetailActivity.class);
            feedIntent.putExtra(FeedsDetailFragment.ARG_ITEM_ID, id);

            Intent convIntent = new Intent(this, ConversionDetailActivity.class);

            Intent geometryIntent = new Intent(this, GeometryDetailActivity.class);
            geometryIntent.putExtra(GeometryDetailFragment.ARG_ITEM_ID, id);

            Intent referenceIntent = new Intent(this, ReferencePagerActivity.class);

            //instantiate activities by id
            switch (input) {
                case 1:
                    startActivity(speedIntent);
                    break;

                case 2:
                    startActivity(feedIntent);
                    break;

                case 3:
                    startActivity(convIntent);
                    break;

                case 4:
                    startActivity(geometryIntent);
                    break;

                case 5:
                    startActivity(referenceIntent);
                    break;

                default:
                    startActivity(detailIntent);
                    break;
            }
        }
    }
}
