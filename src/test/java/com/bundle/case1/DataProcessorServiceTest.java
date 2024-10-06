package com.bundle.case1;

import com.bundle.case1.config.TripletFilterConfig;
import com.bundle.case1.service.DataProcessorService;
import com.bundle.case1.utility.DataCleaner;
import com.bundle.case1.utility.TripletFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import({DataProcessorService.class, TripletFilterConfig.class, TripletFilter.class})
public class DataProcessorServiceTest {
    private DataProcessorService dataProcessorService;

    @MockBean
    private DataCleaner dataCleaner;

    @BeforeEach
    public void setUp() {
        TripletFilterConfig tripletFilterConfig = new TripletFilterConfig();
        tripletFilterConfig.setGroupSize(3);
        tripletFilterConfig.setSumThreshold(90);

        TripletFilter tripletFilter = new TripletFilter(tripletFilterConfig);
        dataCleaner = new DataCleaner();
        dataProcessorService = new DataProcessorService(tripletFilter);
    }

    @Test
    public void testProcessData_withSampleData() {
        List<List<String>> inputData = Arrays.asList(
                Arrays.asList("0", "s1", null, "35", "90", "60"),
                Arrays.asList("ttt", null, null, "15"),
                Arrays.asList("75", "95", "0", "0", null, "ssss", "0", "-15"),
                Arrays.asList("25", "fgdfg", "", "105", "dsfdsf", "-5")
        );

        List<Integer> expectedOutput = Arrays.asList(0, 35, 90, 60, 15, 75, 95, 0, 0);
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_withEmptyInput() {
        List<List<String>> inputData = Collections.emptyList();

        List<Integer> expectedOutput = Collections.emptyList();
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_withNullInput() {
        List<Integer> expectedOutput = Collections.emptyList();
        List<Integer> actualOutput = dataProcessorService.processData(null);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_withNonNumericStrings() {
        List<List<String>> inputData = Arrays.asList(
                Arrays.asList("abc", "def", "ghi"),
                Arrays.asList("jkl", "mno", "pqr")
        );

        List<Integer> expectedOutput = Collections.emptyList();
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_withNegativeNumbers() {
        List<List<String>> inputData = List.of(
                Arrays.asList("-10", "-20", "-30", "-40", "-50", "-60")
        );

        List<Integer> expectedOutput = Collections.emptyList();
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_withMixedValidAndInvalidData() {
        List<List<String>> inputData = List.of(
                Arrays.asList("100", "invalid", "50", "40", "not a number", "30", null, "20", "10")
        );

        List<Integer> expectedOutput = List.of(100, 50, 40);
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_withInsufficientTriplet() {
        List<List<String>> inputData = List.of(
                Arrays.asList("30", "40")
        );

        List<Integer> expectedOutput = Collections.emptyList();
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_tripletSumBelowThreshold() {
        List<List<String>> inputData = Arrays.asList(
                Arrays.asList("10", "20", "30"),
                Arrays.asList("15", "15", "15")
        );

        List<Integer> expectedOutput = Collections.emptyList();
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_tripletSumAtThreshold() {
        List<List<String>> inputData = List.of(
                Arrays.asList("30", "30", "30")
        );

        List<Integer> expectedOutput = Arrays.asList(30, 30, 30);
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_withLargeNumbers() {
        List<List<String>> inputData = List.of(
                Arrays.asList("1000", "2000", "3000")
        );

        List<Integer> expectedOutput = Arrays.asList(1000, 2000, 3000);
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_withZeros() {
        List<List<String>> inputData = List.of(
                Arrays.asList("0", "0", "0", "0", "0", "0")
        );

        List<Integer> expectedOutput = Collections.emptyList();
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testProcessData_withWhitespaceStrings() {
        List<List<String>> inputData = List.of(
                Arrays.asList("   ", "\t", "\n", "30", "60", "   0  ")
        );

        List<Integer> expectedOutput = Arrays.asList(30, 60, 0);
        List<Integer> actualOutput = dataProcessorService.processData(inputData);

        assertEquals(expectedOutput, actualOutput);
    }
}
