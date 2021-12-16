package com.nelisriebezos.broozercruiserbot.persistence.querytest.persontest;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PersonCrudTest  extends DatabaseTest {
    @Test
    public void createPositive() {
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
    public void updatePositive() {
        person.setName("testName");
        Person createdPerson = personService.create(person);
        createdPerson.setName("testName2");
        Person updatedPerson = personService.update(person);
        assertEquals(updatedPerson, personService.findById(createdPerson.getId()));
    }

    @Test
    public void updateNegative() {

    }

    @Test
    public void deletePositive() {

    }

    @Test
    public void findByNamePositive() {

    }

    @Test
    public void findByNameNegative() {

    }

    @Test
    public void findAllTrips() {

    }
}
