package com.uniformorder.uniformorderr.retrofit;

import com.uniformorder.uniformorderr.model.Dashboardmodell;
import com.uniformorder.uniformorderr.model.DeleteOrder;
import com.uniformorder.uniformorderr.model.EditOrderResponse;
import com.uniformorder.uniformorderr.model.Membermodel;
import com.uniformorder.uniformorderr.model.Registermodel;
import com.uniformorder.uniformorderr.model.ResponseDeleteAddSchool;
import com.uniformorder.uniformorderr.model.ResponseDistrict;
import com.uniformorder.uniformorderr.model.ResponsePayCenter;
import com.uniformorder.uniformorderr.model.ResponseSchoolList;
import com.uniformorder.uniformorderr.model.Schoollistmodel;
import com.uniformorder.uniformorderr.model.filtermodel;
import com.uniformorder.uniformorderr.testModel.ResponseOrderList;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface APIInterface {

    @FormUrlEncoded
    @POST("register")
    Call<Registermodel> signup(@Field("name") String name,
                               @Field("email") String email,
                               @Field("mobile") String mobile,
                               @Field("password") String password,
                               @Field("user_type") String user_type,
                               @Field("device_type") String device_type,
                               @Field("device_token") String device_token,
                               @Field("social_type") String social_type);

    @FormUrlEncoded
    @POST("login")
    Call<Registermodel> login(@Field("email") String email,
                              @Field("password") String password,
                              @Field("device_type") String device_type,
                              @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("school/add")
    Call<Registermodel> addschool(@Field("login_id") String login_id,
                                  @Field("name") String name,
                                  @Field("principal_name") String principal_name,
                                  @Field("assistant_name") String assistant_name,
                                  @Field("village_id") String village_id,
                                  @Field("pay_center_id") String pay_center_id,
                                  @Field("district_id") String district_id,
                                  @Field("mobile1") String mobile1,
                                  @Field("mobile2") String mobile2);

    @FormUrlEncoded
    @POST("pattern/add")
    Call<Registermodel> addpattern(@Field("login_id") String login_id,
                                   @Field("name") String name);

    @FormUrlEncoded
    @POST("school/update")
    Call<Registermodel> updateschool(@Field("login_id") String login_id,
                                     @Field("school_id") String school_id,
                                     @Field("name") String name,
                                     @Field("principal_name") String principal_name,
                                     @Field("assistant_name") String assistant_name,
                                     @Field("village_id") String village,
                                     @Field("pay_center_id") String pay_center,
                                     @Field("district_id") String district,
                                     @Field("mobile1") String mobile1,
                                     @Field("mobile2") String mobile2);

    @FormUrlEncoded
    @POST("pattern/update")
    Call<Registermodel> updatpattern(@Field("login_id") String login_id,
                                     @Field("pattern_id") String pattern_id,
                                     @Field("name") String name);

    @FormUrlEncoded
    @POST("change/password")
    Call<Registermodel> changepass(@Field("login_id") String login_id,
                                   @Field("old_password") String old_password,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("school/list")
    Call<ResponseSchoolList> schoollist(@Field("login_id") String login_id,
                                        @Field("limit") String limit,
                                        @Field("offset") String offset,
                                        @Field("search_value") String search_value);


    @FormUrlEncoded
    @POST("pattern/list")
    Call<Schoollistmodel> patternlist(@Field("login_id") String login_id,
                                      @Field("limit") String limit,
                                      @Field("offset") String offset);

    @FormUrlEncoded
    @POST("order/excelexport")
    Call<filtermodel> excelexport(@Field("login_id") String login_id,
                                  @Field("start_date") String start_date,
                                  @Field("end_date") String end_date,
                                  @Field("select_district")String select_district,
                                  @Field("select_pay_center")String select_pay_center,
                                  @Field("select_pattern")String pattern);

    @FormUrlEncoded
    @POST("order/excelexport")
    Call<filtermodel>excelExportAllPayCenter(@Field("login_id") String login_id,
                                  @Field("start_date") String start_date,
                                  @Field("end_date") String end_date,
                                  @Field("select_district")String select_district,
                                  @Field("select_pattern")String pattern);

    @FormUrlEncoded
    @POST("order/excelexport")
    Call<filtermodel>excelExportWithPattern(@Field("login_id") String login_id,
                                             @Field("start_date") String start_date,
                                             @Field("end_date") String end_date,
                                             @Field("select_district")String select_district
    );

    @FormUrlEncoded
    @POST("school/delete")
    Call<ResponseDeleteAddSchool> delete(@Field("login_id") String login_id,
                                         @Field("school_id") String schoolid);

    @FormUrlEncoded
    @POST("user/delete")
    Call<Schoollistmodel> deleteuser(@Field("login_id") String login_id,
                                     @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("pattern/delete")
    Call<Schoollistmodel> deletepattern(@Field("login_id") String login_id,
                                        @Field("pattern_id") String pattern_id);


    @FormUrlEncoded
    @POST("logout")
    Call<Schoollistmodel> logout(@Field("user_id") String login_id,
                                 @Field("token") String pattern_id,
                                 @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("user/logout")
    Call<Schoollistmodel> logout(@Field("user_id") String login_id,
                                 @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("user/list")
    Call<Membermodel> userlist(@Field("login_id") String login_id);



    @FormUrlEncoded
    @POST("order/list")
    Call<ResponseOrderList> orderlist(@Field("login_id") String login_id,
                                      @Field("type") String type,
                                      @Field("search_value") String search_value,
                                      @Field("limit")String limit);

    @FormUrlEncoded
    @POST("user/update")
    Call<Membermodel> useredit(@Field("login_id") String login_id,
                               @Field("user_id") String user_id,
                               @Field("name") String name,
                               @Field("mobile") String mobile,
                               @Field("user_type") String user_type);


    @Multipart
    @POST("update/profile")
    Call<Registermodel> editprfile(@PartMap() Map<String, RequestBody> partMap,
                                   @Part MultipartBody.Part fileThumb);

    @FormUrlEncoded
    @POST("user/dashboard")
    Call<Dashboardmodell> dashboardcalll(@Field("login_id") String login_id);



    @Multipart
    @POST("order/add")
    Call<Schoollistmodel> addorder(@PartMap() Map<String, RequestBody> partMap,
                                   @Part("standards[]") List<Integer> saveorderRequestdetails1,
                                   @Part("boys[]") List<Integer> saveorderRequestdetails2,
                                   @Part("girls[]") List<Integer> saveorderRequestdetails3);


    @Multipart
    @POST("order/deliver")
    Call<Schoollistmodel> editorder(@PartMap() Map<String, RequestBody> partMap,
                                    @Part("standard_id[]") List<Integer> saveorderRequestdetails,
                                    @Part("standards[]") List<Integer> saveorderRequestdetails1,
                                    @Part("boys[]") List<Integer> saveorderRequestdetails2,
                                    @Part("girls[]") List<Integer> saveorderRequestdetails3);



    @FormUrlEncoded
    @POST("order/delete")
    Call<DeleteOrder> deleteOrder(@Field("order_id") String id);

    @Multipart
    @POST("order/update")
    Call<EditOrderResponse> editOrderForUpdate(@PartMap() Map<String, RequestBody> partMap,
                                               @Part("standards[]")List<Integer> standard,
                                               @Part("boys[]")List<Integer>boys,
                                               @Part("girls[]")List<Integer>girls,
                                               @Part("standard_id[]")List<Integer>standID);

    @FormUrlEncoded
    @POST("district")
    Call<ResponseDistrict>getDistrict(@Field("login_id")String id);

    @FormUrlEncoded
    @POST("district/paycenter")
    Call<ResponsePayCenter>getPayCenter(@Field("login_id")String login_id,
                                        @Field("select_district")String select_district);
}
