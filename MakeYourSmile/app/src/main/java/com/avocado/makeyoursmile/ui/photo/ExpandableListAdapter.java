package com.avocado.makeyoursmile.ui.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.network.data.image.ImageDetailGoupData;
import com.avocado.makeyoursmile.network.data.image.ImageSubData;
import com.avocado.makeyoursmile.view.AVToggleButton;

import java.util.ArrayList;

/**
 * Created by HDlee on 2/12/16.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public static final int TYPE_LETCHIIS = 111111110;
    public static final int TYPE_LETMODEL = 111111111;

    private LayoutInflater mInflater;

    int mItemLayout;
    int mGroupLayout;
    Context mContext;
    private GroupViewHolder groupHolder;
    private ChildViewHolder childHolder;
    ArrayList<ImageSubData> mData;

    int mType;

    public ExpandableListAdapter(Context ctx, ArrayList<ImageSubData> data, int type, View.OnClickListener childClickListener) {

        mInflater = LayoutInflater.from(ctx);
        mChildClickListener = childClickListener;
        mData = data;
        mContext = ctx;
        mItemLayout = R.layout.listitem_group_image_letchiis;
        mType = type;
        mGroupLayout = R.layout.listitem_header_image_letchiis;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return mData.get(groupPosition).mImagetList.size();
    }

    @Override
    public View getChildView(final int groupPosition,
                             final int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {

        childHolder = null;

        if (convertView == null) {

            convertView = mInflater.inflate(mItemLayout, parent, false);

            childHolder = new ChildViewHolder();

            childHolder.mList1List = (ImageView) convertView.findViewById(R.id.List1List);
            childHolder.mCountTxt1 = (TextView) convertView.findViewById(R.id.CountTxt1);
            childHolder.mToggleLike1 = (AVToggleButton) convertView.findViewById(R.id.ToggleLike1);

            childHolder.mList2List = (ImageView) convertView.findViewById(R.id.List2List);
            childHolder.mCountTxt2 = (TextView) convertView.findViewById(R.id.CountTxt2);
            childHolder.mToggleLike2 = (AVToggleButton) convertView.findViewById(R.id.ToggleLike2);

            childHolder.mList3List = (ImageView) convertView.findViewById(R.id.List3List);
            childHolder.mCountTxt3 = (TextView) convertView.findViewById(R.id.CountTxt3);
            childHolder.mToggleLike3 = (AVToggleButton) convertView.findViewById(R.id.ToggleLike3);


            convertView.setTag(childHolder);

        } else {

            childHolder = (ChildViewHolder) convertView.getTag();
        }

        final ImageDetailGoupData data = mData.get(groupPosition).mImagetList.get(childPosition);


        if(data.mImageDetailData1 != null) {

            childHolder.mList1List.setVisibility(View.VISIBLE);
            childHolder.mCountTxt1.setVisibility(View.VISIBLE);
            childHolder.mToggleLike1.setVisibility(View.VISIBLE);

            childHolder.mCountTxt1.setText("" + data.mImageDetailData1.mLikeCount);
            childHolder.mList1List.setImageResource(data.mImageDetailData1.mImageRes);

            childHolder.mList1List.setTag(data.mImageDetailData1.mImageRes);
            childHolder.mList1List.setOnClickListener(mChildClickListener);

        }
        else {

            childHolder.mList1List.setVisibility(View.INVISIBLE);
            childHolder.mCountTxt1.setVisibility(View.INVISIBLE);
            childHolder.mToggleLike1.setVisibility(View.INVISIBLE);

            childHolder.mList1List.setOnClickListener(null);

        }


        if(data.mImageDetailData2 != null) {

            childHolder.mList2List.setVisibility(View.VISIBLE);
            childHolder.mCountTxt2.setVisibility(View.VISIBLE);
            childHolder.mToggleLike2.setVisibility(View.VISIBLE);

            childHolder.mCountTxt2.setText("" + data.mImageDetailData2.mLikeCount);
            childHolder.mList2List.setImageResource(data.mImageDetailData2.mImageRes);

            childHolder.mList2List.setTag(data.mImageDetailData1.mImageRes);
            childHolder.mList2List.setOnClickListener(mChildClickListener);

        }
        else {

            childHolder.mList2List.setVisibility(View.INVISIBLE);
            childHolder.mCountTxt2.setVisibility(View.INVISIBLE);
            childHolder.mToggleLike2.setVisibility(View.INVISIBLE);

            childHolder.mList2List.setOnClickListener(null);

        }


        if(data.mImageDetailData3 != null) {

            childHolder.mList3List.setVisibility(View.VISIBLE);
            childHolder.mCountTxt3.setVisibility(View.VISIBLE);
            childHolder.mToggleLike3.setVisibility(View.VISIBLE);

            childHolder.mCountTxt3.setText( "" + data.mImageDetailData3.mLikeCount);
            childHolder.mList3List.setImageResource(data.mImageDetailData3.mImageRes);

            childHolder.mList3List.setTag(data.mImageDetailData1.mImageRes);
            childHolder.mList3List.setOnClickListener(mChildClickListener);


        }
        else {

            childHolder.mList3List.setVisibility(View.INVISIBLE);
            childHolder.mCountTxt3.setVisibility(View.INVISIBLE);
            childHolder.mToggleLike3.setVisibility(View.INVISIBLE);

            childHolder.mList3List.setOnClickListener(null);

        }


        childHolder.index = childPosition;

        return convertView;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {

        return mData.size();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        groupHolder = null;

        if (convertView == null) {

            groupHolder = new GroupViewHolder();

            convertView = mInflater.inflate(mGroupLayout, parent, false);

            groupHolder.mTitleTxt = (TextView) convertView.findViewById(R.id.TitleTxt);
            groupHolder.mSubTxt = (TextView) convertView.findViewById(R.id.SubTxt);
            groupHolder.mSubSubTxt = (TextView) convertView.findViewById(R.id.SubSubTxt);
            groupHolder.mSubLine = convertView.findViewById(R.id.SubLine);

            convertView.setTag(groupHolder);

        } else {

            groupHolder = (GroupViewHolder) convertView.getTag();
        }

        final ImageSubData data = mData.get(groupPosition);


        groupHolder.mTitleTxt.setText(data.mTitle);

        if(groupPosition == 0) {

            groupHolder.mSubLine.setVisibility(View.GONE);
            groupHolder.mSubTxt.setVisibility(View.GONE);
            groupHolder.mSubSubTxt.setText(data.mDate);

        }
        else  if(groupPosition == 1) {


            groupHolder.mSubLine.setVisibility(View.VISIBLE);
            groupHolder.mSubTxt.setVisibility(View.VISIBLE);
            groupHolder.mSubSubTxt.setText("랭킹순");
            groupHolder.mSubTxt.setText("최신순");
       }


        groupHolder.index = groupPosition;


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {


        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {


        super.onGroupExpanded(groupPosition);
    }

    private View.OnClickListener mChildClickListener;


    private class GroupViewHolder {

        TextView mTitleTxt;
        TextView mSubTxt;
        View mSubLine;
        TextView mSubSubTxt;

        int index;

    }

    private class ChildViewHolder {

        ImageView mList1List;
        TextView mCountTxt1;
        AVToggleButton mToggleLike1;

        ImageView mList2List;
        TextView mCountTxt2;
        AVToggleButton mToggleLike2;

        ImageView mList3List;
        TextView mCountTxt3;
        AVToggleButton mToggleLike3;

        int index;
    }

}
