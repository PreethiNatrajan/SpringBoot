package com.nokia.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nokia.student.model.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

}
