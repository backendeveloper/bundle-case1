package com.bundle.case1.utility;

import com.bundle.case1.config.TripletFilterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TripletFilter {
    private final TripletFilterConfig config;

    @Autowired
    public TripletFilter(TripletFilterConfig config) {
        this.config = config;
    }

    public List<Integer> filterTriplets(List<Integer> integers) {
        if (integers == null || integers.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();
        int groupSize = config.getGroupSize();
        int sumThreshold = config.getSumThreshold();

        for (int i = 0; i <= integers.size() - groupSize; i += groupSize) {
            List<Integer> triplet = integers.subList(i, i + groupSize);
            int sum = triplet.stream().mapToInt(Integer::intValue).sum();
            if (sum >= sumThreshold) {
                result.addAll(triplet);
            }
        }

        return result;
    }
}
