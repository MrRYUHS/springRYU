package com.example.week01homework.service;

import com.example.week01homework.models.PersonRequestDto;
import com.example.week01homework.start.Person;
import com.example.week01homework.start.PersonRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService (PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Transactional
    public Long update(Long id, PersonRequestDto requestDto){
        Person person1 = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다")
        );
        person1.update(requestDto);
        return person1.getId();
    }
}
