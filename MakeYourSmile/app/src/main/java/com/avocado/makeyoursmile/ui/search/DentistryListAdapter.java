package com.avocado.makeyoursmile.ui.search;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avocado.makeyoursmile.Constants;
import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.network.data.dentist.DentistData;
import com.avocado.makeyoursmile.view.AVTextView;
import com.bumptech.glide.Glide;

import java.util.List;

public class DentistryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = DentistryListAdapter.class.getCanonicalName();

    public enum ItemType {
        HEADER,
        ITEM,
        FOOTER
    }

    private List<DentistData> mData;
    Context mContext;
    public DentistryListAdapter(Context context, List<DentistData> data) {
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

                final DentistData data = mData.get(position);
                final ViewHolder h = (ViewHolder) holder;

                h.mTitleTxt.setText(data.NAME);

                h.mAddTagLay.removeAllViews();

                if (TextUtils.isEmpty(data.HASH_TAG_1) == false) {

                    String[] hash_tags = data.HASH_TAG_1.split(Constants.SEPARATER);

                    for (int i = 0; i < hash_tags.length; i++) {

                        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_taglay_top_padding, null);
                        ((TextView)v.findViewById(R.id.TagText)).setText(hash_tags[i]);
                        h.mAddTagLay.addView(v);

                    }
                }

                StringBuffer addrBuffer = new StringBuffer();

                if(TextUtils.isEmpty(data.ADDRESS1) == false) {

                    addrBuffer.append(data.ADDRESS1);

                }
                if(TextUtils.isEmpty(data.ADDRESS2) == false) {

                    addrBuffer.append(" ").append(data.ADDRESS2);

                }
                if(TextUtils.isEmpty(data.ADDRESS3) == false) {

                    addrBuffer.append(" ").append(data.ADDRESS3);

                }

                if(TextUtils.isEmpty(data.ADDRESS4) == false) {

                    addrBuffer.append("\n").append(data.ADDRESS4);

                }

                if(TextUtils.isEmpty(addrBuffer.toString()) == false) {

                    h.mSubTitleTxt.setText(addrBuffer.toString());
                }


                if(TextUtils.isEmpty(data.IMG_1) == false) {

                    h.mDentistImg.setVisibility(View.VISIBLE);
                    h.mDentistSampleImg.setVisibility(View.GONE);
                    Uri uri = Uri.parse(data.IMG_1);
                    Glide.with(mContext)
                            .load(uri)
                            .into(h.mDentistImg);

                }
                else {

                    h.mDentistSampleImg.setVisibility(View.VISIBLE);
                    h.mDentistImg.setVisibility(View.GONE);

                }

                h.itemView.setTag(data);
                h.itemView.setOnClickListener(this);

                h.mLandingBntImg.setTag(data);
                h.mLandingBntImg.setOnClickListener(this);

                break;
            }
        }

    }


    public void add(DentistData data) {

        if(!mData.contains(data)) {
            mData.add(data);
            notifyItemInserted( mData.size()+  -1 );
        }
    }

    public void setData(List<DentistData> data) {

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
        private ImageView mDentistImg,mLandingBntImg, mEventImg, mDentistSampleImg;
        ViewGroup mAddTagLay;

        public ViewHolder(View itemView) {
            super(itemView);

            mDentistImg = (ImageView) itemView.findViewById(R.id.DentistImg);
            mDentistSampleImg = (ImageView) itemView.findViewById(R.id.DentistSampleImg);
            mLandingBntImg = (ImageView) itemView.findViewById(R.id.LandingBntImg);
            mEventImg = (ImageView) itemView.findViewById(R.id.EventImg);

            mTitleTxt = (AVTextView) itemView.findViewById(R.id.TitleTxt);
            mSubTitleTxt = (AVTextView) itemView.findViewById(R.id.SubTitleTxt);
            mDistanceTxt = (AVTextView) itemView.findViewById(R.id.DistanceTxt);

            mAddTagLay = (ViewGroup)itemView.findViewById(R.id.AddTagLay);

        }

    }

}
