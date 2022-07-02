package com.uniformorder.uniformorderr.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.adapter.PaginationScrollListener;
import com.uniformorder.uniformorderr.adapter.RefreshListener;
import com.uniformorder.uniformorderr.adapter.Schoollistadapter;
import com.uniformorder.uniformorderr.model.DataItem;
import com.uniformorder.uniformorderr.model.ResponseDeleteAddSchool;
import com.uniformorder.uniformorderr.model.ResponseSchoolList;
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

public class Schoollist extends BaseAppCompatActivity implements Schoollistadapter.onItemClickLisnear, SwipeRefreshLayout.OnRefreshListener, RefreshListener {
    ImageView img_add, img_back;
    LinearLayoutManager linearLayoutManager;
    RecyclerView schoollistview;
    SwipeRefreshLayout swipeRefreshViewPropertyList;
    Schoollistadapter profilelistadapter;
    List<DataItem> schoollistdetails = new ArrayList<>();
    private int listLimite = 20;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    public static boolean isClickBack = true;
    String loginid, search_value = " ";
    EditText edtschoolsearch;

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
        // setContentView(R.layout.activity_schoollist);
        img_back = findViewById(R.id.img_back);
        img_add = findViewById(R.id.img_add);
        schoollistview = findViewById(R.id.schoollist);
        edtschoolsearch = findViewById(R.id.edtschoolsearch);
        swipeRefreshViewPropertyList = findViewById(R.id.swipeRefreshViewPropertyList);
        profilelistadapter = new Schoollistadapter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        schoollistview.setLayoutManager(linearLayoutManager);
        schoollistview.setAdapter(profilelistadapter);
        edtschoolsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search_value = editable.toString();
                schoolllist(search_value);
            }
        });

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Schoollist.this, Addschool.class));
            }
        });

        if (profilelistadapter != null) {
            profilelistadapter.setItemOnClickEvent(this);
        }

        swipeRefreshViewPropertyList.setOnRefreshListener(this);

        schoollistview.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems(RecyclerView recyclerView, int dx, int dy) {

                if (!recyclerView.canScrollVertically(-1)) {

                } else if (!recyclerView.canScrollVertically(1)) {

                    if (!isLastPage) {
                        //      mRelativeProgress.setVisibility(View.VISIBLE);
                        isLoading = true;
                        hideSwipeRefreshView();
                        // mocking network delay for API call
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
        schoolllist(search_value);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void loadNextPage() {
        listLimite += 10;
        isLoading = false;
        schoolllist(search_value);
    }

    public void hideSwipeRefreshView() {
        if (swipeRefreshViewPropertyList != null) {
            if (swipeRefreshViewPropertyList.isRefreshing()) {
                swipeRefreshViewPropertyList.setRefreshing(false);
            }
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_schoollist;
    }

    private void schoolllist(String search_value) {
        loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<ResponseSchoolList> schoollist = apiInterface.schoollist(loginid, String.valueOf(listLimite), "0", search_value);
        schoollist.enqueue(new Callback<ResponseSchoolList>() {
            @Override
            public void onResponse(Call<ResponseSchoolList> call, Response<ResponseSchoolList> response) {
                hideSwipeRefreshView();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().isStatus()) {
                            showHideProgressDialog(false);
                            schoollistview.setVisibility(View.VISIBLE);
                            if (response.body().getData() != null && response.body().getData().size() != 0) {
                                //list
                              //  Log.d("LOGCHECK_>", response.body().getData().get(0).getPayCenter());
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
                            schoollistview.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        schoollistview.setVisibility(View.INVISIBLE);
                        showHideProgressDialog(false);
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Schoollist.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSchoolList> call, Throwable t) {
                isLastPage = true;
                hideProgressDialog();
                hideSwipeRefreshView();
            }
        });
    }


    @Override
    public void onClickEvent(int position, DataItem mPlanData, String mActionType) {
        switch (mActionType) {
            case "delete":
                deletecall(mPlanData);
                break;
            case "edit":
                startActivity(new Intent(Schoollist.this, Addschool.class).putExtra("Schoollid", String.valueOf(mPlanData.getId())));
                break;
        }
    }

    private void deletecall(DataItem mPlanData) {
        showHideProgressDialog(true);

        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<ResponseDeleteAddSchool> delete = apiInterface.delete(loginid, String.valueOf(mPlanData.getId()));
        delete.enqueue(new Callback<ResponseDeleteAddSchool>() {
            @Override
            public void onResponse(Call<ResponseDeleteAddSchool> call, Response<ResponseDeleteAddSchool> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().isStatus()) {
                            showHideProgressDialog(false);
                            Toast.makeText(Schoollist.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            schoolllist(search_value);
                        } else {
                            showHideProgressDialog(false);
                            Toast.makeText(Schoollist.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showHideProgressDialog(false);
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Schoollist.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDeleteAddSchool> call, Throwable t) {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        schoolllist(search_value);
    }

    @Override
    public void onRefresh() {
        listLimite = 20;
        schoolllist(search_value);
    }

    @Override
    public void refresh(boolean b) {
        if (!b) {
            schoolllist(search_value);
        }
    }
}