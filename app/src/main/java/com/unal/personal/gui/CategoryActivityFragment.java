package com.unal.personal.gui;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unal.personal.R;
import com.unal.personal.adapters.TopicsAdapter;
import com.unal.personal.dataSource.TopicDataSource;
import com.unal.personal.structures.Category;
import com.unal.personal.structures.Topic;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class CategoryActivityFragment extends Fragment {

    public static final String CATEGORY_EXTRA = "category";
    private static final String TAG = CategoryActivityFragment.class.getSimpleName();
    private Category category;

    public CategoryActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Bundle b = getArguments();
            category = b.getParcelable(CATEGORY_EXTRA);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        if (category != null) {
            root.setBackgroundColor(Color.parseColor(category.getColor()));
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.getSupportActionBar().setTitle(category.getName());
            List<Topic> topics = TopicDataSource.getTopicList(category, root.getContext());
            RecyclerView topicList = (RecyclerView) root.findViewById(R.id.recycle_view);
            topicList.setLayoutManager(new LinearLayoutManager(root.getContext()));
            topicList.setAdapter(new TopicsAdapter(topics, getActivity()));
        }
        return root;
    }
}
