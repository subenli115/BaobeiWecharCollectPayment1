package com.atech.staggedrv;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atech.staggedrv.model.StaggedModel;

import java.util.ArrayList;
import java.util.List;


public abstract class StaggedAdapter<T extends StaggedModel> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<T>datas = new ArrayList<>();
    private Context c;
    public StaggedAdapter(Context c){
        this.c = c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return addViewHolder(viewGroup,i);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        bindView(viewHolder, i);

    }

    @Override
    public int getItemCount() {
         return datas.size();
    }

    /**
     * 下拉刷新
     * @param datas
     */
    public void refresh(final List<T> datas){

        this.datas = datas;
        //更新数据的时候使用handler
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                notifyItemRangeChanged(0, datas.size());
            }
        });



    }

    /**
     * 加载更多
     * @param datas
     */
    public void loadMore(List<T> datas){

        int startPos = this.datas.size() - 1;

        notifyItemRangeInserted(startPos, datas.size());
        this.datas.addAll(datas);

    }

    public abstract RecyclerView.ViewHolder addViewHolder(ViewGroup viewGroup, int i);

    public abstract void bindView(RecyclerView.ViewHolder viewHolder, int i);

}
