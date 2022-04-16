package com.uniformorder.uniformorderr.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.adapter.PaginationScrollListener;
import com.uniformorder.uniformorderr.adapter.Patternlistadapter;
import com.uniformorder.uniformorderr.adapter.RefreshListener;
import com.uniformorder.uniformorderr.model.Schoollistdetails;
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

public class Patternlist extends BaseAppCompatActivity implements Patternlistadapter.onItemClickLisnear, SwipeRefreshLayout.OnRefreshListener, RefreshListener {
    ImageView img_add, img_back;
    LinearLayoutManager linearLayoutManager;
    RecyclerView patternllist;
    SwipeRefreshLayout swipeRefreshViewPropertyList;
    Patternlistadapter profilelistadapter;
    List<Schoollistdetails> schoollistdetails = new ArrayList<>();
    private int listLimite = 20;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    public static boolean isClickBack = true;
    String loginid;

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
        img_back = findViewById(R.id.img_back);
        img_add = findViewById(R.id.img_add);
        patternllist = findViewById(R.id.patternllist);
        swipeRefreshViewPropertyList = findViewById(R.id.swipeRefreshViewPropertyList);
        profilelistadapter = new Patternlistadapter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        patternllist.setLayoutManager(linearLayoutManager);
        patternllist.setAdapter(profilelistadapter);
        patternllist();
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Patternlist.this, Addpattern.class));
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (profilelistadapter != null) {
            profilelistadapter.setItemOnClickEvent(this);
        }
        swipeRefreshViewPropertyList.setOnRefreshListener(this);
        patternllist.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(-1)) {
                } else if (!recyclerView.canScrollVertically(1)) {
                    if (!isLastPage) {
                        isLoading = true;
                        hideSwipeRefreshView();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadNextPage();
                            }
                        }, 1500);
                    }
                }
            }

            @Override
            public int getTotalPageCount() {
                return schoollistdetails.size();
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_patternlist;
    }


    private void loadNextPage() {
        listLimite += 10;
        isLoading = false;
        patternllist();
    }

    @Override
    protected void onResume() {
        super.onResume();
        patternllist();
    }

    private void patternllist() {
        loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Schoollistmodel> patternlist = apiInterface.patternlist(loginid, String.valueOf(listLimite), "0");
        patternlist.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
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
                        Toast.makeText(Patternlist.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Schoollistmodel> call, Throwable t) {
                isLastPage = true;
                hideProgressDialog();
                hideSwipeRefreshView();
            }
        });
    }

    public void hideSwipeRefreshView() {
        if (swipeRefreshViewPropertyList != null) {
            if (swipeRefreshViewPropertyList.isRefreshing()) {
                swipeRefreshViewPropertyList.setRefreshing(false);
            }
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void refresh(boolean b) {
        if (!b) {
            patternllist();
        }
    }

    @Override
    public void onClickEvent(int position, Schoollistdetails mPlanData, String action) {
        switch (action) {
            case "delete":
                deletecall(mPlanData);
                break;
            case "edit":
                startActivity(new Intent(Patternlist.this, Addpattern.class).putExtra("patternid", String.valueOf(mPlanData.getId())));
                break;
        }
    }

    private void deletecall(Schoollistdetails mPlanData) {
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Schoollistmodel> deletepattern = apiInterface.deletepattern(loginid, String.valueOf(mPlanData.getId()));
        deletepattern.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equalsIgnoreCase("true")) {
                            showHideProgressDialog(false);
                            Toast.makeText(Patternlist.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            patternllist();
                        } else {
                            showHideProgressDialog(false);
                            Toast.makeText(Patternlist.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showHideProgressDialog(false);
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Patternlist.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Patternlist.this, MainActivity.class);
        startActivity(i);
        finish();
    }*/
}