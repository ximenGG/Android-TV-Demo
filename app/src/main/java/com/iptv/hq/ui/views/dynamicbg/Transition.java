package com.iptv.hq.ui.views.dynamicbg;

import android.graphics.RectF;
import android.view.animation.Interpolator;

/**
 * Created by HQ on 2017/3/5.
 */

public class Transition {

    private RectF mSrcRect;
    private RectF mDstRect;
    private final RectF mCurrentRect = new RectF();
    private float mWidthDiff;
    private float mHeightDiff;
    private float mCenterXDiff;
    private float mCenterYDiff;

    private long mDuration;

    private Interpolator mInterpolator;


    public Transition(RectF srcRect, RectF dstRect, long duration, Interpolator interpolator) {
        if (!MathUtils.haveSameAspectRatio(srcRect, dstRect)) {
            throw new IncompatibleRatioException();
        }
        mSrcRect = srcRect;
        mDstRect = dstRect;
        mDuration = duration;
        mInterpolator = interpolator;
        mWidthDiff = dstRect.width() - srcRect.width();
        mHeightDiff = dstRect.height() - srcRect.height();
        mCenterXDiff = dstRect.centerX() - srcRect.centerX();
        mCenterYDiff = dstRect.centerY() - srcRect.centerY();
    }



    public RectF getSourceRect() {
        return mSrcRect;
    }



    public RectF getDestinyRect() {
        return mDstRect;
    }



    public RectF getInterpolatedRect(long elapsedTime) {
        float elapsedTimeFraction = elapsedTime / (float) mDuration;
        float interpolationProgress = Math.min(elapsedTimeFraction, 1);
        float interpolation = mInterpolator.getInterpolation(interpolationProgress);
        float currentWidth = mSrcRect.width() + (interpolation * mWidthDiff);
        float currentHeight = mSrcRect.height() + (interpolation * mHeightDiff);

        float currentCenterX = mSrcRect.centerX() + (interpolation * mCenterXDiff);
        float currentCenterY = mSrcRect.centerY() + (interpolation * mCenterYDiff);

        float left = currentCenterX - (currentWidth / 2);
        float top = currentCenterY - (currentHeight / 2);
        float right = left + currentWidth;
        float bottom = top + currentHeight;

        mCurrentRect.set(left, top, right, bottom);
        return mCurrentRect;
    }


    public long getDuration() {
        return mDuration;
    }

}
