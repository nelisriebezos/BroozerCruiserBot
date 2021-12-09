package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.Exceptions.CarException;
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

    public CarService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public Car create(Car car) throws SQLException, DatabaseException, CarException {
        try (Connection connection = cruiserDB.getConnection()) {
            SqlStatement stmt = new SqlStatement(connection, cruiserDB.getQueryString("car_create"));
            SequenceGenerator gen = new SequenceGenerator(connection, "seq_car");

            Long id = gen.getNextValue();
            car.setId(id);
            stmt.set("id", id);
            stmt.set("kmcounter", car.getKmCounter());

            if (car.getId() == null || car.getId() < 1) throw new CarException("Create error: Id is fout, " + car.getId());
            if (car.getKmCounter() < 1) throw new CarException("Create error: Kmcounter is fout, " + car.getKmCounter());

            stmt.executeUpdate();
            connection.commit();


            return car;
        } catch (SQLException e) {
            LOG.error("car_create SQL error: " + e.getMessage());
            throw new SQLException("Create error: " + e.getMessage());
        } catch (DatabaseException e) {
            LOG.error("car_create Database error: " + e.getMessage());
            throw new DatabaseException("Create error: " + e.getMessage());
        }
    }

    public Car update(Car car) throws SQLException {
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
            LOG.error("car_update error: " + e.getMessage());
            throw new SQLException("Update error: " + e.getMessage());
        }
    }

    public void delete(Long id) throws SQLException {
        try (Connection connection = cruiserDB.getConnection()) {
            SqlStatement stmt = new SqlStatement(connection, cruiserDB.getQueryString("car_delete"));
            stmt.set("id", id);

            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error("car_delete error: " + e.getMessage());
            throw new SQLException("Delete error: " + e.getMessage());
        }
    }

    public Car findById(Long id) throws Exception {
        try (Connection connection = cruiserDB.getConnection()) {
            SqlStatement stmt = new SqlStatement(connection, cruiserDB.getQueryString("car_findbyid"));
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            Car car = new Car();

            if (!rs.isBeforeFirst()) {
                throw new Exception("FindById error: nothing was found, " + id);
            } else {
                while (rs.next()) {
                    car.setId(rs.getLong(1));
                    car.setKmCounter(rs.getInt(2));
                }
            }

            System.out.println(car);

            rs.close();
            stmt.close();
            return car;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new SQLException("FindById error: " + e.getMessage());
        }
    }
}
