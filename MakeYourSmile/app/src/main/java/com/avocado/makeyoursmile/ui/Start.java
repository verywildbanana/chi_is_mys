package com.avocado.makeyoursmile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.ui.alarm.Notice;
import com.avocado.makeyoursmile.ui.ask.Answer;
import com.avocado.makeyoursmile.ui.ask.Estimate;
import com.avocado.makeyoursmile.ui.ask.Request;
import com.avocado.makeyoursmile.ui.photo.Letchiis;
import com.avocado.makeyoursmile.ui.search.Dentistry;
import com.avocado.makeyoursmile.ui.search.DentistryList;
import com.avocado.makeyoursmile.util.IntentManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Start extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);


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

    @OnClick({R.id.Click1, R.id.Click2, R.id.Click3, R.id.Click4, R.id.Click5, R.id.Click6, R.id.Click7, R.id.Click8, R.id.Click9,
              R.id.Click10, R.id.Click11, R.id.Click12, R.id.Click13, R.id.Click14, R.id.Click15})
    public void onClickLay(View v) {

        switch (v.getId()) {

            case R.id.Click1:

                IntentManager.getInstance().push(Start.this, Home.class, true);

                break;

            case R.id.Click2:

                IntentManager.getInstance().push(Start.this, Estimate.class, true);

                break;

            case R.id.Click3:

                IntentManager.getInstance().push(Start.this, Setting.class, true);

                break;

            case R.id.Click4:

                IntentManager.getInstance().push(Start.this, Dentistry.class, true);

                break;


            case R.id.Click5:

                IntentManager.getInstance().push(Start.this, DentistryList.class, true);

                break;


            case R.id.Click6:

                IntentManager.getInstance().push(Start.this, Landing.class, true);

                break;


            case R.id.Click7:

                IntentManager.getInstance().push(Start.this, Request.class, true);

                break;


            case R.id.Click8:

                IntentManager.getInstance().push(Start.this, Answer.class, true);

                break;

            case R.id.Click9:

                IntentManager.getInstance().push(Start.this, Notice.class, true);

                break;


            case R.id.Click10:

                IntentManager.getInstance().push(Start.this, HostJoin.class, true);

                break;


            case R.id.Click11:

                IntentManager.getInstance().push(Start.this, Doctor.class, true);

                break;

            case R.id.Click12:

                IntentManager.getInstance().push(Start.this, EstimateLogin.class, true);

                break;

            case R.id.Click13:

                IntentManager.getInstance().push(Start.this, Letchiis.class, true);

                break;

            case R.id.Click14:

                 IntentManager.getInstance().push(Start.this, Terms.class, true);


                break;


            case R.id.Click15:

                IntentManager.getInstance().push(Start.this, InsertDentist.class, true);


                break;





        }
    }


}
