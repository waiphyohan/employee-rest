package com.example.employee.service.impl;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.mapper.EmployeeMapper;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEmployee(employeeDto);
        return saveAndReturnDTO(employee);
    }

    @Override
    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> {
                    return employeeMapper.toEmployeeDto(employee);
                })

                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toEmployeeDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) {
        return employeeRepository.findById(id).map(employee -> {
            if (employeeDto.getTitle() != null) {
                employee.setTitle(employeeDto.getTitle());
            }

            if (employeeDto.getName() != null) {
                employee.setName(employeeDto.getName());
            }

            if (employeeDto.getEmail() != null) {
                employee.setEmail(employeeDto.getEmail());
            }

            if (employeeDto.getPhone() != null) {
                employee.setPhone(employeeDto.getPhone());
            }

            return employeeMapper.toEmployeeDto(employeeRepository.save(employee));

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDto saveAndReturnDTO(Employee employee){
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeDto(savedEmployee);
    }
}
