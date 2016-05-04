package com.avocado.makeyoursmile.network.api;

import com.avocado.makeyoursmile.network.data.error.ErrorData;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by HDlee on 15. 11. 4..
 */
public interface QueryApi {

    @FormUrlEncoded
    @POST("/insertUserInquiry.do")
    void insertUserInquiry(@Field("USER_ID") String USER_ID,
                       @Field("NAME") String NAME,
                       @Field("ADDRESS1") String ADDRESS1,
                       @Field("ADDRESS2") String ADDRESS2,
                       @Field("AGE") int AGE,
                       @Field("DES") String DENTIST_DES,
                       @Field("IMG_1") String IMG_1,
                       @Field("IMG_2") String IMG_2,
                       @Field("IMG_3") String IMG_3,
                       @Field("GENDER") String GENDER,
                       @Field("P_1") boolean P_1,
                       @Field("P_2") boolean P_2,
                       @Field("P_3") boolean P_3,
                       @Field("P_4") boolean P_4,
                       @Field("P_5") boolean P_5,
                       @Field("P_6") boolean P_6,
                       @Field("INQUIRY_ID") String INQUIRY_ID,
                       @Field("DENTIST_ID") String DENTIST_ID,
                       Callback<ErrorData> callback);

    @FormUrlEncoded
    @POST("/insertDentistReply.do")
    void insertDentistReply(@Field("DENTIST_ID") String DENTIST_ID,
                             @Field("INQUIRY_ID") String INQUIRY_ID,
                             @Field("REPLY_ID") String REPLY_ID,
                             @Field("NAME") String NAME,
                             @Field("DES") String DES,
                             @Field("IMG_1") String IMG_1,
                             Callback<ErrorData> callback);






}
