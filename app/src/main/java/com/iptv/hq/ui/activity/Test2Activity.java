package com.iptv.hq.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.iptv.hq.R;
import com.iptv.hq.view.RequestViews;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.common.UrlRunnable;
import com.iptv.hq.bean.Bean;
import com.iptv.hq.bean.PlayListBean;
import com.iptv.hq.bean.VideoInfo;
import com.iptv.hq.model.VideoListModel;
import com.iptv.hq.ui.views.dialog.EqualizerDialog;
import com.iptv.hq.ui.views.exoplayer.ExoVideoView;
import com.iptv.hq.ui.views.popwindow.PopupRecycler;
import com.iptv.hq.ui.views.popwindow.PopupVideoControl;
import com.iptv.hq.utils.HandlerUtils;
import com.iptv.hq.utils.ThreadPool;
import com.iptv.hq.utils.ToastUtils;

import java.util.List;

import retrofit2.Call;

public class Test2Activity extends AppCompatActivity implements RequestViews, PopupVideoControl.OnItemClickListener {
    private ExoVideoView exoplay;
    private FrameLayout container;
    private PopupVideoControl popupVideoControl;
    private List<PlayListBean.DataListBean> dataList;
    private int current = 0;
    private EqualizerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        container = (FrameLayout) findViewById(R.id.container);
        exoplay = (ExoVideoView) findViewById(R.id.exoplay);
        popupVideoControl = new PopupVideoControl(this);
        popupVideoControl.setOnItemClickListener(this);
        String code = getIntent().getStringExtra("code");
        if (code != null) {
            VideoListModel presenter = new VideoListModel();
            presenter.getPlayList(code, this);
        }
        exoplay.setCompleteListener(new ExoVideoView.CompleteListener() {
            @Override
            public void onComplete() {
                nextSong();
            }
        });
    }

    public void analysis(int current, String videoType) {
        PlayListBean.DataListBean dataListBean = dataList.get(current);
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setResCode(dataListBean.getCode());
        videoInfo.setVideoType(videoType);
        videoInfo.setTitle(dataListBean.getName() + "-" + dataListBean.getArtistName());
        ThreadPool.execute(new UrlRunnable("MFZHb3c9336679880fa5", videoInfo) {
            @Override
            public void onSuccess(VideoInfo videoInfo) {
                exoplay.play(videoInfo);
            }

            @Override
            public void onError(VideoInfo videoInfo) {
            }
        });
    }

    @Override
    public void onResponse(Call call, Bean model, int id) {
        if (id == 0) {
            if (id == 0) {
                PlayListBean playListModel = (PlayListBean) model;
                dataList = playListModel.getDataList();
                analysis(current, "1");
            }
        }
    }

    @Override
    public void onFailure(Call call, Throwable throwable, int id) {
        nextSong();
    }

    @Override
    public void onVideoList(List<VideoInfo> videoInfos) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            popupVideoControl.showAtLocation(container, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            popupVideoControl.getContentView().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
                @Override
                public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                    HandlerUtils.removeOnMainCallback(hidePop);
                    HandlerUtils.runOnMainThread(hidePop, 5000);
                }
            });
            HandlerUtils.runOnMainThread(hidePop, 5000);
            return true;
        }
        return exoplay.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    boolean volume = false;

    @Override
    public void setOnItemClick(View v) {
        HandlerUtils.removeOnMainCallback(hidePop);
        switch (v.getId()) {
            case R.id.repeat:
                ToastUtils.toast(AppOtt.mContext, "重唱");
                exoplay.getmExoPlayer().seekTo(0);
                exoplay.show(true);
                break;
            case R.id.next_song:
                nextSong();
                break;
            case R.id.sound_effect:
                dialog = new EqualizerDialog(this);
                dialog.setAudioSessionId(exoplay.getmExoPlayer().getAudioSessionId());
                dialog.show();
                break;
            case R.id.yuan_ban:
                if (volume) {
                    exoplay.setVolume(0, 1);
                    ToastUtils.toast(AppOtt.mContext, "原唱");
                } else {
                    exoplay.setVolume(1, 0);
                    ToastUtils.toast(AppOtt.mContext, "伴唱");
                }
                volume = !volume;
                break;
            case R.id.song_list:
                ToastUtils.toast(AppOtt.mContext, "歌单");
                popupVideoControl.dismiss();
                showVideoList();
                break;
            default:
                break;
        }
        HandlerUtils.runOnMainThread(hidePop, 5000);
    }

    private void showVideoList() {
        PopupRecycler popupRecycler = new PopupRecycler(this);
        popupRecycler.setAdapter(dataList);
        popupRecycler.showAtLocation(container, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 50);
    }

    public void nextSong() {
        ToastUtils.toast(AppOtt.mContext, "下一首");
        current = ++current > dataList.size() - 1 ? 0 : current;
        analysis(current, "1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        exoplay.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoplay.destroy();
        if (popupVideoControl != null) {
            popupVideoControl.dismiss();
            popupVideoControl = null;
        }
        if (dialog!=null){
            dialog.dismiss(true);
        }
        HandlerUtils.removeOnMainCallback(hidePop);
    }

    @Override
    protected void onPause() {
        super.onPause();
        exoplay.pause();
    }


    Runnable hidePop = new Runnable() {
        @Override
        public void run() {
            if (popupVideoControl != null)
                popupVideoControl.dismiss();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
