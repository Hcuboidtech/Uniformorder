package com.uniformorder.uniformorderr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.Registermodel;
import com.uniformorder.uniformorderr.model.Schoollistmodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Addpattern extends BaseAppCompatActivity {
    ImageView img_back;
    EditText edtpatternname;
    String loginid, patternid;
    Button btnaddpattern;

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
        // setContentView(R.layout.activity_addpattern);
        img_back = findViewById(R.id.img_back);
        edtpatternname = findViewById(R.id.edtpatternname);
        btnaddpattern = findViewById(R.id.btnaddpattern);
        loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();
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
                    addpatterncall();
                } else {
                    updatepattern();
                }
            }
        });
        if (patternid == null) {
        } else {
            editpattern(patternid);
        }
    }

    private void updatepattern() {
        String patternname = edtpatternname.getText().toString();
        if (patternname.isEmpty() || patternname.trim().length() == 0) {
            //  Toast.makeText(this, Constants.vl_email, Toast.LENGTH_SHORT).show();
            edtpatternname.setError("Enter Pattern Name");
        } else {
            showHideProgressDialog(true);
            APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
            Call<Registermodel> updatpattern = apiInterface.updatpattern(loginid, patternid, patternname);
            updatpattern.enqueue(new Callback<Registermodel>() {
                @Override
                public void onResponse(Call<Registermodel> call, Response<Registermodel> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatus().toString().equalsIgnoreCase("true")) {
                                showHideProgressDialog(false);
                                Toast.makeText(Addpattern.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                onBackPressed();

                            } else {
                                showHideProgressDialog(false);
                                Toast.makeText(Addpattern.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            showHideProgressDialog(false);
                        }
                    } else {
                        showHideProgressDialog(false);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(Addpattern.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Addpattern.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void editpattern(String patternid) {
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Schoollistmodel> patternlist = apiInterface.patternlist(loginid, "20", "0");
        patternlist.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                if (response.body().getData().get(i).getId() == Integer.valueOf(patternid)) {
                                    edtpatternname.setText(response.body().getData().get(i).getName());
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
                        Toast.makeText(Addpattern.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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

    private void addpatterncall() {
        String patternname = edtpatternname.getText().toString();
        if (patternname.isEmpty() || patternname.trim().length() == 0) {
            //  Toast.makeText(this, Constants.vl_email, Toast.LENGTH_SHORT).show();
            edtpatternname.setError("Enter Pattern Name");
        } else {
            showHideProgressDialog(true);
            APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
            Call<Registermodel> addpattern = apiInterface.addpattern(loginid, patternname);
            addpattern.enqueue(new Callback<Registermodel>() {
                @Override
                public void onResponse(Call<Registermodel> call, Response<Registermodel> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatus().toString().equalsIgnoreCase("true")) {
                                showHideProgressDialog(false);
                                /*Intent i = new Intent(Addpattern.this, Patternlist.class);
                                startActivity(i);
                                finish();*/
                                Toast.makeText(Addpattern.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                onBackPressed();

                            } else {
                                showHideProgressDialog(false);
                                Toast.makeText(Addpattern.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            showHideProgressDialog(false);
                        }
                    } else {
                        showHideProgressDialog(false);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(Addpattern.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Addpattern.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_addpattern;
    }
}