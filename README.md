# Android-TV-Demo
MVP框架，焦点框架，fragment框架，ExoPlayer媒体播放器
###效果图
![](https://github.com/ximenGG/Android-TV-Demo/blob/master/GIF.gif)

###焦点

	<com.iptv.hq.ui.views.BorderImageView
            android:id="@+id/item_1"
            android:layout_width="@dimen/px217"
            android:layout_height="@dimen/px261"
            android:clickable="true"
            android:focusable="true" />

##MVP框架抽取
    
    public abstract class BaseActivity<M extends IModel, V extends IView, P extends IPresenter<V, M>> {
    protected P mPresenter;
    protected M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = createModel();
        mPresenter = createPresenter();
        mPresenter.onAttach((V) this, mModel);
    }

    protected abstract M createModel();

    protected abstract P createPresenter();

   
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
        Runtime.getRuntime().gc();
        System.runFinalization();
        Runtime.getRuntime().gc();
    }

	}
###Presenter
	
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

##Fragment

###Fragment是基于fragmentation进行拓展的（详情请参考：[https://github.com/YoKeyword/Fragmentation](https://github.com/YoKeyword/Fragmentation)），在此基础上增加了LoadingFragment,事件分发。并简化了Fragment之间的通讯

####Event   无参数无返回的事件

####PEvent  有参数无返回值的事件

####PREvent 有参数和返回值的事件

####REvent  有返回值无参数的事件

###EventManager 事件的管理者
	  
	  //存储一个事件
      EventManager.getInstance().post(new PEvent<ISupportFragment>("loadFragment") {
            @Override
            public void event(ISupportFragment iSupportFragment) {
                loadFragment(iSupportFragment);
            }
        });
	
	 //执行一个事件
	   EventManager.getInstance().invokeEvent("loadFragment",OnlineFragment.newInstance(),true);
	   
	   
##ExoPlayer播放器（详情请阅读：[https://github.com/google/ExoPlayer](https://github.com/google/ExoPlayer)）

##扩展了ffmpeg库增加了ffmpeg音频解码，进行了简单的封装，修改了源代码，使其支持左右声道的切换，增加了均衡器
 
 	.......
     exoplay = (ExoVideoView) findViewById(R.id.exoplay);
	 exoplay.play(videoInfo);
 
    @Override   
	protected void onResume() {
        super.onResume();
        exoplay.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoplay.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        exoplay.pause();
    }
	.........
