package com.avocado.makeyoursmile.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.view.AVCirclePageIndicator;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Info extends BaseActivity {




    @Bind(R.id.ContentLay)
    protected ViewGroup mContentLay;

    @Bind(R.id.MYSViewPage)
    protected ViewPager mMYSViewPage;

    @Bind(R.id.CirclePageIndicator)
    AVCirclePageIndicator mCirclePageIndicator;

    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        ArrayList<String> images = new ArrayList<>();
        images.add("1");
        images.add("1");
        images.add("1");
        images.add("1");
        images.add("1");

        mViewPagerAdapter = new ViewPagerAdapter(this);

        mMYSViewPage.setAdapter(mViewPagerAdapter);

        float density = getResources().getDisplayMetrics().density;
        mCirclePageIndicator.setViewPager(mMYSViewPage);
        mCirclePageIndicator.setRadius(4 * density);
        mCirclePageIndicator.setPageColor(0xFFcccccc);
        mCirclePageIndicator.setFillColor(0xffff0000);
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

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mContentLay.setVisibility(View.VISIBLE);

            }
        }, 700);

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

    @OnClick(R.id.SkipImg)
    public void onClicSkip(View v) {

        IntentManager.getInstance().push(this, Login.class, true);

    }


    class ViewPagerAdapter extends PagerAdapter {

        Context mContext;
        ArrayList<View> mContentView = new ArrayList<>();

        public ViewPagerAdapter(Context context) {

            mContext = context;

            ImageView img = new ImageView(context);
            img.setImageResource(R.drawable.info_01);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            mContentView.add(img);

            img = new ImageView(context);
            img.setImageResource(R.drawable.info_02);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            mContentView.add(img);

            img = new ImageView(context);
            img.setImageResource(R.drawable.info_03);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            mContentView.add(img);

        }

        @Override
        public int getCount() {

            return 3;

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
