package com.jiat.employeecrud2.app.dao;

import com.jiat.employeecrud2.app.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class EmployeeDAO {

    private final EntityManagerFactory entityManagerFactory;

    public EmployeeDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("employeecrud");
    }

    public Employee getById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> getAllEmployees() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public void saveEmployee(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateEmployee(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteEmployee(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void close() {
        entityManagerFactory.close();
    }
}
