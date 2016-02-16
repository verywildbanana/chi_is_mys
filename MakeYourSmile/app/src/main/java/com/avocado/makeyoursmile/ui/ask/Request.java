package com.avocado.makeyoursmile.ui.ask;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.ui.search.Dentistry;
import com.avocado.makeyoursmile.util.IntentManager;
import com.avocado.makeyoursmile.view.AVTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Request extends BaseActivity {


    @Bind(R.id.FemaleTagLay)
    protected ViewGroup mFemaleTagLay;

    @Bind(R.id.MaleTagLay)
    protected ViewGroup mMaleTagLay;

    @Bind(R.id.Year10TagLay)
    protected ViewGroup mYear10TagLay;

    @Bind(R.id.Year20TagLay)
    protected ViewGroup mYear20TagLay;

    @Bind(R.id.Year30TagLay)
    protected ViewGroup mYear30TagLay;

    @Bind(R.id.Year40TagLay)
    protected ViewGroup mYear40TagLay;

    @Bind(R.id.Year50TagLay)
    protected ViewGroup mYear50TagLay;

    @Bind(R.id.Ask1TagLay)
    protected ViewGroup mAsk1TagLay;

    @Bind(R.id.Ask2TagLay)
    protected ViewGroup mAsk2TagLay;

    @Bind(R.id.Ask3TagLay)
    protected ViewGroup mAsk3TagLay;

    @Bind(R.id.Ask4TagLay)
    protected ViewGroup mAsk4TagLay;

    @Bind(R.id.Ask5TagLay)
    protected ViewGroup mAsk5TagLay;

    @Bind(R.id.Ask6TagLay)
    protected ViewGroup mAsk6TagLay;

    @Bind(R.id.Ask7TagLay)
    protected ViewGroup mAsk7TagLay;


    @Bind(R.id.FemaleTagTxt)
    protected AVTextView mFemaleTagTxt;

    @Bind(R.id.MaleTagTxt)
    protected AVTextView mMaleTagTxt;


    @Bind(R.id.Year10TagTxt)
    protected AVTextView mYear10TagTxt;

    @Bind(R.id.Year20TagTxt)
    protected AVTextView mYear20TagTxt;

    @Bind(R.id.Year30TagTxt)
    protected AVTextView mYear30TagTxt;

    @Bind(R.id.Year40TagTxt)
    protected AVTextView mYear40TagTxt;

    @Bind(R.id.Year50TagTxt)
    protected AVTextView mYear50TagTxt;

    @Bind(R.id.Ask1TagTxt)
    protected AVTextView mAsk1TagTxt;

    @Bind(R.id.Ask2TagTxt)
    protected AVTextView mAsk2TagTxt;

    @Bind(R.id.Ask3TagTxt)
    protected AVTextView mAsk3TagTxt;

    @Bind(R.id.Ask4TagTxt)
    protected AVTextView mAsk4TagTxt;

    @Bind(R.id.Ask5TagTxt)
    protected AVTextView mAsk5TagTxt;

    @Bind(R.id.Ask6TagTxt)
    protected AVTextView mAsk6TagTxt;

    @Bind(R.id.Ask7TagTxt)
    protected AVTextView mAsk7TagTxt;


    boolean[] mAskTagSelect = {false, false, false, false, false, false, false};

    String[] DONG1 = {"역삼동","개포동", "청담동", "삼성동", "대치동", "신사동", "논현동", "압구정동", "세곡동", "자곡동", "율현동", "일원동", "수서동", "도곡동", "논현1동", "논현2동", "삼성1동", "삼성2동", "대치1동", "대치2동", "대치4동", "역삼1동", "역삼2동", "도곡1동", "도곡2동", "개포1동", "개포2동", "개포4동", "일원본동", "일원1동", "일원2동"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_request);
        ButterKnife.bind(this);


        mFemaleTagLay.setOnClickListener(mGenderClickListener);
        mMaleTagLay.setOnClickListener(mGenderClickListener);

        mYear10TagLay.setOnClickListener(mYearsClickListener);
        mYear20TagLay.setOnClickListener(mYearsClickListener);
        mYear30TagLay.setOnClickListener(mYearsClickListener);
        mYear40TagLay.setOnClickListener(mYearsClickListener);
        mYear50TagLay.setOnClickListener(mYearsClickListener);

        mAsk1TagLay.setOnClickListener(mAskClickListener);
        mAsk2TagLay.setOnClickListener(mAskClickListener);
        mAsk3TagLay.setOnClickListener(mAskClickListener);
        mAsk4TagLay.setOnClickListener(mAskClickListener);
        mAsk5TagLay.setOnClickListener(mAskClickListener);
        mAsk6TagLay.setOnClickListener(mAskClickListener);
        mAsk7TagLay.setOnClickListener(mAskClickListener);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {

        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case IntentManager.REQ_CODE_PHOTO_ALBUM:
            case IntentManager.REQ_CODE_CAMERA:

                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    String selectImagePath = getRealPathFromURI(selectedImage);

//                    Glide.with(getContext())
//                            .load(selectedImage)
//                            .transform(new CircleTransformation(getContext()))
//                            .into(mProfileImg);
//
//                    String[] temp = {selectImagePath};
//                    new ProcessImageTask().execute(temp);
                }

                break;

        }

    }

    public String getRealPathFromURI(Uri contentUri) {
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return contentUri.getPath();
        }
    }


    @OnClick(R.id.TitleLeftImg)
    public void onClickTitleLeft(View v) {

        onBackPressed();

    }

    @OnClick(R.id.TitleSearchImg)
    public void onClickTitleSearch(View v) {

        IntentManager.getInstance().push(this, Dentistry.class, true);
    }

    @OnClick({R.id.AddressTagLay1, R.id.AddressTagLay2, R.id.UploadImg1, R.id.UploadImg2, R.id.UploadImg3, R.id.ReqBtn})
    public void onClickLay(View v) {

        switch (v.getId()) {

            case R.id.AddressTagLay1:

                break;

            case R.id.AddressTagLay2:

                showListDialog("동을 선택하세요.", null, null, null, true, DONG1, new BaseActivity.OnDialogButtonListener() {
                            @Override
                            public void onPositiveButtonClick() {


                            }

                            @Override
                            public void onNegativeButtonClick() {

                            }
                        },
                        new BaseActivity.OnListDialogButtonListener() {
                            @Override
                            public void onListClick(int position) {

                                if (position == 0) {



                                } else if (position == 1) {


                                }

                            }
                        });


                break;

            case R.id.UploadImg1:


                String[] menus = {"카메라", "앨범"};

               showListDialog(null, null, null, null, true, menus, new BaseActivity.OnDialogButtonListener() {
                            @Override
                            public void onPositiveButtonClick() {


                            }

                            @Override
                            public void onNegativeButtonClick() {

                            }
                        },
                        new BaseActivity.OnListDialogButtonListener() {
                            @Override
                            public void onListClick(int position) {

                                if (position == 0) {

                                    Intent intent = new Intent();
                                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(intent, IntentManager.REQ_CODE_CAMERA);


                                } else if (position == 1) {

                                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                                    photoPickerIntent.setType("image/*");
                                    startActivityForResult(photoPickerIntent, IntentManager.REQ_CODE_PHOTO_ALBUM);


                                }

                            }
                        });

                break;

            case R.id.UploadImg2:


                break;

            case R.id.UploadImg3:

                break;

            case R.id.ReqBtn:


                break;

        }
    }

    View.OnClickListener mGenderClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mFemaleTagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
            mMaleTagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);

            mFemaleTagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
            mMaleTagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));

            switch (v.getId()) {

                case R.id.FemaleTagLay:

                    mFemaleTagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                    mFemaleTagTxt.setTextColor(getResources().getColor(R.color.white));

                    break;

                case R.id.MaleTagLay:

                    mMaleTagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                    mMaleTagTxt.setTextColor(getResources().getColor(R.color.white));

                    break;

            }

        }
    };

    View.OnClickListener mYearsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mYear10TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
            mYear20TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
            mYear30TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
            mYear40TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
            mYear50TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);

            mYear10TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
            mYear20TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
            mYear30TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
            mYear40TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
            mYear50TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));

            switch (v.getId()) {

                case R.id.Year10TagLay:

                    mYear10TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                    mYear10TagTxt.setTextColor(getResources().getColor(R.color.white));

                    break;

                case R.id.Year20TagLay:


                    mYear20TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                    mYear20TagTxt.setTextColor(getResources().getColor(R.color.white));


                    break;

                case R.id.Year30TagLay:

                    mYear30TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                    mYear30TagTxt.setTextColor(getResources().getColor(R.color.white));


                    break;

                case R.id.Year40TagLay:

                    mYear40TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                    mYear40TagTxt.setTextColor(getResources().getColor(R.color.white));


                    break;

                case R.id.Year50TagLay:

                    mYear50TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                    mYear50TagTxt.setTextColor(getResources().getColor(R.color.white));


                    break;
            }

        }
    };

    View.OnClickListener mAskClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.Ask1TagLay:

                    mAskTagSelect[0] = !mAskTagSelect[0];

                    if(mAskTagSelect[0]) {

                        mAsk1TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                        mAsk1TagTxt.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {

                        mAsk1TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
                        mAsk1TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
                    }

                    break;

                case R.id.Ask2TagLay:

                    mAskTagSelect[1] = !mAskTagSelect[1];

                    if(mAskTagSelect[1]) {

                        mAsk2TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                        mAsk2TagTxt.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {

                        mAsk2TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
                        mAsk2TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
                    }

                    break;

                case R.id.Ask3TagLay:

                    mAskTagSelect[2] = !mAskTagSelect[2];

                    if(mAskTagSelect[2]) {

                        mAsk3TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                        mAsk3TagTxt.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {

                        mAsk3TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
                        mAsk3TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
                    }

                    break;

                case R.id.Ask4TagLay:

                    mAskTagSelect[3] = !mAskTagSelect[3];

                    if(mAskTagSelect[3]) {

                        mAsk4TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                        mAsk4TagTxt.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {

                        mAsk4TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
                        mAsk4TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
                    }

                    break;

                case R.id.Ask5TagLay:

                    mAskTagSelect[4] = !mAskTagSelect[4];

                    if(mAskTagSelect[4]) {

                        mAsk5TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                        mAsk5TagTxt.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {

                        mAsk5TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
                        mAsk5TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
                    }

                    break;

                case R.id.Ask6TagLay:

                    mAskTagSelect[5] = !mAskTagSelect[5];

                    if(mAskTagSelect[5]) {

                        mAsk6TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                        mAsk6TagTxt.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {

                        mAsk6TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
                        mAsk6TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
                    }

                    break;

                case R.id.Ask7TagLay:

                    mAskTagSelect[6] = !mAskTagSelect[6];

                    if(mAskTagSelect[6]) {

                        mAsk7TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue);
                        mAsk7TagTxt.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {

                        mAsk7TagLay.setBackgroundResource(R.drawable.tag_round_bg_blue_tranparent);
                        mAsk7TagTxt.setTextColor(getResources().getColor(R.color.mys_txt_blue));
                    }

                    break;

            }

        }
    };



}
