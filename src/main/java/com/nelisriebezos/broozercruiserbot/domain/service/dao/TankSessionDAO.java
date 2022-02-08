package com.nelisriebezos.broozercruiserbot.domain.service.dao;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import com.nelisriebezos.broozercruiserbot.persistence.util.SequenceGenerator;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TankSessionDAO {
    private final Connection connection;
    private TripDAO tripDAO;

    public TankSessionDAO(Connection connection) {
        this.connection = connection;
    }

    public TripDAO getTripService() {
        return tripDAO;
    }

    public void setTripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public TankSession create(TankSession tankSession) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_create"))) {
            SequenceGenerator gen = new SequenceGenerator(connection, "tanksession_seq");

            Long id = gen.getNextValue();
            tankSession.setId(id);

            if (tankSession.getId() == null || tankSession.getId() < 1)
                throw new DatabaseException("Create error: id is wrong, " + tankSession.getId());
            if (tankSession.getDate() == null)
                throw new DatabaseException("Create error: timestamp is wrong, " + tankSession.getDate());
            if (tankSession.getCarId() == null || tankSession.getCarId() < 1)
                throw new DatabaseException("Create error: carId is wrong, " + tankSession.getCarId());

            stmt.set("id", id);
            stmt.set("timestamp", tankSession.getDate());
            stmt.set("carid", tankSession.getCarId());


            stmt.executeUpdate();
            return tankSession;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException(e);
        }
    }

    public TankSession update(TankSession tankSession) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_update"))) {
            if (tankSession.getDate() == null)
                throw new DatabaseException("Update error: timestamp is wrong, " + tankSession.getDate());
            if (tankSession.getCarId() == null || tankSession.getCarId() < 1)
                throw new DatabaseException("Update error: carId is wrong, " + tankSession.getCarId());


            stmt.set("id", tankSession.getId());
            stmt.set("timestamp", tankSession.getDate());
            stmt.set("carid", tankSession.getCarId());

            int recordCount = stmt.executeUpdate();
            if (recordCount != 1) throw new DatabaseException("Number of tanksessions updated: " + recordCount);

            return tankSession;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void delete(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_delete"))) {
            stmt.set("id", id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public TankSession findById(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_findbyid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            TankSession tankSession = new TankSession();

            if (rs.next()) {
                tankSession.setId(rs.getLong("id"));
                tankSession.setDate(rs.getDate("timestamp"));
            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();

            for (Trip trip : tripDAO.findTripsByTankSessionId(id)) {
                tankSession.addTrip(trip);
            }

            return tankSession;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException(e);
        }
    }

    public List<TankSession> findAll() throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_findall"))) {
            ResultSet rs = stmt.executeQuery();

            List<TankSession> tankSessionList = new ArrayList<>();

            while (rs.next()) {
                TankSession tankSession = new TankSession(rs.getLong("id"), rs.getDate("timestamp"));
                for (Trip trip : tripDAO.findTripsByTankSessionId(rs.getLong("id"))) {
                    tankSession.addTrip(trip);
                }
                tankSessionList.add(tankSession);
            }

            return tankSessionList;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public List<TankSession> findTankSessionsByCarId(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_findby_carid"))) {
            stmt.set("carid", id);
            ResultSet rs = stmt.executeQuery();

            List<TankSession> tankSessionList = new ArrayList<>();

            while (rs.next()) {
                TankSession tankSession = new TankSession(rs.getLong("id"), rs.getDate("timestamp"));
                for (Trip trip : tripDAO.findTripsByTankSessionId(rs.getLong("id"))) {
                    tankSession.addTrip(trip);
                }
                tankSessionList.add(tankSession);
            }

            return tankSessionList;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
