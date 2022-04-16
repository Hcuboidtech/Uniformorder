package com.uniformorder.uniformorderr.activities;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.uniformorder.uniformorderr.model.Registerdetails;

import static com.uniformorder.uniformorderr.activities.Constants.USER_PREFERENCES.Formbirthplace;
import static com.uniformorder.uniformorderr.activities.Constants.USER_PREFERENCES.Formbirthtime;

public class UserPreference {

    private Context mContext;
    private static final String TAG = UserPreference.class.getSimpleName();

    private SharedPreferenceUtils helperPreference;

    private UserPreference(Context context) {
        mContext = context;
        helperPreference = SharedPreferenceUtils.getInstance(context);
    }

    public static UserPreference getInstance(Context context) {
        return new UserPreference(context);
    }

    public void save(String userProfileJson) {
        helperPreference.setValue(Constants.USER_PREFERENCES.USER_PREFERENCE, userProfileJson);
    }

    public boolean isUserLogin() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.IS_USER_LOGIN, false);
    }

    public void setUserLogin(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.IS_USER_LOGIN, isUserLogin);
    }

    public boolean isVisible() {
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.Is_Visible, false);
    }

    public void setVisible(boolean isVisible) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Is_Visible, isVisible);
    }


    public String getUserFirstRegistration() {
        return (helperPreference.getStringValue(Constants.USER_PREFERENCES.USER_FIRST_REGISTERTION, "0"));
    }

    public void setUserFirstRegistration(String isUserFirstRegistration) {
        helperPreference.setValue(Constants.USER_PREFERENCES.USER_FIRST_REGISTERTION, isUserFirstRegistration);
    }


    public String getNOLITING() {
        return (helperPreference.getStringValue(Constants.USER_PREFERENCES.NOLITING, "0"));
    }

    public void setNOLITING(String isUserFirstRegistration) {
        helperPreference.setValue(Constants.USER_PREFERENCES.NOLITING, isUserFirstRegistration);
    }


    public Boolean getvisble() {
        return Boolean.valueOf(helperPreference.getStringValue(Constants.USER_PREFERENCES.Visible, "0"));
    }


    public String getLatitude() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.LATITUDE, "0");
    }

    public void setLatitude(double latitude) {
        helperPreference.setValue(Constants.USER_PREFERENCES.LATITUDE, String.valueOf(latitude));
    }

    public String getAuth() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.AUTH, "0");
    }

    public void setAuth(String auth) {
        helperPreference.setValue(Constants.USER_PREFERENCES.AUTH, String.valueOf(auth));
    }

    public String getLoginId() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.LOGIN_ID, "0");
    }

    public void setLoginId(String auth) {
        helperPreference.setValue(Constants.USER_PREFERENCES.LOGIN_ID, String.valueOf(auth));
    }

    public String getToken() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Token, "0");
    }

    public void setToken(String token) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Token, String.valueOf(token));
    }

    public Integer getstd5to8() {
        return helperPreference.getIntValue(Constants.USER_PREFERENCES.std5to8, 0);
    }

    public void setstd1to4(int token) {
        helperPreference.setValue(String.valueOf(Constants.USER_PREFERENCES.std1to4), token);
    }

    public Integer getstd1to4() {
        return helperPreference.getIntValue(Constants.USER_PREFERENCES.std1to4, 0);
    }

    public void setstd5to8(int token) {
        helperPreference.setValue(String.valueOf(Constants.USER_PREFERENCES.std5to8), token);
    }

    public String getprofileuri() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.profileuri, "0");
    }

    public void setprofileuri(String token) {
        helperPreference.setValue(Constants.USER_PREFERENCES.profileuri, String.valueOf(token));
    }

    public String getselectedpattern() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.selectedpattern, "0");
    }

    public void setselectedpattern(String token) {
        helperPreference.setValue(Constants.USER_PREFERENCES.selectedpattern, String.valueOf(token));
    }

    public String getselectedSchool() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.selectedSchool, "");
    }

    public void setselectedSchool(String token) {
        helperPreference.setValue(Constants.USER_PREFERENCES.selectedSchool, String.valueOf(token));
    }

    public String getselectedSchoolid() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.selectedSchoolid, "0");
    }

    public void setselectedSchoolid(String token) {
        helperPreference.setValue(Constants.USER_PREFERENCES.selectedSchoolid, String.valueOf(token));
    }

    public String getselectedpatternid() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.selectedpatternid, "");
    }

    public void setselectedpatternid(String token) {
        helperPreference.setValue(Constants.USER_PREFERENCES.selectedpatternid, String.valueOf(token));
    }

    public String getpayment_pending() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.pending, "0");
    }

    public void setpayment_pending(String token) {
        helperPreference.setValue(Constants.USER_PREFERENCES.pending, String.valueOf(token));
    }

    public String getcompleted() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.completed, "0");
    }

    public void setcompleted(String token) {
        helperPreference.setValue(Constants.USER_PREFERENCES.completed, String.valueOf(token));
    }

    public String getpurchasedate() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.purchasedate, "0");
    }

    public void setpurchasedate(String token) {
        helperPreference.setValue(Constants.USER_PREFERENCES.purchasedate, String.valueOf(token));
    }

    public String getUser_Type() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.User_Type, "0");
    }

    public void setUser_Type(String User_Type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.User_Type, String.valueOf(User_Type));
    }

    public String getCountryID() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.COUNTRYID, "0");
    }

    public void setCountryID(String auth) {
        helperPreference.setValue(Constants.USER_PREFERENCES.COUNTRYID, String.valueOf(auth));
    }

    public String getPROFILEID() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.PROFILEID, "0");
    }

    public void setPROFILEID(String auth) {
        helperPreference.setValue(Constants.USER_PREFERENCES.PROFILEID, String.valueOf(auth));
    }

    public String getOtpID() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.COUNTRYID, "0");
    }

    public void setOtpID(String auth) {
        helperPreference.setValue(Constants.USER_PREFERENCES.COUNTRYID, String.valueOf(auth));
    }

    public String getType() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Type_USer, "0");
    }

    public void setType(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Type_USer, String.valueOf(type));
    }

    public String getpic() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Type_layout, "0");
    }

    public void setpic(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Type_layout, String.valueOf(type));
    }

    public String getPhoneNum1() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.PhoneNum, "0");
    }

    public void setPhoneNum1(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.PhoneNum, String.valueOf(type));
    }

    public String getUserSignUpName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.UserSignUpName, "0");
    }

    public void setUserSignUpName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.UserSignUpName, String.valueOf(type));
    }

    public String getUserQbId() {
        return (helperPreference.getStringValue(Constants.USER_PREFERENCES.QB_ID_SENDER, "0"));
    }

    public void setUserQbId(String qbId) {
        helperPreference.setValue(Constants.USER_PREFERENCES.QB_ID_SENDER, String.valueOf(qbId));
    }

    public String getSocialUserQbId() {
        return (helperPreference.getStringValue(Constants.USER_PREFERENCES.QB_ID_SENDER, "0"));
    }

    public void setSocialUserQbId(String qbId) {
        helperPreference.setValue(Constants.USER_PREFERENCES.QB_ID_SENDER, String.valueOf(qbId));
    }


    public String getPackgeid() {
        return (helperPreference.getStringValue(Constants.USER_PREFERENCES.QB_ID_SENDER, "0"));
    }

    public void setPackgeid(String qbId) {
        helperPreference.setValue(Constants.USER_PREFERENCES.QB_ID_SENDER, String.valueOf(qbId));
    }


    public String getGmailadd() {
        return (helperPreference.getStringValue(Constants.USER_PREFERENCES.GMAIL, "0"));
    }

    public void setGmailadd(String qbId) {
        helperPreference.setValue(Constants.USER_PREFERENCES.GMAIL, String.valueOf(qbId));
    }

    public String getEmail() {
        return (helperPreference.getStringValue(Constants.USER_PREFERENCES.EMAIL, "0"));
    }

    public void setEmail(String qbId) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EMAIL, String.valueOf(qbId));
    }


    public void setRateApp(int rateApp) {
        helperPreference.setValue(Constants.USER_PREFERENCES.RATE_APP, rateApp);
    }


    public void setShareApp(int shareApp) {
        helperPreference.setValue(Constants.USER_PREFERENCES.SHARE_APP, shareApp);
    }

    public String getLongitude() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.LONGITUDE, "0");
    }

    public void setLongitude(double longitude) {
        helperPreference.setValue(Constants.USER_PREFERENCES.LONGITUDE, String.valueOf(longitude));
    }

    public String getDeviceToken() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.CONSTANT_FCM_TOKEN, "");
    }

    public void setDeviceToken(String deviceToken) {
        helperPreference.setValue(Constants.USER_PREFERENCES.CONSTANT_FCM_TOKEN, deviceToken);
    }

    public String getUserverify24hr() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Userverify24hr, "");
    }

    public void setUserverify24hr(String deviceToken) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Userverify24hr, deviceToken);
    }

    public String getActiveUser() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.ActiveUser, "");
    }

    public void setActiveUser(String deviceToken) {
        helperPreference.setValue(Constants.USER_PREFERENCES.ActiveUser, deviceToken);
    }

    public String getpaidUser() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.ispaid, "");
    }

    public void setpaidUser(String deviceToken) {
        helperPreference.setValue(Constants.USER_PREFERENCES.ispaid, deviceToken);
    }

    public String getisverify() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.isverify, "");
    }

    public void setisverify(String deviceToken) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isverify, deviceToken);
    }

    public void clear() {
        setUserLogin(false);
        helperPreference.setValue(Constants.USER_PREFERENCES.USER_PREFERENCE, "");
        helperPreference.setValue(Constants.USER_PREFERENCES.USER_DETAILS, "");
        helperPreference.setValue(Constants.USER_PREFERENCES.LOGINUSER_DETAILS, "");
        helperPreference.setValue(Constants.USER_PREFERENCES.AUTH, "");
        helperPreference.setValue(Constants.USER_PREFERENCES.CONSTANT_FCM_TOKEN, "");
        helperPreference.setValue(Constants.USER_PREFERENCES.QB_ID_SENDER, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.QB_ID_SENDER, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.GMAIL, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.EMAIL, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.patternspinner, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.schoolspinner, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.LOGIN_ID, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Token, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Formggender, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.PROFILEID, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Formdiksha1, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.ActiveUser, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.pending, "pending");

        helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtercountry, "0");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Filterstate, "0");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtercity, "0");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtergender, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Filteredu, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtermarital_status, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Filterage, "0");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Filterincome, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtername, "");

        helperPreference.getStringValue(Constants.USER_PREFERENCES.Userverify24hr, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.planid, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.planamount, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.txnId, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.NOLITING, "");

        helperPreference.getStringValue(Constants.USER_PREFERENCES.countryfilter, "India");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.statefilter, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.cityfilter, "");
        helperPreference.getStringValue(Constants.USER_PREFERENCES.edufilter, "");

//        ChatHelper.getInstance().logout();
    }

    public Registerdetails getUserDetail() {
        Registerdetails data = null;
        String userProfileJson = helperPreference.getStringValue(Constants.USER_PREFERENCES.USER_DETAILS, "");
        if (!TextUtils.isEmpty(userProfileJson)) {
            data = new Gson().fromJson(userProfileJson, Registerdetails.class);
        }
        return data;
    }

    public void setUserDetail(Registerdetails userDetail) {
        Gson gson = new Gson();
        String gsonUserDetails = gson.toJson(userDetail);
        helperPreference.setValue(Constants.USER_PREFERENCES.USER_DETAILS, gsonUserDetails);
        setUserLogin(true);
        setVisible(true);

    }

    public void setloginUserDetail(Registerdetails userDetail) {
        Gson gson = new Gson();
        String gsonUserDetails = gson.toJson(userDetail);
        helperPreference.setValue(Constants.USER_PREFERENCES.LOGINUSER_DETAILS, gsonUserDetails);
        setUserLogin(true);
        setVisible(true);
    }

    public void setPushSub(boolean isAction) {
        helperPreference.setValue(Constants.USER_PREFERENCES.ACTION_EVENT_HANDEL, isAction);
    }

    public boolean getPushSub() {
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.ACTION_EVENT_HANDEL, false);
    }

    public void setFragmentHandel(int isFrag) {
        helperPreference.setValue(Constants.USER_PREFERENCES.ACTION_EVENT_HANDEL, isFrag);
    }


    //// Registration Details
    public String getEdtCmpName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtCmpName, "");
    }

    public void setEdtCmpName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtCmpName, String.valueOf(type));
    }


    public int setRegCountryID1(int regCountryID) {
        helperPreference.setValue(Constants.USER_PREFERENCES.REG_COUNTRY_ID, regCountryID);
        return regCountryID;
    }

    public String getEdtBSPopularName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtBSPopularName, "");
    }

    public void setEdtBSPopularName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtBSPopularName, String.valueOf(type));
    }

    public String getEdtFeinNo() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtFeinNo, "");
    }

    public void setEdtFeinNo(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtFeinNo, String.valueOf(type));
    }

    public String getEdtBSDesc() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtBSDesc, "");
    }

    public void setEdtBSDesc(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtBSDesc, String.valueOf(type));
    }

    public String getEdtBSSince() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtBSSince, "");
    }

    public void setEdtBSSince(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtBSSince, String.valueOf(type));
    }


    public String getEdtTypeOfBs() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtTypeOfBusinName, "");
    }

    public void setEdtTypeOfBs(int type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtTypeOfBusinName, String.valueOf(type));
    }


    public String getEdtBusinessLocation() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtBusinessLocation, "");
    }

    public void setEdtBusinessLocation(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtBusinessLocation, String.valueOf(type));
    }

    public String getCheckStatus() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.CheckStatus, "0");
    }

    public void setCheckStatus(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.CheckStatus, String.valueOf(type));
    }

    public String getEdtStreetNo() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtStreetNo, "");
    }

    public void setEdtStreetNo(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtStreetNo, String.valueOf(type));
    }

    public String getEdtAddress() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtStreetName, "");
    }

    public void setEdtAddress(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtStreetName, String.valueOf(type));
    }

    public String getEdtZipCodeNum() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtZipCodeNum, "");
    }

    public void setEdtZipCodeNum(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtZipCodeNum, String.valueOf(type));
    }


    public String getEdtWebsiteName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtWebsiteName, "");
    }

    public void setEdtWebsiteName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtWebsiteName, String.valueOf(type));
    }

    public String getEdtCityName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtCityName, "");
    }

    public void setEdtCityName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtCityName, String.valueOf(type));
    }

    public String getEdtStateName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtStateName, "");
    }

    public void setEdtStateName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtStateName, String.valueOf(type));
    }

    public String getEdtCountryName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtCountryName, "");
    }

    public void setEdtCountryName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtCountryName, String.valueOf(type));
    }


    public int setRegCountryID(int regCountryID) {
        helperPreference.setValue(Constants.USER_PREFERENCES.REG_COUNTRY_ID, regCountryID);
        return regCountryID;
    }

    public String getEdtTypeOfBusinName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtTypeOfBusinName, "");
    }

    public void setEdtTypeOfBusinName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtTypeOfBusinName, String.valueOf(type));
    }

    public String getCardType() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.CardType, "");
    }

    public void setCardtype(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.CardType, String.valueOf(type));
    }


    public String getUrl() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Url, "");
    }

    public void setUrl(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Url, String.valueOf(type));
    }

    public String getEdtSignUpEmailAddress() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtSignUpEmailAddress, "");
    }

    public void setEdtSignUpEmailAddress(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtSignUpEmailAddress, String.valueOf(type));
    }

    public String getMobileNumber() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtMobileNumber, "");
    }

    public void setMobileNumber(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtMobileNumber, String.valueOf(type));
    }

    public String getEdtAlFirstName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtAlFirstName, "");
    }

    public void setEdtAlFirstName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtAlFirstName, String.valueOf(type));
    }

    public String getEdtAlLastName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtAlLastName, "");
    }

    public void setEdtAlLastName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtAlLastName, String.valueOf(type));
    }

    public String getEdtAlSignUpEmailAddress() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtAlSignUpEmailAddress, "");
    }

    public void setEdtAlSignUpEmailAddress(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtAlSignUpEmailAddress, String.valueOf(type));
    }

    public String getEdtAlMobileNumber() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.setEdtAlMobileNumber, "");
    }

    public void setEdtAlMobileNumber(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.setEdtAlMobileNumber, String.valueOf(type));
    }

    public String getEdtCardNumber() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtCardNumber, "");
    }

    public void setEdtCardNumber(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtCardNumber, String.valueOf(type));
    }

    public String getEdtCardExpDate() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtCardExpDate, "");
    }

    public void setEdtCardExpDate(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtCardExpDate, String.valueOf(type));
    }

    public String getEdtCardCvv() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtCardCvv, "");
    }

    public void setEdtCardCvv(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtCardCvv, String.valueOf(type));
    }

    public String getEdtCardName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtCardName, "");
    }

    public void setEdtCardName(String type) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtCardName, String.valueOf(type));
    }


    //for formdata save

    public String getEMAIL() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EMAIL, "");
    }

    public void setEMAIL(String EMAIL) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EMAIL, String.valueOf(EMAIL));
    }

    public String getFormAlFirstName() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.EdtAlFirstName, "");
    }


    public void setFormAlLastName(String FormAlLastName) {
        helperPreference.setValue(Constants.USER_PREFERENCES.EdtAlLastName, String.valueOf(FormAlLastName));
    }

    public String getGender() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.gender, "");
    }

    public void setGender(String gender) {
        helperPreference.setValue(Constants.USER_PREFERENCES.gender, String.valueOf(gender));
    }

    public String getPhoneNum() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.PhoneNum, "");
    }

    public void setPhoneNum(String phoneNum) {
        helperPreference.setValue(Constants.USER_PREFERENCES.PhoneNum, String.valueOf(phoneNum));
    }

    public String getFormbirthdate() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formbirthdate, "");
    }

    public void setFormbirthdate(String formbirthdate) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formbirthdate, String.valueOf(formbirthdate));
    }

    public String getFormbirthtime() {
        return helperPreference.getStringValue(Formbirthtime, "");
    }

    public void setFormbirthtime(String formbirthtime) {
        helperPreference.setValue(Formbirthtime, String.valueOf(formbirthtime));
    }

    public String getFormbirthplace() {
        return helperPreference.getStringValue(Formbirthplace, "");
    }

    public void setFormbirthplace(String formbirthtime) {
        helperPreference.setValue(Formbirthplace, String.valueOf(formbirthtime));
    }

    public String getFormbirthtimevikramm() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formbirthtimevikramm, "");
    }

    public void setFormbirthtimevikramm(String formbirthtimevikramm) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formbirthtimevikramm, String.valueOf(formbirthtimevikramm));
    }

    public String getFormmithi() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formmithi, "");
    }

    public void setFormmithi(String formmithi) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formmithi, String.valueOf(formmithi));
    }

    public String getFormmahino() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formmahino, "");
    }

    public void setFormmahino(String formmahino) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formmahino, String.valueOf(formmahino));
    }

    public String getFormpaksh() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formpaksh, "");
    }

    public void setFormpaksh(String formpaksh) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formpaksh, String.valueOf(formpaksh));
    }


    public String getFormdiksha1() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formdiksha1, "");
    }

    public void setFormdiksha1(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formdiksha1, String.valueOf(formdiksha));
    }

    public String getFormdiksshaguru() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formdiksshaguru, "");
    }

    public void setFormdiksshaguru(String formdiksshaguru) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formdiksshaguru, String.valueOf(formdiksshaguru));
    }

    public String getFormmatritalstatus() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formmatritalstatus, "");
    }

    public void setFormmatritalstatus(String formmatritalstatus) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formmatritalstatus, String.valueOf(formmatritalstatus));
    }

    public String getFormheight() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formheight, "");
    }

    public void setFormheight(String formheight) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formheight, String.valueOf(formheight));
    }

    public String getFormweight() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formweight, "");
    }

    public void setFormweight(String formweight) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formweight, String.valueOf(formweight));
    }

    public String getFormgotra() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formgotra, "");
    }

    public void setFormgotra(String formgotra) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formgotra, String.valueOf(formgotra));
    }

    public String getFormCommunity() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.FormCommunity, "");
    }

    public void setFormCommunity(String formCommunity) {
        helperPreference.setValue(Constants.USER_PREFERENCES.FormCommunity, String.valueOf(formCommunity));
    }

    public String getFormssubcommunity() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formssubcommunity, "");
    }

    public void setFormssubcommunity(String formssubcommunity) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formssubcommunity, String.valueOf(formssubcommunity));
    }

    public String getFormvarna() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formvarna, "");
    }

    public void setFormvarna(String formvarna) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formvarna, String.valueOf(formvarna));
    }

    public String getFormmool() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formmool, "");
    }

    public void setFormmool(String formmool) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formmool, String.valueOf(formmool));
    }

    public String getFormmanglik() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formmanglik, "");
    }

    public void setFormmanglik(String formmanglik) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formmanglik, String.valueOf(formmanglik));
    }

    public String getFormfathername() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formfathername, "");
    }

    public void setFormfathername(String formfathername) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formfathername, String.valueOf(formfathername));
    }

    public String getFormmothername() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formmothername, "");
    }

    public void setFormmothername(String formmothername) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formmothername, String.valueOf(formmothername));
    }

    public String getFormbbrothername() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formbbrothername, "");
    }

    public void setFormbbrothername(String formbbrothername) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formbbrothername, String.valueOf(formbbrothername));
    }

    public String getFormbbrotherinlaw() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formbbrotherinlaw, "");
    }

    public void setFormbbrotherinlaw(String formbbrotherinlaw) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formbbrotherinlaw, String.valueOf(formbbrotherinlaw));
    }

    public String getFormssistername() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formssistername, "");
    }

    public void setFormssistername(String formssistername) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formssistername, String.valueOf(formssistername));
    }

    public String getFormsisinlaw() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formsisinlaw, "");
    }

    public void setFormsisinlaw(String formsisinlaw) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formsisinlaw, String.valueOf(formsisinlaw));
    }

    public String getFormmaternalname() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formmaternalname, "");
    }

    public void setFormmaternalname(String formmaternalname) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formmaternalname, String.valueOf(formmaternalname));
    }

    public String getFormvilllage() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formvilllage, "");
    }

    public void setFormvilllage(String formvilllage) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formvilllage, String.valueOf(formvilllage));
    }

    public String getFormofficceno() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formofficceno, "");
    }

    public void setFormofficceno(String formofficceno) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formofficceno, String.valueOf(formofficceno));
    }

    public String getFormsecondaryno() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formsecondaryno, "");
    }

    public void setFormsecondaryno(String formofficceno) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formsecondaryno, String.valueOf(formofficceno));
    }

    public String getFormdegree() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formdegree, "");
    }

    public void setFormdegree(String formdegree) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formdegree, String.valueOf(formdegree));
    }

    public String getFormoccupation() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formoccupation, "");
    }

    public void setFormoccupation(String formoccupation) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formoccupation, String.valueOf(formoccupation));
    }

    public String getFormannualincome() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formannualincome, "");
    }

    public void setFormannualincome(String formannualincome) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formannualincome, String.valueOf(formannualincome));
    }

    public String getFormcurrentlocation() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formcurrentlocation, "");
    }

    public void setFormcurrentlocation(String formcurrentlocation) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formcurrentlocation, String.valueOf(formcurrentlocation));
    }

    public String getFormfathhersoccupation() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formfathhersoccupation, "");
    }

    public void setFormfathhersoccupation(String formfathhersoccupation) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formfathhersoccupation, String.valueOf(formfathhersoccupation));
    }

    public String getFormfamillyincome() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formfamillyincome, "");
    }

    public void setFormfamillyincome(String formfamillyincome) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formfamillyincome, String.valueOf(formfamillyincome));
    }

    public String getFormonion() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formonion, "");
    }

    public void setFormonion(String formonion) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formonion, String.valueOf(formonion));
    }

    public String getFormashauch() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formashauch, "");
    }

    public void setFormashauch(String formashauch) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formashauch, String.valueOf(formashauch));
    }

    public String getFormtrevelabbroad() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formtrevelabbroad, "");
    }

    public void setFormtrevelabbroad(String formtrevelabbroad) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formtrevelabbroad, String.valueOf(formtrevelabbroad));
    }

    public String getFormsettleabroad() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formsettleabroad, "");
    }

    public void setFormsettleabroad(String formsettleabroad) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formsettleabroad, String.valueOf(formsettleabroad));
    }

    public String getFormsspectacles() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formsspectacles, "");
    }

    public void setFormsspectacles(String formsspectacles) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formsspectacles, String.valueOf(formsspectacles));
    }

    public String getFormbadhabit() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formbadhabit, "");
    }

    public void setFormbadhabit(String formbadhabit) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formbadhabit, String.valueOf(formbadhabit));
    }

    public String getFormbadhabitpast() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formbadhabitpast, "");
    }

    public void setFormbadhabitpast(String formbadhabitpast) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formbadhabitpast, String.valueOf(formbadhabitpast));
    }

    public String getFormcountryname() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formcountry, "");
    }

    public void setFormcountryname(String formbadhabbitfamily) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formcountry, String.valueOf(formbadhabbitfamily));
    }

    public String getFormstatename() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formstate, "");
    }

    public void setFormstatename(String formbadhabbitfamily) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formstate, String.valueOf(formbadhabbitfamily));
    }

    public String getFormcityname() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formcity, "");
    }

    public void setFormcityname(String formbadhabbitfamily) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formcity, String.valueOf(formbadhabbitfamily));
    }

    public String getFormgender() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formggender, "");
    }

    public void setFormgender(String gender) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formggender, String.valueOf(gender));
    }

    public String getFormeducation() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formedu, "");
    }

    public void setFormeducation(String formbadhabbitfamily) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formedu, String.valueOf(formbadhabbitfamily));
    }

    public String getFormpic() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.FormUploadpic, "");
    }

    public void setFormpic(String formbadhabbitfamily) {
        helperPreference.setValue(Constants.USER_PREFERENCES.FormUploadpic, String.valueOf(formbadhabbitfamily));
    }


    public String getFormupfile() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formuploadfile, "");
    }

    public void setFormupfile(String formbadhabbitfamily) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formuploadfile, String.valueOf(formbadhabbitfamily));
    }

    public String getFormhoby() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formhobby, "");
    }

    public void setFormFormhoby(String formbadhabbitfamily) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formhobby, String.valueOf(formbadhabbitfamily));
    }

    public String getFormdisabltyy() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Formdisablity, "");
    }

    public void setFormdisality(String formbadhabbitfamily) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Formdisablity, String.valueOf(formbadhabbitfamily));
    }

    public String getFilterCountry() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtercountry, "0");
    }

    public void setFilterCountry(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filtercountry, String.valueOf(formdiksha));
    }

    public String getFilterState() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filterstate, "0");
    }

    public void setFilterState(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filterstate, String.valueOf(formdiksha));
    }

    public String getFiltercity() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtercity, "0");
    }

    public void setFiltercity(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filtercity, String.valueOf(formdiksha));
    }

    public String getFiltergender() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtergender, "0");
    }

    public void setFiltergender(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filtergender, String.valueOf(formdiksha));
    }


    public String getFilteredu() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filteredu, "");
    }

    public void setFilteredu(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filteredu, String.valueOf(formdiksha));
    }

    public String getFiltername() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtername, "");
    }

    public void setFiltername(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filtername, String.valueOf(formdiksha));
    }

    public String getFiltermarital_status() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filtermarital_status, "");
    }

    public void setFiltermarital_status(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filtermarital_status, String.valueOf(formdiksha));
    }

    public String getFilterage() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filterage, "0");
    }

    public void setFilterage(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filterage, String.valueOf(formdiksha));
    }

    public String getFilterincome() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filterincome, "");
    }

    public void setFilterincome(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filterincome, String.valueOf(formdiksha));
    }


    public String getFilecount() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.Filecount, "0");
    }

    public void setFilecount(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.Filecount, String.valueOf(formdiksha));
    }

    public String getaudiouri() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.audiouri, "0");
    }

    public void setaudiouri(String formdiksha) {
        helperPreference.setValue(Constants.USER_PREFERENCES.audiouri, String.valueOf(formdiksha));
    }

    public boolean isage20() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isage20, false);
    }

    public void setisage20(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isage20, isUserLogin);
    }

    public boolean isage26() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isage26, false);
    }

    public void setisage26(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isage26, isUserLogin);
    }

    public boolean isage31() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isage31, false);
    }

    public void setisage31(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isage31, isUserLogin);
    }

    public boolean isage36() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isage36, false);
    }

    public void setisage36(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isage36, isUserLogin);
    }

    public boolean isage41() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isage41, false);
    }

    public void setisage41(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isage41, isUserLogin);
    }

    public boolean isage46() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isage46, false);
    }

    public void setisage46(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isage46, isUserLogin);
    }

    public boolean isage51() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isage51, false);
    }

    public void setisage51(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isage51, isUserLogin);
    }

    public boolean isage56() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isage56, false);
    }

    public void setisage56(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isage56, isUserLogin);
    }


    public boolean isinncome50max() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isinncome50max, false);
    }

    public void setisinncome50max(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isinncome50max, isUserLogin);
    }

    public boolean isinncome50k() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isinncome50k, false);
    }

    public void setisinncome50k(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isinncome50k, isUserLogin);
    }

    public boolean isinncome1lac() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isinncome1lac, false);
    }

    public void setisinncome1lac(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isinncome1lac, isUserLogin);
    }

    public boolean isinncome2lac() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isinncome2lac, false);
    }

    public void setisinncome2lac(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isinncome2lac, isUserLogin);
    }

    public boolean isinncome5lac() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isinncome5lac, false);
    }

    public void setisinncome5lac(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isinncome5lac, isUserLogin);
    }

    public boolean isinncome10lac() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isinncome10lac, false);
    }

    public void setisinncome10lac(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isinncome10lac, isUserLogin);
    }

    public boolean isinncome20lac() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isinncome20lac, false);
    }

    public void setisinncome20lac(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isinncome20lac, isUserLogin);
    }


    public boolean isnevermarried() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isnevermarried, false);
    }

    public void setisnevermarried(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isnevermarried, isUserLogin);
    }


    public boolean isdivorced() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.isdivorced, false);
    }

    public void setisdivorced(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.isdivorced, isUserLogin);
    }


    public boolean iswidowed() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.iswidowed, false);
    }

    public void setiswidowed(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.iswidowed, isUserLogin);
    }


    public boolean issingleparents() {
        //return getUserData() != null;
        return helperPreference.getBoolanValue(Constants.USER_PREFERENCES.issingleparents, false);
    }

    public void setissingleparents(boolean isUserLogin) {
        helperPreference.setValue(Constants.USER_PREFERENCES.issingleparents, isUserLogin);
    }


    public void setschoolspinner(String formbbrotherinlaw) {
        helperPreference.setValue(Constants.USER_PREFERENCES.schoolspinner, String.valueOf(formbbrotherinlaw));
    }


    public String getschoolspinner() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.schoolspinner, "0");
    }


    public void setpatternspinner(String formbbrotherinlaw) {
        helperPreference.setValue(Constants.USER_PREFERENCES.patternspinner, String.valueOf(formbbrotherinlaw));
    }

    public String getpatternspinner() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.patternspinner, "");
    }


    public void setFiltercity1(String formbbrotherinlaw) {
        helperPreference.setValue(Constants.USER_PREFERENCES.cityfilter, String.valueOf(formbbrotherinlaw));
    }

    public String getFiltercity1() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.cityfilter, "");
    }

    public void setFilteredudegree(String formbbrotherinlaw) {
        helperPreference.setValue(Constants.USER_PREFERENCES.edufilter, String.valueOf(formbbrotherinlaw));
    }

    public String getFilteredudegree() {
        return helperPreference.getStringValue(Constants.USER_PREFERENCES.edufilter, "");
    }

    //clear data of profile
    public void profileDataClear() {
        setFormgotra("");
        setFormCommunity("");
        setFormssubcommunity("");
        setFormvarna("");
        setFormmool("");
        setFormupfile("");
        setFormfathername("");
        setFormmothername("");
        //  setFormbbrotherinlaw(BrotherinlawName);
        setFormbbrothername("");
        //setFormsisinlaw(SisterinlawName);
        setFormssistername("");
        setFormmaternalname("");
        setEdtAddress("");
        //    setFormvilllage(city);
        setFormofficceno("");
        setFormsecondaryno("");

        setFormoccupation("");
        setFormannualincome("");
        setFormcurrentlocation("");
        setFormfathhersoccupation("");
        setFormfamillyincome("");
        setFormdegree("");

        setFormFormhoby("");
        setFormdisality("");

        setPhoneNum("");
        setFormbirthdate("");
        setEMAIL("");
        setFormbirthtime("");
        setFormbirthtimevikramm("");
        setFormbirthplace("");
        //      UserPreference.getInstance(mBaseAppCompatActivity).setFormdiksha("mthi");
        setFormdiksshaguru("");
        //    UserPreference.getInstance(mBaseAppCompatActivity).setFormmatritalstatus("");
        setFormheight("");
        setFormweight("");
    }

}
