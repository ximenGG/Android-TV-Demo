/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iptv.hq.ui.views.exoplayer;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Util;
import com.iptv.hq.R;

import java.util.Formatter;
import java.util.Locale;
public class TimeBar extends View{
  private static final int DEFAULT_BAR_HEIGHT = 4;
  private static final int DEFAULT_TOUCH_TARGET_HEIGHT = 26;
  private static final int DEFAULT_PLAYED_COLOR = 0xFFFFFFFF;
  private static final int DEFAULT_SCRUBBER_ENABLED_SIZE = 12;
  private final Rect seekBounds;
  private final Rect progressBar;
  private final Rect bufferedBar;
  private final Rect scrubberBar;
  private final Paint playedPaint;
  private final Paint bufferedPaint;
  private final Paint unplayedPaint;
  private final Paint scrubberPaint;
  private final int barHeight;
  private final int touchTargetHeight;
  private final int scrubberEnabledSize;
  private final int scrubberPadding;
  private final StringBuilder formatBuilder;
  private final Formatter formatter;
  private long duration;
  private long position;
  private long bufferedPosition;
  public TimeBar(Context context, AttributeSet attrs) {
    super(context, attrs);
    seekBounds = new Rect();//总体进度
    progressBar = new Rect();//播放进度条
    bufferedBar = new Rect();//缓冲
    scrubberBar = new Rect();//指示器
    playedPaint = new Paint();//播放画笔
    bufferedPaint = new Paint();//缓冲画笔
    unplayedPaint = new Paint();//没有播放画笔
    scrubberPaint = new Paint();//指示器画笔
    scrubberPaint.setAntiAlias(true);
    Resources res = context.getResources();
    DisplayMetrics displayMetrics = res.getDisplayMetrics();
    int defaultBarHeight = dpToPx(displayMetrics, DEFAULT_BAR_HEIGHT);
    int defaultTouchTargetHeight = dpToPx(displayMetrics, DEFAULT_TOUCH_TARGET_HEIGHT);
    int defaultScrubberEnabledSize = dpToPx(displayMetrics, DEFAULT_SCRUBBER_ENABLED_SIZE);
    if (attrs != null) {
      TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TimeBar, 0,
          0);
      try {
        barHeight = a.getDimensionPixelSize(R.styleable.TimeBar_bar_height, defaultBarHeight);
        touchTargetHeight = a.getDimensionPixelSize(R.styleable.TimeBar_touch_target_height, defaultTouchTargetHeight);
        scrubberEnabledSize = a.getDimensionPixelSize(R.styleable.TimeBar_scrubber_enabled_size, defaultScrubberEnabledSize);
        int playedColor = a.getInt(R.styleable.TimeBar_played_color, DEFAULT_PLAYED_COLOR);
        int scrubberColor = a.getInt(R.styleable.TimeBar_scrubber_color, getDefaultScrubberColor(playedColor));
        int bufferedColor = a.getInt(R.styleable.TimeBar_buffered_color, getDefaultBufferedColor(playedColor));
        int unplayedColor = a.getInt(R.styleable.TimeBar_unplayed_color, getDefaultUnplayedColor(playedColor));
        playedPaint.setColor(playedColor);
        scrubberPaint.setColor(scrubberColor);
        bufferedPaint.setColor(bufferedColor);
        unplayedPaint.setColor(unplayedColor);
      } finally {
        a.recycle();
      }
    } else {
      barHeight = defaultBarHeight;
      touchTargetHeight = defaultTouchTargetHeight;
      scrubberEnabledSize = defaultScrubberEnabledSize;
      playedPaint.setColor(DEFAULT_PLAYED_COLOR);
      scrubberPaint.setColor(getDefaultScrubberColor(DEFAULT_PLAYED_COLOR));
      bufferedPaint.setColor(getDefaultBufferedColor(DEFAULT_PLAYED_COLOR));
      unplayedPaint.setColor(getDefaultUnplayedColor(DEFAULT_PLAYED_COLOR));
    }
    formatBuilder = new StringBuilder();
    formatter = new Formatter(formatBuilder, Locale.getDefault());
    scrubberPadding = scrubberEnabledSize / 2;
    duration = C.TIME_UNSET;
    setFocusable(true);
    setPosition(0);
  }

  public void setPosition(long position) {
    this.position = position;
    setContentDescription(getProgressText());
    update();
  }

  public void setBufferedPosition(long bufferedPosition) {
    this.bufferedPosition = bufferedPosition;
    update();
  }

  public void setDuration(long duration) {
    this.duration = duration;
    update();
  }
  @Override
  public void onDraw(Canvas canvas) {
    canvas.save();
    drawTimeBar(canvas);
    drawPlayhead(canvas);
    canvas.restore();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int heightSize = MeasureSpec.getSize(heightMeasureSpec);
    int height = heightMode == MeasureSpec.UNSPECIFIED ? touchTargetHeight : heightMode == MeasureSpec.EXACTLY ? heightSize : Math.min(touchTargetHeight, heightSize);
    setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    int width = right - left;
    int height = bottom - top;
    int barY = (height - touchTargetHeight) / 2;
    int seekLeft = getPaddingLeft();
    int seekRight = width - getPaddingRight();
    int progressY = barY + (touchTargetHeight - barHeight) / 2;
    seekBounds.set(seekLeft, barY, seekRight, barY + touchTargetHeight);
    progressBar.set(seekBounds.left + scrubberPadding, progressY, seekBounds.right - scrubberPadding, progressY + barHeight);
    update();
  }

  @Override
  protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
    super.onSizeChanged(width, height, oldWidth, oldHeight);
  }

  private void update() {
    bufferedBar.set(progressBar);
    scrubberBar.set(progressBar);
    long newScrubberTime = position;
    if (duration > 0) {
      int bufferedPixelWidth = (int) ((progressBar.width() * bufferedPosition) / duration);
      bufferedBar.right = Math.min(progressBar.left + bufferedPixelWidth, progressBar.right);
      int scrubberPixelPosition = (int) ((progressBar.width() * newScrubberTime) / duration);
      scrubberBar.right = Math.min(progressBar.left + scrubberPixelPosition, progressBar.right);
    } else {
      bufferedBar.right = progressBar.left;
      scrubberBar.right = progressBar.left;
    }
    invalidate(seekBounds);
  }
  private void drawTimeBar(Canvas canvas) {
    int progressBarHeight = progressBar.height();
    int barTop = progressBar.centerY() - progressBarHeight / 2;
    int barBottom = barTop + progressBarHeight;
    if (duration <= 0) {
      canvas.drawRect(progressBar.left, barTop, progressBar.right, barBottom, unplayedPaint);
      return;
    }
    int bufferedLeft = bufferedBar.left;
    int bufferedRight = bufferedBar.right;
    int progressLeft = Math.max(Math.max(progressBar.left, bufferedRight), scrubberBar.right);
    if (progressLeft < progressBar.right) {
      canvas.drawRect(progressLeft, barTop, progressBar.right, barBottom, unplayedPaint);
    }
    bufferedLeft = Math.max(bufferedLeft, scrubberBar.right);
    if (bufferedRight > bufferedLeft) {
      canvas.drawRect(bufferedLeft, barTop, bufferedRight, barBottom, bufferedPaint);
    }
    if (scrubberBar.width() > 0) {
      canvas.drawRect(scrubberBar.left, barTop, scrubberBar.right, barBottom, playedPaint);
    }
  }

  private void drawPlayhead(Canvas canvas) {
    if (duration <= 0) {
      return;
    }
    int scrubberSize = scrubberEnabledSize;
    int playheadRadius = scrubberSize / 2;
    int playheadCenter = Util.constrainValue(scrubberBar.right, scrubberBar.left, progressBar.right);
    canvas.drawCircle(playheadCenter, scrubberBar.centerY(), playheadRadius, scrubberPaint);
  }

  private String getProgressText() {
    return Util.getStringForTime(formatBuilder, formatter, position);
  }
  private static int dpToPx(DisplayMetrics displayMetrics, int dps) {
    return (int) (dps * displayMetrics.density + 0.5f);
  }

  private static int getDefaultScrubberColor(int playedColor) {
    return 0xFF000000 | playedColor;
  }

  private static int getDefaultUnplayedColor(int playedColor) {
    return 0x33000000 | (playedColor & 0x00FFFFFF);
  }

  private static int getDefaultBufferedColor(int playedColor) {
    return 0xCC000000 | (playedColor & 0x00FFFFFF);
  }
}
