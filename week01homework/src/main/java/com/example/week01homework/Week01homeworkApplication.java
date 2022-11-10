package com.example.week01homework;

import com.example.week01homework.models.PersonRequestDto;
import com.example.week01homework.service.PersonService;
import com.example.week01homework.start.Person;
import com.example.week01homework.start.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class Week01homeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week01homeworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PersonRepository personRepository, PersonService personService){
        return (args) -> {
            personRepository.save(new Person("류형수","개발자",31));

            System.out.println("ㅎㅎㅎㅎㅎ");
            List<Person> personList =personRepository.findAll();
            for (int i=0; i<personList.size(); i++){
                Person person = personList.get(i);
                System.out.println(person.getId());
                System.out.println(person.getName());
                System.out.println(person.getJob());
                System.out.println(person.getAge());
            }

            PersonRequestDto requestDto = new PersonRequestDto("김혜린","개발자",26);
            personService.update(1L, requestDto);
            personList = personRepository.findAll();
            for (int i=0; i<personList.size(); i++) {
                Person person = personList.get(i);
                System.out.println(person.getId());
                System.out.println(person.getName());
                System.out.println(person.getJob());
                System.out.println(person.getAge());
            }
        };
    }
}
