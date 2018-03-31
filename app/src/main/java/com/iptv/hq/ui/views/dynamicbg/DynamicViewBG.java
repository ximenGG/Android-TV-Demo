package com.iptv.hq.ui.views.dynamicbg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

/**
 * Created by HQ on 2017/3/5.
 */

public class DynamicViewBG extends AppCompatImageView {
    private static final long FRAME_DELAY = 1000 / 60;
    private final Matrix mMatrix = new Matrix();

    private TransitionGenerator mTransGen = new RandomTransitionGenerator();
    private TransitionListener mTransitionListener;
    private Transition mCurrentTrans;
    private final RectF mViewportRect = new RectF();
    private RectF mDrawableRect;
    private long mElapsedTime;
    private long mLastFrameTime;
    private boolean mPaused;
    private boolean mInitialized;

    public DynamicViewBG(Context context) {
        this(context, null);
    }


    public DynamicViewBG(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public DynamicViewBG(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInitialized = true;
        super.setScaleType(ScaleType.MATRIX);
    }


    @Override
    public void setScaleType(ScaleType scaleType) {
    }


    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        switch (visibility) {
            case VISIBLE:
                resume();
                break;
            default:
                pause();
                break;
        }
    }
    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        handleImageChange();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        handleImageChange();
    }


    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        handleImageChange();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        handleImageChange();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        restart();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable d = getDrawable();
        if (!mPaused && d != null) {
            if (mDrawableRect.isEmpty()) {
                updateDrawableBounds();
            } else if (hasBounds()) {
                if (mCurrentTrans == null) {
                    startNewTransition();
                }

                if (mCurrentTrans.getDestinyRect() != null) {
                    mElapsedTime += System.currentTimeMillis() - mLastFrameTime;
                    RectF currentRect = mCurrentTrans.getInterpolatedRect(mElapsedTime);
                    float widthScale = mDrawableRect.width() / currentRect.width();
                    float heightScale = mDrawableRect.height() / currentRect.height();
                    float currRectToDrwScale = Math.min(widthScale, heightScale);
                    float vpWidthScale = mViewportRect.width() / currentRect.width();
                    float vpHeightScale = mViewportRect.height() / currentRect.height();
                    float currRectToVpScale = Math.min(vpWidthScale, vpHeightScale);
                    float totalScale = currRectToDrwScale * currRectToVpScale;
                    float translX = totalScale * (mDrawableRect.centerX() - currentRect.left);
                    float translY = totalScale * (mDrawableRect.centerY() - currentRect.top);
                    mMatrix.reset();
                    mMatrix.postTranslate(-mDrawableRect.width() / 2, -mDrawableRect.height() / 2);
                    mMatrix.postScale(totalScale, totalScale);
                    mMatrix.postTranslate(translX, translY);
                    setImageMatrix(mMatrix);
                    if (mElapsedTime >= mCurrentTrans.getDuration()) {
                        fireTransitionEnd(mCurrentTrans);
                        startNewTransition();
                    }
                } else {
                    fireTransitionEnd(mCurrentTrans);
                }
            }
            mLastFrameTime = System.currentTimeMillis();
            postInvalidateDelayed(FRAME_DELAY);
        }
        super.onDraw(canvas);
    }

    private void startNewTransition() {
        if (!hasBounds()) {
            return;
        }
        mCurrentTrans = mTransGen.generateNextTransition(mDrawableRect, mViewportRect);
        mElapsedTime = 0;
        mLastFrameTime = System.currentTimeMillis();
        fireTransitionStart(mCurrentTrans);
    }

    public void restart() {
        int width = getWidth();
        int height = getHeight();

        if (width == 0 || height == 0) {
            return;
        }

        updateViewport(width, height);
        updateDrawableBounds();

        startNewTransition();
    }

    private boolean hasBounds() {
        return !mViewportRect.isEmpty();
    }

    private void fireTransitionStart(Transition transition) {
        if (mTransitionListener != null && transition != null) {
            mTransitionListener.onTransitionStart(transition);
        }
    }

    private void fireTransitionEnd(Transition transition) {
        if (mTransitionListener != null && transition != null) {
            mTransitionListener.onTransitionEnd(transition);
        }
    }

    public void setTransitionGenerator(TransitionGenerator transgen) {
        mTransGen = transgen;
        startNewTransition();
    }

    private void updateViewport(float width, float height) {
        mViewportRect.set(0, 0, width, height);
    }

    private void updateDrawableBounds() {
        if (mDrawableRect == null) {
            mDrawableRect = new RectF();
        }
        Drawable d = getDrawable();
        if (d != null && d.getIntrinsicHeight() > 0 && d.getIntrinsicWidth() > 0) {
            mDrawableRect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        }
    }

    private void handleImageChange() {
        updateDrawableBounds();
        if (mInitialized) {
            startNewTransition();
        }
    }


    public void setTransitionListener(TransitionListener transitionListener) {
        mTransitionListener = transitionListener;
    }

    public void pause() {
        mPaused = true;
    }


    public void resume() {
        mPaused = false;
        mLastFrameTime = System.currentTimeMillis();
        invalidate();
    }

    public interface TransitionListener {
         void onTransitionStart(Transition transition);

         void onTransitionEnd(Transition transition);
    }
}
