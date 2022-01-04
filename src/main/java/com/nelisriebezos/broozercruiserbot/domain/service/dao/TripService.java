package com.nelisriebezos.broozercruiserbot.domain.service.dao;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
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
    private TankSessionService tankSessionService;
    private PersonService personService;

    public TripService(Connection connection) {
        this.connection = connection;
    }

    public TankSessionService getTankSessionService() {
        return tankSessionService;
    }

    public void setTankSessionService(TankSessionService tankSessionService) {
        this.tankSessionService = tankSessionService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public Trip create(Trip trip) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_create"))) {
            SequenceGenerator gen = new SequenceGenerator(connection, "trip_seq");

            Long id = gen.getNextValue();
            trip.setId(id);

            if (trip.getId() == null || trip.getId() < 1)
                throw new DatabaseException("Create error: id is wrong, " + trip.getId());
            if (trip.getMileageInKm() < 1)
                throw new DatabaseException("Create error: mileageInKm is wrong, " + trip.getMileageInKm());
            if (trip.getDate() == null)
                throw new DatabaseException("Create error: timestamp is wrong, " + trip.getDate());
            if (trip.getTankSessionId() == null || trip.getTankSessionId() < 1)
                throw new DatabaseException("Create error: tanksessionid is wrong, " + trip.getTankSessionId());
            if (trip.getPersonList() == null || trip.getPersonList().size() < 1)
                throw new DatabaseException("Create error: personlost is wrong, " + trip.getPersonList());

            stmt.set("id", id);
            stmt.set("mileageInKm", trip.getMileageInKm());
            stmt.set("timestamp", trip.getDate());
            stmt.set("tanksessionid", trip.getTankSessionId());

            stmt.executeUpdate();

            for (Person person : trip.getPersonList()) {
                createTripPerson(id, person.getId());
            }

            return trip;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException(e);
        }
    }

    public Trip update(Trip trip) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_update"))) {

            if (trip.getPersonList() == null || trip.getPersonList().size() < 1)
                throw new DatabaseException("Create error: personlost is wrong, " + trip.getPersonList());

            stmt.set("id", trip.getId());
            stmt.set("mileageInKm", trip.getMileageInKm());
            stmt.set("timestamp", trip.getDate());
            stmt.set("tanksessionid", trip.getTankSessionId());

            deleteTripPersonById(trip.getId());

            for (Person person : trip.getPersonList()) {
                createTripPerson(trip.getId(), person.getId());
            }

            int recordCount = stmt.executeUpdate();
            if (recordCount != 1) throw new DatabaseException("Number of trips updated: " + recordCount);

            return trip;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void delete(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_delete"))) {

            deleteTripPersonById(id);

            stmt.set("id", id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public Trip findById(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_findbyid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            Trip trip = new Trip();
            long tanksessionId;

            if (rs.next()) {
                trip.setId(rs.getLong("id"));
                trip.setMileageInKm(rs.getInt("mileageInKm"));
                trip.setDate(rs.getDate("timestamp"));
                tanksessionId = rs.getLong("tanksessionId");
            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();

            trip.setTankSession(tankSessionService.findById(tanksessionId));

            for (Person person : personService.findPersonsByTripId(id)) {
                trip.addPerson(person);
            }

            return trip;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException(e);
        }
    }

    public List<Trip> findTripsByTankSessionId(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_findby_tanksessionid"))) {

            stmt.set("tanksessionid", id);
            ResultSet rs = stmt.executeQuery();

            List<Trip> tripList = new ArrayList<>();

            while (rs.next()) {
                tripList.add(
                        new Trip(
                                rs.getLong("id"),
                                rs.getInt("mileageInKm"),
                                rs.getDate("timestamp")
                        )
                );
            }
            return tripList;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void createTripPerson(Long tripId, Long personId) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_person_create"))) {

            if (tripId == null || tripId < 1) throw new DatabaseException("Create error, tripid is wrong, " + tripId);
            if (personId == null || personId < 1) throw new DatabaseException("Create error, personid is wrong, " + personId);

            stmt.set("tripid", tripId);
            stmt.set("personid", personId);

            stmt.executeUpdate();
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException(e);
        }
    }

    public void deleteTripPersonById(Long tripId) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_person_delete_viatrip"))) {
            stmt.set("tripid", tripId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
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
                                rs.getInt("mileageInKm"),
                                rs.getDate("timestamp")
                        )
                );
            }

            return tripList;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}

