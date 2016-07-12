package com.unal.personal.structures;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by juanka on 07/07/2016.
 */

public class Topic implements Parcelable{
    private String content;

    public Topic(String content) {
        this.content = content;
    }

    protected Topic(Parcel in) {
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
