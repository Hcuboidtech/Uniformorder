package com.uniformorder.uniformorderr.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Changepassword extends BaseAppCompatActivity {
    EditText edtoldpass, edtnewpass, edtconfirmpass;
    Button btnchangepass;
    String loginid;
    ImageView img_back;

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
        //setContentView(R.layout.activity_changepassword);
        edtnewpass = findViewById(R.id.edtnewpass);
        edtoldpass = findViewById(R.id.edtoldpass);
        edtconfirmpass = findViewById(R.id.edtconfirmpass);
        btnchangepass = findViewById(R.id.btnchangepass);
        img_back = findViewById(R.id.img_back);
        loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();

        btnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                changpass();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_changepassword;
    }

    private void changpass() {
        String oldpass = edtoldpass.getText().toString();
        String newpass = edtnewpass.getText().toString();
        String cofirmpass = edtconfirmpass.getText().toString();
        if (oldpass.isEmpty() || oldpass.trim().length() == 0) {
            edtoldpass.setError("Enter Old Password");
        } else if (newpass.isEmpty() || newpass.trim().length() == 0) {
            edtnewpass.setError("Enter New Password");
        } else if (!cofirmpass.equals(newpass)) {
            edtconfirmpass.setError("Password not matches");
        } else {
            showHideProgressDialog(true);

            APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
            Call<Registermodel> changepass = apiInterface.changepass(loginid, oldpass, newpass);
            changepass.enqueue(new Callback<Registermodel>() {
                @Override
                public void onResponse(Call<Registermodel> call, Response<Registermodel> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatus().toString().equalsIgnoreCase("true")) {
                                showHideProgressDialog(false);
                                Toast.makeText(Changepassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            } else {
                                showHideProgressDialog(false);
                                Toast.makeText(Changepassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            showHideProgressDialog(false);
                        }
                    } else {
                        showHideProgressDialog(false);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(Changepassword.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Changepassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}