package com.uniformorder.uniformorderr.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.adapter.Addstdadapter;
import com.uniformorder.uniformorderr.adapter.CommonListBottomSheet;
import com.uniformorder.uniformorderr.adapter.Countryadapter;
import com.uniformorder.uniformorderr.adapter.patternadapter;
import com.uniformorder.uniformorderr.model.SaveorderRequestdetails;
import com.uniformorder.uniformorderr.model.Schoollistdetails;
import com.uniformorder.uniformorderr.model.Schoollistmodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

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

public class Quickorderforrm extends BaseAppCompatActivity implements Addstdadapter.OnItemSelecteListener {
    Bundle intent;
    String principal_Name,school_Name,order_id;
    TextView edtschoolname, edtpatternname;
    TextView addtxtpattern, addstd1, addtxtschool;
    TextView schooltxt, patterntxt;
    EditText edtadvdeposit, edtrrate1, edtrrate2;
    RelativeLayout rlschoolname, rlpatternname;
    Button placeorder;
    String spinner;
    RecyclerView rcyestd;
    String login_id = "0", school_id = "0", pattern_id = "0", rate1 = "1", rate2 = "1", deposite = "0", total_amount = "0";
    String selectschoolid = "0", selectpatternid = "0", search_value = " ";
    ImageView img_back, plusschool, pluspattern, plusstudent;
    Spinner patternspinner, schoollspinner;
    String selectedsschoolname = "", selectedpatternname = "";

    List<Schoollistdetails> mListData;
    List<Schoollistdetails> patterndata;


    ArrayList<SaveorderRequestdetails> saveorderRequest;
    List<Integer> std;
    List<Integer> boys;
    List<Integer> girls;
    LinearLayoutManager cartlayout;
    Addstdadapter cartlistAdapter;
    TextView txttotalstdent, totalpayable;
    boolean isChecked = false, isChecked1 = false;

    int rate1to4 = 1, rate5to8 = 1, totalrate = 0;
    int total1to4 = 0, total5to8 = 0;

    int ratee5to8 = 0, ratee1to4 = 0;

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
        //    setContentView(R.layout.activity_quickorderforrm);
        edtschoolname = findViewById(R.id.edtschoolname);
        edtpatternname = findViewById(R.id.edtpatternname);
        addtxtschool = findViewById(R.id.addtxtschool);
        addtxtpattern = findViewById(R.id.addtxtpattern);
        addstd1 = findViewById(R.id.addstd1);
        rlschoolname = findViewById(R.id.rlschoolname);
        rlpatternname = findViewById(R.id.rlpatternname);
        placeorder = findViewById(R.id.placeorder);
        rcyestd = findViewById(R.id.rcyestd);
        img_back = findViewById(R.id.img_back);
        txttotalstdent = findViewById(R.id.txttotalstdent);
        totalpayable = findViewById(R.id.totalpayable);
        edtadvdeposit = findViewById(R.id.edtadvdeposit);
        edtrrate1 = findViewById(R.id.edtrrate1);
        edtrrate2 = findViewById(R.id.edtrrate2);
        schoollspinner = findViewById(R.id.schoollspinner);
        patternspinner = findViewById(R.id.patternspinner);
        schooltxt = findViewById(R.id.schooltxt);
        patterntxt = findViewById(R.id.patterntxt);
        plusschool = findViewById(R.id.plusschool);
        pluspattern = findViewById(R.id.pluspattern);
        plusstudent = findViewById(R.id.plusstudent);

        mListData = new ArrayList<>();
        patterndata = new ArrayList<>();
        saveorderRequest = new ArrayList<SaveorderRequestdetails>();

        rate2 = edtrrate1.getText().toString();

        intent = getIntent().getExtras();

        std = new ArrayList<>();
        boys = new ArrayList<>();
        girls = new ArrayList<>();

        schoollist();
        patternlist();


        if (intent != null) {
                  String sklanem = intent.getString("SchoolName","Q");
                  if ( !sklanem.equals("Q")){
            if (intent.getString("isEdit").equals("order")){
                 principal_Name  = intent.getString("principalName");
                 school_Name = intent.getString("SchoolName");
                 school_id = intent.getString("SchoolId");
                 addtxtschool.setVisibility(View.INVISIBLE);
                 plusschool.setVisibility(View.INVISIBLE);
                 schooltxt.setText(school_Name);
            }
            }else{
                Constants.cartlist = getIntent().getParcelableArrayListExtra("saveorderRequest");
                for (int i = 0; i < Constants.cartlist.size(); i++) {
                    std.add(Constants.cartlist.get(i).getStandard());
                    boys.add(Constants.cartlist.get(i).getBoys());
                    boys.add(Constants.cartlist.get(i).getBoys());
                    girls.add(Constants.cartlist.get(i).getGirls());

                    if (Constants.cartlist.get(i).getStandard() <= 4) {

                        int boysGrildTotal = Constants.cartlist.get(i).getBoys() + Constants.cartlist.get(i).getGirls();
                        total1to4 = total1to4 + boysGrildTotal;

                        Log.d("totall14", String.valueOf(total1to4));
                    } else {

                        int boysGrildTotal = Constants.cartlist.get(i).getBoys() + Constants.cartlist.get(i).getGirls();
                        total5to8 = total5to8 + boysGrildTotal;

                        Log.d("totall58", String.valueOf(total5to8));
                    }


                    Log.d("totall14", String.valueOf(total1to4));
                    Log.d("totall58", String.valueOf(total5to8));

                }

                cartlistAdapter = new Addstdadapter(Constants.cartlist);
                cartlayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                rcyestd.setLayoutManager(cartlayout);
                rcyestd.setAdapter(cartlistAdapter);
                cartlistAdapter.setOnItemClickLister(this);
            }
        }
        else {

        }

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalrate > Integer.parseInt(edtadvdeposit.getText().toString().trim())) {
                    placeordercall();
                } else {
                    Toast.makeText(mBaseAppCompatActivity, "Advance deposit should be less than total payable amount.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rlschoolname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner = "schoolspinner";
                //    setbottomview(spinner);
                isChecked = true;
                schoollspinner.performClick();
            }
        });
        rlpatternname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner = "patternspinner";
                isChecked1 = true;
                patternspinner.performClick();
                //     setbottomview(spinner);
            }
        });

        addstd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, AddStd.class));
            }
        });

        plusstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, AddStd.class));
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.cartlist.clear();
                /*Intent i = new Intent(Quickorderforrm.this, MainActivity.class);
                startActivity(i);
                finish();*/
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchool("");
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid("");
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedpattern("");
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedpatternid("");
                onBackPressed();
            }
        });

        edtrrate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable != null) {
                    rate1 = editable.toString();
                    rate1to4 = Integer.parseInt(rate1);
                    ratee1to4 = rate1to4 * total1to4;
                    totalrate = ratee1to4 + ratee5to8;
                    totalpayable.setText("Total Payable amount : " + totalrate + " INR");
                }
            }
        });
        edtrrate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null) {
                    rate2 = editable.toString();
                    rate5to8 = Integer.parseInt(rate2);
                    ratee5to8 = rate5to8 * total5to8;
                    totalrate = ratee1to4 + ratee5to8;
                    totalpayable.setText("Total Payable amount : " + totalrate + " INR");
                }
            }
        });

        schoollspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String coutryid = String.valueOf(mListData.get(i).getId());
                String selecteddname = mListData.get(i).getName();
                if (isChecked) {
                    schooltxt.setText(selecteddname);
                    selectschoolid = coutryid;
                    Log.d("selecteddname", selecteddname);
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchool(String.valueOf(selecteddname));
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid(String.valueOf(coutryid));
                } else {
                    selectedsschoolname = UserPreference.getInstance(mBaseAppCompatActivity).getselectedSchool();
                    selectschoolid = UserPreference.getInstance(mBaseAppCompatActivity).getselectedSchoolid();
                    schooltxt.setText(selectedsschoolname);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        patternspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String coutryid = String.valueOf(patterndata.get(i).getId());
                String selecteddname = patterndata.get(i).getName();
                if (isChecked1) {
                    patterntxt.setText(selecteddname);
                    selectpatternid = coutryid;
                    Log.d("selecteddname", selecteddname);
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedpattern(String.valueOf(selecteddname));
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedpatternid(String.valueOf(coutryid));
                } else {
                    selectedpatternname = UserPreference.getInstance(mBaseAppCompatActivity).getselectedpattern();
                    selectpatternid = UserPreference.getInstance(mBaseAppCompatActivity).getselectedpatternid();
                    patterntxt.setText(selectedpatternname);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        plusschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, Addschool.class));
            }
        });
        addtxtschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, Addschool.class));
            }
        });
        addtxtpattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, Addpattern.class));
            }
        });
        pluspattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, Addpattern.class));
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_quickorderforrm;
    }

    private void placeordercall() {
        showHideProgressDialog(true);
        login_id = UserPreference.getInstance(this).getLoginId();
        school_id = selectschoolid;
        pattern_id = selectpatternid;
        total_amount = String.valueOf(totalrate);
        deposite = edtadvdeposit.getText().toString();
        LinkedHashMap<String, RequestBody> addPostRequest = new LinkedHashMap<String, RequestBody>();
        addPostRequest.put("login_id", RequestBody.create(MediaType.parse("multipart/form-data"), login_id));
        addPostRequest.put("school_id", RequestBody.create(MediaType.parse("multipart/form-data"), school_id));
        addPostRequest.put("pattern_id", RequestBody.create(MediaType.parse("multipart/form-data"), pattern_id));
        addPostRequest.put("rate1", RequestBody.create(MediaType.parse("multipart/form-data"), rate1));
        addPostRequest.put("rate2", RequestBody.create(MediaType.parse("multipart/form-data"), rate2));
        addPostRequest.put("total_amount", RequestBody.create(MediaType.parse("multipart/form-data"), total_amount));
        addPostRequest.put("deposite", RequestBody.create(MediaType.parse("multipart/form-data"), deposite));
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Schoollistmodel> addorder = apiInterface.addorder(addPostRequest, std, boys, girls);
        addorder.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("loggg", "loggg");
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);
                            Toast.makeText(Quickorderforrm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            //patternllist();
                            Constants.cartlist.clear();
                            UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchool(String.valueOf(" "));
                            UserPreference.getInstance(mBaseAppCompatActivity).setselectedpattern(String.valueOf(" "));
                            UserPreference.getInstance(mBaseAppCompatActivity).setselectedpatternid(String.valueOf(" "));
                            UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid(String.valueOf(" "));
                            startActivity(new Intent(Quickorderforrm.this, MainActivity.class));
                            finish();
                        } else {
                            showHideProgressDialog(false);
                            Toast.makeText(Quickorderforrm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("loggg1", "loggg");
                        showHideProgressDialog(false);
                        Toast.makeText(Quickorderforrm.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Quickorderforrm.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Quickorderforrm.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setbottomview(String spinner) {
        UserPreference.getInstance(this).setschoolspinner(spinner);
        Log.d("logg1", "bt");

        final CommonListBottomSheet loBottomNavigationView = CommonListBottomSheet.newInstance();
        loBottomNavigationView.addListener(Quickorderforrm.this, new CommonListBottomSheet.DailogListener() {
            @Override
            public void onItemClick(String mPosition, String actionType, String s) {
                Log.d("loggg", mPosition);
                switch (actionType) {
                    case "schoolspinner":
                        edtschoolname.setText(s);
                        selectschoolid = mPosition;
                        break;
                    case "patternspinner":
                        edtpatternname.setText(s);
                        selectpatternid = mPosition;
                        break;
                }
            }
        });
        loBottomNavigationView.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onItemSelected2(int alltotal, int stdd1tto4, int std5to8) {
        txttotalstdent.setText("TOTAL STUDENTS " + alltotal);
    }


    private void schoollist() {
        //  loginid=UserPreference.getInstance(getContext()).getLoginId();
        // String  loginid=UserPreference.getInstance(Quickorderforrm.this).getLoginId();;
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(Quickorderforrm.this).create(APIInterface.class);
        Call<Schoollistmodel> schoollist = apiInterface.schoollist(login_id, "100", "0", search_value);
        schoollist.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                //    hideSwipeRefreshView();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);
                            if (response.body().getData() != null && response.body().getData().size() != 0) {
                                //list
                                if (mListData != null) {
                                    mListData.clear();
                                }
                                mListData.addAll(response.body().getData());
                                ///  handle quick order  as intent is null //
                                   if (intent !=null && !intent.getString("SchoolName","Q").equals("Q")) {
                                       if (intent != null && intent.getString("isEdit").equals("order")) {
                                           //// removing the adapter  ///
                                          // value is from orderAdapter
                                       }
                                   }else{
                                    Countryadapter customAdapter = new Countryadapter(Quickorderforrm.this, R.layout.spinnerlayout, mListData);
                                    if (schoollspinner != null) {
                                        schoollspinner.setAdapter(customAdapter);
                                    }
                                }
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
                        Toast.makeText(Quickorderforrm.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
            }
        });
    }

    private void patternlist() {
        //  String  loginid=UserPreference.getInstance(Quickorderforrm.this).getLoginId();;
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(Quickorderforrm.this).create(APIInterface.class);
        Call<Schoollistmodel> schoollist = apiInterface.patternlist(login_id, "100", "0");
        schoollist.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                //    hideSwipeRefreshView();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);
                            if (response.body().getData() != null && response.body().getData().size() != 0) {
                                //list
                                if (patterndata != null) {
                                    patterndata.clear();
                                }
                                patterndata.addAll(response.body().getData());
                                patternadapter customAdapter = new patternadapter(Quickorderforrm.this, R.layout.spinnerlayout1, patterndata);
                                if (patternspinner != null) {
                                    patternspinner.setAdapter(customAdapter);
                                }
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
                        Toast.makeText(Quickorderforrm.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
            }
        });
    }


    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Constants.cartlist.clear();
        *//*Intent i = new Intent(Quickorderforrm.this, MainActivity.class);
        startActivity(i);
        finish();*//*
    }*/
}