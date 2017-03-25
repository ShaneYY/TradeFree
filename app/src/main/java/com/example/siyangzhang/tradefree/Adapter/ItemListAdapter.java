package com.example.siyangzhang.tradefree.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.siyangzhang.tradefree.Util.ACache;
import com.example.siyangzhang.tradefree.R;
import com.example.siyangzhang.tradefree.Bean.Item;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    Context mContext;
    LayoutInflater mInflater;

    ACache mCache;

    List<Item> ItemDatas;

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.onItemClickListener=listener;
    }

    public ItemListAdapter(List<Item> Datas, Context context) {
        ItemDatas = Datas;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        mCache=ACache.get(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view=mInflater.inflate(R.layout.item,parent,false);
        ItemViewHolder ItemViewHolder=new ItemViewHolder(view);

        return ItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.title.setText(ItemDatas.get(position).getTitle());
        String strdetail=ItemDatas.get(position).getDetail();
        Pattern p2= Pattern.compile("<img src=.*>");
        Matcher m=p2.matcher(strdetail);
        while (m.find()) {
            holder.detail.setText(strdetail.replaceAll(m.group(), "Photo"));
        }
        setUpItemEvent(holder);
    }
    @Override
    public int getItemCount() {
        return ItemDatas.size();
    }

    protected void setUpItemEvent(final ItemViewHolder holder){
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutposition = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, layoutposition);
                }
            });

            //longonclick
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutposition = holder.getLayoutPosition();

                    onItemClickListener.onItemLongClick(holder.itemView, layoutposition);
                    return false;
                }
            });
        }
    }
}


class ItemViewHolder extends RecyclerView.ViewHolder{

    TextView title,detail;
    ImageButton like;

    public ItemViewHolder(View itemView) {
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.item_title);
        detail= (TextView) itemView.findViewById(R.id.item_detail);
        like= (ImageButton) itemView.findViewById(R.id.item_like);

    }
}
