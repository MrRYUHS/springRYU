package com.example.week01homework.start;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @GetMapping("/myinfo")
    public Person getPersons(){
        Person person = new Person();
        person.setName("류형수");
        person.setJob("개발자");
        person.setAge(31);
        person.setAddress("대구");
        return person;
    }
}
