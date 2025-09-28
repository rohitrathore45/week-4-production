package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.clients.implementation;

import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.advice.ApiResponse;
import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.clients.EmployeeClient;
import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.dto.EmployeeDTO;
import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImplementation implements EmployeeClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImplementation.class);

    @Override
    public List<EmployeeDTO> getAllEmployee() {

        log.trace("Trying to retrieve all the employee in getAllEmployees");
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            (req, res) -> {
                                log.error(new String(res.getBody().readAllBytes()));
                                throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            log.debug("Successfully listed all the employees in getAllEmployees");
            log.trace("Retrieved employees list in getAllEmployees : {}", employeeDTOList.getData());
            return employeeDTOList.getData();
        } catch (Exception e){
            log.error("Exception occurred in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to retrieve employee by id in getEmployeeById : {}" , employeeId);
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("/employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            (req, res) -> {
                                log.error(new String(res.getBody().readAllBytes()));
                                throw new ResourceNotFoundException("could not find the employee by this id");
                            })
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeResponse.getData();
        } catch (Exception e){
            log.error("Exception occurred in getEmployeeById", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create Employee with information : {}", employeeDTO);
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            (req, res) -> {
                        log.debug("4xxclient error occurred during createNewEmployee");
                                log.error(new String(res.getBody().readAllBytes()));
                                throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            log.trace("Successfully created a new employee : {}", employeeDTOApiResponse.getData());
            return employeeDTOApiResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred in createNewEmployee");
            throw new RuntimeException(e);
        }
    }
}
