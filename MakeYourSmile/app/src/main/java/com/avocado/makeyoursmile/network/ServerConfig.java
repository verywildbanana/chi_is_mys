package com.avocado.makeyoursmile.network;


import com.avocado.makeyoursmile.Global;

/**
 * Created by HDlee on 15. 11. 3..
 */
public class ServerConfig {

    private ServerConfig() {
    }


    public static String getUserAgent() {

        String userAgent = "Chi_Is/" + Global.getInstance().getAppVer();

        return userAgent;
    }

}
