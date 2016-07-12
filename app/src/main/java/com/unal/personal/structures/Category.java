package com.unal.personal.structures;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by juanka on 07/07/2016.
 */

public class Category implements Parcelable {
    private float id;
    private String name;
    private int image;
    private String color;

    public Category(float id, String name, int image, String color) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.color = color;
    }

    protected Category(Parcel in) {
        id = in.readFloat();
        name = in.readString();
        image = in.readInt();
        color = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(id);
        dest.writeString(name);
        dest.writeInt(image);
        dest.writeString(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
