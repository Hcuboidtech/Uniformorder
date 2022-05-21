package com.uniformorder.uniformorderr.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.adapter.Memberadapter;
import com.uniformorder.uniformorderr.adapter.RefreshListener;
import com.uniformorder.uniformorderr.model.Memberdetails;
import com.uniformorder.uniformorderr.model.Membermodel;
import com.uniformorder.uniformorderr.model.Schoollistmodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Memberlist extends BaseAppCompatActivity implements Memberadapter.onItemClickLisnear, SwipeRefreshLayout.OnRefreshListener, RefreshListener,OnItemClicked {
    ImageView img_add, img_back;
    String loginid;
    LinearLayoutManager linearLayoutManager;
    RecyclerView memberlist;
    SwipeRefreshLayout swipeRefreshViewPropertyList;
    Memberadapter profilelistadapter;
    List<Memberdetails> schoollistdetails = new ArrayList<>();
    private int listLimite = 20;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    public static boolean isClickBack = true;

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
        //  setContentView(R.layout.activity_memberlist);
        img_add = findViewById(R.id.img_add);
        img_back = findViewById(R.id.img_back);
        memberlist = findViewById(R.id.memberlist);

        swipeRefreshViewPropertyList = findViewById(R.id.swipeRefreshViewPropertyList);
        profilelistadapter = new Memberadapter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        memberlist.setLayoutManager(linearLayoutManager);
        memberlist.setAdapter(profilelistadapter);

        if (profilelistadapter != null) {
            profilelistadapter.setItemOnClickEvent(this);
        }
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        memberlist();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_memberlist;
    }

    private void memberlist() {
        loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Membermodel> userlist = apiInterface.userlist(loginid);
        userlist.enqueue(new Callback<Membermodel>() {
            @Override
            public void onResponse(Call<Membermodel> call, Response<Membermodel> response) {
                hideSwipeRefreshView();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);
                            if (response.body().getData() != null && response.body().getData().size() != 0) {
                                //list
                                if (schoollistdetails != null) {
                                    if (schoollistdetails.size() == response.body().getData().size()) {
                                        isLastPage = true;
                                    }
                                    schoollistdetails.clear();
                                }
                                schoollistdetails.addAll(response.body().getData());
                                profilelistadapter.addData(schoollistdetails);
                            }
                        } else {
                            showHideProgressDialog(false);
                        }
                    } else {
                        showHideProgressDialog(false);
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Memberlist.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Membermodel> call, Throwable t) {
                isLastPage = true;
                hideProgressDialog();
                hideSwipeRefreshView();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //refresh(isClickBack);
        memberlist();
    }

    @Override
    public void onRefresh() {
        listLimite = 20;
        memberlist();
    }

    @Override
    public void refresh(boolean b) {
        if (!b) {
            memberlist();
        }
    }

    private void loadNextPage() {
        listLimite += 10;
        isLoading = false;
        memberlist();
    }

    public void hideSwipeRefreshView() {
        if (swipeRefreshViewPropertyList != null) {
            if (swipeRefreshViewPropertyList.isRefreshing()) {
                swipeRefreshViewPropertyList.setRefreshing(false);
            }
        }
    }

    @Override
    public void onClickEvent(int position, Memberdetails mPlanData, String action) {
        switch (action) {
            case "edit":
                Log.d("Schoollid1", "loginid");
                Intent i = new Intent(Memberlist.this, Addmember.class);
                i.putExtra("patternid", String.valueOf(mPlanData.getId()));
                startActivity(i);
                break;

            case "delete":
                Log.d("Schoollid1", "loginid");
                memberdelete(mPlanData.getId());
                break;
        }
    }

    private void memberdelete(Integer id) {
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Schoollistmodel> deleteuser = apiInterface.deleteuser(loginid, String.valueOf(id));
        deleteuser.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equalsIgnoreCase("true")) {
                            showHideProgressDialog(false);
                            Toast.makeText(Memberlist.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            memberlist();
                        } else {
                            showHideProgressDialog(false);
                            Toast.makeText(Memberlist.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showHideProgressDialog(false);
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Memberlist.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Schoollistmodel> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClick(String position) {

    }

    @Override
    public void onItemSend() {

    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Memberlist.this, MainActivity.class);
        startActivity(i);
        finish();
    }*/
}