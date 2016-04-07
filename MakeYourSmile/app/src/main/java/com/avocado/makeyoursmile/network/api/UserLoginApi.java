package com.avocado.makeyoursmile.network.api;

import com.avocado.makeyoursmile.network.data.user.LoginParserData;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by HDlee on 12/4/15.
 */
public interface UserLoginApi {


    @FormUrlEncoded
    @POST("/insertFBUser.do")
    void insertFBUser(@Field("FB_ID")String  FB_ID,
                       @Field("LOGIN_TYPE")String  LOGIN_TYPE,
                       @Field("FB_TOKEN")String  FB_TOKEN,
                       @Field("NAME")String  NAME,
                       @Field("IMG_1")String  IMG_1,
                       @Field("PHONE")String  PHONE,
                       @Field("EMAIL")String  EMAIL,
                       Callback<LoginParserData> callback);

    @FormUrlEncoded
    @POST("/insertKAKAOUser.do")
    void insertKAKAOUser(@Field("KAKAO_ID")String  KAKAO_ID,
                       @Field("LOGIN_TYPE")String  LOGIN_TYPE,
                       @Field("KAKAO_TOKEN")String  KAKAO_TOKEN,
                       @Field("NAME")String  NAME,
                       @Field("IMG_1")String  IMG_1,
                       @Field("PHONE")String  PHONE,
                       @Field("EMAIL")String  EMAIL,
                       Callback<LoginParserData> callback);


}
