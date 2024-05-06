package com.assigment3.Assigment3.DAO;

import com.assigment3.Assigment3.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int theId);
    List<Employee> findByFirstName(String theFirstName);
    void update(Employee theEmployee);
    void delete(Integer id);
    int deleteAll();
}
