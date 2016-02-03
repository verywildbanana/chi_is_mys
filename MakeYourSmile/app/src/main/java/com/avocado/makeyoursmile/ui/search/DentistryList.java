package com.avocado.makeyoursmile.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class DentistryList extends BaseActivity {


    @Bind(R.id.ListRecyclerView)
    protected RecyclerView mListRecyclerView;

    DentistryListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dentistrylist);
        ButterKnife.bind(this);

        mListRecyclerView.setHasFixedSize(true);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mListAdapter = new DentistryListAdapter(this, new ArrayList<String>()){
            @Override
            public void onClick(View view) {
                super.onClick(view);
            }
        };

        mListRecyclerView.setAdapter(mListAdapter);

        ArrayList<String> data = new ArrayList<>();
        data.add("지엔 치과1");
        data.add("지엔 치과2");
        data.add("지엔 치과3");
        data.add("지엔 치과4");
        data.add("지엔 치과5");
        data.add("지엔 치과6");
        data.add("지엔 치과7");
        data.add("지엔 치과8");

        mListAdapter.setData(data);


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


    }

}
