package com.nitisha.sdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitisha.sdms.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    
}
