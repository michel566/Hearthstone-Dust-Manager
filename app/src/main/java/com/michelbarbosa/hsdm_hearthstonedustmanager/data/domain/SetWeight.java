package com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class SetWeight implements Parcelable {

    private int index;
    private String set;
    private int weight;

    public SetWeight(int index, String set, int weight) {
        this.index = index;
        this.set = set;
        this.weight = weight;
    }

    protected SetWeight(Parcel in) {
        index = in.readInt();
        set = in.readString();
        weight = in.readInt();
    }

    public static final Creator<SetWeight> CREATOR = new Creator<SetWeight>() {
        @Override
        public SetWeight createFromParcel(Parcel in) {
            return new SetWeight(in);
        }

        @Override
        public SetWeight[] newArray(int size) {
            return new SetWeight[size];
        }
    };

    public int getIndex() {
        return index;
    }

    public String getSet() {
        return set;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeString(set);
        dest.writeInt(weight);
    }
}
