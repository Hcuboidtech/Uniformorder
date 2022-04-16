package com.uniformorder.uniformorderr.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.adapter.DownloadTask;
import com.uniformorder.uniformorderr.model.filtermodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class filteractivity extends BaseAppCompatActivity {
    TextView edtstartdate, edtendtdate;
    ImageView img_back;
    Calendar c = Calendar.getInstance();
    private int mYear, mMonth, mDay, mHour, mMinute;
    String strstartdate, strenddate;
    Button btnDownload;

    String URL = "";

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
        // setContentView(R.layout.activity_filteractivity);
        edtstartdate = findViewById(R.id.edtstartdate);
        edtendtdate = findViewById(R.id.edtendtdate);
        img_back = findViewById(R.id.img_back);
        btnDownload = findViewById(R.id.btnDownload);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        edtstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(filteractivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        edtstartdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        c.set(year, monthOfYear, dayOfMonth);
                        strstartdate = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(year);
                    }
                }, mYear, mMonth, mDay);
                //dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();
            }
        });
        edtendtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(filteractivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        edtendtdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        c.set(year, monthOfYear, dayOfMonth);
                        strenddate = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(year);
                    }
                }, mYear, mMonth, mDay);
                //dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();

            }
        });


        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHideProgressDialog(true);
                String loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();

                APIInterface apiInterface = APIClient.getClient(filteractivity.this).create(APIInterface.class);
                Call<filtermodel> excelexport = apiInterface.excelexport(loginid, strstartdate, strenddate);
                excelexport.enqueue(new Callback<filtermodel>() {
                    @Override
                    public void onResponse(Call<filtermodel> call, Response<filtermodel> response) {

                        if(response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getStatus().toString().equals("true")) {
                                    showHideProgressDialog(false);

                                    URL = response.body().getData();
                                    new DownloadTask(filteractivity.this, URL);

                                } else {
                                    showHideProgressDialog(false);
                                }
                            } else {
                                showHideProgressDialog(false);
                            }
                        }else{
                            showHideProgressDialog(false);
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(filteractivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<filtermodel> call, Throwable t) {
                        hideProgressDialog();
                    }
                });
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_filteractivity;
    }


}