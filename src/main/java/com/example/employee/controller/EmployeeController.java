package com.example.employee.controller;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.findAllEmployees();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto empDto) {
        EmployeeDto employeeDto = employeeService.saveEmployee(empDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long id, @RequestBody EmployeeDto empDto) {
        EmployeeDto employeeDto = employeeService.updateEmployeeById(id, empDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
