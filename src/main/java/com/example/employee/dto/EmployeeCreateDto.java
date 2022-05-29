package com.example.employee.dto;

import lombok.Data;

@Data
public class EmployeeCreateDto {
    private String title;
    private String name;
    private String email;
    private String phone;
}
