package com.uniformorder.uniformorderr.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.activities.Constants;
import com.uniformorder.uniformorderr.activities.Delivernow;
import com.uniformorder.uniformorderr.activities.OnItemClicked;
import com.uniformorder.uniformorderr.activities.PassedStandard;
import com.uniformorder.uniformorderr.activities.Quickorderforrm;
import com.uniformorder.uniformorderr.activities.UserPreference;
import com.uniformorder.uniformorderr.model.SaveorderRequestdetails;
import com.uniformorder.uniformorderr.testModel.DataItem;
import com.uniformorder.uniformorderr.testModel.StandardsItem;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orderadapter extends RecyclerView.Adapter<Orderadapter.ViewHolder> implements PassedStandard {

    public OnItemClicked onItemClicked;

    List<DataItem> profilelist;
    private Context context;
    private static final String TAG = Orderadapter.class.getName();
    String strbirthdate,convertedTime,completeStatus="No";

    public Orderadapter(Context loContext, OnItemClicked listner,String completeFrag) {
        this.profilelist = new ArrayList<DataItem>();
        this.context = loContext;
        this.onItemClicked = listner;
        this.completeStatus =completeFrag;
    }
    @NonNull
    @Override
    public Orderadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist, parent, false);
        Orderadapter.ViewHolder dataObjectHolder = new Orderadapter.ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Orderadapter.ViewHolder holder,int position) {
        final DataItem listdetails = profilelist.get(position);
        if (completeStatus.equals("pending")){
            UserPreference.getInstance(context).setpayment_pending("pending");
            Log.d("Orderadapter SET TO _>","pending");
        }
        String ordder= UserPreference.getInstance(context).getpayment_pending();

        Log.d("Orderadapter",ordder);
        if (ordder.equals("payment_pending")){
            holder.paynow.setVisibility(View.VISIBLE);
            holder.paynow.setText("Pay Now");
            holder.delete.setVisibility(View.GONE);
            holder.edit.setVisibility(View.GONE);

        }else if (ordder.equals("pending")){
            holder.paynow.setVisibility(View.VISIBLE);
            holder.paynow.setText("Deliver Now");
            holder.edit.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.VISIBLE);
        }
        else {
            holder.delete.setVisibility(View.GONE);
            holder.edit.setVisibility(View.GONE);
            holder.paynow.setVisibility(View.INVISIBLE);
            holder.linearLayout.setVisibility(View.GONE);
        }

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          SaveorderRequestdetails saveorderRequestdetails = new SaveorderRequestdetails();
          List<SaveorderRequestdetails>saveorderReqlist = new ArrayList<>();

                Intent intent = new Intent(context, Quickorderforrm.class);
                intent.putExtra("isEdit","order");
                intent.putExtra("PatternName",profilelist.get(position).getPattern().getName());
                intent.putExtra("principalName",profilelist.get(position).getSchool().getPrincipalName());
                intent.putExtra("SchoolName",profilelist.get(position).getSchool().getName());
                intent.putExtra("id",profilelist.get(position).getId());
                intent.putExtra("idSchool",profilelist.get(position).getSchool().getId());
                intent.putExtra("loginId",profilelist.get(position).getLoginId());
                intent.putExtra("idpattern",profilelist.get(position).getPatternId());
                intent.putExtra("orderId",profilelist.get(position).getId());
                intent.putExtra("schoolName",profilelist.get(position).getSchool().getId());
                intent.putExtra("rate1",profilelist.get(position).getRate1());
                intent.putExtra("rate2",profilelist.get(position).getRate2());
                intent.putExtra("rate3",profilelist.get(position).getRate3());
                intent.putExtra("totalam",profilelist.get(position).getTotalAmount());
                intent.putExtra("deposite",profilelist.get(position).getDeposite());
                intent.putExtra("pendingamt",profilelist.get(position).getPendingAmount());
                intent.putExtra("formNum",profilelist.get(position).getFormNumber());
                intent.putExtra("addondate",profilelist.get(position).getAddOnDate());
                intent.putExtra("orderby",profilelist.get(position).getOrderBy());
             //   Log.d("Form Num 77",profilelist.get(position).getFormNumber());
                System.out.println("SCHOOL ID PASSED "+profilelist.get(position).getSchool().getId());
///////////////////////////       FOR LOGS     ////////////////////////////////////
//                Log.d("SchoolName",profilelist.get(position).getSchool().getName());
//                Log.d("TotalAmount",profilelist.get(position).getTotalAmount());
             //   Log.d("IDSchool#",String.valueOf(profilelist.get(position).getSchool().getId()));
//                Log.d("LoginId",profilelist.get(position).getLoginId());
//                Log.d("IDpattern",profilelist.get(position).getPatternId());
//                Log.d("PatternName",profilelist.get(position).getPattern().getName());
                List<StandardsItem> list = profilelist.get(position).getStandards();
               //Log.d("OrderId# ->",String.valueOf(profilelist.get(position).getId()));
//                Log.d("SchoolName ->",profilelist.get(position).getSchool().getName());
//                Log.d("StandaredID ->",profilelist.get(position).getStandards().get(position).getStandardId());
//                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                // passing the standards //

                for (int i =0; i<list.size(); i++){
                     saveorderRequestdetails = new SaveorderRequestdetails();
                     saveorderRequestdetails.setStandardId(Integer.parseInt(list.get(i).getStandardId()));
                     saveorderRequestdetails.setStandard(Integer.parseInt(list.get(i).getStandard()));
                     saveorderRequestdetails.setBoys(Integer.parseInt(list.get(i).getBoys()));
                     saveorderRequestdetails.setGirls(Integer.parseInt(list.get(i).getGirls()));
                     saveorderReqlist.add(i,saveorderRequestdetails);

//                    Log.d("XXStandardID",list.get(i).getStandardId());
//                    Log.d("XXStandard",list.get(i).getStandard());
//                    Log.d("XX Boys",list.get(i).getBoys());
//                    Log.d("XX Girls",list.get(i).getGirls());

                 }
                for (int i=0; i< profilelist.size();i++){

                }
                 for (int i =0; i<saveorderReqlist.size();i++){
//                     Log.d("XD ->",saveorderReqlist.get(i).getStandard().toString());
                 }

                if (Constants.editcardList != null) {
                    for (int i =0; i<saveorderReqlist.size();i++) {
                       Constants.editcardList.add(saveorderReqlist.get(i));
//                        Log.d("ThisXXO ->", "XO");
                    }
                }
                for (int i =0; i<Constants.editcardList.size(); i++){

//                   Log.d("PASSED_STANDARD->",Constants.editcardList.get(i).getStandard().toString());
//                    Log.d("PASSED_STANDARD ID->",Constants.editcardList.get(i).getStandardId().toString());
                }

                // passing the EditData list  //
                 intent.putParcelableArrayListExtra("EditData",Constants.editcardList);
                 context.startActivity(intent);
                 ((Activity)context).finish();


            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBox = new AlertDialog.Builder(context);
                 alertDialogBox.setTitle("Do you want to Delete Order");
                 alertDialogBox.setMessage("Once Order is Deleted It can't be revised");
                 alertDialogBox.setIcon(android.R.drawable.ic_delete);
                 alertDialogBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                          profilelist.remove(position);
                          notifyItemChanged(holder.getAdapterPosition());
                          notifyItemRangeChanged(holder.getAdapterPosition(),profilelist.size());
//                          Log.d("ID_ADP ->",String.valueOf(listdetails.getId()));
                          // Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                          onItemClicked.onClick(String.valueOf(listdetails.getId()));
                         Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                     }
                 });
                 alertDialogBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.cancel();
                     }
                 });
                 alertDialogBox.show();
                //////////////////////////////////
//                 profilelist.remove(position);
//                 notifyItemChanged(holder.getAdapterPosition());
//                 notifyItemRangeChanged(holder.getAdapterPosition(),profilelist.size());
//               Log.d("ID_ADP ->",String.valueOf(listdetails.getId()));
//               // Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
//               onItemClicked.onClick(String.valueOf(listdetails.getId()));
           ///////////////////////////////////////
            }
        });
        holder.paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ordder.equals("pending")){
                    Log.d("TAG",String.valueOf(listdetails.getId()));
                    Intent i=new Intent(context, Delivernow.class);
                    Bundle b = new Bundle();
                    b.putSerializable("user", (Serializable) listdetails);
                    i.putExtras(b);
                    i.putExtra("orderid",String.valueOf(listdetails.getId()));
                    Log.d("orderid11", String.valueOf(listdetails.getId()));
                    i.putExtra("ordertype","pending");
                    context.startActivity(i);
                }
                else {
                    Intent i=new Intent(context, Delivernow.class);
                    Bundle b = new Bundle();
                    b.putSerializable("user", listdetails);
                    i.putExtras(b);
                    i.putExtra("orderid",String.valueOf(listdetails.getId()));
                    i.putExtra("ordertype","payment_pending");
                    context.startActivity(i);
                }
            }
        });
//        Log.d("TAG",listdetails.getOrderDate());
        holder.orderid.setText(String.valueOf(listdetails.getId())); // this is need to check //
        holder.date.setText("Created at: "+ listdetails.getOrderDate());
        holder.schoolname.setText("School Name: "+listdetails.getSchool().getName());
        holder.principalname.setText("Principal Name: "+listdetails.getSchool().getPrincipalName());
        holder.orderBy.setText("Order By: "+ listdetails.getOrderBy());
        holder.pattern_name.setText("Pattern Name: "+listdetails.getPattern().getName());
        Double d = Double.valueOf(listdetails.getTotalStudents());
        int i = d.intValue();
        String amount = String.valueOf(i);
        Log.d("INTEGER",String.valueOf(i));
        holder.total.setText("Total: "+amount);
        holder.pendingamout.setText("Pending: "+listdetails.getPendingAmount());
    }

    @Override
    public int getItemCount() {
        return profilelist.size();
    }
    public void addData(List<DataItem> data) {
        profilelist.clear();
        profilelist.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void passesStd(SaveorderRequestdetails saveorderRequestdetails,int position) {
        Constants.editcardList.add(position,saveorderRequestdetails);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView orderid,date,schoolname,principalname,total,pendingamout,orderBy,pattern_name;
        TextView paynow,delete,edit;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pendingamout=itemView.findViewById(R.id.pendingamout);
            orderid=itemView.findViewById(R.id.orderid);
            date=itemView.findViewById(R.id.date);
            schoolname=itemView.findViewById(R.id.schoolname);
            principalname=itemView.findViewById(R.id.principalname);
            total=itemView.findViewById(R.id.total);
            paynow=itemView.findViewById(R.id.paynow);
            edit = itemView.findViewById(R.id.edit_btn);
            delete = itemView.findViewById(R.id.delete_btn);
            linearLayout = itemView.findViewById(R.id.action_box);
            orderBy = itemView.findViewById(R.id.orderBy);
            pattern_name = itemView.findViewById(R.id.pattern_Name);
        }

        @Override
        public void onClick(View v) {
            if (profilelist.isEmpty()){
              //  Toast.makeText(context, "List is EMpty", Toast.LENGTH_SHORT).show();
            }
        }



    }

    public String tTime(String createdAt){
        strbirthdate=createdAt;
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                Date dt = sdf.parse(strbirthdate);
                final SimpleDateFormat   sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
                String newDate = sdf1.format(dt);
                //final Date dateObj = sdf.parse(String.valueOf(dt));
               // convertedTime=sdf1.format(dateObj);
                convertedTime=newDate;
            }
            //    Log.d(TAG, "tTime: "  + sdf1.format(dateObj));

        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
    }
}
