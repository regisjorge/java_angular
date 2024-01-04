package br.com.CrudJava.dtos;

import java.util.List;


public record CoursePageDto(List<CourseDto> courses, long totalElements, int totalPages) {


}
