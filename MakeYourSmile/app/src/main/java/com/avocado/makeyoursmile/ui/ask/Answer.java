package com.avocado.makeyoursmile.ui.ask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.ui.Doctor;
import com.avocado.makeyoursmile.ui.search.Dentistry;
import com.avocado.makeyoursmile.util.IntentManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Answer extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
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

    @OnClick(R.id.TitleLeftImg)
    public void onClickTitleLeft(View v) {

        onBackPressed();

    }

    @OnClick(R.id.TitleSearchImg)
    public void onClickTitleSearch(View v) {

        IntentManager.getInstance().push(this, Dentistry.class, true);
    }

    @OnClick({R.id.IntroduceDoctorImg, R.id.MapImg, R.id.AskImg, R.id.EventImg})
    public void onClickLay(View v) {

        switch (v.getId()) {

            case R.id.IntroduceDoctorImg:

                IntentManager.getInstance().push(Answer.this, Doctor.class, true);
                break;

            case R.id.MapImg:


                break;

            case R.id.AskImg:

                break;

            case R.id.EventImg:


                break;
        }
    }
}
