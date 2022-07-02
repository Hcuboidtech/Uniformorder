package com.uniformorder.uniformorderr.activities;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.adapter.DistrictAdapter;
import com.uniformorder.uniformorderr.adapter.PayingCenterAdapter;
import com.uniformorder.uniformorderr.model.DistrictName;
import com.uniformorder.uniformorderr.model.NDSpinner;
import com.uniformorder.uniformorderr.model.PayCenterName;
import com.uniformorder.uniformorderr.model.ResponseDistrict;
import com.uniformorder.uniformorderr.model.ResponsePayCenter;
import com.uniformorder.uniformorderr.model.filtermodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class filteractivity extends BaseAppCompatActivity {
    TextView edtstartdate, edtendtdate,districtText,payCenterText;
    CheckBox allDistrictChecked;
    EditText editTextDistrict,editTextPayCenter;
    ImageView img_back;
    Calendar c = Calendar.getInstance();
    private int mYear, mMonth, mDay, mHour, mMinute;
    String strstartdate, strenddate;
    Button btnDownload;
    String URL = "";
    ProgressDialog progressDialog;
    List<DistrictName> districtList;
    List<PayCenterName>payCenterNames;
    //AppCompatSpinner d,payCenterSpinner;
    RelativeLayout rldistrict,rlpaycenter;
    String selectedDistrict="",selectedPayCenter="",passedDistrict="";
    NDSpinner ndSpinner,payCenterSpinner;
     String loginId ="";
    boolean isChecked = false, isChecked1 = true;
   // List<ResponseDistrict> responseDistricts = new ArrayList<>();

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
        districtList = new ArrayList<>();
        payCenterNames = new ArrayList<>();
        UserPreference.getInstance(mBaseAppCompatActivity).setSelectedDistrict("");
        UserPreference.getInstance(mBaseAppCompatActivity).setSelectedPayCenter("");
         loginId = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();
     //     setContentView(R.layout.activity_filteractivity);
         districtText = findViewById(R.id.district_text);
         payCenterText = findViewById(R.id.paycentrename_text);
         rldistrict = findViewById(R.id.rldistrictSelect);
         rlpaycenter = findViewById(R.id.rlPayingCenter);
        ndSpinner = (NDSpinner) findViewById(R.id.districtSpinner);
        payCenterSpinner = findViewById(R.id.paycenterSpinner);
        progressDialog = new ProgressDialog(this);
        edtstartdate = findViewById(R.id.edtstartdate);
        edtendtdate = findViewById(R.id.edtendtdate);
        img_back = findViewById(R.id.img_back);
        btnDownload = findViewById(R.id.btnDownload);
        allDistrictChecked = findViewById(R.id.selectAllDis_checkBox);

        getDistrict();

        rldistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = true;
                ndSpinner.performClick();
            }
        });

            rlpaycenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  if (!passedDistrict.equals("")){
                    isChecked1 = true;
                 payCenterSpinner.performClick();
                  }
                }
            });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserPreference.getInstance(mBaseAppCompatActivity).setSelectedDistrict("");
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
                        strstartdate = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(dayOfMonth);
                     //   openEndDateDialog();
                    }
                }, mYear, mMonth, mDay);
                dpd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        Log.d("Dialog Dismiss","Dialog Dismiss");
                        dpd.dismiss();
                        openEndDateDialog();
                    }
                });
                //dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();
            }
        });

//        edtendtdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dpd = new DatePickerDialog(filteractivity.this, new DatePickerDialog.OnDateSetListener() {
//
//                    @Override
//                    public void onDateSet(DatePicker view, int year,
//                                          int monthOfYear, int dayOfMonth) {
//                        edtendtdate.setText(year + "/" + (monthOfYear + 1) + "/" +dayOfMonth);
//                        c.set(year, monthOfYear, dayOfMonth);
//                        strenddate = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(dayOfMonth);
//                        Toast.makeText(filteractivity.this, "okay button clicked", Toast.LENGTH_SHORT).show();
//                    }
//                    }, mYear, mMonth, mDay);
//
//                //dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
//
//
//                dpd.show();
//
//            }
//
//
//        });


        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allDistrictChecked.isChecked()) {
                    callExcelExportAllPayCenter();
                }
                if(!allDistrictChecked.isChecked()){
                    callExcelExportApi();
                }
               // showHideProgressDialog(true);
            }
        });


     ndSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              Log.d("ON ITEM ->","SELECTED");
              Log.d("ON ITEM ->",String.valueOf(position));
               String district = districtList.get(position).getDistrictName();
                if (isChecked){
                    Log.d("Item set","Set");
                    Log.d("In the ifChecked ->","Item Select");
                    districtText.setText(district);
                    passedDistrict = district;
                    UserPreference.getInstance(mBaseAppCompatActivity).setSelectedDistrict(district);
                    getPayCenter(passedDistrict);
                }else{
                    Log.d("In the else ->","Item Select");
                     selectedDistrict = UserPreference.getInstance(mBaseAppCompatActivity).getSelectedDistrict();
                     districtText.setText(selectedDistrict);
                }
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
              Log.d("Nothing is Selected","Selected");
          }
      });
      payCenterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             String payCenter = payCenterNames.get(position).getPayCenterName();
                if (isChecked1){
                    payCenterText.setText(payCenter);
                    UserPreference.getInstance(mBaseAppCompatActivity).setSelectedPayCenter(payCenter);
                  if (allDistrictChecked.isChecked()){
                      payCenterText.setText("");
                  }
                }else{
                    selectedPayCenter =UserPreference.getInstance(mBaseAppCompatActivity).getSelectedPayCenter();
                    payCenterText.setText(selectedPayCenter);
                    if (allDistrictChecked.isChecked()){
                        payCenterText.setText("");
                    }
                }
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
               Log.d("NOthing is Selected","Selected");
          }
      });
    }
    private void DownloadFile(String url) {
        progressDialog.setMessage("Downloading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String str = url.replaceAll("http://hcuboidtech.com/","");
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("Downloading");
        request.setDescription("Downloading File.......");
        String cookie = CookieManager.getInstance().getCookie(url);
        request.addRequestHeader("cookie",cookie);
        request.setMimeType("application/vnd.ms-excel");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                str);
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },
                3000
        );

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_filteractivity;
    }



    void getDistrict(){

        APIInterface apiInterface = APIClient.getClient(filteractivity.this)
                .create(APIInterface.class);
        Call<ResponseDistrict> getDis = apiInterface.getDistrict(loginId);
               getDis.enqueue(new Callback<ResponseDistrict>() {
                   @Override
                   public void onResponse(Call<ResponseDistrict> call, Response<ResponseDistrict> response) {
                         if (response.isSuccessful()){
                             if (response.code() == 200){
                                 if (response.body().getData() != null && response.body().getData().size() != 0) {
                                     Log.d("Response GET DISTRICT->", "200");
                                     if (districtList != null) {
                                         districtList.clear();
                                     }
                                     ResponseDistrict responseDistrict = response.body();
                                     districtList.addAll(responseDistrict.getData());
                                     DistrictAdapter districtAdapter = new DistrictAdapter(filteractivity.this, R.layout.spinnerlayout, districtList);
                                     Log.d("DATA - >", responseDistrict.getData().get(0).getDistrictName());
                                    ndSpinner.setAdapter(districtAdapter);
                                 }
                             }// 200
                         }
                   }

                   @Override
                   public void onFailure(Call<ResponseDistrict> call, Throwable t) {
                     Log.d("GET DISTRICT ","Response Failed");
                   }
               });

    }
    void getPayCenter(String passedDistrict){

        APIInterface apiInterface = APIClient.getClient(filteractivity.this)
                .create(APIInterface.class);
          UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();
        Call<ResponsePayCenter> getPayCenter = apiInterface.getPayCenter(loginId,passedDistrict);
        getPayCenter.enqueue(new Callback<ResponsePayCenter>() {
            @Override
            public void onResponse(Call<ResponsePayCenter> call, Response<ResponsePayCenter> response) {
                         if (response.isSuccessful()){
                             if (response.code() == 200){
                                 if (payCenterNames != null) {
                                    payCenterNames.clear();
                                 }
                                 Log.d("Paycenter response","200 ");
                                 ResponsePayCenter responsePayCenter = response.body();
                                 payCenterNames.addAll(responsePayCenter.getData());
                                 PayingCenterAdapter payingCenterAdapter = new PayingCenterAdapter(filteractivity.this
                                 , R.layout.spinnerlayout1,payCenterNames);
                                   payCenterSpinner.setAdapter(payingCenterAdapter);

                             }
                         }
            }

            @Override
            public void onFailure(Call<ResponsePayCenter> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onDestroy() {
       UserPreference.getInstance(mBaseAppCompatActivity).setSelectedDistrict("");
        super.onDestroy();
        Log.d("On Destroy Called","Called");
    }

    @Override
    protected void onStop() {
        Log.d("On StopCalled","Called");
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("On Pause","Called");
        UserPreference.getInstance(mBaseAppCompatActivity).setSelectedDistrict("");
    }
    public void item_CLicked(View view){
        if (allDistrictChecked.isChecked()){
          payCenterText.setText("");
        }
        if (!allDistrictChecked.isChecked()){
            districtText.setText("");
            payCenterText.setText("");
        }
    }

     void callExcelExportApi(){

         String paycenter = payCenterText.getText().toString();
         String district = passedDistrict;
         String loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();

         APIInterface apiInterface = APIClient.getClient(filteractivity.this).create(APIInterface.class);
         Call<filtermodel> excelexport = apiInterface.excelexport(loginid, strstartdate, strenddate,district,paycenter);
         excelexport.enqueue(new Callback<filtermodel>() {
             @Override
             public void onResponse(Call<filtermodel> call, Response<filtermodel> response) {

                 if(response.isSuccessful()) {
                     if (response.body() != null) {
                         if (response.body().getStatus().toString().equals("true")) {
//                                    showHideProgressDialog(true);
                             URL = response.body().getData();
                             // new DownloadTask(filteractivity.this, URL);
                             DownloadFile(URL);
                             Log.d("Catch ->","catch01");
                             Log.d("Catch ->","catch01");
                         } else {
                             showHideProgressDialog(false);
                             Log.d("Catch ->","catch02");
                         }
                     } else {
                         Log.d("Catch ->","catch03");
                         showHideProgressDialog(false);
                     }
                 }else{
                     Log.d("Catch ->","catch04");
                     showHideProgressDialog(false);
                     try {
                         Log.d("Catch ->","catch05");
                         JSONObject jObjError = new JSONObject(response.errorBody().string());
                         Toast.makeText(filteractivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                         Log.d("Catch ->","catch1");
                     } catch (JSONException e) {
                         e.printStackTrace();
                         Log.d("Catch ->","catch1");
                     } catch (IOException e) {
                         e.printStackTrace();
                         hideProgressDialog();
                         Log.d("Catch ->","catch2");
                     }
                 }
             }

             @Override
             public void onFailure(Call<filtermodel> call, Throwable t) {
                 Log.d("Catch ->","catch200");
                 hideProgressDialog();
             }
         });

     }
     void callExcelExportAllPayCenter(){
         //String paycenter = payCenterText.getText().toString();
         String district = passedDistrict;
         String loginid = UserPreference.getInstance(mBaseAppCompatActivity).getLoginId();
         Log.d("Distric",district);
         APIInterface apiInterface = APIClient.getClient(filteractivity.this).create(APIInterface.class);
         Call<filtermodel> excelexport = apiInterface.excelExportAllPayCenter(loginid,"2022-6-1","2022-6-17",district);
         excelexport.enqueue(new Callback<filtermodel>() {
             @Override
             public void onResponse(Call<filtermodel> call, Response<filtermodel> response) {

                 if(response.isSuccessful()) {
                     if (response.body() != null) {
                         if (response.body().getStatus().toString().equals("true")) {
//                                    showHideProgressDialog(true);
                             Log.d("ALL DIST","SUCCSEESSFUL");
                             URL = response.body().getData();
                             // new DownloadTask(filteractivity.this, URL);
                             DownloadFile(URL);
                             Log.d("Catch ->","catch01");
                             Log.d("Catch ->","catch01");
                         } else {
                             showHideProgressDialog(false);
                             Log.d("Catch ->","catch02");
                         }
                     } else {
                         Log.d("Catch ->","catch03");
                         showHideProgressDialog(false);
                     }
                 }else{
                     Log.d("Catch ->","catch04");
                     showHideProgressDialog(false);
                     try {
                         Log.d("Catch ->","catch05");
                         JSONObject jObjError = new JSONObject(response.errorBody().string());
                         Toast.makeText(filteractivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                         Log.d("Catch ->","catch1");
                     } catch (JSONException e) {
                         e.printStackTrace();
                         Log.d("Catch ->","catch1");
                     } catch (IOException e) {
                         e.printStackTrace();
                         hideProgressDialog();
                         Log.d("Catch ->","catch2");
                     }
                 }
             }

             @Override
             public void onFailure(Call<filtermodel> call, Throwable t) {
                 Log.d("Catch ->","catch200");
                 hideProgressDialog();
             }
         });
     }
    void openEndDateDialog(){
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(filteractivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                edtendtdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                c.set(year, monthOfYear, dayOfMonth);
                strenddate = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(dayOfMonth);
                Toast.makeText(filteractivity.this, "okay button clicked", Toast.LENGTH_SHORT).show();
            }
        }, mYear, mMonth, mDay);

        //dpd.getDatePicker().setMaxDate(System.currentTimeMillis());


        dpd.show();

    }
}