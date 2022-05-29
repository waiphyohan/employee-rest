package com.example.employee.service;

import com.example.employee.dto.EmployeeCreateDto;
import com.example.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeCreateDto employeeDto);
    List<EmployeeDto> findAllEmployees();
    EmployeeDto findById(long id);
    EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto);
    void deleteEmployeeById(long id);
}
