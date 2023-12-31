package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name="Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name="student_email_unique",columnNames = "email")
        }
)
public class Student {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize=1
    )
    @GeneratedValue(
          strategy = SEQUENCE,
          generator =  "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name="email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;
    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}
    )
    private StudentIdCard studentIdCard;
    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade ={CascadeType.PERSIST,CascadeType.REMOVE}
    )
    private List<Book> books=new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            mappedBy = "student"

    )
//    @JoinTable(
//            name = "enrollment",
//            joinColumns = @JoinColumn(
//                    name = "student_id",
//                    foreignKey = @ForeignKey(
//                            name = "enrollment_student_id_fk"
//                    )
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "course_id",
//                    foreignKey = @ForeignKey(name = "enrollment_course_id_fk")
//            )
//    )
    private List<Enrollment>enrollments=new ArrayList<>();

    public Student( String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void addBook(Book book){
        if(!this.books.contains(book)){
            this.books.add(book);
            book.setStudent(this);
        }
    }
    public void removeBook(Book book){
        if(this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
    public void addEnrollment(Enrollment enrollment){
        if(!enrollments.contains(enrollment)){
            enrollments.add(enrollment);
        }
    }
    public void removeEnrollment(Enrollment enrollment){
            enrollments.remove(enrollment);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(age, student.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, age);
    }
}
