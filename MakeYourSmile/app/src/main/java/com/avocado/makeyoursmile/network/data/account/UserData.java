package com.avocado.makeyoursmile.network.data.account;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HDlee on 11/26/15.
 */
public class UserData {

//    11-26 14:28:19.271: D/Retrofit(4670): {"user_id":1,"assertion_token":"1q2w3e4r"}

    @SerializedName("user_id")
    public long user_id;

    @SerializedName("assertion_token")
    public String assertion_token;
    public String nickname;
    public String email;
    public String phone;
    public String status_msg;
    public String image;
    public String created_at;
    public String updated_at;

}
