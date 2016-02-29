package com.avocado.makeyoursmile.ui.photo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.data.image.ImageData;
import com.avocado.makeyoursmile.network.data.image.ImageDetailGoupData;
import com.avocado.makeyoursmile.network.data.image.ImageSubData;
import com.avocado.makeyoursmile.ui.ask.Request;
import com.avocado.makeyoursmile.ui.search.Dentistry;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.view.AVTextView;
import com.avocado.makeyoursmile.view.AVToggleButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Letchiis extends BaseActivity {


    @Bind(R.id.ImgListViewPage)
    protected ViewPager mImgListViewPage;

    protected AVTextView mTitleTxt;
    protected AVTextView mSubTxt;

    ImageView mList1List;
    TextView mCountTxt1;
    AVToggleButton mToggleLike1;

    ImageView mList2List;
    TextView mCountTxt2;
    AVToggleButton mToggleLike2;

    ImageView mList3List;
    TextView mCountTxt3;
    AVToggleButton mToggleLike3;


    ViewPagerAdapter mViewPagerAdapter;

    ImageData mImgDataOne = new ImageData();

    ExpandableListView mExpandableListViewOne;

    ExpandableListAdapter mExpandableListAdapterOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_letchiis);
        ButterKnife.bind(this);

        View header = findViewById(R.id.ImgListHeaderLay);

        mTitleTxt = (AVTextView) header.findViewById(R.id.TitleTxt);
        mSubTxt = (AVTextView) header.findViewById(R.id.SubTxt);

        View group = findViewById(R.id.ImgListGroupLay);

        mList1List = (ImageView) group.findViewById(R.id.List1List);
        mCountTxt1 = (TextView) group.findViewById(R.id.CountTxt1);
        mToggleLike1 = (AVToggleButton) group.findViewById(R.id.ToggleLike1);
        group.findViewById(R.id.RankinImg1).setVisibility(View.VISIBLE);

        mList2List = (ImageView) group.findViewById(R.id.List2List);
        mCountTxt2 = (TextView) group.findViewById(R.id.CountTxt2);
        mToggleLike2 = (AVToggleButton) group.findViewById(R.id.ToggleLike2);
        group.findViewById(R.id.RankinImg2).setVisibility(View.VISIBLE);

        mList3List = (ImageView) group.findViewById(R.id.List3List);
        mCountTxt3 = (TextView) group.findViewById(R.id.CountTxt3);
        mToggleLike3 = (AVToggleButton) group.findViewById(R.id.ToggleLike3);
        group.findViewById(R.id.RankinImg3).setVisibility(View.VISIBLE);

        mTitleTxt.setText("좋아요 Best");
        mSubTxt.setText("접수중");

        mCountTxt1.setText("" + 1001);
        mList1List.setImageResource(R.drawable.photo1);

        mCountTxt2.setText("" + 1002);
        mList2List.setImageResource(R.drawable.photo2);

        mCountTxt3.setText("" + 1003);
        mList3List.setImageResource(R.drawable.photo3);


        ImageSubData sub = new ImageSubData();
        sub.mTitle = "새로운 사진";

        ImageDetailGoupData goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo4;
        goupData.mImageDetailData1.mLikeCount = 2001;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo5;
        goupData.mImageDetailData2.mLikeCount = 2002;

        goupData.mImageDetailData3.mImageRes = R.drawable.photo6;
        goupData.mImageDetailData3.mLikeCount = 2003;

        sub.mImagetList.add(goupData);

        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo6;
        goupData.mImageDetailData1.mLikeCount = 2004;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo7;
        goupData.mImageDetailData2.mLikeCount = 2005;

        goupData.mImageDetailData3.mImageRes = R.drawable.photo8;
        goupData.mImageDetailData3.mLikeCount = 2006;

        sub.mImagetList.add(goupData);


        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo9;
        goupData.mImageDetailData1.mLikeCount = 2007;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo10;
        goupData.mImageDetailData2.mLikeCount = 2008;

        goupData.mImageDetailData3.mImageRes = R.drawable.photo11;
        goupData.mImageDetailData3.mLikeCount = 2009;

        sub.mImagetList.add(goupData);


        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo12;
        goupData.mImageDetailData1.mLikeCount = 2010;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo1;
        goupData.mImageDetailData2.mLikeCount = 2011;

        goupData.mImageDetailData3 = null;

        sub.mImagetList.add(goupData);

        mImgDataOne.mImageSubDataList.add(sub);


        mExpandableListViewOne = (ExpandableListView) LayoutInflater.from(this).inflate(R.layout.view_expanablelist, null);
        mExpandableListAdapterOne = new ExpandableListAdapter(this, mImgDataOne.mImageSubDataList, ExpandableListAdapter.TYPE_LETCHIIS, mChildClickListener);
        mExpandableListViewOne.setAdapter(mExpandableListAdapterOne);

        mViewPagerAdapter = new ViewPagerAdapter(this);
        mImgListViewPage.setAdapter(mViewPagerAdapter);

        mExpandableListViewOne.expandGroup(0);

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

    @OnClick({R.id.UploadImg})
    public void onClickLay(View v) {

        switch (v.getId()) {

            case R.id.UploadImg:

                IntentManager.getInstance().push(Letchiis.this, Request.class, true);
                break;

        }
    }



    View.OnClickListener mChildClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            int res = (int) v.getTag();
            showIamgeDialog(res);

        }
    };


    class ViewPagerAdapter extends PagerAdapter {

        Context mContext;
        ArrayList<View> mContentView = new ArrayList<>();

        public ViewPagerAdapter(Context context) {

            mContentView.clear();
            mContext = context;
            mContentView.add(mExpandableListViewOne);

        }

        @Override
        public int getCount() {

            return mContentView.size();

        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {


            View myView = mContentView.get(position);

            ((ViewPager) container).addView(myView);

            return myView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {


            container.removeView((View) object);

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    PopupWindow mPopUp;
    AVToggleButton mPopUpToggleLike;
    private void showIamgeDialog(int imgRes) {


        if (mPopUp != null) {

            mPopUp.dismiss();

        }

        View v = LayoutInflater.from(this).inflate(R.layout.layout_image_popup, null);
         mPopUpToggleLike =  (AVToggleButton)v.findViewById(R.id.ToggleLike);

        mPopUp = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        v.findViewById(R.id.CloseImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopUp.dismiss();
            }
        });

        v.findViewById(R.id.PopImg).setOnClickListener(new DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }

            @Override
            public void onDoubleClick(View v) {

                mPopUpToggleLike.setOn(!mPopUpToggleLike.isOn());

            }
        });

        mPopUp.setTouchable(true);
        mPopUp.setBackgroundDrawable(new BitmapDrawable());
        mPopUp.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View parent = getWindow().getDecorView();
        mPopUp.showAtLocation(parent, Gravity.CENTER, 0, -100);
    }


    public abstract class DoubleClickListener implements View.OnClickListener {

        private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

        long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
                onDoubleClick(v);
            } else {
                onSingleClick(v);
            }
            lastClickTime = clickTime;
        }

        public abstract void onSingleClick(View v);
        public abstract void onDoubleClick(View v);
    }



}
