package ir.mahdiparastesh.hellocharts.provider;

import ir.mahdiparastesh.hellocharts.model.BubbleChartData;

public interface BubbleChartDataProvider {

    BubbleChartData getBubbleChartData();

    void setBubbleChartData(BubbleChartData data);
}
