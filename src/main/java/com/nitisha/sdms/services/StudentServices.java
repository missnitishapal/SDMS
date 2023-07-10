package com.nitisha.sdms.services;

import java.util.List;

import com.nitisha.sdms.payloads.StudentDto;

public interface StudentServices {
    
    public StudentDto createStudent(StudentDto studentDto);

    public StudentDto updateStudent(StudentDto studentDto,Integer id);

    public void deleteStudent(Integer id);

    public StudentDto getStudentById(Integer id);

    public List<StudentDto> getStudents();
}
