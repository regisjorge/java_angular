package br.com.CrudJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.CrudJava.dtos.CourseDto;
import br.com.CrudJava.dtos.CoursePageDto;
import br.com.CrudJava.mapper.CouseMapper;
import br.com.CrudJava.services.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService service;
	
	@Autowired
	private CouseMapper mapper;

	@GetMapping()
	public ResponseEntity<CoursePageDto> list( @RequestParam(name = "page",defaultValue = "0") @PositiveOrZero int page,@RequestParam(name="pageSize",defaultValue = "10") @Positive @Max(100) int pageSize) {
		return ResponseEntity.status(HttpStatus.OK).body(service.list(page,pageSize));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseDto> buscaCursoId(@PathVariable("id") @NotNull @Positive Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.buscaCursoId(id));
	}

	@PostMapping
	// @ResponseStatus(code=HttpStatus.CREATED)
	public ResponseEntity<CourseDto> create(@RequestBody @Valid @NotNull CourseDto curso) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(mapper.toEntity(curso)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseDto> update(@PathVariable("id") @NotNull @Positive Long id,
			@RequestBody @Valid @NotNull CourseDto curso) {
	
		return ResponseEntity.status(HttpStatus.OK).body(service.update(mapper.toEntity(curso), id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable("id") @NotNull @Positive Long id) {

		service.remove(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Removido com Sucesso");

	}
}
