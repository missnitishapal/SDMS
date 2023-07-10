package com.nitisha.sdms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitisha.sdms.payloads.StudentDto;
import com.nitisha.sdms.services.StudentServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    
    @Autowired
    private StudentServices studentServices;

    @PostMapping("/")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
        System.out.println("Handler | L-RC createStudent");
        StudentDto createStudent = studentServices.createStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createStudent);
    }

    @GetMapping("/")
    public ResponseEntity<?> getStudents() {
        System.out.println("Handler | L-RC getStudents");
        List<StudentDto> students = studentServices.getStudents();
        if(!students.isEmpty()){
        return ResponseEntity.ok(students);}
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Data Found in DB !!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getSingleStudent(@PathVariable Integer id) {
        System.out.println("Handler | L-RC getSingleStudent");
        StudentDto studentById = studentServices.getStudentById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(studentById);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        System.out.println("Handler | L-RC deleteStudent");
        studentServices.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body("Student Deleted Successfully !!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentDto studentDto,@PathVariable("id") Integer id) {
        System.out.println("Handler | L-RC updateStudent");
        StudentDto updateStudent = studentServices.updateStudent(studentDto,id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateStudent);
    }
}
