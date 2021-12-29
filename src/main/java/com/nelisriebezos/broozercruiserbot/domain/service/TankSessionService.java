package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Correction;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import com.nelisriebezos.broozercruiserbot.persistence.util.SequenceGenerator;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TankSessionService {
    private final Connection connection;

    public TankSessionService(Connection connection) {
        this.connection = connection;
    }

    public TankSession create(TankSession tankSession) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_create"))) {
            SequenceGenerator gen = new SequenceGenerator(connection, "tanksession_seq");

            Long id = gen.getNextValue();
            tankSession.setId(id);
            stmt.set("id", id);
            stmt.set("timestamp", tankSession.getTimestamp());
            stmt.set("carid", tankSession.getCarId());

            if (tankSession.getId() == null || tankSession.getId() < 1)
                throw new DatabaseException("Create error: id is wrong, " + tankSession.getId());
            if (tankSession.getTimestamp() == null)
                throw new DatabaseException("Create error: timestamp is wrong, " + tankSession.getTimestamp());
            if (tankSession.getCarId() == null || tankSession.getCarId() < 1)
                throw new DatabaseException("Create error: carId is wrong, " + tankSession.getCarId());

            stmt.executeUpdate();
            return tankSession;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Create error", e);
        }
    }

    public TankSession update(TankSession tankSession) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_update"))) {
            stmt.set("id", tankSession.getId());
            stmt.set("timestamp", tankSession.getTimestamp());
            stmt.set("carid", tankSession.getCarId());

            int recordCount = stmt.executeUpdate();
            if (recordCount != 1) throw new DatabaseException("Number of tanksessions updated: " + recordCount);

            return tankSession;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Update error", e);
        }
    }

    public void delete(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_delete"))) {
            stmt.set("id", id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Delete error", e);
        }
    }

    public TankSession findnById(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_findbyid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            TankSession tankSession = new TankSession();

            if (rs.next()) {
                tankSession.setId(rs.getLong("id"));
                tankSession.setTimestamp(rs.getTimestamp("timestamp"));
            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();
            return tankSession;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("FindById error", e);
        }
    }

    public List<TankSession> findAll() throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_findall"))) {
            ResultSet rs = stmt.executeQuery();

            List<TankSession> tankSessionList = new ArrayList<>();

            while (rs.next()) {
                tankSessionList.add(
                        new TankSession(
                                rs.getLong("id"),
                                rs.getTimestamp("timestamp")
                        )
                );
            }


            return tankSessionList;
        } catch (SQLException e) {
            throw new DatabaseException("FindById error", e);
        }
    }

    public TankSession findByCarId(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("tanksession_findby_carid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            TankSession tankSession = new TankSession();

            if (rs.next()) {
                tankSession.setId(rs.getLong("id"));
                tankSession.setTimestamp(rs.getTimestamp("timestamp"));
            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();
            return tankSession;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("FindById error", e);
        }
    }
}
