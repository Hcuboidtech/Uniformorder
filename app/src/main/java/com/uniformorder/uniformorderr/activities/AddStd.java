package com.uniformorder.uniformorderr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


;
import com.uniformorder.uniformorderr.R;

import com.uniformorder.uniformorderr.model.SaveorderRequestdetails;

import java.util.ArrayList;
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

        btnadd.setOnClickListener(new View.OnClickListener() {

            //List<SaveorderRequestdetails> requestdetailsList = new ArrayList<>();

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
                saveorderRequestdetails.setStandardId(0);
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
                    Log.d("passing std->","as Edit order");
                    makepayment.putExtra("isEditA","Added");
                    makepayment.putExtra("isEdit","order");
                    makepayment.putExtra("SchoolName",isOrder);
                    makepayment.putExtra("PatternName",intent.getString("PatternName","n"));
                    Log.d("PatternpassedAstd ->",intent.getString("PatternName","n"));
                }
              }
                startActivity(makepayment);
               finish();
            }
        });
    }
}