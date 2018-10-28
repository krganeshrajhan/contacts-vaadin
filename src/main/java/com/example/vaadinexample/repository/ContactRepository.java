package com.example.vaadinexample.repository;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {

    List<Contact> findByIdOrderById(Long id);

    @Query("select c from Contact c where MONTH(dob) = :month and DAY(dob) = :day")
    List<Contact> findByDob(@Param("month") Integer month, @Param("day") Integer day);


}
