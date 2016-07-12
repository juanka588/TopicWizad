package com.unal.personal.gui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unal.personal.R;
import com.unal.personal.structures.Topic;

/**
 * A placeholder fragment containing a simple view.
 */
public class TopicActivityFragment extends Fragment {

    public static final String ARG_TOPIC = "topic";
    private Topic topic;

    public TopicActivityFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.topic = getArguments().getParcelable(ARG_TOPIC);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_topic, container, false);
        TextView textView = (TextView) root.findViewById(R.id.content);
        textView.setText(topic.getContent());
        return root;
    }

    public static TopicActivityFragment newInstance(Topic topic) {
        TopicActivityFragment fragment = new TopicActivityFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TOPIC, topic);
        fragment.setArguments(args);
        return fragment;
    }
}
