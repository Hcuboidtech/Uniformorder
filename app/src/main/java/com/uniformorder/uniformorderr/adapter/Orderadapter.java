package com.uniformorder.uniformorderr.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.activities.Delivernow;
import com.uniformorder.uniformorderr.activities.OnItemClicked;
import com.uniformorder.uniformorderr.activities.Quickorderforrm;
import com.uniformorder.uniformorderr.activities.UserPreference;
import com.uniformorder.uniformorderr.model.Memberdetails;
import com.uniformorder.uniformorderr.model.Orderlistdetails;
import com.uniformorder.uniformorderr.model.Standard;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Orderadapter extends RecyclerView.Adapter<Orderadapter.ViewHolder>  {

    public OnItemClicked onItemClicked;

    List<Orderlistdetails> profilelist;
    private Context context;
    private static final String TAG = Orderadapter.class.getName();
    String strbirthdate,convertedTime;

    public Orderadapter(Context loContext, OnItemClicked listner) {
        this.profilelist = new ArrayList<>();
        this.context = loContext;
        this.onItemClicked = listner;
    }
    @NonNull
    @Override
    public Orderadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist, parent, false);
        Orderadapter.ViewHolder dataObjectHolder = new Orderadapter.ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Orderadapter.ViewHolder holder, int position) {
        final Orderlistdetails listdetails = profilelist.get(position);
        String ordder= UserPreference.getInstance(context).getpayment_pending();
        Log.d("Orderadapter",ordder);
        if (ordder.equals("payment_pending")){

            holder.paynow.setVisibility(View.VISIBLE);
            holder.paynow.setText("Pay Now");

        }else if (ordder.equals("pending")){
            holder.paynow.setVisibility(View.VISIBLE);
            holder.paynow.setText("Deliver Now");
        }
        else {
            holder.paynow.setVisibility(View.INVISIBLE);
        }

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Quickorderforrm.class);
                intent.putExtra("isEdit","order");
                intent.putExtra("details",profilelist.get(position));
                intent.putExtra("principalName",profilelist.get(position).getPrincipalName());
                intent.putExtra("SchoolName",profilelist.get(position).getSchoolsName());
                intent.putExtra("C",profilelist.get(position).getId());
                Log.d("SchoolId",profilelist.get(position).getSchoolId());
                Log.d("pattern",profilelist.get(position).getPatternId());
              //  Log.d("pattern",profilelist.get(position).getStandards());
                 List<Standard> list = profilelist.get(position).getStandards();
                //   Log.d("List",list.get(position).getBoys());
                context.startActivity(intent);
                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 profilelist.remove(position);
                 notifyItemRemoved(position);
               Log.d("ID_ADP ->",String.valueOf(listdetails.getId()));
               // Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
               onItemClicked.onClick(listdetails.getId());
            }
        });
        holder.paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ordder.equals("pending")){
                    Log.d("TAG",listdetails.getId());
                    Intent i=new Intent(context, Delivernow.class);
                    Bundle b = new Bundle();
                    b.putSerializable("user", listdetails);
                    i.putExtras(b);

                    i.putExtra("orderid",listdetails.getId());
                    i.putExtra("ordertype","pending");
                    context.startActivity(i);
                }
                else {
                    Intent i=new Intent(context, Delivernow.class);
                    Bundle b = new Bundle();
                    b.putSerializable("user", listdetails);
                    i.putExtras(b);
                    i.putExtra("orderid",listdetails.getId());
                    i.putExtra("ordertype","payment_pending");
                    context.startActivity(i);
                }
            }
        });

       // tTime(listdetails.getCreatedAt());
        Log.d("TAG",listdetails.getOrderDate());
        holder.orderid.setText(listdetails.getId());
        holder.date.setText("Created at: "+ listdetails.getOrderDate());
        holder.schoolname.setText("School Name: "+listdetails.getSchoolsName());
        holder.principalname.setText("Principal Name: "+listdetails.getPrincipalName());
        holder.total.setText("Total: "+listdetails.getTotalAmount());
        holder.pendingamout.setText("Pending: "+listdetails.getPendingAmount());
    }

    @Override
    public int getItemCount() {
        return profilelist.size();
    }
    public void addData(List<Orderlistdetails> data) {
        profilelist.clear();
        profilelist.addAll(data);
        notifyDataSetChanged();
    }

    void openEditScreen(){

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView orderid,date,schoolname,principalname,total,pendingamout;
        TextView paynow,delete,edit;
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
        }

        @Override
        public void onClick(View v) {
             removeAt(getPosition());
        }

       void removeAt(int position){
          //  profilelist.remove(position);

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
