package com.example.employee.mapper;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.model.Employee;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toEmployeeDto(Employee employee);
    List<EmployeeDto> toEmployeeDtos(List<Employee> employees);
    Employee toEmployee(EmployeeDto productDTO);
}
