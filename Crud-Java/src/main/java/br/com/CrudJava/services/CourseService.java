package br.com.CrudJava.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.CrudJava.dtos.CourseDto;
import br.com.CrudJava.dtos.CoursePageDto;
import br.com.CrudJava.exception.RecordNotFoundException;
import br.com.CrudJava.mapper.CouseMapper;
import br.com.CrudJava.models.Course;
import br.com.CrudJava.repositories.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repository;

	@Autowired
	private CouseMapper mapper;

//	public List<CourseDto> list() {
//		
//		return repository.findAll().stream().sorted().map(mapper::toDto).collect(Collectors.toList());
//	}

	public CoursePageDto list(int pageInit, int pageSize) {
		
		Page<Course> page=repository.findAll(PageRequest.of(pageInit, pageSize));
		List<CourseDto> courses=page.get().map(mapper :: toDto).collect(Collectors.toList());
		return new CoursePageDto(courses, page.getTotalElements(), page.getTotalPages());
	}
	
	public CourseDto create(Course curso) {
		Course c = repository.save(curso);
		return mapper.toDto(c);
	}

	public CourseDto buscaCursoId(Long id) {
		return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public CourseDto update(Course course, long id) {
		CourseDto curso = buscaCursoId(id);
		course.setId(Long.parseLong(curso.id()));
		//course.setCategory(convertCategoryValue(course.getCategory()));// --------------------------------
		return mapper.toDto(repository.save(course));
	}

	public void remove(Long id) {
		repository.delete(repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}

}
