package com.uniformorder.uniformorderr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import java.util.List;

public class AddStd extends AppCompatActivity {
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

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {

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
                    Constants.cartlist.add(saveorderRequestdetails);
                }

                Intent makepayment = new Intent(AddStd.this, Quickorderforrm.class);
                makepayment.putParcelableArrayListExtra("saveorderRequest", Constants.cartlist);
                startActivity(makepayment);
                finish();
            }
        });
    }
}