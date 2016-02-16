package com.avocado.makeyoursmile.ui.ask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.data.host.Answer;
import com.avocado.makeyoursmile.network.data.user.Ask;
import com.avocado.makeyoursmile.ui.search.Dentistry;
import com.avocado.makeyoursmile.util.IntentManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Estimate extends BaseActivity {


    @Bind(R.id.ListRecyclerView)
    protected RecyclerView mListRecyclerView;

    protected EstimateAdapter mEstimateAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_estimate);
        ButterKnife.bind(this);

        mListRecyclerView.setHasFixedSize(true);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListRecyclerView.setItemAnimator(new DefaultItemAnimator());


        Ask headerData = new Ask();
        headerData.mAddress = "강남구 강남구 강남구";

        mEstimateAdapter = new EstimateAdapter(this, new ArrayList<Answer>()) {

            @Override
            public void onClick(View view) {
                super.onClick(view);

                IntentManager.getInstance().push(Estimate.this, com.avocado.makeyoursmile.ui.ask.Answer.class, true);

                }

        };
        mEstimateAdapter.addHeaderView(R.layout.listitem_header_ask_estimate, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, headerData);

        mListRecyclerView.setAdapter(mEstimateAdapter);

        ArrayList<Answer> listData = new ArrayList<>();
        Answer data = new Answer();
        data.mStoreName = "지엔 치과1";
        listData.add(data);

        data = new Answer();
        data.mStoreName = "지엔 치과2";
        listData.add(data);

        data = new Answer();
        data.mStoreName = "지엔 치과3";
        listData.add(data);

        mEstimateAdapter.setData(listData);
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

    @OnClick(R.id.AskBtLogoImg)
    public void onClickAsk(View v) {



    }




    @OnClick(R.id.AskBtLogoImg)
    public void onClickLay(View v) {

        switch (v.getId()) {

            case R.id.AskBtLogoImg:

                IntentManager.getInstance().push(Estimate.this, Request.class, true);

                break;
        }
    }

}
