package com.uniformorder.uniformorderr.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.activities.Addpattern;
import com.uniformorder.uniformorderr.activities.Changepassword;
import com.uniformorder.uniformorderr.activities.UserPreference;
import com.uniformorder.uniformorderr.model.Schoollistdetails;
import com.uniformorder.uniformorderr.model.Schoollistmodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonListBottomSheet extends BaseBottomSheetDialogFragment {
    Context mContext;
    DailogListener dailogListener;
    RecyclerView recyclerviewAddCollection;
    ImageView AddAddress;
    FrameLayout loFlCancel;
    byte[] datauserid, datadevice_id;
    String device_id, userid, baseuserid, basedevice_id;

    LinearLayoutManager linearLayoutManager;
    Spinner spinnerschoollist;

    List<Schoollistdetails> mListData;


    public static CommonListBottomSheet newInstance() {
        return new CommonListBottomSheet();
    }

    @Override
    public void onViewCreated(View contentView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(contentView, savedInstanceState);
        spinnerschoollist = contentView.findViewById(R.id.spinnerschoollist);
        mListData = new ArrayList<>();

        String spinner = UserPreference.getInstance(getContext()).getschoolspinner();

        if (spinner.equals("schoolspinner")) {
            schoollist();
        } else if (spinner.equals("patternspinner")) {
            patternlist();
        }


        spinnerschoollist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String coutryid = String.valueOf(mListData.get(i).getId());
                String selecteddname = mListData.get(i).getName();

                if (dailogListener != null) {
                    dailogListener.onItemClick(coutryid, spinner, selecteddname);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

   private void patternlist() {
//
//        String loginid = UserPreference.getInstance(getContext()).getLoginId();
//        ;
//
//        // showProgressDialog(true);
//
//        APIInterface apiInterface = APIClient.getClient(getContext()).create(APIInterface.class);
//        Call<Schoollistmodel> schoollist = apiInterface.patternlist(loginid, "10", "0");
//        schoollist.enqueue(new Callback<Schoollistmodel>() {
//            @Override
//            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
//
//                //    hideSwipeRefreshView();
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        if (response.body().getStatus().toString().equals("true")) {
//                            //     showProgressDialog(false);
//
//                            if (response.body().getData() != null && response.body().getData().size() != 0) {
//                                //list
//                                if (mListData != null) {
//                                    mListData.clear();
//                                }
//                                mListData.addAll(response.body().getData());
//
//                                Countryadapter customAdapter = new Countryadapter(getActivity(), R.layout.spinnerlayout, mListData);
//                                if (spinnerschoollist != null) {
//                                    spinnerschoollist.setAdapter(customAdapter);
//                                }
//                            }
//
//
//                        } else {
//                            // showProgressDialog(false);
//                        }
//                    } else {
//                        //showProgressDialog(false);
//                    }
//                } else {
//                    try {
//                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Schoollistmodel> call, Throwable t) {
//
//            }
//        });
//
//
   }

    private void schoollist() {
//          loginid=UserPreference.getInstance(getContext()).getLoginId();
//        String loginid = UserPreference.getInstance(getContext()).getLoginId();
//        ;
//
//         showProgressDialog(true);
//
//        APIInterface apiInterface = APIClient.getClient(getContext()).create(APIInterface.class);
//        Call<Schoollistmodel> schoollist = apiInterface.schoollist(loginid, "10", "0", "");
//        schoollist.enqueue(new Callback<Schoollistmodel>() {
//            @Override
//            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
//
//                    hideSwipeRefreshView();
//
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        if (response.body().getStatus().toString().equals("true")) {
//                                 showProgressDialog(false);
//
//                            if (response.body().getData() != null && response.body().getData().size() != 0) {
//                                list
//                                if (mListData != null) {
//                                    mListData.clear();
//                                }
//                                mListData.addAll(response.body().getData());
//
//                                Countryadapter customAdapter = new Countryadapter(getActivity(), R.layout.spinnerlayout, mListData);
//                                if (spinnerschoollist != null) {
//                                    spinnerschoollist.setAdapter(customAdapter);
//                                }
//                            }
//
//
//                        } else {
//                             showProgressDialog(false);
//                        }
//                    } else {
//                        showProgressDialog(false);
//                    }
//                } else {
//                    try {
//                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }


//            }

//            @Override
//            public void onFailure(Call<Schoollistmodel> call, Throwable t) {
//
//            }
//        });


    }

    @Override
    public int getLayoutResource() {
        return R.layout.common_bottom_sheet_layout;
    }

    public void addListener(Context mBaseAppCompatActivity, DailogListener dailog) {
        this.dailogListener = dailog;
        this.mContext = mBaseAppCompatActivity;

    }


    public interface DailogListener {
        void onItemClick(String mPosition, String actionType, String selectname);
    }


}