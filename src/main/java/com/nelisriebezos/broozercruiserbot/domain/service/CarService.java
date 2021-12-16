package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Car;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import com.nelisriebezos.broozercruiserbot.persistence.util.SequenceGenerator;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CarService {
    private final Connection connection;

    public CarService(Connection connection) {
        this.connection = connection;
    }

    public Car create(Car car) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("car_create"))) {
            SequenceGenerator gen = new SequenceGenerator(connection, "car_seq");

            Long id = gen.getNextValue();
            car.setId(id);
            stmt.set("id", id);
            stmt.set("kmcounter", car.getKmCounter());

            if (car.getId() == null || car.getId() < 1) throw new DatabaseException("Create error: Id is fout, " + car.getId());
            if (car.getKmCounter() < 1) throw new DatabaseException("Create error: Kmcounter is fout, " + car.getKmCounter());

            stmt.executeUpdate();
            return car;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Create error", e);
        }
    }

    public Car update(Car car) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("car_update"))) {
            stmt.set("id", car.getId());
            stmt.set("kmcounter", car.getKmCounter());
            stmt.set("id", car.getId());

            int recordCount = stmt.executeUpdate();
            if (recordCount != 1) throw new DatabaseException("Number of cars updated: " + recordCount);

            return car;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Update error", e);
        }
    }

    public void delete(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("car_delete"))) {
            stmt.set("id", id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Delete error", e);
        }
    }

    public Car findById(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("car_findbyid"))) {

            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            Car car = new Car();

            if (rs.next()) {
                car.setId(rs.getLong(1));
                car.setKmCounter(rs.getInt(2));
            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();
            return car;
        } catch (SQLException e) {
            throw new DatabaseException("FindById error", e);
        }
    }
}
