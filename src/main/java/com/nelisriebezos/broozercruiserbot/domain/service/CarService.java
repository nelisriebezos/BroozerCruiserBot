package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Car;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.util.SequenceGenerator;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;


public class CarService {
    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);
    private final CruiserDB cruiserDB;

    public CarService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public Car create(Car car) {
        try (Connection connection = cruiserDB.getConnection();
             SqlStatement stmt = new SqlStatement(connection, cruiserDB.getQueryString("car_create"))) {
            SequenceGenerator gen = new SequenceGenerator(connection, "seq_car");

            Long id = gen.getNextValue();
            car.setId(id);
            stmt.set("id", id);
            stmt.set("kmcounter", car.getKmCounter());

            stmt.executeUpdate();
            connection.commit();

            stmt.close();
            connection.close();

            return car;
        } catch (SQLException | DatabaseException e) {
            LOG.info("car_create error: " + e.getMessage());
            return null;
        }
    }

    public Car update(Car car) {
        try (Connection connection = cruiserDB.getConnection();
             SqlStatement stmt = new SqlStatement(connection, cruiserDB.getQueryString("car_update"))) {
            stmt.set("id", car.getId());
            stmt.set("kmcounter", car.getKmCounter());
            stmt.set("id", car.getId());

            stmt.executeUpdate();
            connection.commit();

            stmt.close();
            connection.close();

            return car;
        } catch (SQLException e) {
            LOG.info("car_create error: " + e.getMessage());
            return null;
        }
    }

    public Car delete(Long id) {
        return null;
    }

    public Car findById(Long id) {
        return null;
    }
}
