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
        Person createdPerson = personService.create(person);
        assertEquals(createdPerson, personService.findById(createdPerson.getId()));
    }

    @Test
    public void createNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                personService.create(person));
    }

    @Test
    public void updatePositive() throws DatabaseException {
        person.setName("testName");
        Person createdPerson = personService.create(person);
        createdPerson.setName("testName2");
        Person updatedPerson = personService.update(createdPerson);
        assertEquals(updatedPerson, personService.findById(createdPerson.getId()));
    }

    @Test
    public void updateNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
            personService.update(person));
    }

    @Test
    public void deletePositive() throws DatabaseException {
        person.setName("testName");
        Person createdPerson = personService.create(person);
        personService.delete(createdPerson.getId());

        Assertions.assertThrows(DatabaseException.class, () ->
                personService.findById(createdPerson.getId()));
    }

    @Test
    public void findByNamePositive() throws DatabaseException {
        person.setName("testName");
        Person createdPerson = personService.create(person);
        assertEquals(createdPerson, personService.findByName(createdPerson.getName()));
    }

    @Test
    public void findByNameNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                personService.findByName("wrongName"));
    }

    @Test
    public void findPersonByTripId() throws DatabaseException {
        List<Person> personList = new ArrayList<>();
        personList.add(personService.findById(1L));
        assertEquals(personList, personService.findPersonByTripId(1L));
    }
}
