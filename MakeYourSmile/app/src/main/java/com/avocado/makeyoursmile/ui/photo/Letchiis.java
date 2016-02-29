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
import android.widget.PopupWindow;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.data.image.ImageDetailGoupData;
import com.avocado.makeyoursmile.ui.ask.Request;
import com.avocado.makeyoursmile.ui.search.Dentistry;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.view.AVToggleButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by HDlee on 1/29/16.
 */
public class Letchiis extends BaseActivity {


    @Bind(R.id.ImgListViewPage)
    protected ViewPager mImgListViewPage;

    ViewPagerAdapter mViewPagerAdapter;


    StickyListHeadersListView mStickyListViewOne;

    StickyHeaderListAdapter mStickyHeaderListAdapterOne;

    public ArrayList<ImageDetailGoupData> mImagetList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_letchiis);
        ButterKnife.bind(this);

        getTestImageData();

        mStickyListViewOne = (StickyListHeadersListView) LayoutInflater.from(this).inflate(R.layout.view_stickylistview, null);
        mStickyHeaderListAdapterOne = new StickyHeaderListAdapter(this,mImagetList, mChildClickListener,  ExpandableListAdapter.TYPE_LETCHIIS);
        mStickyListViewOne.setDrawingListUnderStickyHeader(true);
        mStickyListViewOne.setAreHeadersSticky(true);
        mStickyListViewOne.setAdapter(mStickyHeaderListAdapterOne);

        mViewPagerAdapter = new ViewPagerAdapter(this);

        mImgListViewPage.setAdapter(mViewPagerAdapter);


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

    void  getTestImageData() {

        ImageDetailGoupData goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo1;
        goupData.mImageDetailData1.mLikeCount = 1001;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo2;
        goupData.mImageDetailData2.mLikeCount = 1002;

        goupData.mImageDetailData3.mImageRes = R.drawable.photo3;
        goupData.mImageDetailData3.mLikeCount = 3003;

        goupData.mTitle = "좋아요 Best";
        goupData.mId = 1;
        mImagetList.add(goupData);

        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo4;
        goupData.mImageDetailData1.mLikeCount = 2001;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo5;
        goupData.mImageDetailData2.mLikeCount = 2002;

        goupData.mImageDetailData3.mImageRes = R.drawable.photo6;
        goupData.mImageDetailData3.mLikeCount = 2003;

        goupData.mTitle = "새로운 사진";
        goupData.mId = 0;
        mImagetList.add(goupData);

        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo6;
        goupData.mImageDetailData1.mLikeCount = 2004;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo7;
        goupData.mImageDetailData2.mLikeCount = 2005;

        goupData.mImageDetailData3.mImageRes = R.drawable.photo8;
        goupData.mImageDetailData3.mLikeCount = 2006;

        goupData.mTitle = "새로운 사진";
        goupData.mId = 0;
        mImagetList.add(goupData);


        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo9;
        goupData.mImageDetailData1.mLikeCount = 2007;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo10;
        goupData.mImageDetailData2.mLikeCount = 2008;

        goupData.mImageDetailData3.mImageRes = R.drawable.photo11;
        goupData.mImageDetailData3.mLikeCount = 2009;

        goupData.mTitle = "새로운 사진";
        goupData.mId = 0;
        mImagetList.add(goupData);


        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo12;
        goupData.mImageDetailData1.mLikeCount = 2010;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo1;
        goupData.mImageDetailData2.mLikeCount = 2011;

        goupData.mImageDetailData3 = null;

        goupData.mTitle = "새로운 사진";
        goupData.mId = 0;
        mImagetList.add(goupData);

        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo12;
        goupData.mImageDetailData1.mLikeCount = 2010;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo1;
        goupData.mImageDetailData2.mLikeCount = 2011;

        goupData.mImageDetailData3 = null;

        goupData.mTitle = "새로운 사진";
        goupData.mId = 0;
        mImagetList.add(goupData);


        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo12;
        goupData.mImageDetailData1.mLikeCount = 2010;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo1;
        goupData.mImageDetailData2.mLikeCount = 2011;

        goupData.mImageDetailData3 = null;

        goupData.mTitle = "새로운 사진";
        goupData.mId = 0;
        mImagetList.add(goupData);


        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo12;
        goupData.mImageDetailData1.mLikeCount = 2010;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo1;
        goupData.mImageDetailData2.mLikeCount = 2011;

        goupData.mImageDetailData3 = null;

        goupData.mTitle = "새로운 사진";
        goupData.mId = 0;
        mImagetList.add(goupData);


        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo12;
        goupData.mImageDetailData1.mLikeCount = 2010;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo1;
        goupData.mImageDetailData2.mLikeCount = 2011;

        goupData.mImageDetailData3 = null;

        goupData.mTitle = "새로운 사진";
        goupData.mId = 0;
        mImagetList.add(goupData);


        goupData = new ImageDetailGoupData();
        goupData.mImageDetailData1.mImageRes = R.drawable.photo12;
        goupData.mImageDetailData1.mLikeCount = 2010;

        goupData.mImageDetailData2.mImageRes = R.drawable.photo1;
        goupData.mImageDetailData2.mLikeCount = 2011;

        goupData.mImageDetailData3 = null;

        goupData.mTitle = "새로운 사진";
        goupData.mId = 0;
        mImagetList.add(goupData);

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
            mContentView.add(mStickyListViewOne);

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
