package com.uniformorder.uniformorderr.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.Schoollistdetails;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Schoollistadapter  extends RecyclerView.Adapter<Schoollistadapter.ViewHolder> {
    List<Schoollistdetails> profilelist;
    private Context context;
    private static final String TAG = Schoollistadapter.class.getName();

    onItemClickLisnear mOnItemClickLisnear;

    public Schoollistadapter(Context loContext) {
        this.profilelist = new ArrayList<>();
        this.context = loContext;
    }





    @NonNull
    @Override
    public Schoollistadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schoollist, parent, false);
        Schoollistadapter.ViewHolder dataObjectHolder = new Schoollistadapter.ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Schoollistadapter.ViewHolder holder, int position) {

        final Schoollistdetails listdetails = profilelist.get(position);

        holder.schoolname.setText(listdetails.getName());
        holder.principalname.setText(listdetails.getPrincipalName());
        holder.assisname.setText(listdetails.getAssistantName());
        holder.cityname.setText(listdetails.getState());
        holder.paycentrename.setText(listdetails.getPayCenter());
        holder.mobile1.setText(listdetails.getMobile1());
        holder.mobile2.setText(listdetails.getMobile2());



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(view.getContext())
                        .setMessage("Are you sure you want to delete this school ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if(mOnItemClickLisnear!=null){
                                    mOnItemClickLisnear.onClickEvent(position,listdetails,"delete");
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();


            }
        });


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mOnItemClickLisnear!=null){
                    mOnItemClickLisnear.onClickEvent(position,listdetails,"edit");
                }

            }
        });





    }

    @Override
    public int getItemCount() {
        return profilelist.size();
    }

    public void addData(List<Schoollistdetails> data) {
        profilelist.clear();
        profilelist.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView schoolname,principalname,assisname,cityname,paycentrename,mobile1,mobile2;
        ImageView delete,edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolname=itemView.findViewById(R.id.schoolname);
            principalname=itemView.findViewById(R.id.principalname);
            assisname=itemView.findViewById(R.id.assisname);
            cityname=itemView.findViewById(R.id.cityname);
            paycentrename=itemView.findViewById(R.id.paycentrename);
            mobile1=itemView.findViewById(R.id.mobile1);
            mobile2=itemView.findViewById(R.id.mobile2);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);

        }


    }

    public interface onItemClickLisnear {
        void onClickEvent(int position, Schoollistdetails mPlanData,String action);
    }

    public void setItemOnClickEvent(onItemClickLisnear mOnItemClickLisnear) {
        this.mOnItemClickLisnear = mOnItemClickLisnear;
    }
}
