package com.uniformorder.uniformorderr.activities;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.Schoollistmodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Profile extends BaseAppCompatActivity {
    TextView changepass, txtllogout, editprofile, txtusertype, txtname;
    ImageView img_back;
    String loginid, tokken, usertype;
    Uri selectedImageUri;
    CircleImageView circleprofileimg;


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
        //setContentView(R.layout.activity_profile);
        changepass = findViewById(R.id.changepass);
        img_back = findViewById(R.id.img_back);
        txtllogout = findViewById(R.id.txtllogout);
        editprofile = findViewById(R.id.editprofile);
        txtusertype = findViewById(R.id.txtusertype);
        txtname = findViewById(R.id.txtname);
        circleprofileimg = findViewById(R.id.circleprofileimg);

        picadd();
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, Changepassword.class);
                startActivity(i);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        txtllogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Profile.this)
                        .setMessage("Are you sure you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                logoutcall();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, Changedetails.class);
                startActivity(i);
            }
        });
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_profile;
    }

    private void logoutcall() {
        showHideProgressDialog(true);

        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Schoollistmodel> logout = apiInterface.logout(loginid, tokken, "");
        logout.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);
                            UserPreference.getInstance(mBaseAppCompatActivity).clear();
                            startActivity(new Intent(Profile.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();
                        } else {
                            showHideProgressDialog(false);
                            Toast.makeText(Profile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        showHideProgressDialog(false);
                        // Toast.makeText(Profile.this, response.raw().message(), Toast.LENGTH_SHORT).show();
                        logoutcall1();
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Profile.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Profile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logoutcall1() {
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Schoollistmodel> logout = apiInterface.logout(loginid, "");
        logout.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);
                            UserPreference.getInstance(mBaseAppCompatActivity).clear();
                            startActivity(new Intent(Profile.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();

                        } else {
                            showHideProgressDialog(false);
                            Toast.makeText(Profile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        showHideProgressDialog(false);
                        // Toast.makeText(Profile.this, response.raw().message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Profile.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Profile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void picadd() {
        selectedImageUri = Uri.parse(UserPreference.getInstance(mBaseAppCompatActivity).getprofileuri());
        String picuri = String.valueOf(selectedImageUri);
        if (picuri.equals("")) {
            circleprofileimg.setImageResource(R.drawable.logo);
        } else {
            Picasso.with(Profile.this).load(selectedImageUri).networkPolicy(NetworkPolicy.NO_CACHE).into(circleprofileimg);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();
        usertype = UserPreference.getInstance(mBaseAppCompatActivity).getUser_Type();
        tokken = UserPreference.getInstance(mBaseAppCompatActivity).getToken();
        String name = UserPreference.getInstance(mBaseAppCompatActivity).getUserSignUpName();
        txtusertype.setText("Your Role: " + usertype);
        txtname.setText(name);
        picadd();
    }
}