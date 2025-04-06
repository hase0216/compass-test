package com.example.myproject.service;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.myproject.entity.Student;
import com.example.myproject.repository.StudentRepository;
import com.example.myproject.repository.StudentSpecifications;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudensService {

    private final StudentRepository studentRepository;
    private final StudentSpecifications<Student> studentSpecification;
    private final ModelMapper modelMapper;

    public List<Student> searchStudents(Integer page,  Integer limit, String sort, String order, Map<String, Object> conditionMap) {

        Specification<Student> specs = studentSpecification.buildSpecifications(conditionMap);
        PageRequest pageRequest = buildPageRequest(page, limit, sort, order);
        Page<Student> paginatedstudent = studentRepository.findAll(specs, pageRequest);

        return modelMapper.map (
            paginatedstudent.getContent(),
            new TypeToken<List<Student>>() {
            }.getType());
    }

    private PageRequest buildPageRequest(Integer page, Integer limit, String sort, String order) {
        Direction direction = order.equals("asc") ? Direction.ASC : Direction.DESC;
        Sort sortOrder = Sort.by(direction, sort);
        return PageRequest.of(page, limit, sortOrder);
    }
}