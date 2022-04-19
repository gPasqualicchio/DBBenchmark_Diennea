package com.example.dao;

import java.sql.SQLException;
import java.util.List;

public interface CarDao {
    void generateCars(int n) throws SQLException;

    void generateCar() throws SQLException;

    String findCardModelById(int carId) throws SQLException;

    List<Integer> getCarIdList() throws SQLException;
}
