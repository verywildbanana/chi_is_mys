package com.avocado.makeyoursmile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.avocado.makeyoursmile.Global;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.view.AVTextView;
import com.avocado.makeyoursmile.view.AVToggleButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Setting extends BaseActivity {


    @Bind(R.id.AppversionTxt)
    protected AVTextView mAppversionTxt;

    @Bind(R.id.PushToggle)
    protected AVToggleButton mPushToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        mAppversionTxt.setText(Global.getInstance().getAppVer());


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

    @OnClick(R.id.TitleSearchImg)
    public void onClickTitleSearch(View v) {


    }

    @OnClick({R.id.NoticeLay, R.id.ExplainLay, R.id.ReqLay, R.id.FAQLay, R.id.TermLay, R.id.PersonalTermLay, R.id.AccountTermLay})
    public void onClickLay(View v) {

        switch (v.getId()) {

            case R.id.NoticeLay:

                break;

            case R.id.ExplainLay:


                break;

            case R.id.ReqLay:

                break;

            case R.id.FAQLay:


                break;

            case R.id.TermLay:

                break;

            case R.id.PersonalTermLay:


                break;


            case R.id.AccountTermLay:

                break;

        }
    }
}
