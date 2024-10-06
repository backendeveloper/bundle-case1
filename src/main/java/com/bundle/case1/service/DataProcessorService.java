package com.bundle.case1.service;

import com.bundle.case1.utility.DataCleaner;
import com.bundle.case1.utility.TripletFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DataProcessorService implements IDataProcessorService {

    private final DataCleaner dataCleaner;
    private final TripletFilter tripletFilter;

    @Autowired
    public DataProcessorService(TripletFilter tripletFilter) {
        this.dataCleaner = new DataCleaner();
        this.tripletFilter = tripletFilter;
    }

    @Override
    public List<Integer> processData(List<List<String>> data) {
        if (data == null) {
            return Collections.emptyList();
        }

        List<String> cleanedData = dataCleaner.cleanData(data);
        List<Integer> integerList = dataCleaner.convertToIntegers(cleanedData);
        return tripletFilter.filterTriplets(integerList);
    }
}
