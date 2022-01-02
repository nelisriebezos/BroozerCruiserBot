package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Correction;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import com.nelisriebezos.broozercruiserbot.persistence.util.SequenceGenerator;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CorrectionService {
    private final Connection connection;

    public CorrectionService(Connection connection) {
        this.connection = connection ;
    }

    public Correction create(Correction correction) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("correction_create"))) {
            SequenceGenerator gen = new SequenceGenerator(connection, "correction_seq");

            Long id = gen.getNextValue();
            correction.setId(id);

            if (correction.getId() == null || correction.getId() < 1)
                throw new DatabaseException("Create error: id is wrong, " + correction.getId());
            if (correction.getTimestamp() == null)
                throw new DatabaseException("Create error: timestamp is wrong, " + correction.getTimestamp());
            if (correction.getDistance() < 1)
                throw new DatabaseException("Create error: distance is wrong, " + correction.getDistance());
            if (correction.getPersonId() == null || correction.getPersonId() < 1)
                throw new DatabaseException("Create error: personid is wrong, " + correction.getPersonId());
            if (correction.getTankSessionId() == null || correction.getTankSessionId() < 1)
                throw new DatabaseException("Create error: tanksessionid is wrong, " + correction.getTankSessionId());

            stmt.set("id", id);
            stmt.set("timestamp", correction.getTimestamp());
            stmt.set("distance", correction.getDistance());
            stmt.set("personid", correction.getPersonId());
            stmt.set("tanksessionid", correction.getTankSessionId());

            stmt.executeUpdate();
            return correction;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Create error", e);
        }
    }

    public Correction update(Correction correction) throws DatabaseException{
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("correction_update"))) {
            stmt.set("id", correction.getId());
            stmt.set("timestamp", correction.getTimestamp());
            stmt.set("distance", correction.getDistance());
            stmt.set("personid", correction.getPersonId());
            stmt.set("tanksessionid", correction.getTankSessionId());

            int recordCount = stmt.executeUpdate();
            if (recordCount != 1) throw new DatabaseException("Number of corrections updated: " + recordCount);

            return correction;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Update error", e);
        }
    }

    public void delete(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("correction_delete"))) {
            stmt.set("id", id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Delete error", e);
        }
    }

    public Correction findById(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("correction_findbyid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            Correction correction = new Correction();

            if (rs.next()) {
                correction.setId(rs.getLong("id"));
                correction.setTimestamp(rs.getTimestamp("timestamp"));
                correction.setDistance(rs.getInt("distance"));

            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();
            return correction;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("FindById error", e);
        }
    }

    public Correction findByTankSessionId(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("correction_findby_tanksessionid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            Correction correction = new Correction();

            if (rs.next()) {
                correction.setId(rs.getLong("id"));
                correction.setTimestamp(rs.getTimestamp("timestamp"));
                correction.setDistance(rs.getInt("distance"));

            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();
            return correction;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("FindById error", e);
        }
    }

    public Correction findByPersonId(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("correction_findby_personid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            Correction correction = new Correction();

            if (rs.next()) {
                correction.setId(rs.getLong("id"));
                correction.setTimestamp(rs.getTimestamp("timestamp"));
                correction.setDistance(rs.getInt("distance"));

            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();
            return correction;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("FindById error", e);
        }
    }
}
