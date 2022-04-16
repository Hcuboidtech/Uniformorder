package com.uniformorder.uniformorderr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.Membermodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Addmember extends BaseAppCompatActivity {
    ImageView img_back;
    String patternid, loginid, username, mobile, usertype;
    Button btnaddpattern;
    TextView edtemail;
    EditText edtname, edtnumbernname, edtrole;

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
        //  setContentView(R.layout.activity_addmember);
        img_back = findViewById(R.id.img_back);
        edtname = findViewById(R.id.edtname);
        edtemail = findViewById(R.id.edtemail);
        edtnumbernname = findViewById(R.id.edtnumbernname);
        edtrole = findViewById(R.id.edtrole);
        btnaddpattern = findViewById(R.id.btnaddpattern);
        Intent intent = getIntent();
        patternid = intent.getStringExtra("patternid");
        loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnaddpattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (patternid == null) {

                } else {
                    updatemember();
                }

            }
        });
        if (patternid == null) {

        } else {
            editmember(patternid);
        }
    }

    private void editmember(String patternid) {
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Membermodel> useredit = apiInterface.userlist(loginid);
        useredit.enqueue(new Callback<Membermodel>() {
            @Override
            public void onResponse(Call<Membermodel> call, Response<Membermodel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);

                            for (int i = 0; i < response.body().getData().size(); i++) {
                                if (response.body().getData().get(i).getId() == Integer.valueOf(patternid)) {

                                    edtname.setText(response.body().getData().get(i).getName());
                                    edtnumbernname.setText(response.body().getData().get(i).getMobile());
                                    edtemail.setText(response.body().getData().get(i).getEmail());
                                    edtrole.setText(response.body().getData().get(i).getUserType());
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
                        Toast.makeText(Addmember.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Membermodel> call, Throwable t) {
                showHideProgressDialog(false);
                Toast.makeText(Addmember.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updatemember() {
        username = edtname.getText().toString();
        mobile = edtnumbernname.getText().toString();
        usertype = edtrole.getText().toString();
        if (username.isEmpty() || username.trim().length() == 0) {
            edtname.setError("Enter Member Name");
        } else if (mobile.isEmpty() || mobile.trim().length() == 0) {
            edtnumbernname.setError("Enter number Name");
        } else if (usertype.isEmpty() || usertype.trim().length() == 0) {
            edtrole.setError("Enter Role Name");
        } else {
            showHideProgressDialog(true);
            APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
            Call<Membermodel> useredit = apiInterface.useredit(loginid, patternid, username, mobile, usertype);
            useredit.enqueue(new Callback<Membermodel>() {
                @Override
                public void onResponse(Call<Membermodel> call, Response<Membermodel> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatus().toString().equals("true")) {
                                showHideProgressDialog(false);
                                Toast.makeText(Addmember.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            } else {
                                showHideProgressDialog(false);
                                Toast.makeText(Addmember.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            showHideProgressDialog(false);
                            Toast.makeText(Addmember.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showHideProgressDialog(false);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(Addmember.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Membermodel> call, Throwable t) {
                    showHideProgressDialog(false);
                    Toast.makeText(Addmember.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_addmember;
    }
}