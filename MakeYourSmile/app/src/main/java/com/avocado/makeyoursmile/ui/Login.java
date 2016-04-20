package com.avocado.makeyoursmile.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.avocado.makeyoursmile.Global;
import com.avocado.makeyoursmile.MakeYourSmileApp;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.api.UserLoginApi;
import com.avocado.makeyoursmile.network.data.user.LoginParserData;
import com.avocado.makeyoursmile.network.data.user.UserParserData;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.util.SharedPreferenceManager;
import com.avocado.makeyoursmile.util.SmartLog;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.kakao.auth.AuthType;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.network.tasks.KakaoResultTask;
import com.kakao.network.tasks.KakaoTaskQueue;
import com.kakao.usermgmt.api.UserApi;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.StoryProtocol;
import com.kakao.util.helper.TalkProtocol;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by HDlee on 1/29/16.
 */
public class Login extends BaseActivity {


    private CallbackManager mCallbackManager;
    private ProfileTracker profileTracker;
    private AccessTokenTracker accessTokenTracker;

    private SessionCallback callback;

    UserLoginApi mUserLoginApi = MakeYourSmileApp.createApi(UserLoginApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initSdk();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (mCallbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.SkipImg)
    public void onClicSkip(View v) {

        IntentManager.getInstance().push(this, Start.class, true);

    }


    void initSdk() {


        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        Profile profile = Profile.getCurrentProfile();

                        if(profile == null) {

                            Toast.makeText(Login.this, "Facebook profile null", Toast.LENGTH_LONG).show();
                            return;
                        }

                        final  String name =   profile.getName();
                        final  String id =   profile.getId();
                        final  String token =   loginResult.getAccessToken().getToken();
                        final  String logintype =   "f";
                        final  String img =   profile.getProfilePictureUri(200, 200).toString();

                        SmartLog.getInstance().d(TAG, "FACEBOOK LoginManager onSuccess getToken " + loginResult.getAccessToken().getToken());
                        SmartLog.getInstance().d(TAG, "FACEBOOK LoginManager onSuccess getRecentlyGrantedPermissions " + loginResult.getRecentlyGrantedPermissions());
                        SmartLog.getInstance().d(TAG, "FACEBOOK LoginManager onSuccess profile name " + name);
                        SmartLog.getInstance().d(TAG, "FACEBOOK LoginManager onSuccess profile img  " + img);

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {

                                        SmartLog.getInstance().d(TAG, "FACEBOOK newMeRequest onCompleted object " + object.toString());
                                        SmartLog.getInstance().d(TAG, "FACEBOOK newMeRequest onCompleted response " + response.toString());

                                        showIndicator(false, null);
                                        requestLogin(id, logintype, token,  name, img, null, null );

                                    }

                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();


                    }

                    @Override
                    public void onCancel() {

                        Toast.makeText(Login.this, "Facebook Login Cancel", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(FacebookException exception) {

                        Toast.makeText(Login.this, "Facebook Error : " +exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

                AccessToken.setCurrentAccessToken(newToken);
                SmartLog.getInstance().d(TAG, "FACEBOOK AccessTokenTracker onCurrentAccessTokenChanged getPermissions " + newToken.getPermissions());
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {

                SmartLog.getInstance().d(TAG, "FACEBOOK ProfileTracker onCurrentProfileChanged getName " + newProfile.getName());
                SmartLog.getInstance().d(TAG, "FACEBOOK ProfileTracker onCurrentProfileChanged getId " + newProfile.getId());

            }
        };


        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);

    }


    final String fb_public_profile = "public_profile";
    final String fb_user_friends = "user_friends";
    final String fb_user_birthday = "user_birthday";
    final String fb_publish_actions = "publish_actions";
    final String fb_email = "email";

    @OnClick(R.id.FacebookImg)
    public void onClicLoginFacebook(View v) {



        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(fb_public_profile, fb_user_friends, fb_email));

    }


    public void publishStory() {

        Set<String> permissions = AccessToken.getCurrentAccessToken().getPermissions();

        final List<String> PUBLISH_PERMISSIONS = Arrays.asList(fb_publish_actions/*, fb_user_birthday, fb_email*/);

        SmartLog.getInstance().d(TAG, "FACEBOOK publishStory "  + permissions.contains(PUBLISH_PERMISSIONS) );


        if (!permissions.contains(PUBLISH_PERMISSIONS)) {

            LoginManager.getInstance().logInWithPublishPermissions(this, PUBLISH_PERMISSIONS);
            return;
        }

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .setContentTitle("My message ")
                .build();

    }


    /**
     *
     *  kakao talk
    */



    @OnClick(R.id.KakaoImg)
    public void onClicLoginKakaotalk(View v) {


        if(Session.getCurrentSession().checkAndImplicitOpen() ) {

            SmartLog.getInstance().d(TAG, "kakao Session.getCurrentSession().getAccessToken() " + Session.getCurrentSession().getAccessToken());

        }
        else {

            // 카톡 또는 카스가 존재하면 옵션을 보여주고, 존재하지 않으면 바로 직접 로그인창.
            final List<AuthType> authTypes = getAuthTypes();
            if(authTypes.size() == 1){
                Session.getCurrentSession().open(authTypes.get(0), this);
            } else {
                onClickLoginButton(authTypes);
            }


        }

    }


    private List<AuthType> getAuthTypes() {
        final List<AuthType> availableAuthTypes = new ArrayList<AuthType>();
        if(TalkProtocol.existCapriLoginActivityInTalk(this, Session.getCurrentSession().isProjectLogin())){
            availableAuthTypes.add(AuthType.KAKAO_TALK);
            availableAuthTypes.add(AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN);
        }
        if(StoryProtocol.existCapriLoginActivityInStory(this, Session.getCurrentSession().isProjectLogin())){
            availableAuthTypes.add(AuthType.KAKAO_STORY);
        }
        availableAuthTypes.add(AuthType.KAKAO_ACCOUNT);

        final AuthType[] selectedAuthTypes = Session.getCurrentSession().getAuthTypes();
        availableAuthTypes.retainAll(Arrays.asList(selectedAuthTypes));

        // 개발자가 설정한 것과 available 한 타입이 없다면 직접계정 입력이 뜨도록 한다.
        if(availableAuthTypes.size() == 0){
            availableAuthTypes.add(AuthType.KAKAO_ACCOUNT);
        }
        return availableAuthTypes;
    }

    private void onClickLoginButton(final List<AuthType> authTypes){
        final List<Item> itemList = new ArrayList<Item>();
        if(authTypes.contains(AuthType.KAKAO_TALK)) {
            itemList.add(new Item(com.kakao.usermgmt.R.string.com_kakao_kakaotalk_account, com.kakao.usermgmt.R.drawable.kakaotalk_icon, AuthType.KAKAO_TALK));
        }
        if(authTypes.contains(AuthType.KAKAO_STORY)) {
            itemList.add(new Item(com.kakao.usermgmt.R.string.com_kakao_kakaostory_account, com.kakao.usermgmt.R.drawable.kakaostory_icon, AuthType.KAKAO_STORY));
        }
        if(authTypes.contains(AuthType.KAKAO_ACCOUNT)){
            itemList.add(new Item(com.kakao.usermgmt.R.string.com_kakao_other_kakaoaccount, com.kakao.usermgmt.R.drawable.kakaoaccount_icon, AuthType.KAKAO_ACCOUNT));
        }
        itemList.add(new Item(com.kakao.usermgmt.R.string.com_kakao_account_cancel, 0, null)); //no icon for this one

        final Item[] items = itemList.toArray(new Item[itemList.size()]);

        final ListAdapter adapter = new ArrayAdapter<Item>(
                this,
                android.R.layout.select_dialog_item,
                android.R.id.text1, items){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView tv = (TextView)v.findViewById(android.R.id.text1);

                tv.setText(items[position].textId);
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(15);
                tv.setGravity(Gravity.CENTER);
                if(position == itemList.size() -1) {
                    tv.setBackgroundResource(com.kakao.usermgmt.R.drawable.kakao_cancel_button_background);
                } else {
                    tv.setBackgroundResource(com.kakao.usermgmt.R.drawable.kakao_account_button_background);
                }
                tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);

                int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
                tv.setCompoundDrawablePadding(dp5);

                return v;
            }
        };


        new AlertDialog.Builder(this)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int position) {
                        final AuthType authType = items[position].authType;

                        SmartLog.getInstance().d(TAG, "KAKAO Login List onClick " + authType.toString());

                        if (authType != null) {
                            Session.getCurrentSession().open(authType, Login.this);
                        }

                        dialog.dismiss();
                    }
                }).create().show();

    }

    private static class Item {
        public final int textId;
        public final int icon;
        public final AuthType authType;
        public Item(final int textId, final Integer icon, final AuthType authType) {
            this.textId = textId;
            this.icon = icon;
            this.authType = authType;
        }
    }


    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {

            SmartLog.getInstance().i(TAG, "kakao SessionCallback onSessionOpened redirectSignupActivity");
            SmartLog.getInstance().d(TAG, "kakao onSessionOpened Session.getCurrentSession().getAccessToken() " + Session.getCurrentSession().getAccessToken());


            final String token = Session.getCurrentSession().getAccessToken();
            final String logintype= "k";

            requestMe(new MeResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {

                    String message = "failed to get user info. msg=" + errorResult;

                    SmartLog.getInstance().d(TAG, "kakao requestMe  onFailure " + message);

                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());

                    Toast.makeText(Login.this, "kakao Login fail : " + message, Toast.LENGTH_LONG).show();

                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {

                    SmartLog.getInstance().d(TAG, "kakao requestMe  onSessionClosed ");

                    Toast.makeText(Login.this, "kakao onSessionClosed ", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onSuccess(UserProfile userProfile) {

                    SmartLog.getInstance().d(TAG, "kakao requestMe  onSuccess");

                    String id =  "" + userProfile.getId();
                    String name = userProfile.getNickname();
                    String img = userProfile.getProfileImagePath();

                    showIndicator(false, null);
                    requestLogin(id, logintype, token, name, img, null, null);

                }

                @Override
                public void onNotSignedUp() {

                    SmartLog.getInstance().d(TAG, "kakao requestMe  onNotSignedUp ");

                    Toast.makeText(Login.this, "kakao onNotSignedUp ", Toast.LENGTH_LONG).show();


                }
            }, null, false);

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

            SmartLog.getInstance().i(TAG, "kakao SessionCallback onSessionOpenFailed ");

            if(exception != null) {

                SmartLog.getInstance().i(TAG, "kakao SessionCallback onSessionOpenFailed " + exception.toString());

            }
        }
    }

    public void requestMe(final MeResponseCallback callback, final List<String> propertyKeys, final boolean secureResource) {

        KakaoTaskQueue.getInstance().addTask(new KakaoResultTask<UserProfile>(callback) {
            @Override
            public UserProfile call() throws Exception {
                return UserApi.requestMe(propertyKeys, secureResource).getUserProfile();
            }
        });
    }


    boolean block = false;

    void requestLogin(String id, String login_typ, String token, String name, String img, String phone, String email) {


        if(block) {

            return;

        }
        block = true;

        if(login_typ.equalsIgnoreCase("f")) {

            mUserLoginApi.insertFBUser(id, login_typ, token, name, img, phone, email, new Callback<LoginParserData>() {
                @Override
                public void success(LoginParserData data, Response response) {


                    SmartLog.getInstance().i(TAG, "insertKAKAOUser success ID  " + data.login.ID);

                    if (controlApiError(data)) {

                        getUserInfo(data.login.ID);
                    }
                    else {

                        hideIndicator();

                    }

                    block = false;
                }

                @Override
                public void failure(RetrofitError error) {

                    hideIndicator();
                    block = false;
                }
            });

        }
        else {


            mUserLoginApi.insertKAKAOUser(id, login_typ, token, name, img, phone, email, new Callback<LoginParserData>() {
                @Override
                public void success(LoginParserData data, Response response) {


                    SmartLog.getInstance().i(TAG, "insertKAKAOUser success ID  " + data.login.ID);

                    if (controlApiError(data)) {


                        getUserInfo(data.login.ID);

                    }
                    else {

                        hideIndicator();

                    }

                    block = false;

                }

                @Override
                public void failure(RetrofitError error) {

                    hideIndicator();
                    block = false;
                }
            });

        }


    }

    void getUserInfo(final String id) {

        mUserLoginApi.selectLikeIDUser(id, new Callback<UserParserData>() {
            @Override
            public void success(UserParserData userParserData, Response response) {

                hideIndicator();

                if (controlApiError(userParserData)) {

                    SharedPreferenceManager.getInstance().setUserId(id);
                    Global.getInstance().setUserData(userParserData);
                    IntentManager.getInstance().push(Login.this, Home.class, true);

                }

            }

            @Override
            public void failure(RetrofitError error) {

                hideIndicator();

            }
        });


    }


}