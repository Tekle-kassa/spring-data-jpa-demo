package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentByEmail(String email);//this is the spring jpa query
    @Query("SELECT s FROM Student s WHERE s.email=?1")//this is jpql which can be
        // used to write queries by custom without using the jpa repository interface
    Optional<Student> findStudentByEmailJpql(String email);
    @Query(
            value = "SELECT * FROM Student WHERE email=?1",
            nativeQuery = true)//and this is native sql
    Optional<Student> findStudentByEmailNative(String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id=?1")
    int deleteStudentById(Long id);
}
