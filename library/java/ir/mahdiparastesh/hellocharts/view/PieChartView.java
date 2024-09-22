package ir.mahdiparastesh.hellocharts.view;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.core.view.ViewCompat;

import ir.mahdiparastesh.hellocharts.animation.PieChartRotationAnimator;
import ir.mahdiparastesh.hellocharts.gesture.PieChartTouchHandler;
import ir.mahdiparastesh.hellocharts.listener.DummyPieChartOnValueSelectListener;
import ir.mahdiparastesh.hellocharts.listener.PieChartOnValueSelectListener;
import ir.mahdiparastesh.hellocharts.model.ChartData;
import ir.mahdiparastesh.hellocharts.model.PieChartData;
import ir.mahdiparastesh.hellocharts.model.SelectedValue;
import ir.mahdiparastesh.hellocharts.model.SliceValue;
import ir.mahdiparastesh.hellocharts.provider.PieChartDataProvider;
import ir.mahdiparastesh.hellocharts.renderer.PieChartRenderer;

public class PieChartView extends AbstractChartView implements PieChartDataProvider {
    protected PieChartData data;
    protected PieChartOnValueSelectListener onValueTouchListener = new DummyPieChartOnValueSelectListener();
    protected PieChartRenderer pieChartRenderer;
    protected PieChartRotationAnimator rotationAnimator;

    public PieChartView(Context context) {
        this(context, null, 0);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        pieChartRenderer = new PieChartRenderer(context, this, this);
        touchHandler = new PieChartTouchHandler(context, this);
        setChartRenderer(pieChartRenderer);
        this.rotationAnimator = new PieChartRotationAnimator(this);
        setPieChartData(PieChartData.generateDummyData());
    }

    @Override
    public PieChartData getPieChartData() {
        return data;
    }

    @Override
    public void setPieChartData(PieChartData data) {
        if (null == data)
            this.data = PieChartData.generateDummyData();
        else this.data = data;

        super.onChartDataChange();
    }

    @Override
    public ChartData getChartData() {
        return data;
    }

    @Override
    public void callTouchListener() {
        SelectedValue selectedValue = chartRenderer.getSelectedValue();

        if (selectedValue.isSet()) {
            SliceValue sliceValue = data.getValues().get(selectedValue.getFirstIndex());
            onValueTouchListener.onValueSelected(selectedValue.getFirstIndex(), sliceValue);
        } else onValueTouchListener.onValueDeselected();
    }

    public PieChartOnValueSelectListener getOnValueTouchListener() {
        return onValueTouchListener;
    }

    public void setOnValueTouchListener(PieChartOnValueSelectListener touchListener) {
        if (null != touchListener) this.onValueTouchListener = touchListener;
    }

    public RectF getCircleOval() {
        return pieChartRenderer.getCircleOval();
    }

    public void setCircleOval(RectF originCircleOval) {
        pieChartRenderer.setCircleOval(originCircleOval);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public int getChartRotation() {
        return pieChartRenderer.getChartRotation();
    }

    public void setChartRotation(int rotation, boolean isAnimated) {
        if (isAnimated) {
            rotationAnimator.cancelAnimation();
            rotationAnimator.startAnimation(pieChartRenderer.getChartRotation(), rotation);
        } else {
            pieChartRenderer.setChartRotation(rotation);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public boolean isChartRotationEnabled() {
        if (touchHandler instanceof PieChartTouchHandler) {
            return ((PieChartTouchHandler) touchHandler).isRotationEnabled();
        } else {
            return false;
        }
    }

    public void setChartRotationEnabled(boolean isRotationEnabled) {
        if (touchHandler instanceof PieChartTouchHandler) {
            ((PieChartTouchHandler) touchHandler).setRotationEnabled(isRotationEnabled);
        }
    }

    public SliceValue getValueForAngle(int angle, SelectedValue selectedValue) {
        return pieChartRenderer.getValueForAngle(angle, selectedValue);
    }

    public float getCircleFillRatio() {
        return pieChartRenderer.getCircleFillRatio();
    }

    public void setCircleFillRatio(float fillRatio) {
        pieChartRenderer.setCircleFillRatio(fillRatio);
        ViewCompat.postInvalidateOnAnimation(this);
    }
}
