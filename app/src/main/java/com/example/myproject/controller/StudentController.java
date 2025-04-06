package com.example.myproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.entity.Student;
import com.example.myproject.service.StudensService;

import io.micrometer.common.util.StringUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudensService studentService;

    @GetMapping
    public ResponseEntity<?> getstudents(
        @RequestParam(name = "facilitator_id") int facilitatorId, 
        @RequestParam(required = false, defaultValue = "1") int page, 
        @RequestParam(required = false, defaultValue = "5") int limit,
        @RequestParam(required = false, defaultValue = "id") String sort,
        @RequestParam(required = false, defaultValue = "asc") String order,
        @RequestParam(name = "name_like", required = false) String nameLike,
        @RequestParam(name = "loginid_like", required = false) String loginIdLike) {

        Map<String, Object> conditionMap = buildConditionMap(facilitatorId, nameLike, loginIdLike);
        
        List<Student> students =  studentService.searchStudents(
            Integer.valueOf(page) - 1,
            Integer.valueOf(limit),
            sort,
            order,
            conditionMap
        );
        
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("students", students);
        response.put("totalCount", students.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Map<String, Object> buildConditionMap(int facilitatorId, String nameLike, String loginIdLike) {
        Map<String, Object> coditionMap = new HashMap<>();
        
        coditionMap.put("facilitatorId", facilitatorId);

        if (StringUtils.isNotBlank(nameLike)) {
            coditionMap.put("nameLike", nameLike);
        }

        if (StringUtils.isNotBlank(loginIdLike)) {
            coditionMap.put("loginIdLike", loginIdLike);
        }

        return coditionMap;
    }
}