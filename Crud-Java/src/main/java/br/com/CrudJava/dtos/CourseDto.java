package br.com.CrudJava.dtos;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import br.com.CrudJava.enums.Category;
import br.com.CrudJava.enums.validation.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDto(
		String id,
		@NotNull @NotBlank @Length(min=3, max=100) String name,
		@NotNull @NotBlank @Length(max=100)@ValueOfEnum(enumClass = Category.class) String category,
		List<LessonDto> lessons
		) {
}
