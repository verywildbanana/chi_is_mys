package com.avocado.makeyoursmile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.avocado.makeyoursmile.Constants;
import com.avocado.makeyoursmile.MakeYourSmileApp;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.api.DentistApi;
import com.avocado.makeyoursmile.network.data.dentist.DentistData;
import com.avocado.makeyoursmile.network.data.dentist.DentistListParserData;
import com.avocado.makeyoursmile.ui.ask.Estimate;
import com.avocado.makeyoursmile.ui.photo.Letchiis;
import com.avocado.makeyoursmile.ui.search.Dentistry;
import com.avocado.makeyoursmile.ui.search.DentistryList;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.util.SmartLog;
import com.avocado.makeyoursmile.util.ToastManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by HDlee on 1/29/16.
 */
public class Home extends BaseActivity {

    @Bind(R.id.ListRecyclerView)
    protected RecyclerView mListRecyclerView;

    HomeListAdapter mListAdapter;

    private Handler mFinishHandler;
    private boolean mIsFinish = false;

    DentistApi dentistApi = MakeYourSmileApp.createApi(DentistApi.class);

    public ArrayList<DentistData> mDentistData = new ArrayList<DentistData>();

    LinearLayoutManager mLayoutManager;

    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;
    private boolean loading = false;
    private int reqProgramsAvailablePage = 0;
    private int reqProgramsAvailableSize = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mListRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mListRecyclerView.setLayoutManager(mLayoutManager);
        mListRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mListAdapter = new HomeListAdapter(this, new ArrayList<DentistData>()) {
            @Override
            public void onClick(View view) {
                super.onClick(view);

                if(view.getTag() != null) {

                    DentistData data = (DentistData) view.getTag();
                    String id = data.ID;
                    IntentManager.getInstance().putExtra(IntentManager.EXTRA_ID, id);
                }

                IntentManager.getInstance().push(Home.this, Landing.class, true);

            }
        };

        mListRecyclerView.setAdapter(mListAdapter);

        mListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {

                            loading = false;
                            reqProgramsAvailablePage = reqProgramsAvailablePage + 1;
                            reqDentistList();

                        }
                    }
                }
            }
        });


        mFinishHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    mIsFinish = false;
                }
            }
        };

        showIndicator(true, null);

       reqDentistList();

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

    @Override
    public void onBackPressed() {


        if (mIsFinish == false) {

            String shutDown = "한번 더 누르시면 종료됩니다.";
            ToastManager.getInstance().show(shutDown, Toast.LENGTH_SHORT);
            mIsFinish = true;
            mFinishHandler.sendEmptyMessageDelayed(0, 2000);

        } else {


            SmartLog.getInstance().i(TAG, "Home onBackPressed");

            Intent intent = new Intent(Constants.ACTION_FINISH);
            sendBroadcast(intent);

        }


    }


    @OnClick(R.id.TitleSearchImg)
    public void onClickTitleSearch(View v) {

        IntentManager.getInstance().push(this, Dentistry.class, true);
    }


    @OnClick({R.id.ToothTheme1, R.id.ToothTheme2, R.id.ToothTheme3, R.id.ToothTheme4, R.id.QueryBtn, R.id.PictureBtn, R.id.EventBtn, R.id.SettingBtn })
    public void onClickLay(View v) {

        switch (v.getId()) {

            case R.id.ToothTheme1:

                IntentManager.getInstance().push(Home.this, DentistryList.class, true);

                break;

            case R.id.ToothTheme2:


                break;

            case R.id.ToothTheme3:

                break;

            case R.id.ToothTheme4:


                break;
            case R.id.QueryBtn:

                IntentManager.getInstance().push(Home.this, Estimate.class, true);

                break;
            case R.id.PictureBtn:

                IntentManager.getInstance().push(Home.this, Letchiis.class, true);

                break;
            case R.id.EventBtn:

                break;

            case R.id.SettingBtn:

                IntentManager.getInstance().push(Home.this, Setting.class, true);

                break;
        }
    }

    void reqDentistList() {


        dentistApi.getDentistList(reqProgramsAvailablePage, reqProgramsAvailableSize, new Callback<DentistListParserData>() {
            @Override
            public void success(DentistListParserData data, Response response) {

                hideIndicator();

                if(controlApiError(data)) {

                    loading = data.NEXT;
                    mDentistData.addAll(data.dentist);

                    mListAdapter.setData(mDentistData);
                }


            }

            @Override
            public void failure(RetrofitError error) {

                hideIndicator();

            }
        });


    }


}
