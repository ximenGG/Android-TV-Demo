package com.iptv.hq.bean;

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
public class EquaLizerBean {
    /**
     * effect : [7,13,1,7,11]
     * name : 默认
     */
    private String name;
    private List<Integer> effect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getEffect() {
        return effect;
    }

    public void setEffect(List<Integer> effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "EquaLizerBean{" +
                "name='" + name + '\'' +
                ", effect=" + effect +
                '}';
    }
}
