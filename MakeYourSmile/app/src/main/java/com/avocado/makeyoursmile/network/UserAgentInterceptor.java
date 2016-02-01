package com.avocado.makeyoursmile.network;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by HDlee on 15. 11. 3..
 */
public class UserAgentInterceptor implements Interceptor {

    private static final String USER_AGENT_HEADER = "User-Agent";

    private String userAgent;

    public UserAgentInterceptor(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Request request = builder.removeHeader(USER_AGENT_HEADER).addHeader(USER_AGENT_HEADER, userAgent).build();
        return chain.proceed(request);
    }
}
