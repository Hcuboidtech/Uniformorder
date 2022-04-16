package com.uniformorder.uniformorderr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.Registermodel;
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

public class Signupactivity extends BaseAppCompatActivity {
    RelativeLayout login;
    Button Submit;
    EditText etfirstname, etphone, etemail, etpassword;
    String name, password, email, phone, usertype = "admin";
    Spinner categoryspinner;
    List<String> memberlist;
    TextView membertxt;


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
        // setContentView(R.layout.activity_signupactivity);
        login = findViewById(R.id.login);
        Submit = findViewById(R.id.Submit);
        etpassword = findViewById(R.id.etpassword);
        etemail = findViewById(R.id.etemail);
        etphone = findViewById(R.id.etphone);
        etfirstname = findViewById(R.id.etfirstname);
        membertxt = findViewById(R.id.membertxt);
        categoryspinner = findViewById(R.id.categoryspinner);


        membertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoryspinner.performClick();
            }
        });


        memberlist = new ArrayList<String>();
        memberlist.add("Member Role");
        memberlist.add("Admin");
        memberlist.add("Member");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinnerlayout, memberlist);
        dataAdapter.setDropDownViewResource(R.layout.spinnerlayout);
        categoryspinner.setAdapter(dataAdapter);


        categoryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                usertype = categoryspinner.getSelectedItem().toString();

                membertxt.setText(usertype);

                if (usertype.equals("Admin")) {
                    usertype = "admin";
                } else {
                    usertype = "member";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signupactivity.this, LoginActivity.class));
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                signin();
            }
        });


    }

    private void signin() {
        name = etfirstname.getText().toString();
        email = etemail.getText().toString();
        password = etpassword.getText().toString();
        phone = etphone.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (name.isEmpty() || name.trim().length() == 0) {
            etfirstname.setError(Constants.vl_fullname);
        } else if (phone.isEmpty() || phone.trim().length() == 0) {
            etphone.setError(Constants.vl_cellno);
        } else if (phone.length() < 10) {
            etphone.setError(Constants.val_phnVale);
        } else if (email.isEmpty() || email.trim().length() == 0) {
            etemail.setError(Constants.vl_email);
        } else if (!email.matches(emailPattern)) {
            etemail.setError(Constants.vl_invalid_email);
        } else if (password.isEmpty() || password.trim().length() == 0) {
            etpassword.setError(Constants.vl_password);
        } else if (password.length() < 8) {
            etpassword.setError(Constants.vl_lenth_pasw);
        } else {
            showHideProgressDialog(true);
            APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
            Call<Registermodel> signupcall = apiInterface.signup(name, email, phone, password, usertype, "0", "0", "");
            signupcall.enqueue(new Callback<Registermodel>() {
                @Override
                public void onResponse(Call<Registermodel> call, Response<Registermodel> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatus().toString().equals("true")) {
                                showHideProgressDialog(false);
                                UserPreference.getInstance(mBaseAppCompatActivity).setloginUserDetail(response.body().getData());
                                UserPreference.getInstance(mBaseAppCompatActivity).setUserSignUpName(response.body().getData().getName());
                                UserPreference.getInstance(mBaseAppCompatActivity).setPhoneNum(response.body().getData().getMobile());
                                UserPreference.getInstance(mBaseAppCompatActivity).setEmail(response.body().getData().getEmail());
                                UserPreference.getInstance(mBaseAppCompatActivity).setToken(response.body().getData().getAuthorizationToken());
                                UserPreference.getInstance(mBaseAppCompatActivity).setLoginId(response.body().getData().getId());
                                UserPreference.getInstance(mBaseAppCompatActivity).setprofileuri(response.body().getData().getProfile());
                                UserPreference.getInstance(mBaseAppCompatActivity).setUser_Type(response.body().getData().getUserType());

                                startActivity(new Intent(Signupactivity.this, MainActivity.class));
                                finish();

                            } else {
                                showHideProgressDialog(false);
                                Toast.makeText(Signupactivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            showHideProgressDialog(false);
                        }
                    } else {
                        showHideProgressDialog(false);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(Signupactivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Signupactivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_signupactivity;
    }
}