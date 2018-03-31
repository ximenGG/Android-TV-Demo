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
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.exoplayer2.C;
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
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.iptv.hq.R;
import com.iptv.hq.bean.VideoInfo;
import java.util.ArrayList;
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
 * Created by HQ on 2017/12/03.
 */
public class ExoVideoView_LX extends RelativeLayout {
    /****日志*/
    private String TAG = "ExoVideoView_LX";
    /**********************************控件相关**********************************/
    /***播放进度*/
    private SeekBar seekbar;
    /***播放列表*/
    private RelativeLayout relList;
    /***显示当前播放列表页*/
    private TextView tvCur;
    /***右边翻页*/
    private ImageView ivRight;
    /***翻页左边*/
    private ImageView ivLeft;
    /***列表*/
    private ListView screenAudioListView;
    /***控制栏*/
    private RelativeLayout relControl;
    /***播放的时间*/
    private TextView tvShowtime;
    /***收藏*/
    private ImageView ivPlayStar;
    /***原伴唱*/
    private ImageView ivVocalTract;
    /***歌词*/
    private ImageView ivLrc;
    /***播放下一首*/
    private ImageView ivPlayDown;
    /***播放切换为暂停*/
    private ImageView ivPlayOrPause;
    /***播放上一首*/
    private ImageView ivPlayUp;
    /***播放模式切换*/
    private ImageView ivCirculation;
    /***当前播放*/
    private ScTextview scTvName;
    /***播放显示*/
    private ImageView ivPlayIcon;
    /***加载*/
    private ProgressBar progressBar;
    /***渲染*/
    private TextureView textureview;
    /**********************************控件相关**********************************/
    /****记录当前播放的视频*/
    private static int current = 0;
    private static int currentPage;
    private int totalPage;
    /***当前播放模式*/
    private int currentState = Player.REPEAT_MODE_ALL;
    /****播放器核心*/
    private SimpleExoPlayer mExoPlayer;
    /***状态监听器*/
    private ComponentListener componentListener;
    private StringBuilder formatBuilder = new StringBuilder();
    private Formatter formatter = new Formatter(formatBuilder, Locale.getDefault());
    /****事件日志输出*/
    private EventLogger eventLogger;
    /***渲染器，默认有四个渲染器*/
    private DefaultTrackSelector trackSelector;
    /***显示的时间*/
    private int showTimeoutMs = 5000;
    private int rewindMs = 5000;
    private int fastForwardMs = 15000;
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
    boolean isListViewFocus = false;
    private PlayListAdapter mAdapter;
    private List<MediaSource> mediaSources;
    /***播放列表的标题*/
    private List<String> titles;
    private List<String> currentTitles = new ArrayList<>();
    private boolean isLoadComplete = false;

    public ExoVideoView_LX(Context context) {
        this(context, null);
    }

    public ExoVideoView_LX(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExoVideoView_LX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();//初始化
        initPlayer();//初始化播放器
        hideAfterTimeout();
        initListener();
        getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus instanceof ListView) {
                    isListViewFocus = true;
                    if (mOldTVNumber != null) mOldTVNumber.setVisibility(View.INVISIBLE);

                } else {
                    screenAudioListView.setSelection(current % 9);
                    isListViewFocus = false;
                    if (mOldTVNumber != null) mOldTVNumber.setVisibility(View.VISIBLE);
                }

            }
        });
    }


    private void initListener() {
        ivPlayUp.setOnClickListener(componentListener);
        ivPlayOrPause.setOnClickListener(componentListener);
        ivPlayDown.setOnClickListener(componentListener);
        ivPlayStar.setOnClickListener(componentListener);
        ivCirculation.setOnClickListener(componentListener);
    }

    private void initPlayer() {
        componentListener = new ComponentListener();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        eventLogger = new EventLogger(trackSelector);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
        mExoPlayer.setVideoTextureView(textureview);
        mExoPlayer.addListener(componentListener);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.exo_videoview_lx, this);
        textureview = (TextureView) findViewById(R.id.textureview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ivPlayIcon = (ImageView) findViewById(R.id.iv_play_icon);
        scTvName = (ScTextview) findViewById(R.id.sc_tv_name);
        ivCirculation = (ImageView) findViewById(R.id.iv_circulation);
        ivPlayUp = (ImageView) findViewById(R.id.iv_play_up);
        ivPlayOrPause = (ImageView) findViewById(R.id.iv_play_or_pause);
        ivPlayDown = (ImageView) findViewById(R.id.iv_play_down);
        ivLrc = (ImageView) findViewById(R.id.iv_lrc);
        ivVocalTract = (ImageView) findViewById(R.id.iv_vocal_tract);
        ivPlayStar = (ImageView) findViewById(R.id.iv_play_star);
        tvShowtime = (TextView) findViewById(R.id.tv_showtime);
        relControl = (RelativeLayout) findViewById(R.id.rel_control);
        screenAudioListView = (ListView) findViewById(R.id.screenAudioListView);
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        tvCur = (TextView) findViewById(R.id.tv_cur);
        relList = (RelativeLayout) findViewById(R.id.rel_list);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) mExoPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 播放视频列表
     *
     * @param videoInfos
     */
    public void play(List<VideoInfo> videoInfos) {
        play(videoInfos, 0);
    }

    /**
     * 播放视频列表
     *
     * @param videoInfos
     * @param position
     */
    public void play(List<VideoInfo> videoInfos, int position) {
        current = position;
        mediaSources = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        play(buildMediaSource(Uri.parse(videoInfos.get(position).getPlayURL()), null));
        for (int i = 0; i < videoInfos.size(); i++) {
            mediaSources.add(buildMediaSource(Uri.parse(videoInfos.get(i).getPlayURL()), null));
            titles.add(videoInfos.get(i).getTitle());
        }
        setTitles(titles);
        isLoadComplete = true;

    }

    /**
     * 设置标题列表
     *
     * @param titles
     */
    public void setTitles(List<String> titles) {
        this.titles = titles;
        scTvName.setText("正在播放：" + titles.get(current));
        currentPage = 1;
        totalPage = titles.size() % 9 == 0 ? titles.size() / 9 : titles.size() / 9 + 1;
        tvCur.setText(currentPage + "/" + totalPage);
        currentTitles = getCurrentTitles(currentPage);
        mAdapter = new PlayListAdapter(currentTitles);
        screenAudioListView.setAdapter(mAdapter);
        screenAudioListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isListViewFocus) return;
                TextView tvNumber = (TextView) view.findViewById(R.id.tv_number);
                if (mOldTVNumber != null) mOldTVNumber.setVisibility(View.VISIBLE);
                tvNumber.setVisibility(View.INVISIBLE);
                mOldTVNumber = tvNumber;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (mOldTVNumber != null) mOldTVNumber.setVisibility(View.VISIBLE);
            }
        });
        screenAudioListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                current = (currentPage - 1) * 9 + position;
                System.out.println(current);
                play(mediaSources.get(current));
            }
        });
    }

    /**
     * 显示当前页的数据
     *
     * @param currentPage
     * @return
     */
    private List<String> getCurrentTitles(int currentPage) {
        List<String> list = new ArrayList<>();
        for (int i = (currentPage - 1) * 9; i < (currentPage - 1) * 9 + 9; i++) {
            if (i < titles.size())
                list.add(titles.get(i));
        }
        return list;
    }

    /**
     * 切换到下一页或上一页
     *
     * @param currentPage
     */
    public void changePage(int currentPage) {
        if (!isLoadComplete) return;
        this.currentPage = currentPage;
        currentTitles.clear();
        currentTitles.addAll(getCurrentTitles(currentPage));
        mAdapter.notifyDataSetChanged();
    }

    private TextView mOldTVNumber;

    /**
     * 设置标题列表
     *
     * @param titles
     */

    /**
     * 播放单个视频
     */
    public void play(MediaSource mediaSource) {
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(true);
            mExoPlayer.prepare(mediaSource);
            if (titles != null) scTvName.setText("正在播放：" + titles.get(current));
            if (mAdapter != null) mAdapter.notifyDataSetChanged();
        }
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

    /***
     * 显示控制和播放列表
     */
    public void show(boolean isHide) {
        removeCallbacks(hideAction);
        updateProgress();
        relControl.setVisibility(View.VISIBLE);
        relList.setVisibility(View.VISIBLE);
        showAnim();
        if (isHide) hideAfterTimeout();
    }

    /**
     * 显示的动画
     */
    private void showAnim() {

    }

    /**
     * 隐藏控制和播放列表
     */
    public void hide() {
        removeCallbacks(updateProgress);
        relControl.setVisibility(View.GONE);
        relList.setVisibility(View.GONE);
        hideAnim();
    }

    /***
     *更新播放进度
     */
    public void updateProgress() {
        long contentPosition = mExoPlayer.getContentPosition();
        long duration = mExoPlayer.getDuration();
        System.out.println(duration);
        long bufferPosition = mExoPlayer.getBufferedPosition();
        String contentTime = Util.getStringForTime(formatBuilder, formatter, contentPosition);
        String durationTime = Util.getStringForTime(formatBuilder, formatter, duration);
        tvShowtime.setText(contentTime + "/" + durationTime);
        seekbar.setMax((int) duration);
        seekbar.setProgress((int) contentPosition);
        seekbar.setSecondaryProgress((int) bufferPosition);
        int playbackState = mExoPlayer == null ? Player.STATE_IDLE : mExoPlayer.getPlaybackState();
        if (playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED) {
            postDelayed(updateProgress, 1000);
        }
    }

    /**
     * 隐藏动画
     */
    private void hideAnim() {

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
        return new ExtractorMediaSource(uri, buildDataSourceFactory(true), new DefaultExtractorsFactory(), new Handler(Looper.getMainLooper()), eventLogger);
       /* switch (type) {
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
        }*/
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

    private final class ComponentListener implements Player.EventListener, OnClickListener {
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
            if (playbackState == Player.STATE_ENDED) {
                if (currentState == Player.REPEAT_MODE_ALL) {
                    next();
                } else if (currentState == Player.REPEAT_MODE_ONE) {
                    play(mediaSources.get(current));
                } else if (currentState == Player.REPEAT_MODE_OFF) {
                    int length = (int) (mediaSources.size() * Math.random());
                    length = length > mediaSources.size() - 1 ? mediaSources.size() - 1 : length;
                    play(mediaSources.get(length));
                    current = length;
                }
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
            Toast.makeText(getContext(), "资源不存在", Toast.LENGTH_SHORT).show();
            next();
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

        @Override
        public void onClick(View v) {
            if (v == ivPlayDown) {
                if (!isLoadComplete) return;
                next();
            } else if (v == ivPlayUp) {
                if (!isLoadComplete) return;
                previous();
            } else if (v == ivPlayOrPause) {
                if (!isLoadComplete) return;
                updatePlayerButton();
            } else if (v == ivPlayStar) {

            } else if (v == ivCirculation) {
                if (currentState == Player.REPEAT_MODE_ALL) {//当前模式是循环所有
                    ivCirculation.setImageResource(R.drawable.screen_circulation_one_select);
                    currentState = Player.REPEAT_MODE_ONE;
                    Toast.makeText(getContext(), "当前模式：单曲循环", Toast.LENGTH_SHORT).show();
                } else if (currentState == Player.REPEAT_MODE_ONE) {
                    currentState = Player.REPEAT_MODE_OFF;
                    ivCirculation.setImageResource(R.drawable.screen_circulation_random_select);
                    Toast.makeText(getContext(), "当前模式：全部循环", Toast.LENGTH_SHORT).show();
                } else if (currentState == Player.REPEAT_MODE_OFF) {
                    currentState = Player.REPEAT_MODE_ALL;
                    ivCirculation.setImageResource(R.drawable.screen_circulation_all_select);
                    Toast.makeText(getContext(), "当前模式：随机播放", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void updatePlayerButton() {
        if (mExoPlayer.getPlayWhenReady()) {
            ivPlayIcon.setVisibility(VISIBLE);
            ivPlayOrPause.setImageResource(R.drawable.screen_play_select);
            ivPlayIcon.setImageResource(R.drawable.player_start2_icon);
            show(false);
        } else {
            ivPlayIcon.setVisibility(VISIBLE);
            ivPlayOrPause.setImageResource(R.drawable.screen_pause_select);
            ivPlayIcon.setImageResource(R.drawable.player_pause2_icon);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    ivPlayIcon.setVisibility(GONE);
                }
            }, 1000);
        }
        mExoPlayer.setPlayWhenReady(!mExoPlayer.getPlayWhenReady());
    }

    public boolean dispatchMediaKeyEvent(int keyCode, KeyEvent event) {
        show(true);
        if (mExoPlayer == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (!isLoadComplete) return true;
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_MEDIA_FAST_FORWARD:
                    fastForward();
                    break;
                case KeyEvent.KEYCODE_MEDIA_REWIND:
                    rewind();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                    updatePlayerButton();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY:
                    ivPlayIcon.setVisibility(VISIBLE);
                    ivPlayOrPause.setImageResource(R.drawable.screen_pause_select);
                    ivPlayIcon.setImageResource(R.drawable.player_pause2_icon);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ivPlayIcon.setVisibility(GONE);
                        }
                    }, 1000);
                    mExoPlayer.setPlayWhenReady(true);
                    break;
                case KeyEvent.KEYCODE_MEDIA_PAUSE:
                    mExoPlayer.setPlayWhenReady(false);
                    ivPlayIcon.setVisibility(VISIBLE);
                    ivPlayOrPause.setImageResource(R.drawable.screen_play_select);
                    ivPlayIcon.setImageResource(R.drawable.player_start2_icon);
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    next();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    previous();
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    private void previous() {
        Toast.makeText(getContext(), "正在为您切换上一首", Toast.LENGTH_SHORT).show();
        current = --current < 0 ? mediaSources.size() - 1 : current;
        play(mediaSources.get(current));
    }

    private void next() {
        Toast.makeText(getContext(), "正在为您切换下一首", Toast.LENGTH_SHORT).show();
        current = ++current > mediaSources.size() - 1 ? 0 : current;
        play(mediaSources.get(current));
    }


    private void fastForward() {
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


    private void rewind() {
        if (rewindMs <= 0) {
            return;
        }
        mExoPlayer.seekTo(Math.max(mExoPlayer.getCurrentPosition() - rewindMs, 0));
    }


    @SuppressLint("InlinedApi")
    private static boolean isHandledMediaKey(int keyCode) {
        return keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD
                || keyCode == KeyEvent.KEYCODE_MEDIA_REWIND
                || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY
                || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE
                || keyCode == KeyEvent.KEYCODE_MEDIA_NEXT
                || keyCode == KeyEvent.KEYCODE_MEDIA_PREVIOUS;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: " + keyCode);
        if (relControl.getVisibility() == GONE || keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            show(true);
        }
        if (isListViewFocus && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            currentPage = ++currentPage > totalPage ? 1 : currentPage;
            changePage(currentPage);
            tvCur.setText(currentPage + "/" + totalPage);
        }
        if (isListViewFocus && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            currentPage = --currentPage < 1 ? totalPage : currentPage;
            changePage(currentPage);
            tvCur.setText(currentPage + "/" + totalPage);
            return true;
        }
        return dispatchMediaKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
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
            mExoPlayer.release();
            mExoPlayer = null;
            trackSelector = null;
            eventLogger = null;
        }
    }

    private static class PlayListAdapter extends BaseAdapter {
        private List<String> titles;

        public PlayListAdapter(List<String> titles) {
            this.titles = titles;
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public Object getItem(int position) {
            return titles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_screen_audio_listview, null);
            ViewHolder holder = new ViewHolder();
            holder.rel_item_root = (RelativeLayout) convertView.findViewById(R.id.rel_item_root);
            holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            if (currentPage == current / 9 + 1) {//当前播放的标题
                if (current % 9 == position) {
                    holder.tv_name.setTextColor(Color.GRAY);
                    holder.tv_number.setTextColor(Color.GRAY);
                }
            } else {
                holder.tv_name.setTextColor(Color.WHITE);
                holder.tv_number.setTextColor(Color.WHITE);
            }
            if (holder.rel_item_root.isFocused()) {
                holder.tv_number.setText("");
            } else {
                holder.tv_number.setText(1 + position + (currentPage - 1) * 9 + "");
            }

            holder.tv_name.setText(titles.get(position));
            return convertView;
        }
    }

    private static class ViewHolder {
        RelativeLayout rel_item_root;
        TextView tv_number, tv_name;
    }
}
