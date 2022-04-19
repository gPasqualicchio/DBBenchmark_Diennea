package com.example.dao.impl;

import com.example.dao.CarDao;
import com.example.util.BenchmarkUtils;
import com.example.util.DBConnectionUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
    private static final String INSERT_CAR = "INSERT INTO public.\"CAR\"(\"MODEL\") VALUES (?)";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM public.\"CAR\" WHERE \"ID\" = ?";
    private static final String SELECT_CAR_ID = "SELECT \"ID\" FROM public.\"CAR\"";

    private final Connection connection;

    public CarDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void generateCars(int n) throws SQLException {
        for(int i = 0; i < n; i++){
            generateCar();
        }
    }

    @Override
    public void generateCar() throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAR)){
            String randomString = RandomStringUtils.randomAlphabetic(10);

            preparedStatement.setString(1, randomString);

            long preSelectNanos = System.nanoTime();
            preparedStatement.executeUpdate();
            long postSelectNanos = System.nanoTime();
            long totNanos = postSelectNanos - preSelectNanos;

            BenchmarkUtils.addInsertTimingElem(totNanos);
            checkForCommitExecution();
        }
    }

    @Override
    public String findCardModelById(int carId) throws SQLException {
        String result = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID)){
            preparedStatement.setInt(1, carId);

            long preSelectNanos = System.nanoTime();
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()) {
                    long postSelectNanos = System.nanoTime();
                    long totNanos = postSelectNanos - preSelectNanos;

                    BenchmarkUtils.addSelectTimingElem(totNanos);

                    result = resultSet.getString(1);
                }
            }
        }
        return result;
    }

    private void checkForCommitExecution() throws SQLException {
        if(DBConnectionUtils.shouldCommitBeExecuted()){
            connection.commit();
        }
    }

    @Override
    public List<Integer> getCarIdList() throws SQLException {
        List<Integer> carIdList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_ID);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while(resultSet.next()){
                carIdList.add(resultSet.getInt(1));
            }
        }
        return carIdList;
    }
}
