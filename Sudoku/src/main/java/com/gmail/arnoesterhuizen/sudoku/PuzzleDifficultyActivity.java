package com.gmail.arnoesterhuizen.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;

import com.gmail.arnoesterhuizen.sudoku.dummy.DummyContent;

/**
 * Created by arno.esterhuizen on 2013/06/22.
 */
public class PuzzleDifficultyActivity extends FragmentActivity {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_list);

        if (findViewById(R.id.puzzle_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((PuzzleListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.puzzle_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.

        // TODO: replace with a real list adapter.
        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.ITEMS));

    }

    /**
     * Callback method from {@link PuzzleListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(PuzzleDetailFragment.ARG_ITEM_ID, id);
            PuzzleDetailFragment fragment = new PuzzleDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.puzzle_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PuzzleDetailActivity.class);
            detailIntent.putExtra(PuzzleDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
