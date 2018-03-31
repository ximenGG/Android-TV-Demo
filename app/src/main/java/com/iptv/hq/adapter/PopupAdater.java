package com.iptv.hq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.util.Util;
import com.iptv.hq.R;
import com.iptv.hq.common.UrlRunnable;
import com.iptv.hq.bean.PlayListBean;
import com.iptv.hq.bean.VideoInfo;
import com.iptv.hq.utils.ThreadPool;

import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
 * 　　    ()  　　  ()
 * 　　   ( ) 　　　( )
 * 　　   ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * Created by HQ on 2017/12/18.
 */
public class PopupAdater extends RecyclerView.Adapter<PopupAdater.ViewHolder> {
    private List<PlayListBean.DataListBean> dataList;

    public PopupAdater(List<PlayListBean.DataListBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.item_popup, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PlayListBean.DataListBean dataListBean = dataList.get(position);
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setResCode(dataListBean.getCode());
        videoInfo.setVideoType("1");
        videoInfo.setTitle(dataListBean.getName() + "-" + dataListBean.getArtistName());
        ThreadPool.execute(new UrlRunnable("MFZHb3c9336679880fa5", videoInfo) {
            @Override
            public void onSuccess(VideoInfo videoInfo) {
                holder.title.setText(videoInfo.getTitle());
                String durationTime = Util.getStringForTime(new StringBuilder(), new Formatter(new StringBuilder(), Locale.getDefault()), videoInfo.getDuration());
                holder.time.setText(durationTime);
                System.out.println(videoInfo.getCoverURL());
                Glide.with(holder.imageView.getContext())
                        .load(videoInfo.getCoverURL())
                        .apply(RequestOptions.skipMemoryCacheOf(true))
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(holder.imageView);
            }

            @Override
            public void onError(VideoInfo videoInfo) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, time;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_detail);
            title = (TextView) itemView.findViewById(R.id.popup_title);
            time = (TextView) itemView.findViewById(R.id.popup_time);
        }
    }
}
