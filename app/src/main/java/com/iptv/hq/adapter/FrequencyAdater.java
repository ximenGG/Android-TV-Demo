package com.iptv.hq.adapter;

import android.media.audiofx.Equalizer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.iptv.hq.R;
import com.iptv.hq.bean.EquaLizerBean;

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
 * Created by HQ on 2017/12/25.
 */
public class FrequencyAdater extends BaseAdapter {
    private int audioSessionId;
    private EquaLizerBean mEqualizer;
    private String[] strings = {"60Hz", "230Hz", "910Hz", "3KHz", "14KHz"};
    private static final int FREQUENCY_MAX = 15;
    private static final int FREQUENCY_MIN = -15;
    private Equalizer equalizer;

    public FrequencyAdater(int audioSessionId) {
        this.audioSessionId = audioSessionId;
    }

    public void setEquaLizer(EquaLizerBean equaLizer) {
        this.mEqualizer = equaLizer;
    }

    @Override
    public int getCount() {
        return mEqualizer.getEffect().size();
    }

    @Override
    public Object getItem(int position) {
        return mEqualizer.getEffect().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(parent.getContext(), R.layout.item_list, null);
        TextView frequency = (TextView) view.findViewById(R.id.frequency);
        TextView textView = (TextView) view.findViewById(R.id.current_frequency);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        frequency.setText(strings[position]);
        seekBar.setMax(FREQUENCY_MAX - FREQUENCY_MIN);
        seekBar.setProgress(mEqualizer.getEffect().get(position) - FREQUENCY_MIN);
        textView.setText(mEqualizer.getEffect().get(position) + "db");
        setupEqualizer(position, mEqualizer.getEffect().get(position) * 100);
        return view;
    }

    private void setupEqualizer(int band, int level) {
        if (equalizer != null) {
            equalizer = new Equalizer(0, audioSessionId);
            equalizer.setEnabled(true);// 启用均衡器
            equalizer.setBandLevel((short) band, (short) level);
        }
    }

    public void release() {
        if (equalizer != null)
            equalizer.release();
    }

}
