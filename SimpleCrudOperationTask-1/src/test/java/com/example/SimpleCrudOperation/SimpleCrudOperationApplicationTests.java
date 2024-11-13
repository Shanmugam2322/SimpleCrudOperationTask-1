package com.example.SimpleCrudOperation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Entity.Employees;
import com.example.Repository.EmployeeRepository;
import com.example.Services.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class SimpleCrudOperationApplicationTests {

	@Autowired
	private EmployeeService service;
	
	@MockBean
	private EmployeeRepository repository;
	
	
	 @BeforeEach
	    public void setUp() {
	        
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testFindAll() {
	        Employees employee1 = new Employees();
	        employee1.setId(1L);
	        employee1.setTitle("John Doe");

	        Employees employee2 = new Employees();
	        employee2.setId(2L);
	        employee2.setTitle("Jane Smith");

	        List<Employees> employeesList = Arrays.asList(employee1, employee2);

	        when(repository.findAll()).thenReturn(employeesList);

	        List<Employees> result = service.findAll();

	        // Assertions
	        assertNotNull(result);
	        assertEquals(2, result.size());
	        assertEquals("John Doe", result.get(0).getTitle());
	        assertEquals("Jane Smith", result.get(1).getTitle());
	    }

	    @Test
	    public void testFindById() {
	        Employees employee = new Employees();
	        employee.setId(1L);
	        employee.setTitle("John Doe");

	        when(repository.findById(1L)).thenReturn(Optional.of(employee));

	       Employees result = service.findById(1L);

	        // Assertions
	        assertNotNull(result);
	        assertEquals("John Doe", result.getTitle());
	    }

	    @Test
	    public void testSave() {
	        Employees employee = new Employees();
	        employee.setId(1L);
	        employee.setTitle("John Doe");

   	        when(repository.save(employee)).thenReturn(employee);
	        
	        Employees result = service.save(employee);

	        // Assertions
	        assertNotNull(result);
	        assertEquals("John Doe", result.getTitle());
	    }

	    @Test
	    public void testDelete() {
	        Employees employee = new Employees();
	        employee.setId(1L);
	        employee.setTitle("John Doe");

	        doNothing().when(repository).deleteById(1L);

	        service.delete(1L);

	        // Verify the repository method was called
	        verify(repository, times(1)).deleteById(1L);
	    }

}



