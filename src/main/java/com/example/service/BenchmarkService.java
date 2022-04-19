package com.example.service;

import java.sql.SQLException;

public interface BenchmarkService {
    void registerInsertStatementPerformance(int carsToGenerate) throws SQLException;
    void registerSelectStatementPerformance(int selectToExecute) throws SQLException;
    void printBenchmarkPerformance();
}
