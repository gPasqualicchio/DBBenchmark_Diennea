package com.example;

import com.example.service.impl.BenchmarkServiceImpl;
import com.example.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static final int selectExecutionNumber = 22;
    private static final int insertExecutionNumber = 13;

    public static void main(String... args) throws SQLException, IOException {
        try(Connection connection = DBConnectionUtils.getConnection()){
            BenchmarkServiceImpl benchmarkService = new BenchmarkServiceImpl(connection);

            benchmarkService.recordInsertStatementPerformance(insertExecutionNumber);
            benchmarkService.recordSelectStatementPerformance(selectExecutionNumber);

            benchmarkService.printBenchmarkPerformance();
        }
    }
}
