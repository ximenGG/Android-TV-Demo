package com.iptv.hq.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

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
 * Created by HQ on 2017/11/24.
 */
public class ViewAdapter extends PagerAdapter {
    List<View> views;

    public ViewAdapter(List<View> views) {
        this.views = views;
    }

    /**
     * 返回的int值,会作为ViewPager的总长度来使用
     *
     * @return
     */
    @Override
    public int getCount() {
        return views.size();
    }

    /**
     * 判断是否使用缓存,如果返回的是true,使用缓存,不去调用instantiateItem方法创建一个新的对象
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化一个条目
     *
     * @param container
     * @param position  就是当前需要加载条目的索引
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    /**
     * 销毁一个条目
     *
     * @param container
     * @param position  就是当前需要销毁条目的索引
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}

