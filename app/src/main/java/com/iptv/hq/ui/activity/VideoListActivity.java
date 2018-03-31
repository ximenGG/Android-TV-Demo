package com.iptv.hq.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import com.iptv.hq.R;
import com.iptv.hq.view.RequestViews;
import com.iptv.hq.view.RequestView;
import com.iptv.hq.bean.Bean;
import com.iptv.hq.bean.PlayListBean;
import com.iptv.hq.bean.VideoInfo;
import com.iptv.hq.model.VideoListModel;
import com.iptv.hq.presenter.VideoListPresenter;
import com.iptv.hq.ui.views.exoplayer.ExoVideoView_LX;
import java.util.List;
import retrofit2.Call;

public class VideoListActivity extends BaseActivity<VideoListModel,RequestViews,VideoListPresenter> implements RequestView, RequestViews {
    private ExoVideoView_LX mExoVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mExoVideoView = (ExoVideoView_LX) findViewById(R.id.exoplay);
        String code = getIntent().getStringExtra("code");
        if (code != null) {
            mPresenter.showPlayList(code);
        }
    }

    @Override
    protected VideoListModel createModel() {
        return new VideoListModel();
    }

    @Override
    protected VideoListPresenter createPresenter() {
        return new VideoListPresenter();
    }

    @Override
    public void onResponse(Call call, Bean model, int id) {
        if (id == 0) {
            PlayListBean playListModel = (PlayListBean) model;
            mPresenter.analysisList(playListModel.getDataList());
        }
    }

    @Override
    public void onFailure(Call call, Throwable throwable, int id) {

    }

    @Override
    public void onVideoList(List<VideoInfo> videoInfos) {
        mExoVideoView.play(videoInfos, 0);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mExoVideoView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExoVideoView.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mExoVideoView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mExoVideoView.pause();
    }


}
