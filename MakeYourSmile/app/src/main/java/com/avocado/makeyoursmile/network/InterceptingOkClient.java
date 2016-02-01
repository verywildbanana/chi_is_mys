package com.avocado.makeyoursmile.network;

import com.avocado.makeyoursmile.util.SmartLog;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * Created by HDlee on 12/3/15.
 */
public class InterceptingOkClient extends OkClient
{
    public InterceptingOkClient()
    {
    }

    public InterceptingOkClient(OkHttpClient client)
    {
        super(client);
    }

    @Override
    public Response execute(Request request) throws IOException
    {

        Response response = super.execute(request);

        for (Header header : response.getHeaders())
        {
            SmartLog.getInstance().w("tag", "InterceptingOkClient Header " + request.getUrl() + " : " + header.getName() + " : " + header.getValue());
        }

        return response;
    }
}
