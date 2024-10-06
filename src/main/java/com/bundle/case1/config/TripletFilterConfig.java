package com.bundle.case1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "triplet-filter")
public class TripletFilterConfig {
    private int sumThreshold = 90;
    private int groupSize = 3;

    public int getSumThreshold() {
        return sumThreshold;
    }

    public void setSumThreshold(int sumThreshold) {
        this.sumThreshold = sumThreshold;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }
}
