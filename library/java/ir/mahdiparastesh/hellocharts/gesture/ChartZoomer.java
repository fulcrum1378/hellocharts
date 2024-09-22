package ir.mahdiparastesh.hellocharts.gesture;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

import ir.mahdiparastesh.hellocharts.calculator.ChartCalculator;
import ir.mahdiparastesh.hellocharts.model.Viewport;

public class ChartZoomer {
    public static final float ZOOM_AMOUNT = 0.25f;
    private final ZoomerCompat zoomer;
    private ZoomType zoomType;
    private final PointF zoomFocalPoint = new PointF();// Used for double tap zoom
    private final PointF viewportFocus = new PointF();
    private final Viewport scrollerStartViewport = new Viewport(); // Used only for zooms and flings

    public ChartZoomer(Context context, ZoomType zoomType) {
        zoomer = new ZoomerCompat();
        this.zoomType = zoomType;
    }

    public boolean startZoom(MotionEvent e, ChartCalculator calculator) {
        zoomer.forceFinished(true);
        scrollerStartViewport.set(calculator.getCurrentViewport());
        if (!calculator.rawPixelsToDataPoint(e.getX(), e.getY(), zoomFocalPoint)) {
            // Focus point is not within content area.
            return false;
        }
        zoomer.startZoom(ZOOM_AMOUNT);
        return true;
    }

    public boolean computeZoom(ChartCalculator calculator) {
        if (zoomer.computeZoom()) {
            // Performs the zoom since a zoom is in progress.
            final float newWidth = (1.0f - zoomer.getCurrZoom()) * scrollerStartViewport.width();
            final float newHeight = (1.0f - zoomer.getCurrZoom()) * scrollerStartViewport.height();
            final float pointWithinViewportX = (zoomFocalPoint.x - scrollerStartViewport.left)
                    / scrollerStartViewport.width();
            final float pointWithinViewportY = (zoomFocalPoint.y - scrollerStartViewport.bottom)
                    / scrollerStartViewport.height();

            float left = zoomFocalPoint.x - newWidth * pointWithinViewportX;
            float top = zoomFocalPoint.y + newHeight * (1 - pointWithinViewportY);
            float right = zoomFocalPoint.x + newWidth * (1 - pointWithinViewportX);
            float bottom = zoomFocalPoint.y - newHeight * pointWithinViewportY;
            setCurrentViewport(calculator, left, top, right, bottom);
            return true;
        }
        return false;
    }

    public boolean scale(ChartCalculator calculator, float focusX, float focusY, float scale) {
        final float newWidth = scale * calculator.getCurrentViewport().width();
        final float newHeight = scale * calculator.getCurrentViewport().height();
        if (!calculator.rawPixelsToDataPoint(focusX, focusY, viewportFocus)) {
            // Focus point is not within content area.
            return false;
        }

        float left = viewportFocus.x - (focusX - calculator.getContentRectMinusAllMargins().left)
                * (newWidth / calculator.getContentRectMinusAllMargins().width());
        float top = viewportFocus.y + (focusY - calculator.getContentRectMinusAllMargins().top)
                * (newHeight / calculator.getContentRectMinusAllMargins().height());
        float right = left + newWidth;
        float bottom = top - newHeight;
        setCurrentViewport(calculator, left, top, right, bottom);
        return true;
    }

    private void setCurrentViewport(ChartCalculator calculator, float left, float top, float right, float bottom) {
        Viewport currentViewport = calculator.getCurrentViewport();
        if (ZoomType.HORIZONTAL_AND_VERTICAL == zoomType) {
            calculator.setCurrentViewport(left, top, right, bottom);
        } else if (ZoomType.HORIZONTAL == zoomType) {
            calculator.setCurrentViewport(left, currentViewport.top, right, currentViewport.bottom);
        } else if (ZoomType.VERTICAL == zoomType) {
            calculator.setCurrentViewport(currentViewport.left, top, currentViewport.right, bottom);
        }
    }

    public ZoomType getZoomType() {
        return zoomType;
    }

    public void setZoomType(ZoomType zoomType) {
        this.zoomType = zoomType;
    }
}
