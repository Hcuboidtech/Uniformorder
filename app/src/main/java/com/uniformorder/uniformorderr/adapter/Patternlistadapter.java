package com.uniformorder.uniformorderr.adapter;

import android.annotation.SuppressLint;
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
import java.util.List;

public class Patternlistadapter extends RecyclerView.Adapter<Patternlistadapter.ViewHolder> {
    List<Schoollistdetails> profilelist;
    private Context context;
    private static final String TAG = Patternlistadapter.class.getName();

    onItemClickLisnear mOnItemClickLisnear;

    public Patternlistadapter(Context loContext) {
        this.profilelist = new ArrayList<>();
        this.context = loContext;
    }


    @NonNull
    @Override
    public Patternlistadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patternllist, parent, false);
        Patternlistadapter.ViewHolder dataObjectHolder = new Patternlistadapter.ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Patternlistadapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final Schoollistdetails listdetails = profilelist.get(position);

        holder.schoolname.setText(listdetails.getName());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(view.getContext())
                        .setMessage("Are you sure you want to delete this pattern ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (mOnItemClickLisnear != null) {
                                    mOnItemClickLisnear.onClickEvent(position, listdetails, "delete");

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

                if (mOnItemClickLisnear != null) {
                    mOnItemClickLisnear.onClickEvent(position, listdetails, "edit");
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
        TextView schoolname;
        ImageView delete, edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolname = itemView.findViewById(R.id.schoolname);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

        }


    }

    public interface onItemClickLisnear {
        void onClickEvent(int position, Schoollistdetails mPlanData, String action);
    }

    public void setItemOnClickEvent(onItemClickLisnear mOnItemClickLisnear) {
        this.mOnItemClickLisnear = mOnItemClickLisnear;
    }


}