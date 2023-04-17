package ru.semenova.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.semenova.FirstSecurityApp.models.Person;
import ru.semenova.FirstSecurityApp.repositories.PeopleRepository;
import ru.semenova.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = peopleRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found!"));

        return new PersonDetails(person);
    }
}
