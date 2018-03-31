package com.iptv.hq.bean;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

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
 * Created by HQ on 2017/11/16.
 */
@Entity
public class VideoBean{
    @Id(autoincrement = false)
    private Long id;
    @Unique
    private String resCode;//请求码
    private int position;//列表中的位置
    private String title;//标题
    private String playURL;//播放地址
    private boolean isPlay;//可不可以播放
    private long currentPosition;//播放进度
    private long duration;//总时长
    private String videoType;//视频类型
    private int playCount;//播放次数
    @Generated(hash = 641279176)
    public VideoBean(Long id, String resCode, int position, String title,
            String playURL, boolean isPlay, long currentPosition, long duration,
            String videoType, int playCount) {
        this.id = id;
        this.resCode = resCode;
        this.position = position;
        this.title = title;
        this.playURL = playURL;
        this.isPlay = isPlay;
        this.currentPosition = currentPosition;
        this.duration = duration;
        this.videoType = videoType;
        this.playCount = playCount;
    }
    @Generated(hash = 2024490299)
    public VideoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getResCode() {
        return this.resCode;
    }
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    public int getPosition() {
        return this.position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPlayURL() {
        return this.playURL;
    }
    public void setPlayURL(String playURL) {
        this.playURL = playURL;
    }
    public boolean getIsPlay() {
        return this.isPlay;
    }
    public void setIsPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }
    public long getCurrentPosition() {
        return this.currentPosition;
    }
    public void setCurrentPosition(long currentPosition) {
        this.currentPosition = currentPosition;
    }
    public long getDuration() {
        return this.duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public String getVideoType() {
        return this.videoType;
    }
    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
    public int getPlayCount() {
        return this.playCount;
    }
    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}
