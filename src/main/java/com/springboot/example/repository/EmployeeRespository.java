package com.springboot.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.example.model.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Long> {

}
