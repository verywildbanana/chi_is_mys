package com.avocado.makeyoursmile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.avocado.makeyoursmile.MakeYourSmileApp;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.api.DentistApi;
import com.avocado.makeyoursmile.network.data.error.ErrorData;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.view.AVEditText;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by HDlee on 1/29/16.
 */
public class InsertDentist extends BaseActivity {


    @Bind(R.id.IDEdit)
    protected AVEditText mIDEdit;

    @Bind(R.id.PWEdit)
    protected AVEditText mPWEdit;

    @Bind(R.id.DentistNameEdit)
    protected AVEditText mDentistNameEdit;

    @Bind(R.id.ADDR1Edit)
    protected AVEditText mADDR1Edit;

    @Bind(R.id.ADDR2Edit)
    protected AVEditText mADDR2Edit;

    @Bind(R.id.ADDR3Edit)
    protected AVEditText mADDR3Edit;

    @Bind(R.id.ADDR4Edit)
    protected AVEditText mADDR4Edit;

    @Bind(R.id.LatEdit)
    protected AVEditText mLatEdit;

    @Bind(R.id.LngEdit)
    protected AVEditText mLngEdit;

    @Bind(R.id.NumberEdit)
    protected AVEditText mNumberEdit;

    @Bind(R.id.ActTime1Edit)
    protected AVEditText mActTime1Edit;

    @Bind(R.id.ActTime2Edit)
    protected AVEditText mActTime2Edit;

    @Bind(R.id.ActTime3Edit)
    protected AVEditText mActTime3Edit;

    @Bind(R.id.IntroduceDentistEdit)
    protected AVEditText mIntroduceDentistEdit;

    @Bind(R.id.Doctor1NameEdit)
    protected AVEditText mDoctor1NameEdit;

    @Bind(R.id.Doctor1DesEdit)
    protected AVEditText mDoctor1DesEdit;

    @Bind(R.id.Doctor2NameEdit)
    protected AVEditText mDoctor2NameEdit;

    @Bind(R.id.Doctor2DesEdit)
    protected AVEditText mDoctor2DesEdit;

    @Bind(R.id.Doctor3NameEdit)
    protected AVEditText mDoctor3NameEdit;

    @Bind(R.id.Doctor3DesEdit)
    protected AVEditText mDoctor3DesEdit;


    DentistApi dentistApi = MakeYourSmileApp.createApi(DentistApi.class);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isertdentist);
        ButterKnife.bind(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    @OnClick(R.id.ReqBtn)
    public void onClickRegBtn(View v) {

        showIndicator(true, null);


        String ID = mIDEdit.getText().toString();
        String PASSWORD = mPWEdit.getText().toString();
        String DENTIST_NAME = mDentistNameEdit.getText().toString();
        String ADDRESS1 = mADDR1Edit.getText().toString();
        String ADDRESS2 = mADDR2Edit.getText().toString();
        String ADDRESS3 = mADDR3Edit.getText().toString();
        String ADDRESS4 = mADDR4Edit.getText().toString();
        String LAT = mLatEdit.getText().toString();
        String LNG = mLngEdit.getText().toString();
        String PHONE = mNumberEdit.getText().toString();
        String ACT_TIME1 = mActTime1Edit.getText().toString();
        String ACT_TIME2 = mActTime2Edit.getText().toString();
        String ACT_TIME3 = mActTime3Edit.getText().toString();
        String DENTIST_DES = mIntroduceDentistEdit.getText().toString();
        String DOCTOR1_NAME = mDoctor1NameEdit.getText().toString();
        String DOCTOR1_DES = mDoctor1DesEdit.getText().toString();
        String DOCTOR2_NAME = mDoctor2NameEdit.getText().toString();
        String DOCTOR2_DES = mDoctor2DesEdit.getText().toString();
        String DOCTOR3_NAME = mDoctor3NameEdit.getText().toString();
        String DOCTOR3_DES = mDoctor3DesEdit.getText().toString();

        dentistApi.insertDentist(ID, PASSWORD, DENTIST_NAME, ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, LAT, LNG, PHONE, ACT_TIME1, ACT_TIME2, ACT_TIME3,
                DENTIST_DES, DOCTOR1_NAME, DOCTOR1_DES, DOCTOR2_NAME, DOCTOR2_DES, DOCTOR3_NAME, DOCTOR3_DES, new Callback<ErrorData>() {
                    @Override
                    public void success(ErrorData s, Response response) {

                        hideIndicator();
                        if(controlApiError(s)) {

                            IntentManager.getInstance().push(InsertDentist.this, InsertDentistImg.class, true);

                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        hideIndicator();

                    }
                });

    }

}
