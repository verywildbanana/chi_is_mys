package com.avocado.makeyoursmile.ui.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.base.BaseActivity;
import com.avocado.makeyoursmile.network.data.NoticeData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HDlee on 1/29/16.
 */
public class Notice extends BaseActivity {


    @Bind(R.id.ExpandListView)
    protected ExpandableListView mExpandListView;

    private ExpandableListAdapter mExpandListAdapter;

    private ArrayList<NoticeData> mDataArray = new ArrayList<NoticeData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alaram_notice);
        ButterKnife.bind(this);

        NoticeData data = new NoticeData();
        data.mTitle = "[안내] title 1";
        data.mContents = "content 1 \n" +
                                " 2 \n" +
                                " 3 \n" +
                                " 4 \n" +
                                " 5 \n" +
                                " 6 \n" +
                                " 7 \n" +
                                " 8 \n" +
                                " 9   ";

        mDataArray.add(data);

        data = new NoticeData();
        data.mTitle = "[안내] title 2";
        data.mContents = "content 1 \n" +
                " 2 \n" +
                " 3 \n" +
                " 4 \n" +
                " 5 \n" +
                " 6 \n" +
                " 7 \n" +
                " 8 \n" +
                " 9   ";

        mDataArray.add(data);


        data = new NoticeData();
        data.mTitle = "[안내] title 3";
        data.mContents = "content 1 \n" +
                " 2 \n" +
                " 3 \n" +
                " 4 \n" +
                " 5 \n" +
                " 6 \n" +
                " 7 \n" +
                " 8 \n" +
                " 9   ";

        mDataArray.add(data);



        data = new NoticeData();
        data.mTitle = "[안내] title 4";
        data.mContents = "content 1 \n" +
                " 2 \n" +
                " 3 \n" +
                " 4 \n" +
                " 5 \n" +
                " 6 \n" +
                " 7 \n" +
                " 8 \n" +
                " 9   ";

        mDataArray.add(data);


        data = new NoticeData();
        data.mTitle = "[안내] title 5";
        data.mContents = "content 1 \n" +
                " 2 \n" +
                " 3 \n" +
                " 4 \n" +
                " 5 \n" +
                " 6 \n" +
                " 7 \n" +
                " 8 \n" +
                " 9   ";

        mDataArray.add(data);

        mExpandListAdapter  = new ExpandableListAdapter(this,
                R.layout.listitem_child_alarm_notice,
                R.layout.listitem_group_alarm_notice, mDataArray);


        mExpandListView.setAdapter(mExpandListAdapter);


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



    private class ExpandableListAdapter extends BaseExpandableListAdapter {

        private LayoutInflater mInflater;

        int mItemLayout;
        int mGroupLayout;
        Context mContext;
        private GroupViewHolder groupHolder;
        private ChildViewHolder childHolder;
        ArrayList<NoticeData> mData;

        public ExpandableListAdapter(Context ctx, int itemLayout,
                                     int groupLayout, ArrayList<NoticeData> data) {

            mInflater = LayoutInflater.from(ctx);

            mData = data;

            mContext = ctx;
            mItemLayout = itemLayout;
            mGroupLayout = groupLayout;
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

            return 1;
        }

        @Override
        public View getChildView(final int groupPosition,
                                 final int childPosition, boolean isLastChild, View convertView,
                                 ViewGroup parent) {

            childHolder = null;

            if (convertView == null) {

                convertView = mInflater.inflate(mItemLayout, parent, false);

                childHolder = new ChildViewHolder();

                childHolder.mTitleText = (TextView) convertView.findViewById(R.id.TitleText);
                childHolder.mTitleSubText = (TextView) convertView.findViewById(R.id.TitleSubText);

                convertView.setTag(childHolder);

            } else {

                childHolder = (ChildViewHolder) convertView.getTag();
            }

            final NoticeData data = mData.get(groupPosition);

            childHolder.mTitleText.setText(data.mContents);

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

                groupHolder.mTitleText = (TextView) convertView.findViewById(R.id.TitleText);
                groupHolder.mTitleSubText = (TextView) convertView.findViewById(R.id.TitleSubText);
                groupHolder.mArrowBtn = (ImageView) convertView.findViewById(R.id.ArrowBtn);

                convertView.setTag(groupHolder);

            } else {

                groupHolder = (GroupViewHolder) convertView.getTag();
            }

            final NoticeData data = mData.get(groupPosition);


            groupHolder.mTitleText.setText(data.mTitle);

            groupHolder.index = groupPosition;

            if (mExpandListView.isGroupExpanded(groupPosition)) {

                groupHolder.mArrowBtn.setImageResource(R.drawable.open);

            } else {

                groupHolder.mArrowBtn.setImageResource(R.drawable.closed);

            }


            convertView.setOnClickListener(mGroupClickListener);

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

        private View.OnClickListener mGroupClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                GroupViewHolder holder = (GroupViewHolder) v.getTag();

                final int index = holder.index;

                if (mExpandListView.isGroupExpanded(index)) {

                    mExpandListView.collapseGroup(index);

                } else {

                    mExpandListView.expandGroup(index, true);
                    mExpandListView.smoothScrollToPosition(index);

                }


            }
        };



        private class GroupViewHolder {

            ImageView mArrowBtn;
            TextView mTitleText;
            TextView mTitleSubText;
            int index;

        }

        private class ChildViewHolder {

            TextView mTitleText;
            TextView mTitleSubText;

            int index;
        }

    }

}
