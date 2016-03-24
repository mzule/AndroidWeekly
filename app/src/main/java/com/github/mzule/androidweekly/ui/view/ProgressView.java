package com.github.mzule.androidweekly.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.util.DensityUtil;
import com.github.mzule.androidweekly.util.ThreadUtil;


/**
 * Created by CaoDongping on 1/5/16.
 */
public class ProgressView extends SurfaceView implements SurfaceHolder.Callback {
    private static final int SPEED = 9;
    private Paint paint;
    private RectF rect;
    private int border;
    private float i = 270;
    private float j;
    private int p;
    private SurfaceHolder holder;
    private volatile boolean running;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            return;
        }
        holder = getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSLUCENT);
        border = DensityUtil.dp2px(1);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(border);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        setZOrderOnTop(true);
    }

    protected void doDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        if (rect == null) {
            rect = new RectF(border, border, getMeasuredWidth() - border, getMeasuredHeight() - border);
        }
        canvas.drawArc(rect, i, j, false, paint);
        loop();
    }

    private void loop() {
        if (i < 450) {
            i += SPEED;
            j = i / 3f - 90;
        } else if (i < 630) {
            i += SPEED;
            j = i / -3f + 210;
        } else if (i < 631 || pause()) {
            if (p == 0) {
                j += SPEED * 2;
                i = (j + 226800) / 360f;
            }
        } else if (i < 990) {
            i += SPEED * 2;
            j = -i + 990;
        } else {
            i = 270;
            j = 0;
            p = 0;
        }
    }

    public void finish() {
        setVisibility(View.GONE);
    }

    private boolean pause() {
        return p++ < 10;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        running = true;
        new DrawThread().start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        running = false;
    }

    class DrawThread extends Thread {
        @Override
        public void run() {
            while (running) {
                long startTime = System.currentTimeMillis();
                Canvas canvas = holder.lockCanvas();
                if (canvas == null) {
                    return;
                }
                doDraw(canvas);
                try {
                    holder.unlockCanvasAndPost(canvas);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                ThreadUtil.sleepUntilNextDraw(startTime);
            }
        }
    }
}
