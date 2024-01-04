package br.com.CrudJava.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.CrudJava.dtos.CourseDto;
import br.com.CrudJava.dtos.LessonDto;
import br.com.CrudJava.enums.Category;
import br.com.CrudJava.models.Course;
import br.com.CrudJava.models.Lesson;

@Component
public class CouseMapper {

	public CourseDto toDto(Course course) {
		if (course == null) {
			return null;
		}
		List<LessonDto> lessons = course.getLesson().stream()
				.map(lesson -> new LessonDto(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl(), lesson.getWatched() ))
				.collect(Collectors.toList());

		return new CourseDto("" + course.getId(), course.getName(), course.getCategory().getValue(), lessons);// ------------
	}

	public Course toEntity(CourseDto dto) {
		Course course = new Course();
		if (dto.id() != null && !dto.id().trim().equals("")) {
			course.setId(Long.parseLong(dto.id()));
		}
		course.setName((dto.name() != null) ? dto.name() : null);
		course.setCategory(convertCategoryValue(dto.category()));// --------------------------------------------

		List<Lesson> lessons = dto.lessons().stream().map(lessonDto -> {
			var lesson = new Lesson();
			lesson.setId(lessonDto.id());
			lesson.setName(lessonDto.name());
			lesson.setYoutubeUrl(lessonDto.youtubeUrl());
			lesson.setWatched(lessonDto.watched());
			lesson.setCourse(course);
			return lesson;
		}).collect(Collectors.toList());

		course.setLesson(lessons);
		return course;
	}

	public Category convertCategoryValue(String value) {
		if (value == null) {
			return null;
		}

		return switch (value) {
		case "front-end" -> Category.FRONTEND;
		case "back-end" -> Category.BACKEND;
		default -> throw new IllegalArgumentException("invalido value: " + value);
		};
	}

}
