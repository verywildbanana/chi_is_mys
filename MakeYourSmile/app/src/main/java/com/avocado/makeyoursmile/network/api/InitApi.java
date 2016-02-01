package com.avocado.makeyoursmile.network.api;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by HDlee on 15. 11. 4..
 */
public interface InitApi {

    @GET("/init/check")
    void initCheck(Callback<String> callback);

//    @GET("/app/my/broadcasts")
//    void getMyBroadcasts(Callback<String> callback);

}
