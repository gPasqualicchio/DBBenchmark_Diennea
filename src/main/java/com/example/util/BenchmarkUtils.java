package com.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;

public class BenchmarkUtils {

    private static final List<Long> insertTimingList = new ArrayList<>();
    private static final List<Long> selectTimingList = new ArrayList<>();

    private BenchmarkUtils(){}

    public static void addInsertTimingElem(Long nanos){
        insertTimingList.add(nanos);
    }

    public static LongSummaryStatistics getInsertStat(){
        return insertTimingList.stream()
                .mapToLong((x) -> x)
                .summaryStatistics();
    }

    public static void addSelectTimingElem(long nanos){
        selectTimingList.add(nanos);
    }

    public static LongSummaryStatistics getSelectStat(){
        return selectTimingList.stream()
                .mapToLong((x) -> x)
                .summaryStatistics();
    }

}
