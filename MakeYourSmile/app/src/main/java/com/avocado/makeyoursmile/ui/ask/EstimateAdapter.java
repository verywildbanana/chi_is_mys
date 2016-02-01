package com.avocado.makeyoursmile.ui.ask;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avocado.makeyoursmile.R;
import com.avocado.makeyoursmile.network.data.host.Answer;
import com.avocado.makeyoursmile.network.data.user.Ask;
import com.avocado.makeyoursmile.view.AVTextView;

import java.util.List;

public class EstimateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = EstimateAdapter.class.getCanonicalName();

    public enum ItemType {
        HEADER,
        ITEM,
        FOOTER
    }

    private Ask mHeaderData;
    private List<Answer> mData;
    Context mContext;
    public EstimateAdapter(Context context, List<Answer> data) {
        mContext = context;
        this.mData =data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        ItemType type = viewTypeToItemType(viewType);
        switch (type) {

            case HEADER:

                View v = LayoutInflater.from(parent.getContext()).inflate(mHeader, parent, false);
                HeaderViewHolder headerViewHolder = new HeaderViewHolder(v);
                headerViewHolder.itemView.setOnClickListener(mHeaderClickListener);
                return headerViewHolder;

            case FOOTER:

                return null;

            case ITEM:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_ask_estimate, parent, false);
                ViewHolder holder = new ViewHolder(v);
                holder.itemView.setOnClickListener(this);
                return holder;
        }
        return null;

    }


    @Override
    public int getItemViewType(int position) {

        if (useHeader() && position == 0) {

            return itemTypeToViewType(ItemType.HEADER);
        }
        else {

            return itemTypeToViewType(ItemType.ITEM);
        }
    }

    @Override
    public int getItemCount() {

        int itemCount = getBasicItemCount();

        if (useHeader()) {
            itemCount += 1;
        }
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

    public boolean useHeader() {

        return mHeader != 0;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        int viewType = getItemViewType(position);
        switch (viewTypeToItemType(viewType)) {

            case HEADER: {

                HeaderViewHolder h = (HeaderViewHolder) holder;
                h.mAddressTxt.setText(mHeaderData.mAddress);
                break;
            }

            case ITEM: {

                int realPosition = useHeader() ? position - 1 : position;

                ViewHolder h = (ViewHolder) holder;

                Answer data = mData.get(realPosition);
                final String name = data.mStoreName;

                h.mNameTxt.setText(name);

                break;
            }
        }

    }


    public void add(Answer data, int position) {

        if(!mData.contains(data)) {
            mData.add(position, data);
            notifyItemInserted(position);
        }
    }
    public void add(Answer data) {

        if(!mData.contains(data)) {
            mData.add(data);
            notifyItemInserted( mData.size()+ (useHeader() ? 1 : 0) -1 );
        }
    }

    public void setData(List<Answer> data) {

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

        private ImageView mProfileImg;
        private ImageView mCallImg;

        private AVTextView mDateTxt;
        private AVTextView mNameTxt;
        private AVTextView mDescribeTxt;

        public ViewHolder(View itemView) {
            super(itemView);

            mProfileImg = (ImageView) itemView.findViewById(R.id.ProfileImg);
            mCallImg = (ImageView) itemView.findViewById(R.id.CallImg);

            mDateTxt = (AVTextView) itemView.findViewById(R.id.DateTxt);
            mNameTxt = (AVTextView) itemView.findViewById(R.id.NameTxt);
            mDescribeTxt = (AVTextView) itemView.findViewById(R.id.DescribeTxt);

        }

    }

    int mHeader = 0;
    View.OnClickListener mHeaderClickListener;

    protected void addHeaderView(int header, View.OnClickListener clickListener, Ask data) {

        mHeader = header;
        mHeaderClickListener = clickListener;
        mHeaderData = data;
    }


    public static class HeaderViewHolder extends RecyclerView.ViewHolder  {
        private ImageView mProfileImg;
        private AVTextView mDateTxt;
        private AVTextView mEditTxt;
        private AVTextView mDeleteTxt;
        private AVTextView mAddressTxt;
        private AVTextView mGenderTxt;
        private AVTextView mAskTxt;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mProfileImg = (ImageView) itemView.findViewById(R.id.ProfileImg);

            mDateTxt = (AVTextView) itemView.findViewById(R.id.DateTxt);
            mEditTxt = (AVTextView) itemView.findViewById(R.id.EditTxt);
            mDeleteTxt = (AVTextView) itemView.findViewById(R.id.DeleteTxt);

            mAddressTxt = (AVTextView) itemView.findViewById(R.id.AddressTxt);
            mGenderTxt = (AVTextView) itemView.findViewById(R.id.GenderTxt);
            mAskTxt = (AVTextView) itemView.findViewById(R.id.AskTxt);
        }
    }

}
