package com.uniformorder.uniformorderr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


;
import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.adapter.Addstdadapter;
import com.uniformorder.uniformorderr.model.Saveorder;
import com.uniformorder.uniformorderr.model.SaveorderRequestdetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddStd extends AppCompatActivity {
    Bundle intent;
    Intent ParcelCheck;
    Intent makepayment;
    String isOrder;
    Button btnadd;
    EditText edtstd, edtboys, edtgirls;
    int strboys = 0, strgirls = 0, strstd = 0;
    ImageView img_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_std);
        btnadd = findViewById(R.id.btnadd);
        edtgirls = findViewById(R.id.edtgirls);
        edtstd = findViewById(R.id.edtstd);
        edtboys = findViewById(R.id.edtboys);
        img_back = findViewById(R.id.img_back);
        intent = getIntent().getExtras();
        ParcelCheck = getIntent();
        if (intent != null){
            Log.d("SchoolNameRecAdStd",intent.getString("order","n"));
            isOrder = intent.getString("order","no");
        }
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        edtstd.setFilters(new InputFilter[]{new InputFilterMinMax("1","12")});
//          edtstd.addTextChangedListener(new TextWatcher() {
//              @Override
//              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//              }
//
//              @Override
//              public void onTextChanged(CharSequence s, int start, int before, int count) {
//                  String value =edtstd.getText().toString();
//                try{
//                    if (!TextUtils.isEmpty(value)){
//                        int number = Integer.parseInt(value);
//                        if (number>12){
//                        Log.d("Num>12","Number IF");
//                            //edtstd.setText("12");
//                        }else{
//                            String str =String.valueOf(number);
//                           // edtstd.setText("s");
//                            Log.d("Num>12","Number else");
//                        }
//                    }
//                }catch (Exception exception){
//                    Log.d("Exception",exception.getMessage());
//                }
//
//              }
//
//              @Override
//              public void afterTextChanged(Editable s) {
//
//              }
//          });

        btnadd.setOnClickListener(new View.OnClickListener() {

          //  List<SaveorderRequestdetails>requestdetailsList = new ArrayList<>();

            @Override
            public void onClick(View view) {
                try {
                    strboys = Integer.parseInt(edtboys.getText().toString());
                    strgirls = Integer.parseInt(edtgirls.getText().toString());
                    strstd = Integer.parseInt(edtstd.getText().toString());
                } catch (NumberFormatException ex) { // handle your exception
                  Log.d("Exception",ex.getMessage());
                }
                SaveorderRequestdetails saveorderRequestdetails = new SaveorderRequestdetails();
                saveorderRequestdetails.setStandard(strstd);
                saveorderRequestdetails.setBoys(strboys);
                saveorderRequestdetails.setGirls(strgirls);
                if (Constants.cartlist != null) {
                    if (ParcelCheck.hasExtra("Addsonstd")){
                     Constants.cartlist = intent.getParcelableArrayList("Addsonstd");
                     Constants.editcardList.clear();
                     Log.d("Parcel Merged","getFrom isEdit");
                    }
                    Constants.cartlist.add(saveorderRequestdetails);
                }

                // setting up the parcel for Order | | Edit -> is Order -> AddStd

                if (intent !=null && !isOrder.equals("no")) {
                    makepayment = new Intent(AddStd.this, Quickorderforrm.class);
                    makepayment.putParcelableArrayListExtra("saveorderRequest1", Constants.cartlist);

                }else{
                    // for order
                    makepayment = new Intent(AddStd.this, Quickorderforrm.class);
                    makepayment.putParcelableArrayListExtra("saveorderRequest", Constants.cartlist);
                }

              if (intent != null){
                if (!isOrder.equals("no")){
                    makepayment.putExtra("isEditA","Added");
                    makepayment.putExtra("isEdit","order");
                    makepayment.putExtra("SchoolName",isOrder);
                    makepayment.putExtra("PatternName",intent.getString("PatternName","n"));
                    Log.d("PatternpassedAstd ->",intent.getString("PatternName","n"));
                }
              }
                startActivity(makepayment);
            }
        });
    }
}