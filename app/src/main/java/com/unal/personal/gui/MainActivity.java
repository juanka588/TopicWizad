package com.unal.personal.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.unal.personal.R;
import com.unal.personal.dataSource.TopicDataSource;
import com.unal.personal.dataSource.Utils;
import com.unal.personal.interfaces.OnCategoryListListener;
import com.unal.personal.interfaces.OnTopicListListener;
import com.unal.personal.structures.Category;
import com.unal.personal.structures.Topic;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnCategoryListListener, OnTopicListListener {

    private LinearLayout two_pane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Topic> randomTopic = TopicDataSource.getRandomTopic(getApplicationContext());
                onTopicSelected(randomTopic.get(0));
            }
        });
        initFragments();
    }

    private void initFragments() {
        two_pane = (LinearLayout) findViewById(R.id.two_pane_layout);
        MainActivityFragment currentFragment = new MainActivityFragment();
        Bundle extras = new Bundle();
        if (two_pane != null) {
            extras.putInt(MainActivityFragment.ARG_COLUMN, 1);
        } else {
            extras.putInt(MainActivityFragment.ARG_COLUMN, 2);
        }
        currentFragment.setArguments(extras);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, currentFragment);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCategorySelected(Category category) {
        if (two_pane != null) {
            CategoryActivityFragment currentFragment = new CategoryActivityFragment();
            Bundle extras = new Bundle();
            extras.putParcelable(CategoryActivityFragment.CATEGORY_EXTRA, category);
            currentFragment.setArguments(extras);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_cat, currentFragment);
            transaction.commit();

        } else {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(CategoryActivityFragment.CATEGORY_EXTRA, category);
            startActivity(intent);
        }
    }

    @Override
    public void onTopicSelected(Topic topic) {
        ArrayList<Topic> topics = new ArrayList<>();
        topics.add(topic);
        if (two_pane != null) {
            TopicActivityFragment currentFragment = new TopicActivityFragment();
            Bundle extras = new Bundle();
            extras.putParcelable(TopicActivityFragment.ARG_TOPIC, topic);
            currentFragment.setArguments(extras);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_cat, currentFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else {
            Utils.createTopicActivity(this, topics);
        }
    }
}
