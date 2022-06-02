package com.uniformorder.uniformorderr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.DistrictName;


import java.util.List;

public class DistrictAdapter extends ArrayAdapter {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<DistrictName> items;
    private final int mResource;

    public DistrictAdapter(Context context, int resource, List<DistrictName> data) {
        super(context, resource, data);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = data;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(mResource, parent, false);
        TextView textViewName = view.findViewById(R.id.text1);
       final  String districtName =items.get(position).getDistrictName();
          textViewName.setText(districtName);
        return view;
    }


}
