package com.avocado.makeyoursmile.network;

import java.util.Locale;

import retrofit.RequestInterceptor;

/**
 * Created by HDlee on 15. 11. 3..
 */
public class ApiRequestInterceptor implements RequestInterceptor {

    private static final String LOG_TAG = ApiRequestInterceptor.class.getSimpleName();

    private static final String HEADER_LANGUAGE = "Accept-Language";
    private static final String HEADER_AUTHORIZATION = "Authorization";



    public static boolean mForJsonBodyReq = false;


    @Override
    public void intercept(RequestFacade request) {

        applyDefaultHeader(request);
        applyApiTokenHeader(request);
    }

    private void applyDefaultHeader(RequestFacade request) {

        request.addHeader(HEADER_LANGUAGE, Locale.getDefault().getLanguage());

        if(mForJsonBodyReq) {

            request.addHeader("Content-Type", "application/json;charset=UTF-8");
        }
        else {

            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        }

    }

    private void applyApiTokenHeader(RequestFacade request) {

//        String token = SharedPreferenceManager.getInstance().getAccessToken();
//        String type = SharedPreferenceManager.getInstance().getTokenType();
//        if (TextUtils.isEmpty(token) == false
//                && TextUtils.isEmpty(type) == false) {
//
//            request.addHeader("Authorization",  type + " " + token );
//        }

    }
}

