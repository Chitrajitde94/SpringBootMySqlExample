package com.springboot.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.example.dao.EmployeeDAO;
import com.springboot.example.exception.ServiceException;
import com.springboot.example.model.Employee;
import com.springboot.example.model.ErrorResponse;

@RestController
@RequestMapping("/emp/v1")
public class EmployeeController {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@PostMapping(value="/createEmployee",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createEmployee(@Valid @RequestBody Employee emp) {
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeDAO.create(emp));
		
	}
	
	@GetMapping(value="/findEmployee/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findEmployee(@PathVariable("id") long id) throws ServiceException {
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeDAO.findOne(id));
		
	}
	
	@GetMapping(value="/findAllEmployee",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findAllEmployee() {
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeDAO.findAll());
		
	}
	
	@PutMapping(value="/updateEmployee/{id}",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateEmployee(@Valid @RequestBody Employee emp,@PathVariable("id") long id) throws ServiceException {
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeDAO.update(id, emp));
		
	}
	
	@DeleteMapping(value="/deleteEmployee/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteEmployee(@PathVariable("id") long id) throws ServiceException {
		
		employeeDAO.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity handle(ServiceException ex) {
		
		ErrorResponse resp = new ErrorResponse();
		resp.setErrorCode("400");
		resp.setErrorMessage(ex.getMsg());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		
	}

}
