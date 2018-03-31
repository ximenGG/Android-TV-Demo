package com.iptv.hq.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class BasicActivity extends Activity {
    /**
     * 记录处于前台的Activity
     */
    private static BasicActivity mForegroundActivity = null;
    /**
     * 记录所有活动的Activity
     */
    private static final List<BasicActivity> mActivities = new LinkedList<BasicActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 通过类名启动并且传递参数
     *
     * @param className
     */
    protected void startActivity(String className, Bundle bundle) {
        try {
            startActivity(Class.forName(className), bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过类名启动
     *
     * @param className
     */
    protected void startActivity(String className) {
        try {
            startActivity(Class.forName(className), null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 通过字节码对象并传递参数启动
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    // // 通过字节码对象启动
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    // startActivityForResult
    protected void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    // startActivityForResult
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    // startActivityForResult
    protected void startActivityForResult(String className, int requestCode) {
        try {
            startActivityForResult(Class.forName(className), null, requestCode);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // startActivityForResult
    protected void startActivityForResult(String className, Bundle bundle, int requestCode) {
        try {
            startActivityForResult(Class.forName(className), bundle, requestCode);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // getIntent
    protected Bundle getIntentExtra() {
        Intent intent = getIntent();
        Bundle bundle = null;
        if (null != intent) bundle = intent.getExtras();
        return bundle;
    }

    @Override
    protected void onResume() {
        mForegroundActivity = this;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mForegroundActivity = null;
        super.onPause();
    }

    /**
     * 关闭所有Activity
     */
    public static void finishAll() {
        List<BasicActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BasicActivity>(mActivities);
        }
        for (BasicActivity activity : copy) {
            activity.finish();
        }
    }

    /**
     * 关闭所有Activity，除了参数传递的Activity
     */
    public static void finishAll(BasicActivity except) {
        List<BasicActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BasicActivity>(mActivities);
        }
        for (BasicActivity activity : copy) {
            if (activity != except)
                activity.finish();
        }
    }

    /**
     * 是否有启动的Activity
     */
    public static boolean hasActivity() {
        return mActivities.size() > 0;
    }

    /**
     * 获取当前处于前台的activity
     */
    public static BasicActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 获取当前处于栈顶的activity，无论其是否处于前台
     */
    public static BasicActivity getCurrentActivity() {
        List<BasicActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BasicActivity>(mActivities);
        }
        if (copy.size() > 0) {
            return copy.get(copy.size() - 1);
        }
        return null;
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
