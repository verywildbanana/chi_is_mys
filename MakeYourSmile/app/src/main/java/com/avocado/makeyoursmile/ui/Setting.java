package com.avocado.makeyoursmile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.avocado.makeyoursmile.Global;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.ui.alarm.Notice;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.view.AVTextView;

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
    protected CheckBox mPushToggle;


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

                IntentManager.getInstance().push(Setting.this, Notice.class, true);

                break;

            case R.id.ExplainLay:


                break;

            case R.id.ReqLay:

                IntentManager.getInstance().push(Setting.this, HostJoin.class, true);

                break;

            case R.id.FAQLay:


                break;

            case R.id.TermLay:

                IntentManager.getInstance().push(Setting.this, Terms.class, true);

                break;

            case R.id.PersonalTermLay:

                IntentManager.getInstance().push(Setting.this, Terms.class, true);

                break;


            case R.id.AccountTermLay:

                break;

        }
    }
}
