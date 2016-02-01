package com.avocado.makeyoursmile;

import android.app.Application;

import com.avocado.makeyoursmile.network.ApiRequestInterceptor;
import com.avocado.makeyoursmile.network.InterceptingOkClient;
import com.avocado.makeyoursmile.network.ServerConfig;
import com.avocado.makeyoursmile.network.UserAgentInterceptor;
import com.avocado.makeyoursmile.util.SharedPreferenceManager;
import com.avocado.makeyoursmile.util.SmartLog;
import com.avocado.makeyoursmile.util.ToastManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;

import retrofit.RestAdapter;

/**
 * Created by HDlee on 1/29/16.
 */
public class MakeYourSmileApp extends Application {
    private static final String TAG = MakeYourSmileApp.class.getSimpleName();

    private static MakeYourSmileApp mApplication;
    private OkHttpClient mOkHttpClient;
    private RestAdapter mApiRestAdapter;


    public static MakeYourSmileApp getInstance() {

        if (mApplication != null) {

            return mApplication;

        } else {

            SmartLog.getInstance().d(TAG, "Application is not attached.");
            throw new RuntimeException("MakeYourSmileApp Application is not attached.");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SmartLog.getInstance().d(TAG, "Application onCreate");
        Global.getInstance().setApplicationContext(getApplicationContext());
        initOkHttp();
        initRestAdapter();
        initGlide();
        ToastManager.getInstance().init(this);
        SharedPreferenceManager.getInstance().init(getApplicationContext());
        mApplication = this;
        SharedPreferenceManager.getInstance().setPID(android.os.Process.myPid());

    }

    public static <T> T createApi(Class<T> service) {
        return getInstance().mApiRestAdapter.create(service);
    }

    private void initOkHttp() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.interceptors().add(new UserAgentInterceptor(ServerConfig.getUserAgent()));

        SmartLog.getInstance().w("TAG", "userAgent ~~~ " + ServerConfig.getUserAgent());

    }

    private void initRestAdapter() {

        ApiRequestInterceptor apiRequestInterceptor = new ApiRequestInterceptor();

        InterceptingOkClient client =  new InterceptingOkClient(mOkHttpClient);
        mApiRestAdapter = new RestAdapter.Builder()
                .setEndpoint(Global.getInstance().getEndpoint())
                .setRequestInterceptor(apiRequestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(client)
                .build();


    }

    private void initGlide() {
        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(mOkHttpClient));
    }
}

