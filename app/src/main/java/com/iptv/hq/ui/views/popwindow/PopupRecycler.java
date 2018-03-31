package com.iptv.hq.ui.views.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.iptv.hq.R;
import com.iptv.hq.adapter.PopupAdater;
import com.iptv.hq.bean.PlayListBean;

import java.util.List;

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
public class PopupRecycler extends PopupWindow {
    private View mPopView;
    private RecyclerView mRecyclerView;

    public PopupRecycler(Context context) {
        this(context,null);
    }

    public PopupRecycler(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PopupRecycler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setPopupWindow();
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        mPopView = LayoutInflater.from(context).inflate(R.layout.pop_recyclerview, null);
        mRecyclerView = (RecyclerView) mPopView.findViewById(R.id.popup_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context,RecyclerView.HORIZONTAL));

    }

    public void setAdapter(List<PlayListBean.DataListBean> dataList){
        mRecyclerView.setAdapter(new PopupAdater(dataList));
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
}
