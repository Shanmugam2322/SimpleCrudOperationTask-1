package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Employees;

public interface EmployeeRepository  extends JpaRepository<Employees, Long> {


}
