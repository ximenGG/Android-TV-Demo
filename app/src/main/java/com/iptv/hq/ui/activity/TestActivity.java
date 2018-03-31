package com.iptv.hq.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.iptv.ISupportFragment;
import com.iptv.SupportActivity;
import com.iptv.hq.R;
import com.iptv.hq.ui.fragment.KuleFragment;
import com.iptv.hq.ui.fragment.TestFragment;
import com.iptv.event.EventManager;
import com.iptv.event.PEvent;


public class TestActivity extends SupportActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        loadFragment(TestFragment.newInstance());
        EventManager.getInstance().post(event);

    }

    private PEvent<ISupportFragment> event = new PEvent<ISupportFragment>("loadFragment") {
        @Override
        public void event(ISupportFragment iSupportFragment) {
            loadFragment(iSupportFragment);
        }
    };

    public void loadFragment(@NonNull ISupportFragment toFragment) {
        super.loadRootFragment(R.id.layout_base, toFragment);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEvent.KEYCODE_DPAD_DOWN == event.getKeyCode()){
            showStackHierarchyView();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onBackPressedSupport() {
        if (getTopFragment() instanceof KuleFragment)
        popTo(KuleFragment.class,false);
        super.onBackPressedSupport();
    }

}
