package com.avocado.makeyoursmile.network.data.account;

/**
 * Created by HDlee on 11/26/15.
 */
public class AuthData {



   /*
   {
        "refresh_token": "eyJraWQiOiIxIiwiYWxnIjoiSFMyNTYifQ.eyJhdWQiOiJ1cm46d2VhcnJ1Iiwic3ViIjoiMSIsInRva2VuX3R5cGUiOiJyZWZyZXNoIiwiaWF0IjoxNDQ4NjAyNDI1fQ.fJO6fbBY2Q1P7TwVSVklwKAAyzucp6nB90aUEhiBY3c",
        "access_token": "eyJraWQiOiIxIiwiYWxnIjoiSFMyNTYifQ.eyJhdWQiOiJ1cm46d2VhcnJ1Iiwic3ViIjoiMSIsInRva2VuX3R5cGUiOiJiZWFyZXIiLCJleHAiOjE0NDg2MDYwMjYsInNpZCI6IjUzZDdkYjMyMTJlNDU3ZTJkYmM0ZTU0NDE0ODIwYTVhZTk1YzVkMDZiMzc1ZDk1M2MzNDY5NThiNGU5Y2U3ZTEifQ.Igr_QZNcbgrTblNiNLwFH8mbFimRnHMpJxV-1HvbiN4",
        "token_type": "Bearer",
        "expires_in": 3600,
        "user_id": 1
    }
   */

    public long user_id;

    public String access_token;

    public String refresh_token;

    public String token_type;

    public String expires_in;

}
