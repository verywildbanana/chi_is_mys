package com.avocado.makeyoursmile.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.avocado.makeyoursmile.MakeYourSmileApp;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.api.DentistApi;
import com.avocado.makeyoursmile.util.IntentManager;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class InsertDentistImg extends BaseActivity {


    @Bind(R.id.UploadImg1)
    ImageView mUploadImg1;

    @Bind(R.id.UploadImg2)
    ImageView mUploadImg2;

    @Bind(R.id.UploadImg3)
    ImageView mUploadImg3;

    @Bind(R.id.UploadImg4)
    ImageView mUploadImg4;

    @Bind(R.id.UploadImg5)
    ImageView mUploadImg5;

    @Bind(R.id.UploadImg6)
    ImageView mUploadImg6;

    @Bind(R.id.UploadImg7)
    ImageView mUploadImg7;

    @Bind(R.id.UploadImg8)
    ImageView mUploadImg8;

    DentistApi dentistApi = MakeYourSmileApp.createApi(DentistApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isertdentist_img);
        ButterKnife.bind(this);

        mUploadImg1.setOnClickListener(mClickListener);
        mUploadImg2.setOnClickListener(mClickListener);
        mUploadImg3.setOnClickListener(mClickListener);
        mUploadImg4.setOnClickListener(mClickListener);
        mUploadImg5.setOnClickListener(mClickListener);
        mUploadImg6.setOnClickListener(mClickListener);
        mUploadImg7.setOnClickListener(mClickListener);
        mUploadImg8.setOnClickListener(mClickListener);


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
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case IntentManager.REQ_CODE_PHOTO_ALBUM:
            case IntentManager.REQ_CODE_CAMERA:

                if (resultCode == RESULT_OK) {

                    Uri selectedImage = imageReturnedIntent.getData();
                    String selectImagePath = getRealPathFromURI(selectedImage);
                    setUploadImg(selectedImage, selectImagePath);
                }

                break;

        }
    }


    @OnClick(R.id.TitleLeftImg)
    public void onClickTitleLeft(View v) {

        onBackPressed();

    }

    @OnClick(R.id.ReqBtn)
    public void onClickRegBtn(View v) {


    }


    int mSelectImageViewId;
    public void onClickLay(View v) {

        mSelectImageViewId = v.getId();

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


    }

    private String getRealPathFromURI(Uri contentUri) {
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

    private void setUploadImg(Uri imgUri, String imgPath) {

        ImageView selectImageView = null;

        switch (mSelectImageViewId) {

            case R.id.UploadImg1:

                selectImageView = mUploadImg1;

                break;

            case R.id.UploadImg2:

                selectImageView = mUploadImg2;
                break;

            case R.id.UploadImg3:

                selectImageView = mUploadImg3;
                break;

            case R.id.UploadImg4:

                selectImageView = mUploadImg4;
                break;

            case R.id.UploadImg5:

                selectImageView = mUploadImg5;
                break;

            case R.id.UploadImg6:
                selectImageView = mUploadImg6;

                break;

            case R.id.UploadImg7:
                selectImageView = mUploadImg7;

                break;

            case R.id.UploadImg8:
                selectImageView = mUploadImg8;

                break;
        }

        Glide.with(this)
                .load(imgUri)
                .into(selectImageView);

        String[] temp = {imgPath};

//        new ProcessImageTask().execute(temp);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            onClickLay(v);

        }
    };

}
