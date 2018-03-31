package com.iptv.hq.ui.views.exoplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.iptv.hq.R;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.bean.VideoInfo;
import com.wnafee.vector.MorphButton;

import java.util.Formatter;
import java.util.HashMap;
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
 * Created by HQ on 2017/12/03.
 */
public class ExoVideoView extends RelativeLayout {
    /****日志*/
    private String TAG = "ExoVideoView";
    /**********************************控件相关**********************************/
    /***播放显示*/
    private MorphButton ivPlayIcon;
    /***加载*/
    private ProgressBar progressBar;
    /***渲染*/
    private TextureView textureview;
    /***标题*/
    private TextView exoTitle;
    /***当前进度*/
    private TextView exoPosition;
    /***进度条*/
    private TimeBar exoProgress;
    /***总时长*/
    private TextView exoDuration;
    /***显示时间进度的根布局*/
    private LinearLayout exoTime;
    /**********************************控件相关**********************************/


    /****播放器核心*/
    private SimpleExoPlayer mExoPlayer;
    /***状态监听器*/
    private ComponentListener componentListener;
    private CompleteListener completeListener;
    private StringBuilder formatBuilder = new StringBuilder();
    private Formatter formatter = new Formatter(formatBuilder, Locale.getDefault());
    /****事件日志输出*/
    private EventLogger eventLogger;
    /***渲染器，默认有四个渲染器*/
    private MappingTrackSelector trackSelector;
    private HashMap<String, VideoInfo> hashMap = new HashMap<>();
    /***显示的时间*/
    private int showTimeoutMs = 5000;
    private int rewindMs = 5000;
    private int fastForwardMs = 15000;
    private long duration;
    private final Runnable hideAction = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            updateProgress();
        }
    };
    Runnable hideProgress = new Runnable() {
        @Override
        public void run() {
            ivPlayIcon.setVisibility(GONE);
        }
    };

    public ExoVideoView(Context context) {
        this(context, null);
    }

    public ExoVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExoVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();//初始化
        initPlayer();//初始化播放器
    }


    private void initPlayer() {
        componentListener = new ComponentListener();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        eventLogger = new EventLogger(trackSelector);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(AppOtt.mContext, null, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON), trackSelector);
        mExoPlayer.setVideoTextureView(textureview);
        mExoPlayer.addListener(componentListener);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.exo_videoview, this);
        setBackgroundColor(Color.BLACK);
        ivPlayIcon = (MorphButton) findViewById(R.id.iv_play_icon);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textureview = (TextureView) findViewById(R.id.textureview);
        exoTitle = (TextView) findViewById(R.id.exo_title);
        exoPosition = (TextView) findViewById(R.id.exo_position);
        exoProgress = (TimeBar) findViewById(R.id.exo_progress);
        exoDuration = (TextView) findViewById(R.id.exo_duration);
        exoTime = (LinearLayout) findViewById(R.id.exo_time);
    }

    public void play(VideoInfo videoInfo) {
        hashMap.put(videoInfo.getPlayURL(), videoInfo);
        this.duration = videoInfo.getDuration();
        play(buildMediaSource(Uri.parse(videoInfo.getPlayURL()), null));
        exoTitle.setText(videoInfo.getTitle());
    }

    /**
     * 播放单个视频
     */
    public void play(MediaSource mediaSource) {
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(true);
            mExoPlayer.prepare(mediaSource);
        }
        show(true);
        ivPlayIcon.setVisibility(GONE);
    }

    /**
     * 设置播放器
     *
     * @param player
     */
    public void setPlayer(SimpleExoPlayer player) {
        if (this.mExoPlayer == player) {
            return;
        }
        if (this.mExoPlayer != null) {
            this.mExoPlayer.removeListener(componentListener);
            this.mExoPlayer.clearVideoTextureView(textureview);

        }
        this.mExoPlayer = player;
        if (player != null) {
            player.setVideoTextureView(textureview);
            player.addListener(componentListener);
        }
    }

    public SimpleExoPlayer getmExoPlayer() {
        return mExoPlayer;
    }

    /***
     * 显示控制和播放列表
     */
    public void show(boolean isHide) {
        removeCallbacks(hideAction);
        updateProgress();
        showAnim();
        if (isHide) hideAfterTimeout();
    }

    /**
     * 显示的动画
     */
    private void showAnim() {
        exoTime.setVisibility(VISIBLE);
        exoTitle.setVisibility(VISIBLE);
    }

    /**
     * 隐藏控制和播放列表
     */
    public void hide() {
        removeCallbacks(updateProgress);
        hideAnim();
    }

    /***
     *更新播放进度
     */
    public void updateProgress() {
        long contentPosition = mExoPlayer.getContentPosition();
        long duration = mExoPlayer.getDuration() < 0 ? 0 : mExoPlayer.getDuration();
        long bufferPosition = mExoPlayer.getBufferedPosition();
        String contentTime = Util.getStringForTime(formatBuilder, formatter, contentPosition);
        String durationTime = Util.getStringForTime(formatBuilder, formatter, duration);
        exoProgress.setPosition(contentPosition);
        exoProgress.setBufferedPosition(bufferPosition);
        exoProgress.setDuration(duration);
        exoPosition.setText(contentTime);
        exoDuration.setText(durationTime);
        int playbackState = mExoPlayer == null ? Player.STATE_IDLE : mExoPlayer.getPlaybackState();
        if (playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED) {
            postDelayed(updateProgress, 1000);
        }
    }

    /**
     * 隐藏动画
     */
    private void hideAnim() {
        exoTime.setVisibility(GONE);
        exoTitle.setVisibility(GONE);
    }

    /**
     * 显示多长时间隐藏
     */
    private void hideAfterTimeout() {
        if (mExoPlayer.getPlayWhenReady()) {
            removeCallbacks(hideAction);
            if (showTimeoutMs > 0) {
                postDelayed(hideAction, showTimeoutMs);
            }
        }
    }

    /***
     * 构建媒体资源
     * @param uri
     * @param overrideExtension
     * @return
     */
    public MediaSource buildMediaSource(Uri uri, String overrideExtension) {
        int type = Util.inferContentType(!TextUtils.isEmpty(overrideExtension) ? "." + overrideExtension : uri.getLastPathSegment());
        switch (type) {
            case C.TYPE_SS:
                return new SsMediaSource(uri, buildDataSourceFactory(false), new DefaultSsChunkSource.Factory(buildDataSourceFactory(true)), new Handler(Looper.getMainLooper()), eventLogger);
            case C.TYPE_DASH:
                return new DashMediaSource(uri, buildDataSourceFactory(false),
                        new DefaultDashChunkSource.Factory(buildDataSourceFactory(true)), new Handler(Looper.getMainLooper()), eventLogger);
            case C.TYPE_HLS:
                return new HlsMediaSource(uri, buildDataSourceFactory(true), new Handler(Looper.getMainLooper()), eventLogger);
            case C.TYPE_OTHER:
                return new ExtractorMediaSource(uri, buildDataSourceFactory(true), new DefaultExtractorsFactory(), new Handler(Looper.getMainLooper()), eventLogger);
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }

    /**
     * 流量统计
     */
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

    /**
     * 构建数据工厂
     *
     * @param useBandwidthMeter 是否添加流量统计
     * @return
     */
    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    /**
     * 构建数据工厂
     *
     * @param bandwidthMeter 流量统计
     * @return
     */
    DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(getContext().getApplicationContext(), bandwidthMeter, buildHttpDataSourceFactory(bandwidthMeter));
    }

    /**
     * 构建http数据工厂
     *
     * @param bandwidthMeter 流量统计
     * @return
     */
    HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(Util.getUserAgent(getContext().getApplicationContext(), TAG), bandwidthMeter);
    }

    private final class ComponentListener implements Player.EventListener {
        /**
         * 播放时间的改变
         *
         * @param timeline
         * @param manifest
         */
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            updateProgress();
        }

        /**
         * 轨道改变
         *
         * @param trackGroups
         * @param trackSelections
         */
        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

        }

        /**
         * 加载状态改变
         *
         * @param isLoading
         */
        @Override
        public void onLoadingChanged(boolean isLoading) {

        }

        /**
         * 播放状态的改变
         *
         * @param playWhenReady
         * @param playbackState
         */
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            updateProgress();
            Log.d(TAG, "onPlayerStateChanged: " + playWhenReady + "----" + playbackState);
            if (playbackState == Player.STATE_READY) {
                progressBar.setVisibility(GONE);
                hideAfterTimeout();
            } else {
                progressBar.setVisibility(VISIBLE);
            }
            if (playbackState == Player.STATE_ENDED && completeListener != null) {
                completeListener.onComplete();
            }
        }

        /**
         * 播放模式的改变
         *
         * @param repeatMode
         */
        @Override
        public void onRepeatModeChanged(int repeatMode) {

        }

        @Override
        public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

        }

        /**
         * 播放错误
         *
         * @param error
         */
        @Override
        public void onPlayerError(ExoPlaybackException error) {

        }

        @Override
        public void onPositionDiscontinuity(@Player.DiscontinuityReason int reason) {
            updateProgress();
        }

        /**
         * 播放参数的改变
         *
         * @param playbackParameters
         */
        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

        }

        @Override
        public void onSeekProcessed() {

        }
    }

    public void setVolume(float leftVolume, float rightVolume) {
        mExoPlayer.setVolume(leftVolume,rightVolume);
    }

    public void fastForward() {
        if (fastForwardMs <= 0) {
            return;
        }
        long durationMs = mExoPlayer.getDuration();
        long seekPositionMs = mExoPlayer.getCurrentPosition() + fastForwardMs;
        if (durationMs != C.TIME_UNSET) {
            seekPositionMs = Math.min(seekPositionMs, durationMs);
        }
        mExoPlayer.seekTo(seekPositionMs);
    }


    public void rewind() {
        if (rewindMs <= 0) {
            return;
        }
        mExoPlayer.seekTo(Math.max(mExoPlayer.getCurrentPosition() - rewindMs, 0));
    }


    @SuppressLint("InlinedApi")
    public static boolean isHandledMediaKey(int keyCode) {
        return keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD
                || keyCode == KeyEvent.KEYCODE_MEDIA_REWIND
                || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY
                || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE
                || keyCode == KeyEvent.KEYCODE_MEDIA_NEXT
                || keyCode == KeyEvent.KEYCODE_MEDIA_PREVIOUS;
    }

    public void pause() {
        if (mExoPlayer == null) {
            return;
        }
        if (mExoPlayer.getPlayWhenReady()) {
            mExoPlayer.setPlayWhenReady(false);
        }
    }

    public void resume() {
        if (mExoPlayer == null) {
            return;
        }
        if (!mExoPlayer.getPlayWhenReady()) {
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    public void destroy() {
        if (mExoPlayer != null) {
            removeCallbacks(updateProgress);
            removeCallbacks(hideAction);
            removeCallbacks(hideProgress);
            mExoPlayer.release();
            mExoPlayer = null;
            trackSelector = null;
            eventLogger = null;
        }
    }

    public void updatePlayerButton() {
        if (mExoPlayer.getPlayWhenReady()) {
            removeCallbacks(hideProgress);
            ivPlayIcon.setVisibility(VISIBLE);
            ivPlayIcon.setState(MorphButton.MorphState.START, true);
            show(false);
        } else {
            ivPlayIcon.setState(MorphButton.MorphState.END, true);
            postDelayed(hideProgress, 1000);
        }
        mExoPlayer.setPlayWhenReady(!mExoPlayer.getPlayWhenReady());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            show(true);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            updatePlayerButton();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setCompleteListener(CompleteListener completeListener) {
        this.completeListener = completeListener;
    }


    public interface CompleteListener {
        void onComplete();
    }
}
