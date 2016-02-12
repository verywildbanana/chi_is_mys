package com.avocado.makeyoursmile.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.ImageView;

import com.avocado.makeyoursmile.util.IntentManager;

/**
 * Created by HDlee on 1/29/16.
 */
public abstract class BaseActivity extends FragmentActivity {

    public final String TAG = this.getClass().getSimpleName().trim();

    @Override
    public void onBackPressed() {

        IntentManager.getInstance().pop(this);

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


    Dialog d;

    public void showDialog(String title, String message, String positiveBtn, String negativeBtn, boolean cancelable, final OnDialogButtonListener listener, int imgRes) {

        if(d !=  null) {

            d.dismiss();

        }

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setCancelable(cancelable);

        if (TextUtils.isEmpty(title) == false) alertDialog.setTitle(title);
        if (TextUtils.isEmpty(message) == false) alertDialog.setMessage(message);

        if (TextUtils.isEmpty(positiveBtn) == false) {

            alertDialog.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (listener != null) listener.onPositiveButtonClick();

                }
            });


        }

        if (TextUtils.isEmpty(negativeBtn) == false) {

            alertDialog.setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (listener != null) listener.onNegativeButtonClick();

                }
            });

        }

        if (imgRes > 0) {

            ImageView image = new ImageView(this);
            image.setImageResource(imgRes);
            alertDialog.setView(image);

        }

        alertDialog.create();
        d = alertDialog.show();
    }

    public interface OnDialogButtonListener {

        void onPositiveButtonClick();

        void onNegativeButtonClick();
    }

}
