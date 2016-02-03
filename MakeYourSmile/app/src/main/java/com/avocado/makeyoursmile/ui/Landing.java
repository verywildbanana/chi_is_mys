package com.avocado.makeyoursmile.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.view.AVCirclePageIndicator;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Landing extends BaseActivity {


    @Bind(R.id.LandingViewPage)
    protected ViewPager mLandingViewPage;

    @Bind(R.id.CirclePageIndicator)
    AVCirclePageIndicator mCirclePageIndicator;

    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        ArrayList<String> images = new ArrayList<>();
        images.add("1");
        images.add("1");
        images.add("1");
        images.add("1");
        images.add("1");

        mViewPagerAdapter = new ViewPagerAdapter(this, images);

        mLandingViewPage.setAdapter(mViewPagerAdapter);

        float density = getResources().getDisplayMetrics().density;
        mCirclePageIndicator.setViewPager(mLandingViewPage);
        mCirclePageIndicator.setRadius(4 * density);
        mCirclePageIndicator.setPageColor(0xFFcccccc);
        mCirclePageIndicator.setFillColor(0xffFFFFFF);
        mCirclePageIndicator.setStrokeColor(0xFFCCCCCC);
        mCirclePageIndicator.setStrokeWidth(2 * density);
        mCirclePageIndicator.setCentered(true);

        mCirclePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

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

//    @OnClick({R.id.NoticeLay, R.id.ExplainLay, R.id.ReqLay, R.id.FAQLay, R.id.TermLay, R.id.PersonalTermLay, R.id.AccountTermLay})
//    public void onClickLay(View v) {
//
//        switch (v.getId()) {
//
//            case R.id.NoticeLay:
//
//                break;
//
//            case R.id.ExplainLay:
//
//
//                break;
//
//            case R.id.ReqLay:
//
//                break;
//
//            case R.id.FAQLay:
//
//
//                break;
//
//            case R.id.TermLay:
//
//                break;
//
//            case R.id.PersonalTermLay:
//
//
//                break;
//
//
//            case R.id.AccountTermLay:
//
//                break;
//
//        }
//    }

    class ViewPagerAdapter extends PagerAdapter {

        Context mContext;
        ArrayList<String> mImageUrlData;
        ArrayList<View> mContentView = new ArrayList<>();

        public ViewPagerAdapter(Context context, ArrayList<String> images) {

            mContext = context;


            if (images != null && images.isEmpty() == false) {

                mImageUrlData = images;
                int size = images.size();
                ImageView[] imageView = new ImageView[size];

                for (int i = 0; i < size; i++) {

                    imageView[i] = new ImageView(context);
                    imageView[i].setImageResource(R.drawable.photosample_list);
                    mContentView.add(imageView[i]);

                }

            }
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

}
