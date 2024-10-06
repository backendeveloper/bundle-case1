package com.bundle.case1.utility;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataCleaner {
    public List<String> cleanData(List<List<String>> data) {
        if (data == null) {
            return Collections.emptyList();
        }

        return data.stream()
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .filter(this::isValidString)
                .collect(Collectors.toList());
    }

    public List<Integer> convertToIntegers(List<String> data) {
        if (data == null) {
            return Collections.emptyList();
        }

        return data.stream()
                .filter(this::isNumeric)
                .map(str -> Integer.parseInt(str.trim()))
                .collect(Collectors.toList());
    }

    private boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    private boolean isNumeric(String str) {
        return str.trim().matches("-?\\d+");
    }
}
