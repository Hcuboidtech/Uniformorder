package com.uniformorder.uniformorderr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.Registermodel;
import com.uniformorder.uniformorderr.model.ResponseSchoolList;
import com.uniformorder.uniformorderr.model.Schoollistmodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Addschool extends BaseAppCompatActivity {
    Button addschool;
    ImageView img_back;
    EditText edtschoolname, edtprincipalname, edtassiname, edtcityname, edtpayname, edtmobile1, edtmobile2, edtdistrictname;
    String Schoollid, loginid, search_value = "";

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
       // setContentView(R.layout.activity_addschool);
        addschool = findViewById(R.id.addschool);
        img_back = findViewById(R.id.img_back);
        edtschoolname = findViewById(R.id.edtschoolname);
        edtprincipalname = findViewById(R.id.edtprincipalname);
        edtassiname = findViewById(R.id.edtassiname);
        edtcityname = findViewById(R.id.edtcityname);
        edtpayname = findViewById(R.id.edtpayname);
        edtmobile1 = findViewById(R.id.edtmobile1);
        edtmobile2 = findViewById(R.id.edtmobile2);
        edtdistrictname = findViewById(R.id.edtdistrictname);

        Intent intent = getIntent();
        Schoollid = intent.getStringExtra("Schoollid");
        loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();

        if (Schoollid == null) {
            Log.d("Schoollid", loginid);
        } else {
            editschool(Schoollid);
        }

        addschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Schoollid == null) {
                    addschoolcall();
                } else {
                    updateschool();
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void updateschool() {
        String schoolname = edtschoolname.getText().toString();
        String principalname = edtprincipalname.getText().toString();
        String assistantname = edtassiname.getText().toString();
        String city = edtcityname.getText().toString();
        String paycentre = edtpayname.getText().toString();
        String mobile1 = edtmobile1.getText().toString();
        String mobile2 = edtmobile2.getText().toString();
        String strdistict = edtdistrictname.getText().toString();

        if (schoolname.isEmpty() || schoolname.trim().length() == 0) {
            edtschoolname.setError("Enter School Name");
        } else if (principalname.isEmpty() || principalname.trim().length() == 0) {
            edtprincipalname.setError("Enter Principal Name");
        } else if (assistantname.isEmpty() || assistantname.trim().length() == 0) {
          //  edtassiname.setError("Enter Assistant Name");
        } else if (city.isEmpty() || city.trim().length() == 0) {
            edtcityname.setError("Enter Palce");
        } else if (paycentre.isEmpty() || paycentre.trim().length() == 0) {
            edtpayname.setError("Enter Pay Center");
        } else if (strdistict.isEmpty() || strdistict.trim().length() == 0) {
            edtdistrictname.setError("Enter Pay DISTRICT");
        } else if (mobile1.isEmpty() || mobile1.trim().length() == 0) {
            edtmobile1.setError("Enter Mobile Number 1");
        } else if (mobile2.isEmpty() || mobile2.trim().length() == 0) {
          //  edtmobile2.setError("Enter Mobile Number 2");
        } else {
            showHideProgressDialog(true);
            if(TextUtils.isEmpty(edtassiname.getText().toString())){
                edtassiname.setText("");
            }
            if(TextUtils.isEmpty(edtassiname.getText().toString())){
                edtassiname.setText("");
            }
            APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
            Call<Registermodel> updateschool = apiInterface.updateschool(loginid, Schoollid, schoolname, principalname, assistantname, city, paycentre, strdistict, mobile1, mobile2);
            updateschool.enqueue(new Callback<Registermodel>() {
                @Override
                public void onResponse(Call<Registermodel> call, Response<Registermodel> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatus().toString().equalsIgnoreCase("true")) {
                                showHideProgressDialog(false);
                                /*Intent i = new Intent(Addschool.this, Schoollist.class);
                                startActivity(i);
                                finish();*/
                                Toast.makeText(Addschool.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            } else {
                                showHideProgressDialog(false);
                                Toast.makeText(Addschool.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            showHideProgressDialog(false);
                        }
                    } else {
                        showHideProgressDialog(false);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(Addschool.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Registermodel> call, Throwable t) {
                    showHideProgressDialog(false);
                    Toast.makeText(Addschool.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void editschool(String schoollid) {
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<ResponseSchoolList> schoollist = apiInterface.schoollist(loginid, "20", "0", search_value);
        schoollist.enqueue(new Callback<ResponseSchoolList>() {
            @Override
            public void onResponse(Call<ResponseSchoolList> call, Response<ResponseSchoolList> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().isStatus()) {
                            showHideProgressDialog(false);
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                if (response.body().getData().get(i).getId() == Integer.valueOf(schoollid)) {
                                    edtassiname.setText(response.body().getData().get(i).getAssistantName());
                                    edtschoolname.setText(response.body().getData().get(i).getName());
                                    edtprincipalname.setText(response.body().getData().get(i).getPrincipalName());
                                    edtpayname.setText(response.body().getData().get(i).getCombo().getPayCenterName());
                                    edtcityname.setText(response.body().getData().get(i).getCombo().getVillageName());
                                    edtmobile1.setText(response.body().getData().get(i).getMobile1());
                                    edtmobile2.setText(response.body().getData().get(i).getMobile2());
                                    edtdistrictname.setText(response.body().getData().get(i).getCombo().getDistrictName());
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
                        Toast.makeText(Addschool.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSchoolList> call, Throwable t) {
                showHideProgressDialog(false);
                Toast.makeText(Addschool.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addschoolcall() {
        String schoolname = edtschoolname.getText().toString();
        String principalname = edtprincipalname.getText().toString();
        String assistantname = edtassiname.getText().toString();
        String city = edtcityname.getText().toString();
        String paycentre = edtpayname.getText().toString();
        String mobile1 = edtmobile1.getText().toString();
        String mobile2 = edtmobile2.getText().toString();
        String strdist = edtdistrictname.getText().toString();
        if (schoolname.isEmpty() || schoolname.trim().length() == 0) {
            edtschoolname.setError("Enter School Name");
        } else if (principalname.isEmpty() || principalname.trim().length() == 0) {
            edtprincipalname.setError("Enter Principal Name");
        }  else if (city.isEmpty() || city.trim().length() == 0) {
            edtcityname.setError("Enter Palce");
        } else if (paycentre.isEmpty() || paycentre.trim().length() == 0) {
            edtpayname.setError("Enter Pay Center");
        } else if (strdist.isEmpty() || strdist.trim().length() == 0) {
            edtdistrictname.setError("Enter District");
        } else if (mobile1.isEmpty() || mobile1.trim().length() == 0) {
            edtmobile1.setError("Enter Mobile Number 1");
        } else {
            showHideProgressDialog(true);
            APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
            Call<Registermodel> addschool = apiInterface.addschool(loginid, schoolname, principalname, assistantname, city, paycentre, strdist, mobile1, mobile2);
            addschool.enqueue(new Callback<Registermodel>() {
                @Override
                public void onResponse(Call<Registermodel> call, Response<Registermodel> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {

                            if (response.body().getStatus().toString().equalsIgnoreCase("true")) {
                                showHideProgressDialog(false);

                                /*Intent i = new Intent(Addschool.this, Schoollist.class);
                                startActivity(i);
                                finish();*/
                                Toast.makeText(Addschool.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            } else {
                                showHideProgressDialog(false);
                                Toast.makeText(Addschool.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            showHideProgressDialog(false);
                        }
                    } else {
                        showHideProgressDialog(false);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(Addschool.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Registermodel> call, Throwable t) {
                    showHideProgressDialog(false);
                    Toast.makeText(Addschool.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_addschool;
    }
}