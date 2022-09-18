package com.raj.vineet.springboot.tutorial.controller;

import com.raj.vineet.springboot.tutorial.entity.Department;
import com.raj.vineet.springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;
    //since from controller, we hit service layer. so creating fake service bean

    private Department department;

    @BeforeEach
    void setUp() {
        /* this is output object..i.e. fake output which our func returns */
        department = Department.builder()
                .departmentName("chemical")
                .departmentCode("CE-06")
                .departmentAddress("Chennai")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        /* this is input object ie parameter we give to fake saveDepartment function */
        Department inputDepartment = Department.builder()
                .departmentName("chemical")
                .departmentCode("CE-06")
                .departmentAddress("Chennai")
                .build(); //this obj will be without deptId

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"departmentName\":\"chemical\",\n" +
                                "    \"departmentAddress\":\"Chennai\",\n" +
                                "    \"departmentCode\":\"CE-06\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);

        //calling the GET method
        mockMvc.perform(get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName") //kya json se mila dept name
                        .value(department.getDepartmentName())); //is matching with actual dept name
    }
}
