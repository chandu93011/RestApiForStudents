package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    //inject the dao here
    private EmployeeService employeeService;

    //constructor injection
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }


    //reading the single employee so add mapping.
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee theEmployee=employeeService.findById(employeeId);

        if(theEmployee ==null){
            throw new RuntimeException("Employee  id is not present in the database"+employeeId);
        }
        return theEmployee;
    }

    // add mapping for POST /employees -adding new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        //also just in case they pass an id in JSON... set id to 0

        //this is to force a save of new item ... instead of update

        theEmployee.setId(0);

        Employee dbEmployee =employeeService.save(theEmployee);

        return  dbEmployee;
    }

    //for updating the changes in the database

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        Employee dbEmployee= employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for deleting the employee from the database
    @DeleteMapping("/employees/{employeeId}")
    public String deleteById(@PathVariable  int employeeId){

        Employee employee=employeeService.findById(employeeId);
        if(employee==null){
           throw new RuntimeException("Id is not found in the database....");
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id .."+employeeId;
    }

}
