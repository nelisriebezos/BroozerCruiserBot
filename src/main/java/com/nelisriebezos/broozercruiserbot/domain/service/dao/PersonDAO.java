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

public class PersonDAO {
    private final Connection connection;
    private TripDAO tripDAO;

    public PersonDAO(Connection connection) {
        this.connection = connection;
        buildRelatedDao(connection);
    }

    public void buildRelatedDao(Connection connection) {
        this.tripDAO = new TripDAO(connection);
    }

    public void setTripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public Person create(Person person) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("person_create"))) {
            SequenceGenerator gen = new SequenceGenerator(connection, "person_seq");

            Long id = gen.getNextValue();
            person.setId(id);

            if (person.getId() == null || person.getId() < 1) throw new DatabaseException("Create error: Id is wrong, " + person.getId());
            if (person.getName() == null) throw new DatabaseException("Create error: name is wrong, " + person.getName());

            stmt.set("id", id);
            stmt.set("name", person.getName());

            stmt.executeUpdate();
            return person;
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException(e);
        }
    }

    public Person update(Person person) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("person_update"))) {
            stmt.set("id", person.getId());
            stmt.set("name", person.getName());
            stmt.set("id", person.getId());

            int recordCount = stmt.executeUpdate();
            if (recordCount != 1) throw new DatabaseException("Number of persons updated: " + recordCount);

            return person;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void delete(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("person_delete"))) {
            stmt.set("id", id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public Person findById(Long id) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("person_findbyid"))) {
            stmt.set("id", id);
            ResultSet rs = stmt.executeQuery();

            Person person = new Person();

            if (rs.next()) {
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
            } else {
                throw new DatabaseException("FindById error: nothing was found, " + id);
            }

            rs.close();
            stmt.close();

            for (Trip trip : tripDAO.findTripsByPersonId(id)) {
                person.addTrip(trip);
            }

            return person;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public Person findByName(String name) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("person_findbyname"))) {
            stmt.set("name", name);
            ResultSet rs = stmt.executeQuery();

            Person person = new Person();

            if (rs.next()) {
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));

            } else {
                throw new DatabaseException("FindByName error: nothing was found, " + name);
            }

            rs.close();
            stmt.close();

            for (Trip trip : tripDAO.findTripsByPersonId(person.getId())) {
                person.addTrip(trip);
            }

            return person;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public List<Person> findPersonsByTripId(Long tripId) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_person_findpersonby_trip"))) {
            stmt.set("tripid", tripId);
            ResultSet rs = stmt.executeQuery();

            List<Person> personList = new ArrayList<>();

            while(rs.next()) {
                personList.add(new Person(rs.getLong("id"), rs.getString("name")));
            }

            return personList;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void deleteTripPersonById(Long personId) throws DatabaseException {
        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_person_delete_viaperson"))) {
            stmt.set("id", personId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
