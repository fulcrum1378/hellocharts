package ir.mahdiparastesh.hellocharts.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;

import ir.mahdiparastesh.hellocharts.view.Chart;

public class ChartDataAnimator implements Animator.AnimatorListener,
        ValueAnimator.AnimatorUpdateListener {

    private final Chart chart;
    private final ValueAnimator animator;
    private ChartAnimationListener animationListener = new DummyChartAnimationListener();

    public ChartDataAnimator(Chart chart) {
        this.chart = chart;
        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addListener(this);
        animator.addUpdateListener(this);
    }

    public void startAnimation(long duration) {
        if (duration >= 0)
            animator.setDuration(duration);
        else animator.setDuration(500);
        animator.start();
    }

    public void cancelAnimation() {
        animator.cancel();
    }

    public void onAnimationUpdate(ValueAnimator animation) {
        chart.animationDataUpdate(animation.getAnimatedFraction());
    }

    public void onAnimationCancel(Animator animation) {
    }

    public void onAnimationEnd(Animator animation) {
        chart.animationDataFinished();
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
        if (null == animationListener) {
            this.animationListener = new DummyChartAnimationListener();
        } else {
            this.animationListener = animationListener;
        }
    }
}
