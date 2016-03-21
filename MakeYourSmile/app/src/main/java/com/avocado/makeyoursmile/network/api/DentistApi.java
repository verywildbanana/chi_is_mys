package com.avocado.makeyoursmile.network.api;

import com.avocado.makeyoursmile.network.data.error.ErrorData;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by HDlee on 15. 11. 4..
 */
public interface DentistApi {

    @FormUrlEncoded
    @POST("/insertDentist.do")
    void insertDentist(@Field("ID")String  ID,
                       @Field("PASSWD")String  PASSWORD,
                       @Field("NAME")String  DENTIST_NAME,
                       @Field("ADDRESS1")String  ADDRESS1,
                       @Field("ADDRESS2")String  ADDRESS2,
                       @Field("ADDRESS3")String  ADDRESS3,
                       @Field("ADDRESS4")String  ADDRESS4,
                       @Field("LAT")String  LAT,
                       @Field("LNG")String  LNG,
                       @Field("PHONE")String  PHONE,
                       @Field("ACTIVE_TIME1")String  ACT_TIME1,
                       @Field("ACTIVE_TIME2")String  ACT_TIME2,
                       @Field("ACTIVE_TIME3")String  ACT_TIME3,
                       @Field("DES")String  DENTIST_DES,
                       @Field("DT_1_NAME")String  DOCTOR1_NAME,
                       @Field("DT_1_DES")String  DOCTOR1_DES,
                       @Field("DT_2_NAME")String  DOCTOR2_NAME,
                       @Field("DT_2_DES")String  DOCTOR2_DES,
                       @Field("DT_3_NAME")String  DOCTOR3_NAME,
                       @Field("DT_3_DES")String  DOCTOR3_DES,
                       Callback<ErrorData> callback);

}
