package com.spring.employee_service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.employee_service.dto.APIResponseDto;
import com.spring.employee_service.dto.DepartmentDto;
import com.spring.employee_service.dto.EmployeeDto;
import com.spring.employee_service.dto.OrganizationDto;
import com.spring.employee_service.entity.Employee;
import com.spring.employee_service.mapper.EmployeeMapper;
import com.spring.employee_service.repository.EmployeeRepository;
import com.spring.employee_service.service.EmployeeService;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

//   private RestTemplate restTemplate;
    private WebClient webClient;
//    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    	LOGGER.info("inside getEmployeeById() method");
    	
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }


//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod= "getDefaultDepartment")
	@Retry(name="${spring.application.name}", fallbackMethod="getDefaultDepartment")
    @Override
	public APIResponseDto getEmployeeById(Long employeeId) {
		 Employee employee = employeeRepository.findById(employeeId).get();
		 
//		 ResponseEntity<DepartmentDto> reponseEntity=restTemplate.getForEntity("http://localhost:8080/api/departments/"+ employee.getDepartmentCode(), DepartmentDto.class);
//	      
//	     DepartmentDto departmentDto = reponseEntity.getBody();
	     
		 DepartmentDto departmentDto= webClient.get()
		 		  .uri("http://localhost:8080/api/departments/"+ employee.getDepartmentCode())
		 		  .retrieve()
		 		  .bodyToMono(DepartmentDto.class)
		 		  .block();
		 
//		 DepartmentDto departmentDto=apiClient.getDepartment(employee.getDepartmentCode());
		 OrganizationDto organizationDto= webClient.get()
		 		  .uri("http://localhost:8083/api/organizations/"+ employee.getOrganizationCode())
		 		  .retrieve()
		 		  .bodyToMono(OrganizationDto.class)
		 		  .block();
		 
		 EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

	     APIResponseDto apiResponseDto =  new APIResponseDto();
	     apiResponseDto.setEmployee(employeeDto);
	     apiResponseDto.setDepartment(departmentDto);
	     apiResponseDto.setOrganization(organizationDto);
	    
	        return apiResponseDto;
	}
    
    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
    	LOGGER.info("inside getDefaultDepartment() method");

        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }
}