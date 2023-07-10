package com.nitisha.sdms.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitisha.sdms.entities.Student;
import com.nitisha.sdms.exception.ResourceNotFoundException;
import com.nitisha.sdms.payloads.StudentDto;
import com.nitisha.sdms.repository.StudentRepository;
import com.nitisha.sdms.services.StudentServices;
@Service
public class StudentImpl implements StudentServices {
    
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student savedStudent = studentRepository.save(this.dtoToStudent(studentDto));
        return this.studentToDto(savedStudent);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto,Integer id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
        existingStudent.setName(studentDto.getName());
        existingStudent.setAge(studentDto.getAge());
        existingStudent.setGrade(studentDto.getGrade());
        Student updatedStudent = studentRepository.save(existingStudent);
        return this.studentToDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Integer id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
        studentRepository.delete(existingStudent);
    }

    @Override
    public StudentDto getStudentById(Integer id) {
        
        Student singleStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
        return this.studentToDto(singleStudent);
    }

    @Override
    public List<StudentDto> getStudents() {
        List<Student> findAll = studentRepository.findAll();
        List<StudentDto> listOfStudentDto = findAll.stream().map(student -> this.studentToDto(student)).collect(Collectors.toList());
        return listOfStudentDto;
    }

    /*  ************** Mappers Methodes ********************* */
    
    public Student dtoToStudent(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

    public StudentDto studentToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

}
