package com.unal.personal.gui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.unal.personal.R;
import com.unal.personal.dataSource.TopicDataSource;
import com.unal.personal.dataSource.Utils;
import com.unal.personal.structures.Category;
import com.unal.personal.structures.Topic;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private CategoryActivityFragment currentFragment;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentFragment = new CategoryActivityFragment();
        final Bundle extras = getIntent().getExtras();
        currentFragment.setArguments(extras);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.add(R.id.container, currentFragment);

        // Commit the transaction
        transaction.commit();

        category = extras.getParcelable(CategoryActivityFragment.CATEGORY_EXTRA);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Topic> topics = TopicDataSource.getTopicList(category, getApplicationContext());
                if (topics.isEmpty()) {
                    Snackbar.make(view, CategoryActivity.this.getString(R.string.no_topic), Snackbar.LENGTH_LONG).show();
                } else {
                    Utils.createTopicActivity(CategoryActivity.this, topics);
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
