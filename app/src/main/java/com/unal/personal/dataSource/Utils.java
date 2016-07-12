package com.unal.personal.dataSource;

import android.app.Activity;
import android.content.Intent;

import com.unal.personal.gui.TabTopicActivity;
import com.unal.personal.gui.TopicActivityFragment;
import com.unal.personal.structures.Topic;

import java.util.ArrayList;

/**
 * Created by juanka on 08/07/2016.
 */

public class Utils {
    private Utils(){
        //hidden constructor
    }
    public static void createTopicActivity(Activity activity, ArrayList<Topic> topics){
        Intent intent=new Intent(activity, TabTopicActivity.class);
        intent.putParcelableArrayListExtra(TopicActivityFragment.ARG_TOPIC,topics);
        activity.startActivity(intent);
    }
}
