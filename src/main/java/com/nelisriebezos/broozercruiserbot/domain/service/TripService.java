package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import com.nelisriebezos.broozercruiserbot.persistence.util.SequenceGenerator;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripService {
    private final Connection connection;

    public TripService(Connection connection) {
        this.connection = connection;
    }

    public Trip create(Trip trip) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_create"))) {
            SequenceGenerator gen = new SequenceGenerator(connection, "trip_seq");

            Long id = gen.getNextValue();
            trip.setId(id);
            stmt.set("id", id);
            stmt.set("distance", trip.getDistance());
            stmt.set("timestamp", trip.getTimestamp());
            stmt.set("tanksessionid", trip.getTankSessionId());

            if (trip.getId() == null || trip.getId() < 1)
                throw new DatabaseException("Create error: id is wrong, " + trip.getId());
            if (trip.getDistance() < 1)
                throw new DatabaseException("Create error: distance is wrong, " + trip.getDistance());
            if (trip.getTimestamp() == null)
                throw new DatabaseException("Create error: timestamp is wrong, " + trip.getTimestamp());
            if (trip.getTankSessionId() == null || trip.getTankSessionId() < 1)
                throw new DatabaseException("Create error: tanksessionid is wrong, " + trip.getTankSessionId());

            stmt.executeUpdate();
            return trip;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Create error", e);
        }
    }

    public Trip update(Trip trip) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_update"))) {
            stmt.set("id", trip.getId());
            stmt.set("distance", trip.getDistance());
            stmt.set("timestamp", trip.getTimestamp());
            stmt.set("tanksessionid", trip.getTankSessionId());

            int recordCount = stmt.executeUpdate();
            if (recordCount != 1) throw new DatabaseException("Number of trips updated: " + recordCount);

            return trip;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Update error", e);
        }
    }

    public void delete(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_delete"))) {
            stmt.set("id", id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Delete error", e);
        }
    }

    public Trip findById(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_findbyid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            Trip trip = new Trip();

            if (rs.next()) {
                trip.setId(rs.getLong("id"));
                trip.setDistance(rs.getInt("distance"));
                trip.setTimestamp(rs.getTimestamp("timestamp"));
            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();
            return trip;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("FindById error", e);
        }
    }

    public List<Trip> findByTankSessionId(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_findby_tanksessionid"))) {
            stmt.set("tanksessionid", id);
            ResultSet rs = stmt.executeQuery();

            List<Trip> tripList = new ArrayList<>();

            while (rs.next()) {
                tripList.add(
                        new Trip(
                                rs.getLong("id"),
                                rs.getInt("distance"),
                                rs.getTimestamp("timestamp")
                        )
                );
            }


            return tripList;
        } catch (SQLException e) {
            throw new DatabaseException("FindById error", e);
        }
    }

    public void createTrip_Person(Long tripId, Long personId) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_person_create"))) {
            stmt.set("tripid", tripId);
            stmt.set("personid", personId);

            if (tripId == null || tripId < 1) throw new DatabaseException("Create error, tripid is wrong, " + tripId);
            if (personId == null || personId < 1) throw new DatabaseException("Create error, personid is wrong, " + personId);

            stmt.executeUpdate();
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Create error", e);
        }
    }

    public void updateTrip_Person(Long tripId, Long personId) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_person_update"))) {
            stmt.set("tripid", tripId);
            stmt.set("personid", personId);

            if (tripId == null || tripId < 1) throw new DatabaseException("Create error, tripid is wrong, " + tripId);
            if (personId == null || personId < 1) throw new DatabaseException("Create error, personid is wrong, " + personId);


            int recordCount = stmt.executeUpdate();
            if (recordCount != 1) throw new DatabaseException("Number of trip_person updated: " + recordCount);
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Update error", e);
        }
    }

    public void deleteTrip_Person_byId(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_person_delete_viatrip"))) {
            stmt.set("id", id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Delete error", e);
        }
    }

    public List<Trip> findTripsByPersonId(Long personId) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_person_findtripsby_person"))) {
            stmt.set("personid", personId);
            ResultSet rs = stmt.executeQuery();

            List<Trip> tripList = new ArrayList<>();

            while(rs.next()) {
                tripList.add(
                        new Trip(
                                rs.getLong("id"),
                                rs.getInt("distance"),
                                rs.getTimestamp("timestamp")
                        )
                );
            }

            return tripList;
        } catch (SQLException e) {
            throw new DatabaseException("findTripsByPersonId error", e);
        }
    }
}

