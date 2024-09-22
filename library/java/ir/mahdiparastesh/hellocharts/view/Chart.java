package ir.mahdiparastesh.hellocharts.view;

import ir.mahdiparastesh.hellocharts.animation.ChartAnimationListener;
import ir.mahdiparastesh.hellocharts.calculator.ChartCalculator;
import ir.mahdiparastesh.hellocharts.gesture.ChartTouchHandler;
import ir.mahdiparastesh.hellocharts.gesture.ContainerScrollType;
import ir.mahdiparastesh.hellocharts.gesture.ZoomType;
import ir.mahdiparastesh.hellocharts.listener.ViewportChangeListener;
import ir.mahdiparastesh.hellocharts.model.ChartData;
import ir.mahdiparastesh.hellocharts.model.SelectedValue;
import ir.mahdiparastesh.hellocharts.model.Viewport;
import ir.mahdiparastesh.hellocharts.renderer.AxesRenderer;
import ir.mahdiparastesh.hellocharts.renderer.ChartRenderer;

public interface Chart {

    ChartData getChartData();

    ChartRenderer getChartRenderer();

    void setChartRenderer(ChartRenderer renderer);

    AxesRenderer getAxesRenderer();

    ChartCalculator getChartCalculator();

    ChartTouchHandler getTouchHandler();

    void animationDataUpdate(float scale);

    void animationDataFinished();

    void startDataAnimation();

    void startDataAnimation(long duration);

    void cancelDataAnimation();

    boolean isViewportCalculationEnabled();

    void setViewportCalculationEnabled(boolean isEnabled);

    void setDataAnimationListener(ChartAnimationListener animationListener);

    void setViewportAnimationListener(ChartAnimationListener animationListener);

    void setViewportChangeListener(ViewportChangeListener viewportChangeListener);

    void callTouchListener();

    boolean isInteractive();

    void setInteractive(boolean isInteractive);

    boolean isZoomEnabled();

    void setZoomEnabled(boolean isZoomEnabled);

    boolean isScrollEnabled();

    void setScrollEnabled(boolean isScrollEnabled);

    void moveTo(float x, float y);

    void moveToWithAnimation(float x, float y);

    ZoomType getZoomType();

    void setZoomType(ZoomType zoomType);

    float getMaxZoom();

    void setMaxZoom(float maxZoom);

    float getZoomLevel();

    void setZoomLevel(float x, float y, float zoomLevel);

    void setZoomLevelWithAnimation(float x, float y, float zoomLevel);

    boolean isValueTouchEnabled();

    void setValueTouchEnabled(boolean isValueTouchEnabled);

    Viewport getMaximumViewport();

    void setMaximumViewport(Viewport maxViewport);

    Viewport getCurrentViewport();

    void setCurrentViewport(Viewport targetViewport);

    void setCurrentViewportWithAnimation(Viewport targetViewport);

    void setCurrentViewportWithAnimation(Viewport targetViewport, long duration);

    void resetViewports();

    boolean isValueSelectionEnabled();

    void setValueSelectionEnabled(boolean isValueSelectionEnabled);

    void selectValue(SelectedValue selectedValue);

    SelectedValue getSelectedValue();

    boolean isContainerScrollEnabled();

    void setContainerScrollEnabled(boolean isContainerScrollEnabled, ContainerScrollType containerScrollType);
}
