package com.raj.vineet.springboot.tutorial.service;

import com.raj.vineet.springboot.tutorial.entity.Department;
import com.raj.vineet.springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;
    @BeforeEach
    void setUp() {
        /* Note: Here we could've used constructor/getter-setters to set values in our dept.
        but since we're using builder pattern, so we're passing values like this
        * */
        Department  department = Department.builder()
                .departmentName("CSE")
                .departmentAddress("Kanpur")
                .departmentCode("CSE-01")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("CSE"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get data based on valid department name")
    public void whenValidDepartmentName_thenDepartmentShouldFound() {
        String departmentName = "CSE";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }
}
