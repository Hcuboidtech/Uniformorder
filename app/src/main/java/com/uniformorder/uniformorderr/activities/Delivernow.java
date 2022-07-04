package com.uniformorder.uniformorderr.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.adapter.Deliverorderadapter;
import com.uniformorder.uniformorderr.model.Editorder;
import com.uniformorder.uniformorderr.model.Orderlistdetails;
import com.uniformorder.uniformorderr.model.Schoollistmodel;
import com.uniformorder.uniformorderr.model.Standard;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;
import com.uniformorder.uniformorderr.testModel.DataItem;
import com.uniformorder.uniformorderr.testModel.ResponseOrderList;
import com.uniformorder.uniformorderr.testModel.StandardsItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delivernow extends BaseAppCompatActivity implements Deliverorderadapter.onItemClickLisnear {
    Deliverorderadapter profilelistadapter;
    List<StandardsItem> schoollistdetails = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    EditText edtrecevied, edtpayment;
    TextView txtorder, txtschoolname, totalpayable;
    ImageView img_back;

    RecyclerView recylceorderlist;
    String loginid = "", order_id = "", payment_amount = "0", received_by = "", delivery_type = " ", ordertype = "";
    String strsearch = " ", orderdetails = " ";
    List<Integer> std;
    List<Integer> boys;
    List<Integer> girls;
    List<Integer> stdid;
    Button btnaddpattern;

    DataItem user;

    RadioGroup radioGroup;
    RadioButton radioFulldelivery, radiopartial;

    @Override
    public String getActionTitle() {
        return null;
    }

    @Override
    public boolean isHomeButtonEnable() {
        return false;
    }

    @Override
    public int setHomeButtonIcon() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_delivernow);

        recylceorderlist = findViewById(R.id.recylceorderlist);
        btnaddpattern = findViewById(R.id.btnaddpattern);
        edtrecevied = findViewById(R.id.edtrecevied);
        edtpayment = findViewById(R.id.edtpayment);
        txtschoolname = findViewById(R.id.txtschoolname);
        txtorder = findViewById(R.id.txtorder);
        totalpayable = findViewById(R.id.totalpayable);
        img_back = findViewById(R.id.img_back);
        radioGroup = findViewById(R.id.radioGroup);
        radioFulldelivery = findViewById(R.id.radioFulldelivery);
        radiopartial = findViewById(R.id.radiopartial);
        Bundle extras = getIntent().getExtras();
        loginid = UserPreference.getInstance(Delivernow.this).getLoginId();

        if (extras != null) {
            order_id = String.valueOf(extras.getString("orderid"));
            ordertype = extras.getString("ordertype");
            user = (DataItem) extras.getSerializable("user");
            txtschoolname.setText("School Name: " + user.getSchool().getName());
            totalpayable.setText("Total Payable amount " + user.getPendingAmount() + " INR");
            txtorder.setText("Order No. " + order_id);
            Log.d("orderType",ordertype);
            Log.d("orderid Recived",order_id);
            ////
        }

        profilelistadapter = new Deliverorderadapter(Delivernow.this);
        linearLayoutManager = new LinearLayoutManager(Delivernow.this);
        recylceorderlist.setLayoutManager(linearLayoutManager);
        recylceorderlist.setAdapter(profilelistadapter);

        if (profilelistadapter != null) {
            profilelistadapter.setItemOnClickEvent(this);
        }

        orderlist(strsearch);

        btnaddpattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DILIVERY ->", "API CLICKED 1");
                String input_amount = edtpayment.getText().toString();
                Log.d("input amount ->", input_amount);
                if (!input_amount.matches("")) {
                    Log.d("input amount ->", "NotEmpty");
                    Log.d("input amount ->", input_amount);
                    if (profilelistadapter.getItemCount() != 0) {
                        Log.d("DILIVERY ->", "API CLICKED 2");
                        std = new ArrayList<>();
                        boys = new ArrayList<>();
                        girls = new ArrayList<>();
                        stdid = new ArrayList<>();

                        Log.e("Tag", "ARRAY SIZE" + profilelistadapter.getSelectedArray().size());

                        for (int i = 0; i < profilelistadapter.getSelectedArray().size(); i++) {
                            std.add(Integer.valueOf(profilelistadapter.getSelectedArray().get(i).getStandard()));
                            stdid.add(Integer.valueOf(profilelistadapter.getSelectedArray().get(i).getStandardId()));
                            boys.add(Integer.valueOf(profilelistadapter.getSelectedArray().get(i).getBoys()));
                            girls.add(Integer.valueOf(profilelistadapter.getSelectedArray().get(i).getGirls()));
                        }
                        showHideProgressDialog(true);
                        Log.d("DELIVERY ->", "API CLICKED");
                        int edit = Integer.parseInt(edtpayment.getText().toString());
                        Float payamt = Float.parseFloat(user.getPendingAmount());
                        int am = Math.round(payamt);
                        Log.d("edit ->", String.valueOf(edit));
                        Log.d("payableFloat ->", String.valueOf(payamt));
                        Log.d("payableInt ->", String.valueOf(am));
                        if (edit > am) {
                            Log.d("DILIVERY ->", "value is larger");
                            showHideProgressDialog(false);
                            Toast.makeText(Delivernow.this, "Invalid Payment", Toast.LENGTH_SHORT).show();
                            showHideProgressDialog(false);
                        } else {
                            editorder();
                        }
                    } else {
                        Log.d("Delevery Adapter", "IS EMPTY");
                    }
                }else{
                    Toast.makeText(Delivernow.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radiopartial.isChecked()) {
                    // do something
                    delivery_type = "partial_delivery";
                    Log.d("loggg", delivery_type);
                } else if (radioFulldelivery.isChecked()) {
                    // do something
                    delivery_type = "full_delivery";
                    Log.d("loggg", delivery_type);
                }
            }
        });
    }

    private void editorder() {

        Log.d("loggg", "APICALLED");
        showHideProgressDialog(true);
        received_by = edtrecevied.getText().toString();
        payment_amount = edtpayment.getText().toString();
        Log.d("loggg", delivery_type);
        if (delivery_type.equals(" ")) {
            Toast.makeText(Delivernow.this, "Select Delivery Type Partialdelivery or fulldelivery", Toast.LENGTH_SHORT).show();
        } else {
            LinkedHashMap<String, RequestBody> addPostRequest = new LinkedHashMap<String, RequestBody>();
            addPostRequest.put("login_id", RequestBody.create(MediaType.parse("multipart/form-data"), loginid));
            addPostRequest.put("payment_amount", RequestBody.create(MediaType.parse("multipart/form-data"), payment_amount));
            addPostRequest.put("order_id", RequestBody.create(MediaType.parse("multipart/form-data"), order_id));
            addPostRequest.put("delivery_type", RequestBody.create(MediaType.parse("multipart/form-data"), delivery_type));
            addPostRequest.put("received_by", RequestBody.create(MediaType.parse("multipart/form-data"), received_by));
            APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
            Call<Schoollistmodel> editorder = apiInterface.editorder(addPostRequest, stdid, std, boys, girls);
            editorder.enqueue(new Callback<Schoollistmodel>() {
                @Override
                public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                    showHideProgressDialog(true);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatus().toString().equals("true")) {
                                showHideProgressDialog(false);
                                Log.d("loggg1", "true");
                                Toast.makeText(Delivernow.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Delivernow.this, Orderllist.class);
                                i.putExtra("isComp","Yes");
                                startActivity(i);
                                finish();

                                // order is fully delivered //

                            } else {
                                showHideProgressDialog(false);
                                Toast.makeText(Delivernow.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("loggg1", "false");
                            showHideProgressDialog(false);
                            Toast.makeText(Delivernow.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showHideProgressDialog(false);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(Delivernow.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Schoollistmodel> call, Throwable t) {
                    showHideProgressDialog(false);
                    Toast.makeText(Delivernow.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_delivernow;
    }

    private void orderlist(String strsearch) {
        loginid = UserPreference.getInstance(Delivernow.this).getLoginId();
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(Delivernow.this).create(APIInterface.class);
        Call<ResponseOrderList> userlist = apiInterface.orderlist(loginid, ordertype, strsearch);
        userlist.enqueue(new Callback<ResponseOrderList>() {
            @Override
            public void onResponse(Call<ResponseOrderList> call, Response<ResponseOrderList> response) {
                //   hideSwipeRefreshView();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().isStatus()) {
                            showHideProgressDialog(false);
                            recylceorderlist.setVisibility(View.VISIBLE);
                            if (response.body().getData() != null && response.body().getData().size() != 0) {
                                //list
                                if (schoollistdetails != null) {
                                    if (schoollistdetails.size() == response.body().getData().size()) {
                                        //                     isLastPage = true;
                                    }
                                    schoollistdetails.clear();
                                }
                                //  for (int i = 0; i < response.body().getData().size(); i++) {
                                schoollistdetails.addAll(user.getStandards());
                                profilelistadapter.addData(schoollistdetails);
                                //  }
                            }
                        } else {
                            showHideProgressDialog(false);
                            recylceorderlist.setVisibility(View.GONE);
                            //      Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showHideProgressDialog(false);
                        recylceorderlist.setVisibility(View.GONE);
                        //    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Delivernow.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseOrderList> call, Throwable t) {
                //  isLastPage = true;
                hideProgressDialog();
                //hideSwipeRefreshView();
            }
        });
    }

    @Override
    public void onClickEvent(int position, List<Editorder> mPlanData, String action) {

    }
}