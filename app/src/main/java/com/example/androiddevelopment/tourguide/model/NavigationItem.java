package com.example.androiddevelopment.tourguide.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by androiddevelopment on 29.11.17..
 */

public class NavigationItem implements Parcelable{

    private String title;

    public NavigationItem(String title) {
        this.title = title;
    }

    protected NavigationItem(Parcel in) {
        title = in.readString();
    }

    public static final Creator<NavigationItem> CREATOR = new Creator<NavigationItem>() {
        @Override
        public NavigationItem createFromParcel(Parcel in) {
            return new NavigationItem(in);
        }

        @Override
        public NavigationItem[] newArray(int size) {
            return new NavigationItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
    }
}
