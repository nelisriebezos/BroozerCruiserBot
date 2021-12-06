package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Car;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.TankSession;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.util.SequenceGenerator;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CarService {
    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);
    private final CruiserDB cruiserDB;
    private TankSessionService tankSessionService;

    public CarService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public Car create(Car car) {
        try (Connection connection = cruiserDB.getConnection()) {
            SqlStatement stmt = new SqlStatement(connection, cruiserDB.getQueryString("car_create"));
            SequenceGenerator gen = new SequenceGenerator(connection, "seq_car");



            Long id = gen.getNextValue();
            car.setId(id);
            stmt.set("id", id);
            stmt.set("kmcounter", car.getKmCounter());

            stmt.executeUpdate();
            connection.commit();

            for (TankSession tankSession : car.getTanksessionList()) {
                tankSessionService.create(tankSession);
            }

            return car;
        } catch (SQLException e) {
            LOG.info("car_create SQL error: " + e.getMessage());
            return null;
        } catch (DatabaseException e) {
            LOG.info("car_create Database error: " + e.getMessage());
            return null;
        }
    }

    public Car update(Car car) {
        try (Connection connection = cruiserDB.getConnection()) {
            SqlStatement stmt = new SqlStatement(connection, cruiserDB.getQueryString("car_update"));
            stmt.set("id", car.getId());
            stmt.set("kmcounter", car.getKmCounter());
            stmt.set("id", car.getId());

            stmt.executeUpdate();
            connection.commit();

            stmt.close();
            connection.close();

            return car;
        } catch (SQLException e) {
            LOG.info("car_update error: " + e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try (Connection connection = cruiserDB.getConnection()) {
            SqlStatement stmt = new SqlStatement(connection, cruiserDB.getQueryString("car_delete"));
            stmt.set("id", id);

            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.info("car_delete error: " + e.getMessage());
        }
    }

    public Car findById(Long id) {
        try (Connection connection = cruiserDB.getConnection()) {
            SqlStatement stmt = new SqlStatement(connection, cruiserDB.getQueryString("car_findbyid"));
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();
            Car car = new Car();
            while (rs.next()) {
                car.setId(rs.getLong(1));
                car.setKmCounter(rs.getInt(2));
            }
            rs.close();
            stmt.close();
            return car;
        } catch (SQLException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }
}
