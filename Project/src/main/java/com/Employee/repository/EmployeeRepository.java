package com.Employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Employee.model.*;

	@Repository
	public interface EmployeeRepository extends MongoRepository<Employee,String>{
	
		String countByGender(String gender);

}
