package com.example.service;

import java.sql.SQLException;

public interface BenchmarkService {
    void recordInsertStatementPerformance(int carsToGenerate) throws SQLException;
    void recordSelectStatementPerformance(int selectToExecute) throws SQLException;
    void printBenchmarkPerformance();
}
