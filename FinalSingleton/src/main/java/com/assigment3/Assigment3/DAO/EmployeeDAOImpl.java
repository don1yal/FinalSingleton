package com.assigment3.Assigment3.DAO;

import com.assigment3.Assigment3.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    private EntityManager entityManager;
    @Autowired
    public EmployeeDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee ", Employee.class);
        List<Employee> employees = theQuery.getResultList();
        return employees;
    }
    @Override
    public Employee findById(int theId) {
        Employee theEmployee = entityManager.find(Employee.class,theId);
        return theEmployee;
    }
    @Override
    public List<Employee> findByFirstName(String theFirstName) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "FROM Employee where firstName=:first_name", Employee.class);
        query.setParameter("first_name", theFirstName);
        return query.getResultList();
    }
    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Employee ").executeUpdate();
        return numRowsDeleted;
    }
    @Override
    @Transactional
    public void delete(Integer id) {
        Employee theEmployee = entityManager.find(Employee.class,id);
        entityManager.remove(theEmployee);
    }
    @Override
    @Transactional
    public void update(Employee theEmployee) {
        entityManager.merge(theEmployee);
    }

}
