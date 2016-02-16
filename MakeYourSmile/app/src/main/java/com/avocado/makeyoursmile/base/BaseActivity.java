package com.avocado.makeyoursmile.base;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.avocado.makeyoursmile.Constants;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.util.SmartLog;

import butterknife.ButterKnife;

/**
 * Created by HDlee on 1/29/16.
 */
public abstract class BaseActivity extends FragmentActivity {

    public final String TAG = this.getClass().getSimpleName().trim();
    boolean mReceiverBlock = false;
    private String mLastActionName = "";
    private BaseReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiver();

    }

        @Override
    public void onBackPressed() {

        IntentManager.getInstance().pop(this);

    }
    @Override
    protected void onDestroy() {


        ButterKnife.unbind(this);
        unRegisterReceiver();
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

    protected AlertDialog mCreateAlertDialog;

    public void showListDialog(String title, String message, String positiveBtn, String negativeBtn, boolean cancelable, String[] menu,
                               final OnDialogButtonListener btnListener, final OnListDialogButtonListener listListener) {

        mCreateAlertDialog = null;

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setCancelable(cancelable);

        if (TextUtils.isEmpty(title) == false) alertDialog.setTitle(title);
        if (TextUtils.isEmpty(message) == false) alertDialog.setMessage(message);

        if (TextUtils.isEmpty(positiveBtn) == false) {

            alertDialog.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (btnListener != null) btnListener.onPositiveButtonClick();

                }
            });


        }

        if (TextUtils.isEmpty(negativeBtn) == false) {

            alertDialog.setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (btnListener != null) btnListener.onNegativeButtonClick();

                }
            });

        }

        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(this, R.layout.listitem_dialog_listview, menu);

        LayoutInflater inflater = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        ListView listView = (ListView) inflater.inflate(R.layout.view_listview, null, false);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setItemChecked(0, true);
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (listListener != null) {

                    listListener.onListClick(position);
                }

                mCreateAlertDialog.dismiss();
            }
        });

        alertDialog.setView(listView);
        mCreateAlertDialog = alertDialog.create();
        mCreateAlertDialog.show();


    }

    public interface OnListDialogButtonListener {

        void onListClick(int position);
    }

    public interface OnDialogButtonListener {

        void onPositiveButtonClick();

        void onNegativeButtonClick();
    }

    public void registerReceiver() {

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_FINISH);
        filter.addAction(Constants.ACTION_LOGIN);
        filter.addAction(Constants.ACTION_LOGOUT);
        filter.addAction(Constants.ACTION_GOMAIN);

        mReceiver = new BaseReceiver();
        registerReceiver(mReceiver, filter, Constants.BROADCAST_PERMISSION, null);

    }

    public void unRegisterReceiver() {

        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }


    private class BaseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {


            String action = intent.getAction();

            SmartLog.getInstance().i(TAG, "BaseReceiver onReceive " + action);

            if (mReceiverBlock && action.equals(mLastActionName)) {
                return;

            }

            mLastActionName = action;
            mReceiverBlock = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {

                    mReceiverBlock = false;

                }
            }, 3000);


            if (Constants.ACTION_FINISH.equals(action)) {

                finish();
            }
            else if (Constants.ACTION_LOGOUT.equals(action)) {

                onLogout();


            }
        }
    }



    public void onUpdateMainViewpager() {


    }

    public void onUpdate() {


    }

    public void onLogout() {


    }

}
