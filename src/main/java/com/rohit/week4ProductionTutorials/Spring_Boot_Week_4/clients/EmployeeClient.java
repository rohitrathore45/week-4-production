package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.clients;

import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
