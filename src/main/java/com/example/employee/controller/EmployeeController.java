package com.example.employee.controller;

import com.example.employee.dto.EmployeeCreateDto;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get employees in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = " Get Employees",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.findAllEmployees();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @Operation(summary = "Get employee by Id in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = " Get Employee By Id",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = " Resource Not Found",
                    content = @Content)
    })
    @GetMapping({"/{id}"})
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @Operation(summary = "Create employee in the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = " Create Employee",
                content = {@Content(mediaType = "application/json")})
    })
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeCreateDto empDto) {
        EmployeeDto employeeDto = employeeService.saveEmployee(empDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update employee in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = " Update Employee",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping({"/{id}"})
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long id, @RequestBody EmployeeDto empDto) {
        EmployeeDto employeeDto = employeeService.updateEmployeeById(id, empDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @Operation(summary = "Delete employee by Id in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = " Delete Employee",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
