package com.uniformorder.uniformorderr.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SaveorderRequestdetails implements Parcelable {
    Integer standard;
    Integer boys;
    Integer girls;

    public SaveorderRequestdetails() {

    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public Integer getBoys() {
        return boys;
    }

    public void setBoys(Integer boys) {
        this.boys = boys;
    }

    public Integer getGirls() {
        return girls;
    }

    public void setGirls(Integer girls) {
        this.girls = girls;
    }



    public SaveorderRequestdetails(Parcel in) {
        standard=in.readInt();
        boys=in.readInt();
        girls=in.readInt();


    }

    public static final Creator<SaveorderRequestdetails> CREATOR = new Creator<SaveorderRequestdetails>() {
        @Override
        public SaveorderRequestdetails createFromParcel(Parcel in) {
            return new SaveorderRequestdetails(in);
        }

        @Override
        public SaveorderRequestdetails[] newArray(int size) {
            return new SaveorderRequestdetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(standard);
        parcel.writeInt(boys);
        parcel.writeInt(girls);

    }



}
