package com.avocado.makeyoursmile.ui.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.network.data.image.ImageDetailGoupData;
import com.avocado.makeyoursmile.view.AVToggleButton;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class StickyHeaderListAdapter extends BaseAdapter implements
        StickyListHeadersAdapter, SectionIndexer {

    public static final int TYPE_LETCHIIS = 111111110;
    public static final int TYPE_LETMODEL = 111111111;

    private final Context mContext;
    private int[] mSectionIndices;
    private String[] mSectionLetters;
    private LayoutInflater mInflater;
    public ArrayList<ImageDetailGoupData> mImagetList;
    int mItemLayout;
    int mGroupLayout;
    private View.OnClickListener mChildClickListener;
    private GroupViewHolder groupHolder;
    private ChildViewHolder childHolder;
    int mType;

    public StickyHeaderListAdapter(Context context, ArrayList<ImageDetailGoupData> imagetList, View.OnClickListener childClickListener, int type) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mImagetList = imagetList;
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();

        mItemLayout = R.layout.listitem_group_image_letchiis;
        mGroupLayout = R.layout.listitem_header_image_letchiis;
        mChildClickListener = childClickListener;

        mType = type;

    }

    private int[] getSectionIndices() {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        int lastId = mImagetList.get(0).mId;
        sectionIndices.add(0);
        for (int i = 1; i < mImagetList.size(); i++) {

            if (mImagetList.get(i).mId != lastId) {
                lastId = mImagetList.get(i).mId;
                sectionIndices.add(i);
            }
        }
        int[] sections = new int[sectionIndices.size()];
        for (int i = 0; i < sectionIndices.size(); i++) {
            sections[i] = sectionIndices.get(i);
        }
        return sections;
    }

    private String[] getSectionLetters() {
        String[] letters = new String[mSectionIndices.length];
        for (int i = 0; i < mSectionIndices.length; i++) {
            letters[i] = mImagetList.get(mSectionIndices[i]).mTitle;
        }
        return letters;
    }

    @Override
    public int getCount() {
        return mImagetList.size();
    }

    @Override
    public Object getItem(int position) {
        return mImagetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        childHolder = null;

        if (convertView == null) {

            convertView = mInflater.inflate(mItemLayout, parent, false);

            childHolder = new ChildViewHolder();

            childHolder.mList1List = (ImageView) convertView.findViewById(R.id.List1List);
            childHolder.mRankinImg1 = (ImageView) convertView.findViewById(R.id.RankinImg1);
            childHolder.mCountTxt1 = (TextView) convertView.findViewById(R.id.CountTxt1);
            childHolder.mToggleLike1 = (AVToggleButton) convertView.findViewById(R.id.ToggleLike1);

            childHolder.mList2List = (ImageView) convertView.findViewById(R.id.List2List);
            childHolder.mRankinImg2 = (ImageView) convertView.findViewById(R.id.RankinImg2);
            childHolder.mCountTxt2 = (TextView) convertView.findViewById(R.id.CountTxt2);
            childHolder.mToggleLike2 = (AVToggleButton) convertView.findViewById(R.id.ToggleLike2);

            childHolder.mList3List = (ImageView) convertView.findViewById(R.id.List3List);
            childHolder.mRankinImg3 = (ImageView) convertView.findViewById(R.id.RankinImg3);
            childHolder.mCountTxt3 = (TextView) convertView.findViewById(R.id.CountTxt3);
            childHolder.mToggleLike3 = (AVToggleButton) convertView.findViewById(R.id.ToggleLike3);


            convertView.setTag(childHolder);

        } else {

            childHolder = (ChildViewHolder) convertView.getTag();
        }

        final ImageDetailGoupData data = mImagetList.get(position);

        if(data.mId == 1) {

            childHolder.mRankinImg1.setVisibility(View.VISIBLE);
            childHolder.mRankinImg2.setVisibility(View.VISIBLE);
            childHolder.mRankinImg3.setVisibility(View.VISIBLE);

        }
        else {


            childHolder.mRankinImg1.setVisibility(View.GONE);
            childHolder.mRankinImg2.setVisibility(View.GONE);
            childHolder.mRankinImg3.setVisibility(View.GONE);

        }


        if (data.mImageDetailData1 != null) {

            childHolder.mList1List.setVisibility(View.VISIBLE);
            childHolder.mCountTxt1.setVisibility(View.VISIBLE);
            childHolder.mToggleLike1.setVisibility(View.VISIBLE);

            childHolder.mCountTxt1.setText("" + data.mImageDetailData1.mLikeCount);
            childHolder.mList1List.setImageResource(data.mImageDetailData1.mImageRes);

            childHolder.mList1List.setTag(data.mImageDetailData1.mImageRes);
            childHolder.mList1List.setOnClickListener(mChildClickListener);

        } else {

            childHolder.mList1List.setVisibility(View.INVISIBLE);
            childHolder.mCountTxt1.setVisibility(View.INVISIBLE);
            childHolder.mToggleLike1.setVisibility(View.INVISIBLE);

            childHolder.mList1List.setOnClickListener(null);

        }


        if (data.mImageDetailData2 != null) {

            childHolder.mList2List.setVisibility(View.VISIBLE);
            childHolder.mCountTxt2.setVisibility(View.VISIBLE);
            childHolder.mToggleLike2.setVisibility(View.VISIBLE);

            childHolder.mCountTxt2.setText("" + data.mImageDetailData2.mLikeCount);
            childHolder.mList2List.setImageResource(data.mImageDetailData2.mImageRes);

            childHolder.mList2List.setTag(data.mImageDetailData1.mImageRes);
            childHolder.mList2List.setOnClickListener(mChildClickListener);

        } else {

            childHolder.mList2List.setVisibility(View.INVISIBLE);
            childHolder.mCountTxt2.setVisibility(View.INVISIBLE);
            childHolder.mToggleLike2.setVisibility(View.INVISIBLE);

            childHolder.mList2List.setOnClickListener(null);

        }


        if (data.mImageDetailData3 != null) {

            childHolder.mList3List.setVisibility(View.VISIBLE);
            childHolder.mCountTxt3.setVisibility(View.VISIBLE);
            childHolder.mToggleLike3.setVisibility(View.VISIBLE);

            childHolder.mCountTxt3.setText("" + data.mImageDetailData3.mLikeCount);
            childHolder.mList3List.setImageResource(data.mImageDetailData3.mImageRes);

            childHolder.mList3List.setTag(data.mImageDetailData1.mImageRes);
            childHolder.mList3List.setOnClickListener(mChildClickListener);


        } else {

            childHolder.mList3List.setVisibility(View.INVISIBLE);
            childHolder.mCountTxt3.setVisibility(View.INVISIBLE);
            childHolder.mToggleLike3.setVisibility(View.INVISIBLE);

            childHolder.mList3List.setOnClickListener(null);

        }


        childHolder.index = position;

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        groupHolder = null;

        if (convertView == null) {

            groupHolder = new GroupViewHolder();

            convertView = mInflater.inflate(mGroupLayout, parent, false);

            groupHolder.mTitleTxt = (TextView) convertView.findViewById(R.id.TitleTxt);
            groupHolder.mSubTxt = (TextView) convertView.findViewById(R.id.SubTxt);
            groupHolder.mTagLay = (ViewGroup) convertView.findViewById(R.id.TagLay);
            groupHolder.mSubImg = (ImageView) convertView.findViewById(R.id.SubImg);

            convertView.setTag(groupHolder);

        } else {

            groupHolder = (GroupViewHolder) convertView.getTag();
        }

        final ImageDetailGoupData data = mImagetList.get(position);


        if(data.mId == 1) {

            groupHolder.mTagLay.setVisibility(View.VISIBLE);
            groupHolder.mSubImg.setVisibility(View.GONE);

            groupHolder.mSubTxt.setText("접수중");

        }
        else {

            groupHolder.mTagLay.setVisibility(View.GONE);
            groupHolder.mSubImg.setVisibility(View.VISIBLE);

        }

        groupHolder.mTitleTxt.setText(data.mTitle);

        groupHolder.index = position;

        return convertView;
    }

    /**
     * Remember that these have to be static, postion=1 should always return
     * the same Id that is.
     */
    @Override
    public long getHeaderId(int position) {
        // return the first character of the country as ID because this is what
        // headers are based upon
        return mImagetList.get(position). mId;
    }

    @Override
    public int getPositionForSection(int section) {
        if (mSectionIndices.length == 0) {
            return 0;
        }

        if (section >= mSectionIndices.length) {
            section = mSectionIndices.length - 1;
        } else if (section < 0) {
            section = 0;
        }
        return mSectionIndices[section];
    }

    @Override
    public int getSectionForPosition(int position) {
        for (int i = 0; i < mSectionIndices.length; i++) {
            if (position < mSectionIndices[i]) {
                return i - 1;
            }
        }
        return mSectionIndices.length - 1;
    }

    @Override
    public Object[] getSections() {
        return mSectionLetters;
    }


    private class GroupViewHolder {

        TextView mTitleTxt;
        TextView mSubTxt;
        ViewGroup mTagLay;
        ImageView mSubImg;

        int index;

    }

    private class ChildViewHolder {

        ImageView mList1List, mRankinImg1;
        TextView mCountTxt1;
        AVToggleButton mToggleLike1;

        ImageView mList2List, mRankinImg2;
        TextView mCountTxt2;
        AVToggleButton mToggleLike2;

        ImageView mList3List, mRankinImg3;
        TextView mCountTxt3;
        AVToggleButton mToggleLike3;

        int index;
    }

}
