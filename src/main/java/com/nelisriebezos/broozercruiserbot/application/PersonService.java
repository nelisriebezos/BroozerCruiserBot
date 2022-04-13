package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.data.PersonRepository;
import com.nelisriebezos.broozercruiserbot.domain.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class PersonService implements IPersonService {
    private final PersonRepository personRepository;

    @Override
    public Person getPerson(Long id) {
        return this.personRepository.getById(id);
    }

    @Override
    public Person persistPerson(Person person) {
        return this.personRepository.save(person);
    }

    @Override
    public boolean deletePerson(Person person) {
        this.personRepository.delete(person);
        return true;
    }
}
