package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepo){
        employeeRepo=theEmployeeRepo;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepo.findById(theId);
        Employee theEmployee=null;

        if(result.isPresent()){
        theEmployee=result.get();

        }
        else{
            //we didn't find the employee
            throw new RuntimeException("Didn't find employee id .."+ theId);
        }
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepo.save(theEmployee);
    }
    
    @Override
    public void deleteById(int theId) {
        employeeRepo.deleteById(theId);

    }
}
