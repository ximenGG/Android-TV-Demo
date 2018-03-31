package com.iptv.hq.ui.views.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.iptv.hq.R;

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
 * Created by HQ on 2017/12/17.
 */
public class PopupVideoControl extends PopupWindow implements View.OnClickListener{
    private TextView repeat;
    private TextView nextSong;
    private TextView soundEffect;
    private TextView yuanBan;
    private TextView songList;
    private View mPopView;
    private OnItemClickListener mListener;

    public PopupVideoControl(Context context) {
        this(context, null);

    }

    public PopupVideoControl(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopupVideoControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setPopupWindow();
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        mPopView = LayoutInflater.from(context).inflate(R.layout.pop_control, null);
        repeat = (TextView) mPopView.findViewById(R.id.repeat);
        nextSong = (TextView) mPopView.findViewById(R.id.next_song);
        soundEffect = (TextView) mPopView.findViewById(R.id.sound_effect);
        yuanBan = (TextView) mPopView.findViewById(R.id.yuan_ban);
        songList = (TextView) mPopView.findViewById(R.id.song_list);
        repeat.setOnClickListener(this);
        nextSong.setOnClickListener(this);
        soundEffect.setOnClickListener(this);
        yuanBan.setOnClickListener(this);
        songList.setOnClickListener(this);
    }

    /**
     * 设置窗口的相关属性
     */
    private void setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.popwindow_anim_style);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        this.setOutsideTouchable(true);
    }

    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.setOnItemClick(v);
        }
    }

}
