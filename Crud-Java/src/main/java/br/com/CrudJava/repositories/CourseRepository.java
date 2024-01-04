package br.com.CrudJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.CrudJava.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	//List<Course> findByStatus(boolean status);
	//Optional<Course> findByIdAndStatus(Long id, boolean status);
}
