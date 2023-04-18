package ir.mahdiparastesh.hellocharts.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;

import ir.mahdiparastesh.hellocharts.view.PieChartView;

public class PieChartRotationAnimator implements Animator.AnimatorListener,
        ValueAnimator.AnimatorUpdateListener {

    private final PieChartView chart;
    private final ValueAnimator animator;
    private float startRotation = 0;
    private float targetRotation = 0;
    private ChartAnimationListener animationListener = new DummyChartAnimationListener();

    public PieChartRotationAnimator(PieChartView chart) {
        this(chart, 200);
    }

    public PieChartRotationAnimator(PieChartView chart, long duration) {
        this.chart = chart;
        animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(duration);
        animator.addListener(this);
        animator.addUpdateListener(this);
    }


    public void startAnimation(float startRotation, float targetRotation) {
        this.startRotation = (startRotation % 360 + 360) % 360;
        this.targetRotation = (targetRotation % 360 + 360) % 360;
        animator.start();
    }

    public void cancelAnimation() {
        animator.cancel();
    }

    public void onAnimationUpdate(ValueAnimator animation) {
        float scale = animation.getAnimatedFraction();
        float rotation = startRotation + (targetRotation - startRotation) * scale;
        rotation = (rotation % 360 + 360) % 360;
        chart.setChartRotation((int) rotation, false);
    }

    public void onAnimationCancel(Animator animation) {
    }

    public void onAnimationEnd(Animator animation) {
        chart.setChartRotation((int) targetRotation, false);
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
