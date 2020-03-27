package com.nokia.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokia.student.exception.RecordNotFoundException;
import com.nokia.student.model.StudentEntity;
import com.nokia.student.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public List<StudentEntity> getAllStudents()
	{
		List<StudentEntity> result = (List<StudentEntity>) studentRepository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<StudentEntity>();
		}
	}
	
	public StudentEntity getStudentById(Long id) throws RecordNotFoundException 
	{
		Optional<StudentEntity> student = studentRepository.findById(id);
		
		if(student.isPresent()) {
			return student.get();
		} else {
			throw new RecordNotFoundException("No student record exist for given id");
		}
	}
	
	public StudentEntity createOrUpdateStudent(StudentEntity entity) 
	{
		if(entity.getId()  == 0) 
		{
			entity = studentRepository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<StudentEntity> student = studentRepository.findById(entity.getId());
			
			if(student.isPresent()) 
			{
				StudentEntity newEntity = student.get();
				newEntity.setDept(entity.getDept());
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());

				newEntity = studentRepository.save(newEntity);
				
				return newEntity;
			} else {
				entity = studentRepository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void deleteStudentById(Long id) throws RecordNotFoundException 
	{
		Optional<StudentEntity> student = studentRepository.findById(id);
		
		if(student.isPresent()) 
		{
			studentRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No student record exist for given id");
		}
	} 
}
