package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        StudentIdCardRepository studentIdCardRepository){
        return args -> {
//            generateStudents(studentRepository);
//            studentRepository.findAll(Sort.by(Sort.Direction.ASC,"firstName"))
//                    .forEach(student -> System.out.println(student.getFirstName()));
            Faker faker=new Faker();
            String firstName=faker.name().firstName();
            String lastName=faker.name().lastName();
            String email=String.format("%s.%s@gmail.com",firstName,lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));
            student.addBook(new Book("clean code", LocalDateTime.now().minusDays(4)));
            student.addBook(new Book("mastery", LocalDateTime.now()));
            student.addBook(new Book("the laws of human nature", LocalDateTime.now().minusYears(4)));

             StudentIdCard studentIdCard=new StudentIdCard("123456789",student);
             student.setStudentIdCard(studentIdCard);
             studentRepository.save(student);
//             studentIdCardRepository.save(studentIdCard);
             studentRepository.findById(1L).ifPresent(s->{
                 System.out.println("fetch books lazy...");
                 List<Book>books=student.getBooks();
                 books.forEach(book->{
                     System.out.println(s.getFirstName() + " borrowed "+book.getBookName());
                     });
             });
//             studentRepository.findById(1L).ifPresent(System.out::println);
//             studentIdCardRepository.findById(1L).ifPresent(System.out::println);
//             studentRepository.deleteById(1L);
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
