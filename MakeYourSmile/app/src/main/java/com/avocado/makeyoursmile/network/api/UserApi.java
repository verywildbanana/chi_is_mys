package com.avocado.makeyoursmile.network.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.PUT;

/**
 * Created by HDlee on 12/4/15.
 */
public interface UserApi {


    @FormUrlEncoded
    @PUT("/v1/users/me")
    void users(@Field("nickname") String nickname,
               @Field("email") String email,
               @Field("phone") String phone,
               @Field("status_msg") String status_msg,
               @Field("image") String image,
               Callback<Response> callback);


    @FormUrlEncoded
    @PUT("/v1/users/me/location")
    void usersLocation(@Field("coarse_location") String location, Callback<Response> callback);

}
