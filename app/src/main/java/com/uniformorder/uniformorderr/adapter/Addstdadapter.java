package com.uniformorder.uniformorderr.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.activities.Constants;
import com.uniformorder.uniformorderr.activities.UserPreference;
import com.uniformorder.uniformorderr.model.Memberdetails;
import com.uniformorder.uniformorderr.model.SaveorderRequestdetails;
import com.uniformorder.uniformorderr.model.Schoollistdetails;

import java.util.ArrayList;
import java.util.List;

public class Addstdadapter extends RecyclerView.Adapter<Addstdadapter.ViewHolder> {
    OnItemSelecteListener mOnItemClickLisnear;
    List<SaveorderRequestdetails> profilelist;
    private Context context;
    private static final String TAG = Addstdadapter.class.getName();
    public Addstdadapter(List<SaveorderRequestdetails> cartlist1) {
        this.profilelist=cartlist1;
    }

    @NonNull
    @Override
    public Addstdadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addstdlist, parent, false);
        Addstdadapter.ViewHolder dataObjectHolder = new Addstdadapter.ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Addstdadapter.ViewHolder holder, int position) {
        final SaveorderRequestdetails listdetails = Constants.cartlist.get(position);
        int total=0,alltotal=0;

        holder.txtstd.setText(String.valueOf((listdetails.getStandard())));
        holder.txtboys.setText("Boys: "+  String.valueOf(listdetails.getBoys()));
        holder.txtgirls.setText("Girls: " +String.valueOf(listdetails.getGirls()));
        total=listdetails.getBoys() + listdetails.getGirls();
        holder.txttotal.setText("Total: "+String.valueOf(total));
        for(int i=0;i<Constants.cartlist.size();i++) {
            int total1=0,std1to4=0,std5to8=0,allstd1to4=0,allstd5to8=0;
            total1 = Constants.cartlist.get(i).getBoys() +  Constants.cartlist.get(i).getGirls();
            alltotal=total1+alltotal;

            if(Constants.cartlist.get(i).getStandard()<=4){
                std1to4 = Constants.cartlist.get(i).getBoys() +  Constants.cartlist.get(i).getGirls();
                allstd1to4=std1to4+allstd1to4;
                if (mOnItemClickLisnear != null) {
                    mOnItemClickLisnear.onItemSelected2(alltotal,allstd1to4,allstd5to8);
                }
            }else {
                std5to8 = Constants.cartlist.get(i).getBoys() +  Constants.cartlist.get(i).getGirls();
                allstd5to8=allstd5to8+std5to8;

                if (mOnItemClickLisnear != null) {
                    mOnItemClickLisnear.onItemSelected2(alltotal,allstd1to4,allstd5to8);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return profilelist.size();
    }

    public void add(List<SaveorderRequestdetails> data) {
        profilelist.clear();
        profilelist.addAll(data);
        notifyDataSetChanged();
    }

    public void setOnItemClickLister(OnItemSelecteListener mListener) {
        this.mOnItemClickLisnear = mListener;
    }

    public interface OnItemSelecteListener {
        void onItemSelected2(int alltotal,int stdd1tto4,int std5to8);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtstd,txtboys,txtgirls,txttotal,deleteStandard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            deleteStandard = itemView.findViewById(R.id.delete_img);
            txtstd = itemView.findViewById(R.id.txtstd);
            txtboys = itemView.findViewById(R.id.txtboys);
            txtgirls = itemView.findViewById(R.id.txtgirls);
            txttotal = itemView.findViewById(R.id.txttotal);
        }
    }
}
