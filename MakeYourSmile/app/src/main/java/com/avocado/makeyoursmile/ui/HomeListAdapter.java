package com.avocado.makeyoursmile.ui;


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
import com.avocado.makeyoursmile.util.CircleTransformation;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = HomeListAdapter.class.getCanonicalName();

    public enum ItemType {
        HEADER,
        ITEM,
        FOOTER
    }

    private ArrayList<DentistData> mData;
    Context mContext;
    public HomeListAdapter(Context context, ArrayList<DentistData> data) {
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

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_home, parent, false);
                ViewHolder holder = new ViewHolder(v);
                holder.itemView.setOnClickListener(this);
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

                DentistData data = mData.get(position);
                ViewHolder h = (ViewHolder) holder;
                h.mTitleTxt.setText(data.NAME);


                h.mTagLay.removeAllViews();


                if (TextUtils.isEmpty(data.HASH_TAG_1) == false) {

                    String[] hash_tags = data.HASH_TAG_1.split(Constants.SEPARATER);

                    for (int i = 0; i < hash_tags.length; i++) {

                        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_taglay, null);
                        ((TextView)v.findViewById(R.id.TagText)).setText(hash_tags[i]);
                        h.mTagLay.addView(v);

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

                    addrBuffer.append("\n").append(data.ADDRESS3);

                }

                if(TextUtils.isEmpty(addrBuffer.toString()) == false) {

                    h.mSubTitleTxt.setText(addrBuffer.toString());
                }


                if(TextUtils.isEmpty(data.IMG_1) == false) {

                    h.mProfileImg.setVisibility(View.VISIBLE);
                    h.mProfileDftImg.setVisibility(View.GONE);
                    Uri uri = Uri.parse(data.IMG_1);
                    Glide.with(mContext)
                            .load(uri)
                            .transform(new CircleTransformation(mContext))
                            .into(h.mProfileImg);

                }
                else {

                    h.mProfileDftImg.setVisibility(View.VISIBLE);
                    h.mProfileImg.setVisibility(View.GONE);

                }



                h.itemView.setTag(data);





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

    public void setData(ArrayList<DentistData> data) {

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
        private TextView mTitleTxt, mSubTitleTxt;
        private ImageView mProfileImg, mEventImg, mProfileDftImg;
        private ViewGroup mTagLay;


        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTxt = (TextView) itemView.findViewById(R.id.TitleTxt);
            mSubTitleTxt = (TextView) itemView.findViewById(R.id.SubTitleTxt);

            mProfileImg = (ImageView) itemView.findViewById(R.id.ProfileImg);
            mProfileDftImg  = (ImageView) itemView.findViewById(R.id.ProfileDftImg);
            mEventImg = (ImageView) itemView.findViewById(R.id.EventImg);

            mTagLay = (ViewGroup) itemView.findViewById(R.id.TagLay);

        }

    }

}
