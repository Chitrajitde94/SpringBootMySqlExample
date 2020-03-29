package com.springboot.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.example.exception.ServiceException;
import com.springboot.example.model.Employee;
import com.springboot.example.repository.EmployeeRespository;

@Service
public class EmployeeDAO {
	
	@Autowired
	EmployeeRespository employeeRespository;
	
	public Employee create(Employee emp) {
		return employeeRespository.save(emp);
	}
	
	public Employee findOne(long id) throws ServiceException {
		
		Optional<Employee> op = employeeRespository.findById(id);
		if(op.isPresent()) {
			return op.get();
		}else throw new ServiceException("No such employee to fetch");
	}
	
	public List<Employee> findAll() {
		
		return employeeRespository.findAll();
	}
	
	public Employee update(long id,Employee emp) throws ServiceException {
		Optional<Employee> op = employeeRespository.findById(id);
		if(op.isPresent()) {
			Employee empupdate=op.get();
			empupdate.setCreateAt(emp.getCreateAt());
			empupdate.setDesignation(emp.getDesignation());
			empupdate.setEnterprise(emp.getEnterprise());
			empupdate.setName(emp.getName());
			return create(empupdate);
		}else {
			throw new ServiceException("No such employee to update");
		}
		
	}
	
	public void delete(long id) throws ServiceException {
		Optional<Employee> op = employeeRespository.findById(id);
		if(op.isPresent()) {
			employeeRespository.deleteById(id);
		}else throw new ServiceException("No such employee to delete");
	}

}
