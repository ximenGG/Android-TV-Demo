package com.iptv.hq.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.iptv.hq.R;


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
 * Created by ximen on 2018/1/19.
 */
public class BorderImageView extends AppCompatImageView {
    private float mBorderRadius;
    private float mScaleRatio;
    private Drawable mCircle;
    private Drawable mRectangle;
    private float scale = 1.0f;
    private
    @DrawableRes
    int mCircleRes = R.drawable.focus_shape_3;
    private
    @DrawableRes
    int mRectangleRes = R.drawable.focus_shape_1;
    private int mType;
    private Paint mPaint;
    private Paint paint;
    private int mAnimateTime;
    // 3x3 矩阵，主要用于缩小放大
    private Matrix mMatrix;
    private Matrix matrix;
    //渲染图像，使用图像为绘制图形着色
    private BitmapShader mBitmapShader;
    private float translateX;
    private float translateY;
    private float width;
    private float height;
    private boolean isFirstAnimate;
    private float border = getPx(R.dimen.px2);

    public BorderImageView(Context context) {
        this(context, null);
    }

    public BorderImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.BorderImageView, defStyleAttr, 0);
        mBorderRadius = attr.getDimension(R.styleable.BorderImageView_BorderRadius, getPx(R.dimen.px14));//圆角半径
        mScaleRatio = attr.getFloat(R.styleable.BorderImageView_ScaleRatio, 1.05f);//缩放比
        mType = attr.getInt(R.styleable.BorderImageView_Type, 0);//类型（圆形或者方形）
        mAnimateTime = attr.getInt(R.styleable.BorderImageView_AnimateTime, 250);//播放动画的时间
        if (mType == 0) {
            mRectangle = attr.getDrawable(R.styleable.BorderImageView_RectangleRes);//方形边框
        } else {
            mCircle = attr.getDrawable(R.styleable.BorderImageView_CircleRes);//圆形边框
        }
        attr.recycle();
        mMatrix = new Matrix();
        matrix = new Matrix();
        mPaint = new Paint();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        translateX = -width;
        translateY = -height;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        Bitmap bitmap = drawableToBitamp(getDrawable());
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if (!(bitmap.getWidth() == getWidth() && bitmap.getHeight() == getHeight())) {
            // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
            scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), getHeight() * 1.0f / bitmap.getHeight());
        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mPaint.setShader(mBitmapShader);
        if (mType == 0) {
            canvas.drawRoundRect(new RectF(border, border, width - border, height - border), mBorderRadius, mBorderRadius, mPaint);//圆角图片
            drawBorder(mRectangle, canvas, mType);//绘制方形框
        } else {
            canvas.drawCircle(width / 2, height / 2, width > height ? (height - border) / 2 : (width - border) / 2, mPaint);//圆形图片
            drawBorder(mCircle, canvas, mType);//绘制圆形框
        }
    }

    private void drawBorder(Drawable drawable, Canvas canvas, int type) {
        if (isFocused()) {
            if (drawable == null) {
                if (type == 0) {
                    drawable = getContext().getResources().getDrawable(mRectangleRes);
                } else {
                    drawable = getContext().getResources().getDrawable(mCircleRes);
                }
            }
            drawable.setBounds(new Rect(0, 0, (int) width, (int) height));
            drawable.draw(canvas);
            LinearGradient linearGradient = new LinearGradient(translateX, translateY, width, height, new int[]{0x22ffffff, 0xffffffff, 0x22ffffff}, new float[]{0, 0.5f, 1}, Shader.TileMode.CLAMP);
            paint.setShader(linearGradient);
            matrix.setTranslate(translateX, translateY);
            linearGradient.setLocalMatrix(matrix);
            if (type == 0) {
                canvas.drawRoundRect(new RectF(0, 0, width, height), mBorderRadius, mBorderRadius, paint);
            } else {
                canvas.drawCircle(width / 2, height / 2, width > height ? height / 2 : width / 2, paint);
            }
            translateX += width / 10;
            translateY += height / 10;
            if (width > height) {//宽大于高，位移由宽决定
                if (translateX < width) {
                    postInvalidateDelayed(10);
                } else {
                    translateX = -width;
                    translateY = -height;
                }
            } else {//高大于宽，位移由高决定
                if (translateY < height) {
                    postInvalidateDelayed(10);
                } else {
                    translateX = -width;
                    translateY = -height;
                }
            }
            if (!isFirstAnimate) {//动画只播放一次
                animate().scaleX(mScaleRatio).scaleY(mScaleRatio).setDuration(mAnimateTime).start();
                isFirstAnimate = true;
            }
        } else {//没有焦点缩放动画
            animate().scaleX(scale).scaleY(scale).setDuration(mAnimateTime).start();
            isFirstAnimate = false;
            translateX = -width;
            translateY = -height;
        }
    }

    public void setCircleDrawable(Drawable mCircle) {
        this.mCircle = mCircle;
    }

    public void setRectangle(Drawable mRectangle) {
        this.mRectangle = mRectangle;
    }

    public void setType(int mType) {
        this.mType = mType;
    }

    public void setBorderRadius(float mBorderRadius) {
        this.mBorderRadius = mBorderRadius;
    }

    public void setScaleRatio(float mScaleRatio) {
        this.mScaleRatio = mScaleRatio;
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        // 当设置不为图片，为颜色时，获取的drawable宽高会有问题，所有当为颜色时候获取控件的宽高
        int w = drawable.getIntrinsicWidth() <= 0 ? getWidth() : drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight() <= 0 ? getHeight() : drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    public float getPx(@DimenRes int res) {
        return getResources().getDimension(res);
    }
}
