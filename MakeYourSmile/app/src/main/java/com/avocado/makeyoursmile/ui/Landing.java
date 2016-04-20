package com.avocado.makeyoursmile.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avocado.makeyoursmile.Constants;
import com.avocado.makeyoursmile.MakeYourSmileApp;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.api.DentistApi;
import com.avocado.makeyoursmile.network.data.dentist.DetailDentistParserData;
import com.avocado.makeyoursmile.ui.search.Dentistry;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.util.SmartLog;
import com.avocado.makeyoursmile.view.AVCirclePageIndicator;
import com.avocado.makeyoursmile.view.AVTextView;
import com.bumptech.glide.Glide;

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
public class Landing extends BaseActivity {


    @Bind(R.id.LandingViewPage)
    protected ViewPager mLandingViewPage;

    @Bind(R.id.CirclePageIndicator)
    AVCirclePageIndicator mCirclePageIndicator;

    @Bind(R.id.DentistNameTxt)
    AVTextView mDentistNameTxt;

    @Bind(R.id.DentistDesTxt)
    AVTextView mDentistDesTxt;


    @Bind(R.id.DentistAddressTxt1)
    AVTextView mDentistAddressTxt1;

    @Bind(R.id.DentistAddressTxt2)
    AVTextView mDentistAddressTxt2;

    @Bind(R.id.CareHours)
    AVTextView mCareHours;


    @Bind(R.id.Promotion)
    AVTextView mPromotion;

    @Bind(R.id.PromotionTxt)
    AVTextView mPromotionTxt;

    @Bind(R.id.TagAddLay)
    ViewGroup mTagAddLay;

    @Bind(R.id.EventImg)
    ImageView mEventImg;

    ViewPagerAdapter mViewPagerAdapter;
    String mDentistId;

    DetailDentistParserData mDetailDentistData;

    DentistApi dentistApi = MakeYourSmileApp.createApi(DentistApi.class);

    ArrayList<String> mImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        Intent t = getIntent();

        SmartLog.getInstance().w(TAG, "Landing  onCreate ~~~~~~~~~~~~~~~ ");

        if(t != null) {

            mDentistId = t.getStringExtra(IntentManager.EXTRA_ID);

            SmartLog.getInstance().w(TAG, "Landing  onCreate ~~~~~~~~~~~~~~~mDentistId  " + mDentistId);


            showIndicator(true, null);

            dentistApi.getDentistInfo(mDentistId, new Callback<DetailDentistParserData>() {
                @Override
                public void success(DetailDentistParserData data, Response response) {

                    hideIndicator();

                    if(controlApiError(data)) {

                        mDetailDentistData = data;
                        setView();
                    }

                }

                @Override
                public void failure(RetrofitError error) {

                    hideIndicator();

                }
            });

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

    @OnClick({R.id.IntroduceDoctorImg, R.id.MapImg, R.id.AskImg, R.id.EventImg})
    public void onClickLay(View v) {

        switch (v.getId()) {

            case R.id.IntroduceDoctorImg:

                IntentManager.getInstance().push(Landing.this, Doctor.class, true);
                break;

            case R.id.MapImg:


                break;

            case R.id.AskImg:

                break;

            case R.id.EventImg:


                break;
        }
    }

    void setView() {

        mDentistNameTxt.setText(mDetailDentistData.mDentistData.NAME);
        mDentistDesTxt.setText(mDetailDentistData.mDentistData.DES);

        mDentistAddressTxt1.setText(mDetailDentistData.mDentistData.ADDRESS1 + " " + mDetailDentistData.mDentistData.ADDRESS2);
        mDentistAddressTxt2.setText(mDetailDentistData.mDentistData.ADDRESS3 + " " + mDetailDentistData.mDentistData.ADDRESS4);


        mCareHours.setText("평 일 " + mDetailDentistData.mDentistData.ACTIVE_TIME1 + "\t" + "주 말 " +  mDetailDentistData.mDentistData.ACTIVE_TIME2 + "\n" + "점 심 " + mDetailDentistData.mDentistData.ACTIVE_TIME3);



        mTagAddLay.removeAllViews();

        if (TextUtils.isEmpty(mDetailDentistData.mDentistData.HASH_TAG_1) == false) {

            String[] hash_tags = mDetailDentistData.mDentistData.HASH_TAG_1.split(Constants.SEPARATER);

            for (int i = 0; i < hash_tags.length; i++) {

                View v = LayoutInflater.from(this).inflate(R.layout.layout_taglay_transparent, null);
                ((TextView)v.findViewById(R.id.TagText)).setText(hash_tags[i]);
                mTagAddLay.addView(v);

            }
        }

        mImages.clear();

        if(TextUtils.isEmpty(mDetailDentistData.mDentistData.IMG_1) == false) {

            mImages.add(mDetailDentistData.mDentistData.IMG_1);

        }

        if(TextUtils.isEmpty(mDetailDentistData.mDentistData.IMG_2) == false) {

            mImages.add(mDetailDentistData.mDentistData.IMG_2);

        }

        if(TextUtils.isEmpty(mDetailDentistData.mDentistData.IMG_3) == false) {

            mImages.add(mDetailDentistData.mDentistData.IMG_3);

        }

        if(TextUtils.isEmpty(mDetailDentistData.mDentistData.IMG_4) == false) {

            mImages.add(mDetailDentistData.mDentistData.IMG_4);

        }

        if(TextUtils.isEmpty(mDetailDentistData.mDentistData.IMG_5) == false) {

            mImages.add(mDetailDentistData.mDentistData.IMG_5);

        }

        if(mImages.isEmpty()) {

            mLandingViewPage.setBackgroundResource(R.drawable.photosample_list);

        }
        else {

            mViewPagerAdapter = new ViewPagerAdapter(this, mImages);
            mLandingViewPage.setAdapter(mViewPagerAdapter);

            if(mImages.size() > 1) {

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



        }

    }

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
                    imageView[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
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


            ImageView myView = (ImageView) mContentView.get(position);

            Uri uri = Uri.parse(mImageUrlData.get(position));
            Glide.with(mContext)
                    .load(uri)
                    .into(myView);

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
