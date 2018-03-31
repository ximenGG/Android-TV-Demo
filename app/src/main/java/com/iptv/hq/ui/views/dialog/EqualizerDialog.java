package com.iptv.hq.ui.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.widget.TabLayout;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import com.google.gson.reflect.TypeToken;
import com.iptv.hq.R;
import com.iptv.hq.adapter.FrequencyAdater;
import com.iptv.hq.bean.EquaLizerBean;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.utils.JsonUtil;

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
public class EqualizerDialog extends Dialog {
    private FrequencyAdater adater;
    private List<EquaLizerBean> equaLizers;
    private int audioSessionId;

    public EqualizerDialog(@NonNull Context context) {
        this(context, R.style.EqualizerDialog);
    }

    public EqualizerDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected EqualizerDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_equalizer);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);
        Window win = getWindow();
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams lp = win.getAttributes();
        Point size = new Point();
        display.getSize(size);
        lp.width = size.x / 2;
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        ListView listView = (ListView) findViewById(R.id.listview);
        equaLizers = (List<EquaLizerBean>) JsonUtil.parseJsonToList(AppOtt.mContext.getString(R.string.effect), new TypeToken<List<EquaLizerBean>>() {
        }.getType());
        for (int i = 0; i < equaLizers.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(equaLizers.get(i).getName()).setTag(i));
        }
        adater = new FrequencyAdater(audioSessionId);
        adater.setEquaLizer(equaLizers.get(0));
        listView.setAdapter(adater);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                adater.setEquaLizer(equaLizers.get(tab.getPosition()));
                adater.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void setAudioSessionId(int audioSessionId) {
        this.audioSessionId = audioSessionId;
    }

    public void dismiss(boolean isRelease) {
        super.dismiss();
        if (isRelease) {
            adater.release();
        }
    }


}
