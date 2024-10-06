package com.bundle.case1;

import com.bundle.case1.utility.DataCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataCleanerTest {
    private DataCleaner dataCleaner;

    @BeforeEach
    public void setUp() {
        dataCleaner = new DataCleaner();
    }

    @Test
    public void testCleanData_withNullInput() {
        List<String> expectedOutput = Collections.emptyList();

        List<String> actualOutput = dataCleaner.cleanData(null);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCleanData_withEmptyInput() {
        List<List<String>> inputData = Collections.emptyList();
        List<String> expectedOutput = Collections.emptyList();

        List<String> actualOutput = dataCleaner.cleanData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCleanData_withNullInnerLists() {
        List<List<String>> inputData = Arrays.asList(
                null,
                Arrays.asList("10", null, "20"),
                null
        );
        List<String> expectedOutput = Arrays.asList("10", "20");

        List<String> actualOutput = dataCleaner.cleanData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCleanData_withEmptyAndWhitespaceStrings() {
        List<List<String>> inputData = List.of(
                Arrays.asList("   ", "", "\t", "\n", "25", "  30  ")
        );
        List<String> expectedOutput = Arrays.asList("25", "  30  ");

        List<String> actualOutput = dataCleaner.cleanData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCleanData_withMixedValidAndInvalidData() {
        List<List<String>> inputData = Arrays.asList(
                Arrays.asList("100", null, "abc", "200", "", "300", "   "),
                Arrays.asList("400", "def", null, "500")
        );
        List<String> expectedOutput = Arrays.asList("100", "abc", "200", "300", "400", "def", "500");

        List<String> actualOutput = dataCleaner.cleanData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testConvertToIntegers_withNullInput() {
        List<Integer> expectedOutput = Collections.emptyList();

        List<Integer> actualOutput = dataCleaner.convertToIntegers(null);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testConvertToIntegers_withEmptyInput() {
        List<String> inputData = Collections.emptyList();
        List<Integer> expectedOutput = Collections.emptyList();

        List<Integer> actualOutput = dataCleaner.convertToIntegers(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testConvertToIntegers_withNonNumericStrings() {
        List<String> inputData = Arrays.asList("abc", "def", "ghi");
        List<Integer> expectedOutput = Collections.emptyList();

        List<Integer> actualOutput = dataCleaner.convertToIntegers(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testConvertToIntegers_withMixedData() {
        List<String> inputData = Arrays.asList("100", "abc", "200", "def", "300");
        List<Integer> expectedOutput = Arrays.asList(100, 200, 300);

        List<Integer> actualOutput = dataCleaner.convertToIntegers(inputData);

        assertEquals(expectedOutput, actualOutput);
    }
}
