package com.example.service.impl;

import com.example.dao.CarDao;
import com.example.dao.impl.CarDaoImpl;
import com.example.service.BenchmarkService;
import com.example.util.BenchmarkUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.ThreadLocalRandom;

public class BenchmarkServiceImpl implements BenchmarkService {

    private final CarDao carDao;

    public BenchmarkServiceImpl(Connection connection){
        this.carDao = new CarDaoImpl(connection);
    }

    @Override
    public void registerInsertStatementPerformance(int carsToGenerate) throws SQLException {
        for(int i = 0; i < carsToGenerate; i++){
            carDao.generateCar();
        }
    }

    @Override
    public void registerSelectStatementPerformance(int selectToExecute) throws SQLException {
        List<Integer> carIdList = carDao.getCarIdList();

        for(int i = 0; i < selectToExecute; i++){
            int elemIndexToFind = ThreadLocalRandom.current().nextInt(0, carIdList.size());
            carDao.findCardModelById(carIdList.get(elemIndexToFind));
        }
    }

    @Override
    public void printBenchmarkPerformance() {
        printBenchmarkInsertPerformance();
        printBenchmarkSelectPerformance();
    }

    private void printBenchmarkInsertPerformance() {
        LongSummaryStatistics insertStat = BenchmarkUtils.getInsertStat();
        System.out.println("/***********************************");
        System.out.println("/* INSERT statistics for "+insertStat.getCount()+" transactions (nanos):");
        System.out.println("/* Min: "+insertStat.getMin());
        System.out.println("/* Max: "+insertStat.getMax());
        System.out.println("/* Avg: "+insertStat.getAverage());
        System.out.println("/*****");
    }

    private void printBenchmarkSelectPerformance() {
        LongSummaryStatistics selectStat = BenchmarkUtils.getSelectStat();
        System.out.println("/***********************************");
        System.out.println("/* SELECT statistics for "+selectStat.getCount()+" transactions (nanos):");
        System.out.println("/* Min: "+selectStat.getMin());
        System.out.println("/* Max: "+selectStat.getMax());
        System.out.println("/* Avg: "+selectStat.getAverage());
        System.out.println("/*****");
    }
}
