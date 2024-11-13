package com.example.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Employees;
import com.example.Repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employees> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            
            throw new RuntimeException("Error retrieving employees", e);
        }
    }

    public Employees findById(Long id) {
        try {
            Optional<Employees> optionalEmployee = repository.findById(id);
            if (optionalEmployee.isPresent()) {
                return optionalEmployee.get();
            } else {
                throw new Exception("Employee not found with id: " + id);
            }
        } catch (Exception e) {
           
            throw new RuntimeException("Error retrieving employee with id: " + id, e);
        }
    }

    public Employees save(Employees employees) {
        try {
            return repository.save(employees);
        } catch (Exception e) {
             throw new RuntimeException("Error saving employee", e);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            
            throw new RuntimeException("Error deleting employee with id: " + id, e);
        }
    }
}
