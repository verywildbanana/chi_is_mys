package com.avocado.makeyoursmile.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.util.IntentManager;
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

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Login extends BaseActivity {


    private CallbackManager mCallbackManager;
    private ProfileTracker profileTracker;
    private AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        Profile profile = Profile.getCurrentProfile();

                        SmartLog.getInstance().d(TAG, "FACEBOOK LoginManager onSuccess getToken " + loginResult.getAccessToken().getToken());
                        SmartLog.getInstance().d(TAG, "FACEBOOK LoginManager onSuccess getRecentlyGrantedPermissions " + loginResult.getRecentlyGrantedPermissions());
                        SmartLog.getInstance().d(TAG, "FACEBOOK LoginManager onSuccess profile " + profile.getName());

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {

                                        SmartLog.getInstance().d(TAG, "FACEBOOK newMeRequest onCompleted object " + object.toString());
                                        SmartLog.getInstance().d(TAG, "FACEBOOK newMeRequest onCompleted response " + response.toString());

                                    }

                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();


                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(Login.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(Login.this, exception.getMessage(), Toast.LENGTH_LONG).show();
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


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (mCallbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

    @OnClick(R.id.SkipImg)
    public void onClicSkip(View v) {

        IntentManager.getInstance().push(this, Start.class, true);

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
}