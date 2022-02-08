package com.nelisriebezos.broozercruiserbot.persistence.querytest.persontest;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PersonCrudTest  extends DatabaseTest {
    @Test
    public void createPositive() throws DatabaseException {
        person.setName("testName");
        Person createdPerson = personDAO.create(person);
        assertEquals(createdPerson, personDAO.findById(createdPerson.getId()));
    }

    @Test
    public void createNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                personDAO.create(person));
    }

    @Test
    public void updatePositive() throws DatabaseException {
        person.setName("testName");
        Person createdPerson = personDAO.create(person);
        createdPerson.setName("testName2");
        Person updatedPerson = personDAO.update(createdPerson);
        assertEquals(updatedPerson, personDAO.findById(createdPerson.getId()));
    }

    @Test
    public void updateNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
            personDAO.update(person));
    }

    @Test
    public void deletePositive() throws DatabaseException {
        person.setName("testName");
        Person createdPerson = personDAO.create(person);
        personDAO.delete(createdPerson.getId());

        Assertions.assertThrows(DatabaseException.class, () ->
                personDAO.findById(createdPerson.getId()));
    }

    @Test
    public void findByNamePositive() throws DatabaseException {
        person.setName("testName");
        Person createdPerson = personDAO.create(person);
        assertEquals(createdPerson, personDAO.findByName(createdPerson.getName()));
    }

    @Test
    public void findByNameNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                personDAO.findByName("wrongName"));
    }

    @Test
    public void findPersonByTripId() throws DatabaseException {
        List<Person> personList = new ArrayList<>();
        personList.add(personDAO.findById(1L));
        assertEquals(personList, personDAO.findPersonsByTripId(1L));
    }
}
