package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
//            generateStudents(studentRepository);
//            studentRepository.findAll(Sort.by(Sort.Direction.ASC,"firstName"))
//                    .forEach(student -> System.out.println(student.getFirstName()));

        };
    }

    private static void generateStudents(StudentRepository studentRepository) {
        Faker faker=new Faker();
        for (int i = 0; i <20; i++) {
            String firstName=faker.name().firstName();
            String lastName=faker.name().lastName();
            String email=String.format("%s.%s@gmail.com",firstName,lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            studentRepository.save(student);
        }
    }

}
