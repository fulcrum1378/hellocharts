package ir.mahdiparastesh.hellocharts.animation;

import java.util.EventListener;

public interface ChartAnimationListener extends EventListener {

    void onAnimationStarted();

    void onAnimationFinished();
}
