package com.uniformorder.uniformorderr.activities;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.adapter.Addstdadapter;
import com.uniformorder.uniformorderr.adapter.CommonListBottomSheet;
import com.uniformorder.uniformorderr.adapter.patternadapter;
import com.uniformorder.uniformorderr.model.DataItem;
import com.uniformorder.uniformorderr.model.EditOrderResponse;
import com.uniformorder.uniformorderr.model.NDSpinner;
import com.uniformorder.uniformorderr.model.ResponseSchoolList;
import com.uniformorder.uniformorderr.model.SaveorderRequestdetails;
import com.uniformorder.uniformorderr.model.Schoollistdetails;
import com.uniformorder.uniformorderr.model.Schoollistmodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quickorderforrm extends BaseAppCompatActivity implements Addstdadapter.OnItemSelecteListener {

    AutoCompleteTextView autoCompleteTextView;
    public EditOrderListViewModel editOrderViewModel;
    Calendar c = Calendar.getInstance();
    private int mYear, mMonth, mDay, mHour, mMinute;
    int count = 2;  /// custom value
    SharedPreferences sharedPreferenceUtils;
    SharedPreferences.Editor myEdit;
    Intent parcebelCheck;
    Bundle intent;
    String principal_Name, school_Name, order_id, school_Id, patternName,formNumber,orderBy;
    TextView edtschoolname, edtpatternname,textViewDate;
    TextView addtxtpattern, addstd1, addtxtschool;
    TextView schooltxt, patterntxt;
    EditText edtadvdeposit, edtrrate1, edtrrate2,edtrrate3,edFormNumber,edorderBy;
    RelativeLayout rlschoolname, rlpatternname;
    Button placeorder;
    String spinner;
    RecyclerView rcyestd;
    String passedDate="";
    String login_id = "0", school_id = "0", pattern_id = "0", rate1 = "1", rate2 = "1", rate3="1", deposite = "0", total_amount = "0";
    String selectschoolid = "0", selectpatternid = "0", search_value = " ";
    ImageView img_back, plusschool, pluspattern, plusstudent;
    NDSpinner patternspinner;// schoollspinner;
    String selectedsschoolname = "", selectedpatternname = "";

    List<DataItem> mListData = new ArrayList<com.uniformorder.uniformorderr.model.DataItem>();
    List<Schoollistdetails> patterndata;

    List<DataItem>schoolItems;
    ArrayList<SaveorderRequestdetails> saveorderRequest;
    List<Integer> std;
    List<Integer> boys;
    List<Integer> girls;
    List<Integer>stdId;
    LinearLayoutManager cartlayout;
    Addstdadapter cartlistAdapter;
    TextView txttotalstdent, totalpayable;
    boolean isChecked = false, isChecked1 = false;

    int rate1to4 = 1, rate5to8 = 1, rate9to12=1, totalrate = 0;
    int total1to4 = 0, total5to8 = 0 ,total9to12 =0;

    int ratee5to8 = 0, ratee1to4 = 0, ratee9to12 =0;

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

        sharedPreferenceUtils = getSharedPreferences("Mypref", MODE_PRIVATE);
        myEdit = sharedPreferenceUtils.edit();
        parcebelCheck = getIntent();
        //  setContentView(R.layout.activity_quickorderforrm);
        editOrderViewModel = new ViewModelProvider(this).get(EditOrderListViewModel.class);
        textViewDate= findViewById(R.id.tvDate);
        edorderBy = findViewById(R.id.edOrderBy);
        autoCompleteTextView = findViewById(R.id.autocompleteTextView);
        edtschoolname = findViewById(R.id.edtschoolname);
        edtpatternname = findViewById(R.id.edtpatternname);
        addtxtschool = findViewById(R.id.addtxtschool);
        addtxtpattern = findViewById(R.id.addtxtpattern);
        addstd1 = findViewById(R.id.addstd1);
//        rlschoolname = findViewById(R.id.rlschoolname);
        rlpatternname = findViewById(R.id.rlpatternname);
        placeorder = findViewById(R.id.placeorder);
        rcyestd = findViewById(R.id.rcyestd);
        img_back = findViewById(R.id.img_back);
        txttotalstdent = findViewById(R.id.txttotalstdent);
        totalpayable = findViewById(R.id.totalpayable);
        edtadvdeposit = findViewById(R.id.edtadvdeposit);
        edtrrate1 = findViewById(R.id.edtrrate1);
        edtrrate2 = findViewById(R.id.edtrrate2);
        edtrrate3 = findViewById(R.id.edtrrate3);
//        schoollspinner = (NDSpinner)findViewById(R.id.schoollspinner);
        patternspinner = (NDSpinner) findViewById(R.id.patternspinner);
//        schooltxt = findViewById(R.id.schooltxt);
        patterntxt = findViewById(R.id.patterntxt);
        plusschool = findViewById(R.id.plusschool);
        pluspattern = findViewById(R.id.pluspattern);
        plusstudent = findViewById(R.id.plusstudent);
        edFormNumber = findViewById(R.id.edtformNumber);

        patterndata = new ArrayList<>();
        saveorderRequest = new ArrayList<SaveorderRequestdetails>();

        schoolItems = new ArrayList<>();
        rate2 = edtrrate1.getText().toString();

        intent = getIntent().getExtras();

        std = new ArrayList<>();
        boys = new ArrayList<>();
        girls = new ArrayList<>();
        stdId = new ArrayList<>();

        schoollist();
        patternlist();
        // used to get select the date
        handleSelectDate();

        if (intent != null) {

            if(!sharedPreferenceUtils.getString("form","").equals("")){
                edFormNumber.setText(sharedPreferenceUtils.getString("form",""));
            }

            if (!sharedPreferenceUtils.getString("displayDate","n").equals("n")){
            textViewDate.setText(sharedPreferenceUtils.getString("displayDate","n"));
            System.out.println("TEST THE DATE  - >"+sharedPreferenceUtils.getString("displayDate","n"));

               if (!UserPreference.getInstance(mBaseAppCompatActivity).getselectedSchool().equals("")){
                   autoCompleteTextView.setText(UserPreference.getInstance(mBaseAppCompatActivity).getselectedSchool());
                selectschoolid=UserPreference.getInstance(mBaseAppCompatActivity).getselectedSchoolid();
                }
               if(!UserPreference.getInstance(mBaseAppCompatActivity).getselectedpattern().equals("")){
                   selectedpatternname = UserPreference.getInstance(mBaseAppCompatActivity).getselectedpattern();
                   selectpatternid = UserPreference.getInstance(mBaseAppCompatActivity).getselectedpatternid();

                   patterntxt.setText(selectedpatternname);
               }

            }

            // this is used for the date setup FROM SHARED PREF
            String sklanem = intent.getString("SchoolName", "Q");
            if (!sklanem.equals("Q")) {
                myEdit.putString("mode","edit");
      //////// this means ur editing the order /// cuz u had a school name which u didn't get in
        // quick order
                edorderBy.setEnabled(false);
                textViewDate.setEnabled(false);
//                edFormNumber.setEnabled(false);
                autoCompleteTextView.setEnabled(false);

                addtxtschool.setVisibility(View.INVISIBLE);
                plusschool.setVisibility(View.INVISIBLE);
                pluspattern.setVisibility(View.INVISIBLE);
                addtxtpattern.setVisibility(View.INVISIBLE);
                if (intent.getString("isEdit").equals("order")) {

                    if (parcebelCheck.hasExtra("EditData")) {
                        for (int i = 0; i < Constants.editcardList.size(); i++) {
                            Log.d("REC STD_ID->",Constants.editcardList.get(i).getStandardId().toString());
                           //  stdId.add(Constants.editcardList.get(i).getStandardId());
                        }
                        Constants.editcardList = getIntent().getParcelableArrayListExtra("EditData");
                        ArrayList<SaveorderRequestdetails> list = Constants.editcardList;
                   //     Log.d("FETCEDH BYS", list.get(0).getBoys().toString());
                        String order_idp, schhool_idp, login_idp, pattern_idp,
                                rate1p, rate2p,rate3p,total_amountp, depositep,
                                patternnamep,pendingAMT,formNum,addDate,orderby1;

                        patternnamep = intent.getString("PatternName", "n");
                        Log.d("PatternRecived->", patternnamep);
                        patternName = patternnamep;
                        myEdit.putString("patternName",patternnamep);
                        order_idp = String.valueOf(intent.getInt("orderId", 0));
                        schhool_idp = String.valueOf(intent.getInt("idSchool", 0));
                        login_idp = intent.getString("loginId", "n");
                        pattern_idp = intent.getString("idpattern", "n");
                        rate1p = intent.getString("rate1", "n");
                        rate2p = intent.getString("rate2", "n");
                        rate3p = intent.getString("rate3","n");
                        pendingAMT = intent.getString("pendingamt","n");
                        total_amountp = intent.getString("totalam", "n");
                        depositep = intent.getString("deposite", "n");
                        formNum = intent.getString("formNum","n");
                        addDate = intent.getString("addondate","n");
                        orderby1 = intent.getString("orderby","n");
                       // editOrderViewModel.saveData(order_idp, schhool_idp, login_idp, pattern_idp, rate1p, rate2p,rate3p, total_amountp, depositep);
                        myEdit.putString("orderid", order_idp);
                        myEdit.putString("schoolid", schhool_idp);
                        myEdit.putString("loginid", login_idp);
                        myEdit.putString("patternid", pattern_idp);
                        myEdit.putString("rate1", rate1p);
                        myEdit.putString("rate2", rate2p);
                        myEdit.putString("rate3",rate3p);
                        myEdit.putString("totalam", total_amountp);
                        myEdit.putString("deposite", depositep);
                        myEdit.putString("pendingamt",pendingAMT);
                        myEdit.putString("formNumber",formNum);
                        myEdit.putString("addondate",addDate);
                        myEdit.putString("orderby",orderby1);
                        myEdit.commit();
                        Log.d("PatternSAVED",sharedPreferenceUtils.getString("patternName","n"));

                          if (rate2p.equals("n")){
                              rate2p = "0";
                          }
                          if (rate1p.equals("n")){
                              rate1p = "0";
                          }
                          if (rate3p.equals("n")){
                              rate3p = "0";
                          }
                          edFormNumber.setText(formNum);
                          textViewDate.setText(addDate);
                          edorderBy.setText(orderby1);
                        edtrrate1.setText(String.valueOf((int) Double.parseDouble(rate1p)));
                        edtrrate2.setText(String.valueOf((int) Double.parseDouble(rate2p)));
                        edtrrate3.setText(String.valueOf((int) Double.parseDouble(rate3p)));
                     //   totalpayable
                        edtadvdeposit.setText(String.valueOf((int) Double.parseDouble(depositep)));
                        total_amountp = String.valueOf((int) Double.parseDouble(total_amountp));
                        totalrate = (int) Double.parseDouble(total_amountp);

                        placeorder.setText("Update Order");
                        totalpayable.setText("Total Payable amount : " + total_amountp + " INR");


                        ////adding the card list

                        Log.d("Receive ->", "Parcel");

                        Constants.editcardList = getIntent().getParcelableArrayListExtra("EditData");

                        for (int i = 0; i < Constants.editcardList.size(); i++) {
                            std.add(Constants.editcardList.get(i).getStandard());
                            boys.add(Constants.editcardList.get(i).getBoys());
                            girls.add(Constants.editcardList.get(i).getGirls());

                            if (Constants.editcardList.get(i).getStandard() <= 4) {

                                int boysGrildTotal = Constants.editcardList.get(i).getBoys()
                                        + Constants.editcardList.get(i).getGirls();
                                total1to4 = total1to4 + boysGrildTotal;

                                Log.d("totall14", String.valueOf(total1to4));
                            } else if (Constants.editcardList.get(i).getStandard() <=8){

                                int boysGrildTotal = Constants.editcardList.get(i).getBoys()
                                        + Constants.editcardList.get(i).getGirls();
                                total5to8 = total5to8 + boysGrildTotal;

                                Log.d("totall58", String.valueOf(total5to8));
                            }else{
                                int boysGrildTotal = Constants.editcardList.get(i).getBoys()
                                        + Constants.editcardList.get(i).getGirls();
                                total9to12 = total9to12 + boysGrildTotal;
                                Log.d("totall912", String.valueOf(total9to12));
                            }
                            Log.d("totall14", String.valueOf(total1to4));
                            Log.d("totall58", String.valueOf(total5to8));
                        }

                        cartlistAdapter = new Addstdadapter(Constants.editcardList, "isEdit");
                        cartlayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                        rcyestd.setLayoutManager(cartlayout);
                        rcyestd.setAdapter(cartlistAdapter);
                        rcyestd.setItemAnimator(null);
                        cartlistAdapter.setOnItemClickLister(this);
                        //cartlistAdapter.notifyItemRemoved();
                        cartlistAdapter.notifyDataSetChanged();
                    }
                    /// after retuning from Adstd As -> Edit Order //
//                    edFormNumber.setEnabled(false);
                    principal_Name = intent.getString("principalName");
                    school_Name = intent.getString("SchoolName");
                    school_id = intent.getString("SchoolId");
                    addtxtschool.setVisibility(View.INVISIBLE);
                    plusschool.setVisibility(View.INVISIBLE);
                    pluspattern.setVisibility(View.INVISIBLE);
                    addtxtpattern.setVisibility(View.INVISIBLE);
                     autoCompleteTextView.setText(school_Name);
                    //schooltxt.setText(school_Name);
                     ///   this means ur coming from STD as a Edit
                    if (parcebelCheck.hasExtra("saveorderRequest1") &&
                            intent.getString("isEditA", "n").equals("Added")) {
                        Log.d("YES _>>>", "ADDED");
                           String s =sharedPreferenceUtils.getString("formNumber","n");
//                        edFormNumber.setEnabled(false);
                        edFormNumber.setText(s);
                        edorderBy.setText(sharedPreferenceUtils.getString("orderby","n"));
                        textViewDate.setText(sharedPreferenceUtils.getString("addondate","n"));
                        placeorder.setText("Update Order");
                        Constants.cartlist = getIntent().getParcelableArrayListExtra("saveorderRequest1");

                        for (int i = 0; i < Constants.cartlist.size(); i++) {
                            std.add(Constants.cartlist.get(i).getStandard());
                            boys.add(Constants.cartlist.get(i).getBoys());
                            girls.add(Constants.cartlist.get(i).getGirls());
                            if (Constants.cartlist.get(i).getStandard() <= 4) {
                                int boysGrildTotal = Constants.cartlist.get(i).getBoys() + Constants.cartlist.get(i).getGirls();
                                total1to4 = total1to4 + boysGrildTotal;
                                Log.d("totall14", String.valueOf(total1to4));
                            } else if (Constants.cartlist.get(i).getStandard()<=8){
                                int boysGrildTotal = Constants.cartlist.get(i).getBoys() + Constants.cartlist.get(i).getGirls();
                                total5to8 = total5to8 + boysGrildTotal;
                                Log.d("totall58", String.valueOf(total5to8));
                            }else{
                                int boysGrildTotal = Constants.cartlist.get(i).getBoys() + Constants.cartlist.get(i).getGirls();
                                total9to12= total9to12 + boysGrildTotal;
                                Log.d("totall912", String.valueOf(total9to12));
                            }
                            Log.d("totall14", String.valueOf(total1to4));
                            Log.d("totall58", String.valueOf(total5to8));

                        }
                        Constants.editcardList = Constants.cartlist;
                        cartlistAdapter = new Addstdadapter(Constants.editcardList, "isEdit");
                        cartlayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                        rcyestd.setLayoutManager(cartlayout);
                        rcyestd.setAdapter(cartlistAdapter);
                        cartlistAdapter.setOnItemClickLister(this);
                        cartlistAdapter.notifyDataSetChanged();

                        edtrrate1.setText(String.valueOf((int) Double.parseDouble(sharedPreferenceUtils.getString("rate1", "0"))));
                        edtrrate2.setText(String.valueOf((int) Double.parseDouble(sharedPreferenceUtils.getString("rate2", "0"))));
                        edtrrate3.setText(String.valueOf((int) Double.parseDouble(sharedPreferenceUtils.getString("rate3", "0"))));
                        edtadvdeposit.setText(String.valueOf((int) Double.parseDouble(sharedPreferenceUtils.getString("deposite", "0"))));
                        String total_amountp = String.valueOf((int) Double.parseDouble(sharedPreferenceUtils.getString("totalam", "0")));
                        totalrate = (int) Double.parseDouble(total_amountp);
                        placeorder.setText("Update Order");
                        totalpayable.setText("Total Payable amount : " + total_amountp + " INR");  // bug setting the payable from shared pref
                        int amount = total1to4 +total5to8+total9to12;
                        totalpayable.setText("Total Payable amount : " + amount + " INR");
                        //   setupThePayableAfterReturnSTD();


                    }
                }
            }   ////   End Of Had SCHOOL NAME  //
            else {
                /// this used for the New Order  ///
                ///  NEW ORDER BLOCK   ///
                Constants.cartlist = getIntent().getParcelableArrayListExtra("saveorderRequest");
                for (int i = 0; i < Constants.cartlist.size(); i++) {
                    std.add(Constants.cartlist.get(i).getStandard());
                    boys.add(Constants.cartlist.get(i).getBoys());
                    girls.add(Constants.cartlist.get(i).getGirls());

                    if (Constants.cartlist.get(i).getStandard() <= 4) {

                        int boysGrildTotal = Constants.cartlist.get(i).getBoys() + Constants.cartlist.get(i).getGirls();
                        total1to4 = total1to4 + boysGrildTotal;

                        Log.d("totall14", String.valueOf(total1to4));
                    } else if (Constants.cartlist.get(i).getStandard() <=8){

                        int boysGrildTotal = Constants.cartlist.get(i).getBoys() + Constants.cartlist.get(i).getGirls();
                        total5to8 = total5to8 + boysGrildTotal;

                        Log.d("totall58", String.valueOf(total5to8));
                    }else{
                        int boysGrildTotal = Constants.cartlist.get(i).getBoys() + Constants.cartlist.get(i).getGirls();
                        total9to12= total9to12 + boysGrildTotal;
                        Log.d("totall58", String.valueOf(total9to12));
                    }
                    Log.d("totall14", String.valueOf(total1to4));
                    Log.d("totall58", String.valueOf(total5to8));
                    Log.d("totall912", String.valueOf(total9to12));
                }
                  /// isForm is passed in ADD STD if ure making a new order
                cartlistAdapter = new Addstdadapter(Constants.cartlist, "isFrom");
                cartlayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                rcyestd.setLayoutManager(cartlayout);
                rcyestd.setAdapter(cartlistAdapter);
                cartlistAdapter.notifyDataSetChanged();
                cartlistAdapter.setOnItemClickLister(this);
            }      ///    END OF NEW ORDER BLOCK
        } else {

        }
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (totalrate > Integer.parseInt(edtadvdeposit.getText().toString().trim())) {
                    Log.d("Count1 ->", String.valueOf(count));
                    if (placeorder.getText().equals("Update Order")) {
                        Log.d("Count2 ->", String.valueOf(count));
                        if (count <= 0) {
                            for (int i =0;i<Constants.editcardList.size(); i++){
                                Log.d("CardList After remove",Constants.editcardList.get(i).toString());
                            }
                            Toast.makeText(mBaseAppCompatActivity, "Please Add one Standard", Toast.LENGTH_SHORT).show();
                            Log.d("Editing Failed", "Adapter List Is Empty");
                        } else {
                            editorderApi();
                        }
                    } else {
                        placeordercall();
                    }
                     Log.d("totalrate ->", String.valueOf(totalrate));
                    Log.d("editDeposite", edtadvdeposit.getText().toString().trim());
                } else {
                    Toast.makeText(mBaseAppCompatActivity, "Advance deposit should be less than total payable amount.", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        rlschoolname.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                spinner = "schoolspinner";
//                //    setbottomview(spinner);
//                isChecked = true;
//             //   schoollspinner.performClick();
//            }
//        });
        rlpatternname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner = "patternspinner";
                isChecked1 = true;
                patternspinner.performClick();
                //     setbottomview(spinner);
            }
        });

        addstd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Quickorderforrm.this, AddStd.class);
                if (intent != null) {
                    String name = intent.getString("SchoolName", "Z");
                    Log.d("SchoolnmPasstoAddStd ->", name);
                    Log.d("Add Std Button ","Clicked");
                    if (intent != null && !name.equals("Z")) {
                        patternName = sharedPreferenceUtils.getString("patternName","n");
                        intent2.putExtra("order", name);
                        intent2.putExtra("PatternName", patternName);
                        Log.d("patternPasstoADSTD ->", patternName);
                        intent2.putParcelableArrayListExtra("Addsonstd", Constants.editcardList);
                    }
                }
                    String formnum =  edFormNumber.getText().toString();
                      myEdit.putString("form",formnum);
                    myEdit.commit();
                startActivity(intent2);
                finish();

            }
        });

        plusstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, AddStd.class));
                finish();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.cartlist.clear();
                /*Intent i = new Intent(Quickorderforrm.this, MainActivity.class);
                startActivity(i);
                finish();*/
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchool("");
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid("");
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedpattern("");
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedpatternid("");
                Constants.editcardList.clear();
                Constants.editcartlist.clear();
                onBackPressed();
            }
        });

        edtrrate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() ==0){
                    totalpayable.setText("Total Payable amount : ");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    if (count !=0){
                    rate1 = editable.toString();
                    rate1to4 = Integer.parseInt(rate1);
                    ratee1to4 = rate1to4 * total1to4;
                    totalrate = ratee1to4 + ratee5to8;
                    totalpayable.setText("Total Payable amount : " + totalrate + " INR");
                         }
                }
            }
        });
        edtrrate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() ==0){
                    totalpayable.setText("Total Payable amount : ");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    if (count !=0){
                    rate2 = editable.toString();
                    rate5to8 = Integer.parseInt(rate2.trim());
                    ratee5to8 = rate5to8 * total5to8;
                    totalrate = ratee1to4 + ratee5to8;
                    totalpayable.setText("Total Payable amount : " + totalrate + " INR");
                }
                }
            }
        });
        edtrrate3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==0){
                    totalpayable.setText("Total Payable amount ");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    if (count !=0){
                    rate3 = editable.toString();
                    rate9to12 = Integer.parseInt(rate3.trim());
                    ratee9to12 = rate9to12 * total9to12;
                    totalrate = ratee1to4 + ratee5to8 + ratee9to12;
                    totalpayable.setText("Total Payable amount : " + totalrate + " INR");
                    }
                }
            }
        });


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int index =0;
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid("");
                String item = (String) adapterView.getItemAtPosition(i);

                System.out.println("SELECTED CLICKED"+ adapterView.getItemAtPosition(i));
                System.out.println("SELECTED -> ITEM"+item);
                for (int x =0; x<mListData.size();x++){
                    System.out.println("ITEMS are ->"+mListData.get(x).getName());
                   if (mListData.get(x).getName().equals(item)){
                       index =x;
                   }
                }
                 int li = mListData.indexOf(item);
                System.out.println("Selected"+li);
                String coutryid = String.valueOf(mListData.get(index).getId());
                System.out.println("SELECTED - country id"+coutryid);
                String selecteddname = mListData.get(index).getName();
                System.out.println("SELECTED -- selected name"+selecteddname);
                selectschoolid = coutryid;
                System.out.println("SELECTED SCHOOL ID"+selectschoolid);
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchool(String.valueOf(selecteddname));
                UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid(String.valueOf(coutryid));
            }
        });

//        schoollspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String coutryid = String.valueOf(mListData.get(i).getId());
//                String selecteddname = mListData.get(i).getName();
//                if (isChecked) {
//                    schooltxt.setText(selecteddname);
//                    selectschoolid = coutryid;
//                    Log.d("selecteddname", selecteddname);
//                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchool(String.valueOf(selecteddname));
//                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid(String.valueOf(coutryid));
//                } else {
//                    selectedsschoolname = UserPreference.getInstance(mBaseAppCompatActivity).getselectedSchool();
//                    selectschoolid = UserPreference.getInstance(mBaseAppCompatActivity).getselectedSchoolid();
//                    schooltxt.setText(selectedsschoolname);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        patternspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String coutryid = String.valueOf(patterndata.get(i).getId());
                String selecteddname = patterndata.get(i).getName();
                if (isChecked1) {
                    patterntxt.setText(selecteddname);
                    selectpatternid = coutryid;
                    Log.d("selecteddname", selecteddname);
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedpattern(String.valueOf(selecteddname));
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedpatternid(String.valueOf(coutryid));
                } else {
                    System.out.println("Pattern 00 -?");
                    selectedpatternname = UserPreference.getInstance(mBaseAppCompatActivity).getselectedpattern();
                    selectpatternid = UserPreference.getInstance(mBaseAppCompatActivity).getselectedpatternid();
                    patterntxt.setText(selectedpatternname);
                    if (selectedpatternname.equals("")){
                    if (!sharedPreferenceUtils.getString("patternName","n").equals("n")){
                        patterntxt.setText(sharedPreferenceUtils.getString("patternName","n"));
                    }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        plusschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, Addschool.class));
            }
        });
        addtxtschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, Addschool.class));
            }
        });
        addtxtpattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, Addpattern.class));
            }
        });
        pluspattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quickorderforrm.this, Addpattern.class));
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_quickorderforrm;
    }

    private void editorderApi() {
        Log.d("Editor Api called", "Called");
        LinkedHashMap<String, RequestBody> update = new LinkedHashMap<String, RequestBody>();

        deposite = edtadvdeposit.getText().toString();
        String orderidvm = sharedPreferenceUtils.getString("orderid", "n");
        String schoolIdvm = sharedPreferenceUtils.getString("schoolid", "n");
        String patterIdvm = sharedPreferenceUtils.getString("patternid", "n");
        String loginidvm = UserPreference.getInstance(this).getLoginId();
        formNumber = edFormNumber.getText().toString();
        total_amount = String.valueOf(totalrate);
        deposite = edtadvdeposit.getText().toString();
         String rate_1    = edtrrate1.getText().toString();
         String rate_2  = edtrrate2.getText().toString();
         String rate_3 =  edtrrate3.getText().toString();
         edtrrate3.getText().toString();
         String totalamt = totalpayable.getText().toString();

        passedDate = sharedPreferenceUtils.getString("addondate","n");
        orderBy = edorderBy.getText().toString();

        update.put("order_id", RequestBody.create(MediaType.parse("multipart/form-data"), orderidvm));
        update.put("login_id", RequestBody.create(MediaType.parse("multipart/form-data"), loginidvm));
        update.put("school_id", RequestBody.create(MediaType.parse("multipart/form-data"), schoolIdvm));
        update.put("pattern_id", RequestBody.create(MediaType.parse("multipart/form-data"), patterIdvm));
        update.put("rate1", RequestBody.create(MediaType.parse("multipart/form-data"), rate_1));
        update.put("rate2", RequestBody.create(MediaType.parse("multipart/form-data"), rate_2));
        update.put("rate3",RequestBody.create(MediaType.parse("multipart/form-data"),rate_3));
        totalamt = totalamt.replaceAll("[^\\d.]", "");
        update.put("total_amount", RequestBody.create(MediaType.parse("multipart/form-data"),totalamt));
        update.put("deposite", RequestBody.create(MediaType.parse("multipart/form-data"), deposite));
        update.put("form_number",RequestBody.create(MediaType.parse("multipart/form-data"),formNumber));
        update.put("add_date_on",RequestBody.create(MediaType.parse("multipart/form-data"),passedDate));
        update.put("order_by",RequestBody.create(MediaType.parse("multipart/form-data"),orderBy));
        Log.d("passedate# ->",passedDate);
        Log.d("passedorderBY#->",orderBy);
        Log.d("passedorderID# ->",orderidvm);
        Log.d("passedloginId#->",loginidvm);
        Log.d("passedschoolid# ->",schoolIdvm);
        Log.d("passedpattern#->",patterIdvm);
        Log.d("passedrate1# ->",rate_1);
        Log.d("passedrate2#->",rate_2);
        Log.d("passedrate3# ->",rate_3);
        Log.d("passedtotalamt#->",totalamt);
        Log.d("passeddepopsite# ->",deposite);
        Log.d("passedLogionID#->",loginidvm);

        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<EditOrderResponse> updateOrder = apiInterface.editOrderForUpdate(update, std, boys, girls,stdId);
        updateOrder.enqueue(new Callback<EditOrderResponse>() {
            @Override
            public void onResponse(Call<EditOrderResponse> call, Response<EditOrderResponse> response) {
                 Log.d("Errror Code",""+response.code());
                if (response.isSuccessful()) {
                    Log.d("Update SuccessFull", "UpdateOrder");
                    sharedPreferenceUtils.edit().clear().apply();
                    editOrderViewModel.clearViewModelData();
                    Constants.editcardList.clear();
                    Constants.cartlist.clear();
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchool(String.valueOf(" "));
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedpattern(String.valueOf(" "));
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedpatternid(String.valueOf(" "));
                    UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid(String.valueOf(" "));
                    myEdit.clear();
                    myEdit.commit();
                    myEdit.apply();
                    startActivity(new Intent(Quickorderforrm.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<EditOrderResponse> call, Throwable t) {
                Log.d("Update Failed", "Failed");
                Log.d("Update Failed", t.getMessage().toString());
                Log.d("Update Failed",t.getCause().toString());
                myEdit.clear();
                myEdit.commit();
                myEdit.apply();
            }
        });
    }

    private void placeordercall() {
        showHideProgressDialog(true);

        Log.d("Place order", "Called");
        login_id = UserPreference.getInstance(this).getLoginId();
        school_id = UserPreference.getInstance(mBaseAppCompatActivity).getselectedSchoolid();
        pattern_id = selectpatternid;
        total_amount = String.valueOf(totalrate);
        deposite = edtadvdeposit.getText().toString();
        formNumber = edFormNumber.getText().toString();
        passedDate = sharedPreferenceUtils.getString("date","n");
        orderBy = edorderBy.getText().toString();
        for (int i =0; i<std.size();i++)
        Log.d("PLACED -> ","std"+std.get(i));

        System.out.println(login_id);
        System.out.println(school_id);
        System.out.println(pattern_id);
        System.out.println(total_amount);
        System.out.println(formNumber);
        System.out.println(passedDate);
        System.out.println(orderBy);

        LinkedHashMap<String, RequestBody> addPostRequest = new LinkedHashMap<String, RequestBody>();
        addPostRequest.put("login_id", RequestBody.create(MediaType.parse("multipart/form-data"), login_id));
        addPostRequest.put("school_id", RequestBody.create(MediaType.parse("multipart/form-data"), school_id));
        addPostRequest.put("pattern_id", RequestBody.create(MediaType.parse("multipart/form-data"), pattern_id));
        addPostRequest.put("rate1", RequestBody.create(MediaType.parse("multipart/form-data"), rate1));
        addPostRequest.put("rate2", RequestBody.create(MediaType.parse("multipart/form-data"), rate2));
        addPostRequest.put("rate3",RequestBody.create(MediaType.parse("multipart/form-data"), rate3));
        addPostRequest.put("total_amount", RequestBody.create(MediaType.parse("multipart/form-data"), total_amount));
        addPostRequest.put("deposite", RequestBody.create(MediaType.parse("multipart/form-data"), deposite));
        addPostRequest.put("form_number",RequestBody.create(MediaType.parse("multipart/form-data"),formNumber));
        addPostRequest.put("add_date_on",RequestBody.create(MediaType.parse("multipart/form-data"),passedDate));
        addPostRequest.put("order_by",RequestBody.create(MediaType.parse("multipart/form-data"),orderBy));
        System.out.println("Passed"+ passedDate);
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Schoollistmodel> addorder = apiInterface.addorder(addPostRequest, std, boys, girls);
        addorder.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                System.out.println("RESPONSE ->"+response.raw());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("loggg", "loggg");
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);
                            Toast.makeText(Quickorderforrm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            //patternllist();
                            Constants.cartlist.clear();
                            Constants.editcardList.clear();
                            myEdit.clear();
                            UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchool(String.valueOf(" "));
                            UserPreference.getInstance(mBaseAppCompatActivity).setselectedpattern(String.valueOf(" "));
                            UserPreference.getInstance(mBaseAppCompatActivity).setselectedpatternid(String.valueOf(" "));
                            UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid(String.valueOf(" "));

                            startActivity(new Intent(Quickorderforrm.this, MainActivity.class));
                            finish();
                        } else {
                            showHideProgressDialog(false);
                            Toast.makeText(Quickorderforrm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showHideProgressDialog(false);
                        Toast.makeText(Quickorderforrm.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Quickorderforrm.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Quickorderforrm.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onItemSelected2(int alltotal, int stdd1tto4, int std5to8) {
        txttotalstdent.setText("TOTAL STUDENTS " + alltotal);
    }


     // this function is showing that u had a empty Standard Adapter List   //
    @Override
    public void checkListisEmpty(int value) {

        Log.d("Value", String.valueOf(value));
        count = value;
         if (count == 0) {
             Toast.makeText(mBaseAppCompatActivity, "At Least On Standard is Required", Toast.LENGTH_SHORT).show();
             Constants.cartlist.clear();
             Constants.editcardList.clear();
           }
    }

    @Override
    public void updatedWhenDelete(String standard, String total_boys_girls) {
            try {
                std.remove(Integer.valueOf(standard));
            //    stdId.remove(indexOf);
            }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                Toast.makeText(mBaseAppCompatActivity, "Id Is Invalid", Toast.LENGTH_SHORT).show();
            }
          int parseInt = Integer.parseInt(standard);
            String boys_girls_total = total_boys_girls.replaceAll("[^0-9.]", "");
              String totalPayableAmt = totalpayable.getText().toString();
          int parseInt2 = Integer.parseInt(boys_girls_total);
           if (parseInt>0 && parseInt<=4){
              // rate 1 updated here
              int rate1 = Integer.parseInt(edtrrate1.getText().toString());
              int cancel_amt = parseInt2 * rate1;
               int total_payAMT  =  Integer.parseInt(totalPayableAmt.replaceAll("[^0-9.]", ""));
              totalpayable.setText("Total Payable amount : "+String.valueOf(total_payAMT - cancel_amt+ " INR"));

          }else if(parseInt>4 && parseInt<=8){
              // rate 2 updated here
              int rate2 = Integer.parseInt(edtrrate2.getText().toString());
              int cancel_amt = parseInt2 * rate2;
               int total_payAMT  =  Integer.parseInt(totalPayableAmt.replaceAll("[^0-9.]", ""));
               totalpayable.setText("Total Payable amount : "+String.valueOf(total_payAMT - cancel_amt+ " INR"));
          }else if (parseInt>8 && parseInt<=12){
              // rate 3 updated here
              int rate3 = Integer.parseInt(edtrrate3.getText().toString());
              int cancel_amt = parseInt2 * rate3;
               int total_payAMT  =  Integer.parseInt(totalPayableAmt.replaceAll("[^0-9.]", ""));
               totalpayable.setText("Total Payable amount : "+String.valueOf(total_payAMT - cancel_amt+ " INR"));
           }
        Log.d("Updated ->","When Deleted STANDARD");
        Log.d("Updated ->","When Deleted STANDARD "+parseInt);
        Log.d("Updated ->","When Deleted STANDARD"+boys_girls_total);

    }

    private void setPayAmtAfterAddStd(){
        Log.d("TOTAL1to4", String.valueOf(total1to4));
        Log.d("TOTAL5to8", String.valueOf(total5to8));
        Log.d("TOTAL9to12", String.valueOf(total9to12));

        if (total1to4 != 0){

        }else if (total5to8 != 0){

        } else if (total9to12 != 0){

        }
    }

    private void schoollist() {
        //  loginid=UserPreference.getInstance(getContext()).getLoginId();
        // String  loginid=UserPreference.getInstance(Quickorderforrm.this).getLoginId();;
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(Quickorderforrm.this).create(APIInterface.class);
        Call<ResponseSchoolList> schoollist = apiInterface.schoollist(login_id, "100", "0", search_value);
        schoollist.enqueue(new Callback<ResponseSchoolList>() {
            @Override
            public void onResponse(Call<ResponseSchoolList> call, Response<ResponseSchoolList> response) {
                //    hideSwipeRefreshView();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().isStatus()) {
                            showHideProgressDialog(false);
                            if (response.body().getData() != null && response.body().getData().size() != 0) {
                                //list
                                if (mListData != null) {
                                    mListData.clear();
                                }
                                mListData.addAll(response.body().getData());
                                ///  handle quick order  as intent is null //
                                if (intent != null && !intent.getString("SchoolName", "Q").equals("Q") || parcebelCheck.hasExtra("EditData")) {
                                    if (intent != null && intent.getString("isEdit").equals("order")) {
                                        //// removing the adapter  ///
                                        // value is from orderAdapter
                                    }
                                } else {
                                    // adding the adapter to the autocomplete text view //
                                   ArrayList<String> arrayList= new ArrayList<String>();
                                    for (int i=0; i<mListData.size(); i++){
                                        arrayList.add(i,mListData.get(i).getName());
                                    }
                                        ArrayAdapter arrayAdapter = new ArrayAdapter(mBaseAppCompatActivity,android.R.layout.simple_list_item_1,arrayList);
                                        autoCompleteTextView.setAdapter(arrayAdapter);
                                }
                            }
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
                        Toast.makeText(Quickorderforrm.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseSchoolList> call, Throwable t) {
                showHideProgressDialog(false);
            }
        });
    }

    private void patternlist() {
        //  String  loginid=UserPreference.getInstance(Quickorderforrm.this).getLoginId();;
        showHideProgressDialog(true);
        APIInterface apiInterface = APIClient.getClient(Quickorderforrm.this).create(APIInterface.class);
        Call<Schoollistmodel> schoollist = apiInterface.patternlist(login_id, "100", "0");
        schoollist.enqueue(new Callback<Schoollistmodel>() {
            @Override
            public void onResponse(Call<Schoollistmodel> call, Response<Schoollistmodel> response) {
                //    hideSwipeRefreshView();
                String df = "";
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equals("true")) {
                            showHideProgressDialog(false);
                            if (response.body().getData() != null && response.body().getData().size() != 0) {
                                //list
                                if (patterndata != null) {
                                    patterndata.clear();
                                }
                                patterndata.addAll(response.body().getData());
                                patternadapter customAdapter = new patternadapter(Quickorderforrm.this, R.layout.spinnerlayout1, patterndata);
                                if (patternspinner != null) {
                                    if (intent != null && !intent.getString("SchoolName", "Q").equals("Q")
                                            || parcebelCheck.hasExtra("EditData")) {
                                        if (intent != null && intent.getString("isEdit").equals("order")) {
                                            df = intent.getString("PatternName", "n");
                                             patternspinner.setAdapter(customAdapter);
                                            patterntxt.setText(df);
                                            Log.d("Pattern1212->>>>", intent.getString("PatternName"));
                                        }
                                    } else {
                                        System.out.println("Pattern -> ELSE ");
                                        patternspinner.setAdapter(customAdapter);
                                        patterntxt.setText(df);

                                    }

                                }
                            }
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
                        Toast.makeText(Quickorderforrm.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
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
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constants.cartlist.clear();
        UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchool("");
        UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid("");
        UserPreference.getInstance(mBaseAppCompatActivity).setselectedpattern("");
        UserPreference.getInstance(mBaseAppCompatActivity).setselectedpatternid("");
        Constants.editcardList.clear();
        Constants.editcartlist.clear();
    }

   void handleSelectDate(){
        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYear = c.get(Calendar.YEAR);
                               mMonth = c.get(Calendar.MONTH);
                                mDay = c.get(Calendar.DAY_OF_MONTH);
                                DatePickerDialog dpd = new DatePickerDialog(Quickorderforrm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                   public void onDateSet(DatePicker view, int year,
                                         int monthOfYear, int dayOfMonth) {
                                               textViewDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                               String dateDis = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                               myEdit.putString("displayDate",dateDis);
                                                c.set(year, monthOfYear, dayOfMonth);
                                                passedDate = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(dayOfMonth);
                                                myEdit.putString("date",passedDate.toString());
                                                myEdit.commit();
                        System.out.println("Passed Date "+passedDate);
                                            }
                }, mYear, mMonth, mDay);
                               //dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                                       dpd.show();
            }
        });
         }

         void  updateTheSchoolListOutsideTheResponseThread(List<DataItem> dataItemList){
             int index = 0;
             String schoolName = sharedPreferenceUtils.getString("school","");
             for (int x =0; x<dataItemList.size();x++){
                 System.out.println("ITEMS are 1->"+mListData.get(x).getName());
                 if (mListData.get(x).getName().equals(schoolName)){
                     index =x;
                     System.out.println("school found at ->"+x);
                     autoCompleteTextView.setText(schoolName);
                     UserPreference.getInstance(mBaseAppCompatActivity).setselectedSchoolid(String.valueOf(index));
                     break;
                 }
             }



         }




    private void setbottomview(String spinner) {
        UserPreference.getInstance(this).setschoolspinner(spinner);
        Log.d("logg1", "bt");

        final CommonListBottomSheet loBottomNavigationView = CommonListBottomSheet.newInstance();
        loBottomNavigationView.addListener(Quickorderforrm.this, new CommonListBottomSheet.DailogListener() {
            @Override
            public void onItemClick(String mPosition, String actionType, String s) {
                Log.d("loggg", mPosition);
                switch (actionType) {
                    case "schoolspinner":
                        edtschoolname.setText(s);
                        selectschoolid = mPosition;
                        break;
                    case "patternspinner":
                        edtpatternname.setText(s);
                        selectpatternid = mPosition;
                        break;
                }
            }
        });
        loBottomNavigationView.show(getSupportFragmentManager(), "");
    }
}
