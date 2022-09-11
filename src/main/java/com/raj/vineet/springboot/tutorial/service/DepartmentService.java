package com.raj.vineet.springboot.tutorial.service;
/*
 * in this interface, we define our own specs as to what services would be provided
 * */

import com.raj.vineet.springboot.tutorial.entity.Department;
import com.raj.vineet.springboot.tutorial.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public List<Department> fetchDepartmentList();

    public Department saveDepartment(Department department);

    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartmentById(Long departmentId, Department department);

    public Department fetchDepartmentByName(String departmentName);
}
