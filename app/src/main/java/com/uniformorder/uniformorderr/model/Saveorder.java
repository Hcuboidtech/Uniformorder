package com.uniformorder.uniformorderr.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Saveorder implements Parcelable {

    private List<SaveorderRequestdetails> detail = null;


    public Saveorder() {

    }

    protected Saveorder(Parcel in) {
        detail = in.createTypedArrayList(SaveorderRequestdetails.CREATOR);
    }

    public static final Creator<Saveorder> CREATOR = new Creator<Saveorder>() {
        @Override
        public Saveorder createFromParcel(Parcel in) {
            return new Saveorder(in);
        }

        @Override
        public Saveorder[] newArray(int size) {
            return new Saveorder[size];
        }
    };

    public List<SaveorderRequestdetails> getDetail() {
        return detail;
    }

    public void setDetail(List<SaveorderRequestdetails> detail) {
        this.detail = detail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(detail);


    }

}
