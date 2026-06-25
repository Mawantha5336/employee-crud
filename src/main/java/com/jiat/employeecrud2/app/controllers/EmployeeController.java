package com.jiat.employeecrud2.app.controllers;

import com.jiat.employeecrud2.app.dao.EmployeeDAO;
import com.jiat.employeecrud2.app.entity.Employee;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;

@Path("/employee")
public class EmployeeController {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getEmployee(@PathParam("id") int id) {
        Employee employee = employeeDAO.getById(id);
        if (employee != null) {
            String responseText = "Name: " + employee.getName() + "\n"
                    + "Position: " + employee.getPosition() + "\n"
                    + "Department: " + employee.getDepartment() + "\n"
                    + "Hire Date: " + employee.getHireDate() + "\n"
                    + "Salary: " + employee.getSalary();
            return Response.ok(responseText).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Employee not found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (!employees.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (Employee employee : employees) {
                result.append("Employee ID: ").append(employee.getId()).append("\n")
                        .append("Name: ").append(employee.getName()).append("\n")
                        .append("Position: ").append(employee.getPosition()).append("\n")
                        .append("Department: ").append(employee.getDepartment()).append("\n")
                        .append("Hire Date: ").append(employee.getHireDate()).append("\n")
                        .append("Salary: ").append(employee.getSalary()).append("\n\n");
            }
            return Response.ok(result.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No employees found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addEmployee(
            @FormParam("name") String name,
            @FormParam("position") String position,
            @FormParam("department") String department,
            @FormParam("hireDate") Date hireDate,
            @FormParam("salary") Double salary) {

        Employee employee = new Employee();
        employee.setName(name);
        employee.setPosition(position);
        employee.setDepartment(department);
        employee.setHireDate(hireDate);
        employee.setSalary(salary);

        employeeDAO.saveEmployee(employee);

        return Response.status(Response.Status.CREATED)
                .entity("Employee added successfully.")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateEmployee(
            @PathParam("id") int id,
            @FormParam("name") String name,
            @FormParam("position") String position,
            @FormParam("department") String department,
            @FormParam("hireDate") Date hireDate,
            @FormParam("salary") Double salary) {

        Employee existingEmployee = employeeDAO.getById(id);
        if (existingEmployee == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Employee not found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        existingEmployee.setName(name);
        existingEmployee.setPosition(position);
        existingEmployee.setDepartment(department);
        existingEmployee.setHireDate(hireDate);
        existingEmployee.setSalary(salary);

        employeeDAO.updateEmployee(existingEmployee);

        return Response.ok("Employee updated successfully.")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        Employee employee = employeeDAO.getById(id);
        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Employee not found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        employeeDAO.deleteEmployee(id);

        return Response.ok("Employee deleted successfully.")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
