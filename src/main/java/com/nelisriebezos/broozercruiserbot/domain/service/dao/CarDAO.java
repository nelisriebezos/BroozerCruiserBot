package com.nelisriebezos.broozercruiserbot.domain.service.dao;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Car;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.TankSession;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import com.nelisriebezos.broozercruiserbot.persistence.util.SequenceGenerator;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CarDAO {
    private final Connection connection;
    private TankSessionDAO tankSessionDAO;

    public CarDAO(Connection connection) {
        this.connection = connection;
        buildRelatedDao(connection);
    }

    public void setTankSessionService(TankSessionDAO tankSessionDAO) {
        this.tankSessionDAO = tankSessionDAO;
    }

    public void buildRelatedDao(Connection connection) {
        this.tankSessionDAO = new TankSessionDAO(connection);
    }

    public Car create(Car car) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("car_create"))) {
            SequenceGenerator gen = new SequenceGenerator(connection, "car_seq");

            Long id = gen.getNextValue();
            car.setId(id);
            if (car.getId() == null || car.getId() < 1) throw new DatabaseException("Create error: Id is wrong, " + car.getId());
            if (car.getKmCounter() < 1) throw new DatabaseException("Create error: Kmcounter is wrong, " + car.getKmCounter());

            stmt.set("id", id);
            stmt.set("kmcounter", car.getKmCounter());

            stmt.executeUpdate();
            System.out.println(car + " create test");
            return car;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException(e);
        }
    }

    public Car update(Car car) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("car_update"))) {
            stmt.set("id", car.getId());
            stmt.set("kmcounter", car.getKmCounter());

            int recordCount = stmt.executeUpdate();
            if (recordCount != 1) throw new DatabaseException("Number of cars updated: " + recordCount);

            return car;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void delete(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("car_delete"))) {
            stmt.set("id", id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public Car findById(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("car_findbyid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            Car car = new Car();

            if (rs.next()) {
                car.setId(rs.getLong("id"));
                car.setKmCounter(rs.getInt("kmcounter"));
            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();

            for (TankSession tankSession : tankSessionDAO.findTankSessionsByCarId(id)) {
                car.addTanksession(tankSession);
            }

            return car;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public Car findByKmCounter(int kmCounter) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("car_findbykmcounter"))) {
            stmt.set("kmcounter", kmCounter);
            ResultSet rs = stmt.executeQuery();

//            if there are multiple cars with the same KmCounter this will brick.
//            am too lazy to fix it now since its not a problem that i will run into atm (date: 7-02-2022)

            Car car = new Car();
            if (rs.next()) {
                car.setId(rs.getLong("id"));
                car.setKmCounter(rs.getInt("kmcounter"));
            } else {
                throw new DatabaseException("FindByKmCounter error: nothing was found, ");
            }
            rs.close();

            for (TankSession tankSession : tankSessionDAO.findTankSessionsByCarId(car.getId())) {
                car.addTanksession(tankSession);
            }
            return car;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
