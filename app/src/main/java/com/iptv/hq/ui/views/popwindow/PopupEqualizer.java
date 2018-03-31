package com.iptv.hq.ui.views.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.iptv.hq.R;
import com.iptv.hq.bean.EquaLizerBean;

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
 * Created by HQ on 2017/12/22.
 */
public class PopupEqualizer extends PopupWindow {
    private List<EquaLizerBean> equaLizer;
    private View mPopView;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    public PopupEqualizer(Context context) {
        this(context,null);
    }

    public PopupEqualizer(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PopupEqualizer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setPopupWindow();
    }

    private void init(Context context) {
        mPopView = LayoutInflater.from(context).inflate(R.layout.pop_equalizer,null);
        tabLayout = (TabLayout) mPopView.findViewById(R.id.tablayout);
        recyclerView = (RecyclerView) mPopView.findViewById(R.id.recyclerview);
    }

    public void setList(List<EquaLizerBean> equaLizer){
        for (int i = 0; i < equaLizer.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(equaLizer.get(i).getName()));
        }
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
