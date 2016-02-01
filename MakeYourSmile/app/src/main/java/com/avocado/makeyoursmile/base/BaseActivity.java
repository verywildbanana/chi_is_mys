package com.avocado.makeyoursmile.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by HDlee on 1/29/16.
 */
public abstract class BaseActivity extends FragmentActivity {

    public final String TAG = this.getClass().getSimpleName().trim();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
