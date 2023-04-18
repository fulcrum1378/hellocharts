package ir.mahdiparastesh.hellocharts.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;

import ir.mahdiparastesh.hellocharts.model.Viewport;
import ir.mahdiparastesh.hellocharts.view.Chart;

public class ChartViewportAnimator implements Animator.AnimatorListener,
        ValueAnimator.AnimatorUpdateListener {
    int FAST_ANIMATION_DURATION = 300;
    private final Chart chart;
    private final ValueAnimator animator;
    private final Viewport startViewport = new Viewport();
    private final Viewport targetViewport = new Viewport();
    private final Viewport newViewport = new Viewport();
    private ChartAnimationListener animationListener = new DummyChartAnimationListener();


    public ChartViewportAnimator(Chart chart) {
        this.chart = chart;
        animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addListener(this);
        animator.addUpdateListener(this);
        animator.setDuration(FAST_ANIMATION_DURATION);
    }

    public void startAnimation(Viewport startViewport, Viewport targetViewport) {
        this.startViewport.set(startViewport);
        this.targetViewport.set(targetViewport);
        animator.setDuration(FAST_ANIMATION_DURATION);
        animator.start();
    }

    public void startAnimation(Viewport startViewport, Viewport targetViewport, long duration) {
        this.startViewport.set(startViewport);
        this.targetViewport.set(targetViewport);
        animator.setDuration(duration);
        animator.start();
    }

    public void cancelAnimation() {
        animator.cancel();
    }

    public void onAnimationUpdate(ValueAnimator animation) {
        float scale = animation.getAnimatedFraction();
        float diffLeft = (targetViewport.left - startViewport.left) * scale;
        float diffTop = (targetViewport.top - startViewport.top) * scale;
        float diffRight = (targetViewport.right - startViewport.right) * scale;
        float diffBottom = (targetViewport.bottom - startViewport.bottom) * scale;
        newViewport.set(startViewport.left + diffLeft, startViewport.top + diffTop, startViewport.right + diffRight,
                startViewport.bottom + diffBottom);
        chart.setCurrentViewport(newViewport);
    }

    public void onAnimationCancel(Animator animation) {
    }

    public void onAnimationEnd(Animator animation) {
        chart.setCurrentViewport(targetViewport);
        animationListener.onAnimationFinished();
    }

    public void onAnimationRepeat(Animator animation) {
    }

    public void onAnimationStart(Animator animation) {
        animationListener.onAnimationStarted();
    }

    public boolean isAnimationStarted() {
        return animator.isStarted();
    }

    public void setChartAnimationListener(ChartAnimationListener animationListener) {
        if (null == animationListener)
            this.animationListener = new DummyChartAnimationListener();
        else this.animationListener = animationListener;
    }
}
