package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.request.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.dto.response.EmployeeResponseDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.service.impl.EmployeeServiceImpl;
import org.apache.tomcat.jni.Library;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class EmploymentManagementServiceTests {

    /*@Autowired
    private EmployeeService employeeService;*/

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

/*    @Test
    void addEmployeeTest(){
        EmployeeRequestDTO employeeRequestDTO=new EmployeeRequestDTO();
        employeeRequestDTO.setAge(20);
        employeeRequestDTO.setName("Saurav");
        employeeRequestDTO.setPhoneNo("7025896547");
        Employee employee=new Employee();
        employeeRepository.save(employee);
        employeeServices.addEmployee(employeeRequestDTO);
        Mockito.verify(employeeRepository,Mockito.times(1)).save(employee);

    }*/

    @Test
     void EmployeeServiceTest_addEmployee(){
        Employee employee=Employee.builder().name("deepak")
                                            .age(22)
                                            .phoneNo("8592840425")
                                            .build();

        EmployeeRequestDTO employeeRequestDTO=EmployeeRequestDTO.builder().name("Abhijith")
                .age(19)
                .phoneNo("7034483113")
                .build();
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);

        EmployeeResponseDTO<Employee> employeeResponseDTO = employeeService.addEmployee(employeeRequestDTO);

        Assertions.assertThat(employeeResponseDTO).isNotNull();
    }

    @Test
    void EmployeeServiceTest_findAll(){
        Employee employee=Mockito.mock(Employee.class);

        List <Employee> list=Arrays.asList(employee,employee);
        when(employeeRepository.findAll()).thenReturn(list);
        EmployeeResponseDTO<List<Employee>> employeeResponseDTO=employeeService.findAll();
        Assertions.assertThat(employeeResponseDTO).isNotNull();
        Assertions.assertThat(employeeResponseDTO.getResult()).hasSize(2);
    }

    @Test
     void EmployeeServiceTest_update() {
        Long id = 1L;
        Employee employee = Employee.builder().name("akshay")
                .age(20)
                .phoneNo("9446920711")
                .build();

        EmployeeRequestDTO employeeRequestDTO = EmployeeRequestDTO.builder().name("Arya")
                .age(20)
                .phoneNo("9656867688")
                .build();

        when(employeeRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(employee));
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
        EmployeeResponseDTO<Employee> employeeResponseDTO = employeeService.update(id, employeeRequestDTO);

        Assertions.assertThat(employeeResponseDTO).isNotNull();

    }

        @Test
         void EmployeeServiceTest_delete(){
            Long id=1L;
            doNothing().when(employeeRepository).deleteById(id);
            employeeService.delete(id);
            verify(employeeRepository,times(1)).deleteById(id);

        }
    }




