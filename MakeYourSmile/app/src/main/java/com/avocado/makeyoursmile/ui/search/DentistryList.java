package com.avocado.makeyoursmile.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.avocado.makeyoursmile.MakeYourSmileApp;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.api.DentistApi;
import com.avocado.makeyoursmile.network.data.dentist.DentistData;
import com.avocado.makeyoursmile.network.data.dentist.DentistListParserData;
import com.avocado.makeyoursmile.ui.Landing;
import com.avocado.makeyoursmile.util.IntentManager;

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
public class DentistryList extends BaseActivity {


    public static final String THEME_1 = "THEME_1";
    public static final String THEME_2 = "THEME_2";
    public static final String THEME_3 = "THEME_3";
    public static final String THEME_4 = "THEME_4";

    @Bind(R.id.ListRecyclerView)
    protected RecyclerView mListRecyclerView;

    @Bind(R.id.Tooth1BottomLine)
    ImageView mTooth1BottomLine;

    @Bind(R.id.Tooth2BottomLine)
    ImageView mTooth2BottomLine;

    @Bind(R.id.Tooth3BottomLine)
    ImageView mTooth3BottomLine;

    @Bind(R.id.Tooth4BottomLine)
    ImageView mTooth4BottomLine;


    ArrayList<DentistData> mDentistDataList = new ArrayList<DentistData>();

    DentistryListAdapter mListAdapter;

    DentistApi dentistApi = MakeYourSmileApp.createApi(DentistApi.class);

    String mSelectThemeType;

    LinearLayoutManager mLayoutManager;

    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;
    private boolean loading = false;
    private int reqProgramsAvailablePage = 0;
    private int reqProgramsAvailableSize = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dentistrylist);
        ButterKnife.bind(this);

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

                        IntentManager.getInstance().push(DentistryList.this, Landing.class, true);

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
                            reqDentistList(mSelectThemeType);

                        }
                    }
                }
            }
        });

        Intent t = getIntent();

        if(t != null) {

            String type = t.getStringExtra(IntentManager.EXTRA_TYPE);
            reqDentistList(type);
        }


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

    @OnClick({R.id.Tooth1Txt, R.id.Tooth2Txt, R.id.Tooth3Txt, R.id.Tooth4Txt })
    public void onClickLay(View v) {

        switch (v.getId()) {

            case R.id.Tooth1Txt:

                reqDentistList(THEME_1);

                break;

            case R.id.Tooth2Txt:

                reqDentistList(THEME_2);

                break;

            case R.id.Tooth3Txt:

                reqDentistList(THEME_3);

                break;


            case R.id.Tooth4Txt:

                reqDentistList(THEME_4);

                break;
        }


    }


    void reqDentistList(String type) {


        if(TextUtils.isEmpty(mSelectThemeType) || type.equalsIgnoreCase(mSelectThemeType) == false) {

            loading = false;
            mDentistDataList.clear();
            showIndicator(true, null);

        }

        mTooth1BottomLine.setVisibility(View.INVISIBLE);
        mTooth2BottomLine.setVisibility(View.INVISIBLE);
        mTooth3BottomLine.setVisibility(View.INVISIBLE);
        mTooth4BottomLine.setVisibility(View.INVISIBLE);

        if(type.equals(THEME_1)) {

            mTooth1BottomLine.setVisibility(View.VISIBLE);

        }
        else if(type.equals(THEME_2)) {

            mTooth2BottomLine.setVisibility(View.VISIBLE);

        }
        else if(type.equals(THEME_3)) {

            mTooth3BottomLine.setVisibility(View.VISIBLE);

        }
        else if(type.equals(THEME_4)) {

            mTooth4BottomLine.setVisibility(View.VISIBLE);

        }

        dentistApi.getDentistThemeList(type, reqProgramsAvailablePage, reqProgramsAvailableSize, new Callback<DentistListParserData>() {
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

            }

            @Override
            public void failure(RetrofitError error) {

                hideIndicator();

            }
        });


        mSelectThemeType =  type;

    }


}
