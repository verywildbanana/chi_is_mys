package com.avocado.makeyoursmile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;

import com.avocado.makeyoursmile.MakeYourSmileApp;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.api.DentistApi;
import com.avocado.makeyoursmile.network.data.error.ErrorData;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.util.SmartLog;
import com.avocado.makeyoursmile.util.ToastManager;
import com.avocado.makeyoursmile.view.AVEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by HDlee on 1/29/16.
 */
public class InsertDentistThemesHashTags extends BaseActivity {


    @Bind(R.id.IDEdit)
    protected AVEditText mIDEdit;

    @Bind(R.id.Toggle1)
    protected CheckBox mToggle1;

    @Bind(R.id.Toggle2)
    protected CheckBox mToggle2;

    @Bind(R.id.Toggle3)
    protected CheckBox mToggle3;

    @Bind(R.id.Toggle4)
    protected CheckBox mToggle4;


    DentistApi dentistApi = MakeYourSmileApp.createApi(DentistApi.class);

    String mDentistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isertdentist_themes_hashtags);
        ButterKnife.bind(this);


        Intent t = getIntent();

        if (t != null) {

            mDentistId = t.getStringExtra(IntentManager.EXTRA_ID);
        }

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
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick(R.id.TitleLeftImg)
    public void onClickTitleLeft(View v) {

        onBackPressed();

    }

    @OnClick(R.id.ReqTagBtn)
    public void onClickReqTagBtn(View v) {

        if (TextUtils.isEmpty(mIDEdit.getText().toString()) == false) {

            showIndicator(true, null);

            dentistApi.updateDentistHashTags(mDentistId, mIDEdit.getText().toString(), new Callback<ErrorData>() {
                @Override
                public void success(ErrorData s, Response response) {

                    hideIndicator();

                    if (controlApiError(s)) {


                    }
                }

                @Override
                public void failure(RetrofitError error) {

                    hideIndicator();

                }
            });

        } else {


            ToastManager.getInstance().show("해쉬테그를 입력해 주세요.");

        }


    }


    @OnClick(R.id.ReqThemeBtn)
    public void onClickReqThemeBtn(View v) {


        showIndicator(true, null);

        String ch1 = "N";
        String ch2 = "N";
        String ch3 = "N";
        String ch4 = "N";


        if(mToggle1.isChecked()) {

            ch1 = "Y";
        }

        if(mToggle2.isChecked()) {

            ch2 = "Y";
        }

        if(mToggle3.isChecked()) {

            ch3 = "Y";
        }


        if(mToggle4.isChecked()) {

            ch4 = "Y";
        }


        SmartLog.getInstance().d(TAG, "onClickReqThemeBtn  " + ch1 + ", " + ch2 + ", " + ch3+ ", " + ch4 );


        dentistApi.updateDentistThemes(mDentistId, ch1, ch2, ch3, ch4, new Callback<ErrorData>() {
            @Override
            public void success(ErrorData s, Response response) {

                hideIndicator();

                if (controlApiError(s)) {

                    IntentManager.getInstance().putExtra(IntentManager.EXTRA_ID, mDentistId);
                    IntentManager.getInstance().push(InsertDentistThemesHashTags.this, InsertDentistImg.class, true);
                }
            }

            @Override
            public void failure(RetrofitError error) {

                hideIndicator();

            }
        });


    }

}
