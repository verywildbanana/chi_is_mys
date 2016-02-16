package com.avocado.makeyoursmile.ui.search;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.view.AVTextView;

import java.util.List;

public class DentistryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = DentistryListAdapter.class.getCanonicalName();

    public enum ItemType {
        HEADER,
        ITEM,
        FOOTER
    }

    private List<String> mData;
    Context mContext;
    public DentistryListAdapter(Context context, List<String> data) {
        mContext = context;
        this.mData =data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        ItemType type = viewTypeToItemType(viewType);
        switch (type) {

            case HEADER:

                return null;

            case FOOTER:

                return null;

            case ITEM:

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_search_dentistry, parent, false);
                ViewHolder holder = new ViewHolder(v);
                return holder;
        }
        return null;

    }


    @Override
    public int getItemViewType(int position) {

        return itemTypeToViewType(ItemType.ITEM);
    }

    @Override
    public int getItemCount() {

        int itemCount = getBasicItemCount();
        return itemCount;
    }

    private int itemTypeToViewType(ItemType itemType) {

        return itemType.ordinal();
    }

    private ItemType viewTypeToItemType(int viewType) {
        return ItemType.values()[viewType];
    }


    public int getBasicItemCount() {


        if(mData == null) {

            return  0;
        }
        else {

            return  mData.size();
        }

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        int viewType = getItemViewType(position);
        switch (viewTypeToItemType(viewType)) {

            case HEADER: {


                break;
            }

            case FOOTER: {

                break;
            }
            case ITEM: {

                int realPosition =  position;

                String data = mData.get(position);
                ViewHolder h = (ViewHolder) holder;
                h.mTitleTxt.setText(data);
                h.itemView.setTag(data);
                h.itemView.setOnClickListener(this);
                h.mLandingBntImg.setTag(data);
                h.mLandingBntImg.setOnClickListener(this);

                break;
            }
        }

    }


    public void add(String data) {

        if(!mData.contains(data)) {
            mData.add(data);
            notifyItemInserted( mData.size()+  -1 );
        }
    }

    public void setData(List<String> data) {

        mData = data;
        notifyDataSetChanged();

    }


    public void clear(){
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }


    public static class ViewHolder extends RecyclerView.ViewHolder  {
        private AVTextView mTitleTxt, mSubTitleTxt, mDistanceTxt;
        private ImageView mDentistImg,mLandingBntImg, mEventImg;

        public ViewHolder(View itemView) {
            super(itemView);

            mDentistImg = (ImageView) itemView.findViewById(R.id.DentistImg);
            mLandingBntImg = (ImageView) itemView.findViewById(R.id.LandingBntImg);
            mEventImg = (ImageView) itemView.findViewById(R.id.EventImg);

            mTitleTxt = (AVTextView) itemView.findViewById(R.id.TitleTxt);
            mSubTitleTxt = (AVTextView) itemView.findViewById(R.id.SubTitleTxt);
            mDistanceTxt = (AVTextView) itemView.findViewById(R.id.DistanceTxt);
        }

    }

}
