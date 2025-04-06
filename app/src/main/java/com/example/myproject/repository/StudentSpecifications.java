package com.example.myproject.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.example.myproject.entity.Student;

@Component
public class StudentSpecifications<T> {

    public Specification<Student> buildSpecifications(Map<String, Object> conditionMap) {
        Specification<Student> specs = Specification.where(null);

        for (Entry<String, Object> entry : conditionMap.entrySet()) {
            Object value = entry.getValue();

            switch (entry.getKey()) {
                case "facilitatorId" :
                    specs = specs.and(facilitatorIdEqual(value));
                    break;
                case "nameLike" :
                    specs = specs.and(hasNameLike(value));
                    break;
                case "loginIdLike" :
                    specs = specs.and(hasLoginIdLike(value));
                    break;
                default :
                    break;
            }
        }

        return specs;
    }
    
    public Specification<Student> facilitatorIdEqual(Object facilitatorId) {
        Integer target = Integer.valueOf(String.valueOf(facilitatorId));
        return (root, query, builder) -> builder.equal(root.join("facilitator").get("id"), target);
    }
    
    public Specification<Student> hasNameLike(Object nameLike) {
        String target = String.valueOf(nameLike);
        return StringUtils.isBlank(target) ? null 
            : (root, query, builder) -> builder.like(root.get("name"), "%" + target + "%");
    }

    public Specification<Student> hasLoginIdLike(Object loginIdLike) {
        String target = String.valueOf(loginIdLike);
        return StringUtils.isBlank(target) ? null 
        : (root, query, builder) -> builder.like(root.get("login_id"), "%" + loginIdLike + "%");
    }
}
