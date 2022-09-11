package com.raj.vineet.springboot.tutorial.repository;
/*
 * this interface, we are extending the JPARepo to our own dept repo
 * so that we can take benefit of its various pre-existing methods
 * */

import com.raj.vineet.springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByDepartmentName(String departmentName);

    public Department findByDepartmentNameIgnoreCase(String departmentName);
}
