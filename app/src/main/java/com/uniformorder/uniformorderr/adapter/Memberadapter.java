package com.uniformorder.uniformorderr.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.activities.Profile;
import com.uniformorder.uniformorderr.model.Memberdetails;
import com.uniformorder.uniformorderr.model.Schoollistdetails;

import java.util.ArrayList;
import java.util.List;

public class Memberadapter extends RecyclerView.Adapter<Memberadapter.ViewHolder> {
    List<Memberdetails> profilelist;
    private Context context;
    private static final String TAG = Memberadapter.class.getName();

    onItemClickLisnear mOnItemClickLisnear;

    public Memberadapter(Context loContext) {
        this.profilelist = new ArrayList<>();
        this.context = loContext;
    }


    @NonNull
    @Override
    public Memberadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patternllist, parent, false);
        Memberadapter.ViewHolder dataObjectHolder = new Memberadapter.ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Memberadapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Memberdetails listdetails = profilelist.get(position);

        holder.schoolname.setText(listdetails.getName());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Schoollid","loginid");

                if (mOnItemClickLisnear != null) {
                    mOnItemClickLisnear.onClickEvent(position, listdetails, "edit");
                }

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("memberid", String.valueOf(listdetails.getId()));


                new AlertDialog.Builder(view.getContext())
                        .setMessage("Are you sure you want to delete this user ?")
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

    }

    @Override
    public int getItemCount() {
        return profilelist.size();
    }

    public void addData(List<Memberdetails> data) {
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
        void onClickEvent(int position,Memberdetails mPlanData,String action);
    }

    public void setItemOnClickEvent(onItemClickLisnear mOnItemClickLisnear) {
        this.mOnItemClickLisnear = mOnItemClickLisnear;
    }
}
