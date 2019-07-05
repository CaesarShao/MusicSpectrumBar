package com.caesar.musicspectrumbarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * created by Caesar on 2019/6/11
 * email : 15757855271@163.com
 */
public class MusicSpectrumBar extends View {

    private int viewAllWidth;
    private int viewAllHigh;
    private Paint paint;
    private ArrayList<SpectrumData> myCData;
    private int[] highD = {1, 3, 5, 4, 6, 2, 7, 5, 6, 3, 2, 1, 2, 1, 2, 6, 5, 4, 2, 7, 5, 2, 3, 1, 2, 1, 3, 2, 1};
    private String[] ColorStr = {"#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df", "#3153df", "#3a53e0", "#4454e0", "#4e54e1", "#5855e2", "#6255e2", "#6d56e3", "#7756e3", "#8257e4", "#8c58e5", "#9758e5", "#a159e6", "#ab59e7", "#b45ae7", "#be5ae8", "#c65be8", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb"};
    private OnSeekChangeListener listener;
    private int currentT = -1;
    private int roundAngle;
    private int poseType;
    private float gapMultiple;
    private int unSelectColor;
    private int colorGradient;
    private float SpectMultiple;

    public MusicSpectrumBar(Context context) {
        this(context, null);
    }

    public MusicSpectrumBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicSpectrumBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MusicSpectrumBar);
        roundAngle = array.getInt(R.styleable.MusicSpectrumBar_roundAngle, 5);
        poseType = array.getInt(R.styleable.MusicSpectrumBar_poseType, 0);
        gapMultiple = array.getFloat(R.styleable.MusicSpectrumBar_gapMultiple, 2);
        unSelectColor = array.getColor(R.styleable.MusicSpectrumBar_unSelectColor, Color.WHITE);
        colorGradient = array.getInt(R.styleable.MusicSpectrumBar_colorGradient, 0);
        SpectMultiple = array.getFloat(R.styleable.MusicSpectrumBar_SpectMultiple, (float) 0.5);
        array.recycle();
        clearItems();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (myCData.isEmpty()) {
            return;
        }
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
        }
        for (int i = 0; i < highD.length; i++) {
            if (i <= currentT) {
                if (currentT > 0) {
                    if (colorGradient == 0) {
                        int selectCol = i * highD.length / currentT;
                        if (selectCol < 0) {
                            selectCol = 0;
                        } else if (selectCol > highD.length - 1) {
                            selectCol = highD.length - 1;
                        }
                        paint.setColor(Color.parseColor(ColorStr[selectCol]));
                    } else {
                        paint.setColor(Color.parseColor(ColorStr[i]));
                    }
                } else {
                    paint.setColor(Color.parseColor(ColorStr[0]));
                }
            } else {
                paint.setColor(unSelectColor);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRoundRect(myCData.get(i).getLeft(), myCData.get(i).getTop(), myCData.get(i).getRight(), myCData.get(i).getBottom(), roundAngle, roundAngle, paint);
            } else {
                canvas.drawRect(myCData.get(i).getLeft(), myCData.get(i).getTop(), myCData.get(i).getRight(), myCData.get(i).getBottom(), paint);
            }
        }
    }


    /**
     * 强迫症完美主义者实现
     *
     * @return 完美
     */
    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                performClick();
                if (listener != null) {
                    listener.onStartTrackingTouch();
                }
            case MotionEvent.ACTION_MOVE:
                currentT = (int) ((event.getX() / viewAllWidth) * highD.length);
                invalidate();
                if (listener != null) {
                    float refan = event.getX();
                    if (refan < 0) {
                        refan = 0;
                    } else if (refan > viewAllWidth) {
                        refan = viewAllWidth;
                    }
                    listener.onProgressChanged((int) (refan / viewAllWidth * 100), true);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    float refan = event.getX();
                    if (refan < 0) {
                        refan = 0;
                    }
                    listener.onStopTrackingTouch((int) (refan / viewAllWidth * 100));
                }
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewAllWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewAllHigh = MeasureSpec.getSize(heightMeasureSpec);
        setItems();
    }


    /**
     * 设置每条频谱线的属性
     */
    private void setItems() {
        float lineWidth = ((float) viewAllWidth) / ((highD.length - 1) * (1 + gapMultiple) + 1);
        float lineMinHigh = ((float) viewAllHigh) / getMaxIntArr();
        float SpectMinHigh = (float) viewAllHigh * (1 - SpectMultiple) / (getMaxIntArr() - 1);
        if (myCData.isEmpty()) {
            for (int i = 0; i < highD.length; i++) {
                float lineStartW = (float) i * (1 + gapMultiple) * lineWidth;
                float lineStartH = 0;
                if (poseType == 0) {
                    lineStartH = ((float) viewAllHigh - highD[i] * lineMinHigh) / 2;
                    myCData.add(new SpectrumData(lineStartW, lineStartW + lineWidth, lineStartH, lineStartH + highD[i] * lineMinHigh, ColorStr[i]));
                } else if (poseType == 1) {
                    lineStartH = (float) viewAllHigh - highD[i] * lineMinHigh;
                    myCData.add(new SpectrumData(lineStartW, lineStartW + lineWidth, lineStartH, lineStartH + highD[i] * lineMinHigh, ColorStr[i]));
                } else if (poseType == 2) {
                    myCData.add(new SpectrumData(lineStartW, lineStartW + lineWidth, lineStartH, lineStartH + highD[i] * lineMinHigh, ColorStr[i]));
                } else {
                    myCData.add(new SpectrumData(lineStartW, lineStartW + lineWidth, SpectMinHigh * (highD[i] - 1), SpectMinHigh * (highD[i] - 1) + (float) viewAllHigh * SpectMultiple, ColorStr[i]));
                }
            }
        }
    }

    /**
     * 获取频谱进度条中最长一条
     *
     * @return 最长的长度倍数
     */
    private int getMaxIntArr() {
        int maxSin = 0;
        for (int item : highD) {
            if (item > maxSin) {
                maxSin = item;
            }
        }
        return maxSin;
    }

    /**
     * 清除进度条里的频谱数据
     */
    private void clearItems() {
        if (myCData == null) {
            myCData = new ArrayList<>();
        }
        myCData.clear();
    }

    /**
     * 设置进度的属性数据
     *
     * @param highDArr 频谱条的数据组
     * @param colorArr 频谱条的颜色组
     */
    protected void setDatas(int[] highDArr, String[] colorArr) {
        this.highD = highDArr;
        this.ColorStr = colorArr;
        clearItems();
        setItems();
        invalidate();
    }


    /**
     * 设置当前进度
     *
     * @param current 进度
     */
    protected void setCurrent(int current) {
        currentT = highD.length * current / 100;
        invalidate();
    }


    /**
     * 设置监听器,跟seekbar的滑动监听一样
     *
     * @param listener 监听器
     */
    public void setOnSeekBarChangeListener(OnSeekChangeListener listener) {
        this.listener = listener;
    }

    public interface OnSeekChangeListener {

        void onProgressChanged(int i, boolean b);

        void onStartTrackingTouch();

        void onStopTrackingTouch(int i);

    }

}
