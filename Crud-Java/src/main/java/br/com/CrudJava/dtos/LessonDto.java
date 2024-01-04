package br.com.CrudJava.dtos;

public record LessonDto(
		Long id,
		String name,
		String youtubeUrl,
		Boolean watched
		) {}
