package ir.mahdiparastesh.hellocharts.listener;

import ir.mahdiparastesh.hellocharts.model.Viewport;

public interface ViewportChangeListener {

    /**
     * Called when current viewport of chart changed. You should not modify that viewport.
     */
    void onViewportChanged(Viewport viewport);
}
