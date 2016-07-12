package com.unal.personal.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unal.personal.R;
import com.unal.personal.dataSource.Utils;
import com.unal.personal.structures.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanka on 07/07/2016.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicViewHolder> {

    private List<Topic> topics;
    private Activity mActivity;


    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewtopic, parent, false);
        return new TopicViewHolder(v);
    }

    @Override
    public void onViewRecycled(TopicViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int i) {
        Topic p = topics.get(i);
        holder.title.setText((i + 1) + ". " + p.getContent());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public TopicsAdapter(List<Topic> places, Activity activity) {
        this.topics = places;
        this.mActivity = activity;
    }

    public void showImage(int position) {
        ArrayList<Topic> topic=new ArrayList<>();
        topic.add(topics.get(position));
        Utils.createTopicActivity(mActivity,topic);
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView title;

        TopicViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            title = (TextView) itemView.findViewById(R.id.title);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showImage(getAdapterPosition());
        }

    }
}
