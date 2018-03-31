package com.iptv.hq.presenter;

import com.iptv.hq.view.IView;
import com.iptv.hq.model.IModel;

import java.lang.ref.WeakReference;

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
 * Created by HQ on 2017/12/27.
 */
public abstract class IPresenter<V extends IView, M extends IModel> {
    public WeakReference<IView> iView;
    public WeakReference<IModel> iModel;

    public void onAttach(V iView, M model) {
        this.iView = new WeakReference<IView>(iView);
        this.iModel = new WeakReference<IModel>(model);
    }

    public void onDetach() {
        iView.clear();
        iModel.clear();
    }

    public M getIModel() {
        return (M) iModel.get();
    }

    public V getIView() {
        return (V) iView.get();
    }

}
