package com.avocado.makeyoursmile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.ui.ask.Estimate;
import com.avocado.makeyoursmile.util.IntentManager;

import butterknife.ButterKnife;

/**
 * Created by HDlee on 1/29/16.
 */
public class Start extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                IntentManager.getInstance().push(Start.this, Estimate.class, true);


            }
        }, 1000);
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

}
