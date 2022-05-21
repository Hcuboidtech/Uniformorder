package com.uniformorder.uniformorderr.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.activities.OnItemClicked;
import com.uniformorder.uniformorderr.activities.UserPreference;
import com.uniformorder.uniformorderr.adapter.Orderadapter;
import com.uniformorder.uniformorderr.model.Orderlistdetails;
import com.uniformorder.uniformorderr.model.Orderlistmodel;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Completed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Completed extends BaseFragment implements OnItemClicked {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Orderadapter profilelistadapter;
    List<Orderlistdetails> schoollistdetails = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RecyclerView recylceorderlist;
    String loginid;
    EditText edtsearch;
    String strsearch = " ";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Completed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Completed.
     */
    // TODO: Rename and change types and number of parameters
    public static Completed newInstance(String param1, String param2) {
        Completed fragment = new Completed();
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
        recylceorderlist = view.findViewById(R.id.recylceorderlist);


        profilelistadapter = new Orderadapter(getContext(),this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recylceorderlist.setLayoutManager(linearLayoutManager);
        recylceorderlist.setAdapter(profilelistadapter);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        orderlist(strsearch);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_completed;
    }

    private void orderlist(String strsearch) {

        loginid = UserPreference.getInstance(getContext()).getLoginId();

        showHideProgressDialog(true);

        APIInterface apiInterface = APIClient.getClient(getContext()).create(APIInterface.class);
        Call<Orderlistmodel> userlist = apiInterface.orderlist(loginid, "completed", strsearch);
        userlist.enqueue(new Callback<Orderlistmodel>() {
            @Override
            public void onResponse(Call<Orderlistmodel> call, Response<Orderlistmodel> response) {

                //   hideSwipeRefreshView();
                UserPreference.getInstance(getContext()).setpayment_pending("completed");
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
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

                                schoollistdetails.addAll(response.body().getData());
                                profilelistadapter.addData(schoollistdetails);

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
                        //Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Orderlistmodel> call, Throwable t) {
                //  isLastPage = true;
                hideProgressDialog();
                //hideSwipeRefreshView();

            }
        });
    }

    @Override
    public void onClick(String position) {
             Log.d("Completed ->",String.valueOf(position));
    }

    @Override
    public void onItemSend() {

    }
}