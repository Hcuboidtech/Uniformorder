package com.uniformorder.uniformorderr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.Registermodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseAppCompatActivity {
    Button login;
    LinearLayout Signup;
    TextView forgatepass;
    String email, password;
    EditText etemail, etpassword;

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
        //   setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        Signup = findViewById(R.id.Signup);
        forgatepass = findViewById(R.id.forgatepass);
        etpassword = findViewById(R.id.etpassword);
        etemail = findViewById(R.id.etemail);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Signupactivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                loginin();

            }
        });

        forgatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Changepassword.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    private void loginin() {
        email = etemail.getText().toString();
        password = etpassword.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty() || email.trim().length() == 0) {
            etemail.setError(Constants.vl_email);
        } else if (!email.matches(emailPattern)) {
            etemail.setError(Constants.vl_invalid_email);
        } else if (password.isEmpty() || password.trim().length() == 0) {
            etpassword.setError(Constants.vl_password);
        } else {
            showHideProgressDialog(true);

            APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
            Call<Registermodel> login = apiInterface.login(email, password, "0", "0");
            login.enqueue(new Callback<Registermodel>() {
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
                                UserPreference.getInstance(mBaseAppCompatActivity).setUser_Type(response.body().getData().getUserType());
                                UserPreference.getInstance(mBaseAppCompatActivity).setToken(response.body().getData().getAuthorizationToken());
                                UserPreference.getInstance(mBaseAppCompatActivity).setLoginId(response.body().getData().getId());
                                UserPreference.getInstance(mBaseAppCompatActivity).setprofileuri(response.body().getData().getProfile());

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                            } else {
                                showHideProgressDialog(false);
                                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            showHideProgressDialog(false);
                            //  Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showHideProgressDialog(false);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(LoginActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}