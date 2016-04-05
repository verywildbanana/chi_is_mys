package com.avocado.makeyoursmile.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.avocado.makeyoursmile.Constants;
import com.avocado.makeyoursmile.Global;
import com.avocado.makeyoursmile.MakeYourSmileApp;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.network.data.error.ErrorData;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.util.SmartLog;
import com.avocado.makeyoursmile.util.ToastManager;

import butterknife.ButterKnife;

/**
 * Created by HDlee on 1/29/16.
 */
public abstract class BaseActivity extends FragmentActivity {

    public final String TAG = this.getClass().getSimpleName().trim();
    boolean mReceiverBlock = false;
    private String mLastActionName = "";
    private BaseReceiver mReceiver;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        if (Global.getInstance().getSdkInt() >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.mys_titlebar_0));

        }

    }

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
        clearReferences();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MakeYourSmileApp.setCurrentActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    private void clearReferences() {
        Activity currActivity = MakeYourSmileApp.getCurrentActivity();
        if (currActivity != null && currActivity.equals(this)) {
            MakeYourSmileApp.setCurrentActivity(null);
        }
    }

    Dialog d;

    public void showDialog(String title, String message, String positiveBtn, String negativeBtn, boolean cancelable, final OnDialogButtonListener listener, int imgRes) {

        if (d != null) {

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

        SmartLog.getInstance().i(TAG, "Base registerReceiver()");

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_FINISH);
        filter.addAction(Constants.ACTION_LOGIN);
        filter.addAction(Constants.ACTION_LOGOUT);
        filter.addAction(Constants.ACTION_GOMAIN);

        mReceiver = new BaseReceiver();
        registerReceiver(mReceiver, filter);

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

            SmartLog.getInstance().i(TAG, "BroadcastReceiver onReceive " + action);

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


                SmartLog.getInstance().i(TAG, "BroadcastReceiver Constants.ACTION_FINISH");

                finish();
            } else if (Constants.ACTION_LOGOUT.equals(action)) {

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


    ViewGroup mFrameIndicator;


    public void showIndicator(boolean isTouchBlock, String text) {


        if (mFrameIndicator == null) {
            Window w = getWindow();

            ViewGroup view = (ViewGroup) w.getDecorView();

            mFrameIndicator = (ViewGroup) LayoutInflater.from(this).inflate(
                    R.layout.view_indicator_spinner, view, false);

            // 스피너 색상 변경
            //			ProgressBar spinner = (ProgressBar)mFrameIndicator.findViewById(R.id.ProgressBar02);
            //			spinner.getIndeterminateDrawable().setColorFilter(
            //					0xffeb5340, Mode.MULTIPLY);

            if (isTouchBlock == true) {
                mFrameIndicator.setClickable(true);
            }


            w.addContentView(mFrameIndicator, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            //			ViewAnimationUtil.waveScaleAnimation(this, spinner, null);
        }
    }

    public void hideIndicator() {

        if (mFrameIndicator != null) {
            // 기존 Pickat 1.x
            //			if ((ViewGroup) mFrameIndicator.getParent() != null) {
            //
            //				mFrameIndicator.setVisibility(View.GONE);
            //
            //				if (((ViewGroup) mFrameIndicator.getParent()).indexOfChild(mFrameIndicator) >= 0) {
            //
            //					((ViewGroup) mFrameIndicator.getParent()).removeView(mFrameIndicator);
            //				}
            //				mFrameIndicator = null;
            //			}
            // 변경
            ViewGroup parent = (ViewGroup) mFrameIndicator.getParent();
            if (parent != null && parent.getChildCount() > 0) {
                parent.removeView(mFrameIndicator);
                mFrameIndicator = null;
            }
        }
    }


    public boolean controlApiError(final ErrorData errorData) {

        if (errorData != null) {

            if (errorData.mCode.equals(Constants.API_ERROR_CODE_REQ_UPDTE_APP)) {

                //강제 업데이트
                String message = errorData.getDpMsg();
                String positive = "확인";

                showDialog(null, message, positive, null, false, new BaseActivity.OnDialogButtonListener() {
                    @Override
                    public void onPositiveButtonClick() {

                        if (errorData.update_info.update_url != null) {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(errorData.update_info.update_url));
                            startActivity(intent);

                        }
                        sendBroadcast(new Intent(Constants.ACTION_FINISH));

                    }

                    @Override
                    public void onNegativeButtonClick() {

                    }
                }, 0);

                return false;
            } else if (errorData.mCode.contains("401.")) {

                ToastManager.getInstance().show(errorData.getDpMsg());
                return false;
            }

        }

        return true;

    }

}
