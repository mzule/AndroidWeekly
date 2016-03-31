package com.github.mzule.androidweekly.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.github.mzule.androidweekly.R;


/**
 * Created by CaoDongping on 11/30/15.
 */
public abstract class PopupView<T> extends RelativeLayout {
    public static final int ANIMATION_DIRECTION_BOTTOM_TO_TOP = 0;
    public static final int ANIMATION_DIRECTION_TOP_TO_BOTTOM = 1;
    public static final int ANIMATION_DIRECTION_LEFT_TO_RIGHT = 2;
    public static final int ANIMATION_DIRECTION_RIGHT_TO_LEFT = 3;
    public static final int ANIMATE_DURATION = 200;
    private boolean inAnimation;

    public PopupView(Context context) {
        super(context);
    }

    public PopupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 显示到Activity上面去
     *
     * @param activity
     * @param data
     * @return
     */
    public PopupView<T> attachTo(Activity activity, T data) {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return attachTo(activity, data, lp);
    }

    /**
     * 显示到Activity上面去
     *
     * @param activity
     * @param data
     * @param layoutParams
     * @return
     */
    public PopupView<T> attachTo(Activity activity, T data, FrameLayout.LayoutParams layoutParams) {
        setId(R.id.popup_view_id);
        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        if (null != contentView.findViewById(R.id.popup_view_id)) {
            return this;
        }
        render(data);
        contentView.addView(this, layoutParams);
        animateIn();
        return this;
    }

    protected int getAnimationDirection() {
        return ANIMATION_DIRECTION_BOTTOM_TO_TOP;
    }

    /**
     * 动画显示
     */
    protected void animateIn() {
        if (inAnimation) {
            return;
        }
        setVisibility(INVISIBLE);
        inAnimation = true;
        post(new Runnable() {
            @Override
            public void run() {
                setVisibility(VISIBLE);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(getMaskView(), "alpha", 0, 1);
                alpha.setDuration(ANIMATE_DURATION);
                alpha.start();
                int measuredHeight = getMainView().getMeasuredHeight();
                int measuredWidth = getMainView().getMeasuredWidth();
                ObjectAnimator translate;
                switch (getAnimationDirection()) {
                    case ANIMATION_DIRECTION_BOTTOM_TO_TOP:
                        translate = ObjectAnimator.ofFloat(getMainView(), "translationY", measuredHeight, 0);
                        break;
                    case ANIMATION_DIRECTION_TOP_TO_BOTTOM:
                        translate = ObjectAnimator.ofFloat(getMainView(), "translationY", -measuredHeight, 0);
                        break;
                    case ANIMATION_DIRECTION_LEFT_TO_RIGHT:
                        translate = ObjectAnimator.ofFloat(getMainView(), "translationX", -measuredWidth, 0);
                        break;
                    case ANIMATION_DIRECTION_RIGHT_TO_LEFT:
                        translate = ObjectAnimator.ofFloat(getMainView(), "translationX", measuredWidth, 0);
                        break;
                    default:
                        throw new RuntimeException("unknown animation direction");
                }
                translate.setInterpolator(new AccelerateInterpolator());
                translate.setDuration(ANIMATE_DURATION);
                translate.start();
                translate.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        inAnimation = false;
                    }
                });
            }
        });
    }

    public void finish() {
        finish(null);
    }

    /**
     * 从当前Activity上移除
     */
    public void finish(final FinishCallback callback) {
        if (!inAnimation) {
            inAnimation = true;
            ObjectAnimator alpha = ObjectAnimator.ofFloat(getMainView(), "alpha", 1, 0);
            alpha.setDuration(ANIMATE_DURATION);
            alpha.start();
            int measuredHeight = getMainView().getMeasuredHeight();
            int measuredWidth = getMainView().getMeasuredWidth();
            ObjectAnimator translate;
            switch (getAnimationDirection()) {
                case ANIMATION_DIRECTION_BOTTOM_TO_TOP:
                    translate = ObjectAnimator.ofFloat(getMainView(), "translationY", 0, measuredHeight);
                    break;
                case ANIMATION_DIRECTION_TOP_TO_BOTTOM:
                    translate = ObjectAnimator.ofFloat(getMainView(), "translationY", 0, -measuredHeight);
                    break;
                case ANIMATION_DIRECTION_LEFT_TO_RIGHT:
                    translate = ObjectAnimator.ofFloat(getMainView(), "translationX", 0, -measuredWidth);
                    break;
                case ANIMATION_DIRECTION_RIGHT_TO_LEFT:
                    translate = ObjectAnimator.ofFloat(getMainView(), "translationX", 0, measuredWidth);
                    break;
                default:
                    throw new RuntimeException("unknown animation direction");
            }
            translate.setInterpolator(new AccelerateInterpolator());
            translate.setDuration(ANIMATE_DURATION);
            translate.start();
            translate.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (getParent() instanceof ViewGroup) {
                        ((ViewGroup) getParent()).removeView(PopupView.this);
                    }
                    inAnimation = false;
                    if (callback != null) {
                        callback.afterFinish();
                    }
                }
            });
        }
    }

    /**
     * 渲染数据到UI
     *
     * @param data
     */
    protected abstract void render(T data);

    /**
     * 获取需要fadeIn显示的遮罩视图
     *
     * @return
     */
    protected abstract View getMaskView();


    /**
     * 获取需要translation的主UI视图
     *
     * @return
     */
    protected abstract View getMainView();

    public interface FinishCallback {
        void afterFinish();
    }
}
