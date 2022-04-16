package com.uniformorder.uniformorderr.activities;

import com.uniformorder.uniformorderr.model.Editorder;
import com.uniformorder.uniformorderr.model.SaveorderRequestdetails;
import com.uniformorder.uniformorderr.model.Standard;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String CHANNEL_ID = "my_channel_01";
    public static final String CHANNEL_NAME = "Simplified Coding Notification";
    public static final String CHANNEL_DESCRIPTION = "www.simplifiedcoding.net";

    /* Font List*/
    public static final int requestSinglechat = 0;
    public static String val_firstname = "Please Enter FirstName";
    public static String val_Lastname = "Please Enter LastName";
    public static String vl_fullname = "Please enter Full Name.";
    public static String vl_OTp = "Please enter OTP.";
    public static String vl_username = "Please enter User First name.";
    public static String vl_usernameLast = "Please enter User Last name.";
    public static String vl_cellno = "Please enter mobile no.";
    public static String vl_otp = "Please enter OTP.";
    public static String val_phnVale = "Please enter 10 Degit Mobile Number";
    public static String valide_number = "Mobile No. Contain only 15 Digit";
    public static String vl_phonecode = "Please select country code.";
    public static String vl_must_cellno = "Please enter valid mobile no.";
    public static String vl_confirmpassword = "Please enter confirm password.";
    public static String vl_min_confirmpassword = "Password does not match.";
    public static String vl_passwordnomatch = "Password does not match.";
    public static String vl_sigup_address = "Please enter address.";
    public static String val_state = "Please select state.";
    public static String val_city = "Please select city.";
    public static String val_tremsandcondistion = "Please select terms & Condition.";
    public static String val_pincode = "Please enter pincode.";

    public static String not_match = "Password and Confirm not match";
    public static String select_userType = "Please Select User Type";
    public static String vl_email = "Please enter email address.";
    public static String vl_invalid_email = "Please enter valid email address.";
    public static String exit_email = "Email are already exist .";
    public static String vl_password = "Please enter password.";
    public static String vl_lenth_pasw = "Please enter Password atleast 8 charater";
    public static String vl_C_password = "Please enter Confirm Password.";
    public static String vl_new_password = "Please enter New Password.";
    public static String vl_GroupName = "Please Enter Group Name";
    public static String card_expireDate = " Please Enter Card Expired date";
    public static String CardNumber = "Please Enter Card Number ";

    public static String HelpTitle = "Please Enter   Title ";
    public static String HelpMessage = "Please Enter your Message ";

    public static String Message = "Please Enter Message ";

    public static String Opening_hours= "Enter Opening Hours";
    public static String Closing_hours= "Enter Closing Hours";

    public static String val_Phonename = "Please Enter Contact Name";
    public static String val_Phonenumber = "Please Enter Contact Number";
    public static String val_ordermsg = "Please Enter Your Order";

    public static String CompanyName = "Please Enter Company Name ";
    public static String CompanyPopularName = "Please Enter Company Popular Name ";
    public static String FLAT_num = "Please Enter  Flat No.";
    public static String Landmark = "Please Enter  Landmark";
    public static String Since = "Please Enter  Since year ";
    public static String Street_No = "Please Enter  Street Number ";
    public static String Address = "Please Enter  Address Type ";
    public static String AddressTitle = "Please Enter Home or Office or Gym ";
    public static String ZipCode = "Please Enter ZIP Code";
    public static String website = "Please Enter WebSite";
    public static String BusinessLocation = "Please Enter Business Location  ";


    public static String val_date= "Please Select Date ";
    public static String val_time = "Please Select Time ";
    public static String val_msg = "Enter your Message";

    public static String radioBtnGender = " Please Select Gender";
    public  static String radioBtnMeritalStatus = "Plase Select Merital Status";
    //Edit Profile

    public static String Cmpy_name = "Company";
    public static String Cmpy_pop_name = "CompPopName";
    public static String Fein_no = "FEIN_no";
    public static String address_prof = "address";
    public static String Streetnum = "StreetNum";
    public static String p_f_name = "p_f_name";
    public static String p_l_name = "p_l_name";
    public static String p_email = "p_email";
    public static String p_phone = "p_phone";
    public static String a_f_name = "a_f_name";
    public static String a_l_name = "a_l_name";
    public static String a_email = "a_email";
    public static String a_phone = "a_phone";
    public static String websiteEdt = "website";
    public static String CountryName = "CountryName";
    public static String CityName = "Enter CityName";
    public static String StateName = "SateName";
    public static int CityId = 0;
    public static int StateId = 0;
    public static String card_num = "card_num";
    public static String card_holder_name = "Card_holder_name";
    public static String cvv = "cvv";
    public static String expiredate="expDate";
    public static int busnetype= 0;
    public static String businesLoc="businesss_location";
    public static String year="year";
    public static String des_cmp="des_cmp";
    public static String Zipcode="ZipCode";

    public static String Terms_Service = "Please Accept Terms & Services ";
   public static ArrayList<SaveorderRequestdetails> cartlist = new ArrayList<>();
   public static ArrayList<Editorder> editcartlist = new ArrayList<>();




    public interface USER_TYPE {
        String USER_FREE = "Free User";
        String USER_FULL = "Full User";
        String USER_PRO = "Pro User";
    }

    public interface USER_PREFERENCES {
        String USER_PREFERENCE = "user_preference";
        String IS_USER_LOGIN = "is_user_login";
        String Is_Visible = "is_user_visible";
        String ActiveUser = "ActiveUser";
        String ispaid = "ispaid";
        String isverify = "isverify";


        String CONSTANT_FCM_TOKEN = "fcm_token";
        String USER_DETAILS = "user_details";
        String LOGINUSER_DETAILS = "user_details";
        String TOPOFFERSDETAILS = "Topoffers_details";
        String LATITUDE = "latitude";
        String Visible = "visible";
        String gender = "gender";
        String Token = "Token";

        String LONGITUDE = "longitude";
        String RATE_APP = "rateapp";
        String SHARE_APP = "shareapp";
        String AUTH = "auth";
        String LOGIN_ID = "loginid";
        String Type_USer = "User_type";
        String Type_layout = "Layout_type";
        String PhoneNum = "PhoneNum";
        String UserSignUpName = "UserSignUpName";
        String QB_ID_SENDER = "send_qb_id";
        String GMAIL = "Gmail Address";
        String EMAIL = "Email Address";
        String ACTION_EVENT_HANDEL = "action_handel";
        String ACTION_FRAGMENT_HANDEL = "action_frag_handel";

        String User_Type = "User_Type";

        String REG_COUNTRY_ID = "regCountry";
        String COUNTRYID = "countryID";
        String ITEMCATID = "Itemcat ID";
        String PROFILEID = "PROFILEID";

        String EdtCmpName = "EdtCmpName";
        String EdtBSPopularName = "EdtBSPopularName";
        String EdtFeinNo = "EdtFeinNo";
        String EdtBSDesc = "EdtBSDesc";
        String EdtBSSince = "EdtBSSince";
        String EdtStreetNo = "EdtStreetNo";
        String EdtStreetName = "EdtStreetName";
        String EdtZipCodeNum = "EdtZipCodeNum";
        String EdtWebsiteName = "EdtWebsiteName";
        String EdtCityName = "EdtCityName";
        String EdtStateName = "EdtStateName";
        String EdtCountryName = "EdtCountryName";
        String EdtTypeOfBusinName = "EdtTypeOfBusinName";
        String CardType = "CardType";
        String EdtBusinessLocation = "BusinessLocation";
        String CheckStatus = "CheckStatus";


        String schoolspinner = "schoolspinner";
        String patternspinner = "patternspinner";
        String  std1to4="std1to4";
        String  std5to8="std5to8";
        String  completed="completed";
        String  pending="pending";
        String Url = "Url";
        String EdtSignUpEmailAddress = "EdtSignUpEmailAddress";
        String EdtMobileNumber = "EdtMobileNumber";
        String EdtAlFirstName = "EdtAlFirstName";
        String EdtAlLastName = "EdtAlLastName";
        String EdtAlSignUpEmailAddress = "EdtAlSignUpEmailAddress";
        String setEdtAlMobileNumber = "setEdtAlMobileNumber";
        String EdtCardNumber = "EdtCardNumber";
        String EdtCardExpDate = "EdtCardExpDate";
        String EdtCardCvv = "EdtCardCvv";
        String EdtCardName = "EdtCardName";
        String Formbirthdate = "Formbirthdate";
        String Formbirthtime = "Formbirthtime";
        String Formbirthplace = "Formbirthplace";
        String Formbirthtimevikramm = "Formbirthtimevikramm";
        String Formmithi = "Formmithi";
        String Formmahino = "Formmahino";
        String Formpaksh = "Formpaksh";
        String Formdiksha = "Formdiksha";
        String Formdiksha1 = "Formdiksha";
        String Formdiksshaguru = "Formdiksshaguru";
        String Formmatritalstatus = "Formmatritalstatus";
        String Formheight = "Formheight";
        String Formweight = "Formweight";
        String Formgotra = "Formgotra";
        String FormCommunity = "FormCommunity";
        String Formssubcommunity = "Formssubcommunity";
        String Formvarna = "Formvarna";
        String Formmool = "Formmool";
        String Formmanglik = "Formmanglik";
        String Formfathername = "Formfathername";
        String Formmothername = "Formmothername";
        String Formbbrothername = "Formbbrothername";
        String Formbbrotherinlaw = "Formbbrotherinlaw";
        String Formssistername = "Formssistername";
        String Formsisinlaw = "Formsisinlaw";
        String Formmaternalname = "Formmaternalname";
        String Formvilllage = "Formvilllage";
        String Formofficceno = "Formofficceno";
        String Formsecondaryno = "Formsecondaryno";
        String Formdegree = "Formdegree";
        String Formoccupation = "Formoccupation";
        String Formannualincome = "Formannualincome";
        String Formcurrentlocation = "Formcurrentlocation";
        String Formfathhersoccupation = "Formfathhersoccupation";
        String Formfamillyincome = "Formfamillyincome";
        String Formonion = "Formonion";
        String Formashauch = "Formashauch";
        String Formtrevelabbroad = "Formtrevelabbroad";
        String Formsettleabroad = "Formsettleabroad";
        String Formsspectacles = "Formsspectacles";
        String Formbadhabit = "Formbadhabit";
        String Formbadhabitpast = "Formbadhabitpast";
        String Formbadhabbitfamily = "Formbadhabbitfamily";
        String Formcountry = "Formcountry";
        String Formstate = "Formstate";
        String Formcity = "Formcity";
        String Formggender = "Formggender";
        String Formedu = "Formedu";
        String FormUploadpic = "FormUploadpic";
        String Formuploadfile = "Formuploadfile";
        String Formhobby = "Formuphobby";
        String Formdisablity = "Formdisablity";
        String Userverify24hr = "Userverify24hr";


        //For Filter
        String Filtercountry = "Filtercountry";
        String Filterstate = "Filterstate";
        String Filtercity = "Filtercity";
        String Filtergender = "Filtergender";
        String Filteredu = "Filteredu";
        String Filtermarital_status = "Filtermarital_status";
        String Filterage = "Filterage";
        String Filterincome = "Filterincome";
        String Filtername = "Filtername";
        String Filecount = "Filecount";
        String audiouri = "audiouri";


        //filter selection
        String isage20 = "isage20";
        String isage26 = "isage26";
        String isage31 = "isage31";
        String isage36 = "isage36";
        String isage41 = "isage41";
        String isage46 = "isage46";
        String isage51 = "isage51";
        String isage56 = "isage56";

        String isinncome50max = "isinncome50max";
        String isinncome50k = "isinncome50k";
        String isinncome1lac = "isinncome1lac";
        String isinncome2lac = "isinncome2lac";
        String isinncome5lac = "isinncome5lac";
        String isinncome10lac = "isinncome10lac";
        String isinncome20lac = "isinncome20lac";

        String isnevermarried = "isnevermarried";
        String isdivorced = "isdivorced";
        String iswidowed = "iswidowed";
        String issingleparents = "issingleparents";

        String countryfilter="India";
        String statefilter="statefilter";
        String cityfilter="cityfilter";
        String edufilter="edufilter";

        String planid = "planid";
        String planamount = "planamount";
        String planname = "planname";
        String txnId = "txnId";

        String selectedSchool = "selectedSchool";
        String selectedpattern = "selectedpattern";
        String selectedSchoolid = "selectedSchoolid";
        String selectedpatternid = "selectedpatternid";


        String profileuri="profileuri";

        String expiredate = "expiredate";
        String purchasedate = "purchasedate";

        String USER_FIRST_REGISTERTION = "USER_FIRST_REGISTERTION";

        String  NOLITING="NOLITING";

    }

    public static final String no_data_found = "Welcome to FlippBidd! Add Your First ";
    public static final String no_data_found_append = " Here by Pressing the '+' Button on Top.";

    public static final String[] cvFileExtensions = new String[]{"pdf", "docx"};

    // Prefrance constant key
    public static final String Pref_Token = "oldtoken";
    public static final String Pref_TokenCreateTime = "tokencreatetime";
    public static final String Pref_TokenExpiredTime = "tokenexpiredtime";
    public static final String Pref_UserQuickBloxId = "userquickbloxid";
    public static final String Pref_UserFullName = "userfullname";
    public static final String Pref_UserEmailAddress = "useremailaddress";
    public static final String Pref_UserPassword = "userpassword";
    public static final String Pref_GCMToken = "gcmtoken";
    public static final String Pref_IsRegistedToQuickbloxforPush = "isregistedtoquickbloxpush";

}
