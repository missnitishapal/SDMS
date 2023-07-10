package com.nitisha.sdms.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class StudentDto {

    private Integer id;
    private String name;
    private Integer age;
    private Character grade;
}
