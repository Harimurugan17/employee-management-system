package org.example.service;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees() {
        Employee emp1 = new Employee(1L, "John", "Doe", "john@example.com");
        Employee emp2 = new Employee(2L, "Jane", "Doe", "jane@example.com");
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getEmployeeById_Found() {
        Employee emp = new Employee(1L, "John", "Doe", "john@example.com");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));

        Employee result = employeeService.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void createEmployee() {
        Employee emp = new Employee(null, "John", "Doe", "john@example.com");
        Employee savedEmp = new Employee(1L, "John", "Doe", "john@example.com");
        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmp);

        Employee result = employeeService.createEmployee(emp);
        assertNotNull(result.getId());
        assertEquals("John", result.getFirstName());
    }
}
