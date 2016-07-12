package com.unal.personal.gui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.unal.personal.R;
import com.unal.personal.dataSource.TopicDataSource;
import com.unal.personal.dataSource.Utils;

public class MainActivity extends AppCompatActivity {

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
                Utils.createTopicActivity(MainActivity.this, TopicDataSource.getRandomTopic(getApplicationContext()));
            }
        });
        initFragments();
    }

    private void initFragments() {
        View two_pane = findViewById(R.id.two_pane_layout);
        MainActivityFragment currentFragment = new MainActivityFragment();
        Bundle extras = new Bundle();
        if (two_pane == null) {
            extras.putInt(MainActivityFragment.ARG_COLUMN, 2);
        } else {
            extras.putInt(MainActivityFragment.ARG_COLUMN, 1);
        }
        currentFragment.setArguments(extras);
        // FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // transaction.replace(R.id.container, currentFragment);
        //transaction.commit();

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
}
