package com.uniformorder.uniformorderr.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.activities.Constants;
import com.uniformorder.uniformorderr.activities.UserPreference;
import com.uniformorder.uniformorderr.model.Editorder;
import com.uniformorder.uniformorderr.model.SaveorderRequestdetails;
import com.uniformorder.uniformorderr.model.Standard;
import com.uniformorder.uniformorderr.testModel.StandardsItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deliverorderadapter extends RecyclerView.Adapter<Deliverorderadapter.ViewHolder> {
    List<StandardsItem> profilelist;
    private Context context;
    private static final String TAG = Deliverorderadapter.class.getName();
    int stdid;
    List<StandardsItem> editlist = new ArrayList<>();


    onItemClickLisnear mOnItemClickLisnear;

    public Deliverorderadapter(Context loContext) {
        this.profilelist = new ArrayList<>();
        this.context = loContext;
    }


    @NonNull
    @Override
    public Deliverorderadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delliverlist, parent, false);
        Deliverorderadapter.ViewHolder dataObjectHolder = new Deliverorderadapter.ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Deliverorderadapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final StandardsItem listdetails = profilelist.get(position);
    //    final Standard listdetails1 = profilelist1.get(position);

        holder.simpleTextView.setText(listdetails.getStandard());
        holder.simpleTextView1.setText(listdetails.getBoys());
        holder.simpleTextView2.setText(listdetails.getTotal());
        holder.simpleTextView3.setText(listdetails.getGirls());


        String ordder= UserPreference.getInstance(context).getpayment_pending();

        Log.d("TAG",ordder);


        Log.d("log", String.valueOf(profilelist.get(position).getStandard()));
        Log.d("log", String.valueOf(profilelist.get(position).getBoys()));
        Log.d("log", String.valueOf(profilelist.get(position).getGirls()));
        Log.d("logp", String.valueOf(profilelist.get(position).getPendingBoys()));
        Log.d("logp", String.valueOf(profilelist.get(position).getPendingGirls()));
        Log.d("log", String.valueOf(profilelist.size()));
        Log.d("logstdid", String.valueOf(profilelist.get(position).getStandardId()));


        holder.simpleTextView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialuge(listdetails, position);
            }
        });




    }

    private void opendialuge(StandardsItem objectSTD, int position) {
        //Standard listdetails = profilelist.get(position);
        //Editorder saveorderRequestdetails = new Editorder();

        final Dialog dialog;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.editdialogue);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        EditText edtboys = dialog.findViewById(R.id.edtboys1);
        EditText edtgirls = dialog.findViewById(R.id.edtgirls1);

        TextView btnregisternow = dialog.findViewById(R.id.btnregisternow);
        TextView txtcancle = dialog.findViewById(R.id.txtcancle);

        btnregisternow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strtxt1 = edtboys.getText().toString();
                String strtxt2 = edtgirls.getText().toString();

                Log.d("total", String.valueOf(strtxt1));
                Log.d("total", String.valueOf(strtxt2));

                int total=0;

                try {
                    int i1 = Integer.parseInt(strtxt1);
                    int i2 = Integer.parseInt(strtxt2);
                    total = i1 + i2;
                    Log.d("total", String.valueOf(total));
                } catch (NumberFormatException ex) { // handle your exception
                }

                int finalTotal = total;

                objectSTD.setBoys(strtxt1);
                objectSTD.setGirls(strtxt2);
                objectSTD.setTotal(String.valueOf(finalTotal));

                String stdid1 = "-1";
                int pos = 0;

                if (editlist != null && editlist.size() != 0) {
                    for (int i = 0; i < editlist.size(); i++) {
                        String selectedID = editlist.get(i).getStandard();
                        Log.d(TAG, "ID First =>" + profilelist.get(position).getStandard());
                        Log.d(TAG, "ID Second =>" + stdid1);
                        if (profilelist.get(position).getStandard().equalsIgnoreCase(selectedID)) {
                            Log.d(TAG, "if part");
                            stdid1 = editlist.get(i).getStandard();
                            pos = i;
                            break;
                        }

                    }

                    if (stdid1.equalsIgnoreCase("-1")) {
                        Log.d(TAG, "-1");
                        editlist.add(objectSTD);
                    } else {
                        Log.d(TAG, "if part");
                        editlist.set(pos, objectSTD);
                    }

                } else {
                    editlist.add(objectSTD);
                    Log.d(TAG, "else part");
                }


                Log.d(TAG, String.valueOf(editlist.size()));
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });


        txtcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }

    public List<StandardsItem> getSelectedArray() {
        return editlist;
    }

    @Override
    public int getItemCount() {
        return profilelist.size();
    }

    public void addData(List<StandardsItem> data) {
        profilelist.clear();
        profilelist.addAll(data);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView simpleTextView, simpleTextView1, simpleTextView3, simpleTextView2, total, pendingamout;

        ImageView simpleTextView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pendingamout = itemView.findViewById(R.id.pendingamout);
            simpleTextView = itemView.findViewById(R.id.simpleTextView);
            simpleTextView1 = itemView.findViewById(R.id.simpleTextView1);
            simpleTextView3 = itemView.findViewById(R.id.simpleTextView3);
            simpleTextView2 = itemView.findViewById(R.id.simpleTextView2);
            simpleTextView4 = itemView.findViewById(R.id.simpleTextView4);


        }
    }


    public interface onItemClickLisnear {
        void onClickEvent(int position, List<Editorder> editlist, String action);
    }

    public void setItemOnClickEvent(onItemClickLisnear mOnItemClickLisnear) {
        this.mOnItemClickLisnear = mOnItemClickLisnear;
    }
}
