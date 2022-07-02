package com.uniformorder.uniformorderr.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.Dashboardmodell;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.airbnb.lottie.L.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends BaseAppCompatActivity {
    LinearLayout addpattern, addstd, quickorder, addmember, orderll;
    RelativeLayout rlprofile;
    String loginid;
    TextView textrevorder, textrevorderforboys, textrevorderforgirls, textrevpayment, textrmainpayment, textdeliveredorder;
    TextView textboyscount, textgirlscount, name;
    TextView rlorder, rlschool, rlpattern, rlmemberr;
    CircleImageView img_profile;
    Uri selectedImageUri;

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
        //     setContentView(R.layout.activity_main);
        addpattern = findViewById(R.id.addpattern);
        addstd = findViewById(R.id.addstd);
        quickorder = findViewById(R.id.quickorder);
        addmember = findViewById(R.id.addmember);
        rlprofile = findViewById(R.id.rlprofile);
        orderll = findViewById(R.id.orderll);
        name = findViewById(R.id.name);
        img_profile = findViewById(R.id.img_profile);

        textrevorder = findViewById(R.id.textrevorder);
        textrevorderforboys = findViewById(R.id.textrevorderforboys);
        textrevorderforgirls = findViewById(R.id.textrevorderforgirls);
        textrevpayment = findViewById(R.id.textrevpayment);
        textrmainpayment = findViewById(R.id.textrmainpayment);
        textdeliveredorder = findViewById(R.id.textdeliveredorder);
        textboyscount = findViewById(R.id.textboyscount);
        textgirlscount = findViewById(R.id.textgirlscount);

        rlorder = findViewById(R.id.rlorder);
        rlschool = findViewById(R.id.rlschool);
        rlpattern = findViewById(R.id.rlpattern);
        rlmemberr = findViewById(R.id.rlmemberr);

        loginid = UserPreference.getInstance(MainActivity.this).getLoginId();
        String strname = UserPreference.getInstance(mBaseAppCompatActivity).getUserSignUpName();
        name.setText("  Welcome, " + strname);
        picadd();
        dashboardcall();
        addpattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Patternlist.class));
            }
        });
        addstd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LOGCH ->","School Clicked");

                startActivity(new Intent(MainActivity.this, Schoollist.class));
            }
        });

        quickorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, Quickorderforrm.class);
                 startActivity(intent);

            }
        });

        addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Memberlist.class));
            }
        });

        rlprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });

        orderll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserPreference.getInstance(MainActivity.this).setpayment_pending("pending");
                startActivity(new Intent(MainActivity.this, Orderllist.class));
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    private void dashboardcall() {
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Dashboardmodell> dashboardcalll = apiInterface.dashboardcalll(loginid);
        dashboardcalll.enqueue(new Callback<Dashboardmodell>() {
            @Override
            public void onResponse(Call<Dashboardmodell> call, Response<Dashboardmodell> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);

                            textrevorder.setText("Received Orders: " + response.body().getData().getTodayReceivedOrders());
                            textrevorderforboys.setText("Order for Boys: " + response.body().getData().getTodayOrderforBoys());
                            textrevorderforgirls.setText("Order for Girls: " + response.body().getData().getTodayOrderforGirls());
                            textrevpayment.setText("Received Payment: " + response.body().getData().getTodayReceivedPayment() + " INR");
                            textrmainpayment.setText("Remain Payment: " + response.body().getData().getTodayRemainPayment() + " INR");
                            textdeliveredorder.setText("Delivered Order: " + response.body().getData().getTodayDeliveredOrders());
                            textboyscount.setText("Delivered Boy's Dress count: " + response.body().getData().getTodayDeliveredBoysDressCount());
                            textgirlscount.setText("Delivered Girl's Dress count: " + response.body().getData().getTodayDeliveredGirlsDressCount());

                            int members = response.body().getData().getMembers() - 1;

                            rlorder.setText("Order(" + response.body().getData().getPendingOrders() + ")");
                            rlschool.setText("School(" + response.body().getData().getSchools() + ")");
                            rlpattern.setText("Patterns(" + response.body().getData().getPatterns() + ")");
                            rlmemberr.setText("Members(" + members + ")");
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
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Dashboardmodell> call, Throwable t) {

            }
        });
    }


    public void picadd() {
        selectedImageUri = Uri.parse(UserPreference.getInstance(mBaseAppCompatActivity).getprofileuri());
        String picuri = String.valueOf(selectedImageUri);

        if (picuri.equals("")) {
            img_profile.setImageResource(R.drawable.logo);
        } else {
            Picasso.with(MainActivity.this).load(selectedImageUri).networkPolicy(NetworkPolicy.NO_CACHE).into(img_profile);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        picadd();
    }
}