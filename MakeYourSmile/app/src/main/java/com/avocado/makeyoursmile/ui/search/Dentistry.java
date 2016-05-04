package com.avocado.makeyoursmile.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.avocado.makeyoursmile.MakeYourSmileApp;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.api.DentistApi;
import com.avocado.makeyoursmile.network.data.dentist.DentistData;
import com.avocado.makeyoursmile.network.data.dentist.DentistListParserData;
import com.avocado.makeyoursmile.ui.Landing;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.util.SmartLog;
import com.avocado.makeyoursmile.view.AVEditText;
import com.avocado.makeyoursmile.view.AVTextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
public class Dentistry extends BaseActivity {

    public final static String SEARCH_TYPE_TAG = "TAG";
    public final static String SEARCH_TYPE_KEYWORD = "KEYWORD";


    @Bind(R.id.SearchEdit)
    protected AVEditText mSearchEdit;

    @Bind(R.id.TagText1)
    protected AVTextView mTagText1;

    @Bind(R.id.TagText2)
    protected AVTextView mTagText2;

    @Bind(R.id.TagText3)
    protected AVTextView mTagText3;


    @Bind(R.id.ListRecyclerView)
    protected RecyclerView mListRecyclerView;


    DentistApi dentistApi = MakeYourSmileApp.createApi(DentistApi.class);

    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;
    private boolean loading = false;
    private int reqProgramsAvailablePage = 0;
    private int reqProgramsAvailableSize = 20;


    ArrayList<DentistData> mDentistDataList = new ArrayList<DentistData>();

    DentistryListAdapter mListAdapter;
    LinearLayoutManager mLayoutManager;

    String mSelectSearchType;
    String mSelectSearchContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dentistry);
        ButterKnife.bind(this);

        mSearchEdit.postDelayed(new Runnable() {
            @Override
            public void run() {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mSearchEdit, InputMethodManager.SHOW_FORCED);

            }
        }, 500);
        mSearchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (KeyEvent.KEYCODE_ENTER == keyCode) {

                    reqSearchDentistList(SEARCH_TYPE_KEYWORD, true, mSearchEdit.getText().toString());

                }

                return false;
            }
        });

        mListRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mListRecyclerView.setLayoutManager(mLayoutManager);
        mListRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mListAdapter = new DentistryListAdapter(this, new ArrayList<DentistData>()){
            @Override
            public void onClick(View view) {
                super.onClick(view);

                switch (view.getId()) {

                    case R.id.LandingBntImg:

                        if(view.getTag() != null) {

                            DentistData data = (DentistData) view.getTag();
                            String id = data.ID;
                            IntentManager.getInstance().putExtra(IntentManager.EXTRA_ID, id);
                        }

                        IntentManager.getInstance().push(Dentistry.this, Landing.class, true);

                        break;
                }

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
                            reqSearchDentistList(mSelectSearchType, false, mSelectSearchContents);

                        }
                    }
                }
            }
        });


    }

    @Override
    public void onBackPressed() {


        SmartLog.getInstance().d(TAG, "onBackPressed ");

        super.onBackPressed();
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

    @OnClick(R.id.SearchImg)
    public void onClickTitleSearch(View v) {


        if (TextUtils.isEmpty(mSearchEdit.getText().toString()) == false) {

            reqSearchDentistList(SEARCH_TYPE_KEYWORD, true, mSearchEdit.getText().toString());
        }
    }


    @OnClick({R.id.TagLay1, R.id.TagLay2, R.id.TagLay3})
    public void onClickTagsh(View v) {

        String query = null;

        switch (v.getId()) {

            case R.id.TagLay1:
                query = mTagText1.getText().toString();

                break;

            case R.id.TagLay2:

                query = mTagText2.getText().toString();
                break;


            case R.id.TagLay3:

                query = mTagText3.getText().toString();
                break;

        }

        reqSearchDentistList(SEARCH_TYPE_TAG, true, query);
    }

    boolean block = false;

    void reqSearchDentistList(String type, boolean init, String searchContents) {

        if(block) return;

        block = true;

        if(init) {


            visibleItemCount = 0;
            totalItemCount = 0;
            pastVisiblesItems = 0;
            loading = false;
            reqProgramsAvailablePage = 0;
            reqProgramsAvailableSize = 20;
            mDentistDataList.clear();

            showIndicator(true, null);


        }

        mSearchEdit.postDelayed(new Runnable() {
            @Override
            public void run() {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchEdit.getWindowToken(), 0);

            }
        }, 100);


        String query = null;

        try {

            query = URLEncoder.encode(searchContents, "UTF-8");

        } catch (UnsupportedEncodingException e) {

            block = false;
            e.printStackTrace();
        }

        dentistApi.searchDentistList(type, query, reqProgramsAvailablePage, reqProgramsAvailableSize, new Callback<DentistListParserData>() {
            @Override
            public void success(DentistListParserData data, Response response) {

                hideIndicator();

                if(data.dentist == null || data.dentist.isEmpty()) {

                    mListAdapter.setData(mDentistDataList);

                }
                else {

                    mDentistDataList.addAll(data.dentist);
                    mListAdapter.setData(mDentistDataList);

                }

                if(controlApiError(data)) {

                    loading = data.NEXT;
                }


                block = false;

            }

            @Override
            public void failure(RetrofitError error) {

                hideIndicator();

                block = false;

            }
        });

        mSelectSearchType = type;
        mSelectSearchContents = searchContents;
    }



}
