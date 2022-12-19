package com.uniformorder.uniformorderr.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.activities.Changepassword;
import com.uniformorder.uniformorderr.activities.OnItemClicked;
import com.uniformorder.uniformorderr.activities.UserPreference;
import com.uniformorder.uniformorderr.adapter.Orderadapter;
import com.uniformorder.uniformorderr.adapter.PaginationScrollListener;
import com.uniformorder.uniformorderr.model.DeleteOrder;
import com.uniformorder.uniformorderr.model.Orderlistdetails;
import com.uniformorder.uniformorderr.model.Orderlistmodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;
import com.uniformorder.uniformorderr.testModel.DataItem;
import com.uniformorder.uniformorderr.testModel.ResponseOrderList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pendingorder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pendingorder extends BaseFragment implements OnItemClicked {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SwipeRefreshLayout swipeRefreshViewPropertyList;

    Orderadapter profilelistadapter;
    List<DataItem> schoollistdetails = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RecyclerView recylceorderlist;
    String loginid;
    String strsearch = " ";
    EditText edtsearch;


    int limit = 4;
    private boolean isLoading = false;
    private boolean isLastPage = false;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pendingorder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pendingorder.
     */
    // TODO: Rename and change types and number of parameters
    public static Pendingorder newInstance(String param1, String param2) {
        Pendingorder fragment = new Pendingorder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserPreference.getInstance(getContext()).setpayment_pending("pending");

        recylceorderlist = view.findViewById(R.id.recylceorderlist);

        profilelistadapter = new Orderadapter(getContext(),this,"pending");
        linearLayoutManager = new LinearLayoutManager(getContext());
        recylceorderlist.setLayoutManager(linearLayoutManager);
        recylceorderlist.setAdapter(profilelistadapter);
        recylceorderlist.getRecycledViewPool().clear();
        recylceorderlist.setItemAnimator(null);
        profilelistadapter.notifyDataSetChanged();

        edtsearch = view.findViewById(R.id.edtsearch);
        // Inflate the layout for this fragment

        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                strsearch = editable.toString();
                orderlist(strsearch);
                Log.d("TAG1", strsearch);

            }
        });

        orderlist(strsearch);


        recylceorderlist.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(-1)) {
//                    LogUtils.LOGE(TAG, "onScrolledToTop()");
                    //showProgressBar(false)
                  showHideProgressDialog(false);
                } else if (!recyclerView.canScrollVertically(1)) {
                    //hide progress bottom view
                    if (!isLastPage) {
                        isLoading = true;
                        new Handler().postDelayed(() -> loadNextPage(), 1000);
                    }
                }
            }

            @Override
            public int getTotalPageCount() {
                return  schoollistdetails.size();
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

    void loadNextPage() {
        limit += 10;
        isLoading = false;
//        showHideProgressDialog(true);
        orderlist("");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pendingorder;
    }

    private void orderlist(String strsearch) {

        loginid = UserPreference.getInstance(getContext()).getLoginId();

        showHideProgressDialog(true);

        APIInterface apiInterface = APIClient.getClient(getContext()).create(APIInterface.class);
        Call<ResponseOrderList> userlist = apiInterface.orderlist(loginid, "pending", strsearch,String.valueOf(limit));
        userlist.enqueue(new Callback<ResponseOrderList>() {
            @Override
            public void onResponse(Call<ResponseOrderList> call, Response<ResponseOrderList> response) {

                showHideProgressDialog(false);
                UserPreference.getInstance(getContext()).setpayment_pending("pending");
                //   hideSwipeRefreshView();
                if (response.isSuccessful()) {
                    //Orderlistmodel orderlistmodel = response.body();
                 ResponseOrderList responseOrderList = response.body();

                //    List<Orderlistdetails>orderlistdetails =  orderlistmodel.getData();
                    List<DataItem>orderlistdetails =  responseOrderList.getData();
                          Log.d("ID ->",String.valueOf(orderlistdetails.get(0).getId()));
                          UserPreference userPreference = UserPreference.getInstance(getContext());

                          Log.d("Auth Token",userPreference.getToken());
                    if (response.body() != null) {
                        //if (response.body().getStatus().toString().equals("true")) {
                            if (response.body().isStatus()) {
                            showHideProgressDialog(false);
                            recylceorderlist.setVisibility(View.VISIBLE);
                            if (response.body().getData() != null && response.body().getData().size() != 0) {
                                //list
                                if (schoollistdetails != null) {
                                    if (schoollistdetails.size() == response.body().getData().size()) {
                                        //
                                                            isLastPage = true;
                                    }
                                    schoollistdetails.clear();
                                }
                                schoollistdetails.addAll(response.body().getData());
                                profilelistadapter.addData(schoollistdetails);
                              showHideProgressDialog(false);
                            }
                        } else {
                                isLastPage = true;
                            showHideProgressDialog(false);
                            recylceorderlist.setVisibility(View.GONE);
                            UserPreference.getInstance(getContext()).setpayment_pending("pending");
                            //      Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showHideProgressDialog(false);
                        recylceorderlist.setVisibility(View.GONE);
                        UserPreference.getInstance(getContext()).setpayment_pending("pending");
                        //    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                        Log.d("Error ->",jObjError.getString("message"));
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
                isLastPage = true;
                hideProgressDialog();
                //hideSwipeRefreshView();
            }
        });
    }

    @Override
    public void onClick(String position) {
        Log.d("Pending",String.valueOf(position));
        callDeleteApi(position);
    }

    @Override
    public void onItemSend() {

    }

    void callDeleteApi(String position){
          APIInterface apiClient = APIClient.getClient(getContext()).create(APIInterface.class);

          Call<DeleteOrder> deleteOrderCall = apiClient.deleteOrder(position);
           deleteOrderCall.enqueue(new Callback<DeleteOrder>() {
               @Override
               public void onResponse(Call<DeleteOrder> call, Response<DeleteOrder> response) {
                    if (response.isSuccessful()){
                        if(response.body() !=null){
                           Log.d("Removed Item ->","with ID"+position);
                        }
                    }
               }

               @Override
               public void onFailure(Call<DeleteOrder> call, Throwable t) {
                          //Log.d("Failed To removed ->",+t.getMessage().toString())
               }
           });

          }

}
